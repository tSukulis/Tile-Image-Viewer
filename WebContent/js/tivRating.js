var TIVRatings = (function () { //TIVRatings.showRating();
    'use strict';
    var loadedRatings = [];
    var ratingId;
    var photoID;
    var userName;
    var userRateIndex;
   

    function innerSetRatings(ratings) {
      loadedRatings = ratings;
    }

    function innerGetRatings() {
      return loadedRatings;
    }

    function innerAddRating(rating) {
      loadedRatings.push(rating);
    }

    function innerRemoveRating() {
      if (typeof userRateIndex != undefined && userRateIndex != null && userRateIndex > -1 && userRateIndex < loadedRatings.length){
    	var rating = loadedRatings[userRateIndex];
    	loadedRatings.splice(userRateIndex, 1);
    	innerClearRatingsDOM('ratingSection');
    	innerShowRating();
    	removeRatePhoto(removeRatePhotoIdCallback,rating);
      }
    }

    function innerRemoveRatings() {
      loadedRatings = [];
    }

    function innerSetPhotoID(ID) {
      photoID = ID;
    }

    function innerGetPhotoID() {
      return photoID;
    }

    function innerSetUserName(username) {
      userName = username;
    }

    function innerGetUserName() {
      return userName;
    }

    function innerSetUserRateIndex(index) {
    	userRateIndex = index;
    }
    
    function innerGetUserRateIndex() {
    	return userRateIndex;
    }
    
    function innerShowRating() { //elem is the ratings
      console.log('Entered: innerShowRating');
      
      var ratingSection = document.getElementById('ratingSection');
      if(loggedUser != null){
    	  
	      var ratingRow = document.createElement('div');
	      ratingRow.setAttribute('class', 'row');
	      var ratingColumns = document.createElement('div');
	      ratingColumns.setAttribute('class', 'col-lg-8 col-md-8 col-sm-8');
	
	      var fieldset = document.createElement('fieldset');
	      fieldset.setAttribute('class', 'rating');
	      var yourRate = document.createElement('h4');
	      yourRate.setAttribute('id', 'YourRate');
	      yourRate.innerHTML = 'your rate  ';
	      fieldset.appendChild(yourRate);
	
	      for (var i = 5; i > 0; i--) {
	        /*=================input=================*/
	        var input = document.createElement('input');
	        input.setAttribute('type', 'radio');
	        input.setAttribute('id', 'star' + i);
	        input.setAttribute('name', 'rating');
	        input.setAttribute('value', i);
	        input.setAttribute('onclick', 'PostRating(star' + i + ');');
	        fieldset.appendChild(input);
	        
	        /*=================label=================*/
	        var label = document.createElement('label');
	        label.setAttribute('for', 'star' + i);
	        label.setAttribute('title', 'Rocks');
	        label.innerHTML = i + 'stars';
	        fieldset.appendChild(label);	        
	      }
	
	      ratingColumns.appendChild(fieldset);
	      ratingRow.appendChild(ratingColumns);
	      
	      /*=================remove Span Columns=================*/
	      var removeRatingColumns = document.createElement('div');
	      removeRatingColumns.setAttribute('class', 'col-lg-4 col-md-4 col-sm-4');
	      var removeRating = document.createElement('span');
	      removeRating.setAttribute('id', 'removeRate');
	      removeRating.setAttribute('class', 'glyphicon glyphicon-remove');
	      removeRating.setAttribute('onclick', 'TIVRatings.removeRating();');
	      removeRatingColumns.appendChild(removeRating);
	      ratingRow.appendChild(removeRatingColumns);
	      
	      ratingSection.appendChild(ratingRow);
      }
      
      var totalRatingsRow = document.createElement('div');
      totalRatingsRow.setAttribute('class', 'row');
      var totalRatingsColumns = document.createElement('div');
      totalRatingsColumns.setAttribute('class', 'col-lg-8 col-md-8 col-sm-8');
      var fullRatings = document.createElement('h5');
      fullRatings.setAttribute('id', 'fullRatings');
      fullRatings.setAttribute('onclick', 'TIVRatings.showRatings();');
      fullRatings.innerHTML = 'fullRatings';
      totalRatingsColumns.appendChild(fullRatings);
      totalRatingsRow.appendChild(totalRatingsColumns);
      ratingSection.appendChild(totalRatingsRow);
      
      

      /*=============set Checked Star==============*/
      for(var i = 0; i < loadedRatings.length; i++){
    	  if (loadedRatings[i].userName == loggedUser) {
    		  userRateIndex = i;
    		  var userRate = loadedRatings[i].rate; 
    	      if (typeof userRate != undefined && userRate != 0) {
    	    	  var checked = document.getElementById('star' + userRate);
    	    	  checked.setAttribute('checked', true);
    	    	  return;
    	      }
    	  }
      }
    }

    function innerShowRatings(elem) {//elem is the where you want to put the modal
      console.log('Entered: innerShowRatings');

      /*===========Modal Created==================*/
      var rateModal = document.createElement('div');
      rateModal.setAttribute('id', 'rateModal');
      rateModal.setAttribute('class', 'myRateModal');
      
      if (loadedRatings.length === 0) {
        var header = document.createElement('h2');
        header.innerHTML = 'NO Ratings ... . .';
        rateModal.appendChild(header);
      }
      /*==============First Row==================*/
      var spanCloseRow = document.createElement('div');
      spanCloseRow.setAttribute('class', 'row');
      var spanCloseColumns = document.createElement('div');
      spanCloseColumns.setAttribute('class', 'col-lg-1 col-md-1 col-sm-1 col-lg-offset-7 col-md-offset-7 col-sm-offset-7');
      var spanClose = document.createElement('span');
      spanClose.setAttribute('id', 'rateModalClose');
      spanClose.setAttribute('class', 'glyphicon glyphicon-remove');
      spanCloseColumns.appendChild(spanClose);
      spanCloseRow.appendChild(spanCloseColumns);
      rateModal.appendChild(spanCloseRow);

      for (var j = 0; j < loadedRatings.length; j++) {
        /*=============Second Row==================*/
        var ratingRow = document.createElement('div');
        ratingRow.setAttribute('class', 'row rateRowLeftMargin');
        var userNameRatingColumns = document.createElement('div');
        userNameRatingColumns.setAttribute('class', 'col-lg-1 col-md-1 col-sm-2 col-md-offset-4 col-lg-offset-4');
        var userName = document.createElement('h4');
        userName.setAttribute('class', 'userRate');
        userName.innerHTML = loadedRatings[j].userName + ':' ;
        userNameRatingColumns.appendChild(userName);
        ratingRow.appendChild(userNameRatingColumns);

        var RatingColumns = document.createElement('div');
        RatingColumns.setAttribute('class', 'col-lg-4 col-md-4 col-sm-6');
        for (var i = 0; i < loadedRatings[j].rate; i++){
          var star = document.createElement('span');
          star.setAttribute('class', 'glyphicon glyphicon-star ratedStar');
          star.setAttribute('aria-hidden', 'true');
          RatingColumns.appendChild(star);
        }
        for (var i = 0; i < 5 - loadedRatings[j].rate; i++){
          var star = document.createElement('span');
          star.setAttribute('class', 'glyphicon glyphicon-star nonRatedStar');
          star.setAttribute('aria-hidden', 'true');
          RatingColumns.appendChild(star);
        }
        ratingRow.appendChild(RatingColumns);
        rateModal.appendChild(ratingRow);

        /*===========timestamp Column==================*/
        var timeRow = document.createElement('div');
        timeRow.setAttribute('class', 'row rateRowLeftMargin timeStamprowOffset');
        var timeRowColumns = document.createElement('div');
        timeRowColumns.setAttribute('class', 'col-lg-4 col-md-4 col-sm-4 col-md-offset-4 col-lg-offset-4');
        var timestamp = document.createElement('p');
        timestamp.setAttribute('class', 'userRate');
        timestamp.innerHTML = loadedRatings[j].timestamp;
        timeRowColumns.appendChild(timestamp);
        timeRow.appendChild(timeRowColumns);
        rateModal.appendChild(timeRow);

        var rateHr = document.createElement('hr');
        rateHr.setAttribute('class', 'rateHr');
        rateModal.appendChild(rateHr);
      }

      document.getElementById('ratingsModalSection').appendChild(rateModal);

      // When the user clicks on <span> (x), close the modal
      document.getElementById('rateModalClose').onclick = function () {
          rateModal.style.display = 'none';
          innerClearRatingsDOM('ratingsModalSection');
        };

      // When the user doublelicks on modal (x), close the modal
      document.getElementById('rateModal').ondblclick = function () {
          rateModal.style.display = 'none';
          innerClearRatingsDOM('ratingsModalSection');
        };

      rateModal.style.display = 'block';

    }

    function innerClearRatingsDOM(elem) {
      var element = document.getElementById(elem);
      while (element.hasChildNodes())
       element.removeChild(element.lastChild);
    }

    function innerToString() {
      for (var i = 0; i < loadedRatings.length; i++) {
        console.log('=========================');
        console.log('ID: ' + loadedRatings[i].ID);
        console.log('rate: ' + loadedRatings[i].rate);
        console.log('userName: ' + loadedRatings[i].userName);
      }
    }

    return {
        setRatings:      innerSetRatings,
        getRatings:      innerGetRatings,
        addRating:       innerAddRating,
        removeRating:    innerRemoveRating,
        removeRatings:   innerRemoveRatings,
        setPhotoID:      innerSetPhotoID,
        getPhotoID:      innerGetPhotoID,
        setUserName:     innerSetUserName,
        getUserName:     innerGetUserName,
        setUserRateIndex:innerSetUserRateIndex,
        getUserRateIndex:innerGetUserRateIndex,
        showRating:      innerShowRating,
        showRatings:     innerShowRatings,
        clearRatingsDOM: innerClearRatingsDOM,
        toString:        innerToString,
      };
  })();


