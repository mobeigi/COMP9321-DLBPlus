package edu.unsw.comp9321;

import java.io.IOException;
import java.util.ArrayList;
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
    
    //Quick redirects
    if (action != null) {
      if (action.equals("login")) {
        RequestDispatcher rd = request.getRequestDispatcher("adminLogin.jsp");
        rd.forward(request, response);
        return;
      }
      
      if (action.equals("portal")) {
        if (!isLoggedIn(request)) {
          response.sendRedirect("admin?action=login");
          return;
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("adminIndex.jsp");
        rd.forward(request, response);
        return;
      }
    }
    
    // Case when no admin, action = login: verify
    if (currAdmin == null && action.equals("adminLoginProcess")) {
      String inputUsername = request.getParameter("username");
      String inputPwd = request.getParameter("password");
      
      // Verify admin
      if (this.db.VerifyAdmin(inputUsername, inputPwd)) {
        Admin newAdmin = this.db.GetAdmin(inputUsername);
        request.getSession().setAttribute("currAdmin", newAdmin);
        System.out.println("Admin successfully logged in!");
        response.sendRedirect("admin?action=portal");
        return;
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
      if (!isLoggedIn(request)) {
        response.sendRedirect("admin?action=login");
        return;
      }
      
      // no action specified
      response.sendRedirect("admin?action=portal");
      return;
      
    } else if (action.equals("viewallusers")) {
      if (!isLoggedIn(request)) {
        response.sendRedirect("admin?action=login");
        return;
      }
      
      List<User> ListOfUsers = this.db.GetAllUsers();
      request.setAttribute("ListOfUsers", ListOfUsers);
      nextPage = "adminUsers.jsp";
    } else if (action.equals("viewalllistings")) {
      if (!isLoggedIn(request)) {
        response.sendRedirect("admin?action=login");
        return;
      }
      
      List<Listing> ListOfListings = this.db.GetListings(0, this.db.GetNumListings());
      request.setAttribute("ListOfListings", ListOfListings);
      nextPage = "adminListings.jsp";
    } else if (action.equals("adminLogout")) {
      if (!isLoggedIn(request)) {
        response.sendRedirect("admin?action=login");
        return;
      }
      
      System.out.println("Admin wants to log out...");
      request.getSession().removeAttribute("currAdmin");
      
      response.sendRedirect("admin?action=login");
      return;
    } else if (action.equals("vieworderhistory")) {
      if (!isLoggedIn(request)) {
        response.sendRedirect("admin?action=login");
        return;
      }
      
      //View past orders
      String errorMessage = "";
      String qUserId = (request.getParameter("userid") == null || request.getParameter("userid").isEmpty()) ? null : new String( request.getParameter("userid").getBytes(), "UTF-8").trim();
      Integer userId = null;
      
      if (qUserId != null) {
        try {
          userId = Integer.parseInt(qUserId);
        } catch (NumberFormatException e) {}
      }
      
      if (qUserId == null || userId == null) {
        errorMessage = "Invalid user ID provided";
      } else {
        User u = db.GetUser(userId);
        if (u == null) {
          errorMessage = "No user exists with user id:" + userId;
        } else {
          List<Order> orderList = db.GetOrderHistory(userId);
          
          if(orderList.isEmpty()){
            errorMessage = "This user has not purchased anything yet!";
          } else {
            int pageNo = SetupServlet.getPaginationPageNum(request, orderList.size(), 10);
            
            String currentFullUrl = SetupServlet.getCurrentFullUrl(request);
            request.getSession().setAttribute("currentFullUrl", currentFullUrl);
            
            OrderBean orderBean = new OrderBean(orderList, pageNo);
            request.getSession().setAttribute("userOrderList", orderBean);
          }
        }
      }
      request.getSession().setAttribute("eMessage", errorMessage);
      nextPage = "adminUserOrderHistory.jsp";
    } else if (action.equals("viewremovedlistings")) {
      if (!isLoggedIn(request)) {
        response.sendRedirect("admin?action=login");
        return;
      }
      
      //View past orders
      String errorMessage = "";
      String qUserId = (request.getParameter("userid") == null || request.getParameter("userid").isEmpty()) ? null : new String( request.getParameter("userid").getBytes(), "UTF-8").trim();
      Integer userId = null;
      
      if (qUserId != null) {
        try {
          userId = Integer.parseInt(qUserId);
        } catch (NumberFormatException e) {}
      }
      
      if (qUserId == null || userId == null) {
        errorMessage = "Invalid user ID provided";
      } else {
        User u = db.GetUser(userId);
        if (u == null) {
          errorMessage = "No user exists with user id:" + userId;
        } else {
          List<CartItem> removedCart = db.GetRemovedCartItems(userId);
          List<Listing> removedListings = new ArrayList<Listing>();
          
          for (CartItem ci : removedCart)
            removedListings.add(db.GetListing(ci.getListingid()));
          
          if(removedListings.isEmpty()){
            errorMessage = "This user has not removed any listings from their shopping cart yet!";
          } else {
            int pageNo = SetupServlet.getPaginationPageNum(request, removedListings.size(), 10);
            
            String currentFullUrl = SetupServlet.getCurrentFullUrl(request);
            request.getSession().setAttribute("currentFullUrl", currentFullUrl);
            
            ListingBean listingBean = new ListingBean(removedListings, pageNo);
            request.getSession().setAttribute("userListings", listingBean);
          }
        }
      }
      request.getSession().setAttribute("eMessage", errorMessage);
      nextPage = "adminUserRemovedListings.jsp";
    } else if (action.equals("viewuserdetails")) {
      if (!isLoggedIn(request)) {
        response.sendRedirect("admin?action=login");
        return;
      }
  
      String qUserId = (request.getParameter("userid") == null || request.getParameter("userid").isEmpty()) ? null : new String( request.getParameter("userid").getBytes(), "UTF-8").trim();
      Integer userId = null;
  
      if (qUserId != null) {
        try {
          userId = Integer.parseInt(qUserId);
        } catch (NumberFormatException e) {}
      }
  
      if (qUserId == null || userId == null) {
        //error
      } else {
        User u = db.GetUser(userId);
        if (u != null) {
          request.setAttribute("myUser", u);
        }
      }
  
      nextPage = "adminUserDetails.jsp";
    }
    
    //Check redirect to other pages
    String userId = request.getParameter("userId");
    String listingId = request.getParameter("listingId");
    
    if(userId != null) { //redirect to user details page
      User myUser = this.db.GetUser(Integer.parseInt(userId));
      List<Order> ListOfOrders = this.db.GetOrderHistory(Integer.parseInt(userId));
      List<CartItem> ListOfRemovedCartItems = this.db.GetRemovedCartItems(myUser.getCartid());
      
      int numOrders = ListOfOrders.size();
      List<String> SellerNames = new ArrayList<String>();
      
      //obtain seller names from order seller name IDs
      for(int i=0; i<numOrders; i++) {
        int currSellerId = ListOfOrders.get(i).getSellerid();
        
        SellerNames.add(this.db.GetUser(currSellerId).getFname() + " " + this.db.GetUser(currSellerId).getLname());
      }
      
      request.getSession().setAttribute("SellerNames", SellerNames);
      request.getSession().setAttribute("ListOfOrders", ListOfOrders);
      request.getSession().setAttribute("ListOfRemovedCartItems", ListOfRemovedCartItems);
      request.getSession().setAttribute("myUser", myUser);
      nextPage = "adminUserDetails.jsp";
    } else if (listingId != null) { //redirect to listing details page
      Listing listing = this.db.GetListing(Integer.parseInt(listingId));
      request.getSession().setAttribute("viewListing", listing);
      nextPage = "adminListingDetails.jsp";
    } else if (action != null && action.equals("UpdateUsersStatus")) { //update status of users
      if (!isLoggedIn(request)) {
        response.sendRedirect("admin?action=login");
        return;
      }
      
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
      request.setAttribute("ListOfUsers", listOfUsers);
      nextPage = "adminUsers.jsp";
      
      response.sendRedirect("admin?action=viewallusers");
      return;
      
    } else if(action != null && action.equals("removeListing")) { //remove a listing
      if (!isLoggedIn(request)) {
        response.sendRedirect("admin?action=login");
        return;
      }
      
      String itemId = request.getParameter("itemId");
      this.db.RemoveListing(Integer.parseInt(itemId));
      
      List<Listing> ListOfListings = this.db.GetListings(0, this.db.GetNumListings());
      request.setAttribute("ListOfListings", ListOfListings);
      nextPage = "adminListings.jsp";
    }
    
    RequestDispatcher rd = request.getRequestDispatcher(nextPage);
    rd.forward(request, response);
  }
  
  /**
   * Check if admin is logged in.
   *
   * @param req Request object
   * @return true if logged in, false otherwise
   */
  private boolean isLoggedIn(HttpServletRequest req) {
    Admin a = (Admin)req.getSession().getAttribute("currAdmin");
    return (a != null);
  }
  
}
