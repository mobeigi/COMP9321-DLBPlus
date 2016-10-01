package edu.unsw.comp9321;

import java.io.IOException;
import java.util.Collections;
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
		doPost(request,response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Check for action string
		String action = request.getParameter("action");
		Admin currAdmin = (Admin) request.getSession().getAttribute("currAdmin");
		
		// Case when no admin, and no action: login
		if (currAdmin == null && action == null) {
			RequestDispatcher rd = request.getRequestDispatcher("adminLogin.jsp");
			rd.forward(request, response);
			return;
		}
		
		// Case when no admin, action = login: verify
		if (currAdmin == null && action.equals("adminLogin")) {
			String inputUsername = request.getParameter("username");
			String inputPwd = request.getParameter("password");
			
			// Verify admin
			if (this.db.VerifyAdmin(inputUsername, inputPwd)) {
				Admin newAdmin = this.db.GetAdmin(inputUsername);
				request.getSession().setAttribute("currAdmin", newAdmin);
				System.out.println("Admin succesfully logged in!");
			} else {
				// Admin could not be verified - redirect back to login page
				request.setAttribute("errorMsg", "Incorrect username or password.");
				RequestDispatcher rd = request.getRequestDispatcher("adminLogin.jsp");
				rd.forward(request, response);
				return;
			}
		}
		
		// At this point, admin is logged in. Redirect based on action
		String nextPage = "adminIndex.jsp";
		if (action == null) {
			// no action specified
			nextPage = "adminIndex.jsp";

		} else if (action.equals("viewAllUsers")) {
			List<User> ListOfUsers = this.db.GetAllUsers();
			Collections.sort(ListOfUsers);
			request.setAttribute("ListOfUsers", ListOfUsers);
			nextPage = "adminUsers.jsp";
		} else if (action.equals("viewAllListings")) {
			List<Listing> ListOfListings = this.db.GetListings(0, this.db.GetNumListings());
			request.setAttribute("ListOfListings", ListOfListings);
			nextPage = "adminListings.jsp";
		} else if (action.equals("adminLogout")) {
			System.out.println("Admin wants to log out...");
			request.getSession().removeAttribute("currAdmin");
			nextPage = "adminLogin.jsp";
		}
		
		//Check redirect to other pages
		String userId = request.getParameter("userId"); //redirect to user details page
		if(userId != null) {
			User myUser = this.db.GetUser(Integer.parseInt(userId));
			
			
			request.getSession().setAttribute("myUser", myUser);
			nextPage = "adminUserDetails.jsp";
		} else if (action != null && action.equals("UpdateUsersStatus")) { //update status of users
			List<User> listOfUsers = this.db.GetAllUsers();
			
			for (User user : listOfUsers) {
				
				// Obtain the status of the user
				int currId = user.getId();
				String currUserStatus = request.getParameter(Integer.toString(currId));
				
				// Update the status
				Boolean newStatus;
				if (currUserStatus.equals("true")) {
					newStatus = true;
				} else {
					newStatus = false;
				}
				this.db.SetUserAccountStatus(this.db.GetUser(currId), newStatus);
				
			}
			
			// Reload the users page
			listOfUsers = this.db.GetAllUsers();
			Collections.sort(listOfUsers);
			request.setAttribute("ListOfUsers", listOfUsers);
			nextPage = "adminUsers.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

}
