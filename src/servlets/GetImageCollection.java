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
 * Servlet implementation class GetImageCollection
 */
@WebServlet(urlPatterns = {"/GetImageCollection"})
@MultipartConfig
public class GetImageCollection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    
    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("application/json");
    	System.out.println("==========GetImageCollection==========");
    	System.out.println("userName: " + request.getParameter("userName"));
        System.out.println("number: "+request.getParameter("number"));
        	HttpSession session = request.getSession(true);
        	User user = (User)session.getAttribute("logUser");
        	
        	int i = Integer.parseInt(request.getParameter("number"));
        	System.out.println("Integer.parseInt: "+ i);
        	if (request.getParameter("userName") != null && !request.getParameter("userName").equals("") && !request.getParameter("userName").equals("undefined")){
            	if (request.getParameter("number") != null){
            		
            		try (PrintWriter out = response.getWriter()) {
						out.print(PhotosDB.getPhotoIDs(Integer.parseInt(request.getParameter("number")), request.getParameter("userName")));
					} catch (NumberFormatException e) {
						System.out.println("NumberFormatException");
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						System.out.println("ClassNotFoundException");
						e.printStackTrace();
					}	
            		
            	}
            	else{
                		try (PrintWriter out = response.getWriter()) {
    						out.print(PhotosDB.getPhotoIDs(10, request.getParameter("userName")));
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
            	try (PrintWriter out = response.getWriter()) {
					out.print(PhotosDB.getPhotoIDs(10));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
