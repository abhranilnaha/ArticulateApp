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
            	url: '/getCategories'            	 
            },
            getCategoriesByLevel: { 
            	method: 'GET',
            	isArray: true,
            	url: '/getCategories?level=:level'            	 
            },
            setCategory: {
          	  url: '/setCategories', 
          	  method: "POST", 
          	  isArray: false
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
           },
           makeSentence: {
               method: 'GET',                
               url: '/makeSentence?noun=:noun&verb=:verb&object=:object&extra=:extra&tense=:tense&negation=:negation&question=:question', 
           	   isArray: false
          },
          addUser: {
        	  url: '/authenticateUser', 
        	  method: "POST", 
        	  isArray: false
          },
          getUser: {
        	  url: '/authenticateUser?userEmail=:userEmail', 
        	  method: "GET", 
        	  isArray: false
          }
        }
    );
    
    this.getCategories = function() {
        return resource.getCategories().$promise.then(function(result){
            return result;
        });
    };
    
    this.getCategoriesByLevel = function(level) {
        return resource.getCategories({level:level}).$promise.then(function(result){
            return result;
        });
    };
    
    this.setCategory = function(name, parentName, level) {
    	var category = {name: name, parentName: parentName, level: level, icon: '', link: ''};
        return resource.setCategory(category).$promise.then(function(result) {
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
    
    this.makeSentence = function(noun, verb, object, extra, tense, negation, question) {
        return resource.makeSentence({noun: noun, verb: verb, object: object, extra: extra, tense :tense, negation: negation, question: question}).$promise.then(function(result){
            return result;
        });
    };
    
    this.addUser = function(userEmail, userPassword) {
    	var user = {userEmail: userEmail, userPassword: userPassword};
        return resource.addUser(user).$promise.then(function(result) {
            return result;
        });
    };
    
    this.getUser = function(userEmail) {    	
        return resource.getUser({userEmail: userEmail}).$promise.then(function(result) {
            return result;
        });
    };
	    
}]);
   