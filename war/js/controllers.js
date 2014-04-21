'use strict';

/* Controllers */

var articulateAppControllers = angular.module('articulateAppControllers', []);

articulateAppControllers.controller('HomeCtrl', ['$scope', 'categoryService',
  function($scope, categoryService) {
	$scope.message = '';
	
	// Code for Menu Component
	categoryService.getCategories().then(function (categoryMenu) {
	  	
	  	$('#menu').multilevelpushmenu({
	        menu: categoryMenu,
	        menuID: 'multilevelpushmenu', 
	        mode: 'cover',
	        containersToPush: [$( '#pushobj' )],
	  		backItemIcon: 'fa fa-angle-left',
	  		groupIcon: 'fa fa-angle-right',
	  		wrapperClass: 'multilevelpushmenu_wrapper',	  		
	        onGroupItemClick: function() {
	            var event = arguments[0],
	                $menuLevelHolder = arguments[1],
	                $item = arguments[2],
	                options = arguments[3],
	                title = $menuLevelHolder.find( 'h2:first' ).text(),
	                itemName = $item.find( 'a:first' ).text();

	            $scope.message = !$scope.message ? itemName : $scope.message + ' ' + itemName;
	            $scope.$apply();
	        },
	        onItemClick: function() {
	            var event = arguments[0],
	                $menuLevelHolder = arguments[1],
	                $item = arguments[2],
	                options = arguments[3],
	                title = $menuLevelHolder.find( 'h2:first' ).text(),
	                itemName = $item.find( 'a:first' ).text();

	            $scope.message = !$scope.message ? itemName : $scope.message + ' ' + itemName; 
	            $scope.$apply();
	        }    
        });
	  	
    });
	
	// Code for Carousal Component
	$scope.myInterval = -1;
	var slides = $scope.slides = [];
	$scope.addSlide = function(i) {		
	    slides.push({	      
	      image1: 'img/phones/t-mobile-mytouch-4g.' + i + '.jpg',
	      image2: 'img/phones/dell-venue.' + i + '.jpg',
	      image3: 'img/phones/samsung-galaxy-tab.' + i + '.jpg',
	      text: 'Phone ' + (i + 1),
	      id: i + 1
	    });
	};
	for (var i=0; i<6; i++) {
		$scope.addSlide(i);
	}
	
	
	$scope.speakInput = function(message) {
		speak(message);
	}
	
	// Code for Grid Component
	$scope.myData = [{id: 1, name: "Abhranil Naha", gender: 'Male'},
                     {id: 2, name: "Varun Dixit", gender: 'Male'},
                     {id: 3, name: "Harini Aswin", gender: 'Female'},
                     {id: 4, name: "Asif Nadaf", gender: 'Male'},
                     {id: 5, name: "Priya Rajesh", gender: 'Female'}];
    $scope.gridOptions = { 
    	data: 'myData',
    	columnDefs: [
    	             {field:'id', displayName:'ID'},
    	             {field:'name', displayName:'Name'}, 
    	             {field:'gender', displayName:'Gender'}
    	            ]
    };
}]);