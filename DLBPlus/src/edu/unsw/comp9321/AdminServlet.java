package edu.unsw.comp9321;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBHelper db = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        
        // Instantiate database connection
		this.db = new DBHelper();
		boolean initSuccess = this.db.init();
		if (!initSuccess) {
			System.out.println("LOL there was an error.");
		} else {
			System.out.println("Successfully connected to DB for admin");
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Redirect to login page if no admin is currently logged in; index page otherwise
		String redir = "";
		Admin currAdmin = (Admin) request.getSession().getAttribute("currAdmin");
		if (currAdmin == null) {
			//redir = "adminLogin.html";
			RequestDispatcher rd = request.getRequestDispatcher("adminLogin.jsp");
			rd.forward(request, response);
		} else {
			
			String Action = request.getParameter("Action");
			if(Action == null) {
				RequestDispatcher rd = request.getRequestDispatcher("adminIndex.jsp");
				rd.forward(request, response);
			} else if (Action == "Users") {
				
				
				List<User> ListOfUsers = this.db.GetAllUsers();
				request.setAttribute("ListOfUsers", ListOfUsers);
				
				
				RequestDispatcher rd = request.getRequestDispatcher("adminUsers.jsp");
				rd.forward(request, response);
			} else if (Action == "Listings") {
	
				
				
				RequestDispatcher rd = request.getRequestDispatcher("adminUsers.jsp");
				rd.forward(request, response);
			}
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Determine redirection page as appropriate to user action
		String req = request.getParameter("action");
		String nextPage = "adminIndex.jsp";		// default
		if (req.equals("viewAllUsers")) {
			nextPage = "adminUsers.jsp";	
		}
		
		// Admin needs to log in
		else if (req.equals("adminLogin")) {
			// Obtain user login parameters
			String inputUsername = request.getParameter("username");
			String inputPwd = request.getParameter("password");
			
			// Verify admin
			if (this.db.VerifyAdmin(inputUsername, inputPwd)) {
				Admin currAdmin = this.db.GetAdmin(inputUsername);
				request.getSession().setAttribute("currAdmin", currAdmin);
			} else {
				// Admin could not be verified - redirect back to login page
				nextPage = "adminLogin.jsp";
				request.getSession().setAttribute("errorMsg", "Incorrect username or password.");
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

}
