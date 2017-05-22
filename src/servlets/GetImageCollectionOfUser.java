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

import db.PhotosDB;
import model.User;

/**
 * Servlet implementation class GetImageCollectionOfOtherUser
 */
@WebServlet("/GetImageCollectionOfUser")
@MultipartConfig
public class GetImageCollectionOfUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
    	System.out.println("==========GetImageCollectionOfOtherUser==========");
    	System.out.println("visitName: " + request.getParameter("visitName"));
        System.out.println("number: "+request.getParameter("number"));
        	HttpSession session = request.getSession(true);
        	User user = (User)session.getAttribute("logUser");
        	
        	
        	if (request.getParameter("visitName") != null){
            	if (request.getParameter("number") != null){
            		try (PrintWriter out = response.getWriter()) {
						out.print(PhotosDB.getPhotoIDs(Integer.parseInt(request.getParameter("number")), request.getParameter("visitName")));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
            	}
            	else{
                		try (PrintWriter out = response.getWriter()) {
    						out.print(PhotosDB.getPhotoIDs(10, user.getUserName()));
    					} catch (NumberFormatException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					} catch (ClassNotFoundException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}	
            	}
            }
        	else{
        		System.out.println("No VisitName");
        	}
	}

}
