// script.js

    var textApp = angular.module('textApp', ['ngRoute']);

    textApp.config(function($routeProvider) {
        $routeProvider

        	.when('/', {
        		templateUrl : 'pages/postList.html',
                controller  : 'PostController'
        	})
        
            .when('/add', {
                templateUrl : 'pages/add.html',
                controller  : 'AddPost'
            })
            
            .when('/edit/:id', {
                templateUrl : 'pages/edit.html',
                controller  : 'EditPost'
            })
    });

    textApp.controller('mainController', function($scope) {
    });
    
    textApp.controller('PostController', function($scope, $http) {

    	var responsePromise = $http.get("rest/post/getAll");

        responsePromise.success(function(data, status, headers, config) {
            $scope.posts = data;
        });
        responsePromise.error(function(data, status, headers, config) {
            alert("AJAX failed!");
        });
        
        $scope.open = function(post) {
        	$location.path("#/edit/" + post.id);
        };
        
    });
    
    textApp.controller('AddPost', function($scope, $http, $location) {
    	$scope.addPost = function() {
    		
    		var json = {
    			title : $scope.title,
    			text : $scope.text
    		};
    		
    		var responsePromise = $http({
    		    method: 'POST',
    		    url: 'rest/post/addNewPost',
    		    data: json,
    		    headers: {'Content-Type': 'application/json'}
    		});
    		
    		responsePromise.success(function(data, status, headers, config) {
    			$location.path('/');
    		});
    		
    		responsePromise.error(function(data, status, headers, config) {
    			alert("AJAX failed!");
    		});
    		
    	};
    });
    
    textApp.controller('EditPost', function($scope, $http, $location, $routeParams) {

    	var responsePromise = $http.get("rest/post/" + $routeParams.id);

        responsePromise.success(function(data, status, headers, config) {
            $scope.post = data;
        });
        responsePromise.error(function(data, status, headers, config) {
            alert("AJAX failed!");
        });
        
        $scope.open = function(post) {
        	$location.path("#/edit/" + post.id);
        };    	
        
    	$scope.editPost = function() {
    		
    		var json = {
    			title : $scope.post.title,
    			text : $scope.post.text
    		};
    		
    		var responsePromise = $http({
    		    method: 'PUT',
    		    url: 'rest/post/' + $scope.post.id + '/update',
    		    data: json,
    		    headers: {'Content-Type': 'application/json'}
    		});
    		
    		responsePromise.success(function(data, status, headers, config) {
    			$location.path('/');
    		});
    		
    		responsePromise.error(function(data, status, headers, config) {
    			alert("AJAX failed!");
    		});
    		
    	};

    });    
