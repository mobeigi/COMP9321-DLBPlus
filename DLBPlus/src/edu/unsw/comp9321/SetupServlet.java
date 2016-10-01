package edu.unsw.comp9321;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
		String link = "home.jsp";
			
		if(req.equals("back")){
			link = "index.jsp";
		} else if(req.equals("home")){
			String errorMessage = "";
			if (this.db.dbConnStatus) {
				List<Listing> randListings = new ArrayList<Listing>();
				List<Integer> randListingIDs = new ArrayList<Integer>();
				
				// Obtain a unique list of random publications
				Listing listingToAdd = this.db.GetRandomListing();
				if (listingToAdd == null) {
					errorMessage = "No listings can be obtained.";
				}
				while (listingToAdd != null && randListings.size() < 10) {
					
					while (randListingIDs.contains(listingToAdd.getId())) {
						listingToAdd = this.db.GetRandomListing();
					}
					randListings.add(listingToAdd);
					randListingIDs.add(listingToAdd.getId());
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
		} else if(req.equals("search")){
			String query = request.getParameter("searchQuery");
			String type = request.getParameter("pubType");
			LinkedList<Listing> result = search(query, type, request);
			SearchPageBean SPBean = new SearchPageBean(result);
			request.getSession().setAttribute("searchFound",SPBean);
			
			link = "result.jsp";
		} else if(req.equals("aSearch")){
			String title = request.getParameter("searchTitle");
			String author = request.getParameter("searchAuthor");
			String editor = request.getParameter("searchEditor");
			String volume = request.getParameter("searchVolume");
			String chapter = request.getParameter("searchChapter");
			String pages = request.getParameter("searchPage");
			String publisher = request.getParameter("searchPubber");
			String isbn = request.getParameter("searchISBN");
			String year = request.getParameter("searchYear");
			String venues = request.getParameter("searchVenues");
			String seller = request.getParameter("searchSeller");	
			String type = request.getParameter("searchPubType");
			LinkedList<Listing> result = aSearch(title, author, editor, volume, chapter, pages, publisher, isbn, year, venues, seller, type);
			SearchPageBean searchPageBean = new SearchPageBean(result);
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
					
		} else if(req.equals("viewPreviousSearchPage")){
			navigateSearchPage(request);
			link = "result.jsp";
		} else if(req.equals("viewNextSearchPage")){
			navigateSearchPage(request);
			link = "result.jsp";
		} else if(req.equals("viewNextSearchPage")){
			navigateSearchPage(request);
			link = "result.jsp";
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
					link = "confirmation.jsp";
				}
			}
		} else if(req.equals("regSuccess")){
			String code = request.getParameter("code");
			String emailCode = request.getSession().getAttribute("confirmationNumber").toString();
			User newUser = new User(); 
			newUser = (User) request.getSession().getAttribute("newUser");
			System.out.println("code is" + emailCode);
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
				request.getSession().setAttribute("user",user);
				System.out.println(user.getUsername());
				link = "userAccount.jsp";
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
			link = "modifyDetails.jsp";
		} else if(req.equals("loginPage")){
			System.out.println("Going to login page");
			String errorMessage = "";
			request.getSession().setAttribute("eMessage",errorMessage);
			link = "login.jsp";
		} else if(req.equals("toAccount")){
			link = "userAccount.jsp";
		} else if(req.equals("viewHist")){
			//View past orders
			link = "userSoldListings.jsp";
		} else if(req.equals("viewListings")){
			//View sales
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
				String[] authorStrings = authors.split("|");
				authorList = new ArrayList<String>(Arrays.asList(authorStrings));
			}
			String type = request.getParameter("pubType");		//Required
			String editors = request.getParameter("editors");	//split
			List<String> editorList = null;
			if(editors != null &&!editors.isEmpty() ){
				String[] editorStrings = editors.split("|");
				editorList = new ArrayList<String>(Arrays.asList(editorStrings));
			}
			String venues = request.getParameter("venues");		//split
			List<String> venueList = null;
			if(venues != null && !venues.isEmpty()){
				String[] venueStrings = venues.split("|");
				venueList = new ArrayList<String>(Arrays.asList(venueStrings));
			}
			String pages = request.getParameter("pages");
			String volume = request.getParameter("volume");
			Integer year = Integer.parseInt(request.getParameter("year"));
			String month = request.getParameter("month");
			String address = request.getParameter("address");
			String number = request.getParameter("number");
			String urls = request.getParameter("urls");			//split
			List<String> urlList = null;
			if(urls != null && !urls.isEmpty()){
				String[] urlStrings = urls.split("|");
				urlList = new ArrayList<String>(Arrays.asList(urlStrings));
			}
			String ees = request.getParameter("ees");			//split
			List<String> eeList = null;
			if(ees != null && !ees.isEmpty()){
				String[] eeStrings = ees.split("|");
				eeList = new ArrayList<String>(Arrays.asList(eeStrings));
			}
			String cdrom = request.getParameter("cdrom");
			String cities = request.getParameter("cities");		//split
			List<String> citeList = null;
			if(cities != null && !cities.isEmpty()){
				String[] citeStrings = cities.split("|");
				citeList = new ArrayList<String>(Arrays.asList(citeStrings));
			}
			String publisher = request.getParameter("publisher");
			String isbns = request.getParameter("isbns");		//split
			List<String> isbnList = null;
			if(isbns != null && !isbns.isEmpty()){
				String[] isbnStrings = isbns.split("|");
				isbnList = new ArrayList<String>(Arrays.asList(isbnStrings));
			}
			String crossref = request.getParameter("crossref");
			String series = request.getParameter("series");
			String chapter = request.getParameter("chapter");
			String rating = request.getParameter("rating");
			String note = request.getParameter("note");
			Double price = Double.parseDouble(request.getParameter("price"));		//Required
			Integer quantity = Integer.parseInt(request.getParameter("quantity"));
			String image = request.getParameter("image");
			int duration = Integer.parseInt(request.getParameter("duration")) * 24 * 60 * 60;
			Type listType = Listing.stringToType(type);
			
			Timestamp listdate = new Timestamp(new Date().getTime());
			Timestamp enddate = new Timestamp(new Date().getTime()+ duration);
			
			
			
			Listing newListing = db.CreateListing(seller, quantity, listdate, enddate, price, image, listType, authorList, editorList, title, venueList, pages, year, address, volume, number, month, urlList, eeList, cdrom, citeList, publisher, note, crossref, isbnList, series, chapter, rating);
			
		}
		
		// Case when user wants to view admin
		else if (req.equals("loginAdmin")) {
			response.sendRedirect("/DLBPlus/admin");
			return;
		}
		
		 RequestDispatcher rd = request.getRequestDispatcher("/"+link);
		 rd.forward(request, response);
	}
	private void navigateSearchPage(HttpServletRequest request) {
		String action = request.getParameter("action");
		SearchPageBean searchPageBean = (SearchPageBean) request.getSession().getAttribute("searchFound");
		if (searchPageBean == null) {
			System.out.println("What the fck");
		} else {
			if(action.equals("viewPreviousSearchPage")){
				searchPageBean.currPage = searchPageBean.currPage - 1;
			} else if(action.equals("viewNextSearchPage")){
				searchPageBean.currPage = searchPageBean.currPage + 1;
			}
			request.getSession().setAttribute("searchFound", searchPageBean);
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
	private LinkedList<Listing> aSearch(String title, String author, String editor, String volume, String chapter, String pages, String publisher, String isbn, String year, String venues, String seller, String type) {
		LinkedList<Listing> result = new LinkedList<Listing>();
		boolean flag = true;
		String publicationType = type;
//		if(type.toLowerCase().equals("any")){
//			for (Publication p : this.db) {
//				flag = true;
//				if(title != "" && flag == true){
//					if(!p.getTitle().toLowerCase().contains(title.toLowerCase())){
//						flag = false;
//					}
//				}
//				if(author != "" && flag == true){
//					if(p.getAuthor()== null || !p.getAuthor().toLowerCase().contains(author.toLowerCase())){
//						flag = false;
//					}
//				}
//				if(editor != "" && flag == true){
//					if(p.getEditor()== null || !p.getEditor().toLowerCase().contains(editor.toLowerCase())){
//						flag = false;
//					}
//				}
//				if(volume != "" && flag == true){
//					if(p.getVolume()== null || !p.getVolume().toLowerCase().contains(volume.toLowerCase())){
//						flag = false;
//					}
//				}
//				if(publisher != null && flag == true){
//					if(p.getPublisher()== null || !p.getPublisher().toLowerCase().contains(publisher.toLowerCase())){
//						flag = false;
//					}
//				}
//				if(isbn != "" && flag == true){
//					if(p.getISBN()== null || !p.getISBN().toLowerCase().contains(isbn.toLowerCase())){
//						flag = false;
//					}
//				}
//				if(year != "" && flag == true){
//					if(p.getYear()== null || !p.getYear().toLowerCase().contains(year.toLowerCase())){
//						flag = false;
//					}
//				}
//				if(flag == true){
//					result.add(p);
//				}
//			}
//		}
//		if(type.toLowerCase().equals("article")){
//			advSearchHelper(title, author, editor, volume, publisher, isbn, year, result);
//		}
//		
//		if(type.toLowerCase().equals("inproceedings")){
//			advSearchHelper(title, author, editor, volume, publisher, isbn, year, result);
//		}
//		
//		if(type.toLowerCase().equals("proceedings")){
//			advSearchHelper(title, author, editor, volume, publisher, isbn, year, result);
//		}
//		
//		if(type.toLowerCase().equals("book")){
//			advSearchHelper(title, author, editor, volume, publisher, isbn, year, result);
//		}
//		
//		if(type.toLowerCase().equals("incollection")){
//			advSearchHelper(title, author, editor, volume, publisher, isbn, year, result);
//		}
//		
//		if(type.toLowerCase().equals("phdthesis")){
//			advSearchHelper(title, author, editor, volume, publisher, isbn, year, result);
//		}
//		
//		if(type.toLowerCase().equals("phdthesis")){
//			advSearchHelper(title, author, editor, volume, publisher, isbn, year, result);
//		}
//		
//		if(type.toLowerCase().equals("phdthesis")){
//			advSearchHelper(title, author, editor, volume, publisher, isbn, year, result);
//		}
//		
		return null;
	}
	
	private LinkedList<Listing> advSearchHelper(String title, String author, String editor, String volume, String publisher, String isbn, String year, LinkedList<Listing> results) {
		/*
		for (Publication p : this.db) {
			boolean flag = true;
			if(publicationType != "" && flag == true){
				if(!p.getPubType().toLowerCase().equals(publicationType.toLowerCase())){

					flag = false;
				}
			}
			if(title != "" && flag == true){
				System.out.print("title: " + title+ " == " + p.getTitle());
				if(!p.getTitle().toLowerCase().contains(title.toLowerCase())){

					flag = false;
				}
			}
			if(author != "" && flag == true){
				if(p.getAuthor() == null || !p.getAuthor().toLowerCase().contains(author.toLowerCase())){
				
					flag = false;
				}
			}
			if(editor != "" && flag == true){
				if(p.getEditor()== null || !p.getEditor().toLowerCase().contains(editor.toLowerCase())){
					flag = false;
				}
			}
			if(volume != "" && flag == true){
				if(p.getVolume()== null || !p.getVolume().toLowerCase().contains(volume.toLowerCase())){
					flag = false;
				}
			}
			if(publisher != null && flag == true){
				if(p.getPublisher()== null || !p.getPublisher().toLowerCase().contains(publisher.toLowerCase())){
					flag = false;
				}
			}
			if(isbn != "" && flag == true){
				if(p.getISBN() == null || !p.getISBN().toLowerCase().contains(isbn.toLowerCase())){
					flag = false;
				}
			}
			if(year != "" && flag == true){
				if(p.getYear()== null || !p.getYear().toLowerCase().contains(year.toLowerCase())){
					flag = false;
				}
			}
			if(flag == true){
				results.add(p);
			}	
		}
		*/
		return results;
	}
	private LinkedList<Listing> search(String searchQuery, String pubType, HttpServletRequest request){
		LinkedList<Listing> result = new LinkedList<Listing>();
		
		/*
		if(pubType.toLowerCase().equals("any")){
			for (Publication p : this.db) {
				if (p.getTitle().toLowerCase().contains(searchQuery.toLowerCase())|| (p.getAuthor() != null && p.getAuthor().toLowerCase().contains(searchQuery.toLowerCase()))){
					result.add(p);
				}
			}
		}
		
		if(pubType.toLowerCase().equals("article")){
			basicSearchHelper(searchQuery, pubType, result);
		}
		
		if(pubType.toLowerCase().equals("inproceedings")){
			basicSearchHelper(searchQuery, pubType, result);
		}
		
		if(pubType.toLowerCase().equals("proceedings")){
			basicSearchHelper(searchQuery, pubType, result);
		}
		
		if(pubType.toLowerCase().equals("book")){
			basicSearchHelper(searchQuery, pubType, result);
		}
		
		if(pubType.toLowerCase().equals("incollection")){
			basicSearchHelper(searchQuery, pubType, result);
		}
		
		if(pubType.toLowerCase().equals("phdthesis")){
			basicSearchHelper(searchQuery, pubType, result);
		}
		
		if(pubType.toLowerCase().equals("phdthesis")){
			basicSearchHelper(searchQuery, pubType, result);
		}
		
		if(pubType.toLowerCase().equals("phdthesis")){
			basicSearchHelper(searchQuery, pubType, result);
		}
		*/
		return result;
		
	}	
	private LinkedList<Listing> basicSearchHelper(String query, String pubType, LinkedList<Listing> results){
		/*
		for (Publication p : this.db) {
			if (p.getTitle().toLowerCase().contains(query.toLowerCase()) || (p.getAuthor() != null && p.getAuthor().toLowerCase().contains(query.toLowerCase()) && p.getPubType().toLowerCase().equals(pubType))){
				System.out.println(p.getAuthor());
				results.add(p);
			}
		}
		*/
		return results;
	}
	
}
