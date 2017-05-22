package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PageBuilder;
import model.User;
import db.UserDB;

/**
 * @author sukulis 
 * A.M: 2575
 *
 */
/**
 * Servlet implementation class ShowMembersServlet
 */
@WebServlet(urlPatterns = {"/ShowMembersServlet"})
public class ShowMembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 
		 HttpSession session = request.getSession(true);
		 User user = (User)session.getAttribute("logUser");
		 System.out.println("Registered Users:");
		 if(user != null){
			 try {
				if(UserDB.getUsers() != null){

				     	try (PrintWriter out = response.getWriter()) {
				     		out.print(PageBuilder.upBody(true, "Registered Users","notCollection"));
				         	out.println(PageBuilder.printUsers());
				         	out.print(PageBuilder.downBody(false));
				     	}
				}
				 else{
					 	try (PrintWriter out = response.getWriter()) {
							out.print("<div class=\"alert alert-warning alert-dismissable\"><a  class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">×</a>");
						 	out.print("<h2 id=\"logresp\">dataBase is Empty</h2> </div>");
						}
					 }
			} catch (ClassNotFoundException e) {
				System.out.println("Log In First");
				try (PrintWriter out = response.getWriter()) {
					out.print("<div class=\"alert alert-warning alert-dismissable\"><a  class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">×</a>");
				 	out.print("<h2 id=\"logresp\">Log In First</h2> </div>");
				}
				e.printStackTrace();
			}
		 }
		 
	 }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
