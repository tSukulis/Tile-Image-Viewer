package servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import db.PhotosDB;
import model.Photo;
import model.User;

/**
 * Servlet implementation class GetImage
 */
@WebServlet("/GetImage")
@MultipartConfig
public class GetImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
    	System.out.println("==========GetImage==========");
    	
    	System.out.println("id: " + request.getParameter("id"));
        System.out.println("metaData: "+request.getParameter("metaData"));
        HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute("logUser");
        if(request.getParameter("metaData").equals("false")){
        	System.out.println("Blob Spot");
        	response.setContentType("image/jpg");
        	byte[] imgData = PhotosDB.getPhotoBlobWithID(Integer.parseInt(request.getParameter("id")));
        	try (OutputStream os = response.getOutputStream()) {
        		os.write(imgData);
                os.flush();
                os.close();
			}
        }
        else{System.out.println("metaData Spot");
        	response.setContentType("application/json");
        	Photo metadata = PhotosDB.getPhotoMetadataWithID(Integer.parseInt(request.getParameter("id")));
        	String metadataTojson = new Gson().toJson(metadata);
        	try (PrintWriter out = response.getWriter()) {
				out.print(metadataTojson);
			}
        }
        	
        	
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
		try {
			processRequest(request,response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
