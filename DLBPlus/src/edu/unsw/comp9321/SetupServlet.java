package edu.unsw.comp9321;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.PasswordAuthentication;
import java.security.Security;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.net.PasswordAuthentication;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.unsw.comp9321.Listing.Type;

/**
 * Servlet implementation class SetupServlet
 */
public class SetupServlet extends HttpServlet {
  
  // Maintains connection with database
  private DBHelper db = null;
  /**
   * @see HttpServlet#HttpServlet()
   */
  public SetupServlet() {
    super();
    
    // Instantiate database connection
    this.db = new DBHelper();
    boolean initSuccess = this.db.init();
    if (!initSuccess) {
      System.out.println("LOL there was an error.");
    } else {
      System.out.println("Successfully connected");
    }
  }
  
  
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
  }
  
  private LinkedList<String> getEntryDeets(Integer listingID) {
    Listing listing = this.db.GetListing(listingID);
    //LinkedList<String> entryDetails = pub.getPubDetails();
    //return entryDetails;
    return null;
  }
  
  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    String req;
    if(request.getParameter("action") != null){
      req = request.getParameter("action");
    } else {
      req = "home";
    }
    String link = "index.jsp";
    
    if(req.equals("home")){
      String errorMessage = "";
      if (this.db.dbConnStatus) {
        List<Listing> randListings = new ArrayList<Listing>();
        
        // Check whether there is no listings
        int totalNumListings = this.db.GetNumListings();
        if (totalNumListings == 0) {
          errorMessage = "No listings can be obtained.";
        }
        
        //Get all listings
        List<Listing> allListings = db.GetAllListings();
        
        //Filter out paused items and items with no quantity
        for (Iterator<Listing> iter = allListings.listIterator(); iter.hasNext(); ) {
          Listing l = iter.next();
          if (l.getQuantity() <= 0 || l.getPaused())
            iter.remove();
        }
        
        // Obtain a unique list of random listings
        if (allListings.size() <= 10) {
          randListings = allListings;
        }
        else {
          Random rand = new Random();
          int randIndex = rand.nextInt(allListings.size() - 1);
          
          Listing listingToAdd = allListings.get(randIndex);
          List<Integer> randListingIDs = new ArrayList<Integer>();
          while (randListings.size() < 10) {
            while (randListingIDs.contains(listingToAdd.getId())) {
              randIndex = rand.nextInt(allListings.size() - 1);
              listingToAdd = allListings.get(randIndex);
            }
            randListings.add(listingToAdd);
            randListingIDs.add(listingToAdd.getId());
          }
        }
        
        // Set random publication list to session
        request.getSession().setAttribute("eMessage", errorMessage);
        request.setAttribute("randomListings", randListings);
        
      } else {
        System.out.println("Could not get random publication. Connection doesn't exist.");
      }
      
      link = "index.jsp";
    } else if(req.equals("advancedsearch")){
      link = "search.jsp";
    } else if (req.equals("search")){
      String qTitle = (request.getParameter("title") == null || request.getParameter("title").isEmpty()) ? null : new String( request.getParameter("title").getBytes(), "UTF-8").trim();
      String qSellerUsername = (request.getParameter("sellerusername") == null || request.getParameter("sellerusername").isEmpty()) ? null : new String( request.getParameter("sellerusername").getBytes(), "UTF-8").trim();
      String qQuantity = (request.getParameter("quantity") == null || request.getParameter("quantity").isEmpty()) ? null : new String( request.getParameter("quantity").getBytes(), "UTF-8").trim();
      String qStartdate = (request.getParameter("startdate") == null || request.getParameter("startdate").isEmpty()) ? null : new String( request.getParameter("startdate").getBytes(), "UTF-8").trim();
      String qEnddate = (request.getParameter("enddate") == null || request.getParameter("enddate").isEmpty()) ? null : new String( request.getParameter("enddate").getBytes(), "UTF-8").trim();
      String qMinprice = (request.getParameter("minprice") == null || request.getParameter("minprice").isEmpty()) ? null : new String( request.getParameter("minprice").getBytes(), "UTF-8").trim();
      String qMaxprice = (request.getParameter("maxprice") == null || request.getParameter("maxprice").isEmpty()) ? null : new String( request.getParameter("maxprice").getBytes(), "UTF-8").trim();
      String qAuthors = (request.getParameter("author") == null || request.getParameter("author").isEmpty()) ? null : new String( request.getParameter("author").getBytes(), "UTF-8").trim();
      String qEditors = (request.getParameter("editor") == null || request.getParameter("editor").isEmpty()) ? null : new String( request.getParameter("editor").getBytes(), "UTF-8").trim();
      String qVolume = (request.getParameter("volume") == null || request.getParameter("volume").isEmpty()) ? null : new String( request.getParameter("volume").getBytes(), "UTF-8").trim();
      String qChapter = (request.getParameter("chapter") == null || request.getParameter("chapter").isEmpty()) ? null : new String( request.getParameter("chapter").getBytes(), "UTF-8").trim();
      String qNumber = (request.getParameter("number") == null || request.getParameter("number").isEmpty()) ? null : new String( request.getParameter("number").getBytes(), "UTF-8").trim();
      String qCdrom = (request.getParameter("cdrom") == null || request.getParameter("cdrom").isEmpty()) ? null : new String( request.getParameter("cdrom").getBytes(), "UTF-8").trim();
      String qPages = (request.getParameter("pages") == null || request.getParameter("pages").isEmpty()) ? null : new String( request.getParameter("pages").getBytes(), "UTF-8").trim();
      String qPublisher = (request.getParameter("publisher") == null || request.getParameter("publisher").isEmpty()) ? null : new String( request.getParameter("publisher").getBytes(), "UTF-8").trim();
      String qMonth = (request.getParameter("month") == null || request.getParameter("month").isEmpty()) ? null : new String( request.getParameter("month").getBytes(), "UTF-8").trim();
      String qYear = (request.getParameter("year") == null || request.getParameter("year").isEmpty()) ? null : new String( request.getParameter("year").getBytes(), "UTF-8").trim();
      String qAddress = (request.getParameter("address") == null || request.getParameter("address").isEmpty()) ? null : new String( request.getParameter("address").getBytes(), "UTF-8").trim();
      String qVenues = (request.getParameter("venues") == null || request.getParameter("venues").isEmpty()) ? null : new String( request.getParameter("venues").getBytes(), "UTF-8").trim();
      String qUrls = (request.getParameter("urls") == null || request.getParameter("urls").isEmpty()) ? null : new String( request.getParameter("urls").getBytes(), "UTF-8").trim();
      String qEes = (request.getParameter("ees") == null || request.getParameter("ees").isEmpty()) ? null : new String( request.getParameter("ees").getBytes(), "UTF-8").trim();
      String qCites = (request.getParameter("cites") == null || request.getParameter("cites").isEmpty()) ? null : new String( request.getParameter("cites").getBytes(), "UTF-8").trim();
      String qCrossref = (request.getParameter("crossref") == null || request.getParameter("crossref").isEmpty()) ? null : new String( request.getParameter("crossref").getBytes(), "UTF-8").trim();
      String qIsbns = (request.getParameter("isbns") == null || request.getParameter("isbns").isEmpty()) ? null : new String( request.getParameter("isbns").getBytes(), "UTF-8").trim();
      String qNote = (request.getParameter("note") == null || request.getParameter("note").isEmpty()) ? null : new String( request.getParameter("note").getBytes(), "UTF-8").trim();
      String qSeries = (request.getParameter("series") == null || request.getParameter("series").isEmpty()) ? null : new String( request.getParameter("series").getBytes(), "UTF-8").trim();
      String qRatings = (request.getParameter("ratings") == null || request.getParameter("ratings").isEmpty()) ? null : new String( request.getParameter("ratings").getBytes(), "UTF-8").trim();
      String qType = (request.getParameter("type") == null || request.getParameter("type").isEmpty()) ? null : new String( request.getParameter("type").getBytes(), "UTF-8").trim();
      
      //Handle separated multiple items
      //We also trim any whitespace around entries
      String[] qAuthorList = new String[0];
      String[] qEditorList = new String[0];
      String[] qUrlList = new String[0];
      String[] qEeList = new String[0];
      String[] qCiteList = new String[0];
      String[] qIsbnList = new String[0];
      String[] qVenueList = new String[0];
      
      if (qAuthors != null) qAuthorList = qAuthors.trim().split("\\\r\n");
      if (qEditors != null) qEditorList = qEditors.trim().split("\\\r\n");
      if (qUrls != null) qUrlList = qUrls.trim().split("\\\r\n");
      if (qEes != null) qEeList = qEes.trim().split("\\\r\n");
      if (qCites != null) qCiteList = qCites.trim().split("\\\r\n");
      if (qIsbns != null) qIsbnList = qIsbns.trim().split("\\\r\n");
      if (qVenues != null) qVenueList = qVenues.trim().split("\\\r\n");
      
      //Options
      String strMatchCase = (request.getParameterValues("matchcase") == null) ? null : request.getParameterValues("matchcase")[0];
      String strExactMatch = (request.getParameterValues("exactmatch") == null) ? null : request.getParameterValues("exactmatch")[0];
      boolean matchCase, exactMatch;
      matchCase = exactMatch = false;
      if (strMatchCase != null && strMatchCase.equals("on")) matchCase = true;
      if (strExactMatch != null && strExactMatch.equals("on")) exactMatch = true;
      
      //Creating listing item for searching
      Listing query = new Listing();
      
      if (qSellerUsername != null) {
        User u = db.GetUser(qSellerUsername);
        
        if (u == null) {
          query.setSellerid(-1); //as username was provided but no user found, search for dummy id
        } else {
          query.setSellerid(u.getId());
        }
      } else {
        query.setSellerid(null);
      }
      
      
      query.setTitle(qTitle);
      
      if (qQuantity != null)
        query.setQuantity(Integer.parseInt(qQuantity));
      
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      
      if (qStartdate != null) {
        try {
          Date d = df.parse(qStartdate);
          query.setListdate(new Timestamp(d.getTime()));
        } catch (ParseException e) {
          e.printStackTrace();
        }
      }
      
      if (qEnddate != null) {
        try {
          Date d = df.parse(qEnddate);
          query.setEnddate(new Timestamp(d.getTime()));
        } catch (ParseException e) {
          e.printStackTrace();
        }
      }
      
      Double minSellPrice = null;
      Double maxSellPrice = null;
      
      if (qMinprice != null)
        minSellPrice = Double.parseDouble(qMinprice);
      
      if (qMaxprice != null)
        maxSellPrice = Double.parseDouble(qMaxprice);
      
      if (qAuthors != null)
        query.setAuthors(Arrays.asList(qAuthorList));
      
      if (qEditors != null)
        query.setEditors(Arrays.asList(qEditorList));
      
      if (qVolume != null)
        query.setVolume(qVolume);
      
      if (qChapter != null)
        query.setChapter(qChapter);
      
      if (qNumber != null)
        query.setNumber(qNumber);
      
      if (qCdrom != null)
        query.setCdrom(qCdrom);
      
      if (qPages != null)
        query.setPages(qPages);
      
      if (qPublisher != null)
        query.setPublisher(qPublisher);
      
      if (qMonth != null)
        query.setMonth(qMonth);
      
      if (qYear != null)
        query.setYear(Integer.parseInt(qYear));
      
      if (qAddress != null)
        query.setAddress(qAddress);
      
      if (qVenues != null)
        query.setVenues(Arrays.asList(qVenueList));
      
      if (qUrls != null)
        query.setUrls(Arrays.asList(qUrlList));
      
      if (qEes != null)
        query.setEes(Arrays.asList(qEeList));
      
      if (qCites != null)
        query.setCites(Arrays.asList(qCiteList));
      
      if (qCrossref != null)
        query.setCrossref(qCrossref);
      
      if (qIsbns != null)
        query.setIsbns(Arrays.asList(qIsbnList));
      
      if (qNote != null)
        query.setNote(qNote);
      
      if (qSeries != null)
        query.setSeries(qSeries);
      
      if (qRatings != null)
        query.setRating(qRatings);
      
      if (qType != null)
        query.setType(qType);
      
      //Perform search
      List<Listing> results = db.SearchListings(query, minSellPrice, maxSellPrice, exactMatch, matchCase);
      
      //Filter out paused items and items with no quantity
      for (Iterator<Listing> iter = results.listIterator(); iter.hasNext(); ) {
        Listing l = iter.next();
        if (l.getQuantity() <= 0 || l.getPaused() || (l.getEnddate().getTime() < new Timestamp(new Date().getTime()).getTime()))
          iter.remove();
      }
      
      int pageNo = getPaginationPageNum(request, results.size(), 10);
      String currentFullUrl = getCurrentFullUrl(request);
      request.getSession().setAttribute("currentFullUrl", currentFullUrl);
      
      ListingBean listingBean = new ListingBean(results, pageNo);
      request.getSession().setAttribute("searchFound", listingBean);
      
      link = "result.jsp";
      
    } else if(req.equals("viewcart")){
      if (!isLoggedIn(request)) {
        response.sendRedirect("/dblplus?action=login");
        return;
      }
      
      User currUser = (User) request.getSession().getAttribute("user");
      List<CartItem> cartList = db.GetActiveCartItems(currUser.getId());
      List<Listing> cartListAsListings = new ArrayList<Listing>();
      
      for (CartItem ci : cartList)
        cartListAsListings.add(db.GetListing(ci.getListingid()));
      
      int pageNo = getPaginationPageNum(request, cartListAsListings.size(), 10);
      
      String currentFullUrl = getCurrentFullUrl(request);
      request.getSession().setAttribute("currentFullUrl", currentFullUrl);
      
      ListingBean listingBean = new ListingBean(cartListAsListings, pageNo);
      
      request.getSession().setAttribute("cartList", cartList);
      request.getSession().setAttribute("cartListAsListings", listingBean);
      
      link = "cart.jsp";
    } else if(req.equals("remove")) {
      link = remove(request);
    } else if(req.equals("add")){
      String errorMessage = "";
      boolean flag = false;
      int listingID = Integer.parseInt(request.getParameter("listingID"));
      Listing listing = db.GetListing(listingID);
      User currUser = (User) request.getSession().getAttribute("user");
      List<CartItem> cartList = db.GetActiveCartItems(currUser.getCartid());
      
      //Check if seller = curruser
      if (currUser.getId().equals(listing.getSellerid())) {
        errorMessage = "You can't buy an item that you are selling.";
      } else {
        if (cartList != null) {
          for (CartItem cartItem : cartList) {
            if (cartItem.getListingid() == listingID) {
              flag = true;
            }
          }
        }
        
        if (flag) {
          request.getSession().setAttribute("isAlreadySelected", true);
        } else {
          request.getSession().setAttribute("isAlreadySelected", false);
          CartItem item = db.AddToCart(currUser, listing);
          if (item == null) {
            errorMessage = "Failed to add item!";
          }
        }
      }
      
      
      cartList = db.GetActiveCartItems(currUser.getCartid());
      
      List<Listing> cartListAsListings = new ArrayList<Listing>();
      
      for (CartItem ci : cartList)
        cartListAsListings.add(db.GetListing(ci.getListingid()));
      
      int pageNo = getPaginationPageNum(request, cartListAsListings.size(), 10);
      
      String currentFullUrl = getCurrentFullUrl(request);
      request.getSession().setAttribute("currentFullUrl", currentFullUrl);
      
      ListingBean listingBean = new ListingBean(cartListAsListings, pageNo);
      
      request.getSession().setAttribute("cartList", cartList);
      request.getSession().setAttribute("cartListAsListings", listingBean);
      
      response.sendRedirect("/dblplus?action=viewcart");
      return;
      
    } else if(req.equals("checkout")){
      link = checkoutCartItems(request);
    } else if(req.equals("registeraccount")){
      int flag = 0;
      String errorMessage = "";
      String firstName = request.getParameter("fname");
      String lastName = request.getParameter("lname");
      String userName = request.getParameter("uname");
      String nickName = request.getParameter("nickname");
      String email = request.getParameter("email");
      String password = request.getParameter("pass");
      String address = request.getParameter("address");
      String creditCard = request.getParameter("ccn");
      String stringDob = request.getParameter("dob");
      String passConfirm = request.getParameter("passConfirm");
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      if(address == null){
        address="";
      }
      System.out.println(firstName + " " + lastName + " " + userName+ " " + email + " " + password + " " + address + " " + creditCard + " " + stringDob);
      Date dob = null;
      String dp = "";
      try {
        dob = df.parse(stringDob);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      if (db.DoesUserExist(userName)){
        System.out.println("Checking username ----- exists");
        errorMessage = "Username already exists!";
        flag = 1;
        link = "register.jsp";
        request.getSession().setAttribute("eMessage",errorMessage);
      }
      if (!passConfirm.equals(password)){
        errorMessage = "Passwords do not match!";
        flag = 1;
        link = "register.jsp";
        request.getSession().setAttribute("eMessage",errorMessage);
      }
      else {
        if (flag == 0){
          errorMessage = "";
          User newUser = new User();
          newUser = db.CreateUser(userName, password, firstName, lastName, nickName, email, address, dob, creditCard, dp);
          Random random = new Random();
          int rand = random.nextInt(99999);
          System.out.println("generating " + rand);
          request.getSession().setAttribute("confirmationNumber", rand);
          request.getSession().setAttribute("newUser",newUser);
          request.getSession().setAttribute("eMessage", errorMessage);
          SendConfirmEmail(userName, email,rand);
          link = "confirmation.jsp";
        }
      }
    } else if(req.equals("regSuccess")){
      String errorMessage = "";
      
      String code = request.getParameter("code");
      String emailCode = request.getSession().getAttribute("confirmationNumber").toString();
      User newUser = (User) request.getSession().getAttribute("newUser");
      System.out.println("code is" + emailCode);
      if(code == null){
        link = "confirmation.jsp";
      } else {
        if(code.equals(emailCode)){
          request.getSession().setAttribute("user",newUser);
          this.db.SetAcctConfirmed(newUser, true);
          response.sendRedirect("/dblplus?action=myaccount");
          return;
        } else {
          errorMessage = "Code is incorrect!";
          request.getSession().setAttribute("eMessage",errorMessage);
          link = "confirmation.jsp";
        }
      }
      
    } else if(req.equals("loginProcess")){
      String errorMessage = "";
      String userName = request.getParameter("uname");
      String password = request.getParameter("pass");
      boolean success = db.VerifyUser(userName, password);
      
      if(success){
        User user = db.GetUser(userName);
        
        //Check if account suspended
        if (!user.getAcctstatus()) {
          errorMessage = "Your account is currently suspended";
          request.getSession().setAttribute("eMessage",errorMessage);
          link = "login.jsp";
        } else {
          if (user.getAcctconfrm()) {
            request.getSession().setAttribute("user", user);
            System.out.println(user.getUsername());
            response.sendRedirect("/dblplus?action=myaccount");
            return;
          } else {
            Random random = new Random();
            int rand = random.nextInt(99999);
            System.out.println("generating " + rand);
            request.getSession().setAttribute("confirmationNumber", rand);
            request.getSession().setAttribute("newUser", user);
            SendConfirmEmail(userName, user.getEmail(), rand);
            link = "confirmation.jsp";
          }
        }
      } else {
        errorMessage = "Incorrect Username or Password";
        link = "login.jsp";
        request.getSession().setAttribute("eMessage",errorMessage);
      }
    } else if(req.equals("logout")){
      String errorMessage = "";
      request.getSession().setAttribute("eMessage",errorMessage);
      request.getSession().setAttribute("user",null);
      link = "index.jsp";
    } else if(req.equals("confirmPurchase")){
      link = "transactionSuccessful.jsp";
    } else if(req.equals("register")){
      System.out.println("Going to register page");
      String errorMessage = "";
      request.getSession().setAttribute("eMessage",errorMessage);
      link = "register.jsp";
    } else if(req.equals("modifydetails")){
      if (!isLoggedIn(request)) {
        response.sendRedirect("/dblplus?action=login");
        return;
      }
      
      User user = (User) request.getSession().getAttribute("user");
      request.getSession().setAttribute("user",user);
      link = "modifyDetails.jsp";
      String errorMessage = "";
      request.getSession().setAttribute("eMessage",errorMessage);
    } else if(req.equals("detailsAdded")){
      String firstName = (request.getParameter("fname") == null || request.getParameter("fname").isEmpty()) ? null : new String( request.getParameter("fname").getBytes(), "UTF-8").trim();
      String lastName = (request.getParameter("lname") == null || request.getParameter("lname").isEmpty()) ? null : new String( request.getParameter("lname").getBytes(), "UTF-8").trim();
      String nickName = (request.getParameter("nickname") == null || request.getParameter("nickname").isEmpty()) ? null : new String( request.getParameter("nickname").getBytes(), "UTF-8").trim();
      String email = (request.getParameter("email") == null || request.getParameter("email").isEmpty()) ? null : new String( request.getParameter("email").getBytes(), "UTF-8").trim();
      String password = (request.getParameter("pass") == null || request.getParameter("pass").isEmpty()) ? null : new String( request.getParameter("pass").getBytes(), "UTF-8").trim();
      String passConfirm = (request.getParameter("passConfirm") == null || request.getParameter("passConfirm").isEmpty()) ? null : new String( request.getParameter("passConfirm").getBytes(), "UTF-8").trim();
      String currentPassword = (request.getParameter("currentPassword") == null || request.getParameter("currentPassword").isEmpty()) ? null : new String( request.getParameter("currentPassword").getBytes(), "UTF-8").trim();
      String address = (request.getParameter("address") == null || request.getParameter("address").isEmpty()) ? null : new String( request.getParameter("address").getBytes(), "UTF-8").trim();
      String creditCard = (request.getParameter("ccn") == null || request.getParameter("ccn").isEmpty()) ? null : new String( request.getParameter("ccn").getBytes(), "UTF-8").trim();
      String stringDob = (request.getParameter("dob") == null || request.getParameter("dob").isEmpty()) ? null : new String( request.getParameter("dob").getBytes(), "UTF-8").trim();
      String errorMessage = "";
      
      //Get user object
      User toChange = (User) request.getSession().getAttribute("user");
      if (toChange == null) {
        errorMessage = "User does not exist!";
      } else {
        //Check to see if correct input password
        if (currentPassword == null || currentPassword.isEmpty() || !db.VerifyUser(toChange.getUsername(), currentPassword)) {
          System.out.println(passConfirm);
          errorMessage = "Current Password is incorrect";
        } else if (password != null && passConfirm != null && !password.equals(passConfirm)) {
          errorMessage = "Passwords do not match!";
        } else {
          if (firstName != null)
            toChange.setFname(firstName);
          
          if (lastName != null)
            toChange.setLname(lastName);
          
          if (nickName != null)
            toChange.setNickname(nickName);
          
          if (email != null)
            toChange.setEmail(email);
          
          if (address != null)
            toChange.setAddress(address);
          
          if (creditCard != null)
            toChange.setCreditcard(creditCard);
          
          if (stringDob != null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date dob = null;
            try {
              dob = df.parse(stringDob);
            } catch (ParseException e) {
              e.printStackTrace();
            }
            
            if (dob != null)
              toChange.setDob(dob);
            
          }
          
          if (password!= null && !password.isEmpty())
            db.ChangeUserPassword(toChange, password);
          
          //Change details
          db.ChangeUserDetails(toChange);
        }
      }
      
      request.getSession().setAttribute("eMessage",errorMessage);
      link = "modifyDetails.jsp";
      
    } else if(req.equals("login")){
      String errorMessage = "";
      request.getSession().setAttribute("eMessage",errorMessage);
      link = "login.jsp";
    } else if(req.equals("myaccount")){
      if (!isLoggedIn(request)) {
        response.sendRedirect("/dblplus?action=login");
        return;
      }
      
      link = "userAccount.jsp";
    } else if(req.equals("vieworderhistory")){
      if (!isLoggedIn(request)) {
        response.sendRedirect("/dblplus?action=login");
        return;
      }
      
      //View past orders
      String errorMessage = "";
      User buyer = (User) request.getSession().getAttribute("user");
      int buyerID = buyer.getId();
      List<Order> orderList = db.GetOrderHistory(buyerID);
      
      if(orderList.isEmpty()){
        errorMessage = "You have not purchased anything yet!";
      } else {
        int pageNo = getPaginationPageNum(request, orderList.size(), 10);
        
        String currentFullUrl = getCurrentFullUrl(request);
        request.getSession().setAttribute("currentFullUrl", currentFullUrl);
        
        OrderBean orderBean = new OrderBean(orderList, pageNo);
        request.getSession().setAttribute("userOrderList", orderBean);
      }
      request.getSession().setAttribute("eMessage", errorMessage);
      
      link = "userSoldListings.jsp";
    } else if(req.equals("viewlistings")){
      if (!isLoggedIn(request)) {
        response.sendRedirect("/dblplus?action=login");
        return;
      }
      
      //View sales
      String errorMessage = "";
      
      User currUser = (User) request.getSession().getAttribute("user");
      List<Listing> userListings = db.GetUserListings(currUser.getId());
      if (userListings.isEmpty()) {
        errorMessage = "Looks like you don't have any current listings";
      } else {
        int pageNo = getPaginationPageNum(request, userListings.size(), 10);
        
        String currentFullUrl = getCurrentFullUrl(request);
        request.getSession().setAttribute("currentFullUrl", currentFullUrl);
        
        ListingBean listingBean = new ListingBean(userListings, pageNo);
        request.getSession().setAttribute("userListings", listingBean);
      }
      
      request.getSession().setAttribute("eMessage", errorMessage);
      
      link = "userSellListings.jsp";
    } else if(req.equals("updateListingStatus")){
      
      User currUser = (User) request.getSession().getAttribute("user");
      List<Listing> allListings = db.GetUserListings(currUser.getId());
      
      // For each listing, check whether the isPaused checkbox is checked
      for(Listing listing : allListings){
        int listingId = listing.getId();
        String[] currListingStatus = request.getParameterValues(Integer.toString(listingId));
        
        // Case when checkbox is NOT checked
        if (currListingStatus == null) {
          // NOTE: We don't need to iterate over the string array 'currListingStatus', as
          // 		 we're sure that for every listing, there will be exactly ONE value in the array,
          //       and that value is the string 'checked'
          
          // Check whether the listing IS paused - change it otherwise
          if (listing.getPaused()) {
            this.db.SetPausedStatus(listing, false);
          }
        }
        
        // Case when checkbox is checked
        else {
          // Check whether the listing is NOT paused - change it otherwise
          if (listing.getPaused() == false) {
            this.db.SetPausedStatus(listing, true);
          }
        }
        
      }
      
      String errorMessage = "";
      List<Listing> userListings = db.GetUserListings(currUser.getId());
      if(userListings.isEmpty()){
        errorMessage = "Looks like you dont have any current listings";
      }
      request.getSession().setAttribute("eMessage",errorMessage);
      request.getSession().setAttribute("userListings", userListings);
      
      response.sendRedirect("/dblplus?action=viewlistings");
      return;
    } else if(req.equals("createlisting")){
      if (!isLoggedIn(request)) {
        response.sendRedirect("/dblplus?action=login");
        return;
      }
      
      //Create new listing
      link = "createListing.jsp";
    } else if(req.equals("registerItem")){
      
      User seller = (User) request.getSession().getAttribute("user");
      
      if (seller != null) {
        //Get fields
        String qTitle = (request.getParameter("title") == null || request.getParameter("title").isEmpty()) ? null : new String( request.getParameter("title").getBytes(), "UTF-8").trim();
        String qQuantity = (request.getParameter("quantity") == null || request.getParameter("quantity").isEmpty()) ? null : new String( request.getParameter("quantity").getBytes(), "UTF-8").trim();
        String qAuthors = (request.getParameter("author") == null || request.getParameter("author").isEmpty()) ? null : new String( request.getParameter("author").getBytes(), "UTF-8").trim();
        String qEditors = (request.getParameter("editor") == null || request.getParameter("editor").isEmpty()) ? null : new String( request.getParameter("editor").getBytes(), "UTF-8").trim();
        String qVolume = (request.getParameter("volume") == null || request.getParameter("volume").isEmpty()) ? null : new String( request.getParameter("volume").getBytes(), "UTF-8").trim();
        String qChapter = (request.getParameter("chapter") == null || request.getParameter("chapter").isEmpty()) ? null : new String( request.getParameter("chapter").getBytes(), "UTF-8").trim();
        String qNumber = (request.getParameter("number") == null || request.getParameter("number").isEmpty()) ? null : new String( request.getParameter("number").getBytes(), "UTF-8").trim();
        String qCdrom = (request.getParameter("cdrom") == null || request.getParameter("cdrom").isEmpty()) ? null : new String( request.getParameter("cdrom").getBytes(), "UTF-8").trim();
        String qPages = (request.getParameter("pages") == null || request.getParameter("pages").isEmpty()) ? null : new String( request.getParameter("pages").getBytes(), "UTF-8").trim();
        String qPublisher = (request.getParameter("publisher") == null || request.getParameter("publisher").isEmpty()) ? null : new String( request.getParameter("publisher").getBytes(), "UTF-8").trim();
        String qMonth = (request.getParameter("month") == null || request.getParameter("month").isEmpty()) ? null : new String( request.getParameter("month").getBytes(), "UTF-8").trim();
        String qYear = (request.getParameter("year") == null || request.getParameter("year").isEmpty()) ? null : new String( request.getParameter("year").getBytes(), "UTF-8").trim();
        String qAddress = (request.getParameter("address") == null || request.getParameter("address").isEmpty()) ? null : new String( request.getParameter("address").getBytes(), "UTF-8").trim();
        String qVenues = (request.getParameter("venues") == null || request.getParameter("venues").isEmpty()) ? null : new String( request.getParameter("venues").getBytes(), "UTF-8").trim();
        String qUrls = (request.getParameter("urls") == null || request.getParameter("urls").isEmpty()) ? null : new String( request.getParameter("urls").getBytes(), "UTF-8").trim();
        String qEes = (request.getParameter("ees") == null || request.getParameter("ees").isEmpty()) ? null : new String( request.getParameter("ees").getBytes(), "UTF-8").trim();
        String qCites = (request.getParameter("cites") == null || request.getParameter("cites").isEmpty()) ? null : new String( request.getParameter("cites").getBytes(), "UTF-8").trim();
        String qCrossref = (request.getParameter("crossref") == null || request.getParameter("crossref").isEmpty()) ? null : new String( request.getParameter("crossref").getBytes(), "UTF-8").trim();
        String qIsbns = (request.getParameter("isbns") == null || request.getParameter("isbns").isEmpty()) ? null : new String( request.getParameter("isbns").getBytes(), "UTF-8").trim();
        String qNote = (request.getParameter("note") == null || request.getParameter("note").isEmpty()) ? null : new String( request.getParameter("note").getBytes(), "UTF-8").trim();
        String qSeries = (request.getParameter("series") == null || request.getParameter("series").isEmpty()) ? null : new String( request.getParameter("series").getBytes(), "UTF-8").trim();
        String qRatings = (request.getParameter("ratings") == null || request.getParameter("ratings").isEmpty()) ? null : new String( request.getParameter("ratings").getBytes(), "UTF-8").trim();
        String qType = (request.getParameter("type") == null || request.getParameter("type").isEmpty()) ? null : new String( request.getParameter("type").getBytes(), "UTF-8").trim();
        
        String qPrice = (request.getParameter("price") == null || request.getParameter("price").isEmpty()) ? null : new String( request.getParameter("price").getBytes(), "UTF-8").trim();
        String qDuration = (request.getParameter("duration") == null || request.getParameter("duration").isEmpty()) ? null : new String( request.getParameter("duration").getBytes(), "UTF-8").trim();
        String qImage = (request.getParameter("image") == null || request.getParameter("image").isEmpty()) ? null : new String( request.getParameter("image").getBytes(), "UTF-8").trim();
        
        //Handle separated multiple items
        //We also trim any whitespace around entries
        List<String> qAuthorList = null;
        List<String> qEditorList = null;
        List<String> qUrlList = null;
        List<String> qEeList = null;
        List<String> qCiteList = null;
        List<String> qIsbnList = null;
        List<String> qVenueList = null;
        
        if (qAuthors != null) qAuthorList = Arrays.asList(qAuthors.trim().split("\\\r\n"));
        if (qEditors != null) qEditorList = Arrays.asList(qEditors.trim().split("\\\r\n"));
        if (qUrls != null) qUrlList = Arrays.asList(qUrls.trim().split("\\\r\n"));
        if (qEes != null) qEeList = Arrays.asList(qEes.trim().split("\\\r\n"));
        if (qCites != null) qCiteList = Arrays.asList(qCites.trim().split("\\\r\n"));
        if (qIsbns != null) qIsbnList = Arrays.asList(qIsbns.trim().split("\\\r\n"));
        if (qVenues != null) qVenueList = Arrays.asList(qVenues.trim().split("\\\r\n"));
        
        //Create objects
        Integer quantity = null;
        try {
          quantity = Integer.parseInt(qQuantity);
        } catch (NumberFormatException e) {}
        
        Double sellprice = null;
        try {
          sellprice = Double.parseDouble(qPrice);
        } catch (NumberFormatException e) {}
        
        Integer year = null;
        try {
          year = Integer.parseInt(qYear);
        } catch (NumberFormatException e) {}
        
        int duration = Integer.parseInt(qDuration) * 24 * 60 * 60; //convert duration into seconds (from days)
        Type listType = Listing.stringToType(qType);
        
        Timestamp listdate = new Timestamp(new Date().getTime()); //now
        Timestamp enddate = new Timestamp(new Date().getTime() + duration); //now + duration
        
        Listing newListing = db.CreateListing(seller, quantity, listdate, enddate, sellprice, qImage, listType, qAuthorList,
          qEditorList, qTitle, qVenueList, qPages, year, qAddress, qVolume, qNumber,
          qMonth, qUrlList, qEeList, qCdrom, qCiteList, qPublisher, qNote, qCrossref,
          qIsbnList, qSeries, qChapter, qRatings);
        
        
        if (newListing == null) {
          System.out.println("We have failed");
        }
        
        // Prepare and redirect to view all listings page
        List<Listing> userListings = db.GetUserListings(seller.getId());
        String errorMessage = "";
        if (userListings.isEmpty()) {
          errorMessage = "Looks like you don't have any current listings";
        } else {
          int pageNo = getPaginationPageNum(request, userListings.size(), 10);
          
          String currentFullUrl = getCurrentFullUrl(request);
          request.getSession().setAttribute("currentFullUrl", currentFullUrl);
          
          ListingBean listingBean = new ListingBean(userListings, pageNo);
          request.getSession().setAttribute("userListings", listingBean);
        }
        
        request.getSession().setAttribute("eMessage", errorMessage);
        link = "userSellListings.jsp";
      }
      else {
        link = "login.jsp"; //if not logged in
      }
    }
    // Case when user wants to view admin
    else if (req.equals("adminlogin")) {
      response.sendRedirect("/admin?action=login");
    }
    else if (req.equals("portal")) {
      response.sendRedirect("/admin?action=portal");
    }
    else if (req.equals("viewlistingdetails")){
      int listingID = -1;
      
      try {
        listingID = Integer.parseInt(request.getParameter("id"));
      } catch (NumberFormatException e) {}
      
      if (listingID != -1) {
        Listing listing = db.GetListing(listingID);
        if (listing != null) {
          request.getSession().setAttribute("listings", listing);
          
          //Increment views for listing
          User viewer = (User)request.getSession().getAttribute("user");
          if (viewer == null || (viewer.getId() != listing.getSellerid())) { //null viewer = guest
            db.IncrementListingViews(listing);
          }
        } else {
          listingID = -1; //error
        }
      }
      
      request.getSession().setAttribute("listingFound", (listingID != -1));
      link = "listing.jsp";
    }
    
    // Case when user wants to visualise shit
    else if (req.equals("visualise")) {
      
      // Prepare visualise page
      List<VisNode> visNodes = this.db.GetAllVisNodes();
      List<VisRelationship> visRelationships = this.db.GetAllVisRelationships();
      
      // Bind visNodes and visRelationships to request
      request.setAttribute("visNodes", visNodes);
      request.setAttribute("visRelationships", visRelationships);
      link = "visualise.jsp";
    }
    
    // Case when user wants to query the visualisation
    else if (req.equals("queryVisualisation")) {
      
      // Obtain value of query (split based on newline)
      String qData = (request.getParameter("queryData") == null || request.getParameter("queryData").isEmpty()) ? null : new String( request.getParameter("queryData").getBytes(), "UTF-8").trim();
      String[] qDataList = new String[0];
      if (qData != null) qDataList = qData.trim().split("\\\r\n");
      
      // Obtain type of query
      String qType = new String( request.getParameter("queryType").getBytes(), "UTF-8").trim();	// this will never be null
      
      // Create the query object
      VisQuery query = new VisQuery();
      if (qData != null) {
        query.setQueryData(Arrays.asList(qDataList));
      }
      query.setQueryType(qType);
      
      // TODO: Remove duplicates in query fields for the lists
      
      // Obtain the node and relationship results based on query
      VisResult result = this.db.SearchVis(query);
      
      // Bind visNodes and visRelationships from result to request
      request.setAttribute("visNodes", result.getVisNodesResult());
      request.setAttribute("visRelationships", result.getVisRelationshipResult());
      link = "visualise.jsp";
    }
    
    RequestDispatcher rd = request.getRequestDispatcher("/" + link);
    rd.forward(request, response);
  }
  private String checkoutCartItems(HttpServletRequest request) {
    User currUser = (User) request.getSession().getAttribute("user");
    List<CartItem> checkOutItems = db.GetActiveCartItems(currUser.getCartid());
    for(CartItem checkOutItem : checkOutItems){
      //Gets the listing id and object of an item in the shopping cart
      Integer listingid = checkOutItem.getListingid();
      Listing listing = db.GetListing(listingid);
      
      // Send checkout message to seller's email
      String sellerName = checkOutItem.getSellerName();
      User seller = db.GetUser(sellerName);
      String listingName = listing.getTitle();
      String buyerAddress = currUser.getAddress();
      SendCheckoutEmail(seller.getEmail(), listingName, sellerName, currUser.getUsername(), buyerAddress);
      
      // Decrement listing's quantity
      db.DecrementListingQuantity(listing);
      
      // Create order
      db.CreateOrder(currUser.getId(), listing);
      
      // Remove from cart
      db.HardRemoveFromCart(checkOutItem);
    }
    
    
    System.out.println("Checkout complete!");
    return "checkoutSuccess.jsp";
  }
  
  private void SendCheckoutEmail(String email, String listingName, String sellerName, String buyerName, String buyerAddress) {
    final String username = "dlbpluscode@gmail.com	";
    final String password = "uncommonpassword";
    
    Properties props = new Properties();
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    
    Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
          return new javax.mail.PasswordAuthentication(username, password);
        }
      });
    try {
      
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("erikzhong1@gmail.com,"));
      message.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(email));
      message.setSubject("DBL+ Purchase Notification!");
      message.setText("Hi " + sellerName + ",\n" + "A copy of " + listingName + " has just been purchased by " + buyerName + ".\n" + "Item will be shipped ASAP to " + buyerAddress + "\n\n From,\nDBL+");
      System.out.println("Hi " + sellerName + ",\n" + "A copy of " + listingName + " has just been purchased by " + buyerName + ".\n" + "Item will be shipped ASAP to " + buyerAddress + ".\n\nFrom,\nDBL+");
      
      Transport.send(message);
      
      System.out.println("Seller notified");
      
    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }
  
  
  private void SendConfirmEmail(String userName, String email, int rand) {
    final String username = "dlbpluscode@gmail.com	";
    final String password = "uncommonpassword";
    
    Properties props = new Properties();
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    
    Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
          return new javax.mail.PasswordAuthentication(username, password);
        }
      });
    try {
      
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("erikzhong1@gmail.com,"));
      message.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(email));
      message.setSubject("DBL+ email account verification");
      message.setContent("Hi " + userName + ",<br>" + "Thank you for registering with DBL+. Your account confirmation code is:<br><br>" + "<center><strong>" + rand + "</strong></center><br><br>" + "From,<br>The DBL+ Team","text/html");
      
      Transport.send(message);
      
      System.out.println("Done");
      
    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }
  private String remove(HttpServletRequest request){
    List<CartItem> cartList = (List<CartItem>) request.getSession().getAttribute("cartList");
    Integer listingID = Integer.parseInt(request.getParameter("removeListingID"));
    User currUser = (User) request.getSession().getAttribute("user");
    CartItem item = null;
    if(!cartList.isEmpty() && cartList != null){
      for(CartItem cartItem : cartList){
        if(cartItem.getListingid() == listingID){
          item = cartItem;
        }
      }
    }
    if(item != null){
      db.RemoveFromCart(item);
    }
    
    cartList = db.GetActiveCartItems(currUser.getCartid());
    
    List<Listing> cartListAsListings = new ArrayList<Listing>();
    
    for (CartItem ci : cartList)
      cartListAsListings.add(db.GetListing(ci.getListingid()));
    
    //Get page number for pagination
    int pageNo = getPaginationPageNum(request, cartListAsListings.size(), 10);
    
    String currentFullUrl = getCurrentFullUrl(request);
    request.getSession().setAttribute("currentFullUrl", currentFullUrl);
    
    ListingBean listingBean = new ListingBean(cartListAsListings, pageNo);
    
    request.getSession().setAttribute("cartList", cartList);
    request.getSession().setAttribute("cartListAsListings", listingBean);
    
    return "cart.jsp";
  }
  
  /**
   * Check if admin is logged in.
   *
   * @param req Request object
   * @return true if logged in, false otherwise
   */
  private boolean isLoggedIn(HttpServletRequest req) {
    User u = (User)req.getSession().getAttribute("user");
    return (u != null);
  }
  
  public static String getCurrentFullUrl(HttpServletRequest request) {
    String currentFullUrl = request.getRequestURL().toString();
    //Ensure query string is not empty
    if (request.getQueryString() != null && !request.getQueryString().isEmpty()) {
      String[] queries = request.getQueryString().split("&");
      ArrayList<String> finalQueryStringArray = new ArrayList<String>();
      //Remove current pageNo parameters
      for (String s : queries) {
        if (!s.startsWith("pageNo="))
          finalQueryStringArray.add(s);
      }
      //Recombine all other parameters in same order
      StringBuilder sb = new StringBuilder();
      for (String s : finalQueryStringArray)
      {
        sb.append(s);
        sb.append("&");
      }
      String finalQueryString = sb.toString();
      if (finalQueryString.endsWith("&")) //remove final '&' from join
        finalQueryString = finalQueryString.substring(0, finalQueryString.length() - 1);
      currentFullUrl += ("?" + finalQueryString);
    }
    
    return currentFullUrl;
  }
  
  public static int getPaginationPageNum(HttpServletRequest request, int size, int resultsPerPage) {
    //Get page number for pagination
    String qPageNo = null;
    int pageNo = 1;
    
    try {
      qPageNo = (request.getParameter("pageNo") == null || request.getParameter("pageNo").isEmpty()) ? null : new String(request.getParameter("pageNo").getBytes(), "UTF-8").trim();
    } catch (Exception e) {
      return pageNo;
    }
    int lastPage = (int) Math.ceil((double)size / resultsPerPage);
    
    try {
      pageNo = Integer.parseInt(qPageNo);
    } catch (NumberFormatException e) {
    }
    
    //Ensure pageNo is within range
    if (pageNo > lastPage)
      pageNo = lastPage;
    if (pageNo < 1)
      pageNo = 1;
    
    return pageNo;
  }
  
}
