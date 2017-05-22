var TIVComment = (function () {
    'use strict';
    var loadedComments = [];
    var commentId;
    var photoID;
    var userName;

    
    function innerSetComments(comments) {
      loadedComments = comments;
    }

    function innerGetComments() {
      return loadedComments;
    }

    function innerAddComment(comment) {
      loadedComments.push(comment);
    }

    function innerRemoveComment(i) {
      loadedComments.splice(i, i + 1);
    }

    function innerRemoveComments() {
      loadedComments = [];
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
    
    function innerShowComment(elem) { //elem is the comments
      console.log('Entered: innerShowComment');
      if (loadedComments.length === 0) {
        var header = document.createElement('h2');
        header.innerHTML = 'NO Comment ... . .';
        document.getElementById(elem).appendChild(header);
      }

    
      var commentElem = document.createElement('div');
      commentElem.setAttribute('id', 'comment' + loadedComments[loadedComments.length - 1].ID);

      var commentRowDetails = document.createElement('div');
      commentRowDetails.setAttribute('class', 'row');
      var commentColDetails = document.createElement('div');
      commentColDetails.setAttribute('class', 'col-lg-12 col-md-12 col-sm-12');
      var commentDetails = document.createElement('p');
      commentDetails.setAttribute('class', 'comment');
      var strongDetails = document.createElement('strong');
      strongDetails.innerHTML = 'Comment By:' + loadedComments[loadedComments.length - 1].userName + ' &nbsp;&nbsp;&nbsp;&nbsp;     time: '  + loadedComments[loadedComments.length - 1].timestamp;
      commentDetails.appendChild(strongDetails);
      commentColDetails.appendChild(commentDetails);
      commentRowDetails.appendChild(commentColDetails);

      
      	
      var commentRow = document.createElement('div');
      commentRow.setAttribute('class', 'row');
      var commentCol = document.createElement('div');
      commentCol.setAttribute('class', 'col-lg-12 col-md-12 col-sm-12');
      var comment = document.createElement('p');
      comment.setAttribute('class', 'comment');
      comment.innerHTML = loadedComments[loadedComments.length - 1].comment;
      
      if(loggedUser != null && loadedComments[loadedComments.length - 1].userName == loggedUser){
    	  var deleteComment = document.createElement('span');
          deleteComment.setAttribute('class', 'deleteComment');
          deleteComment.setAttribute('onclick', 'deleteComment(' + loadedComments[loadedComments.length - 1].ID +');');
          deleteComment.innerHTML = '×';
          comment.appendChild(deleteComment);
      }
      
      commentCol.appendChild(comment);
      commentRow.appendChild(commentCol);

      var commentLine = document.createElement('hr');
      commentLine.setAttribute('class', 'commentHr');

      commentElem.appendChild(commentRowDetails);
      commentElem.appendChild(commentRow);
      commentElem.appendChild(commentLine);

      var commentsSection = document.getElementById(elem);
      commentsSection.appendChild(commentElem);
    
    
    }

    function innerShowComments(elem) { //elem is the comments
      console.log('Entered: innerShowComments');
      
    	  innerClearComments();
          if (loadedComments.length === 0) {
            var header = document.createElement('h2');
            header.innerHTML = '    ... . .';
            document.getElementById(elem).appendChild(header);
          }

          for (var i = 0; i < loadedComments.length; i++) { 
            
            var commentElem = document.createElement('div');
            commentElem.setAttribute('id', 'comment' + loadedComments[i].ID);

            var commentRowDetails = document.createElement('div');
            commentRowDetails.setAttribute('class', 'row');
            var commentColDetails = document.createElement('div');
            commentColDetails.setAttribute('class', 'col-lg-12 col-md-12 col-sm-12');
            var commentDetails = document.createElement('p');
            commentDetails.setAttribute('class', 'comment');
            var strongDetails = document.createElement('strong');
            strongDetails.innerHTML = 'Comment By: ' + loadedComments[i].userName + ' &nbsp;&nbsp;&nbsp;&nbsp;     time: ' + loadedComments[i].timestamp;
            commentDetails.appendChild(strongDetails);
            commentColDetails.appendChild(commentDetails);
            commentRowDetails.appendChild(commentColDetails);

            var commentRow = document.createElement('div');
            commentRow.setAttribute('class', 'row');
            var commentCol = document.createElement('div');
            commentCol.setAttribute('class', 'col-lg-12 col-md-12 col-sm-12');
            var comment = document.createElement('p');
            comment.setAttribute('class', 'comment');
            comment.innerHTML = loadedComments[i].comment;
            
            if(loggedUser != null && loadedComments[i].userName == loggedUser){
          	  var deleteComment = document.createElement('span');
                deleteComment.setAttribute('class', 'deleteComment');
                deleteComment.setAttribute('onclick', 'deleteComment(' + loadedComments[i].ID +');');
                deleteComment.innerHTML = '×';
                comment.appendChild(deleteComment);
            }
            
            commentCol.appendChild(comment);
            commentRow.appendChild(commentCol);

            var commentLine = document.createElement('hr');
            commentLine.setAttribute('class', 'commentHr');

            commentElem.appendChild(commentRowDetails);
            commentElem.appendChild(commentRow);
            commentElem.appendChild(commentLine);

            var commentsSection = document.getElementById(elem);
            commentsSection.appendChild(commentElem);
          }
      
    }
    
    function innerClearComments() {
    	var commentsSection = document.getElementById('commentsSection');
    	while (commentsSection.hasChildNodes())
    		commentsSection.removeChild(commentsSection.lastChild);
    }

    return {
        setComments:   innerSetComments,
        getComments:   innerGetComments,
        addComment:    innerAddComment,
        removeComment: innerRemoveComment,
        removeComments:innerRemoveComments,
        setPhotoID:    innerSetPhotoID,
        getPhotoID:    innerGetPhotoID,
        setUserName:   innerSetUserName,
        getUserName:   innerGetUserName,
        showComment:   innerShowComment,
        showComments:  innerShowComments,
        clearComments: innerClearComments
      };
  })();



/* manages array of all commnets of loaded photos */
var commentsByphotoID =  (function () {
	var IDcommentsPair = {};
	
	function innerAddPair(key,value) {
		IDcommentsPair[key]= value;
	}
	
	function innerGetPair(key) {
		return IDcommentsPair[key];
	}
	
	function innerSetPair(key) {
		return IDcommentsPair[key];
	}
	
	function innerClearIDcommentsPair() {
		IDcommentsPair = [];
	}
	
	function innertoString(){
		for (var key in IDcommentsPair) {
			  if (IDcommentsPair.hasOwnProperty(key)) {
			    console.log(key + ' -> ' + IDcommentsPair[key][0].comment);
			  }
		}
	}
	
	
	return {
		addPair:   innerAddPair,
		getPair: innerGetPair,
		setPair: innerSetPair,
		clearIDcommentsPair: innerClearIDcommentsPair,
		toString: innertoString
	};
})();


function comment(comment,userName,photoID){
	this.comment   = comment;
	this.userName  = userName
	this.photoID   = photoID,
	this.timestamp = new Date()
}


var PostCommentBtn = document.getElementById('PostCommentButton');
PostCommentBtn.addEventListener('click', function () {
   PostComment();
}, false);


function PostComment() {
	var myComment = new comment(document.getElementById('CommentArea').value, loggedUser, TIVComment.getPhotoID());
    makeComment("makeCommentCallback",myComment);
    document.getElementById('CommentArea').value = '';
}


function deleteComment(ID) {
	var myComment = {};
	myComment.photoID = TIVComment.getPhotoID();
	myComment.ID = ID;
	DeleteCommentByID(DeleteCommentByIDCallback,myComment);
}


