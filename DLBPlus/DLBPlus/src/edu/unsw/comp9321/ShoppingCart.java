package edu.unsw.comp9321;

import java.util.*;

public class ShoppingCart {
	private LinkedList<Publication> items;

	public ShoppingCart() { 
		items = new LinkedList<Publication>(); 
	} 
	
	public LinkedList<Publication> getElements() { 
		return items; 
	} 
	
	public void setElements(LinkedList<Publication> items) {
		this.items = items;
	}
	
	public String toString() { return "ShoppingCart to "; }
}
