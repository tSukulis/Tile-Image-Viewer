var TIV2575 = function() {
    "use strict";
    var files;
    var loadedImagesArray = [];
    var lastIndex = 0; //index to last image drawn

    function innerLoadImages() {
        if (loadedImagesArray.length === 0) {
            lastIndex = 0;
        } else {
            lastIndex = loadedImagesArray.length;
        }
        //if elem not exits loadedImagesArray is Null
        if (!document.getElementById("imagesCollection")) {
            loadedImagesArray = null;
        } else {
            files = document.getElementById("imagesCollection").files;
            var index = 0;
            for (var i = 0; i < files.length; i++) {
                /*check if file is an image though using the file.name */
                if (!files[i].name.match(/\.(jpg|jpeg|png|gif)$/)) {
                    alert(files[i].name + ": not an image");
                    continue;
                }
                //store image files to loadedImages
                loadedImagesArray[index] = files[i];
                index++;
            }
        }
    }

    function innerGetLoadImages() {
        return loadedImagesArray;
    }

    /* clear array so not to duplicate images from DB */
    function innerClearLoadImages() {
    	loadedImagesArray = [];
    }
    
    
    /* clear array so not to duplicate images from DB */
    function innerAddImage(file){
    	loadedImagesArray.push(file);
    }
    
    function innerShowLoadImages(elem) { //elem is the container-fluid
        console.log("Entered: innerShowLoadImages");
        if (loadedImagesArray.length === 0) {
            var header = document.createElement("h2");
            header.innerHTML = 'NO elem has been Loaded';
            document.getElementsByClassName(elem)[2].appendChild(header);
        }
        var row = document.createElement("div");
        var index = 1;
        for (var i = lastIndex; i < loadedImagesArray.length; i++) {
            if ((document.getElementsByClassName("row").length) !== 0) {
                row.setAttribute('class', "row tile top-offsetCol");
            } else {
                row.setAttribute('class', "row tile");
            }
            var imag = loadedImagesArray[i];
            var reader = new FileReader();
            // Closure to capture the file information.
            reader.onload = (function(imag, i) {
                return function(e) {
                    // Render thumbnail.
                    //creation of tiles
                    var col = document.createElement("div");
                    col.setAttribute('class', "col-xs-6 col-sm-2 col-md-2");
                    var fig = document.createElement("figure");
                    fig.setAttribute('class', "image");
                    fig.setAttribute('id', "img" + i);
                    /*=======EVENT LISTENER========================*/
                    /* adds onclick function: innerShowImageDetailedExifWithMap 
                     to image if it has exif metadata and GPSLatitude is not null
                     else adds onclick function: innerShowImageDetailedExifInfo
                     if no metaData the adds onclick function: innerShowImage*/
                  
                    EXIF.getData(innerGetLoadImages()[i], function() {
                    	
                        if (EXIF.pretty(this) === "") {
                        	fig.addEventListener('click', function() {
                        		if (typeof commentsByphotoID.getPair(innerGetLoadImages()[i].id) !== undefined){
                        			TIVComment.setComments(commentsByphotoID.getPair(innerGetLoadImages()[i].id));
                        		}
                        		TIVComment.setPhotoID(innerGetLoadImages()[i].id);
                        		
                        		
                        		if (typeof ratingsByphotoID.getPair(innerGetLoadImages()[i].id) !== undefined){
                        			TIVRatings.setRatings(ratingsByphotoID.getPair(innerGetLoadImages()[i].id));
                        		}
                        		TIVRatings.setPhotoID(innerGetLoadImages()[i].id);
                        		
                        		innerShowImage(i, "myModal");
                            }, false);
                        }
                        else if (EXIF.pretty(this) !== "" && (EXIF.getTag(this, "GPSLatitude") === "" || EXIF.getTag(this, "GPSLatitude") === undefined)){
                        	fig.addEventListener('click', function() {
                        		if (typeof commentsByphotoID.getPair(innerGetLoadImages()[i].id) !== undefined){
                        			TIVComment.setComments(commentsByphotoID.getPair(innerGetLoadImages()[i].id));
                        		}
                        		TIVComment.setPhotoID(innerGetLoadImages()[i].id);
                        		
                        		if (typeof ratingsByphotoID.getPair(innerGetLoadImages()[i].id) !== undefined){
                        			TIVRatings.setRatings(ratingsByphotoID.getPair(innerGetLoadImages()[i].id));
                        		}
                        		TIVRatings.setPhotoID(innerGetLoadImages()[i].id);
                        		
                        		innerShowImageDetailedExifInfo(i, "myModal");
                            }, false);
                        }
                        else{
                        	fig.addEventListener('click', function() {
                        		if (typeof commentsByphotoID.getPair(innerGetLoadImages()[i].id) !== undefined){
                        			TIVComment.setComments(commentsByphotoID.getPair(innerGetLoadImages()[i].id));
                        		}
                        		TIVComment.setPhotoID(innerGetLoadImages()[i].id);
                        		
                        		if (typeof ratingsByphotoID.getPair(innerGetLoadImages()[i].id) !== undefined){
                        			TIVRatings.setRatings(ratingsByphotoID.getPair(innerGetLoadImages()[i].id));
                        		}
                        		TIVRatings.setPhotoID(innerGetLoadImages()[i].id);
                        		
                        		innerShowImageDetailedExifWithMap(i, "myModal");
                            }, false);
                        }
                    });
                    
                    
                    
                    /*==================================================*/
                    imag.src = e.target.result ;
                    fig.innerHTML = ['<img src="', e.target.result,
                        '" title="', escape(imag.name), '">'
                    ].join('');
                    col.appendChild(fig);
                    var figc = document.createElement("figcaption");
                    figc.innerHTML = "lalalala<br>";
                    fig.appendChild(figc);
                    var span = document.createElement('span');
                    span.innerHTML = "lalalala2";
                    figc.appendChild(span);
                    
                    if(loggedUser != null){
                    	if(visitFlag == false){
                    		var btn = document.createElement("button");
    	                    btn.setAttribute('class', "btn btn-default");
    	                    btn.setAttribute('id', "deletePhotobutton");
    	                    btn.innerHTML = "delete";
    	                    
    	                    btn.addEventListener('click', function() {
    	                    	var currentId = innerGetLoadImages()[i].id;
    	                    	DeletePhoto(currentId,DeletePhotoCallback);
    	                    }, false);
    	
    	                    col.appendChild(btn);
                    	}
	                    
	                    /*var rateRow = document.createElement("div");
	                    rateRow.setAttribute('class', 'row');
	                    var ColratingLabel = document.createElement("div");
	                    ColratingLabel.setAttribute('class', 'col-sm-3 col-md-3 col-lg-3');
	                    
	                    var ratingLabel = document.createElement("label");
	                    ratingLabel.innerHTML = "Rate";
	                    ColratingLabel.appendChild(ratingLabel);
	                    rateRow.appendChild(ColratingLabel);
	                    
	                    var Colrating = document.createElement("div");
	                    Colrating.setAttribute('class', 'col-sm-3 col-md-3 col-lg-3');
	                    
	                    var rating = document.createElement("input");
	                    rating.setAttribute('class', 'rateFirst');
	                    //rating.setAttribute('id', 'rating'+i);
	                    rating.setAttribute('id', 'rating');
	                    rating.setAttribute('type', 'number');
	                    rating.setAttribute('min', '0');
	                    rating.setAttribute('max', '5');
	                    rating.setAttribute('size', '5');
	                    Colrating.appendChild(rating);
	                    rateRow.appendChild(Colrating);
	                    
	                    var ColratingOk = document.createElement("div");
	                    ColratingOk.setAttribute('class', 'col-sm-2 col-md-2 col-lg-2');
	                    var buttonRating = document.createElement("button");
	                    buttonRating.setAttribute('id', 'buttonRating');
	                    buttonRating.innerHTML = "OK";
	                    ColratingOk.appendChild(buttonRating);
	                    rateRow.appendChild(ColratingOk);*/
	                    
	                    /*buttonRating.addEventListener('click', function() {
	                    	if (typeof ratingsByphotoID.getPair(innerGetLoadImages()[i].id) !== undefined){
                    			TIVRatings.setRatings(ratingsByphotoID.getPair(innerGetLoadImages()[i].id));
                    		}
                    		TIVRatings.setPhotoID(innerGetLoadImages()[i].id);
	                    	PostRating();
	                    	
	                    }, false);
	                    
	                    col.appendChild(rateRow);*/
                    }
                    row.appendChild(col);
                    //append the completed row to conatiner-fluid elem and create a new one
                    if (index == 6) {
                        document.getElementsByClassName(elem)[2].appendChild(row);
                        row = null;
                        row = document.createElement("div");
                        row.setAttribute('class', "row tile top-offsetCol");
                        index = 0;
                    }
                    index++;
                    /*if row is not completed at the end of the loop
                    append the row to container-fluid elem*/
                    if (i == loadedImagesArray.length - 1) {
                        document.getElementsByClassName(elem)[2].appendChild(row);
                    }
                };
            })(imag, i);
            // Read in the image file as a data URL.
            reader.readAsDataURL(imag);
            
            
        }
    }

    function innerShowImage(index, elem) {
        console.log("Entered: innerShowImage");
        var modal = document.getElementById(elem);
        var modalImg = document.getElementById("img01");
        var captionText = document.getElementById("caption");
        document.getElementById('exif').innerHTML = null;
        document.getElementById('map').innerHTML = null;
        
        if(document.getElementById('mapTitle') !== null){
        	document.getElementById('mapTitle').style.display = "none";
        }
        /* set PhotoID for comments - ratings */
        //TIVComment.setPhotoID(innerGetLoadImages()[index].id);
        TIVRatings.showRating();
        metaDataByphotoID.showMetaData(innerGetLoadImages()[index].id);
        TIVComment.showComments('commentsSection');
        modal.style.display = "block";
        modalImg.src = innerGetLoadImages()[index].src;
        modal.scrollTo(0,0);
        //captionText.innerHTML = "URL: " + innerGetLoadImages()[index].webkitRelativePath;

        // Get the <span> element that closes the modal
        var close = document.getElementById("collectionModalClose");

        // When the user clicks on <span> (x), close the modal
        close.onclick = function() {
        	TIVRatings.clearRatingsDOM('ratingSection');
            modal.style.display = 'none';
        };
        
        // When the user clicks on modal, close the modal
        modal.ondblclick = function() {
        	TIVRatings.clearRatingsDOM('ratingSection');
            modal.style.display = 'none';
        };
    }

    function innerShowImageDetailedExifInfo(index, elem) {
        /*gets the exif infos and loads them to section with id = "exif"*/
        EXIF.getData(innerGetLoadImages()[index], function() {
            document.getElementById('exif').innerHTML = EXIF.pretty(this);
        });
        document.getElementById('map').innerHTML = null;
        var modal = document.getElementById(elem);
        var modalImg = document.getElementById("img01");
        var captionText = document.getElementById("caption");
        
        if(document.getElementById('mapTitle') !== null){
        	document.getElementById('mapTitle').style.display = 'none';
        }
        
        TIVRatings.showRating();
        metaDataByphotoID.showMetaData(innerGetLoadImages()[index].id);
        TIVComment.showComments('commentsSection');
        modal.style.display = "block";
        modalImg.src = innerGetLoadImages()[index].src;
        modal.scrollTo(0,0);
        
        //captionText.innerHTML = "URL: " + innerGetLoadImages()[index].webkitRelativePath;

        // Get the <span> element that closes the modal
        var close = document.getElementById('collectionModalClose');

        // When the user clicks on <span> (x), close the modal
        close.onclick = function() {
        	TIVRatings.clearRatingsDOM('ratingSection');
            modal.style.display = 'none';
        };

        // When the user clicks on modal (x), close the modal
        modal.ondblclick = function() {
        	TIVRatings.clearRatingsDOM('ratingSection');
            modal.style.display = 'none';
        };
    }

    function innerShowImageDetailedExifWithMap(index, elem) {
        var Latitude = null;
        var Longitude = null;
        var toDecimal = null;
        var GPSLongitude = null;
        document.getElementById('exif').innerHTML = null;
        document.getElementById('map').innerHTML = null;
        if(document.getElementById('mapTitle') !== null){
        	document.getElementById('mapTitle').style.display = "block";
        	document.getElementById('mapTitle').style.color = "#d3d7cf";
        }
        
        /*gets the exif infos and loads them to section with id = "exif"*/
        EXIF.getData(innerGetLoadImages()[index], function() {
            document.getElementById('exif').innerHTML = EXIF.pretty(this);
            var GPSLatitude = EXIF.getTag(this, "GPSLatitude");
            var GPSLongitude = EXIF.getTag(this, "GPSLongitude");
            console.log(GPSLongitude);
            /*=== reverse geocoding=====*/
            Latitude = GPSLatitude[0].numerator + GPSLatitude[1].numerator / (60 * GPSLatitude[1].denominator) + GPSLatitude[2].numerator / (3600 * GPSLatitude[2].denominator);
            Longitude = GPSLongitude[0].numerator + GPSLongitude[1].numerator / (60 * GPSLongitude[1].denominator) + GPSLongitude[2].numerator / (3600 * GPSLongitude[2].denominator);
            /*==========Google Map Marker===========*/
            var uluru = {
                lat: Latitude,
                lng: Longitude
            };
            var map = new google.maps.Map(document.getElementById('map'), {
                zoom: 4,
                center: uluru
            });
            var marker = new google.maps.Marker({
                position: uluru,
                map: map
            });

        });
        
        TIVRatings.showRating();
        metaDataByphotoID.showMetaData(innerGetLoadImages()[index].id);
        TIVComment.showComments('commentsSection');
        modal.style.display = "block";
        modalImg.src = innerGetLoadImages()[index].src;
        modal.scrollTo(0,0);
        
        var modal = document.getElementById(elem);
        var modalImg = document.getElementById("img01");
        var captionText = document.getElementById("caption");
        modal.style.display = "block";
        modalImg.src = innerGetLoadImages()[index].src;
        modal.scrollTo(0,0);
        
        // Get the close element that closes the modal
        var close = document.getElementById("collectionModalClose");

        // When the user clicks on <close> (x), close the modal
        close.onclick = function() {
        	TIVRatings.clearRatingsDOM('ratingSection');
            modal.style.display = "none";
        };

        // When the user clicks on modal, close the modal
        modal.ondblclick = function() {
        	TIVRatings.clearRatingsDOM('ratingSection');
            modal.style.display = "none";
        };
    }
    
    return {
        loadImages: innerLoadImages,
        getLoadImages: innerGetLoadImages,
        addImage:	innerAddImage,
        clearLoadImages: innerClearLoadImages,
        showLoadImages: innerShowLoadImages,
        showImage: innerShowImage,
        showImageDetailedExifInfo: innerShowImageDetailedExifInfo,
        showImageDetailedExifWithMap: innerShowImageDetailedExifWithMap
    };
}();
