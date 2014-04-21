'use strict';

/* Services */

var articulateAppServices = angular.module('articulateAppServices', []);

articulateAppServices.service('categoryService', ['$resource', function($resource) {
    var resource = $resource('mocks/categories.json',
        {},
        {
            getCategories: { method: 'GET', isArray: true }
        }
    );
    
    this.getCategories = function() {
        return resource.getCategories().$promise.then(function(result){
            return result;
        });
    };
	    
}]);
   