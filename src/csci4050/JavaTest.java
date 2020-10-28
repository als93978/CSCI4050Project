package csci4050;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JavaTest
 */
@WebServlet("/JavaTest")
public class JavaTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JavaTest() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String dbURL = "jdbc:mysql://localhost:3306/BookBayDB?serverTimezone=UTC";
		
		String useDBQuery = "USE BookBayDB;";
		
		String getAllUserDataQuery = "SELECT * FROM User;";
		
		String username = "root";
		String password = "ajgopattymn7890";
		
		try {
			Connection connection = DriverManager.getConnection(dbURL, username, password);
			
			PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
			useDBStmt.executeQuery();
			
			PreparedStatement getAllUserDataPstmt = connection.prepareStatement(getAllUserDataQuery);
			ResultSet allUserDataRS = getAllUserDataPstmt.executeQuery();
			
			ArrayList<String> allUserData = new ArrayList<String>();
			
			while(allUserDataRS.next()) {
				allUserData.add(allUserDataRS.getString(2));
				allUserData.add(allUserDataRS.getString(3));
				
				System.out.println(allUserDataRS.getString(2) + " " + allUserDataRS.getString(3));
			}
			
			request.setAttribute("allUserData", allUserData);
			
			connection.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Test2.jsp");
		dispatcher.forward(request, response);
	}

}
