'use strict';

/* Controllers */

var articulateAppControllers = angular.module('articulateAppControllers', []);

articulateAppControllers.controller('HomeCtrl', ['$scope', '$modal', 'homeService', '$upload', '$timeout', '$location', '$cacheFactory',
  function($scope, $modal, homeService, $upload, $timeout, $location, $cacheFactory) {
	$scope.message = '';
	
	// Code for Menu Component
	homeService.getCategories().then(function (categoryMenu) {
		
		$scope.cache = $cacheFactory('appCache');
		
		$scope.documents= categoryMenu[0].items;
			
		$scope.Images = [];
		$scope.getCategorybyParent = function(item) {		    
			$scope.message += ' ' +item.name;
			$scope.Images += item;
		    $scope.documents = item.items[0].items;		    
		};
	  	
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
	
	// Code for text to speech
	$scope.speakInput = function(message) {
		speak(message);
	}
	
	// Code for modal dialog
	$scope.items = ['item1', 'item2', 'item3'];	
	
	$scope.addCategories = function(message) {		
		var modalInstance = $modal.open({
	      templateUrl: 'partials/categories.html',
	      controller: function ($scope, items) {
	    	  $scope.items = items;
	    	  $scope.selected = {
	    	    item: $scope.items[0]
	    	  };

	    	  $scope.ok = function () {
	    	    modalInstance.close($scope.selected.item);
	    	  };

	    	  $scope.cancel = function () {
	    	    modalInstance.dismiss('cancel');
	    	  }; 
	      },
	      resolve: {	    	  
	        items: function () {
	          return $scope.items;
	        }
	      }
	    });

	    modalInstance.result.then(function (selectedItem) {
	      $scope.selected = selectedItem;
	    }, function () {
	      console.log('Modal dismissed at: ' + new Date());
	    });
	};
	
	// Code for file upload
	homeService.getFiles().then(function (files) {
		$scope.files = files;		
	});
	
	$scope.deleteFile = function (fileId) {		
		homeService.deleteFile(fileId).then(function (result) {
			var file = _.filter($scope.files, function(file){ return file.fileId == result.message; });
			var index = $scope.files.indexOf(file[0]);
			$scope.files.splice(index, 1);			
		});
	};
	
	$scope.downloadFile = function (fileId) {	
		// TO DO	
	};
	
	$scope.openFileUploadPopup = function () {
		var parentScope = $scope;
		var modalInstance = $modal.open({
			templateUrl: 'partials/fileUpload.html',
			controller: function ($scope) {
				$scope.onFileSelect = function($files) {				    
				      var file = $files[0];
				      $scope.upload = $upload.upload({
				        url: '/uploadFile',
				        method: 'POST',
				        data : {
							message : $(".message-input").val()
						},
				        file: file, 
				      }).then(function(response) {
				    	  $scope.result = response.data;
				    	  homeService.getFiles().then(function (files) {				    		  
				    		  $timeout(function(){
				    			  parentScope.files = files;
				    			  parentScope.$apply();
				    	      }, 250);			    		  
				    	  });
				      });				      
			    };
			    $scope.ok = function () {
			    	modalInstance.dismiss('cancel');
		    	}; 
			}
		});
    };
	
	// Code for sentence maker
	$scope.makeSentence = function () {
		$scope.noun = 'mary';
		$scope.verb = 'want';
		$scope.object = 'tea';
		var parentScope = $scope;
		var modalInstance = $modal.open({
			templateUrl: 'partials/sentenceMaker.html',
			controller: function ($scope, noun, verb, object) {
				$scope.noun = noun;
				$scope.verb = verb;
				$scope.object = object;
				$scope.tense = 'present';
				$scope.negation = 'no';
				$scope.question = 'no';
				$scope.makeSentence = function() {
					var noun = $('#val1').text();
					var verb = $('#val2').text();
					var object = $('#val3').text();
					var extra = $('#val4').text();
					var tense = $('#val5').text();
					var negation = $('#val6').text();
					var question = $('#val7').text();
					homeService.makeSentence(noun, verb, object, extra, tense, negation, question).then(function (result) {
						$scope.sentenceOutput = result.message;
					});
			    };
			    $scope.useSentence = function () {
			    	parentScope.message = $scope.sentenceOutput;
			    	modalInstance.dismiss('cancel');
		    	}; 
			},
			resolve: {	    	  
				noun: function () {
		          return parentScope.noun;
		        },
		        verb: function () {
		          return parentScope.verb;
		        },
		        object: function () {
		          return parentScope.object;
		        }
		    }
		});
    };
    
    $scope.signIn = function() {
    	$scope.userEmail = $('#userEmail').val();
    	$scope.cache.put('userName', userEmail);
    	$scope.authenticated = true;
    }
    
    $scope.signUp = function() {
    	$scope.userEmail = $('#signUpEmail').val();
    	$scope.cache.put('userName', userEmail);
    	$scope.authenticated = true;
    }
    
    $scope.signOut = function() {
    	$scope.userEmail = '';
    	$scope.cache.remove('userName');
    	$scope.authenticated = false;
    }
    
    $scope.reset = function() {
    	$location.path('/');
    }    
}]);