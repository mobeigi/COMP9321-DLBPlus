package edu.unsw.comp9321;

import java.security.SecureRandom;
import java.sql.*;
import java.util.*;

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
		if (!dbConnStatus)
			return null;

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
		if (!dbConnStatus)
			return null;

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
	 * Create a user by inserting provideduser details into database
	 *
	 * @param username Provided username
	 * @param plainTextPassword Unsalted Password
	 * @return User corresponding to successful insertion (null otherwise)
	 */
	public User CreateUser(String username, String plainTextPassword, String fname, String lname, String email,
												 String address, java.util.Date dob, String creditcard, String dp) {
		if (!dbConnStatus)
			return null;

		try {
			if (!doesUserExist(username)) {
				System.out.println("User does not exist, creating new user");


				//Generate random salt and hashed password
				final Random r = new SecureRandom();
				byte[] salt = new byte[32];
				r.nextBytes(salt);
				String encodedSalt = Base64.encode(salt);
				String passwordHash = DigestUtils.sha1Hex(encodedSalt + plainTextPassword);

				Statement stmt;
				dbConn.setAutoCommit(false);
				stmt = dbConn.createStatement();
				String q = "INSERT INTO users (username, salt, password, fname, lname, email, address, dob, creditcard, cartid, dp, acctstatus)" +
								"VALUES ('" + username + "', '" + encodedSalt + "', '" + passwordHash + "', '" + fname + "', '" + lname + "', '" + email + "', '" + address + "', '" + dob.toString() + "', '" + creditcard + "', '" + dp + "', true);";
				System.out.println(q);
				stmt.executeUpdate(q);
				dbConn.commit();
				return null; //todo: change to return user GetUser
			} else { //User already exists
				System.out.println("User already exists");
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
	public boolean doesUserExist(String username) {
		if (!dbConnStatus)
			return true; //this should never be returned

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
	 * Validate a user
	 *
	 * @param inputUsername 
	 * @param inputPwd
	 * @return boolean True when user is verifed, False otherwise
	 */
	public boolean VerifyUser(String inputUsername, String inputPwd) {
		// TODO
		return false;
	}
	
	/**
	 * Validate an admin
	 *
	 * @param inputUsername 
	 * @param inputPwd
	 * @return boolean True when admin is verifed, False otherwise
	 */
	public boolean VerifyAdmin(String inputUsername, String inputPwd) {
		// TODO
		return false;
	}
	
	/**
	 * Create a listing (item for sale)
	 *
	 * @param newListing The new listing to be added\
	 * @return boolean True when listing was successfully created; false otherwise
	 */
	 public boolean CreateListing(Listing newListing) {
		// TODO
		return true;
	 }
	 
	/**
	 * Set the listing's paused status to be true or false
	 *
	 * @param listing the listing to modify paused status
	 * @return boolean True when paused was succesfully set. False otherwise
	 */
	 public boolean SetPausedStatus(int listingID, boolean paused) {
		 return true;
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
	 * @param userid the id of the user
	 * @return returns a user when successful, null otherwise
	 */	
	public User GetUser(int userID) {
		// TODO
		return null;
	}
	
	/**
	 * Obtain a list of all existing listings
	 *
	 * @return returns a list of listings
	 */		
	public List<Listing> GetAllListings() {
		List<Listing> allListings = new ArrayList<Listing>();
		//TODO
		return allListings;
	}
	
	/**
	 * Obtain a list of all users
	 *
	 * @return returns a list of all existing users, regardless of account status
	 */		
	public List<User> GetAllUsers() {
		List<User> allUsers = new ArrayList<User>();
		//TODO
		return allUsers;
	}
	
	/**
	 * Obtain all active cart items in a given cart
	 *
	 * @param cartid the id of the cart
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
	 * @param cart id the cart of id
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
	 * @param userid the id of the user
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
		// TODO Auto-generated method stub
		return null;
	}

	 /**
	 * Obtain a particular listing
	 *
	 * @param listingID the id of the listing to obtain
	 * @return the listing corresponding to given ID; null if such a listing doesn't exist
	 */
	public Listing GetListing(int listingID) {
		// TODO Auto-generated method stub
		return null;
	}

	 /**
	 * Incremements the number of views on a particular listing
	 *
	 * @param listingID the id of the listing to edit
	 */
	public void IncrementListingViews(int listingID) {
		// TODO Auto-generated method stub
		
	}

}