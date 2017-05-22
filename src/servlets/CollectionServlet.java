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


/**
 * @author sukulis 
 * A.M: 2575
 */
/**
 * Servlet implementation class CollectionServlet
 */

@WebServlet(urlPatterns = {"/CollectionServlet"})
public class CollectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	 
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("logUser");
		System.out.println(user.getUserName());
		if(user != null){
		
			try (PrintWriter out = response.getWriter()) {
         		out.print(PageBuilder.upBody(true, null, "collection"));
             	out.print(PageBuilder.downBody(true));
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
		processRequest(request,response);
	}

}
