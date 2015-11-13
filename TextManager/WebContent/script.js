// script.js

    var textApp = angular.module('textApp', ['ngRoute']);

    textApp.config(function($routeProvider) {
        $routeProvider

            // route for the home page
            .when('/add', {
                templateUrl : 'pages/add.html',
                controller  : 'AddPost'
            })
    });

    textApp.controller('mainController', function($scope) {
    });
    
    textApp.controller('PostController', function($scope, $http, $location) {
        $scope.title = 'Contact us! JK. This is just a demo.';

        var responsePromise = $http.get("rest/post/getAll");

        responsePromise.success(function(data, status, headers, config) {
            $scope.posts = data;
            console.log($scope.posts);
        });
        responsePromise.error(function(data, status, headers, config) {
            alert("AJAX failed!");
        });
        
        console.log("posts", $scope.posts);
        
        $scope.go = function ( path ) {
        	  $location.path( path );
        	};
                
    });
