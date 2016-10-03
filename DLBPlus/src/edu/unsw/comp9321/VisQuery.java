/*
 * This class represents a query for visualisation
 */
package edu.unsw.comp9321;

import java.util.*;

public class VisQuery {
	private List<String> qTitles;
	private List<String> qAuthors;
	private List<String> qEditors;
	private List<String> qVenues;
	
	// Constructor
	public VisQuery() {
		this.qTitles = new ArrayList<String>();
		this.qAuthors = new ArrayList<String>();
		this.qEditors = new ArrayList<String>();
		this.qVenues = new ArrayList<String>();
		
	}
	
	// Getters and setters
	public List<String> getTitles() { return this.qTitles; }
	public List<String> getAuthors() { return this.qAuthors; }
	public List<String> getEditors() { return this.qEditors; }
	public List<String> getVenues() { return this.qVenues; }
	
	public void setTitles(List<String> newTitles) { this.qTitles = newTitles; }
	public void setAuthors(List<String> newAuthors) { this.qAuthors = newAuthors; }
	public void setEditors(List<String> newEditors) { this.qEditors = newEditors; }
	public void setVenues(List<String> newVenues) { this.qVenues = newVenues; }
}
