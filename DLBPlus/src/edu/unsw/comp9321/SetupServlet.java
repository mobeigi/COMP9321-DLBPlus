package edu.unsw.comp9321;

import java.io.File;
import java.io.IOException;
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

import org.apache.commons.lang3.StringUtils;

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
			
		if(req.equals("back")){
			link = "index.jsp";
		} else if(req.equals("home")){
			String errorMessage = "";
			if (this.db.dbConnStatus) {
				List<Listing> randListings = new ArrayList<Listing>();
				
				// Check whether there is no listings
				int totalNumListings = this.db.GetNumListings();
				if (totalNumListings == 0) {
					errorMessage = "No listings can be obtained.";
				}
				
				// Check whether there is < 10 listings
				else if (totalNumListings <= 10) {
					randListings = this.db.GetAllListings();
				}
				
				// Case when there are > 10
				else {
					// Obtain a unique list of random publications
					Listing listingToAdd = this.db.GetRandomListing();
					List<Integer> randListingIDs = new ArrayList<Integer>();
					while (randListings.size() < 10) {
						while (randListingIDs.contains(listingToAdd.getId())) {
							listingToAdd = this.db.GetRandomListing();
						}
						randListings.add(listingToAdd);
						randListingIDs.add(listingToAdd.getId());
						listingToAdd = this.db.GetRandomListing();
					}
				}

				// Set random publication list to session
				request.getSession().setAttribute("eMessage", errorMessage);
				request.setAttribute("randomListings", randListings);
				
			} else {
				System.out.println("Could not get random publication. Connection doesn't exist.");
			}
			
		link = "index.jsp";
		} else if(req.equals("viewSearchPage")){
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
      
      //Get page number for pagination
      String qPageNo = (request.getParameter("pageNo") == null || request.getParameter("pageNo").isEmpty()) ? null : new String( request.getParameter("pageNo").getBytes(), "UTF-8").trim();
      int pageNo = 1;
      int resultsPerPage = 10;
      int lastPage = (int)Math.ceil((double)results.size() / resultsPerPage);
      
      try {
        pageNo = Integer.parseInt(qPageNo);
      } catch (NumberFormatException e) {}
      
      //Ensure pageNo is within range
      if (pageNo > lastPage)
        pageNo = lastPage;
      if (pageNo < 1)
        pageNo = 1;
      
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
      request.getSession().setAttribute("currentFullUrl", currentFullUrl);
      
      SearchPageBean searchPageBean = new SearchPageBean(results, pageNo);
      request.getSession().setAttribute("searchFound", searchPageBean);
      
			link = "result.jsp";
			
		} else if(req.equals("viewCart")){	
			link = "cart.jsp";
		} else if(req.equals("remove")) {
			link = remove(request);
		} else if(req.equals("add")){
			/*
			Integer id = Integer.parseInt(request.getParameter("publicationID"));
			 ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("ShoppingCart");
			 LinkedList<Listing> shoppingCartItems = shoppingCart.getElements();

			 if (shoppingCartItems.contains(this.db.get(id))) {
				 request.getSession().setAttribute("isAlreadySelected", true);
			 }
			 else {
	    	 		request.getSession().setAttribute("isAlreadySelected", false);
	    	 		shoppingCartItems.add(this.db.get(id));
	    	 		shoppingCart.setElements(shoppingCartItems);
			 }
			 */
			 //request.getSession().setAttribute("cartSize", shoppingCartItems.size());
			 link = "cart.jsp";
					
		} else if(req.equals("register")){
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
			System.out.println(stringDob);
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
			System.out.println(dob);
			if (db.DoesUserExist(userName)){
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
					User newUser = new User();
					newUser = db.CreateUser(userName, password, firstName, lastName, nickName, email, address, dob, creditCard, dp);
					Random random = new Random();
					int rand = random.nextInt(99999);
					System.out.println("generating " + rand);
					request.getSession().setAttribute("confirmationNumber", rand);
					request.getSession().setAttribute("newUser",newUser);
					SendEmail(email,rand);
					link = "confirmation.jsp";
				}
			}
		} else if(req.equals("regSuccess")){
			String code = request.getParameter("code");
			String emailCode = request.getSession().getAttribute("confirmationNumber").toString();
			User newUser = (User) request.getSession().getAttribute("newUser");
			System.out.println("code is" + emailCode);
			if(newUser == null) System.out.println("WHAT THE FUCK");
			System.out.println(newUser.getId());
			if(code == null){
				link = "confirmation.jsp";
			} else {
				if(code.equals(emailCode)){
					request.getSession().setAttribute("user",newUser);
					link = "userAccount.jsp";
				} else {
					link = "confirmation.jsp";
				}
			}
			
		} else if(req.equals("login")){
			String errorMessage = "";
			String userName = request.getParameter("uname");
			String password = request.getParameter("pass");
			
			boolean success = db.VerifyUser(userName, password);
			System.out.println(success);
			if(success == true){
				User user = db.GetUser(userName);
				if (user.getAcctconfrm()){
					request.getSession().setAttribute("user",user);
					System.out.println(user.getUsername());
					link = "userAccount.jsp";
				} else {
					
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
		} else if(req.equals("registerPage")){
			System.out.println("Going to register page");
			String errorMessage = "";
			request.getSession().setAttribute("eMessage",errorMessage);
			link = "register.jsp";
		} else if(req.equals("modified")){
			User user = (User) request.getSession().getAttribute("user");
			request.getSession().setAttribute("user",user);
			link = "modifyDetails.jsp";
			String errorMessage = "";
			request.getSession().setAttribute("eMessage",errorMessage);
		} else if(req.equals("detailsAdded")){
			String errorMessage = "";
			String firstName = request.getParameter("fname");
			String lastName = request.getParameter("lname");
			String nickName = request.getParameter("nickname");
			String email = request.getParameter("email");
			String password = request.getParameter("pass");
			String address = request.getParameter("address");
			String creditCard = request.getParameter("ccn");
			String stringDob = request.getParameter("dob");
			String passConfirm = request.getParameter("passConfirm");
			String dp = "";
			
			if (!passConfirm.equals(password)){
				errorMessage = "Passwords do not match!";
				link = "modifyDetails.jsp";
				request.getSession().setAttribute("eMessage",errorMessage);
			}
			else {
				User toChange = (User) request.getSession().getAttribute("user");
				toChange.setFname(firstName);
				toChange.setLname(lastName);
				toChange.setNickname(nickName);
				toChange.setEmail(email);
				toChange.setAddress(address);
				toChange.setCreditcard(creditCard);
				db.ChangeUserPassword(toChange, password);
				boolean success = db.ChangeUserDetails(toChange);
				if (success){
					System.out.println("acc details changed");
				}
				
				request.getSession().setAttribute("eMessage",errorMessage);
				link = "userAccount.jsp";
			}                  
			request.getSession().setAttribute("eMessage",errorMessage);
			link = "userAccount.jsp";
		} else if(req.equals("loginPage")){
			String errorMessage = "";
			request.getSession().setAttribute("eMessage",errorMessage);
			link = "login.jsp";
		} else if(req.equals("toAccount")){
			link = "userAccount.jsp";
		} else if(req.equals("viewHist")){
			//View past orders
			User buyer = (User) request.getSession().getAttribute("user");
			int buyerID = buyer.getId();
			List<Order> orderList = db.GetOrderHistory(buyerID);
			
			request.getSession().setAttribute("userOrderList",orderList);
			link = "userSoldListings.jsp";
		} else if(req.equals("viewListings")){
			//View sales
			String errorMessage = "";
			User currUser = (User) request.getSession().getAttribute("user");
			List<Listing> userListings = db.GetUserListings(currUser.getId());
			if(userListings.isEmpty()){
				errorMessage = "Looks like you dont have any current listings";
			}
			request.getSession().setAttribute("eMessage",errorMessage);
			request.getSession().setAttribute("userListings", userListings);
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
			link = "userSellListings.jsp";
		} else if(req.equals("createListing")){
			//Create new listing
			link = "createListing.jsp";
		} else if(req.equals("registerItem")){
			User seller = (User) request.getSession().getAttribute("user");
			String title = request.getParameter("itemName"); //Required
			String authors = request.getParameter("authors");	//Required, split
			List<String> authorList = null;
			if(authors != null && !authors.isEmpty()){
				String[] authorStrings = StringUtils.split(authors, "|");
				authorList = new ArrayList<String>(Arrays.asList(authorStrings));
			}
			String type = request.getParameter("pubType");		//Required
			String editors = request.getParameter("editors");	//split
			List<String> editorList = null;
			if(editors != null &&!editors.isEmpty() ){
				String[] editorStrings = StringUtils.split(editors, "|");
				editorList = new ArrayList<String>(Arrays.asList(editorStrings));
			}
			String venues = request.getParameter("venues");		//split
			List<String> venueList = null;
			if(venues != null && !venues.isEmpty()){
				String[] venueStrings = StringUtils.split(venues, "|");
				venueList = new ArrayList<String>(Arrays.asList(venueStrings));
			}
			String pages = request.getParameter("pages");
			String volume = request.getParameter("volume");
			Integer year = Integer.parseInt(request.getParameter("year"));
			if (year.equals("")){
				year = null;
			}
			String month = request.getParameter("month");
			String address = request.getParameter("address");
			String number = request.getParameter("number");
			String urls = request.getParameter("urls");			//split
			List<String> urlList = null;
			if(urls != null && !urls.isEmpty()){
				String[] urlStrings = StringUtils.split(urls, "|");;
				urlList = new ArrayList<String>(Arrays.asList(urlStrings));
			}
			String ees = request.getParameter("ees");			//split
			List<String> eeList = null;
			if(ees != null && !ees.isEmpty()){
				String[] eeStrings = StringUtils.split(ees, "|");;
				eeList = new ArrayList<String>(Arrays.asList(eeStrings));
			}
			String cdrom = request.getParameter("cdrom");
			String cites = request.getParameter("cites");		//split
			List<String> citeList = null;
			if(cites != null && !cites.isEmpty()){
				String[] citeStrings = StringUtils.split(cites, "|");;
				citeList = new ArrayList<String>(Arrays.asList(citeStrings));
			}
			String publisher = request.getParameter("publisher");
			String isbns = request.getParameter("isbns");		//split
			List<String> isbnList = null;
			if(isbns != null && !isbns.isEmpty()){
				String[] isbnStrings = StringUtils.split(isbns, "|");;
				isbnList = new ArrayList<String>(Arrays.asList(isbnStrings));
			}
			String crossref = request.getParameter("crossref");
			String series = request.getParameter("series");
			String chapter = request.getParameter("chapter");
			String rating = request.getParameter("rating");
			String note = request.getParameter("note");
			Double price = Double.parseDouble(request.getParameter("price"));		//Required
			Integer quantity = Integer.parseInt(request.getParameter("quantity"));
			if(quantity.equals("")){
				quantity = null;
			}
				
			String image = request.getParameter("image");
			int duration = Integer.parseInt(request.getParameter("duration")) * 24 * 60 * 60;
			Type listType = Listing.stringToType(type);
			
			Timestamp listdate = new Timestamp(new Date().getTime());
			Timestamp enddate = new Timestamp(new Date().getTime()+ duration);

			Listing newListing = db.CreateListing(seller, quantity, listdate, enddate, price, image, listType, authorList, editorList, title, venueList, pages, year, address, volume, number, month, urlList, eeList, cdrom, citeList, publisher, note, crossref, isbnList, series, chapter, rating);
			
			// Prepare and redirect to view all listings page
			User currUser = (User) request.getSession().getAttribute("user");
			List<Listing> userListings = db.GetUserListings(currUser.getId());
			String errorMessage = "";
			if(userListings.isEmpty()){
				errorMessage = "Looks like you dont have any current listings";
			}
			request.getSession().setAttribute("eMessage",errorMessage);
			request.getSession().setAttribute("userListings", userListings);
			link = "userSellListings.jsp";	
		} 	
		// Case when user wants to view admin
		else if (req.equals("loginAdmin")) {
			response.sendRedirect("/DLBPlus/admin");
			return;
		}
		else if (req.equals("viewListingDetails")){
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
			link = "visualise.jsp";
		}
		
		 RequestDispatcher rd = request.getRequestDispatcher("/"+link);
		 rd.forward(request, response);
	}
	private void SendEmail(String email, int rand) {
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
            message.setFrom(new InternetAddress("dlpbpluscode@gmail.com,"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(email));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
	}
	private String remove(HttpServletRequest request){
		/*
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("ShoppingCart");
		LinkedList<Publication> itemsInCart = shoppingCart.getElements();
		
		String[] itemsToRemove = request.getParameterValues("removeFromCart");
		if (itemsToRemove == null) {
			return "cart.jsp";
		}
		for (String item : itemsToRemove) {
			//itemsInCart.remove(this.db.get(Integer.parseInt(item)));
		}
		request.getSession().setAttribute("cartSize", itemsInCart.size());
		*/
		return "cart.jsp";
	}

}
