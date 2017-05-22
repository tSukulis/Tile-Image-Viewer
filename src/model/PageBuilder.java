package model;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import db.UserDB;
import servlets.RegisterServlet;
import servlets.ValidateServlet;


/**
 * @author sukulis 
 * A.M: 2575
 * class PageBuilder builds the template of the html page
 */
public class PageBuilder {
	
	/**
	 * @param signed
	 * @return sb.toString
	 */
	public static String  pageHeader(boolean signed,String page){
		StringBuilder sb = new StringBuilder();
		String log = "Sign In";
		String call = "sendModalReq()";
		if (signed == true) {
			log = "Sign Out";
			call = "LogOutReq(LogOutCallback)";
		}
		
		sb.append("<!DOCTYPE html>");
        sb.append("<html lang=\"en-US\">");
        sb.append("<head>");
        sb.append("<meta charset=\"utf-8\">");
		sb.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		sb.append("<title> Tile Image Viewer </title>");	
		sb.append("<link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>");
		sb.append("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">");
		sb.append("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css\">");
		if (page.equals("collection")) {
			sb.append("<link rel=\"stylesheet\" href=\"css/collection.css\">");
		}
		else{
			sb.append("<link rel=\"stylesheet\" href=\"css/reg.css\">");
		}
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<nav class=\"navbar navbar-default navbar-fixed-top navbar-custom\">");
		sb.append("<div class=\"container-fluid\">");
		sb.append("<!-- Brand and toggle get grouped for better mobile display -->");
		sb.append("<div class=\"navbar-header\">");
		sb.append("<button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\" aria-expanded=\"false\">");
		sb.append("<span class=\"sr-only\">Toggle navigation</span>");
		sb.append("<span class=\"icon-bar\"></span><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span>");
		sb.append("</button>");
		sb.append("<a id=\"brand\" class=\"navbar-brand\" href=\"tiv.html\">Tiv</a>");
		sb.append("</div>");
		sb.append("<!-- Collect the nav links, forms, and other content for toggling -->");
		sb.append("<div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">");
		sb.append("<ul id=\"navmarg\" class=\"nav navbar-nav\">");
		sb.append("<!--<li class=\"active\"><a href=\"#\">Link <span class=\"sr-only\">(current)</span></a></li>-->");
		sb.append("<li><a id = \"myHome\" onclick=\" collectionReq(collectionCallback,false);\" >Home</a></li><li><a href=\"#\">About Tiv</a></li>");
		sb.append("</ul>");
		sb.append("<ul class=\"nav navbar-nav navbar-right\">");
		if (signed == false) {
			sb.append("<li><a id=\"mySignUp\" href=\"register.html\"><span class=\"glyphicon glyphicon-user\"></span> Sign Up</a></li>");
		}
		sb.append("<li> <a id=\"mySignIn\" onclick="+call+">"+log+"</a> </li>");
		sb.append("</ul>");
		sb.append("</div>");
		sb.append("<!-- /.navbar-collapse -->");
		sb.append("</div>");
		sb.append("<!-- /.container-fluid -->");
		sb.append("</nav>");
		sb.append("<div id = \"login\"> </div>");
		
		if (page.equals("collection")) {
			sb.append("<div class=\"jumbotron\">");
			sb.append("<div  class=\"container-fluid\">");
			sb.append("<div class=\"row\">");
			sb.append("<div class=\"col-md-4 col-sm-2 col-lg-4\">");
			sb.append("<div class=\"dropdown\">");
			sb.append("<button id=\"menubtn\" class=\"btn dropdown-toggle\" type=\"button\" data-toggle=\"dropdown\">");
			sb.append("<span class=\"glyphicon glyphicon-align-justify\"></span>");
			sb.append("</button>");
			sb.append("<ul class=\"dropdown-menu\">");
			sb.append("<li><a class=\"drpbtn\" onclick=\"ShowProfile(ShowProfileCallback)\"><h4>Profile</h4></a></li>");
			sb.append("<li><a class=\"drpbtn\" onclick=\"EditProfileReq(callback)\"><h4>Edit Profile</h4></a></li>");
			sb.append("<li><a class=\"drpbtn\" onclick=\"showMembersReq(memberCallback)\"><h4>See All Users</h4></a></li>");
			sb.append("<li id=\"signout\" class=\"drpbtn\"><a onclick=\"LogOutReq(LogOutCallback)\"><h4>Sign Out</h4></a></li>");
			sb.append("</ul></div></div>");
			sb.append("<div class=\"col-md-4 col-sm-2 col-lg-4\">");
			sb.append("<h2>your Personal</h2><h1>Collection</h1></div>");
			sb.append("<div class=\"col-md-4 col-sm-2 col-lg-4\">");
			sb.append("<input id=\"imagesCollection\" type=\"file\" webkitdirectory mozdirectory directory name=\"myFiles\" class=\"pull-right\" onchange=\"TIV2575.loadImages();TIV2575.showLoadImages('container-fluid');\" multiple/ style=\"background-color:#272727;color:#fff;\">");
			sb.append("<label class=\"custom-file-upload\" for=\"imagesCollection\"><i class=\"fa fa-cloud-upload\"></i>Upload Your Photo</label>");
			sb.append("</div></div></div></div>");	
			/*===================================================================*/
			/*=================tileContainer======================================*/
			sb.append("<div id = \"tileContainer\" class=\"container-fluid\">");
			sb.append("<div id = \"test\" >");
			sb.append("</div>");
			sb.append("</div>");
			sb.append(PageBuilder.imageModal());
			sb.append("<section id='ratingsModalSection'></section>");
				/*=======================================================================================================================*/
		}
		else{sb.append("<div id=\"fluid\" class=\"container-fluid\">");}

		return sb.toString();
	}
	
	
	/**
	 * @param collection
	 * @return
	 */
	public static String  pageEnd(boolean collection){
		StringBuilder sb = new StringBuilder();
		if (collection == false) {
			sb.append("</div>");
		}
		
		sb.append("<footer class=\"footer\">");
		sb.append("<p id=\"ftr\"><small>Copyright ©</small></p>");
		sb.append("<!--small tag use -->");
		sb.append("</footer>");
		if (collection == true) {
			sb.append("<script src=\"js/collection.js\"></script>");
			sb.append("<script src=\"js/exif.js\"></script>");
			sb.append("<script async defer src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyAQpn8CugNQm6Gu5kkRIqaRwvE76NwIndQ\">");
			sb.append("<script src=\"js/metadData.js.js\"></script>");
		}
		sb.append("<script src=\"js/reg.js\"></script>");
		sb.append("<script src=\"https://code.jquery.com/jquery-3.1.1.min.js\"></script>");
		sb.append("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
		sb.append("</body>");
		sb.append("</html>");
		
		return sb.toString();
		
	}
	
	
	/**
	 * @param signed
	 * @param message
	 * @param page
	 * @return
	 */
	public static String  upBody(boolean signed,String message,String page){
		StringBuilder sb = new StringBuilder();
		String log = "Sign In";
		String call = "showModal()";
		if (signed == true) {
			log = "Sign Out";
			call = "LogOutReq(LogOutCallback)";
		}
		
		sb.append("<nav class=\"navbar navbar-default navbar-fixed-top navbar-custom\">");
		sb.append("<div class=\"container-fluid\">");
		sb.append("<!-- Brand and toggle get grouped for better mobile display -->");
		sb.append("<div class=\"navbar-header\">");
		sb.append("<button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\" aria-expanded=\"false\">");
		sb.append("<span class=\"sr-only\">Toggle navigation</span>");
		sb.append("<span class=\"icon-bar\"></span><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span>");
		sb.append("</button>");
		sb.append("<a id=\"brand\" class=\"navbar-brand\" href=\"tiv.html\">Tiv</a>");
		sb.append("</div>");
		sb.append("<!-- Collect the nav links, forms, and other content for toggling -->");
		sb.append("<div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">");
		sb.append("<ul id=\"navmarg\" class=\"nav navbar-nav\">");
		sb.append("<!--<li class=\"active\"><a href=\"#\">Link <span class=\"sr-only\">(current)</span></a></li>-->");
		sb.append("<li><a id = \"myHome\" onclick=\" collectionReq(collectionCallback,false);\" >Home</a></li><li><a href=\"#\">About Tiv</a></li>");
		sb.append("</ul>");
		sb.append("<ul class=\"nav navbar-nav navbar-right\">");
		sb.append("<li> <a id=\"mySignIn\"  onclick="+call+">"+log+"</a> </li>");
		if (signed == false) {
			sb.append("<li><a id=\"mySignUp\" href=\"register.html\">Sign Up</a></li>");
		}
		sb.append("</ul>");
		sb.append("</div>");
		sb.append("<!-- /.navbar-collapse -->");
		sb.append("</div>");
		sb.append("<!-- /.container-fluid -->");
		sb.append("</nav>");
		sb.append("<div id = \"login\"> </div>");
		
		/*==================================ForCollectionPage=====================================================================================*/
		if (signed == true && page.equals("collection")) {
			sb.append("<div class=\"jumbotron\">");
			sb.append("<div  class=\"container-fluid\">");
			sb.append("<div class=\"row\">");
			sb.append("<div class=\"col-md-4 col-sm-2 col-lg-4\">");
			sb.append("<div class=\"dropdown\">");
			sb.append("<button id=\"menubtn\" class=\"btn dropdown-toggle\" type=\"button\" data-toggle=\"dropdown\">");
			sb.append("<span class=\"glyphicon glyphicon-align-justify\"></span>");
			sb.append("</button>");
			sb.append("<ul class=\"dropdown-menu\">");
			sb.append("<li><a class=\"drpbtn\" onclick=\"ShowProfile(ShowProfileCallback)\"><h4>Profile</h4></a></li>");
			sb.append("<li><a class=\"drpbtn\" onclick=\"EditProfileReq(callback)\"><h4>Edit Profile</h4></a></li>");
			sb.append("<li><a class=\"drpbtn\" onclick=\"showMembersReq(memberCallback)\"><h4>See All Users</h4></a></li>");
			sb.append("<li><a class=\"drpbtn\" onclick=\" collectionReq(collectionCallback,false);\"><h4>Collection</h4></a></li>");
			sb.append("<li id=\"signout\" class=\"drpbtn\"><a onclick=\"LogOutReq(LogOutCallback)\"><h4>Sign Out</h4></a></li>");
			sb.append("</ul></div></div>");
			sb.append("<div class=\"col-md-4 col-sm-2 col-lg-4\">");
			sb.append("<h2>your Personal</h2><h1>Collection</h1></div>");
			sb.append("<div class=\"col-md-3 col-sm-2 col-lg-3 col-md-offset-1 col-lg-offset-1\">");
			sb.append("<!--<input id=\"imagesCollection\" type=\"file\" webkitdirectory mozdirectory directory name=\"myFiles\" class=\"pull-right\" onchange=\"TIV2575.loadImages();TIV2575.showLoadImages('container-fluid');\" multiple/ style=\"background-color:#272727;color:#fff;\">-->");
			
			sb.append("<form  id=\"formData\"   class=\"form-group\" >");
			sb.append("<h4 id=\"imagesCollection\">Title:</h4>");
			sb.append("<input  type=\"text\" name=\"title\" value=\"This is the title\" size=\"30\">");
		
			sb.append("<h4>Portrait Photo</h4> <input id=\"fileButton\" type=\"file\" name=\"photo\"   style=\"background-color:#272727;color:#fff;\">");
			sb.append("<label class=\"custom-file-upload\" for=\"fileButton\"><i class=\"fa fa-cloud-upload\"></i>Upload Your Photo</label>");
			sb.append("<tr>");
			sb.append("<input id=\"uploadDone\" type=\"submit\" onclick=\"UploadImage();\">");
			sb.append("<label class=\"custom-done-upload\" for=\"uploadDone\"></i>Done</label>");
			sb.append("<h4>Num Of Photos: </h4> <input  id=\"PhotosNum\" type=\"number\" value=\"1\" name=\"PhotosNum\" size=\"5\" min=\"1\" max =\"20\">");
			sb.append("<input id=\"PhotosNumRequest\" type=\"submit\" onclick=\"GetImageCollectionByNumber(GetImageCollectionByNumberCallback);\">");
			sb.append("<label class=\"custom-numPhoto-upload\" for=\"PhotosNumRequest\"></i>Done</label>");
			sb.append("</form>");
			sb.append("</div></div></div></div>");
			/*=================tileContainer======================================*/
			sb.append("<div id = \"tileContainer\" class=\"container-fluid\">");
			sb.append("<div id = \"test\" >");
			sb.append("</div>");
			sb.append("</div>");
			sb.append(PageBuilder.imageModal());
			sb.append("<section id='ratingsModalSection'></section>");
			/*=======================================================================================================================*/
		} else {

			sb.append("<div id=\"fluid\" class=\"container-fluid\">");
			if (signed == true) {
				sb.append("<div class=\"row\">");
				sb.append("<div class=\"col-md-4 col-sm-2 col-lg-4\">");
				sb.append("<div class=\"dropdown\">");
				sb.append("<button id=\"menubtn\" class=\"btn dropdown-toggle\" type=\"button\" data-toggle=\"dropdown\"><span class=\"glyphicon glyphicon-align-justify\"></span> </button>");
				sb.append("<ul class=\"dropdown-menu\">");
				sb.append("<li><a class=\"drpbtn\" onclick= \"ShowProfile(ShowProfileCallback)\"><h4>Profile</h4></a></li>");
				sb.append("<li><a class=\"drpbtn\" onclick= \"EditProfileReq(callback)\"><h4>Edit Profile</h4></a></li>");
				sb.append("<li><a class=\"drpbtn\" onclick=\"showMembersReq(memberCallback)\"><h4>See All Users</h4></a></li>");
				sb.append("<li><a class=\"drpbtn\" onclick=\"collectionReq(collectionCallback,false);\"><h4>Collection</h4></a></li>");
				sb.append("<li id=\"signout\" class=\"drpbtn\"><a onclick=\"LogOutReq(LogOutCallback)\"><h4>Sign Out</h4></a></li>");
				sb.append("</ul>");
				sb.append("</div></div>");
				sb.append("<div class=\"col-md-6 col-sm-10 col-lg-6\">");
				sb.append("<h2 id=\"focusare\">"+message+"</h2>");
				sb.append("</div>");
				sb.append("<div class=\"col-md-2 col-sm-2 col-lg-2\">");
				sb.append("<button id=\"regButton\" type=\"button\"  class=\"btn btn-default regbutton\"  onclick=\"DeleteUser(DeleteUserCallback)\">delete Account</button>");
				sb.append("</div></div>");
				sb.append("<hr id = \"line\">");
				
			}
		}
		return sb.toString();
	}
	
	
	
	
	
	public static String  upBodyOtherUser(String message){
		StringBuilder sb = new StringBuilder();
		
		sb.append("<nav class=\"navbar navbar-default navbar-fixed-top navbar-custom\">");
		sb.append("<div class=\"container-fluid\">");
		sb.append("<!-- Brand and toggle get grouped for better mobile display -->");
		sb.append("<div class=\"navbar-header\">");
		sb.append("<button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\" aria-expanded=\"false\">");
		sb.append("<span class=\"sr-only\">Toggle navigation</span>");
		sb.append("<span class=\"icon-bar\"></span><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span>");
		sb.append("</button>");
		sb.append("<a id=\"brand\" class=\"navbar-brand\" href=\"tiv.html\">Tiv</a>");
		sb.append("</div>");
		sb.append("<!-- Collect the nav links, forms, and other content for toggling -->");
		sb.append("<div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">");
		sb.append("<ul id=\"navmarg\" class=\"nav navbar-nav\">");
		sb.append("<!--<li class=\"active\"><a href=\"#\">Link <span class=\"sr-only\">(current)</span></a></li>-->");
		sb.append("<li><a id = \"myHome\" onclick=\" collectionReq(collectionCallback,false);\" >Home</a></li><li><a href=\"#\">About Tiv</a></li>");
		sb.append("</ul>");
		sb.append("<ul class=\"nav navbar-nav navbar-right\">");
		sb.append("<li> <a id=\"mySignIn\"  onclick= LogOutReq(LogOutCallback)>Sign Out</a> </li>");
		sb.append("</ul>");
		sb.append("</div>");
		sb.append("<!-- /.navbar-collapse -->");
		sb.append("</div>");
		sb.append("<!-- /.container-fluid -->");
		sb.append("</nav>");
		sb.append("<div id = \"login\"> </div>");
		/*==================================ForCollectionPage=====================================================================================*/
		
		sb.append("<div class=\"jumbotron\">");
		sb.append("<div  class=\"container-fluid\">");
		sb.append("<div class=\"row\">");
		sb.append("<div class=\"col-md-4 col-sm-2 col-lg-4\">");
		sb.append("<div class=\"dropdown\">");
		sb.append("<button id=\"menubtn\" class=\"btn dropdown-toggle\" type=\"button\" data-toggle=\"dropdown\"><span class=\"glyphicon glyphicon-align-justify\"></span> </button>");
		sb.append("<ul class=\"dropdown-menu\">");
		sb.append("<li><a class=\"drpbtn\" onclick= \"ShowProfile(ShowProfileCallback)\"><h4>Profile</h4></a></li>");
		sb.append("<li><a class=\"drpbtn\" onclick= \"EditProfileReq(callback)\"><h4>Edit Profile</h4></a></li>");
		sb.append("<li><a class=\"drpbtn\" onclick=\"showMembersReq(memberCallback)\"><h4>See All Users</h4></a></li>");
		sb.append("<li><a class=\"drpbtn\" onclick=\" collectionReq(collectionCallback,false);\"><h4>Collection</h4></a></li>");
		sb.append("<li id=\"signout\" class=\"drpbtn\"><a onclick=\"LogOutReq(LogOutCallback)\"><h4>Sign Out</h4></a></li>");
		sb.append("</ul>");
		sb.append("</div></div>");
		sb.append("<div class=\"col-md-4 col-sm-2 col-lg-4 col-md-offset-4 col-lg-offset-4\">");
		sb.append("<h2>"+message+"</h2>");
		sb.append("</div>");
		sb.append("</div>");
		sb.append("</div>");
		sb.append("</div>");
		/*=================tileContainer======================================*/  
		sb.append("<div id = \"tileContainer\" class=\"container-fluid\">");
		sb.append("<div id = \"test\" >");
		sb.append("</div>");
		sb.append("</div>");
		sb.append(PageBuilder.imageModal());
		sb.append("<section id='ratingsModalSection'></section>");
			/*=======================================================================================================================*/
		return sb.toString();
	}
	
	
	/**
	 * 
	 * @return sb.toString
	 */
	public static String  downBody(boolean colletion){
		StringBuilder sb = new StringBuilder();
		if(colletion == false){
			sb.append("</div>");
		}
		sb.append("<hr id = \"line\">");
		sb.append("<footer class=\"footer\">");
		sb.append("<p id=\"ftr\"><small>Copyright ©</small></p>");
		sb.append("<!--small tag use -->");
		sb.append("</footer>");
		sb.append("<script src=\"js/tivComment.js\"></script>");
		sb.append("<script src=\"js/tivRating.js\"></script>");
		sb.append("<script src=\"js/metaData.js.js\"></script>");
		sb.append("<script src=\"js/tiv.js\"></script>");
		sb.append("<script src=\"js/reg.js\"></script>");
		sb.append("<script src=\"https://code.jquery.com/jquery-3.1.1.min.js\"></script>");
		sb.append("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
		
		return sb.toString();
	}
	
	
	/**
	 * @return sb.toString
	 * @throws ClassNotFoundException
	 */
	public static String  printUsers() throws ClassNotFoundException{
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class=\"row\">");
	    sb.append("<div class=\"col-xs-12 col-md-6 col-lg-6 col-md-offset-2 col-lg-offset-2\">");
	    sb.append("<ul class=\"list-group\">");
	            
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        for (int i = 0; i < UserDB.getUsers().size(); i++) {
        	String userName = UserDB.getUsers().get(i).getUserName();
        	String eMail = UserDB.getUsers().get(i).getEmail();
        	String status = RegisterServlet.loggedUsers.get(userName);
        	
        	
        	sb.append("<li  id = \"VisitProfile\" onclick=OtherUserProfile(OtherUserProfileCallback,'"+userName+"')  class=\"list-group-item member\">User:   <strong>"+userName+"</strong>&nbsp;&nbsp;&nbsp;&nbsp;");
			sb.append("&nbsp;&nbsp;&nbsp;&nbsp;eMail:&nbsp;&nbsp;<strong>"+eMail+"</strong>&nbsp;&nbsp;&nbsp;&nbsp;");
			sb.append("&nbsp;&nbsp;&nbsp;&nbsp;status:&nbsp;&nbsp;<strong>"+status+"</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ");
			sb.append("</li>");
	 		System.out.println("userName: " + userName + " eMail: " + eMail);
		}
		sb.append("</ul></div></div>");
	 	return sb.toString();
	
	}


	/**
	 * @param user
	 * @return
	 */
	public static String getRadio(User user)
    {
    	StringBuilder sb = new StringBuilder();

    	switch(user.getGender().toString()){
			
 		case "Female":
 			sb.append("<label class=\"radio-inline\">");
 			sb.append("<input id=\"Male\" type=\"radio\" name=\"gender\" value=\"male\"> Male</label>");
 			sb.append("<label class=\"radio-inline\">");
 			sb.append("<input id=\"Female\" type=\"radio\" name=\"gender\" value=\"female\"checked>Female</label>");
 			sb.append("<label class=\"radio-inline\">");
 			sb.append("<input id=\"Unknown\" type=\"radio\" name=\"gender\" value=\"Unknown\">Unknown</label>");
            break;
        case "Male":
        	sb.append("<label class=\"radio-inline\">");
 			sb.append("<input id=\"Male\" type=\"radio\" name=\"gender\" value=\"male\" checked> Male</label>");
 			sb.append("<label class=\"radio-inline\">");
 			sb.append("<input id=\"Female\" type=\"radio\" name=\"gender\" value=\"female\">Female</label>");
 			sb.append("<label class=\"radio-inline\">");
 			sb.append("<input id=\"Unknown\" type=\"radio\" name=\"gender\" value=\"Unknown\">Unknown</label>");
            break;
        default:
        	sb.append("<label class=\"radio-inline\">");
 			sb.append("<input id=\"Male\" type=\"radio\" name=\"gender\" value=\"male\"> Male</label>");
 			sb.append("<label class=\"radio-inline\">");
 			sb.append("<input id=\"Female\" type=\"radio\" name=\"gender\" value=\"female\">Female</label>");
 			sb.append("<label class=\"radio-inline\">");
 			sb.append("<input id=\"Unknown\" type=\"radio\" name=\"gender\" value=\"Unknown\" checked>Unknown</label>");
            break;
 		}
    	return sb.toString();
    }
	
	
	
	public static String  imageModal(){
		StringBuilder sb = new StringBuilder();
		
		sb.append("<!---The Modal ------------->");
		sb.append("<div id=\"myModal\" class=\"imageModal\">");
		sb.append("<div class=\"row myModalRowOffset\">");
		sb.append("<div class=\"col-lg-1 col-md-1 col-sm-1 col-lg-offset-11 col-md-offset-11 col-sm-offset-11 myModalCloseRowOffset\">");
		sb.append("<span id = \"collectionModalClose\">×</span>");
		sb.append("</div>");
		sb.append("</div>");
		sb.append("<div class=\"row myModalRowsLeftOffset\">");
		sb.append("<div class=\"col-lg-8 col-md-8 col-sm-12\">");
		sb.append("<!--<div id=\"caption\"></div>-->");
		sb.append("<img id=\"img01\">");
		sb.append("</div>");
		sb.append("<div class=\"col-lg-4 col-md-4 col-sm-12\">");
		sb.append("<section id = \"metaData\">");
		sb.append("</section>");
		sb.append("<section id='ratingSection'></section>");
		sb.append("<hr class=\"commentHr\">");
		sb.append("<section id = \"commentsSection\">");  
		sb.append("</section>");
		sb.append("<textarea id = \"CommentArea\" name=\"PostComment\" rows=\"3\" maxlength=\"200\" autocomplete = \"off\"></textarea>");
		sb.append("<button id = \"PostCommentButton\" type=\"button\" class=\"btn btn-default\" onclick=\"PostComment()\">Post Comment");
		sb.append("</button>");
		sb.append("</div>");
		sb.append("</div>");
		sb.append("<div class=\"row myModalRowsLeftOffset\">");
		sb.append("<div class=\"col-lg-8 col-md-8 col-sm-12\">");
		sb.append("<!---Google Map---------------->");
		sb.append("<h2 id = \"mapTitle\">This Image Captured:</h2>");
		sb.append("<div id=\"map\"></div>");
		sb.append("</div>");
		sb.append("<div class=\"col-lg-4 col-md-4 col-sm-12\">");
		sb.append("<!---exif Metadata-------------->");
		sb.append("<section id=\"exif\" ></section>");
		sb.append("</div>");
		sb.append("</div>");
		sb.append("</div>");
		
		return sb.toString();
	}
	
 	
}	
     


	