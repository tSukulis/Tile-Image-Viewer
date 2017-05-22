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

import com.google.gson.Gson;

import db.RatingDB;
import model.Rating;

/**
 * Servlet implementation class GetRatings
 */
@WebServlet("/GetRatings")
@MultipartConfig
public class GetRatings extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		System.out.println("==========GetRatings==========");
		System.out.println("photoID: " + request.getParameter("photoID"));
		if (request.getParameter("photoID") != null){
			try (PrintWriter out = response.getWriter()) {
				List<Rating> ratings = RatingDB.getRatings(Integer.parseInt(request.getParameter("photoID")));
				String ratingsJson = new Gson().toJson(ratings);
				System.out.println(ratingsJson);
				out.print(ratingsJson);
			} catch (NumberFormatException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
	}

}
