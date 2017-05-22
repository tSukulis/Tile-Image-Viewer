package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.UserDB;
import model.PageBuilder;
import model.User;
import servlets.ValidateServlet;


/**
 * @author sukulis 
 * A.M: 2575
 *
 */
/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet(urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		/* delete logged user */
		HttpSession tmpsession = request.getSession(true);
    	User user=(User)tmpsession.getAttribute("logUser");
    	RegisterServlet.loggedUsers.put(user.getUserName(),new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(Calendar.getInstance().getTime()));
    	    	
		HttpSession session = request.getSession(false);
		
		if(session != null){
    		session.invalidate();
    	}
		try (PrintWriter out = response.getWriter()) {
			
			out.print(PageBuilder.upBody(false, null,"notCollection"));
			
			out.print("<div id = \"myAlert\" class=\"alert alert-warning alert-dismissable\"><a href=\"#\" id = \"myPopUpModalClose\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">×</a>");
	 		out.print("<h2 id=\"logresp\" >Sign Out SuccessFull</h2> </div>");
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
		processRequest(request,response);
		
	}

}
