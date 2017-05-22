/*=======================================
     submitForm Fields
  =======================================*/
var user = [{
    userName: document.getElementById("userName").value,
    email: document.getElementById("email").value,
    password: document.getElementById("password").value,
    confirmPass: document.getElementById("confirmPass").value,
    firstName: document.getElementById("firstName").value,
    lastName: document.getElementById("lastName").value,
    birthDate: document.getElementById("birthDate").value,
    gender: $('input[type=radio][name=gender]:checked').attr('id'),
    country: document.getElementById("country").value,
    city: document.getElementById("city").value,
    info: document.getElementById("info").value

}];

var loggedUser = null;
var collTemplate;
var idsArray = []; 
var visitFlag = false;


/*=======================================
    submitForm
=======================================*/
function registerReq() {
    "use strict";
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "RegisterServlet");
    
    xhr.onload = function () {
        if(xhr.readyState == 4 && xhr.status === 200) {
        	if(xhr.responseText.includes("fail"))
            {
        		document.getElementById("regFail").innerHTML= xhr.responseText;
        		document.getElementById("myModal").setAttribute("style", "display: block; width: auto;");
                document.getElementById("regFail").focus();
            } else {
            	document.body.innerHTML = xhr.responseText;
            	window.scrollTo(0,0);
                
            }

        } else if(xhr.status != 200) {
            alert("Request failed. Returned status of" + xhr.status);

        }
    };

    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send("userName=" + document.getElementById("userName").value + "&email=" + document.getElementById("email").value + "&password=" + document.getElementById("password").value +
        "&confirmValidation=" + document.getElementById("confirmPass").value + "&firstName=" + document.getElementById("firstName").value +
        "&lastName=" + document.getElementById("lastName").value + "&birthDate=" + document.getElementById("birthDate").value +
        "&gender=" + $('input[type=radio][name=gender]:checked').attr('id') + "&country=" + document.getElementById("country").value +
        "&city=" + document.getElementById("city").value + "&info=" + document.getElementById("info").value);
}


/*=======================================
     request to Validate login
=======================================*/
function ValidateLogin(ValidateCallback) {
    "use strict";
    
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "ValidateServlet",false);
    xhr.onload = function () {
        if(xhr.readyState == 4 && xhr.status === 200) {
        	ValidateCallback(xhr);
        	
        } else if(xhr.status != 200) {
            alert("Request failed. Returned status of" + xhr.status);      
        }
    };
    loggedUser = document.getElementById("loginName").value;
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send("loginName=" + document.getElementById("loginName").value + "&loginPsw=" + document.getElementById("loginPsw").value);
}

function ValidateCallback(xhr){
	"use strict";
	if(xhr.responseText.includes("logresp")){
		document.getElementById("id01").setAttribute("style", "display: block; width: auto;");
		document.getElementById("logmsg").innerHTML= xhr.responseText;
        document.getElementById("myModal").setAttribute("style", "display: block; width: auto;");
        document.getElementById("logmsg").focus();
    }
	else{
		visitFlag = false;
		collTemplate = xhr.responseText;
		document.body.innerHTML = xhr.responseText;
    	window.scrollTo(0,0);
    	GetImageCollection(GetImageCollectionCallback,loggedUser);
	}
}

/*=======================================
    request to Show Profile
=======================================*/
function ShowProfile(ShowProfileCallback) {
    "use strict";
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "ProfileServlet");
    xhr.onload = function () {
        if(xhr.readyState == 4 && xhr.status === 200) {
        	ShowProfileCallback(xhr);
        } else if(xhr.status != 200) {
            alert("Request failed. Returned status of" + xhr.status);      
        }
    };
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send();
}

function ShowProfileCallback(xhr){
	document.body.innerHTML = xhr.responseText;
	window.scrollTo(0,0);
}


/*=======================================
  request to Delete User
=======================================*/
function DeleteUser(DeleteUserCallback) {
	"use strict";
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "DeleteUser");
	xhr.onload = function () {
	    if(xhr.readyState == 4 && xhr.status === 200) {
	    	DeleteUserCallback(xhr);
	    } else if(xhr.status != 200) {
	        alert("Request failed. Returned status of" + xhr.status);      
	    }
	};
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send();
}

function DeleteUserCallback(xhr){
	document.body.innerHTML = xhr.responseText;
window.scrollTo(0,0);
}

