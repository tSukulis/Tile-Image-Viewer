package servlets;

import db.UserDB;
import model.Encryption;
import model.PageBuilder;
import model.User;


/*======================================*/
/**
 * OWASP Java HTML Sanitizer
 * protection against XSS.
 */
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
/*======================================*/

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
/**
 * @author sukulis 
 * A.M: 2575
 *
 */
/**
 * Servlet implementation class UserServlet
 */
@WebServlet(urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
	
	public static  HashMap<String,String> loggedUsers;
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 * initializes UserStructure
	 */
    @Override
	public void init() throws ServletException {
    	//regUsers = new ComposerData();
    	loggedUsers = new HashMap<String,String>();
	}

	/**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        	response.setContentType("text/html;charset=UTF-8");
        	
            if (request.getParameter("userName") != null
                    && request.getParameter("email") != null
                    && request.getParameter("password") != null
	            	&& request.getParameter("firstName") != null
	                && request.getParameter("lastName") != null
					&& request.getParameter("birthDate") != null
					&& request.getParameter("country") != null
					&& request.getParameter("city") != null){
            	
            		System.out.println(request.getParameter("userName"));
            	try {
					if(UserDB.checkValidUserName(request.getParameter("userName")) == false){
						
						try (PrintWriter out = response.getWriter()) {
							out.print("<div class=\"alert alert-warning alert-dismissable\"><a  id = \"myPopUpModalClose\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">×</a>");
						 	out.print("<h2 id=\"fail\">Sorry.. User Already Registered</h2> </div>");
						}	
					}else{
						
						loggedUsers.put(request.getParameter("userName"),"Never");
						/*=========================================================================*/
						/**=========================================================================
						 *
						 * OWASP Java HTML Sanitizer
						 * protection against XSS.
						 */
						PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
						String safeUserName = policy.sanitize(request.getParameter("userName"));
						String safeEmail = policy.sanitize(request.getParameter("email"));
						String safePassword = policy.sanitize(request.getParameter("password"));
						String safeFirstName = policy.sanitize(request.getParameter("firstName"));
						String safeLastName = policy.sanitize(request.getParameter("lastName"));
						String safeBirthDate = policy.sanitize(request.getParameter("birthDate"));
						String safeGender = policy.sanitize(request.getParameter("gender"));
						String safeCountry = policy.sanitize(request.getParameter("country"));
						String safeCity = policy.sanitize(request.getParameter("city"));
						String safeInfo = policy.sanitize(request.getParameter("info"));
						System.out.println("gender: "+safeGender);
						/*=========================================================================*/
						/*=========================================================================*/
						
						
						
						/*=========================================================================*/
						/**=========================================================================
						 *
						 * password Encryption
						 */						
						String securePassword = null;
						try {
							securePassword = Encryption.MD5_Encryption(safePassword);
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        System.out.println(securePassword);

				        /*=========================================================================*/
						/*=========================================================================*/
						
						User user = new User(safeUserName ,
								             safeEmail,
								             securePassword,
								             safeFirstName,
								             safeLastName,
								             safeBirthDate,
								             safeCountry,
								             safeCity,
											 safeInfo,
											 safeGender
						);
						synchronized (this) {
							UserDB.addUser(user);
					    }
						
						
						
						 try (PrintWriter out = response.getWriter()) {
							 	
							 out.println(PageBuilder.upBody(false ,null,"notCollection"));
							 out.println(PageBuilder.imageModal());
							 out.print("<div class=\"row\"><div class=\"col-md-6 col-sm-12 col-lg-6 col-md-offset-2 col-lg-offset-2\"><h2 id='rgs'>Register Succeeded</h2></div></div><hr id='line'>");
							 out.println(user.printUser());
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
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   	   
            
            }else{
            	try (PrintWriter out = response.getWriter()) {
            		out.print("<h2 id=\"iman\">Sorry userName,email,password,firstName,<br>lastName,birthDate,city!\nare required fields</p>");    
            	}	
            }     
    }
    

	/**
	 * @see Servlet#getServletInfo()
	 */
    @Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null; 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet__!!");
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost__!!");
		processRequest(request, response);
	}

}
