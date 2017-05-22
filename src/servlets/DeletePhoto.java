package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.CommentDB;
import db.PhotosDB;
import db.RatingDB;
import model.Comment;
import model.Rating;
import model.User;

/**
 * Servlet implementation class DeletePhoto
 */
@WebServlet("/DeletePhoto")
@MultipartConfig
public class DeletePhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		System.out.println("==========DeletePhoto==========");
		
		if (request.getParameter("id") != null){
			System.out.println("id: "+request.getParameter("id"));
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				
				/* delete photo Ratings */
				List<Rating> ratings = RatingDB.getRatings(id);
				for(int i = 0; i < ratings.size(); i++){
					RatingDB.deleteRating(ratings.get(i));
				}
				
				/* delete photo Comments */
				List<Comment> comments = CommentDB.getComments(id);
				for(int i = 0; i < comments.size(); i++){
					CommentDB.deleteComment(comments.get(i));
				}
				
				/* delete Photo */
				PhotosDB.deletePhoto(id);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
        
        
        
	}

}