/*=======================================
  request to "Edit Profile
=======================================*/
function EditProfileReq(callback) {
	"use strict";
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "EditProfileServlet");
	xhr.onload = function () {
	    if(xhr.readyState == 4 && xhr.status === 200) {
	    	callback(xhr);
	    } else if(xhr.status != 200) {
	        alert("Request failed. Returned status of" + xhr.status);
	    }
	};
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(null);
}

function callback(xhr) {
//	document.getElementById("login").innerHTML = "¯\_(ツ)_/¯";
	document.body.innerHTML = xhr.responseText;
	window.scrollTo(0,0);
}


/*=======================================
  request to upDate info
=======================================*/
function upDateReq(upDateCallback) {
	"use strict";
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "UpdateDataServlet");
	xhr.onload = function () {
		if(xhr.readyState == 4 && xhr.status === 200) {
			upDateCallback(xhr);
	    } else if(xhr.status != 200) {
	        alert("Request failed. Returned status of" + xhr.status);
	    }
	};
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send("userName=" + document.getElementById("userName").value + "&email=" + document.getElementById("email").value + "&password=" + document.getElementById("password").value +
        "&firstName=" + document.getElementById("firstName").value +
        "&lastName=" + document.getElementById("lastName").value + "&birthDate=" + document.getElementById("birthDate").value +
        "&gender=" + $('input[type=radio][name=gender]:checked').attr('id') + "&country=" + document.getElementById("country").value +
        "&city=" + document.getElementById("city").value + "&info=" + document.getElementById("info").value);
}

function upDatecallback(xhr) {
	if(xhr.responseText.includes("logresp")){
		document.getElementById("login").innerHTML= xhr.responseText;
        window.scrollTo(0,0);
    }
	else{
		document.body.innerHTML = xhr.responseText;
    	window.scrollTo(0,0);
	}
}


/*=======================================
	 show registeredMembers
=======================================*/
function showMembersReq(memberCallback) {
	"use strict";
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "ShowMembersServlet");
	xhr.onload = function () {
		if(xhr.readyState == 4 && xhr.status === 200) {
			memberCallback(xhr);
	    } else if(xhr.status != 200) {
	        alert("Request failed. Returned status of" + xhr.status);
	    }
		
	};
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send();

}

function memberCallback(xhr){
	document.body.innerHTML = xhr.responseText;
	window.scrollTo(0,0);
}

/*=======================================
    request to logOut
=======================================*/
function LogOutReq(LogOutCallback) {
	"use strict";
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "LogoutServlet");
	xhr.onload = function () {
	    if(xhr.readyState == 4 && xhr.status === 200) {
	    	LogOutCallback(xhr);
	    } else if(xhr.status != 200) {
	        alert("Request failed. Returned status of" + xhr.status);
	    }
	};
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send();
}

function LogOutCallback(xhr){
	loggedUser = null;
	document.body.innerHTML = xhr.responseText;
	window.scrollTo(0,0);
	window.location = "tiv.html";
}

/*=======================================
   request to show collection
=======================================*/
function collectionReq(callback,booleanPhotosNum) {
	"use strict";
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "CollectionServlet");
	xhr.onload = function () {
	    if(xhr.readyState == 4 && xhr.status === 200) {
	    	callback(xhr,booleanPhotosNum);
	    } else if(xhr.status != 200) {
	        alert("Request failed. Returned status of" + xhr.status);
	    }
	};
	visitFlag = false;
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send();
}

function collectionCallback(xhr,booleanPhotosNum){
	collTemplate = xhr.responseText;
	document.body.innerHTML = xhr.responseText;
	window.scrollTo(0,0);
	GetImageCollection(GetImageCollectionCallback,loggedUser);
}


/*=======================================
    request to GetImageCOllection
=======================================*/
function GetImageCollection(callback,userName) {
	"use strict";
	var data = new FormData();
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "GetImageCollection",false);
	xhr.onload = function () {
	    if(xhr.readyState == 4 && xhr.status === 200) {
	    	TIV2575.clearLoadImages();
	    	TIVComment.removeComments();	    	
	    	commentsByphotoID.clearIDcommentsPair();
	    	callback(xhr);
	    } else if(xhr.status != 200) {
	        alert("Request failed. Returned status of" + xhr.status);
	    }
	};
	data.append('userName', userName);
	data.append('number', 10);
	xhr.send(data);
}
function GetImageCollectionCallback(xhr){
	idsArray = JSON.parse(xhr.responseText);
	/* call GetImageBlob for every id */
	for (var i = 0; i < idsArray.length; i++) {
		GetImageBlob(GetImageBlobCallback,idsArray[i],i);   
    }
	/* set 5 seconds delay before show images so to be loaded from DB */
	setTimeout(function() { TIV2575.showLoadImages('container-fluid'); }, 2000);
	
	/* get comments for every photo */
	for (var i = 0; i < idsArray.length; i++) {
		GetCommentsByPhotoId(GetCommentsByPhotoIdCallback,idsArray[i]);
	}
	
	/* get metadata for every photo */
	for (var i = 0; i < idsArray.length; i++) {	
		GetImageMetaData(GetImageMetaDataCallback,idsArray[i]);
	}
	
	/* get ratings for every photo */
	for (var i = 0; i < idsArray.length; i++) {
		GetRatingsByPhotoId(GetRatingsByPhotoIdCallback,idsArray[i]);
	}
	
}

