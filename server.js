var http = require('http');
var url = require('url');
var parse = require('parse');
//parse.initialize("mSjyVccMyMBh8fxcY6zkIYlDEXiXgwjngbKoiFM8", "IA5wobRXq295jg2YucJ7TavgM8pD4848K2FlOknj");
var qs = require('querystring');
var request = require('request');

var server = http.createServer( function(req, res) {

    var answers = [];
    var sessions = [];

    var sessionExists = function(sess) {
      for (i = 0; i < sessions.length; i++) {
        if (sessions[i] == sess) {
          return true;
        }
      }
      return false;
    };

    var sessionIndex = function(sess) {
      for (i = 0; i < sessions.length; i++) {
        if (sessions[i] == sess) {
          return i;
        }
      }
      return "no session found";
    };

    res.writeHead(200, {
        'content-type': 'application/json;charset=utf-8',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'GET, POST, OPTIONS, PUT, PATCH, DELETE',
        'Access-Control-Allow-Headers': 'X-Requested-With,content-type'
    });
    
    var parsedUrl = url.parse(req.url, true);
    var resp = {};

    var body = '';
    req.on('data', function (data) {
      body += data;

      // Too much POST data, kill the connection!
      if (body.length > 1e6)
        request.connection.destroy();
    });

    var resp = {};

    req.on('end', function () {
      var post = qs.parse(body);


      if (post.requestType == "webApp") {
        if (sessionExists(post.session)) {
          console.log(post);
          resp.consoleLog = post;
          console.log(req.method);
          resp.success = true;
          resp.vals = answers;    
          res.end(JSON.stringify(resp));
          answers = [];
        }
        else {
          sessions.push(post.session);
          answers.push([]);
          console.log(post);
          resp.consoleLog = post;
          console.log(req.method);
          resp.success = true;     
          res.end(JSON.stringify(resp));
        }
      }
      else if (post.requestType == "client") {
        if (sessionExists(post.session)) {
          answers[sessionIndex(post.session)].push(post.answer);
  
          console.log(post);
          resp.consoleLog = post;
          console.log(req.method);
          resp.success = true;     
          res.end(JSON.stringify(resp));
        }
        else {
          console.log(post);
          resp.consoleLog = post;
          console.log(req.method);
          resp.success = false;     
          res.end(JSON.stringify(resp));
        }
      }

    });
});

server.listen(8080);


// var io = require('socket.io').listen(server);
// //creating a new websocket to keep the content updated without any AJAX request
// io.sockets.on('connection', function(socket) {
//   var json = {
//     "answer": "a"
//   };
  
//   socket.volatile.emit('notification', json);
// });


