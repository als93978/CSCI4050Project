package controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dataAccess.BookDA;
import models.Book;
import models.ErrorMessage;
import models.Message;

/**
 * Servlet implementation class AddNewBook
 */
@WebServlet("/AddNewBook")
@MultipartConfig
public class AddNewBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookDA bookDA = new BookDA();
	
	private Book book = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewBook() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processUpload(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			returnError(request, response, e.getMessage());
		}
	}
	
	private void processUpload(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		book = new Book();
		
		Part uploadedImgPart = request.getPart("image");
		String uploadedImgFileName = uploadedImgPart.getSubmittedFileName();
		InputStream uploadedImgInputStream = uploadedImgPart.getInputStream();
		File imgFolderPath = new File(System.getProperty("catalina.base") + "/imgStorage/");
		
		imgFolderPath.mkdir();
		//System.out.println(System.getProperty("catalina.base"));
		
		File uploadedImgSaveFile = new File(imgFolderPath, uploadedImgFileName);
		
		if(!uploadedImgSaveFile.exists()) {
			try(uploadedImgInputStream) {
				Files.copy(uploadedImgInputStream, uploadedImgSaveFile.toPath());
			}
		}
		
		String title = request.getParameter("title");
		
		String author = request.getParameter("author");
		
		float price;
		if(request.getParameter("price").equals("") || request.getParameter("price") == null)
			price = 0.0f;
		else
			price = Float.parseFloat(request.getParameter("price"));
		
		String genre = request.getParameter("genre");
		
		String description = request.getParameter("description");
		
		String imagePath = "imgStorage/" + uploadedImgFileName;
		
		book.setTitle(title);
		book.setAuthor(author);
		book.setSellingPrice(price);
		book.setGenre(genre);
		book.setDescription(description);
		book.setImagePath(imagePath);
		
		bookDA.createBook(book);
		
		String bookCreatedMsg = "Book successfully created.";
		returnMessage(request, response, bookCreatedMsg);
	}
	
	private void returnMessage(HttpServletRequest request, HttpServletResponse response, String messageStr) {
		Message message = new Message();
		
		message.setMessage(messageStr);
		
		request.setAttribute("message", message);
		
		redirectToPage(request, response, "ManageBooks");
	}
	
	private void returnError(HttpServletRequest request, HttpServletResponse response, String message) {
		ErrorMessage errorMessage = new ErrorMessage();
		
		errorMessage.setMessage("An error occurred: " + message);
		
		request.setAttribute("errorMessage", errorMessage);
		
		redirectToPage(request, response, "ManageBooks");
	}
	
	private void redirectToPage(HttpServletRequest request, HttpServletResponse response, String page) {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + page);
		
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
