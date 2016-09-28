/*
 * This file contains the functions for database manipulation,
 * solely for the purpose of the COMP9321 assignment 2
 */
package edu.unsw.comp9321;

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
	 * Create a user by inserting provideduser details into database
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
	 * Validate an admin
	 *
	 * @param inputUsername the username of admin
	 * @param inputPwd plaintext password for admin
	 * @return boolean True when admin is verified, False otherwise
	 */
	public boolean VerifyAdmin(String inputUsername, String inputPwd);

	/**
	 * Create a listing (item for sale)
	 *
	 * @param newListing The new listing to be added
	 * @return boolean True when listing was successfully created; false otherwise
	 */
	 public boolean CreateListing(Listing newListing);
	 
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
	 * Incremements the number of views on a particular listing
	 *
	 * @param listingID the id of the listing to edit
	 */
	 public void IncrementListingViews(int listingID);
	 
	 /**
	  * Set the listing's paused status to be true or false
	  *
	  * @param listingID the id to change status
	  * @param paused the status to be changed to
	  * @return boolean True when paused was succesfully set. False otherwise
	  */
	public boolean SetPausedStatus(int listingID, boolean paused);
	 
	/**
	 * Obtain a list of publications (FOR SALE) that match the search queries
	 *
	 * @param queries contains key-value pair of query and value
	 * @return List of publications (empty if no results found)
	 */	 
	public List<Publication> SearchPublications(HashMap<String, String> queries);

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
	 * Obtain a user
	 *
	 * @param userID the id of the user
	 * @return returns a user when successful, null otherwise
	 */	
	public User GetUser(int userID);
  
  /**
   * Obtain a user
   *
   * @param username the username of the user
   * @return returns a user when successful, null otherwise
   */
  public User GetUser(String username);

	/**
	 * Obtain a list of all existing listings
	 *
	 * @return returns a list of listings
	 */		
	public List<Listing> GetAllListings();

	/**
	 * Obtain a list of all users
	 *
	 * @return @return returns a list of all existing users, regardless of account status, empty list otherwise
	 */		
	public List<User> GetAllUsers();

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
	 * Obtain the order history of a particular user
	 *
	 * @param userID the id of the user
	 * @return returns a list of orders that the user has made
	 */	
	public List<Order> GetOrderHistory(int userID);
}
