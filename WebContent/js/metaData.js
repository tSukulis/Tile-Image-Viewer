/* manages array of metaData of loaded photos */
var metaDataByphotoID =  (function () {
	var IDmetaDataPair = {};
	
	function innerAddMetaData(key,value) {
		IDmetaDataPair[key]= value;
	}
	
	function innerGetMetaData(key) {
		return IDmetaDataPair[key];
	}
	
	function innerClearMetaData() {
		IDmetaDataPair = [];
	}
	
	function innerShowMetaData(key) {
		
		innerClearMetaDataDOM();
		if(typeof IDmetaDataPair[key] !== undefined){
			var metaDataSection = document.getElementById('metaData');
			if(IDmetaDataPair[key].title !== 'Untitled'){
				
				/* create first row of metaData with the title */
				var titleRow = document.createElement('div');
				titleRow.setAttribute('class', 'row');
	            var titleColumn = document.createElement('div');
	            titleColumn.setAttribute('class', 'col-lg-12 col-md-12 col-sm-12');
	            var titleMetadata = document.createElement('p');
	            titleMetadata.setAttribute('class', 'comment');
	            var title = document.createElement('strong');
	            
	            title.innerHTML = IDmetaDataPair[key].title;
	            titleMetadata.appendChild(title);
	            titleColumn.appendChild(titleMetadata);
	            titleRow.appendChild(titleColumn);
	            metaDataSection.appendChild(titleRow);
			}
            /* create second row of metaData with details */
            var detailsRow = document.createElement('div');
            detailsRow.setAttribute('class', 'row');
            var detailsColumn = document.createElement('div');
            detailsColumn.setAttribute('class', 'col-lg-10 col-md-10 col-sm-10');
            var detailsMetadata = document.createElement('p');
            detailsMetadata.setAttribute('class', 'comment');
            var details = document.createElement('strong');
            details.innerHTML = ' upLoaded by: ' + IDmetaDataPair[key].userName + ' at ' + IDmetaDataPair[key].date;
            detailsMetadata.appendChild(details);
            detailsColumn.appendChild(detailsMetadata);
            detailsRow.appendChild(detailsColumn);
            
            if(loggedUser != null && visitFlag == false){	
	            var trashColumn = document.createElement('div');
	            trashColumn.setAttribute('class', 'col-lg-2 col-md-2 col-sm-2');
	            var trashAbbr = document.createElement('abbr');
	            trashAbbr.setAttribute('title', 'delelte Image');
	            var trashIcon = document.createElement('span');
	            trashIcon.setAttribute('class', 'trash');
	            trashIcon.setAttribute('class', 'glyphicon glyphicon-trash trash');
	            trashIcon.addEventListener('click', function() {
	            	DeletePhoto(key,DeletePhotoCallback);
	            }, false);
	            
	            trashAbbr.appendChild(trashIcon);
	            trashColumn.appendChild(trashAbbr);
	            detailsRow.appendChild(trashColumn);
            }
            metaDataSection.appendChild(detailsRow);
            
            var metaDataHr = document.createElement('hr');
            metaDataHr.setAttribute('class', 'metaDataHr');
            metaDataSection.appendChild(metaDataHr);
			
		}
	}
	
	function innerClearMetaDataDOM() {
    	var metaDataSection = document.getElementById('metaData');
    	while (metaDataSection.hasChildNodes())
    		metaDataSection.removeChild(metaDataSection.lastChild);
    }
	
	
	function innertoString(){
		for (var key in IDmetaDataPair) {
			  if (IDmetaDataPair.hasOwnProperty(key)) {
			    console.log(key + " -> " + IDmetaDataPair[key][0].comment);
			  }
		}
	}
	
	
	return {
		addMetaData:     innerAddMetaData,
		getMetaData:     innerGetMetaData,
		clearMetaData:   innerClearMetaData,
		showMetaData:    innerShowMetaData,
		clearMetaDataDOM:innerClearMetaDataDOM,
		toString:        innertoString
	};
})();