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
 *
 */
/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet(urlPatterns = {"/EditProfileServlet"})
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        	response.setContentType("text/html;charset=UTF-8");
        	System.out.println("==========Edit Profile===========");
        	//response.setHeader("refresh", "2");
        	try (PrintWriter out = response.getWriter()) {
        		/* HttpSession to stay loggedIn */
        		HttpSession session = request.getSession(true);
            	User user = (User)session.getAttribute("logUser");
            	
            		out.print(PageBuilder.upBody(true, "Edit Profile","notCollection"));
			 		out.print("<div class=\"form-group row\">");
			 		out.print("<label for=\"userName\" class=\"col-xs-3 col-md-1 col-lg-1 col-md-offset-2 col-lg-offset-2 col-form-label\">userName</label>");
			 		out.print("<div class=\"col-xs-8 col-md-6 col-lg-6\">");
			 		out.print("<input id=\"userName\" class=\"form-control\" type=\"text\" readonly value="+ user.getUserName()+  "></div></div>");
			 		out.print("<div class=\"form-group row\">");
			 		out.print("<label for=\"email\" class=\"col-xs-3 col-md-1 col-lg-1 col-md-offset-2 col-lg-offset-2 col-form-label\">eMail</label>");
			 		out.print("<div class=\"col-xs-8 col-md-6 col-lg-6\">");
			 		out.print("<input id=\"email\" class=\"form-control\" type=\"text\" value="+ user.getEmail()+ "><p id=\"p2\"></p></div></div>");
			 		out.print("<div class=\"form-group row\">");
			 		out.print("<label for=\"password\" class=\"col-xs-3 col-md-1 col-lg-1 col-md-offset-2 col-lg-offset-2 col-form-label\">Password</label>");
			 		out.print("<div class=\"col-xs-8 col-md-6 col-lg-6\">");
			 		out.print("<input id=\"password\" class=\"form-control\" type=\"text\" value="+ user.getPassword()+ "><p id=\"p3\"></div></div>");
			 		out.print("<div class=\"form-group row\">");
			 		out.print("<label for=\"firstName\" class=\"col-xs-3 col-md-1 col-lg-1 col-md-offset-2 col-lg-offset-2 col-form-label\">firstName</label>");
			 		out.print("<div class=\"col-xs-8 col-md-6 col-lg-6\">");
			 		out.print("<input id=\"firstName\" class=\"form-control\" type=\"text\" value="+ user.getFirstName()+ "><p id=\"p5\"></div></div>");
			 		out.print("<div class=\"form-group row\">");
			 		out.print("<label for=\"lastName\" class=\"col-xs-3 col-md-1 col-lg-1 col-md-offset-2 col-lg-offset-2 col-form-label\">lastName</label>");
			 		out.print("<div class=\"col-xs-8 col-md-6 col-lg-6\">");
			 		out.print("<input id=\"lastName\" class=\"form-control\" type=\"text\" value="+ user.getLastName()+ "><p id=\"p6\"></div></div>");
			 		out.print("<div class=\"form-group row\">");
			 		out.print("<label for=\"birthDate\" class=\"col-xs-3 col-md-1 col-lg-1 col-md-offset-2 col-lg-offset-2 col-form-label\">birthDate</label>");
			 		out.print("<div class=\"col-xs-8 col-md-6 col-lg-6\">");
			 		out.print("<input id=\"birthDate\" class=\"form-control\" type=\"text\" value="+ user.getBirthDate()+ "><p id=\"p74\"></div></div>");
			 		
			 		out.print("<div class=\"form-group row\">");
			 		out.print("<label for=\"gender\" class=\"col-xs-3 col-md-1 col-lg-1 col-md-offset-2 col-lg-offset-2 col-form-label\">gender</label>");
			 		out.print("<div class=\"col-xs-8 col-md-6 col-lg-6\">");
			 		out.print(PageBuilder.getRadio(user));
			 		out.print("</div></div>");
			 		out.print("<div class=\"form-group row\">");
			 		out.print("<label for=\"country\" class=\"col-xs-3 col-md-1 col-lg-1 col-md-offset-2 col-lg-offset-2 col-form-label\">country</label>");
			 		out.print("<div class=\"col-xs-8 col-md-6 col-lg-6\">");
			 		out.print("<select class=\"form-control\" id=\"country\" name=\"country\" onclick= \"JsonToSelect()\">");
			 		out.print("<option value="+user.getCountry()+">"+user.getCountry()+"</option>");
			 		out.print("</select></div></div>");
			 		out.print("<div class=\"form-group row\">");
			 		out.print("<label for=\"city\" class=\"col-xs-3 col-md-1 col-lg-1 col-md-offset-2 col-lg-offset-2 col-form-label\">city</label>");
			 		out.print("<div class=\"col-xs-8 col-md-6 col-lg-6\">");
			 		out.print("<input id=\"city\" class=\"form-control\" type=\"text\" value="+ user.getTown()+"><p id=\"p8\"></div></div>");
			 		out.print("<div class=\"form-group row\">");
			 		out.print("<label for=\"info\" class=\"col-xs-3 col-md-1 col-lg-1 col-md-offset-2 col-lg-offset-2 col-form-label\">info</label>");
			 		out.print("<div class=\"col-xs-8 col-md-6 col-lg-6\">");
			 		out.print("<textarea id=\"info\" name=\"info\" rows=\"6\" maxlength=\"500\" value="+ user.getInfo()+">"+ user.getInfo()+"</textarea ><p id=\"p9\"></div></div>");
			 		out.print("<div class=\"col-xs-12 col-md-6 col-lg-6 col-md-offset-3 col-lg-offset-3\">");
			 		out.print("<button id = \"updatebtn\" type=\"button\" class=\"btn btn-default regbutton\" onclick=\"upDateHandler(upDatecallback)\">");
			 		out.print("UpDate</button></div>");   
			 		out.print(PageBuilder.downBody(false));
			}   	        	
    }
    
	/**
	 * @see Servlet#getServletInfo()
	 */
    
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null; 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
