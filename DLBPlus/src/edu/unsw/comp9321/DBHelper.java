package edu.unsw.comp9321;

import java.security.SecureRandom;
import java.sql.*;
import java.util.*;
import java.util.Date;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

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
   * Close connection to DB
   */
  public boolean close() {
    if (!dbConnStatus) //if not connected
      return true;
    
    try {
      dbConn.close();
      dbConnStatus = false;
    } catch (Exception e) {
      System.out.println(e);
    }
    
    return dbConnStatus;
  }

	/**
	 * Create a user by inserting provided user details into database
	 *
	 * @param username Provided username
	 * @param plainTextPassword Unsalted Password
	 * @return User corresponding to successful insertion (null otherwise)
	 */
	public User CreateUser(String username, String plainTextPassword, String fname, String lname, String nickname, String email,
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
				String q = "INSERT INTO users (username, salt, password, fname, lname, nickname, email, address, dob, creditcard, dp, acctstatus, acctconfrm, acctcreated) " +
								"VALUES ('" + username + "', '" + encodedSalt + "', '" + passwordHash + "', '" + fname + "', '" + lname + "', '" + nickname + "', '" + email + "', '" + address + "', '" + dob.toString() + "', '" + creditcard + "', '" + dp + "', true, false, '" + now.toString() + "');";
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
				System.out.println("count: " + count);
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
   * Changes the details of a user based on their user id (which cannot be changed).
   *
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
        User u = GetUser(changedUser.getId());
        
        if (!u.getUsername().equals(changedUser.getUsername())) { //If username is being changed
          if (DoesUserExist(changedUser.getUsername())) { //but username is already taken
            this.PrintDebugMessage("ChangeUserDetails", "Changed username is already taken.");
            return false;
          }
        }
        
        Statement stmt;
        dbConn.setAutoCommit(false);
        stmt = dbConn.createStatement();
        String q =  "UPDATE users" +
                    " SET username = '" + changedUser.getUsername() + //change username
                    "', fname = '" + changedUser.getFname() +
                    "', lname = '" + changedUser.getLname() +
								    "', nickname = '" + changedUser.getNickname() +
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
	 * Change users stored password including salt.
	 *
	 * @param user the user being changed
	 * @param plainTextPassword the new password in plain text
	 * @return true if password successfully changed, false otherwise
	 */
	public boolean ChangeUserPassword(User user, String plainTextPassword) {
		if (!dbConnStatus) {
			this.PrintDebugMessage("ChangeUserPassword", "No connection with database");
			return false;
		}

		try {
			if (DoesUserExist(user.getId())) {

				//Generate random salt and hashed password
				final Random r = new SecureRandom();
				byte[] salt = new byte[32];
				r.nextBytes(salt);
				String encodedSalt = Base64.encode(salt);
				String passwordHash = DigestUtils.sha1Hex(encodedSalt + plainTextPassword);

				Statement stmt;
				dbConn.setAutoCommit(false);
				stmt = dbConn.createStatement();
				String q = "UPDATE users " +
									 "SET salt='" + encodedSalt + "', " +
								   "password='" + passwordHash + "' " +
								   "WHERE id = " + user.getId() + ";";
				PrintDebugMessage("ChangeUserPassword", "Running query: " + q);
				stmt.executeUpdate(q);
				dbConn.commit();
				return true;
			} else { //User already exists
				PrintDebugMessage("ChangeUserPassword", "Error! User does not exist");
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
	 public Listing CreateListing(User seller, Integer quantity, Timestamp listdate, Timestamp enddate, Double sellprice, String image,
                               Listing.Type type, List<String> authors, List<String> editors, String title, List<String> venues,
                               String pages, Integer year, String address, String volume, String number, String month, List<String> urls,
                               List<String> ees, String cdrom, List<String> cites, String publisher, String note, String crossref,
                               List<String> isbns, String series, String chapter, String rating) {
    if (!dbConnStatus) {
      this.PrintDebugMessage("CreateListing", "No connection with database");
      return null;
    }
    
    if (seller == null) {
      PrintDebugMessage("CreateListing", "Error! Seller or item is null");
      return null;
    }
    
    try {
      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      
      String q = "INSERT INTO listings (";
      List<String> inserts = new ArrayList<String>();
      List<String> insertsValues = new ArrayList<String>();
      
      //Go through inputs
      inserts.add("sellerid");
      insertsValues.add(seller.getId().toString());
      
      if (quantity != null) {
        inserts.add("quantity");
        insertsValues.add(quantity.toString());
      }
  
      if (listdate != null) {
        inserts.add("listdate");
        insertsValues.add("to_timestamp(" + listdate.getTime()/1000 + ")");
      }
  
      if (enddate != null) {
        inserts.add("enddate");
        insertsValues.add("to_timestamp(" + enddate.getTime()/1000 + ")");
      }
  
      if (sellprice != null) {
        inserts.add("sellprice");
        insertsValues.add(sellprice.toString());
      }
  
      if (image != null) {
        inserts.add("image");
        insertsValues.add("'" + image + "'");
      }
  
      if (type != null) {
        inserts.add("type");
        insertsValues.add("'" + type.toString().toLowerCase() + "'");
      }
  
      if (authors != null && !authors.isEmpty()) {
        inserts.add("authors");
        insertsValues.add("'" + StringUtils.join(authors , "|") + "'");
      }
  
      if (editors != null && !editors.isEmpty()) {
        inserts.add("editors");
        insertsValues.add("'" + StringUtils.join(editors , "|") + "'");
      }
  
      if (title != null) {
        inserts.add("title");
        insertsValues.add("'" + title + "'");
      }
  
      if (venues != null && !venues.isEmpty()) {
        inserts.add("venues");
        insertsValues.add("'" + StringUtils.join(venues , "|") + "'");
      }
  
      if (pages != null) {
        inserts.add("pages");
        insertsValues.add("'" + pages + "'");
      }
  
      if (year != null) {
        inserts.add("year");
        insertsValues.add(year.toString());
      }
  
      if (address != null) {
        inserts.add("address");
        insertsValues.add("'" + address + "'");
      }
  
      if (volume != null) {
        inserts.add("volume");
        insertsValues.add("'" + volume + "'");
      }
      
      if (number != null) {
        inserts.add("number");
        insertsValues.add("'" + number + "'");
      }
  
      if (month != null) {
        inserts.add("month");
        insertsValues.add("'" + month + "'");
      }
  
      if (urls != null && !urls.isEmpty()) {
        inserts.add("urls");
        insertsValues.add("'" + StringUtils.join(urls , "|") + "'");
      }
  
      if (ees != null && !ees.isEmpty()) {
        inserts.add("ees");
        insertsValues.add("'" + StringUtils.join(ees , "|") + "'");
      }
  
      if (cdrom != null) {
        inserts.add("cdrom");
        insertsValues.add("'" + cdrom + "'");
      }
  
      if (cites != null && !cites.isEmpty()) {
        inserts.add("cites");
        insertsValues.add("'" + StringUtils.join(cites , "|") + "'");
      }
  
      if (publisher != null) {
        inserts.add("publisher");
        insertsValues.add("'" + publisher + "'");
      }
  
      if (note != null) {
        inserts.add("note");
        insertsValues.add("'" + note + "'");
      }
      
      if (crossref != null) {
        inserts.add("crossref");
        insertsValues.add("'" + crossref + "'");
      }
  
      if (isbns != null && !isbns.isEmpty()) {
        inserts.add("isbns");
        insertsValues.add("'" + StringUtils.join(isbns , "|") + "'");
      }
  
      if (series != null) {
        inserts.add("series");
        insertsValues.add("'" + series + "'");
      }
  
      if (chapter != null) {
        inserts.add("chapter");
        insertsValues.add("'" + chapter + "'");
      }
  
      if (rating != null) {
        inserts.add("rating");
        insertsValues.add("'" + rating + "'");
      }
      
      //Complete query
      q += StringUtils.join(inserts , ", ");
      q += ") VALUES (";
      q += StringUtils.join(insertsValues , ", ");
      q += ") RETURNING id;";
      
      PrintDebugMessage("CreateListing", "Running query: " + q);
      ResultSet rs = stmt.executeQuery(q);
      
      if (rs.next()) {
        // Return the newly created listing object
        Integer listingid = rs.getInt("id");
        PrintDebugMessage("CreateListing", "We made a ID with listing ID: " + listingid);
        dbConn.commit();
  
        // Parse successfully created listing into the visualisation tables
        this.parseVisualisationDetails(title, authors, editors, venues); //TODO: uncomment and fix
        
        return GetListing(listingid);
        
      } else {
        return null;
      }
    }
    catch (SQLException e) {
      System.out.println(e.getMessage());
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
	       stmt.executeUpdate("UPDATE listings SET paused = " + paused + " WHERE id = " + listing.getId() + ";" );
	       dbConn.commit();
	       listing.setPaused(paused); //update local object
	       return true;
	     }
	     catch (SQLException e) {
	       return false;
	     }
	 }

	/**
	 * Obtain a list of listings (FOR SALE) that match the search queries
	 *
	 * @param queryListing Listing that contains query information
	 * @param minSellPrice Optional minimum sell price (may be null)
	 * @param maxSellPrice Optional maximum sell price (may be null)
	 * @param exactMatch Should exact matches be performed on strings
	 * @param caseSensitive Should string matches be case sensitive
	 *
	 * @return List of listings (empty if no results found)
	 */
	public List<Listing> SearchListings(Listing queryListing, Double minSellPrice, Double maxSellPrice, boolean exactMatch, boolean caseSensitive) {
		List<Listing> results = new ArrayList<Listing>();

		if (!dbConnStatus) {
			this.PrintDebugMessage("SearchListings", "No connection with database");
			return results;
		}

		String query = "SELECT * FROM listings ";
		ArrayList<String> sqlQueries = new ArrayList<>();

		//Case sensitivity check
		String compStr = "ILIKE";
		if (caseSensitive)
			compStr = "LIKE";

		//Exact match
		String strQuoteStart = "'%";
		String strQuoteEnd = "%'";

		if (exactMatch) {
			strQuoteStart = strQuoteEnd =  "'";
		}

		//Construct listing
		if (queryListing.getSellerid() != null)
			sqlQueries.add("sellerid = " + queryListing.getSellerid());

		if (queryListing.getQuantity() != null)
			sqlQueries.add("quantity >= " + queryListing.getQuantity());

		if (queryListing.getListdate() != null)
			sqlQueries.add("listdate >= to_timestamp(" + queryListing.getListdate().getTime()/1000 + ")");

		if (queryListing.getEnddate() != null)
			sqlQueries.add("enddate <= to_timestamp(" + queryListing.getEnddate().getTime()/1000 + ")");

		if (minSellPrice != null)
			sqlQueries.add("sellprice >= " + minSellPrice);

		if (maxSellPrice != null)
			sqlQueries.add("sellprice <= " + maxSellPrice);

		if (queryListing.getType() != null)
			sqlQueries.add("type = '" + queryListing.getType().toString().toLowerCase() + "'");

		if (queryListing.getAuthors() != null && !queryListing.getAuthors().isEmpty())
			sqlQueries.add("authors " + compStr + " " + strQuoteStart + StringUtils.join(queryListing.getAuthors() , "|") + strQuoteEnd);

		if (queryListing.getEditors() != null && !queryListing.getEditors().isEmpty())
			sqlQueries.add("editors " + compStr + " " + strQuoteStart + StringUtils.join(queryListing.getEditors() , "|") + strQuoteEnd);

		if (queryListing.getTitle() != null)
			sqlQueries.add("title " + compStr + " " + strQuoteStart + queryListing.getTitle() + strQuoteEnd);

		if (queryListing.getVenues() != null && !queryListing.getVenues().isEmpty())
			sqlQueries.add("venues " + compStr + " " + strQuoteStart + StringUtils.join(queryListing.getVenues() , "|") + strQuoteEnd);

		if (queryListing.getPages() != null)
			sqlQueries.add("pages " + compStr + " " + strQuoteStart + queryListing.getPages() + strQuoteEnd);

		if (queryListing.getYear() != null)
			sqlQueries.add("year = " + queryListing.getYear());

		if (queryListing.getAddress() != null)
			sqlQueries.add("address " + compStr + " " + strQuoteStart + queryListing.getAddress() + strQuoteEnd);

		if (queryListing.getVolume() != null)
			sqlQueries.add("volume " + compStr + " " + strQuoteStart + queryListing.getVolume() + strQuoteEnd);

		if (queryListing.getNumber() != null)
			sqlQueries.add("number " + compStr + " " + strQuoteStart + queryListing.getNumber() + strQuoteEnd);

		if (queryListing.getMonth() != null)
			sqlQueries.add("month " + compStr + " " + strQuoteStart + queryListing.getMonth() + strQuoteEnd);

		if (queryListing.getUrls() != null && !queryListing.getUrls().isEmpty())
			sqlQueries.add("urls " + compStr + " " + strQuoteStart + StringUtils.join(queryListing.getUrls() , "|") + strQuoteEnd);

		if (queryListing.getEes() != null && !queryListing.getEes().isEmpty())
			sqlQueries.add("ees " + compStr + " " + strQuoteStart + StringUtils.join(queryListing.getEes() , "|") + strQuoteEnd);

		if (queryListing.getCdrom() != null)
			sqlQueries.add("cdrom " + compStr + " " + strQuoteStart + queryListing.getCdrom() + strQuoteEnd);

		if (queryListing.getCites() != null && !queryListing.getCites().isEmpty())
			sqlQueries.add("cites " + compStr + " " + strQuoteStart + StringUtils.join(queryListing.getCites() , "|") + strQuoteEnd);

		if (queryListing.getPublisher() != null)
			sqlQueries.add("publisher " + compStr + " " + strQuoteStart + queryListing.getPublisher() + strQuoteEnd);

		if (queryListing.getNote() != null)
			sqlQueries.add("note " + compStr + " " + strQuoteStart + queryListing.getNote() + strQuoteEnd);

		if (queryListing.getCrossref() != null)
			sqlQueries.add("crossref " + compStr + " " + strQuoteStart + queryListing.getCrossref() + strQuoteEnd);

		if (queryListing.getIsbns() != null && !queryListing.getIsbns().isEmpty())
			sqlQueries.add("isbns " + compStr + " " + strQuoteStart + StringUtils.join(queryListing.getIsbns() , "|") + strQuoteEnd);

		if (queryListing.getSeries() != null)
			sqlQueries.add("series " + compStr + " " + strQuoteStart + queryListing.getSeries() + strQuoteEnd);

		if (queryListing.getChapter() != null)
			sqlQueries.add("chapter " + compStr + " " + strQuoteStart + queryListing.getChapter() + strQuoteEnd);

		if (queryListing.getRating() != null)
			sqlQueries.add("rating " + compStr + " " + strQuoteStart + queryListing.getRating() + strQuoteEnd);

		//Join sql queries
		if (!sqlQueries.isEmpty()) {
			query += "WHERE ";

			for (String q : sqlQueries)
				query += (q + " AND ");

			try {
				query = query.substring(0, query.lastIndexOf(" AND ")); //remove final AND if it exists
			} catch (Exception e) {}
		}

		query += ";";

		try {
			Statement stmt;
			dbConn.setAutoCommit(false);
			stmt = dbConn.createStatement();

			PrintDebugMessage("SearchListings", "RUNNING QUERY: " + query);
			ResultSet rs = stmt.executeQuery(query);

			Listing l = processResultSetIntoListing(rs);

			while (l != null) {
				results.add(l);
				l = processResultSetIntoListing(rs);
			}
		}
		catch (SQLException e) {
			return results;
		}

		return results;
	}
  
  /**
   * Add a listing to a user's cart
   *
   * @param user contains the cartid of the user
   * @param listingToAdd contains the listing to add into the user's cart
   * @return CartItem of item that was added if successful, null otherwise
   */
  public CartItem AddToCart(User user, Listing listingToAdd) {
    if (!dbConnStatus) {
      this.PrintDebugMessage("AddToCart", "No connection with database");
      return null;
    }
    
    try {
      //Generate timestamp
      Date now = new Date();
      Timestamp tNow = new Timestamp(now.getTime());
      
      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      String q = "INSERT INTO activecartitems (cartid, listingid, addedts) " +
        "VALUES (" + user.getCartid() + ", " + listingToAdd.getId() + ", to_timestamp(" + tNow.getTime()/1000  + "));";
      PrintDebugMessage("AddToCart", "Running query: " + q);
      stmt.executeUpdate(q);
      dbConn.commit();
      
      //Create CartItem to return
      CartItem cartItem = new CartItem();
			cartItem.setCartid(user.getCartid());
			cartItem.setListingid(listingToAdd.getId());
			cartItem.setAddedts(tNow);
			cartItem.setRemovedts(null);

			User u = GetUser(listingToAdd.getSellerid());
			cartItem.setSellerName(u.getUsername());
			cartItem.setPublicationName(listingToAdd.getTitle());
			cartItem.setPublicationType(listingToAdd.getType());
			cartItem.setPrice(listingToAdd.getSellprice());

      return cartItem;
    }
    catch (SQLException e) {
      return null;
    }
  }
	
	/**
	 * Remove a particular cartItem from a user's cart
	 *
	 * @param cartItem cart item to be removed
	 * @return boolean True when removal was successful
	 */		
	public boolean RemoveFromCart(CartItem cartItem) {
    if (!dbConnStatus) {
      this.PrintDebugMessage("RemoveFromCart", "No connection with database");
      return false;
    }

    if (!cartItem.isActive()) {
      this.PrintDebugMessage("RemoveFromCart", "CartItem is not active, cannot remove this item.");
      return false;
    }
    
    try {
      //Get current time
      Timestamp now = new Timestamp(new Date().getTime());

      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      stmt.executeUpdate("INSERT INTO removedcartitems (cartid, listingid, addedts, removedts) " +
                         "VALUES (" + cartItem.getCartid() + ", " + cartItem.getListingid() + ", to_timestamp(" + cartItem.getAddedts().getTime()/1000 + "), to_timestamp(" +  now.getTime()/1000 + "));");
      dbConn.commit();
      
      //Remove previous item
      stmt.executeUpdate("DELETE FROM activecartitems WHERE ctid IN ( SELECT ctid FROM activecartitems " +
                         "WHERE cartid=" + cartItem.getCartid() + " AND listingid=" + cartItem.getListingid() + " AND addedts=to_timestamp(" + cartItem.getAddedts().getTime()/1000 + ") " +
                         "ORDER BY addedts ASC LIMIT 1 );");
      dbConn.commit();
      
      cartItem.setRemovedts(now);
      return true;
    }
    catch (SQLException e) {
      return false;
    }
	}
	
	/**
	 * Remove a particular cartItem from a user's cart
	 *
	 * @param cartItem cart item to be removed
	 * @return boolean True when removal was successful
	 */
	public boolean HardRemoveFromCart(CartItem cartItem) {
	    if (!dbConnStatus) {
	        this.PrintDebugMessage("HardRemoveFromCart", "No connection with database");
	        return false;
	      }

	      if (!cartItem.isActive()) {
	        this.PrintDebugMessage("HardRemoveFromCart", "CartItem is not active, cannot remove this item.");
	        return false;
	      }
	      
	      try {
	        //Remove previous item
	        Statement stmt = dbConn.createStatement();
	        stmt.executeUpdate("DELETE FROM activecartitems WHERE ctid IN ( SELECT ctid FROM activecartitems " +
	                           "WHERE cartid=" + cartItem.getCartid() + " AND listingid=" + cartItem.getListingid() + " AND addedts=to_timestamp(" + cartItem.getAddedts().getTime()/1000 + ") " +
	                           "ORDER BY addedts ASC LIMIT 1 );");
	        dbConn.commit();
	        return true;
	      }
	      catch (SQLException e) {
	        return false;
	      }		
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
    	System.out.println(e.getMessage());
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
        String nickname = rs.getString("nickname");
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
        u.setNickname(nickname);
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
      ResultSet rs = stmt.executeQuery("SELECT * FROM listings ORDER BY id ASC;");			// default: return in order of id
      
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
      String query = "SELECT * FROM users ORDER BY id ASC;";
      ResultSet rs = stmt.executeQuery(query);
  
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
		if (!this.dbConnStatus) {
			this.PrintDebugMessage("GetActiveCartItems", "No connection to database");
			return null;
		}
		List<CartItem> activeCartItems = new ArrayList<CartItem>();
		try {
		      Statement stmt;
		      dbConn.setAutoCommit(false);
		      stmt = dbConn.createStatement();
		      String query = 
		    		  "SELECT " +
		    		  "		u.cartid AS cartid," +
		    		  "		l.id AS listingid," +
		    		  "		s.username AS sellername," +
		    		  "		l.type AS pubtype," +
		    		  "		l.title AS pubname," +
		    		  "		l.sellprice AS price," +
		    		  "		ac.addedts AS addedts " +
		    		  "FROM " +
		    		  "		activecartitems ac " +
		    		  "LEFT JOIN " +
		    		  "		users u ON ac.cartid = u.cartid " +
		    		  "LEFT JOIN " +
		    		  "		listings l ON ac.listingid = l.id " +
		    		  "LEFT JOIN " +
		    		  "		users s ON l.sellerid = s.id " +
		    		  "WHERE (" +
		    		  "		ac.cartid = " + cartID +
		    		  ");";
		      ResultSet rs = stmt.executeQuery(query);
		      System.out.println("Executed query: " + query);
		      
		      CartItem cartItem = processResultSetIntoCartItem(rs);
		      while (cartItem != null) {
		        activeCartItems.add(cartItem);
		        cartItem = processResultSetIntoCartItem(rs);
		      }			
			
		} catch (Exception e) {
			return null;
		}
		return activeCartItems;
	}

	/**
	 * Obtain all removed cart items in a given cart
	 *
	 * @param cartID the cart of id
	 * @return returns a list of cart items that have been removed
	 */	
	public List<CartItem> GetRemovedCartItems(int cartID) {
		if (!this.dbConnStatus) {
			this.PrintDebugMessage("GetActiveCartItems", "No connection to database");
			return null;
		}
		List<CartItem> removedCartItems = new ArrayList<CartItem>();
		try {
			Statement stmt;
			dbConn.setAutoCommit(false);
			stmt = dbConn.createStatement();
			String query =
											"SELECT " +
											"		u.cartid AS cartid," +
											"		l.id AS listingid," +
											"		s.username AS sellername," +
											"		l.type AS pubtype," +
											"		l.title AS pubname," +
											"		l.sellprice AS price," +
											"		rc.addedts AS addedts," +
											"		rc.removedts AS removedts " +
											"FROM " +
											"		removedcartitems rc " +
											"LEFT JOIN " +
											"		users u ON rc.cartid = u.cartid " +
											"LEFT JOIN " +
											"		listings l ON rc.listingid = l.id " +
											"LEFT JOIN " +
											"		users s ON l.sellerid = s.id " +
											"WHERE (" +
											"		rc.cartid = " + cartID +
											");";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("Executed query: " + query);

			CartItem cartItem = processResultSetIntoCartItem(rs);
			while (cartItem != null) {
				removedCartItems.add(cartItem);
				cartItem = processResultSetIntoCartItem(rs);
			}

		} catch (Exception e) {
			return null;
		}
		return removedCartItems;
	}
	
	/**
	 * Parses a result set into a CartItem
	 * @param rs the result set
	 * @return a CartItem if successfully parsed, null otherwise
	 */
	private CartItem processResultSetIntoCartItem(ResultSet rs) {
		CartItem cartItem = new CartItem();
		
		try {
			if (rs.next()) {
				Integer cartid = rs.getInt("cartid");
				Integer listingid = rs.getInt("listingid");
				String sellername = rs.getString("sellername");
				String pubtype = rs.getString("pubtype");
				String pubname = rs.getString("pubname");
				Double price = rs.getDouble("price");
				Timestamp addedts = rs.getTimestamp("addedts");
				
				// Case when removedts is undefined (eg for activecartitems)
				Timestamp removedts = null;
				try {
					removedts = rs.getTimestamp("removedts");
				} catch (SQLException e){
					// do nothing
				}

				// Set CartItem fields
				cartItem.setCartid(cartid);
				cartItem.setListingid(listingid);
				cartItem.setSellerName(sellername);
				cartItem.setPublicationType(Listing.stringToType(pubtype));
				cartItem.setPublicationName(pubname);
				cartItem.setPrice(price);
				cartItem.setAddedts(addedts);
				cartItem.setRemovedts(removedts);

			} else { //No result found
				cartItem = null;
			}
		} catch (SQLException e) {
			return null;
		}
		return cartItem;		
	}
	
	/**
	 * Creates an order, after the purchase has been made
	 * 
	 * @param userID the User object of the buyer
	 * @param soldListing the Listing object that is bought
	 * @return the Order if successfully created, null otherwise
	 */
	public Order CreateOrder(int userID, Listing soldListing) {
    if (!dbConnStatus) {
      this.PrintDebugMessage("CreateOrder", "No connection with database");
      return null;
    }
    
    try {
      Timestamp now = new Timestamp(new Date().getTime());
      
      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      String q = "INSERT INTO orders (buyerid, sellerid, pubtitle, order_date, price) " +
        "VALUES (" + userID + ", " + soldListing.getSellerid() + ", '" + soldListing.getTitle() + "', to_timestamp(" + now.getTime()/1000 + ")," + soldListing.getSellprice() + ")" +
        "RETURNING id;";
      PrintDebugMessage("CreateOrder", "Running query: " + q);
      ResultSet rs = stmt.executeQuery(q);
      
      if (rs.next()) {
        Integer orderid = rs.getInt("id");
        dbConn.commit();
        return GetOrder(orderid);
      } else {
        return null;
      }
    }
    catch (SQLException e) {
      return null;
    }
	}
  
  /**
   * Get an order
   *
   * @param orderID the id of the order
   * @return returns an order if it exists, null otherwise
   **/
  public Order GetOrder(int orderID) {
    if (!dbConnStatus) {
      this.PrintDebugMessage("GetOrder", "No connection with database");
      return null;
    }
    
    try {
      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM orders WHERE id = '" + orderID + "';");
      
      return processResultSetIntoOrder(rs);
    }
    catch (SQLException e) {
      return null;
    }
  }

	/**
	 * Obtain the order history of a particular user
	 *
	 * @param buyerID the id of the user
	 * @return returns a list of orders that the user has made
	 **/	
	public List<Order> GetOrderHistory(int buyerID) {
	    List<Order> orderHistory = new ArrayList<Order>();
	    
	    if (!dbConnStatus) {
	      this.PrintDebugMessage("GetOrderHistory", "No connection with database");
	      return orderHistory;
	    }
	    
	    try {
	      Statement stmt;
	      dbConn.setAutoCommit(false);
	      stmt = dbConn.createStatement();
	      ResultSet rs = stmt.executeQuery("SELECT * FROM orders WHERE buyerid = '" + buyerID + "';");
	      
	      Order o = processResultSetIntoOrder(rs);
	      
	      while (o != null) {
	        orderHistory.add(o);
	        o = processResultSetIntoOrder(rs);
	      }
	    }
	    catch (SQLException e) {
	      return orderHistory;
	    }
	    
	    return orderHistory;
	}
  
  /**
   * Given a result set, parse next row of values into Order
   *
   * @param rs result set for a query on the order table
   * @return null if no match found/rows exhausted, order of next row otherwise
   */
  private Order processResultSetIntoOrder(ResultSet rs) {
    Order o = new Order();
    
    try {
      if (rs.next()) {
        Integer id = rs.getInt("id");
        Integer buyerid = rs.getInt("buyerid");
        Integer sellerid = rs.getInt("sellerid");
				String pubTitle = rs.getString("pubTitle");
        Timestamp order_date = rs.getTimestamp("order_date");
        Double price = rs.getDouble("price");
        
        //Set Order fields
        o.setId(id);
        o.setBuyerid(buyerid);
        o.setSellerid(sellerid);
				o.setPubTitle(pubTitle);
        o.setOrder_date(order_date);
        o.setPrice(price);
      } else { //No result found
        o = null;
      }
    } catch (SQLException e) {
      return null;
    }
    
    return o;
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
      ResultSet rs = stmt.executeQuery("SELECT * FROM listings WHERE id = " + listingID + ";" );
      
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
      if (rs != null && rs.next()) {
        Integer id = rs.getInt("id");
        Integer sellerid = rs.getInt("sellerid");
        Integer quantity = rs.getInt("quantity");
        Timestamp listdate = rs.getTimestamp("listdate");
        Timestamp enddate = rs.getTimestamp("enddate");
        Double sellprice = rs.getDouble("sellprice");
        String image = rs.getString("image");
        Boolean paused = rs.getBoolean("paused");
        Integer numviews = rs.getInt("numviews");


				String type = rs.getString("type");

				List<String> authors = (rs.getString("authors") == null || rs.getString("authors").equals("")) ?
								new LinkedList<String>()
								: new LinkedList<String>(Arrays.asList(rs.getString("authors").split("\\|")));

				List<String> editors = (rs.getString("editors") == null || rs.getString("editors").equals("")) ?
								new LinkedList<String>()
								: new LinkedList<String>(Arrays.asList(rs.getString("editors").split("\\|")));

				String title = rs.getString("title");
				String pages = rs.getString("pages");
				Integer year = (rs.getInt("year") == 0 ? null : rs.getInt("year"));
        String address = rs.getString("address");
				String volume = rs.getString("volume");
				String number = rs.getString("number");
				String month = rs.getString("month");

				List<String> urls = (rs.getString("urls") == null || rs.getString("urls").equals("")) ?
								new LinkedList<String>()
								: new LinkedList<String>(Arrays.asList(rs.getString("urls").split("\\|")));

				List<String> ees = (rs.getString("ees") == null || rs.getString("ees").equals("")) ?
								new LinkedList<String>()
								: new LinkedList<String>(Arrays.asList(rs.getString("ees").split("\\|")));

				String cdrom = rs.getString("cdrom");

				List<String> cites = (rs.getString("cites") == null || rs.getString("cites").equals("")) ?
								new LinkedList<String>()
								: new LinkedList<String>(Arrays.asList(rs.getString("cites").split("\\|")));

				String publisher = rs.getString("publisher");
				String note = rs.getString("note");
				String crossref = rs.getString("crossref");

				List<String> isbns = (rs.getString("isbns") == null || rs.getString("isbns").equals("")) ?
								new LinkedList<String>()
								: new LinkedList<String>(Arrays.asList(rs.getString("isbns").split("\\|")));

				String series = rs.getString("series");

				List<String> venues = (rs.getString("venues") == null || rs.getString("venues").equals("")) ?
								new LinkedList<String>()
								: new LinkedList<String>(Arrays.asList(rs.getString("venues").split("\\|")));

				String chapter = rs.getString("chapter");
				String rating = rs.getString("rating");
        
		        //Set fields
		        l.setId(id);
				l.setSellerid(sellerid);
				l.setQuantity(quantity);
				l.setListdate(listdate);
				l.setEnddate(enddate);
				l.setSellprice(sellprice);
				l.setImage(image);
				l.setPaused(paused);
				l.setNumviews(numviews);

				l.setType(type);
				l.setAuthors(authors);
				l.setEditors(editors);
				l.setTitle(title);
				l.setPages(pages);
				l.setYear(year);
				l.setAddress(address);
				l.setVolume(volume);
				l.setNumber(number);
				l.setMonth(month);
				l.setUrls(urls);
				l.setEes(ees);
				l.setCdrom(cdrom);
				l.setCites(cites);
				l.setPublisher(publisher);
				l.setNote(note);
				l.setCrossref(crossref);
				l.setIsbns(isbns);
				l.setSeries(series);
				l.setVenues(venues);
				l.setChapter(chapter);
				l.setRating(rating);
        
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
	 * @param sellerID the seller ID of the seller
   * @return returns a list of listings
   */
  public List<Listing> GetUserListings(int sellerID) {
    List<Listing> userListings = new ArrayList<Listing>();
  
    if (!dbConnStatus) {
      this.PrintDebugMessage("GetUserListings", "No connection with database");
      return userListings;
    }
  
    try {
      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      String query = "SELECT * FROM listings WHERE sellerid =" + sellerID + " ORDER BY id ASC;";	// default: sort by id
      ResultSet rs = stmt.executeQuery(query);
    
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
      stmt.executeUpdate("UPDATE listings SET numviews = numviews + 1 WHERE id = " + listing.getId() + ";" );
      dbConn.commit();
      listing.setNumviews(listing.getNumviews() + 1); //update local object
      return true;
    }
    catch (SQLException e) {
      return false;
    }
	}

	/**
	 * Decrements listing quantity.
	 *
	 * @param listing Listing that is to be decremented
	 * @return true if successful, false otherwise
	 */
	public boolean DecrementListingQuantity(Listing listing) {
		if (!dbConnStatus) {
			this.PrintDebugMessage("DecrementListingQuantity", "No connection with database");
			return false;
		}

		try {
			Statement stmt;
			dbConn.setAutoCommit(false);
			stmt = dbConn.createStatement();
			stmt.executeUpdate("UPDATE listings SET quantity = quantity - 1 WHERE id = " + listing.getId() + ";" );
			dbConn.commit();
			listing.setQuantity(listing.getQuantity() - 1); //update local object
			return true;
		} catch (SQLException e) {
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
  
  /**
   * Remove a particular user
   *
   * @param userID the id of the user to remove
   * @return boolean True when successfully removed, False otherwise
   */
	public boolean RemoveUser(int userID) {
    if (!dbConnStatus) {
      this.PrintDebugMessage("RemoveUser", "No connection with database");
      return false;
    }
    
    try {
      if (DoesUserExist(userID)) {
        //Remove this users listings first (for constraint reasons)
        List<Listing> listings = GetUserListings(userID);
         
        for (Listing l : listings) {
          RemoveListing(l.getId());
        }
        
        //Finally remove user
        Statement stmt;
        dbConn.setAutoCommit(false);
        stmt = dbConn.createStatement();
        stmt.executeUpdate("DELETE FROM users WHERE id = " + userID + ";");
        dbConn.commit();
        return true;
      } else {
        PrintDebugMessage("RemoveUser", "Error! No user exists with user ID: " + userID);
        return false;
      }
    }
    catch (SQLException e) {
      return false;
    }
	}
  
  /**
   * Remove a specified listing
   *
   * @param listingID the id of the listing to remove
   * @return returns True if successfully removed; false otherwise
   */
  public boolean RemoveListing(int listingID) {
    if (!dbConnStatus) {
      this.PrintDebugMessage("RemoveListing", "No connection with database");
      return false;
    }
  
    try {
      Statement stmt;
      dbConn.setAutoCommit(false);
      stmt = dbConn.createStatement();
      String query = "";
      
      // Remove from activecartitems where id corresponds to listing
      query = "DELETE FROM activecartitems WHERE listingid = " + listingID + ";";
      stmt.executeUpdate(query);
      
      // Remove from removedcartitems
      query = "DELETE FROM removedcartitems WHERE listingid = " + listingID + ";";
      stmt.executeUpdate(query);
      
      // Remove from listings with the specified id
      query = "DELETE FROM listings WHERE id = " + listingID + ";";
      stmt.executeUpdate(query);
      dbConn.commit();
      return true;
    }
    catch (SQLException e) {
      return false;
    }
  }
	
  /**
   * Change the account status of a user
   *
   * @param user the user to change
   * @param newStatus the new status to change to
   * @return boolean True when status is changed, False otherwise
   */
  public boolean SetUserAccountStatus(User user, boolean newStatus) {
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
      ResultSet rs = stmt.executeQuery("SELECT * FROM listings ORDER BY id ASC OFFSET " + offset + " LIMIT " + limit + ";");
    
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
	* @return boolean True when admin is verified, False otherwise
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
        
	    		//Set Admin fields
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
	
	/**
	 * This function parses given parameters into the visualisation related tables
	 * 
	 * @param pubtitle the title of the publication
	 * @param authors the list of publication authors
	 * @param editors the list of publication editors
	 * @param venues the list of publication venues
	 */
	private void parseVisualisationDetails(String pubtitle, List<String> authors, 
										List<String> editors, List<String> venues) {
		
		// Get the id of the node corresponding to title
		Integer nodeTitleID = 0;
		if (this.DoesVisNodeExist("title", pubtitle)) {
			nodeTitleID = this.GetVisNodeID("title", pubtitle);
		} else {
			nodeTitleID = this.CreateVisNode("title", pubtitle);
		}
		
		// Get the ids of the nodes corresponding to the authors
		List<Integer> nodeAuthorIDs = new ArrayList<Integer>();
		if (authors != null) {
			for (String author : authors) {
				Integer nodeAuthorID = 0;
				if (this.DoesVisNodeExist("author", author)) {
					nodeAuthorID = this.GetVisNodeID("author", author);
				} else {
					nodeAuthorID = this.CreateVisNode("author", author);
				}
				nodeAuthorIDs.add(nodeAuthorID);
			}
		}
		
		// Get the ids of the nodes corresponding to the editors
		List<Integer> nodeEditorIDs = new ArrayList<Integer>();
		if (editors != null) {
			for (String editor : editors) {
				Integer nodeEditorID = 0;
				if (this.DoesVisNodeExist("editor", editor)) {
					nodeEditorID = this.GetVisNodeID("editor", editor);
				} else {
					nodeEditorID = this.CreateVisNode("editor", editor);
				}
				nodeEditorIDs.add(nodeEditorID);
			}
		}
		
		// Get the ids of the nodes corresponding to the venues
		List<Integer> nodeVenueIDs = new ArrayList<Integer>();
		if (venues != null) {
			for (String venue : venues) {
				Integer nodeVenueID = 0;
				if (this.DoesVisNodeExist("venue", venue)) {
					nodeVenueID = this.GetVisNodeID("venue", venue);
				} else {
					nodeVenueID = this.CreateVisNode("venue", venue);
				}
				nodeVenueIDs.add(nodeVenueID);
			}
		}
		
		// Populate the vis_relationships
		// Build Author Entries
		for (Integer authorID : nodeAuthorIDs) {
			// Check if there is already a relationship between the title and author
			// - do nothing
			if (!this.DoesVisRelationshipEntryExists(nodeTitleID, "authoredBy", authorID)) {
				this.CreateVisRelationship(nodeTitleID, "authoredBy", authorID);
			}
		}
		
		// Build Editor Entries
		for (Integer editorID : nodeEditorIDs) {
			// Check if there is already a relationship between the title and author
			// - do nothing
			if (!this.DoesVisRelationshipEntryExists(nodeTitleID, "editedBy", editorID)) {
				this.CreateVisRelationship(nodeTitleID, "editedBy", editorID);
			}
		}
		
		// Build Venue Entries
		for (Integer venueID : nodeVenueIDs) {
			// Check if there is already a relationship between the title and author
			// - do nothing
			if (!this.DoesVisRelationshipEntryExists(nodeTitleID, "publishedIn", venueID)) {
				this.CreateVisRelationship(nodeTitleID, "publishedIn", venueID);
			}
		}
	}
	
	/**
	 * This function checks in the vis_nodes table whether there is a node
	 * of type "attrtype" and "value"
	 * 
	 * @param attrtype the type of node
	 * @param value the value of the node
	 * @return True when such a node exists, false otherwise
	 * 
	 * NOTE WHEN CHECKING FOR PUBLICATION EXISTENCE:
	 * This function assumes a publication with the same name but different number of authors (specified
	 * by the user who's listing up the publication) will be considered the SAME publication.
	 * ie does NOT do checks for authors
	 * 
	 */
	private boolean DoesVisNodeExist(String attrtype, String value) {
		if (!dbConnStatus) {
			this.PrintDebugMessage("DoesVisNodeExist", "No connection with database");
		    return true;
	    }

		try {
			Statement stmt;
			dbConn.setAutoCommit(false);
			stmt = dbConn.createStatement();
			String query = "SELECT COUNT(*) FROM vis_nodes " +
					"WHERE ( " +
					" 	attrtype = '" + attrtype + "' AND " +
					"   value = '" + value + "' " + 
					");";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				int count = rs.getInt("count");
				if (count == 0)
					return false;
				else
					return true;
			}

			return true; //this should never be returned
		} catch (SQLException e) {
			return true; //this should never be returned
		}
	}
	
	/**
	 * Obtains the ID of a specific vis_node with the given details
	 * 
	 * @param attrtype the type of node
	 * @param value the value of node
	 * @return the id of the node, null otherwise
	 */
	private Integer GetVisNodeID(String attrtype, String value) {
		
		if (!dbConnStatus) {
			this.PrintDebugMessage("GetVisNodeID", "No connection with database");
		    return null;
	    }

		try {
			Statement stmt;
			dbConn.setAutoCommit(false);
			stmt = dbConn.createStatement();
			String query = "SELECT id FROM vis_nodes " +
					"WHERE ( " +
					" 	attrtype = '" + attrtype + "' AND " +
					"   value = '" + value + "' " + 
					");";
			ResultSet rs = stmt.executeQuery(query);
			
			try {
				if (rs != null && rs.next()) {
					Integer id = rs.getInt("id");
					return id;
			    } else { //No result found
			        return null;
			    }
			} catch (SQLException e) {
			     return null;
			}

		} catch (SQLException e) {
			return null; //this should never be returned
		}	
	}
	
	/**
	 * Insert an entry into the vis_nodes database
	 * 
	 * @param attrtype the type of node
	 * @param value the value of node
	 * @return the id of the created node, null otherwise
	 */
	private Integer CreateVisNode(String attrtype, String value) {
		
		if (!dbConnStatus) {
			this.PrintDebugMessage("CreateVisNode", "No connection with database");
		    return null;
	    }

		try {
			Statement stmt;
			dbConn.setAutoCommit(false);
			stmt = dbConn.createStatement();
			String query = "INSERT INTO vis_nodes(attrtype, value) " +
					"VALUES ( " +
					"	'" + attrtype + "', '" + value + "'" + 
					");";
			stmt.executeUpdate(query);
			dbConn.commit();
			return this.GetVisNodeID(attrtype, value);

		} catch (SQLException e) {
			return null; //this should never be returned
		}	
	}
	
	/**
	 * Checks whether a particular relationship exists
	 * 
	 * @param nodeTitleID the id of the publication
	 * @param relationship 
	 * @param relNodeID
	 * @return
	 */
	private boolean DoesVisRelationshipEntryExists(Integer firstNodeID, 
										String relationship, Integer secondNodeID) {
		if (!dbConnStatus) {
			this.PrintDebugMessage("DoesVisRelationshipEntryExists", "No connection with database");
		    return true;
	    }

		try {
			Statement stmt;
			dbConn.setAutoCommit(false);
			stmt = dbConn.createStatement();
			String query = "SELECT COUNT(*) FROM vis_relationships " +
					"WHERE ( " +
					" 	firstnode = '" + firstNodeID + "' AND " +
					" 	reltype = '" + relationship + "' AND " +
					"   secondnode = '" + secondNodeID + "' " + 
					");";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				int count = rs.getInt("count");
				if (count == 0)
					return false;
				else
					return true;
			}

			return true; //this should never be returned
		} catch (SQLException e) {
			return true; //this should never be returned
		}
	}
	
	/**
	 * This function will insert an entry into the vis_relationships table with
	 * the specified detail
	 * 
	 * @param firstNodeID the first node
	 * @param relationship the relationship type
	 * @param secondNodeID the second node
	 */
	private void CreateVisRelationship(Integer firstNodeID, 
								String relationship, Integer secondNodeID) {
		if (!dbConnStatus) {
			this.PrintDebugMessage("CreateVisRelationship", "No connection with database");
	    }

		try {
			Statement stmt;
			dbConn.setAutoCommit(false);
			stmt = dbConn.createStatement();
			String query = "INSERT INTO vis_relationships(firstnode, reltype, secondnode) " +
					"VALUES ( " + firstNodeID + ", '" + relationship + "', " + secondNodeID + ");";
			stmt.executeUpdate(query);
			dbConn.commit();

		} catch (SQLException e) {
			return;		// this should never be returned
		}	
	}

	/**
	 * Obtains all visualisation nodes
	 * @return list of vis_nodes, empty list otherwise
	 */
	public List<VisNode> GetAllVisNodes() {
		List<VisNode> allVisNodes = new ArrayList<VisNode>();
		
	    if (!dbConnStatus) {
	        this.PrintDebugMessage("GetAllVisNodes", "No connection with database");
	        return allVisNodes;
	    }
	      
	    try {
	        Statement stmt;
	        dbConn.setAutoCommit(false);
	        stmt = dbConn.createStatement();
	        String query = "SELECT * FROM vis_nodes ORDER BY id ASC;";
	        ResultSet rs = stmt.executeQuery(query);
	    
	        VisNode vn = processResultSetIntoVisNode(rs);
	        while (vn != null) {
	        	allVisNodes.add(vn);
	        	vn = processResultSetIntoVisNode(rs);
	        }
	        
	    } catch (SQLException e) {
	    	System.out.println(e);
	        return allVisNodes;
	    }
		return allVisNodes;
	}
	
	/**
	 * Parses result set into a visualisation node
	 * @param rs the result set
	 * @return a new visNode, null otherwise
	 */
	private VisNode processResultSetIntoVisNode(ResultSet rs) {
		VisNode vn = new VisNode();
	    
	    try {
	    	if (rs.next()) {
	    		Integer id = rs.getInt("id");
	    		String attrtype = rs.getString("attrtype");
	    		String value = rs.getString("value");
	    		
	    		// Set vis node fields
	    		vn.setID(id);
	    		vn.setValue(value);
	    		vn.setNodeType(attrtype);

	    	} else { //No result found
	    		return null;
	    	}
	    } catch (SQLException e) {
	    	System.out.println(e);
	    	return null;
	    }
	    return vn;		
	}

	/**
	 * Obtains all visualisation relationships
	 * @return list of vis_relationships, empty list otherwise
	 */
	public List<VisRelationship> GetAllVisRelationships() {
		List<VisRelationship> allVisRelationships = new ArrayList<VisRelationship>();
		
	    if (!dbConnStatus) {
	        this.PrintDebugMessage("GetAllVisRelationships", "No connection with database");
	        return allVisRelationships;
	    }
	    try {
	        Statement stmt;
	        dbConn.setAutoCommit(false);
	        stmt = dbConn.createStatement();
	        String query = "SELECT * FROM vis_relationships;";
	        ResultSet rs = stmt.executeQuery(query);
	    
	        VisRelationship vr = processResultSetIntoVisRelationship(rs);
	        while (vr != null) {
	        	allVisRelationships.add(vr);
	        	vr = processResultSetIntoVisRelationship(rs);
	        }
	    } catch (SQLException e) {
	    	System.out.println(e);
	        return allVisRelationships;
	    }
		return allVisRelationships;
	}
	
	/**
	 * Parses the result set into a visualisation relationship
	 * @param rs the input result set
	 * @return a visualisation node, null otherwise
	 */
	private VisRelationship processResultSetIntoVisRelationship(ResultSet rs) {
		VisRelationship vr = new VisRelationship();
	    
	    try {
	    	if (rs.next()) {
	    		Integer fromNodeID = rs.getInt("firstnode");
	    		String reltype = rs.getString("reltype");
	    		Integer toNodeID = rs.getInt("secondnode");
	    		
	    		// Set vis relationship fields
	    		vr.setFromNodeID(fromNodeID);
	    		vr.setRelationshipValue(reltype);
	    		vr.setToNodeID(toNodeID);

	    	} else { //No result found
	    		return null;
	    	}
	    } catch (SQLException e) {
	    	System.out.println(e);
	    	return null;
	    }
	    return vr;	
	}

	/**
	 * Searches the visualisation tables for visNodes and visRelationships
	 * satisfying a given query
	 * 
	 * @param query encapsulates the information to search
	 * @return a VisResult
	 */
	public VisResult SearchVis(VisQuery query) {
		VisResult result = new VisResult();

		if (!dbConnStatus) {
			this.PrintDebugMessage("SearchVis", "No connection with database");
			return result;
		}
		
		// Case when query has no query data: return everything
		if (query.getQueryData() == null || query.getQueryData().isEmpty()) {
			System.out.println("No query data provided!");
			result.setVisNodesResult(this.GetAllVisNodes());
			result.setVisRelationshipResult(this.GetAllVisRelationships());
			return result;
		}
		
		// Loop through each query data string
		try {
			
			// Prepare statement object (used to execute the query)
			Statement stmt;
			dbConn.setAutoCommit(false);
			stmt = dbConn.createStatement();
			ResultSet rs;
			String sqlQuery;
			
			// Maintain list of visNode IDs
			List<Integer> visNodeIDs = new ArrayList<Integer>();
			
			// Maintain a list of visNodes and visRelationships
			List<VisNode> visNodeResults = new ArrayList<VisNode>();
			List<VisRelationship> visRelationshipResults = new ArrayList<VisRelationship>();
			
			// Iterate over each query data string, and obtain a list of ID's corresponding to entries in vis_nodes
			// by searching in vis_relationships
			for (String qData : query.getQueryData()) {
				
				// Get the visnode id of qData
				Integer qDataVisNodeID = this.GetVisNodeID(query.getQueryType(), qData);
				
				// Case when none is found
				if (qDataVisNodeID == null) {
					System.out.println();
					continue;
				} else {
					visNodeIDs.add(qDataVisNodeID);
				}
				
				// Get list of visRelationship objects that are only related to qData
				sqlQuery = "SELECT * FROM vis_relationships WHERE (firstnode = " + qDataVisNodeID + " OR secondnode = " + qDataVisNodeID  + ");";
				PrintDebugMessage("SearchVis", "RUNNING QUERY: " + sqlQuery);
				rs = stmt.executeQuery(sqlQuery);
				VisRelationship vr = this.processResultSetIntoVisRelationship(rs);
				while (vr != null) {
					visRelationshipResults.add(vr);
					
					// Append id of the related entity (NOT the one corresponding to qData)
					// assumes the related entity can't be itself
					if (!vr.getFromNodeID().equals(qDataVisNodeID)) {
						visNodeIDs.add(vr.getFromNodeID());
					} else {
						visNodeIDs.add(vr.getToNodeID());
					}
					
					vr = this.processResultSetIntoVisRelationship(rs);
				}
			}
			
			// Check whether there are any id's for visNodes
			if (!visNodeIDs.isEmpty()) {
			
				// Obtain list of visNodes from list of visNodeIDs
				sqlQuery = "SELECT * FROM vis_nodes WHERE ";
				for (Integer visNodeID : visNodeIDs) {
					sqlQuery += "id = " + visNodeID + " OR ";
				}
				sqlQuery = sqlQuery.substring(0, sqlQuery.lastIndexOf(" OR "));	// remove trailing " OR "
				sqlQuery += ";";
				PrintDebugMessage("SearchVis", "RUNNING QUERY: " + sqlQuery);
				rs = stmt.executeQuery(sqlQuery);
				
				// Parse result set into list of VisNodes
				VisNode vn = this.processResultSetIntoVisNode(rs);
				while (vn != null) {
					visNodeResults.add(vn);
					vn = this.processResultSetIntoVisNode(rs);
				}
			
			}
			
			// Bind results to result object
			result.setVisNodesResult(visNodeResults);
			result.setVisRelationshipResult(visRelationshipResults);
			return result;
			
		} catch (Exception e) {
			System.out.println(e);
			return result;
		}
	}
	
}