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

import db.CommentDB;
import model.Comment;

/**
 * Servlet implementation class DeleteComment
 */
@WebServlet("/DeleteComment")
@MultipartConfig
public class DeleteComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		System.out.println("==========DeleteComment==========");
		
		
	        if(request.getParameter("photoID") != null)
	        {
				
		        //String userName = request.getParameter("userName");
		        int ID = Integer.parseInt(request.getParameter("ID"));
		        String photoID = request.getParameter("photoID");
		        //String timestamp = request.getParameter("timestamp");
		        //String comment = request.getParameter("comment");
		        
		        //System.out.println("userName: "+userName);
		        System.out.println("ID: "+ID);
		        System.out.println("photoID: "+photoID);
		        //System.out.println("date: "+timestamp);
		        //System.out.println("comment: "+comment);
	
		        try {
					CommentDB.deleteComment(ID);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        try (PrintWriter out = response.getWriter()) {
		        	List<Comment> comments = null;
					try {
						comments = CommentDB.getComments(Integer.parseInt(request.getParameter("photoID")));
					} catch (NumberFormatException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String json = new Gson().toJson(comments);
					out.print(json);
				}
	        }
		else{
			System.out.println("No photoID for that Comment Found");
		}
		
	}

}