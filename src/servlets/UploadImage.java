package servlets;

import db.PhotosDB;
import db.UserDB;
import model.PageBuilder;
import model.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author papadako
 */
@WebServlet(urlPatterns = {"/UploadImage"})
@MultipartConfig(maxFileSize = 1011074)    // upload file's size up to 1MB
public class UploadImage extends HttpServlet {

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("==========UploadImage==========");
        // gets values of text fields
        String userName = request.getParameter("userName");
        String contentType = request.getParameter("contentType");
        System.out.println("userName: "+userName);
        System.out.println("contentType: "+contentType);
        
        /*===========increase number of upLoaded photos=========*/
    	HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("logUser");
		System.out.println(user.getUserName());
		
		user.setPhotosNum();
		/*======================================================*/
        InputStream inputStream = null; // input stream of the upload file

        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        
	        try {
	            // uploadPhoto returns the id of the photo
	            PhotosDB.uploadPhoto(inputStream, userName, contentType);
	            
	            try (PrintWriter out = response.getWriter()) {				 
	             		out.print(PageBuilder.upBody(true, null, "collection"));
	                 	out.print(PageBuilder.downBody(true));
	            }
	        } catch (Exception ex) {
	            Logger.getLogger(UploadImage.class.getName()).log(Level.SEVERE, null, ex);
	        }
        }
        else{
        	System.out.println("edw eisai re");
            try (PrintWriter out = response.getWriter()) {				 
             		out.print(PageBuilder.upBody(true, null, "collection"));
                 	out.print(PageBuilder.downBody(true));
            }
        }
    }
}
