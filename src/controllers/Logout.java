package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Cookie loginCookie = null;
    	Cookie[] cookies = request.getCookies();
    	if (cookies != null) {
	    	for(Cookie cookie : cookies) {
	    		if(cookie.getName().equals("userID")) {
	    			loginCookie = cookie;
	    			break;
	    		}
	    	}
    	}
    	
    	if (loginCookie != null) {
    		loginCookie.setMaxAge(0);
        	response.addCookie(loginCookie);
    	}

		try {
			response.sendRedirect(request.getContextPath() + "/Index");
		}
		
		catch(Exception error) {
			error.printStackTrace();
		}
	}

}