/*=======================================
   request to GetImageCOllectionByNumber
=======================================*/
function GetImageCollectionByNumber(callback) {
	"use strict";
	var data = new FormData();
	var xhr = new XMLHttpRequest();
	var PhotosNum = null;
	PhotosNum = document.getElementById("PhotosNum").value;
	xhr.open("POST", "GetImageCollection",false);
	xhr.onload = function () {
	    if(xhr.readyState == 4 && xhr.status === 200) {
	    	document.body.innerHTML = collTemplate;
	    	/* clear arrays so not to duplicate with data to browser cashe */ 
	    	TIV2575.clearLoadImages();
	    	TIVComment.removeComments();	    	
	    	commentsByphotoID.clearIDcommentsPair();
	    	callback(xhr);
	    } else if(xhr.status != 200) {
	        alert("Request failed. Returned status of" + xhr.status);
	    }
	};
	data.append('userName', loggedUser);
	data.append('number', PhotosNum);
	xhr.send(data);
}
function GetImageCollectionByNumberCallback(xhr){
	idsArray = JSON.parse(xhr.responseText);
	visitFlag = false;
	/* call GetImageBlob for every id */
	for (var i = 0; i < idsArray.length; i++) {
		GetImageBlob(GetImageBlobCallback,idsArray[i],i);   
	}
	
	/* set 5 seconds delay before show images so to be loaded from DB */
	setTimeout(function() { TIV2575.showLoadImages('container-fluid'); }, 2500);
	
	/* get comments for every photo */
	for (var i = 0; i < idsArray.length; i++) {
		GetCommentsByPhotoId(GetCommentsByPhotoIdCallback,idsArray[i]);
	}
	
	/* get metadata for every photo */
	for (var i = 0; i < idsArray.length; i++) {
		GetImageMetaData(GetImageMetaDataCallback,idsArray[i]);
	}
	
	/* get ratings for every photo */
	for (var i = 0; i < idsArray.length; i++) {
		GetRatingsByPhotoId(GetCommentsByPhotoIdCallback,idsArray[i]);
	}
}


/*=======================================
    request to UploadImage
=======================================*/
function UploadImage() {
	"use strict";
	var contentType;
	var formData = new FormData();
	var reader = new FileReader();
	var files = document.getElementById("fileButton").files;
	if(files[0] !== null){
		/*check if file is an image though using the file.name */
	    if (!files[0].name.match(/\.(jpg|jpeg|png|gif)$/)) {
	        alert(files[0].name + ": not an image");  
	    }
	    /*check the typo of image using the file.name */
		if(files[0].name.match(/.jpg*/)){
			contentType = "image/jpg";
		}
		if(files[0].name.match(/.jpeg*/)){
			contentType = "image/jpeg";
		}
		if(files[0].name.match(/.png*/)){
			contentType = "image/png";
		}
		if(files[0].name.match(/.gif*/)){
			contentType = "image/gif";
		}
		
		formData.append('contentType', contentType);
		formData.append('userName', loggedUser);
		
		reader.onload = function() {
		   var xhr = new XMLHttpRequest();
		   formData.append('photo', files[0]);
		   xhr.open('POST', 'UploadImage',false);
		   xhr.onload = function () {
			    if(xhr.readyState == 4 && xhr.status === 200) {
			    	document.body.innerHTML = xhr.responseText;
			    	window.scrollTo(0,0);
			    	GetImageCollection(GetImageCollectionCallback,loggedUser);
			    } else if(xhr.status != 200) {
			        alert("Request failed. Returned status of" + xhr.status);
			    }
			};
			
		   xhr.send(formData);
		}();
		reader.readAsDataURL(files[0]);
	}
	else{
		document.body.innerHTML = collTemplate;
	}
}


