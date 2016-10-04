package edu.unsw.comp9321;
/*
 * This class represent the model for the search results page
 */
import java.util.*;

public class ListingBean {
	public List<Listing> results = new ArrayList<Listing>();
	public int currPage = 1;
	public int totalPages = 0;
	public int numItemsPerPage = 10;
	
	// Constructor
	public ListingBean(List<Listing> searchListings, int currPage) {
		this.results = searchListings;
    
    this.currPage = currPage;
    
		// Determine number of pages
		this.totalPages = (int) Math.ceil((double)this.results.size() / (double)this.numItemsPerPage);
	}
	
	// getters
	public List<Listing> getResults() { return this.results; }
	public int getTotalItems() { return this.results.size(); }
	public int getCurrPage() { return this.currPage; }
	public int getTotalPages() { return this.totalPages; }
	public int getNumItemsPerPage() { return this.numItemsPerPage; }
}