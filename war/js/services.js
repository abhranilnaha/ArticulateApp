'use strict';

/* Services */

var articulateAppServices = angular.module('articulateAppServices', []);

articulateAppServices.service('homeService', ['$resource', function($resource,$http) {
    var resource = $resource('./mocks/categories.json',
        {},
        {
            getCategories: { 
            	method: 'GET',
            	isArray: true,
            	//url: '/getCategories',
            	 
            },
            getFiles: { 
            	method: 'GET', 
            	url: '/listFile', 
            	isArray: true 
            },
            deleteFile: {
                method: 'GET',                
                url: '/deleteFile?action=delete&id=:id', 
            	isArray: false
           }
        }
    );
    
    this.getCategories = function() {
        return resource.getCategories().$promise.then(function(result){
            return result;
        });
    };
    
    this.getFiles = function() {
        return resource.getFiles().$promise.then(function(result){
            return result;
        });
    };
    
    this.deleteFile = function(fileId) {
        return resource.deleteFile({id: fileId}).$promise.then(function(result){
            return result;
        });
    };
	    
}]);
   