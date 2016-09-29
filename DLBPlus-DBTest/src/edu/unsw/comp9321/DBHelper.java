package edu.unsw.comp9321;

import java.security.SecureRandom;
import java.sql.*;
import java.util.*;
import java.util.Date;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Database helper class which abstracts database calls.
 */

public class DBHelper implements DLBPlusDBInterface {
	
	private static final String HOST = "localhost";
	private static final Integer PORT = 5432;
	private static final String dbName = "dlbplus";
	private static final String dbUser = "postgres";
	private static final String dbPass = "password";

	private Connection dbConn = null;
	public boolean dbConnStatus = false;

	/**
	 * Initiate connection to DB
	 */
	public boolean init() {
		if (dbConnStatus) //if already connected
			return true;

		try {
			Class.forName("org.postgresql.Driver");
			dbConn = DriverManager
							.getConnection("jdbc:postgresql://" + HOST + ":" + PORT + "/" + dbName,
											dbUser, dbPass);
			dbConnStatus = true;

			//Get total number of publications
		} catch (Exception e) {
			System.out.println(e);
			dbConnStatus = false;
		}

		return dbConnStatus;
	}

	/**
	 * Get a random publication
	 *
	 * @return Random publication or null if no publications exist (0 publications in db)
	 */
	public Publication GetRandomPublication() {
		if (!dbConnStatus) {
      this.PrintDebugMessage("GetRandomPublication", "No connection with database");
      return null;
    }

		try {
			Statement stmt;
			dbConn.setAutoCommit(false);
			stmt = dbConn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM publications OFFSET floor(random()*(SELECT COUNT(*) FROM publications)) LIMIT 1;" );

			return processResultSet(rs);
		}
		catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Get publication using ID
	 *
	 * @param pubID Publication identifier
	 * @return publication with matching publication identifier
	 */
	public Publication GetPublication(int pubID) {
		if (!dbConnStatus) {
      this.PrintDebugMessage("GetPublication", "No connection with database");
      return null;
    }

		try {
			Statement stmt;
			dbConn.setAutoCommit(false);
			stmt = dbConn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM publications where id = " + pubID + ";" );

			return processResultSet(rs);
		}
		catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Given a result set, parse next row of values into Publication
	 *
	 * @param rs result set for a query on the publications table
	 * @return null if no match found/rows exhausted, publication of next row otherwise
	 */
	private Publication processResultSet(ResultSet rs) {
		Publication p = new Publication();

		try {
			if (rs.next()) {
				Integer id = rs.getInt("id");
				String type = rs.getString("type");
				
				List<String> authors = (rs.getString("authors").equals("")) ? 
										new LinkedList<String>() 
										: new LinkedList<String>(Arrays.asList(rs.getString("authors").split("\\|")));

				List<String> editors = (rs.getString("editors").equals("")) ? 
						new LinkedList<String>() 
						: new LinkedList<String>(Arrays.asList(rs.getString("editors").split("\\|")));
				
				String title = rs.getString("title");
				String pages = rs.getString("pages");
				Integer year = rs.getInt("year");
				String address = rs.getString("address");
				String volume = rs.getString("volume");
				String number = rs.getString("number");
				String month = rs.getString("month");
				
				List<String> urls = (rs.getString("urls").contains("")) ? 
						new LinkedList<String>() 
						: new LinkedList<String>(Arrays.asList(rs.getString("urls").split("\\|")));
				
				List<String> ees = (rs.getString("ees").equals("")) ? 
						new LinkedList<String>() 
						: new LinkedList<String>(Arrays.asList(rs.getString("ees").split("\\|")));
				
				String cdrom = rs.getString("cdrom");
				
				List<String> cites = (rs.getString("cites") == null) ? 
						new LinkedList<String>() 
						: new LinkedList<String>(Arrays.asList(rs.getString("cites").split("\\|")));
				
				String publisher = rs.getString("publisher");
				String note = rs.getString("note");
				String crossref = rs.getString("crossref");
				
				List<String> isbns = (rs.getString("isbns") == null) ? 
						new LinkedList<String>() 
						: new LinkedList<String>(Arrays.asList(rs.getString("isbns").split("\\|")));
				
				String series = rs.getString("series");
				
				List<String> venues = (rs.getString("venues") == null) ? 
						new LinkedList<String>() 
						: new LinkedList<String>(Arrays.asList(rs.getString("venues").split("\\|")));
						
				String chapter = rs.getString("chapter");
				Double recprice = rs.getDouble("recPrice");
				String rating = rs.getString("rating");

				//Set publication fields
				p.setId(id);
				p.setType(type);

				for (String author : authors)
					p.setAuthor(author);

				for (String editor : editors)
					p.setEditor(editor);

				p.setTitle(title);
				p.setPages(pages);
				p.setYear(year);
				p.setAddress(address);
				p.setVolume(volume);
				p.setNumber(number);
				p.setMonth(month);

				for (String url : urls)
					p.setUrl(url);

				for (String ee : ees)
					p.setEe(ee);

				p.setCdrom(cdrom);

				for (String cite : cites)
					p.setCite(cite);

				p.setPublisher(publisher);
				p.setNote(note);
				p.setCrossref(crossref);

				for (String isbn : isbns)
					p.setIsbn(isbn);

				p.setSeries(series);

				for (String venue : venues)
					p.setVenue(venue);

				p.setChapter(chapter);
				p.setRecprice(recprice);
				p.setRating(rating);
				
				// Finalise publication
				p.finalise();
				
			} else { //No result found
				p = null;
			}
		} catch (SQLException e) {
			return null;
		}

		return p;
	}

	/**
	 * Create a user by inserting provided user details into database
	 *
	 * @param username Provided username
	 * @param plainTextPassword Unsalted Password
	 * @return User corresponding to successful insertion (null otherwise)
	 */
	public User CreateUser(String username, String plainTextPassword, String fname, String lname, String email,
												 String address, java.util.Date dob, String creditcard, String dp) {
		if (!dbConnStatus) {
      this.PrintDebugMessage("CreateUser", "No connection with database");
      return null;
    }

		try {
			if (!DoesUserExist(username)) {
        PrintDebugMessage("CreateUser", "User does not exist, creating new user with username: " + username);
        
				//Generate random salt and hashed password
				final Random r = new SecureRandom();
				byte[] salt = new byte[32];
				r.nextBytes(salt);
				String encodedSalt = Base64.encode(salt);
				String passwordHash = DigestUtils.sha1Hex(encodedSalt + plainTextPassword);

        //Current date for acctcreated
        Date now = new Date();
        
				Statement stmt;
				dbConn.setAutoCommit(false);
				stmt = dbConn.createStatement();
				String q = "INSERT INTO users (username, salt, password, fname, lname, email, address, dob, creditcard, dp, acctstatus, acctconfrm, acctcreated) " +
								"VALUES ('" + username + "', '" + encodedSalt + "', '" + passwordHash + "', '" + fname + "', '" + lname + "', '" + email + "', '" + address + "', '" + dob.toString() + "', '" + creditcard + "', '" + dp + "', true, false, '" + now.toString() + "');";
        PrintDebugMessage("CreateUser", "Running query: " + q);
				stmt.executeUpdate(q);
				dbConn.commit();
				return GetUser(username);
			} else { //User already exists
        PrintDebugMessage("CreateUser", "Error! User already exists with username: " + username);
				return null;
			}
		}
		catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Checks whether a username is already associated with a user  
	 *
	 * @param username Username to check
	 * @return boolean True for exists, False otherwise
	 */
	public boolean DoesUserExist(String username) {
		if (!dbConnStatus) {
      this.PrintDebugMessage("DoesUserExist", "No connection with database");
      return true;
    }

		try {
			Statement stmt;
			dbConn.setAutoCommit(false);
			stmt = dbConn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM users where username = '" + username + "';" );

			if (rs.next()) {
				int count = rs.getInt("count");
				if (count == 0)
					return false;
				else
					return true;
			}

			return true; //this should never be returned
		}
		catch (SQLException e) {
			return true; //this should never be returned
		}

	}
  
  
  /**
   * Checks whether a username is already associated with a user
   *
   * @param userID user id of user
   * @return boolean True for exists, False otherwise
   */
  public boolean DoesUserExist(int userID) {
    if (!dbConnStatus) {
      this.PrintDebugMessage("DoesUserExist", "No connection with database");
      return true;
    }
    
    try {
      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM users where id = '" + userID + "';" );
      
      if (rs.next()) {
        int count = rs.getInt("count");
        if (count == 0)
          return false;
        else
          return true;
      }
      
      return true; //this should never be returned
    }
    catch (SQLException e) {
      return true; //this should never be returned
    }
  }
  
  /**
   * Changes the details of a user
   * @param changedUser The User object that contains all the information to change
   * @return True whether user was changed successfully, false otherwise
   */
  public boolean ChangeUserDetails(User changedUser) {
    if (!dbConnStatus) {
      this.PrintDebugMessage("ChangeUserDetails", "No connection with database");
      return false;
    }
    
    try {
      if (DoesUserExist(changedUser.getId())) { //user exists
        Statement stmt;
        dbConn.setAutoCommit(false);
        stmt = dbConn.createStatement();
        String q =  "UPDATE users" +
                    " SET username = '" + changedUser.getUsername() + //change username
                    "', fname = '" + changedUser.getFname() +
                    "', lname = '" + changedUser.getLname() +
                    "', email = '" + changedUser.getEmail() +
                    "', address = '" + changedUser.getAddress() +
                    "', dob = '" + changedUser.getDob() +
                    "', creditcard = '" + changedUser.getCreditcard() +
                    "', cartid = " + changedUser.getCartid() +
                    ", dp = '" + changedUser.getDp() +
                    "', acctstatus = " + changedUser.getAcctstatus() +
                    ", acctconfrm = " + changedUser.getAcctconfrm() +
                    ", acctcreated = '" + changedUser.getAcctcreated() +
                    "' WHERE id = " + changedUser.getId() + ";";
        this.PrintDebugMessage("ChangeUserDetails", "Running query: " + q);
        stmt.executeUpdate(q);
        dbConn.commit();
        return true;
      }
      else {
        PrintDebugMessage("ChangeUserDetails", "Error! No user exists with username: " + changedUser.getUsername());
        return false;
      }
    }
    catch (SQLException e) {
      return false;
    }
  }
  
  /**
   * Validate a user
   *
   * @param inputUsername the username of user
   * @param inputPwd plaintext password for user
   * @return boolean True when user is verifed, False otherwise
   */
	public boolean VerifyUser(String inputUsername, String inputPwd) {
    if (!dbConnStatus) {
      this.PrintDebugMessage("VerifyUser", "No connection with database");
      return false;
    }
    
    try {
      if (DoesUserExist(inputUsername)) { //user exists
        
        //Get salt + hash from database
        Statement stmt;
        dbConn.setAutoCommit(false);
        stmt = dbConn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT salt, password FROM users where username = '" + inputUsername + "';" );
        
        if (rs.next()) {
          String salt = rs.getString("salt");
          String storePasswordHash = rs.getString("password");
          
          //Create testHash to compare
          String testHash = DigestUtils.sha1Hex(salt + inputPwd);
          
          //Perform comparison
          //Valid input password
          return testHash.equals(storePasswordHash);
        } else {
          //Should never happen
          return false;
        }
      } else {
        PrintDebugMessage("VerifyUser", "Error! No user exists with username: " + inputUsername);
        return false;
      }
    }
    catch (SQLException e) {
      return false;
    }
	}
  
  /**
   * Sets the account confirmed status to a new value
   *
   * @param user the user account
   * @param confirmedStatus the new status to change to
   * @return True when successfully changed, False otherwise
   */
  public boolean SetAcctConfirmed(User user, boolean confirmedStatus) {
    if (!dbConnStatus) {
      this.PrintDebugMessage("SetAcctConfirmed", "No connection with database");
      return false;
    }
  
    try {
      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      stmt.executeUpdate("UPDATE users SET acctconfrm = " + confirmedStatus + " WHERE id = " + user.getId() + ";" );
      dbConn.commit();
      user.setAcctconfrm(confirmedStatus); //update local object
      return true;
    }
    catch (SQLException e) {
      return false;
    }
  }
	
	/**
	 * Create a listing (item for sale)
	 *
	 * @return Listing Null if listing unsuccessful, Listing of newly created listing otherwise
	 */
	 public Listing CreateListing(User seller, Publication item, Integer quantity, Timestamp listdate, Timestamp enddate,
                                Double sellprice, String image) {
     if (!dbConnStatus) {
       this.PrintDebugMessage("CreateListing", "No connection with database");
       return null;
     }
    
     if (seller == null || item == null) {
       PrintDebugMessage("CreateListing", "Error! Seller or item is null");
       return null;
     }
     
     try {
       Statement stmt;
       dbConn.setAutoCommit(false);
       stmt = dbConn.createStatement();
       String q = "INSERT INTO listings (sellerid, itemid, quantity, listdate, enddate, sellprice, image) " +
         "VALUES (" + seller.getId() + ", " + item.getId() + ", " + quantity + ", to_timestamp(" + listdate.getTime() + "), to_timestamp(" + enddate.getTime() + "), " + sellprice + ", '" + image + "')" +
         "RETURNING listingid;";
       PrintDebugMessage("CreateListing", "Running query: " + q);
       ResultSet rs = stmt.executeQuery(q);
       
       if (rs.next()) {
         Integer listingid = rs.getInt("listingid");
         dbConn.commit();
         return GetListing(listingid);
       } else {
         return null;
       }
     }
     catch (SQLException e) {
       return null;
     }
	 }
	 
	/**
	 * Set the listing's paused status to be true or false
	 *
	 * @param listing the listing to modify paused status
   * @param paused the new value for paused
	 * @return boolean True when paused was successfully set. False otherwise
	 */
	 public boolean SetPausedStatus(Listing listing, boolean paused) {
     if (!dbConnStatus) {
       this.PrintDebugMessage("SetPausedStatus", "No connection with database");
       return false;
     }
    
     try {
       Statement stmt;
       dbConn.setAutoCommit(false);
       stmt = dbConn.createStatement();
       stmt.executeUpdate("UPDATE listings SET paused = " + paused + " WHERE listingid = " + listing.getListingid() + ";" );
       dbConn.commit();
       listing.setPaused(paused); //update local object
       return true;
     }
     catch (SQLException e) {
       return false;
     }
	 }
	 
	/**
	 * Obtain a list of publications (FOR SALE) that match the search queries
	 *
	 * @param queries contains key-value pair of query and value
	 * @return List of publications (empty if no results found)
	 */	 
	public List<Publication> SearchPublications(HashMap<String, String> queries) {
		List<Publication> results = new ArrayList<Publication>();
		// TODO
		return results;
	}

	/**
	 * Add a listing to a user's cart
	 *
	 * @param user contains the cartid of the user
	 * @param listingToAdd contains the listing to add into the user's cart
	 * @return boolean whether the update was successful
	 */	
	public boolean AddToCart(User user, Listing listingToAdd) {
		return true;
	}
	
	/**
	 * Remove a particular listing from a user's cart
	 *
	 * @param user contains the cartid of the user
	 * @param listingID	the ID of the listing to remove
	 * @return boolean True when removal was successful
	 */		
	public boolean RemoveFromCart(User user, int listingID) {
		//TODO
		return true;
	}
	
	/**
	 * Obtain a user
	 *
	 * @param userID the id of the user
	 * @return returns a user when successful, null otherwise
	 */	
	public User GetUser(int userID) {
    if (!dbConnStatus) {
      this.PrintDebugMessage("GetUser", "No connection with database");
      return null;
    }
    
    try {
      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM users where id = " + userID + ";" );
      
      return processResultSetIntoUser(rs);
    }
    catch (SQLException e) {
      return null;
    }
	}
  
  /**
   * Obtain a user
   *
   * @param username the username of the user
   * @return returns a user when successful, null otherwise
   */
  public User GetUser(String username) {
    if (!dbConnStatus) {
      this.PrintDebugMessage("GetUser", "No connection with database");
      return null;
    }
    
    try {
      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM users where username = '" + username + "';" );
      
      return processResultSetIntoUser(rs);
    }
    catch (SQLException e) {
      return null;
    }
  }
  
  /**
   * Given a result set, parse next row of values into User
   *
   * @param rs result set for a query on the user table
   * @return null if no match found/rows exhausted, user of next row otherwise
   */
  private User processResultSetIntoUser(ResultSet rs) {
    User u = new User();
    
    try {
      if (rs.next()) {
        Integer id = rs.getInt("id");
        String username = rs.getString("username");
        String fname = rs.getString("fname");
        String lname = rs.getString("lname");
        String email = rs.getString("email");
        String address = rs.getString("address");
        Date dob = rs.getDate("dob");
        String creditcard = rs.getString("creditcard");
        Integer cartid = rs.getInt("cartid");
        String dp = rs.getString("dp");
        Boolean acctstatus = rs.getBoolean("acctstatus");
        Boolean acctconfrm = rs.getBoolean("acctconfrm");
        Date acctcreated = rs.getDate("acctcreated");
        
        //Set User fields
        u.setId(id);
        u.setUsername(username);
        u.setFname(fname);
        u.setLname(lname);
        u.setEmail(email);
        u.setAddress(address);
        u.setDob(dob);
        u.setCreditcard(creditcard);
        u.setCartid(cartid);
        u.setDp(dp);
        u.setAcctstatus(acctstatus);
        u.setAcctconfrm(acctconfrm);
        u.setAcctcreated(acctcreated);
      } else { //No result found
        u = null;
      }
    } catch (SQLException e) {
      return null;
    }
    
    return u;
  }
  
	/**
	 * Obtain a list of all existing listings
	 *
	 * @return returns a list of listings
	 */		
	public List<Listing> GetAllListings() {
    List<Listing> allListings = new ArrayList<Listing>();
    
    if (!dbConnStatus) {
      this.PrintDebugMessage("GetAllListings", "No connection with database");
      return allListings;
    }
    
    try {
      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM listings;");
      
      Listing l = processResultSetIntoListing(rs);
      
      while (l != null) {
        allListings.add(l);
        l = processResultSetIntoListing(rs);
      }
    }
    catch (SQLException e) {
      return allListings;
    }
    
    return allListings;
	}
	
	/**
	 * Obtain a list of all users
	 *
	 * @return returns a list of all existing users, regardless of account status, empty list otherwise
	 */		
	public List<User> GetAllUsers() {
    List<User> allUsers = new ArrayList<User>();
    
    if (!dbConnStatus) {
      this.PrintDebugMessage("GetAllUsers", "No connection with database");
      return allUsers;
    }
    
    try {
      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM users;");
  
      User u = processResultSetIntoUser(rs);
      
      while (u != null) {
        allUsers.add(u);
        u = processResultSetIntoUser(rs);
      }
    }
    catch (SQLException e) {
      return allUsers;
    }
    
		return allUsers;
	}
	
	/**
	 * Obtain all active cart items in a given cart
	 *
	 * @param cartID the id of the cart
	 * @return returns a list of Cart Items
	 */	
	public List<CartItem> GetActiveCartItems(int cartID) {
		List<CartItem> cartItems = new ArrayList<CartItem>();
		//TODO
		return cartItems;
	}

	/**
	 * Obtain all removed cart items in a given cart
	 *
	 * @param cartID the cart of id
	 * @return returns a list of cart items that have been removed
	 */	
	public List<CartItem> GetRemovedCartItems(int cartID) {
		List<CartItem> removedCartItems = new ArrayList<CartItem>();
		//TODO
		return removedCartItems;
	}

	/**
	 * Obtain the order history of a particular user
	 *
	 * @param userID the id of the user
	 * @return returns a list of orders that the user has made
	 **/	
	public List<Order> GetOrderHistory(int userID) {
		List<Order> orderHistory = new ArrayList<Order>();
		//TODO
		return orderHistory;
	}

	 /**
	 * Obtain a random listing
	 *
	 * @return returns a listing if there is at least one in DB; null otherwise
	 */
	public Listing GetRandomListing() {
    if (!dbConnStatus) {
      this.PrintDebugMessage("GetRandomListing", "No connection with database");
      return null;
    }
    
    try {
      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM listings OFFSET floor(random()*(SELECT COUNT(*) FROM listings)) LIMIT 1;" );
      
      return processResultSetIntoListing(rs);
    }
    catch (SQLException e) {
      return null;
    }
	}

	 /**
	 * Obtain a particular listing
	 *
	 * @param listingID the id of the listing to obtain
	 * @return the listing corresponding to given ID; null if such a listing doesn't exist
	 */
	public Listing GetListing(int listingID) {
    if (!dbConnStatus) {
      this.PrintDebugMessage("GetListing", "No connection with database");
      return null;
    }
    
    try {
      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM listings where listingid = " + listingID + ";" );
      
      return processResultSetIntoListing(rs);
    }
    catch (SQLException e) {
      return null;
    }
	}
  
  /**
   * Given a result set, parse next row of values into Listing
   *
   * @param rs result set for a query on the listing table
   * @return null if no match found/rows exhausted, listing of next row otherwise
   */
  private Listing processResultSetIntoListing(ResultSet rs) {
    Listing l = new Listing();
    
    try {
      if (rs.next()) {
        Integer listingid = rs.getInt("listingid");
        Integer sellerid = rs.getInt("sellerid");
        Integer itemid = rs.getInt("itemid");
        Integer quantity = rs.getInt("quantity");
        Timestamp listdate = rs.getTimestamp("listdate");
        Timestamp enddate = rs.getTimestamp("enddate");
        Double sellprice = rs.getDouble("sellprice");
        String image = rs.getString("image");
        Boolean paused = rs.getBoolean("paused");
        Integer numviews = rs.getInt("numviews");
        
        //Set User fields
        l.setListingid(listingid);
        l.setSellerid(sellerid);
        l.setItemid(itemid);
        l.setQuantity(quantity);
        l.setListdate(listdate);
        l.setEnddate(enddate);
        l.setSellprice(sellprice);
        l.setImage(image);
        l.setPaused(paused);
        l.setNumviews(numviews);
        
      } else { //No result found
        l = null;
      }
    } catch (SQLException e) {
      return null;
    }
    
    return l;
  }
  
  /**
   * Obtain a list of listings by a particular user
   *
   * @return returns a list of listings
   */
  public List<Listing> GetUserListings(int userID) {
    List<Listing> userListings = new ArrayList<Listing>();
  
    if (!dbConnStatus) {
      this.PrintDebugMessage("GetUserListings", "No connection with database");
      return userListings;
    }
  
    try {
      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM listings WHERE sellerid =" + userID + ";");
    
      Listing l = processResultSetIntoListing(rs);
    
      while (l != null) {
        userListings.add(l);
        l = processResultSetIntoListing(rs);
      }
    }
    catch (SQLException e) {
      return userListings;
    }
  
    return userListings;
  }

	 /**
	 * Increments the number of views on a particular listing
	 *
	 * @param listing the listing being changed
   * @return true if successful, false otherwise
	 */
	public boolean IncrementListingViews(Listing listing) {
    if (!dbConnStatus) {
      this.PrintDebugMessage("IncrementListingViews", "No connection with database");
      return false;
    }
    
    try {
      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      stmt.executeUpdate("UPDATE listings SET numviews = numviews + 1 WHERE listingid = " + listing.getListingid() + ";" );
      dbConn.commit();
      listing.setNumviews(listing.getNumviews() + 1); //update local object
      return true;
    }
    catch (SQLException e) {
      return false;
    }
	}
  
  /**
   * Helper function used to print out debug messages
   *
   * @param function name of function in which message originates from
   * @param message the message (error, information etc)
   */
	private void PrintDebugMessage(String function, String message) {
    System.out.println(function + ": " + message);
  }
  
  /**
   * Return the total number of users
   *
   * @return the total number of users, -1 on error
   */
  public int GetNumUsers() {
    if (!dbConnStatus) {
      this.PrintDebugMessage("GetNumUsers", "No connection with database");
      return -1;
    }
  
    try {
      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM users;" );
    
      if (rs.next()) {
        int count = rs.getInt("count");
        return count;
      }
    
      return -1; //This should never be returned
    
    } catch (SQLException e) {
      return -1;
    }
	}
  
  /**
   * Obtain a specific range of users (inclusive)
   *
   * @param startIndex the starting index (must be 0 - (numusers - 1))
   * @param endIndex the ending index (must be 0-numusers and >= startIndex)
   * @return returns a list of users in specified range
   */
  public List<User> GetUsers(int startIndex, int endIndex) {
    List<User> users = new ArrayList<User>();
    int offset = startIndex;
    int limit = endIndex - startIndex + 1; //number of results (if enough exist)
  
    if (startIndex > endIndex) {
      PrintDebugMessage("GetUsers", "End index is smaller than start index.");
      return users;
    }
  
    if (startIndex < 0 || endIndex < 0) {
      PrintDebugMessage("GetUsers", "Start or End indexes cannot be negative.");
      return users;
    }
  
    if (!dbConnStatus) {
      this.PrintDebugMessage("GetUsers", "No connection with database");
      return users;
    }
  
    try {
      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM users ORDER BY id ASC OFFSET " + offset + " LIMIT " + limit + ";");
    
      User u = processResultSetIntoUser(rs);
    
      while (u != null) {
        users.add(u);
        u = processResultSetIntoUser(rs);
      }
    }
    catch (SQLException e) {
      return users;
    }
  
    return users;
  }
	
	@Override
	public boolean RemoveUser(int userID) {
		// TODO Auto-generated method stub
		return false;
	}
  
  /**
   * Change the account status of a user
   *
   * @param user the user to change
   * @param newStatus the new status to change to
   * @return boolean True when status is changed, False otherwise
   */
  public boolean SetUserStatus(User user, boolean newStatus) {
    if (!dbConnStatus) {
      this.PrintDebugMessage("SetUserStatus", "No connection with database");
      return false;
    }
  
    try {
      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      stmt.executeUpdate("UPDATE users SET acctstatus = " + newStatus + " WHERE id = " + user.getId() + ";" );
      dbConn.commit();
      user.setAcctstatus(newStatus); //update local object
      return true;
    }
    catch (SQLException e) {
      return false;
    }
  }
	
	@Override
	public boolean RemoveListing(int listingID) {
		// TODO Auto-generated method stub
		return false;
	}
  
  /**
   * Return the total number of listings
   *
   * @return the total number of listings, or -1 on error
   */
	public int GetNumListings() {
    if (!dbConnStatus) {
      this.PrintDebugMessage("GetNumListings", "No connection with database");
      return -1;
    }
    
    try {
      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM listings;" );
  
      if (rs.next()) {
        int count = rs.getInt("count");
        return count;
      }
      
      return -1; //This should never be returned
      
    } catch (SQLException e) {
      return -1;
    }
	}
  
  /**
   * Obtain a specific range of listings (inclusive)
   *
   * @param startIndex the starting index (must be 0 - (numlistings - 1))
   * @param endIndex the ending index (must be 0-numlistings and >= startIndex)
   * @return returns a list of listings in specified range or empty list
   */
  public List<Listing> GetListings(int startIndex, int endIndex) {
    List<Listing> listings = new ArrayList<Listing>();
    int offset = startIndex;
    int limit = endIndex - startIndex + 1; //number of results (if enough exist)
    
    if (startIndex > endIndex) {
      PrintDebugMessage("GetListings", "End index is smaller than start index.");
      return listings;
    }
    
    if (startIndex < 0 || endIndex < 0) {
      PrintDebugMessage("GetListings", "Start or End indexes cannot be negative.");
      return listings;
    }
    
    if (!dbConnStatus) {
      this.PrintDebugMessage("GetListings", "No connection with database");
      return listings;
    }
  
    try {
      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM listings ORDER BY listingid ASC OFFSET " + offset + " LIMIT " + limit + ";");
    
      Listing l = processResultSetIntoListing(rs);
    
      while (l != null) {
        listings.add(l);
        l = processResultSetIntoListing(rs);
      }
    }
    catch (SQLException e) {
      return listings;
    }
  
    return listings;
	}
	
	/**
	 * Create an admin
	 *
	 * @param username the username of the new admin
	 * @param plainTextPassword plaintext password for new admin
	 * @return returns an Admin object when successfully created, otherwise
	 */		
	public Admin CreateAdmin(String username, String plainTextPassword) {
		if (!this.dbConnStatus) {
			this.PrintDebugMessage("CreateAdmin", "No connection with database");
			return null;
		}
		
		try {
			if (!DoesAdminExist(username)) {
				
				//Generate random salt and hashed password
				final Random r = new SecureRandom();
				byte[] salt = new byte[32];
				r.nextBytes(salt);
				String encodedSalt = Base64.encode(salt);
				String passwordHash = DigestUtils.sha1Hex(encodedSalt + plainTextPassword);
	    
				// Prepare insert statement
				Statement stmt;
				dbConn.setAutoCommit(false);
				stmt = dbConn.createStatement();
				String q = "INSERT INTO admins (username, salt, password) " +
						"VALUES ('" + username + "', '" + encodedSalt + "', '" + passwordHash + "')";
				PrintDebugMessage("CreateAdmin", "Running query: " + q);
				stmt.executeUpdate(q);
				dbConn.commit();
				
				// Return the newly created admin
				return this.GetAdmin(username);
				
			} else {
				this.PrintDebugMessage("CreateAdmin", "Error: Admin with username " + username + " already exists.");
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Get an admin
	 * 
	 * @param username the username of the admin
	 * @return returns an Admin object if succesfully retrieved, null otherwise
	 */
	public Admin GetAdmin(String username) {
		if (!dbConnStatus) {
			this.PrintDebugMessage("GetAdmin", "No connection with database");
			return null;
		}
		  
		try {
			Statement stmt;
			dbConn.setAutoCommit(false);
			stmt = dbConn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM admins where username = '" + username + "';" );
    
			return processResultSetIntoAdmin(rs);
			
		} catch (SQLException e) {
			return null;
		}
	}
	
	/**
	* Validate an admin
	*
	* @param inputUsername the username of admin
	* @param inputPwd plaintext password for admin
	* @return boolean True when admin is verifed, False otherwise
	*/
	public boolean VerifyAdmin(String inputUsername, String inputPwd) {

		if (!dbConnStatus) {
			this.PrintDebugMessage("VerifyAdmin", "No connection with database");
			return false;
		}
	      
		try {
			if (DoesAdminExist(inputUsername)) { //admin exists
      
				//Get salt + hash from database
				Statement stmt;
				dbConn.setAutoCommit(false);
				stmt = dbConn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT salt, password FROM admins where username = '" + inputUsername + "';" );
      
				if (rs.next()) {
					String salt = rs.getString("salt");
					String storePasswordHash = rs.getString("password");
        
					//Create testHash to compare
					String testHash = DigestUtils.sha1Hex(salt + inputPwd);
        
					//Perform comparison
					//Valid input password
					return testHash.equals(storePasswordHash);
				} else {
					//Should never happen
					return false;
				}
			} else {
				PrintDebugMessage("VerifyAdmin", "Error! No admin exists with username: " + inputUsername);
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	
	/**
	 * Checks whether an admin with given username exists
	 *
	 * @param username Username to check
	 * @return boolean True for exists, False otherwise
	 */
	public boolean DoesAdminExist(String username) {
		if (!dbConnStatus) {
      this.PrintDebugMessage("DoesAdminExist", "No connection with database");
      return true;
    }

		try {
			Statement stmt;
			dbConn.setAutoCommit(false);
			stmt = dbConn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM admins where username = '" + username + "';" );

			if (rs.next()) {
				int count = rs.getInt("count");
				if (count == 0)
					return false;
				else
					return true;
			}

			return true; //this should never be returned
		}
		catch (SQLException e) {
			return true; //this should never be returned
		}
	}
	
	/**
	* Given a result set, parse next row of values into User
	*
	* @param rs result set for a query on the user table
	* @return null if no match found/rows exhausted, user of next row otherwise
	*/
	private Admin processResultSetIntoAdmin(ResultSet rs) {
		Admin a = new Admin();
    
	    try {
	    	if (rs.next()) {
	    		Integer id = rs.getInt("id");
	    		String username = rs.getString("username");
        
	    		//Set User fields
	    		a.setId(id);
	    		a.setUsername(username);

	    	} else { //No result found
	    		a = null;
	    	}
	    } catch (SQLException e) {
	    	return null;
	    }
    
	    return a;
  }

}