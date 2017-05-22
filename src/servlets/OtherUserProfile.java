package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PageBuilder;
import model.User;

/**
 * Servlet implementation class OtherUserProfile
 */
@WebServlet("/OtherUserProfile")
@MultipartConfig
public class OtherUserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OtherUserProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("==========OtherUserProfile==========");
		String message = request.getParameter("visitName")+ "'s Profile";
		HttpSession session = request.getSession(true);
    	User user = (User)session.getAttribute("logUser");
		if(request.getParameter("visitName").equals(user.getUserName()))
			message = "Your Profile "+request.getParameter("visitName");
		if(!request.getParameter("visitName").equals("") && request.getParameter("visitName") != null){
			try (PrintWriter out = response.getWriter()) {
	        	out.print(PageBuilder.upBodyOtherUser(message));
             	out.print(PageBuilder.downBody(true));
	        	System.out.println("OtherUserProfile: Success____userName: "+request.getParameter("visitName"));
	        }
		}
		else
			System.out.println("No visitName input");
	}

}
