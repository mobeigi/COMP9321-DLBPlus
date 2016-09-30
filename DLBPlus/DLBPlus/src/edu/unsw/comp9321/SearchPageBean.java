package edu.unsw.comp9321;
/*
 * This class represent the model for the search results page
 */
import java.util.*;

public class SearchPageBean {
	public List<Publication> results = new ArrayList<Publication>();
	public int currPage = 1;
	public int totalPages = 0;
	public int numItemsPerPage = 10;
	
	// Constructor given a list of publications
	public SearchPageBean(List<Publication> searchPublications) {
		
		// Convert list of publications into publicationBeans
		if (searchPublications != null) {
			for (Publication p : searchPublications) {
				Publication pb = new Publication(p);
				this.results.add(pb);
			}
		}
		
		// Determine number of pages
		this.totalPages = (int) Math.ceil(new Double(this.results.size()) / new Double(this.numItemsPerPage));
	}
	
	// getters
	public List<Publication> getResults() { return this.results; }
	public int getCurrPage() { return this.currPage; }
	public int getTotalPages() { return this.totalPages; }
	public int getNumItemsPerPage() { return this.numItemsPerPage; }
}