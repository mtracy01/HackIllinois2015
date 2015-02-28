var app = angular.module("Echo", []);

var sessionName = "";

app.controller("LandingPageController", function($scope, $http) {
	$scope.sessionName = "";


	$scope.startSession = function() {
		sessionName = scope.sessionName;
		window.location = "dashboard.html";
	};
});

app.controller("SessionPageController", function($scope, $http) {
	
});