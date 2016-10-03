/*
 * This class represents the results obtained from querying the
 * visualisation tables using a VisQuery object
 */
package edu.unsw.comp9321;

import java.util.*;

public class VisResult {
	private List<VisNode> visNodesResult = null;
	private List<VisRelationship> visRelationshipResult = null;
	
	// Constructor
	public VisResult() {
		this.visNodesResult = new ArrayList<VisNode>();
		this.visRelationshipResult = new ArrayList<VisRelationship>();
	}
	
	// Getters and setters
	public List<VisNode> getVisNodesResult() {
		return visNodesResult;
	}
	public void setVisNodesResult(List<VisNode> visNodesResult) {
		this.visNodesResult = visNodesResult;
	}
	public List<VisRelationship> getVisRelationshipResult() {
		return visRelationshipResult;
	}
	public void setVisRelationshipResult(List<VisRelationship> visRelationshipResult) {
		this.visRelationshipResult = visRelationshipResult;
	}
}
