/*
 * This file contains the functions for database manipulation,
 * solely for the purpose of the COMP9321 assignment 2
 */
package edu.unsw.comp9321;

import java.sql.Timestamp;
import java.util.*;

public interface DLBPlusDBInterface {
	
	// --------------------------
	// ATTRIBUTES
	// --------------------------
	public boolean dbConnStatus = false;
	
	// --------------------------
	// METHODS
	// --------------------------
	/**
	 * Initiate connection to DB
	 */
	public boolean init();

	/**
	 * Get a random publication
	 *
	 * @return Random publication or null if no publications exist (0 publications in db)
	 */
	public Publication GetRandomPublication();

	/**
	 * Get publication using ID
	 *
	 * @param pubID Publication identifier
	 * @return publication with matching publication identifier
	 */
	public Publication GetPublication(int pubID);
	
	/**
	 * Obtain a list of publications (FOR SALE) that match the search queries
	 *
	 * @param queries contains key-value pair of query and value
	 * @return List of publications (empty if no results found)
	 */	 
	public List<Publication> SearchPublications(HashMap<String, String> queries);

	/**
	* Create a listing (item for sale)
	*
	* @return Listing Null if listing unsuccessful, Listing of newly created listing otherwise
	*/
	public Listing CreateListing(User seller, Publication item, Integer quantity, Timestamp listdate, Timestamp enddate,
							   Double sellprice, String image);
	 
	/**
	 * Obtain a random listing
	 *
	 * @return returns a listing if there is at least one in DB; null otherwise
	 */
	 public Listing GetRandomListing();
	 
	/**
	 * Obtain a particular listing
	 *
	 * @param listingID the id of the listing to obtain
	 * @return the listing corresponding to given ID; null if such a listing doesn't exist
	 */
	 public Listing GetListing(int listingID);
  
  /**
   * Increments the number of views on a particular listing
   *
   * @param listing the listing being changed
   * @return true if successful, false otherwise
   */
  public boolean IncrementListingViews(Listing listing);
  
  /**
   * Set the listing's paused status to be true or false
   *
   * @param listing the listing to modify paused status
   * @param paused the new value for paused
   * @return boolean True when paused was successfully set. False otherwise
   */
	public boolean SetPausedStatus(Listing listing, boolean paused);

	/**
	 * Add a listing to a user's cart
	 *
	 * @param user contains the cartid of the user
	 * @param listingToAdd contains the listing to add into the user's cart
	 * @return boolean whether the update was successful
	 */	
	public boolean AddToCart(User user, Listing listingToAdd);

	/**
	 * Remove a particular listing from a user's cart
	 *
	 * @param user contains the cartid of the user
	 * @param listingID	the ID of the listing to remove
	 * @return boolean True when removal was successful
	 */		
	public boolean RemoveFromCart(User user, int listingID);

	/**
	 * Obtain all active cart items in a given cart
	 *
	 * @param cartID the id of the cart
	 * @return returns a list of Cart Items
	 */	
	public List<CartItem> GetActiveCartItems(int cartID);

	/**
	 * Obtain all removed cart items in a given cart
	 *
	 * @param cartID the cart of id
	 * @return returns a list of cart items that have been removed
	 */	
	public List<CartItem> GetRemovedCartItems(int cartID);
	
	/**
	 * Create a user by inserting provided user details into database
	 *
	 * @param username Provided username
	 * @param plainTextPassword Unsalted Password
	 * @return User corresponding to successful insertion (null otherwise)
	 */
	public User CreateUser(String username, String plainTextPassword, 
						String fname, String lname, String email, String address, 
						Date dob, String creditcard, String dp);

	/**
	 * Validate a user
	 *
	 * @param inputUsername the username of user
	 * @param inputPwd plaintext password for user
	 * @return boolean True when user is verified, False otherwise
	 */
	public boolean VerifyUser(String inputUsername, String inputPwd);

	/**
	* Obtain a user
	*
	* @param username the username of the user
	* @return returns a user when successful, null otherwise
	*/
	public User GetUser(String username);
	
	/**
	 * Obtain a specific user
	 *
	 * @param userID the id of the user to obtain
	 * @return returns User object, null if doesn't exist
	 */	
	public User GetUser(int userID);
	
