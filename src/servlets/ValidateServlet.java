package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.UserDB;
import model.Encryption;
import model.PageBuilder;
import model.User;



/**
 * @author sukulis 
 * A.M: 2575
 *
 */
/**
 * Servlet implementation class ValidateServlet
 */
@WebServlet(urlPatterns = {"/ValidateServlet"})
public class ValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
       
    
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		if(RegisterServlet.loggedUsers == null)
			RegisterServlet.loggedUsers = new HashMap<String,String>();
	}
	
	
    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
    	
    	System.out.println("loginName: "+request.getParameter("loginName"));
    	System.out.println("pass: "+request.getParameter("loginPsw"));
    	
    	
    	/*=========================================================================*/
		/**=========================================================================
		 *
		 * password Encryption
		 */						
		String securePassword = null;
		try {
			securePassword = Encryption.MD5_Encryption(request.getParameter("loginPsw"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(securePassword);

        /*=========================================================================*/
		/*=========================================================================*/
    	
    	
        	response.setContentType("text/html;charset=UTF-8");
        	if(request.getParameter("loginName")!= null
        			&& request.getParameter("loginPsw") != null){
        		
        		if (UserDB.checkValidUserName(request.getParameter("loginName")) == false){
        			if(UserDB.getUser(request.getParameter("loginName")).getPassword().equals(securePassword)){
						
						System.out.println(request.getParameter("loginName"));
						HttpSession session=request.getSession(true); 
				        session.setAttribute("logUser",UserDB.getUser(request.getParameter("loginName")));  
				        RegisterServlet.loggedUsers.put(request.getParameter("loginName"),"active");
				        
				        
				        
				        try (PrintWriter out = response.getWriter()) {
				        	out.print(PageBuilder.upBody(true, null, "collection"));
			             	out.print(PageBuilder.downBody(true));
				        	System.out.println("Validate: Success____Login: Success");
				        }
					}
					
					else{
						try (PrintWriter out = response.getWriter()) {
				        	
							out.print("<div class=\"alert alert-warning alert-dismissable\"><a id = \"myPopUpModalClose\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">×</a>");
           			 		out.print("<h2 id=\"logresp\">Sorry.. Wrong password</h2> </div>");
           			 		System.out.println("Sorry.. Wrong password");
						}
					}
        		
				}else {
					try (PrintWriter out = response.getWriter()) {
						out.print("<div  class=\"alert alert-warning alert-dismissable\"><a  id = \"myPopUpModalClose\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">×</a>");
       			 		out.print("<h2 id=\"logresp\">Sorry.. Wrong userName..</h2> </div>");
       			 		System.out.println("Sorry.. Wrong userName");
					}
				}
        	}else{
        		try (PrintWriter out = response.getWriter()) {
					out.print("<div class=\"alert alert-warning alert-dismissable\"><a  id = \"myPopUpModalClose\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">×</a>");
   			 		out.print("<h2 id=\"logresp\">Ther Are Empty Fields</h2> </div>");
   			 		System.out.println("Ther Are Empty Fields");
        	}  	   
        
        }
        	
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		System.out.println("=====get ValidateLogin=====");
		try {
			processRequest(request,response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=======ValidateLogin======");
		try {
			processRequest(request,response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
