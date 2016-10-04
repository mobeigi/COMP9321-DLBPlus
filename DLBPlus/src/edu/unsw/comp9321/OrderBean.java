package edu.unsw.comp9321;
/*
 * This class represent the model for the orders
 */
import java.util.*;

public class OrderBean {
  public List<Order> results = new ArrayList<Order>();
  public int currPage = 1;
  public int totalPages = 0;
  public int numItemsPerPage = 10;
  
  // Constructor
  public OrderBean(List<Order> orders, int currPage) {
    this.results = orders;
    
    this.currPage = currPage;
    
    // Determine number of pages
    this.totalPages = (int) Math.ceil((double)this.results.size() / (double)this.numItemsPerPage);
  }
  
  // Getters
  public List<Order> getResults() { return this.results; }
  public int getTotalItems() { return this.results.size(); }
  public int getCurrPage() { return this.currPage; }
  public int getTotalPages() { return this.totalPages; }
  public int getNumItemsPerPage() { return this.numItemsPerPage; }
}