/*=======================================
   request to DeletePhoto
=======================================*/
function DeletePhoto(id,callback) {
	"use strict";
	var data = new FormData();
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "DeletePhoto");
	xhr.onload = function () {
	    if(xhr.readyState == 4 && xhr.status === 200) {
	    	callback(xhr);
	    } else if(xhr.status != 200) {
	        alert("Request failed. Returned status of" + xhr.status);
	    }
	};
	data.append('id', id);
    xhr.send(data);
}
function DeletePhotoCallback(xhr){
	document.body.innerHTML = collTemplate;
	GetImageCollection(GetImageCollectionCallback,loggedUser);
	
}

/*=======================================
    request to GetImageBlob
=======================================*/
function GetImageBlob(callback,id,i) {
	"use strict";
	var data = new FormData();
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "GetImage");
	xhr.responseType = "blob";
	xhr.onload = function (oEvent) {
	    if(xhr.readyState == 4 && xhr.status === 200) {
	    	callback(xhr,id,i);
	    	
	    } else if(xhr.status != 200) {
	        alert("Request failed. Returned status of" + xhr.status);
	    }
	};
	data.append('id', id);
	data.append('metaData', false);
	xhr.send(data);
}
function GetImageBlobCallback(xhr,id,i){
	var blob = xhr.response;
	/*set date and name to blob to create a file */
	blob.lastModifiedDate = new Date();
	blob.name = "image"+i;
	blob.id = id;
	/* push blob to TIV2575.loadedImagesArray */
	TIV2575.addImage(blob);
}

/*=======================================
   request to GetImageMetaData
=======================================*/
function GetImageMetaData(callback,photoID) {
	"use strict";
	var data = new FormData();
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "GetImage");
	xhr.onload = function () {
	    if(xhr.readyState == 4 && xhr.status === 200) {
	    	callback(xhr,photoID);
	    } else if(xhr.status != 200) {
	        alert("Request failed. Returned status of" + xhr.status);
	    }
	};
	data.append('id', photoID);
	data.append('metaData', true);
	xhr.send(data);
}
function GetImageMetaDataCallback(xhr,photoID){
	var metadata = JSON.parse(xhr.responseText);
	metaDataByphotoID.addMetaData(photoID,metadata);
}


/*=======================================
  request to RatePhoto
=======================================*/
function makeRating(callback,myRating) {
	
	"use strict";
	var data = new FormData();
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "RatingServlet");
	xhr.onload = function () {
	    if(xhr.readyState == 4 && xhr.status === 200) {
	    	var ratings = JSON.parse(xhr.responseText);
	    	ratingsByphotoID.setPair(myRating.photoID,ratings);
	    	TIVRatings.setRatings(ratings);
	    	//callback(xhr, myRating.photoID);
	    } else if(xhr.status != 200) {
	        alert("Request failed. Returned status of" + xhr.status);
	    }
	};
	
	data.append('userName', myRating.userName);
	data.append('photoID', myRating.photoID);
	data.append('rate', myRating.rate);
	data.append('timestamp', myRating.timestamp);
	xhr.send(data);
}

/*=======================================
	request to updateRatePhoto
=======================================*/
function updateRatePhoto(callback,myRating) {
	'use strict';
	var data = new FormData();
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'RatingUpdate');
	xhr.onload = function () {
	    if(xhr.readyState == 4 && xhr.status === 200) {
	    	callback(xhr,myRating.photoID);
	    } else if(xhr.status != 200) {
	        alert('Request failed. Returned status of' + xhr.status);
	    }
	};
	data.append('userName', myRating.userName);
	data.append('photoID', myRating.photoID);
	data.append('rate', myRating.rate);
	data.append('ID', myRating.ID);
	data.append('timestamp', myRating.timestamp);
	xhr.send(data);
}
function updateRatePhotoIdCallback(xhr,photoID){
	var ratings = JSON.parse(xhr.responseText);
	console.log(ratings);
	ratingsByphotoID.setPair(photoID,ratings);
}

/*=======================================
   request to removeRatePhoto
=======================================*/
function removeRatePhoto(callback,myRating) {
	'use strict';
	var data = new FormData();
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'RemoveRating');
	xhr.onload = function () {
	    if(xhr.readyState == 4 && xhr.status === 200) {
	    	callback(xhr,myRating.photoID);
	    } else if(xhr.status != 200) {
	        alert('Request failed. Returned status of' + xhr.status);
	    }
	};
	data.append('userName', myRating.userName);
	data.append('photoID', myRating.photoID);
	data.append('rate', myRating.rate);
	data.append('ID', myRating.ID);
	data.append('timestamp', myRating.timestamp);
	xhr.send(data);
}
function removeRatePhotoIdCallback(xhr,photoID){
	var ratings = JSON.parse(xhr.responseText);
	console.log(ratings);
	ratingsByphotoID.setPair(photoID,ratings);
	TIVRatings.setRatings(ratings);
}


