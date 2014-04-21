'use strict';

/* App Module */

var articulateApp = angular.module('articulateApp', [
  'ngRoute',
  'ngResource',
  'ngGrid',
  'ui.bootstrap',
    	
  'articulateAppControllers',  
  'articulateAppServices'
]);

articulateApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.      
      when('/home', {
          templateUrl: 'partials/home.html',
          controller: 'HomeCtrl'
      }).
      otherwise({
        redirectTo: '/home'
      });
  }]);