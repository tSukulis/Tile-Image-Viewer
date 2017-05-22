package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/* for Validate username*/
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * Servlet implementation class UpdateDataServlet
 */
@WebServlet(urlPatterns = {"/UpdateDataServlet"})
public class UpdateDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Pattern pattern;
	private Matcher matcher;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDataServlet() {
        super();
        // TODO Auto-generated constructor stub
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
        	response.setContentType("text/html;charset=UTF-8");
        	HttpSession session = request.getSession(true);
        	User user=(User)session.getAttribute("logUser");
        	System.out.println(user.getUserName());
        	
        	System.out.println("gender: "+request.getParameter("gender"));
        	
        	if(user != null){
	        	if (request.getParameter("userName") != null
				         && request.getParameter("email") != null
				         && request.getParameter("password") != null
				    	&& request.getParameter("firstName") != null
				        && request.getParameter("lastName") != null
						&& request.getParameter("birthDate") != null
						&& request.getParameter("country") != null
						&& request.getParameter("city") != null){
	        		
	        		
	        		
	        		/*=========================================================================*/
					/**=========================================================================
					 *
					 * password Encryption
					 */						
					String securePassword = null;
					try {
						securePassword = Encryption.MD5_Encryption(request.getParameter("password"));
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        System.out.println(securePassword);

			        /*=========================================================================*/
					/*=========================================================================*/
	        		
	        		 if(isValidEmailAddress(request.getParameter("email")) == true )
	        		 {
		        		 user.setEmail(request.getParameter("email"));
		        		 user.setPassword(securePassword);
		        		 user.setFirstName(request.getParameter("firstName"));
		        		 user.setLastName(request.getParameter("lastName"));
		        		 user.setBirthDate(request.getParameter("birthDate"));
		        		 user.setGender(request.getParameter("gender"));
		        		 user.setCountry(request.getParameter("country"));
		        		 user.setTown(request.getParameter("city"));
		        		 user.setInfo(request.getParameter("info"));
		        		 
		        		 UserDB.updateUser(user);
		        		 
	
						 try (PrintWriter out = response.getWriter()) {
							 out.print(PageBuilder.upBody(true, "Profile Updated","notCollection"));
							 out.println(user.printUser()); 
							 out.print(PageBuilder.downBody(false));
							 System.out.println("Profile Updated");
						 }
	        		 }
	        		 else{
	        			 try (PrintWriter out = response.getWriter()) {
							 out.print("<div class=\"alert alert-warning alert-dismissable\"><a href=\"#\" id = \"myPopUpModalClose\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">×</a>");
						 		out.print("<h2 id=\"logresp\">Please Insert a Valid eMail</h2> </div>");
						 }
	        		 }
				 }
				 else {
					 try (PrintWriter out = response.getWriter()) {
						 out.print("<div class=\"alert alert-warning alert-dismissable\"><a href=\"#\" id = \"myPopUpModalClose\"class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">×</a>");
					 		out.print("<h2 id=\"logresp\">There Are Empty Fields</h2> </div>");
					 }
				}
	        	
        	}
        	
        	 
        	
    }
    
    /**
     * @param email
     * @return
     */
    public boolean isValidEmailAddress(String email) {
    	   boolean result = true;
    	   try {
    	      InternetAddress emailAddr = new InternetAddress(email);
    	      emailAddr.validate();
    	   } catch (AddressException ex) {
    		   ex.printStackTrace();
    	      result = false;
    	   }
    	   return result;
    }
    
    
    /**
     * @param username
     * @return
     */
    public boolean validate(String username){
		  matcher = pattern.matcher(username);
		  return matcher.matches();
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
		// TODO Auto-generated method stub
		System.out.println("==========Update================");
		try {
			processRequest(request,response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