/*=======================================
   request to makeComment
=======================================*/
function makeComment(callback,myComment) { 
	"use strict";
	var data = new FormData();
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "MakeComment");
	xhr.onload = function () {
	    if(xhr.readyState == 4 && xhr.status === 200) {
	    	var comments = JSON.parse(xhr.responseText);
	    	console.log(comments);
	    	commentsByphotoID.addPair(myComment.photoID,comments);
	    	TIVComment.setComments(comments);
	    	TIVComment.showComments('commentsSection');
	    } else if(xhr.status != 200) {
	        alert("Request failed. Returned status of" + xhr.status);
	    }
	};
	data.append('userName', myComment.userName);
	data.append('photoID', myComment.photoID);
	data.append('comment', myComment.comment);
	data.append('timestamp', myComment.timestamp);
	xhr.send(data);
}

/*=======================================
   request to DeleteCommentByID
=======================================*/
function DeleteCommentByID(callback,myComment) {
	'use strict';
	var data = new FormData();
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'DeleteComment');
	xhr.onload = function () {
	    if(xhr.readyState == 4 && xhr.status === 200) {
	    	callback(xhr,myComment);
	    } else if(xhr.status != 200) {
	        alert('Request failed. Returned status of' + xhr.status);
	    }
	};
	data.append('userName', myComment.userName);
	data.append('photoID', myComment.photoID);
	data.append('comment', myComment.comment);
	data.append('ID', myComment.ID);
	data.append('timestamp', myComment.timestamp);
	xhr.send(data);
}
function DeleteCommentByIDCallback(xhr,myComment){
	var comments = JSON.parse(xhr.responseText);
	console.log(comments);
	commentsByphotoID.addPair(myComment.photoID,comments);
	TIVComment.setComments(comments);
    TIVComment.showComments('commentsSection');
}

/*=======================================
   request to GetCommentsByPhotoId
=======================================*/
function GetCommentsByPhotoId(callback,photoID) {
	'use strict';
	var data = new FormData();
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'GetComments');
	xhr.onload = function () {
	    if(xhr.readyState == 4 && xhr.status === 200) {
	    	callback(xhr,photoID);
	    } else if(xhr.status != 200) {
	        alert('Request failed. Returned status of' + xhr.status);
	    }
	};
	data.append('photoID', photoID);
	xhr.send(data);
}
function GetCommentsByPhotoIdCallback(xhr,photoID){
	var comments = JSON.parse(xhr.responseText);
	console.log(typeof comments);
	commentsByphotoID.addPair(photoID,comments);
}


/*=======================================
request to GetRatingsByPhotoId
=======================================*/
function GetRatingsByPhotoId(callback,photoID) { 
	'use strict';
	var data = new FormData();
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'GetRatings');
	xhr.onload = function () {
	    if(xhr.readyState == 4 && xhr.status === 200) {
	    	callback(xhr,photoID);
	    } else if(xhr.status != 200) {
	        alert('Request failed. Returned status of' + xhr.status);
	    }
	};
	data.append('photoID', photoID);
	xhr.send(data);
}
function GetRatingsByPhotoIdCallback(xhr,photoID){
	var ratings = JSON.parse(xhr.responseText);
	console.log(typeof ratings);
	ratingsByphotoID.addPair(photoID,ratings);
}
/*=======================================
   request to OtherUserProfile
=======================================*/
function OtherUserProfile(callback,visitName) {
	"use strict";
	var data = new FormData();
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "OtherUserProfile");
	xhr.onload = function () {
	    if(xhr.readyState == 4 && xhr.status === 200) {
	    	callback(xhr,visitName);
	    } else if(xhr.status != 200) {
	        alert("Request failed. Returned status of" + xhr.status);
	    }
	};
	data.append('visitName', visitName);
	xhr.send(data);
}
function OtherUserProfileCallback(xhr,visitName){
	visitFlag = true;
	document.body.innerHTML = xhr.responseText;
	GetImageCollection(GetImageCollectionCallback,visitName);
}




/*=======================================
		showModal on click of signUp
=======================================*/
function showModal() {
	document.getElementById("id01").setAttribute("style", "display: block; width: auto;");
}

