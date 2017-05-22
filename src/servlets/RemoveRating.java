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

import db.RatingDB;
import model.Rating;
import model.User;

/**
 * Servlet implementation class RemoveRating
 */
@WebServlet("/RemoveRating")
@MultipartConfig
public class RemoveRating extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		System.out.println("==========RemoveRating==========");
		if(!request.getParameter("rate").equals(""))
		{
	        // gets values of text fields
			
	        String userName = request.getParameter("userName");
	        int ID = Integer.parseInt(request.getParameter("ID"));
	        String photoID = request.getParameter("photoID");
	        String timestamp = request.getParameter("timestamp");
	        String rate = request.getParameter("rate");
	        
	        System.out.println("userName: "+userName);
	        System.out.println("ID: "+ID);
	        System.out.println("photoID: "+photoID);
	        System.out.println("date: "+timestamp);
	        System.out.println("rate: "+rate);

	        try {
				RatingDB.deleteRating(ID);
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