'use strict';

/* Controllers */

var articulateAppControllers = angular.module('articulateAppControllers', []);

articulateAppControllers.controller('HomeCtrl', ['$scope', '$modal', 'homeService', '$upload', '$timeout', '$location', 'localStorageService',
  function($scope, $modal, homeService, $upload, $timeout, $location, localStorageService) {
	$scope.message = '';
		
	if (localStorageService.get('user')) {		
		$scope.authenticated = true;
		$scope.userEmail = localStorageService.get('user');
	}
	
	// Code for Menu Component
	homeService.getCategories().then(function (categoryMenu) {		
		
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
	
	// Code for add categories modal dialog
	$scope.addCategories = function() {		
		var modalInstance = $modal.open({
	      templateUrl: 'partials/categories.html',
	      controller: function ($scope) {
	    	  homeService.getCategoriesByLevel(1).then(function (categoryMenu) {
	    		  $scope.category1Menu = categoryMenu;
	    	  });
	    	  
	    	  homeService.getCategoriesByLevel(2).then(function (categoryMenu) {
	    		  $scope.level2Menu = categoryMenu;
	    	  });
	    	  
	    	  $scope.getCategory2 = function(category1) {
	    		  $scope.category1 = category1;
	    		  $scope.category2Menu = _.filter($scope.level2Menu, function(category){ return category.parentName == category1.name; });
	    	  };
	    	  
	    	  $scope.setCategory2 = function(category2) {
	    		  $scope.category2 = category2;	    		  
	    	  };

	    	  $scope.addCategory = function () {
	    		  var name = $('#newCategory').val();
	    		  var level = 1;
	    		  var parentName = '';
	    		  if ($scope.category1) {
	    			  parentName = $scope.category1.name; 
	    			  level++;
	    		  }	  
	    		  if ($scope.category2) {
	    			  parentName = $scope.category2.name;
	    			  level++;
	    		  }	  
	    		  homeService.setCategory(name, parentName, level).then(function (category) {
	    			  modalInstance.dismiss('cancel');
	    			  $location.path('/');
	    		  });
	    	  };

	    	  $scope.cancel = function () {
	    	    modalInstance.dismiss('cancel');
	    	  }; 
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
	
	$scope.useFile = function (fileId) {	
		$scope.message = fileId;	
	};
	
	$scope.openFileUploadPopup = function () {
		var parentScope = $scope;
		var modalInstance = $modal.open({
			templateUrl: 'partials/fileUpload.html',
			controller: function ($scope) {
				$scope.favoriteMessage = $(".message-input").val();
				$("#favoriteMessageInput").val($scope.favoriteMessage);
				$scope.onFileSelect = function($files) {				    
				      var file = $files[0];
				      $scope.upload = $upload.upload({
				        url: '/uploadFile',
				        method: 'POST',
				        data : {
							message : $("#favoriteMessageInput").val()
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
    	$scope.userPassword = $('#userPassword').val();    	
    	homeService.getUser($scope.userEmail).then(function (response) {
    		if (response.message) {
    		var savedPassword  = CryptoJS.enc.Utf8.stringify(angular.fromJson(response.message));
	            if ($scope.userPassword && savedPassword == $scope.userPassword) {
	            	localStorageService.set('user', $scope.userEmail);	            	
	            	$scope.authenticated = true;
	            	return;
	            }
    		}
    		var parentScope = $scope;
    		var modalInstance = $modal.open({
    			templateUrl: 'partials/signup.html',
    			windowClass: 'signup-modal',
    			controller: function ($scope, signUpMessage) {
    				$scope.signUpMessage = signUpMessage;    				    				
    			    $scope.signUp = function () {
    			    	var userEmail = $('.signup-modal').find('#signUpEmail').val();
    			    	var userPassword1 = $('.signup-modal').find('#signUpPassword1').val();
    			    	var userPassword2 = $('.signup-modal').find('#signUpPassword2').val();
    			    	var result = parentScope.signUp(userEmail, userPassword1, userPassword2);
    			    	if (result != 'error') {
    			    		modalInstance.dismiss('cancel');
    			    	} else {
    			    		$scope.message = "Passwords do not match!";
    			    	}	
    		    	};
    		    	$scope.reset = function() {
    		    		modalInstance.dismiss('cancel');
    		    	};
    			},
    			resolve: {	    	  
    				signUpMessage: function () {
    		          return "Invalid Login!";
    		        }
    		    }
    		});            
    	});    	
    }
    
    $scope.signUp = function(userEmail, userPassword1, userPassword2) {
    	$scope.userEmail = $('#signUpEmail').val() || userEmail;
    	$scope.userPassword1 = $('#signUpPassword1').val() || userPassword1;
    	$scope.userPassword2 = $('#signUpPassword2').val() || userPassword2;    	
    	if ($scope.userPassword1 != $scope.userPassword2) {
    		$scope.signUpMessage = "Passwords do not match!"
    		return "error";	
    	}
    	var words = CryptoJS.enc.Utf8.parse($scope.userPassword1);
    	homeService.addUser($scope.userEmail, words).then(function (response) {
    		localStorageService.set('user', $scope.userEmail);    		
    		$scope.authenticated = true;
    	});   	
    }
    
    $scope.signOut = function() {
    	$scope.userEmail = '';
    	localStorageService.remove('user');    	
    	$scope.authenticated = false;
    }
    
    $scope.reset = function() {
    	$location.path('/');
    }
    
    $scope.ballfall = function(message) {		
		var modalInstance = $modal.open({
	      templateUrl: 'partials/ballfall.html',
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
	
	$scope.snake = function(message) {		
		var modalInstance = $modal.open({
	      templateUrl: 'partials/snake.html',
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
	

	$scope.video_abc = function(message) {		
		var modalInstance = $modal.open({
	      templateUrl: 'partials/video_abc.html',
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

	$scope.video_numbers = function(message) {		
		var modalInstance = $modal.open({
	      templateUrl: 'partials/video_numbers.html',
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
	
	
	$scope.video_shapes = function(message) {		
		var modalInstance = $modal.open({
	      templateUrl: 'partials/video_shapes.html',
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
}]);