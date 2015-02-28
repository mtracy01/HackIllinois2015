var http = require('http');
var url = require('url');

var qs = require('querystring');
var request = require('request');

var server = http.createServer( function(req, res) {

    
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
            
      console.log(post);
      resp.consoleLog = post;
      console.log(req.method);
      resp.success = true;  
      resp.answer = "a";   
      res.end(JSON.stringify(resp));
    });
});

server.listen(8080);


var io = require('socket.io').listen(server);
//creating a new websocket to keep the content updated without any AJAX request
io.sockets.on('connection', function(socket) {
  var json = {
    "answer": "a"
  };
  
  socket.volatile.emit('notification', json);
});


