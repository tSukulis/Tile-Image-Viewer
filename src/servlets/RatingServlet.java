package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import db.CommentDB;
import db.RatingDB;
import model.Comment;
import model.PageBuilder;
import model.Rating;
import model.User;

/**
 * Servlet implementation class Rating
 */
@WebServlet("/RatingServlet")
@MultipartConfig
public class RatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int ID;

	/**
	 * @see Servlet#init(ServletConfig)
	 * initializes UserStructure
	 */
    @Override
	public void init() throws ServletException {
    	ID = 1;
	}
    
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		System.out.println("==========Rating==========");
		if(!request.getParameter("rate").equals(""))
		{
	        // gets values of text fields
			ID++;
	        String userName = request.getParameter("userName");
	        int ID = RatingServlet.ID;
	        String photoID = request.getParameter("photoID");
	        String timestamp = request.getParameter("timestamp");
	        String rate = request.getParameter("rate");
	        
	        System.out.println("userName: "+userName);
	        System.out.println("ID: "+ID);
	        System.out.println("photoID: "+photoID);
	        System.out.println("date: "+timestamp);
	        System.out.println("rate: "+rate);
	        
	       
	    	HttpSession session = request.getSession(true);
			User user = (User)session.getAttribute("logUser");
			System.out.println(user.getUserName());
			
			
			Rating rating = new Rating();		
			rating.setID(ID);
	        rating.setUserName(request.getParameter("userName"));
	        rating.setPhotoID(Integer.parseInt(request.getParameter("photoID")));
	        rating.setTimestamp(request.getParameter("date"));
	        rating.setRate(Integer.parseInt(request.getParameter("rate")));
	        try {
				RatingDB.addRating(rating);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        try (PrintWriter out = response.getWriter()) {
	        	List<Rating> ratings = null;
				try {
					ratings = RatingDB.getRatings(Integer.parseInt(request.getParameter("photoID")));
				} catch (NumberFormatException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String json = new Gson().toJson(ratings);
				out.print(json);
			}
		}
		else{
			System.out.println("No Rate Input");
		}
		
	}

}
