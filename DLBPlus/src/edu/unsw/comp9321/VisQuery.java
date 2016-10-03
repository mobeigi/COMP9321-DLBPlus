/*
 * This class represents a query for visualisation
 */
package edu.unsw.comp9321;

import java.util.*;

public class VisQuery {
	
	private List<String> qDataList = null;
	private String qType;
	
	// Constructor
	public VisQuery() {}
	
	// Getters and setters
	public List<String> getQueryData() { return this.qDataList; }
	public String getQueryType() { return this.qType; }
	
	public void setQueryData(List<String> newData) { this.qDataList = newData; }
	public void setQueryType(String typeStr) { this.qType = typeStr; }
	
	// Debugging
	public void showDetails() {
		System.out.println("Query type: " + this.qType);
		System.out.println("Query data list: ");
		for (String qData : this.qDataList) {
			System.out.println(qData);
		}
	}
}
