/*
 * This class represents a relationship between two VisNodes
 */
package edu.unsw.comp9321;

public class VisRelationship {
	Integer fromNodeID;
	Integer toNodeID;
	String relValue;		// relationship value
	
	// Enhancement: Make relValue an ENUM rather than a simple string
	
	// Constructor
	public VisRelationship() {}
	
	// Getters
	public Integer getFromNodeID() { return this.fromNodeID; }
	public Integer getToNodeID() { return this.toNodeID; }
	public String getRelationshipValue() { return this.relValue; }
	
	// Setters
	public void setFromNodeID(Integer newFromNodeID) { this.fromNodeID = newFromNodeID; }
	public void setToNodeID(Integer newToNodeID) { this.toNodeID = newToNodeID; }
	public void setRelationshipValue(String newRelValue) { this.relValue = newRelValue; }
	
}
