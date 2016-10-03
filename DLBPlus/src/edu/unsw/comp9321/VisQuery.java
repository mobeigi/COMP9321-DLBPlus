/*
 * This class represents a query for visualisation
 */
package edu.unsw.comp9321;

import java.util.*;

public class VisQuery {
	
	public enum Type {
	    TITLES,
	    AUTHORS,
	    EDITORS,
	    VENUES
	}
	
	private List<String> qDataList = null;
	private VisQuery.Type qType;
	
	// Constructor
	public VisQuery() {}
	
	// Getters and setters
	public List<String> getQueryData() { return this.qDataList; }
	public VisQuery.Type getQueryType() { return this.qType; }
	
	public void setQueryData(List<String> newData) { this.qDataList = newData; }
	public void setQueryType(String typeStr) { 
		if (typeStr.equals("titles")) {
			this.qType = VisQuery.Type.TITLES;
		} else if (typeStr.equals("authors")) {
			this.qType = VisQuery.Type.AUTHORS;
		} else if (typeStr.equals("editors")) {
			this.qType = VisQuery.Type.EDITORS;
		} else if (typeStr.equals("venues")) {
			this.qType = VisQuery.Type.VENUES;
		}
	}
}