/*=======================================
		closeLoginModal
=========================================*/

document.getElementById('myAlert').addEventListener("click", function(){
	document.getElementById('myAlert').style.display = "none";
});


/*=======================================
           clearFormInputs
=========================================*/
function resetForm() {
    document.getElementById("myForm").reset();
}

/*=======================================
   load countries.json asynchronously
=========================================*/
function loadJSON(callback) {   
	var xobj = new XMLHttpRequest();
	    xobj.overrideMimeType("application/json");
	xobj.open('GET', "json/countries.json", true); 
	xobj.onreadystatechange = function () {
	      if (xobj.readyState == 4 && xobj.status == "200") {
	        callback(xobj.responseText);
	      }
	};
	xobj.send(null);  
}


function JsonToSelect() {
	"use strict";
	loadJSON(function(response) {
	// Parse JSON string into object
		var jObj = JSON.parse(response);
		var select = document.getElementById("country");
		for(var i = 0; i<jObj.length; i++) {
			var option = document.createElement("option");
			option.value = jObj[i].name;
			option.innerHTML = jObj[i].name;
			select.appendChild(option);
		}
	});
}

/*=======================================
    Handlers
=========================================*/
function RegHandler() {
   "use strict";
   if(ValidationForm()){
      registerReq();
   }
}

function upDateHandler(callback){
		"use strict";
	   if(UpdateValidationForm()){
		   upDateReq(callback);
	   }
}


/*=================================================
		valdateUpdateData
===================================================*/
function UpdateValidationForm() {
	"use strict";
	/*==========return==========*/
	
	if(!emailValidation()) {
		return false;
	}
	if(!passValidation()) {
		return false;
	}
	if(!nameValidation()) {
		return false;
	}
	if(!lastNameValidation()) {
		return false;
	}
	if(!cityValidation()) {
		return false;
	}
	if(!infoValidation()) {
		return false;
	}
	return true;

	/*=======================================
	     emailValidation
	=========================================*/
	function emailValidation() {
		var emailExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		var mail;
		mail = document.getElementById("email").value;
		
		if(document.getElementById("email").value === "") {
		    document.getElementById("p2").innerText = "Please enter your email in the form";
		    document.getElementById('userName').scrollIntoView();
		return false;
		}
		if(!emailExp.test(document.getElementById("email").value)) {
		    document.getElementById("p2").innerText = "Please enter a valid email address";
		    document.getElementById("userName").focus();
		    return false;
		}
		document.getElementById("p2").innerText = "";
		document.getElementById("p2").style.display = "none";
		return true;
	}
	
	/*=======================================
	    passValidation
	=========================================*/
	function passValidation() {
	    if(document.getElementById("password").value === "") {
	        document.getElementById("p3").innerText = "Please enter your password in the form";
	        document.getElementById('email').scrollIntoView();
	        return false;
	    }
	
	    if(lengthDefine(document.getElementById("password").value, 6, 10) === false) {
	        document.getElementById("p3").innerText = "Please enter between " + 6 + " and " + 10 + " characters *";
	        document.getElementById('email').scrollIntoView();
	        return false;
	    }
	
	    var pswd = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,10}$/;
	    if(!pswd.test(document.getElementById("password").value)) {
	        document.getElementById("p3").innerText = "Please enter at least one numeric digit and a special character";
	        document.getElementById('email').scrollIntoView();
	        return false;
	    }
	    document.getElementById("p3").innerText = "";
	    document.getElementById("p3").style.display = "none";
	    return true;
	}
	
	
	/*=======================================
	    firstNameValidation
	=========================================*/
	function nameValidation() {
	    if(document.getElementById("firstName").value === "") {
	        document.getElementById("p5").innerText = "Please enter your first Name in the form";
	        document.getElementById('password').scrollIntoView();
	        return false;
	    }
	
	    var name = /^[a-zA-Z]+$/;
	    if(!name.test(document.getElementById("firstName").value)) {
	        document.getElementById("p5").innerText = "Please enter only characters";
	        document.getElementById('password').scrollIntoView();
	        return false;
	    }
	    if(lengthDefine(document.getElementById("firstName").value, 3, 20) === false) {
	        document.getElementById("p5").innerText = "Please enter between " + 3 + " and " + 20 + " characters *";
	        document.getElementById('password').scrollIntoView();
	        return false;
	    }
	    document.getElementById("p5").innerText = "";
	    document.getElementById("p5").style.display = "none";
	    return true;
	}
	
	/*=======================================
	    lastNameValidation
	=========================================*/
	function lastNameValidation() {
	    if(document.getElementById("lastName").value === "") {
	        document.getElementById("p6").innerText = "Please enter your Last Name in the form";
	        document.getElementById('firstName').scrollIntoView();
	        return false;
	    }
	
	    var name = /^[a-zA-Z]+$/;
	    if(!name.test(document.getElementById("lastName").value)) {
	        document.getElementById("p6").innerText = "Please enter only characters";
	        document.getElementById('firstName').scrollIntoView();
	        return false;
	    }
	    if(lengthDefine(document.getElementById("lastName").value, 4, 20) === false) {
	        document.getElementById("p6").innerText = "Please enter between " + 4 + " and " + 20 + " characters *";
	        document.getElementById('firstName').scrollIntoView();
	        return false;
	    }
	    document.getElementById("p6").innerText = "";
	    document.getElementById("p6").style.display = "none";
	    return true;
	}
	
	/*=======================================
	      CityValidation
	=========================================*/
	function cityValidation() {
	    if(document.getElementById("city").value === "") {
	        document.getElementById("p8").innerText = "Please enter your city in the form";
	        document.getElementById('country').scrollIntoView();
	        return false;
	    }
	
	    var name = /^[a-zA-Z]+$/;
	    if(!name.test(document.getElementById("city").value)) {
	        document.getElementById("p8").innerText = "Please enter only characters";
	        document.getElementById('country').scrollIntoView();
	        return false;
	    }
	    if(lengthDefine(document.getElementById("city").value, 2, 50) === false) {
	        document.getElementById("p8").innerText = "Please enter between " + 2 + " and " + 50 + " characters *";
	        document.getElementById('country').scrollIntoView();
	        return false;
	    }
	    document.getElementById("p8").innerText = "";
	    document.getElementById("p8").style.display = "none";
	    return true;
	}
	
	/*=======================================
	    InfoValidation
	=========================================*/
	function infoValidation() {
	    if(lengthDefine(document.getElementById("info").value, 0, 500) === false) {
	        document.getElementById("p9").innerText = "Max characters: 500";
	        document.getElementById('city').scrollIntoView();
	        return false;
	    }
	    document.getElementById("p9").innerText = "";
	    document.getElementById("p9").style.display = "none";
	    return true;
	}
	
	/*=======================================
	    lengthValidation
	=========================================*/
	function lengthDefine(input, min, max) {
	    if(input.length >= min && input.length <= max) {
	        return true;
	    }
	    return false;
	}
}

