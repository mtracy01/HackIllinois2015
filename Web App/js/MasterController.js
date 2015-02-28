var app = angular.module("Echo", []);

app.service('sharedVars', function () {
        var sessionName = 'Name';

        return {
            getProperty: function () {
                return sessionName;
            },
            setProperty: function(value) {
                sessionName = value;
            }
        };
    });

app.controller("LandingPageController", function($scope, sharedVars, $http) {
	$scope.startSession = function() {
		window.location = "dashboard.html";
	};
});

app.controller("SessionPageController", function($scope, sharedVars, $http) {

	$scope.questionVals = []; 
	$scope.ansoptionVals = [];
	$scope.qindex = 0;
	$scope.qdindex = 0;
	$scope.qToEdit = "";
	$scope.qToDisplay = "";


	$scope.addQuestion = function() {
		$scope.questionVals.push($scope.questionValue);
		$scope.ansoptionVals.push([]);
		console.log($scope.questionVals);
		console.log($scope.ansoptionVals);
		console.log($scope.qindex);
	}
	$scope.clearQuestions = function() {
		$scope.questionVals = [];
		$scope.ansoptionVals = [];
		console.log($scope.questionVals);
		console.log($scope.ansoptionVals);
		console.log($scope.qindex);
	}
	$scope.showOptions = function(q) {
		for (i = 0; i < $scope.questionVals.length; i++) {
			if ($scope.questionVals[i] == q) 
				$scope.qindex = i;
		}
		$scope.qToEdit = $scope.questionVals[$scope.qindex]; 
		console.log($scope.questionVals);
		console.log($scope.ansoptionVals);
		console.log($scope.qindex);
	}
	$scope.addOption = function() {
		if ($scope.questionVals.length > 0)
			$scope.ansoptionVals[$scope.qindex].push($scope.optionValue);
		console.log($scope.questionVals);
		console.log($scope.ansoptionVals);
		console.log($scope.qindex);
	}

	$scope.startDisplaySession = function() {
		$scope.qdindex = 0;
		$scope.qToDisplay = $scope.questionVals[$scope.qdindex];
	}

	$scope.incrementQ = function() {
		if ($scope.qdindex < $scope.questionVals.length - 1) 
			$scope.qdindex++;
		$scope.qToDisplay = $scope.questionVals[$scope.qdindex];
	}

	$scope.decrementQ = function() {
		if ($scope.qdindex > 0)
			$scope.qdindex--;
		$scope.qToDisplay = $scope.questionVals[$scope.qdindex];
	}
});