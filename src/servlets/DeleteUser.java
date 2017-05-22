package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.PhotosDB;
import db.UserDB;
import model.PageBuilder;
import model.User;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException, ClassNotFoundException {
	    	
	    	System.out.println("userName: "+request.getParameter("userName"));
	    	
	    	HttpSession session = request.getSession(true);
			User user = (User)session.getAttribute("logUser");
			System.out.println(user.getUserName());
			if(user != null){
				/*======delete photos tha user has upload==========*/
				List<Integer> photos = new ArrayList<>();
				photos = PhotosDB.getPhotoIDs(user.getPhotosNum(), user.getUserName());
				for (int i = 0; i < photos.size(); i++) {
					PhotosDB.deletePhoto(photos.get(i));
				}
				/*=================================================*/
				UserDB.deleteUser(user);
				
				session.invalidate();
				
				try (PrintWriter out = response.getWriter()) {
					out.print(PageBuilder.upBody(false, null,"notCollection"));
					out.print("<div  class=\"container-fluid\">");
					out.print("<div class=\"row\">");
					out.print("<div class=\"col-md-4 col-sm-2 col-lg-4 col-md-offset-2 col-lg-offset-2\">");
					out.print("<h2>User Deleted</h2></div></div></div>");
					out.print("<div id=\"id01\" class=\"modal\">");
		  			out.print("<div id=\"logmsg\"> </div>");
		  			out.print("<form id=\"mcanm\" class=\"modal-content animate\" >");
		  			out.print("<div id=\"mdcn\"class=\"container\">");
		  			out.print("<label><b>Username</b></label>");
		  			out.print("<input id=\"loginName\" type=\"text\" placeholder=\"Enter Username\" name=\"uname\" required>");
		  			out.print("<label><b>Password</b></label>");
		  			out.print("<input id=\"loginPsw\"  type=\"password\" placeholder=\"Enter Password\" name=\"psw\" required>");
		  			out.print("<button class=\"loginButton\" type=\"submit\" onclick=\"ValidateLogin(ValidateCallback)\">Login</button>");
		  			out.print("<input type=\"checkbox\" checked=\"checked\"> Remember me</div>");
		  			out.print("<div id=\"mdcnt\" class=\"container\" style=\"background-color:#f1f1f1\">");
		  			out.print("<button class=\"loginButton cancelbtn\" type=\"button\" onclick=\"document.getElementById('id01').style.display='none'\" >Cancel</button>");
		  			out.print("<span id=\"myLog\">Not a member? <a href=\"register.html\">Sign Up</a></span>");
		  			out.print("</div>");
		  			out.print("</form>");
		  			out.print("</div>"); 
					out.println(PageBuilder.downBody(false));
	         	}
			} 
	    	
	 }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request,response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
