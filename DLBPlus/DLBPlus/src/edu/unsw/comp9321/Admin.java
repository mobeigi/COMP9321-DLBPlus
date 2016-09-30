// This class represents an Admin object
package edu.unsw.comp9321;

public class Admin {
	private Integer id;
	private String username;
	
	// Constructor
	public Admin() {
		this.id = 0;
		this.username = "";
	}
	
	// Setters
	public void setId(int newID) { this.id = newID; }
	public void setUsername(String newUsername) { this.username = newUsername; }
	
	// Getters
	public Integer getId() { return this.id; }
	public String getUsername() { return this.username; }
	
	// Functions
	public void showDetails() {
		System.out.println("ID: " + this.id);
		System.out.println("Username: " + this.username);
	}
}