/*===========================================================
            valdateData
=============================================================*/
function ValidationForm() {
    "use strict";
    /*==========return==========*/
    if(!userNameValidation()) {
        return false;
    }
    if(!emailValidation()) {
        return false;
    }
    if(!passValidation()) {
        return false;
    }
    if(!confirmValidation()) {
        return false;
    }
    if(!nameValidation()) {
        return false;
    }
    if(!lastNameValidation()) {
        return false;
    }
    if(!cityValidation()) {
        return false;
    }
    if(!infoValidation()) {
        return false;
    }
        
    return true;


    /*=======================================
      userNameValidation
  =========================================*/
    function userNameValidation() {
        if(document.getElementById("userName").value === "") {
            document.getElementById("p1").innerText = "Please enter your Name in the form";
            document.getElementById('login').scrollIntoView();
            return false;
        }
        if(document.getElementById("userName").value.length < 8) {
            document.getElementById("p1").innerText = "minimum 8 characters";
            document.getElementById('login').scrollIntoView();
            return false;
        }
        document.getElementById("p1").innerText = "";
        document.getElementById("p1").style.display = "none";
        return true;
    }
    /*=======================================
       emailValidation
   =========================================*/
    function emailValidation() {
        var emailExp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        var mail;
        mail = document.getElementById("email").value;

        if(document.getElementById("email").value === "") {
            document.getElementById("p2").innerText = "Please enter your email in the form";
            document.getElementById('userName').scrollIntoView();
            return false;
        }
        if(!emailExp.test(document.getElementById("email").value)) {
            document.getElementById("p2").innerText = "Please enter a valid email address";
            document.getElementById('userName').scrollIntoView();
            return false;
        }
        document.getElementById("p2").innerText = "";
        document.getElementById("p2").style.display = "none";
        return true;
    }

    /*=======================================
       passValidation
   =========================================*/
    function passValidation() {
        if(document.getElementById("password").value === "") {
            document.getElementById("p3").innerText = "Please enter your password in the form";
            document.getElementById('email').scrollIntoView();
            return false;
        }

        if(lengthDefine(document.getElementById("password").value, 6, 10) === false) {
            document.getElementById("p3").innerText = "Please enter between " + 6 + " and " + 10 + " characters *";
            document.getElementById('email').scrollIntoView();
            return false;
        }

        var pswd = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,10}$/;
        if(!pswd.test(document.getElementById("password").value)) {
            document.getElementById("p3").innerText = "Please enter at least one numeric digit and a special character";
            document.getElementById('email').scrollIntoView();
            return false;
        }
        document.getElementById("p3").innerText = "";
        document.getElementById("p3").style.display = "none";
        return true;
    }

    /*=======================================
       confirmValidation
   =========================================*/
    function confirmValidation() {
        if(document.getElementById("confirmPass").value === "") {
            document.getElementById("p4").innerText = "Please enter your confirm password in the form";
            document.getElementById('email').scrollIntoView();
            return false;
        }

        if(document.getElementById("password").value != document.getElementById("confirmPass").value) {
            document.getElementById("p4").innerText = "Please re-enter your confirm password in the form";
            document.getElementById('email').scrollIntoView();
            return false;
        }
        document.getElementById("p4").innerText = "";
        document.getElementById("p4").style.display = "none";
        return true;
    }

    /*=======================================
       firstNameValidation
   =========================================*/
    function nameValidation() {
        if(document.getElementById("firstName").value === "") {
            document.getElementById("p5").innerText = "Please enter your first Name in the form";
            document.getElementById('password').scrollIntoView();
            return false;
        }

        var name = /^[a-zA-Z]+$/;
        if(!name.test(document.getElementById("firstName").value)) {
            document.getElementById("p5").innerText = "Please enter only characters";
            document.getElementById('password').scrollIntoView();
            return false;
        }
        if(lengthDefine(document.getElementById("firstName").value, 3, 20) === false) {
            document.getElementById("p5").innerText = "Please enter between " + 3 + " and " + 20 + " characters *";
            document.getElementById('password').scrollIntoView();
            return false;
        }
        document.getElementById("p5").innerText = "";
        document.getElementById("p5").style.display = "none";
        return true;
    }

    /*=======================================
       lastNameValidation
   =========================================*/
    function lastNameValidation() {
        if(document.getElementById("lastName").value === "") {
            document.getElementById("p6").innerText = "Please enter your Last Name in the form";
            document.getElementById('password').scrollIntoView();
            return false;
        }

        var name = /^[a-zA-Z]+$/;
        if(!name.test(document.getElementById("lastName").value)) {
            document.getElementById("p6").innerText = "Please enter only characters";
            document.getElementById('password').scrollIntoView();
            return false;
        }
        if(lengthDefine(document.getElementById("lastName").value, 4, 20) === false) {
            document.getElementById("p6").innerText = "Please enter between " + 4 + " and " + 20 + " characters *";
            document.getElementById('password').scrollIntoView();
            return false;
        }
        document.getElementById("p6").innerText = "";
        document.getElementById("p6").style.display = "none";
        return true;
    }

    /*=======================================
       CityValidation
   =========================================*/
    function cityValidation() {
        if(document.getElementById("city").value === "") {
            document.getElementById("p8").innerText = "Please enter your city in the form";
            document.getElementById('birthDate').scrollIntoView();
            return false;
        }

        var name = /^[a-zA-Z]+$/;
        if(!name.test(document.getElementById("city").value)) {
            document.getElementById("p8").innerText = "Please enter only characters";
            document.getElementById('birthDate').scrollIntoView();
            return false;
        }
        if(lengthDefine(document.getElementById("city").value, 2, 50) === false) {
            document.getElementById("p8").innerText = "Please enter between " + 2 + " and " + 50 + " characters *";
            document.getElementById('birthDate').scrollIntoView();
            return false;
        }
        document.getElementById("p8").innerText = "";
        document.getElementById("p8").style.display = "none";
        return true;
    }

    /*=======================================
       InfoValidation
   =========================================*/
    function infoValidation() {
        if(lengthDefine(document.getElementById("info").value, 0, 500) === false) {
            document.getElementById("p9").innerText = "Max characters: 500";
            document.getElementById('city').scrollIntoView();
            return false;
        }
        document.getElementById("p9").innerText = "";
        document.getElementById("p9").style.display = "none";
        return true;
    }

    /*=======================================
       lengthValidation
   =========================================*/
    function lengthDefine(input, min, max) {
        if(input.length >= min && input.length <= max) {
            return true;
        }
        return false;
    }


}

