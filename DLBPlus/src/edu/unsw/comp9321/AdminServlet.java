package edu.unsw.comp9321;

import java.io.IOException;

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
		/*
		// Redirect to login page if no admin is currently logged in; index page otherwise
		String redir = "";
		Admin currAdmin = (Admin) request.getSession().getAttribute("currAdmin");
		if (currAdmin == null) {
			redir = "adminlogin.html";
		} else {
			redir = "adminindex.html";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/" + redir);
		*/
		RequestDispatcher rd = request.getRequestDispatcher("/admin/adminIndex.jsp");
		rd.forward(request, response);
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

		RequestDispatcher rd = request.getRequestDispatcher("/admin/" + nextPage);
		rd.forward(request, response);
	}

}
