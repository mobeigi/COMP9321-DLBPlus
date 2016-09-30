package edu.unsw.comp9321;
/*
 * This class represent the model for the search results page
 */
import java.util.*;

public class SearchPageBean {
	public List<Listing> results = new ArrayList<Listing>();
	public int currPage = 1;
	public int totalPages = 0;
	public int numItemsPerPage = 10;
	
	// Constructor given a list of publications
	public SearchPageBean(List<Listing> searchListings) {
		
		// Convert list of publications into publicationBeans
		if (searchListings != null) {
			for (Listing listing : searchListings) {
				Listing newListing = new Listing();
				this.results.add(newListing);
			}
		}
		
		// Determine number of pages
		this.totalPages = (int) Math.ceil(new Double(this.results.size()) / new Double(this.numItemsPerPage));
	}
	
	// getters
	public List<Listing> getResults() { return this.results; }
	public int getCurrPage() { return this.currPage; }
	public int getTotalPages() { return this.totalPages; }
	public int getNumItemsPerPage() { return this.numItemsPerPage; }
}