	/**
	* Checks whether a user with a particular username exists
	*
	* @param username the username of potential user
	* @return User: returns true when user found in db; false otherwise
	*/
	public boolean DoesUserExist(String username);
	
	/**
	 * Sets the account confirmed status to a new value
	 * 
	 * @param user the user account
	 * @param confirmedStatus the new status to change to
	 * @return True when successfully changed, False otherwise
	 */
  public boolean SetAcctConfirmed(User user, boolean confirmedStatus);
  
  /**
   * Changes the details of a user based on their user id (which cannot be changed).
   *
   * @param changedUser The User object that contains all the information to change
   * @return True whether user was changed successfully, false otherwise
   */
  public boolean ChangeUserDetails(User changedUser);
	
	/**
	 * Obtain a list of all users
	 *
	 * @return @return returns a list of all existing users, regardless of account status, empty list otherwise
	 */		
	public List<User> GetAllUsers();
	
	/**
	 * Return the total number of users
	 *
	 * @return the total number of users, -1 on error
	 */	
	public int GetNumUsers();
	
	/**
	 * Obtain a specific range of users (inclusive)
	 *
   * @param startIndex the starting index (must be 0 - (numusers - 1))
   * @param endIndex the ending index (must be 0-numusers and >= startIndex)
	 * @return returns a list of users in specified range
	 */	
	 public List<User> GetUsers(int startIndex, int endIndex);
	
	/**
	 * Remove a particular user
	 *
	 * @param userID the id of the user to remove
	 * @return boolean True when successfully removed, False otherwise
	 */
	public boolean RemoveUser(int userID);
	
	/**
	 * Change the account status of a user
	 *
	 * @param user the user to change
	 * @param newStatus the new status to change to
	 * @return boolean True when status is changed, False otherwise
	 */
	public boolean SetUserAccountStatus(User user, boolean newStatus);

	/**
	 * Validate an admin
	 *
	 * @param inputUsername the username of admin
	 * @param inputPwd plaintext password for admin
	 * @return boolean True when admin is verified, False otherwise
	 */
	public boolean VerifyAdmin(String inputUsername, String inputPwd);
	
	/**
	 * Create an admin
	 *
	 * @param username the username of the new admin
	 * @param plainTextPassword plaintext password for new admin
	 * @return returns an Admin object when successfully created, null otherwise
	 */	
	public Admin CreateAdmin(String username, String plainTextPassword);
	
	/**
	 * Get an admin
	 * 
	 * @param username the username of the admin
	 * @return returns an Admin object if successfully retrieved, null otherwise
	 */
	public Admin GetAdmin(String username);
	
	/**
	 * Checks whether an admin with given username exists
	 *
	 * @param username Username to check
	 * @return boolean True for exists, False otherwise
	 */
	public boolean DoesAdminExist(String username);
	
	/**
	 * Obtain a list of all existing listings
	 *
	 * @return returns a list of listings
	 */		
	public List<Listing> GetAllListings();
	
	/**
	 * Obtain a list of listings by a particular user
	 *
	 * @return returns a list of listings
	 */		
	public List<Listing> GetUserListings(int userID);
	
	/**
	 * Remove a specified listing
	 *
	 * @param listingID the id of the listing to remove
	 * @return returns True if successfully removed; false otherwise
	 */	
	public boolean RemoveListing(int listingID);
	
	/**
	 * Return the total number of listings
	 *
	 * @return the total number of listings, or -1 on error
	 */	
	public int GetNumListings();
  
  /**
   * Obtain a specific range of listings (inclusive)
   *
   * @param startIndex the starting index (must be 0 - (numlistings - 1))
   * @param endIndex the ending index (must be 0-numlistings and >= startIndex)
   * @return returns a list of listings in specified range or empty list
   */
	public List<Listing> GetListings(int startIndex, int endIndex);
	 
	
	/**
	 * Creates an order, after the purchase has been made
	 * 
	 * @param buyer the User object of the buyer
	 * @param soldListing the Listing object that is bought
	 * @return the Order if successfully created, null otherwise
	 */
	public Order CreateOrder(User buyer, Listing soldListing);

	/**
	 * Obtain the order history of a particular user
	 *
	 * @param userID the id of the user
	 * @return returns a list of orders that the user has made
	 */	
	public List<Order> GetOrderHistory(int userID);
}