/*===manages array of all Ratings of loaded photos====*/
var ratingsByphotoID =  (function () {
  var IDratingsPair = {};

  function innerAddPair(key, value) {
    IDratingsPair[key] = value;
  }

  function innerGetPair(key) {
    return IDratingsPair[key];
  }
  
  function innerSetPair(key,value) {
	IDratingsPair[key] = value;
  }

  function innerClearIDratingsPair() {
    IDratingsPair = [];
  }

  function innertoString() {
    for (var key in IDratingsPair) {
      if (IDratingsPair.hasOwnProperty(key)) {
        console.log('~~~~~~~~~~~');
        console.log('photoID:' + key);
        for (var i = 0; i < IDratingsPair[key].length; i++) {
          console.log('    rating:' + IDratingsPair[key][i].rate);
          console.log('    timestamp: ' + IDratingsPair[key][i].timestamp);
          console.log('    userName: ' + IDratingsPair[key][i].userName);
          console.log('    ===========');
        }
      }
    }
  }

  return {
    addPair:            innerAddPair,
    getPair:            innerGetPair,
    setPair:            innerSetPair,
    clearIDratingsPair: innerClearIDratingsPair,
    toString:           innertoString,
  };
})();

function Rating(rate, userName, photoID) {
  this.rate      = rate;
  this.userName  = userName;
  this.photoID   = photoID,
  this.timestamp = new Date();
}


function PostRating(elem) {
	 for(var i = 0; i < TIVRatings.getRatings().length; i++){
		 if (TIVRatings.getRatings()[i].userName == loggedUser){
			 TIVRatings.setUserRateIndex(i);
			 TIVRatings.getRatings()[i].rate = elem.value;
			 TIVRatings.getRatings()[i].timestamp = new Date();
			 updateRatePhoto(updateRatePhotoIdCallback,TIVRatings.getRatings()[i]);
			 return;
		 }
	 }
	  var myRating = new Rating(elem.value, loggedUser, TIVRatings.getPhotoID());
	  TIVRatings.addRating(myRating);
	  TIVRatings.setUserRateIndex(TIVRatings.getRatings().length -1);
	  makeRating("makeRatingCallback",myRating);
	}

function showRatings() {
  document.getElementById('rateModal').style.display = 'block';
  document.getElementById('rateModal').onclick = function () {
      document.getElementById('rateModal').style.display = 'none';
    };
}
