package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
import model.Comment;
import model.User;

/**
 * Servlet implementation class MakeComment
 */
@WebServlet("/MakeComment")
@MultipartConfig
public class MakeComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		
	}
	
   

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		System.out.println("==========MakeComment==========");
		
		System.out.println("userName: "+request.getParameter("userName"));
        System.out.println("photoID: "+request.getParameter("photoID"));
        System.out.println("timestamp: "+request.getParameter("timestamp"));
        System.out.println("comment: "+request.getParameter("comment"));
        
		
		HttpSession session = request.getSession(true);
    	User user = (User)session.getAttribute("logUser");
    	if(request.getParameter("comment") != null && !request.getParameter("comment").equals("")
    			&& request.getParameter("photoID") != null &&  Integer.parseInt(request.getParameter("photoID"))> 0) 
		{
    		Comment comment = new Comment();
    		comment.setUserName(request.getParameter("userName"));
    		comment.setPhotoID(Integer.parseInt(request.getParameter("photoID")));
    		comment.setComment(request.getParameter("comment"));
    		comment.setTimestamp(request.getParameter("timestamp"));
    		
    		try {
				comment.checkFields();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		try {
				CommentDB.addComment(comment);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		/* get Comments as Json*/
    		try (PrintWriter out = response.getWriter()) {
	        	List<Comment> comments = null;
				try {
					comments = CommentDB.getComments(Integer.parseInt(request.getParameter("photoID")));
				} catch (NumberFormatException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String json = new Gson().toJson(comments);
				System.out.println(json);
				out.print(json);
			}
		}

	}
}
