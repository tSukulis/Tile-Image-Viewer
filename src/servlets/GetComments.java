package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import db.CommentDB;
import db.PhotosDB;
import model.Comment;

/**
 * Servlet implementation class GetComments
 */
@WebServlet("/GetComments")
@MultipartConfig
public class GetComments extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		System.out.println("==========GetComments==========");
		System.out.println("photoID: " + request.getParameter("photoID"));
		if (request.getParameter("photoID") != null){
			try (PrintWriter out = response.getWriter()) {
				List<Comment> comments = CommentDB.getComments(Integer.parseInt(request.getParameter("photoID")));
				String json = new Gson().toJson(comments);
				out.print(json);
			} catch (NumberFormatException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
	}
		

}
