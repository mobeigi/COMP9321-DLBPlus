/*
 * This class represents a query for visualisation
 */
package edu.unsw.comp9321;

import java.util.*;

public class VisQuery {
	private String qTitle;
	private List<String> qAuthors;
	private List<String> qEditors;
	private List<String> qVenues;
	
	// Constructor
	public VisQuery() {
		this.qTitle = "";
		this.qAuthors = new ArrayList<String>();
		this.qEditors = new ArrayList<String>();
		this.qVenues = new ArrayList<String>();
		
	}
	
	// Getters and setters
	public String getTitle() { return this.qTitle; }
	public List<String> getAuthors() { return this.qAuthors; }
	public List<String> getEditors() { return this.qEditors; }
	public List<String> getVenues() { return this.qVenues; }
	
	public void setTitle(String newTitle) { this.qTitle = newTitle; }
	public void setAuthors(List<String> newAuthors) { this.qAuthors = newAuthors; }
	public void setEditors(List<String> newEditors) { this.qEditors = newEditors; }
	public void setVenues(List<String> newVenues) { this.qVenues = newVenues; }
}
