/*
 * This class represents a node for visualisation
 */
package edu.unsw.comp9321;

public class VisNode {
	
	// Attributes
	public enum NodeType {
		TITLE,
		AUTHOR,
		EDITOR,
		VENUE
	}
	VisNode.NodeType nodeType;
	Integer id;
	String value;


	
	// Contsructor
	public VisNode() {}
	
	// Getters
	public Integer getID() { return this.id; }
	public String getValue() { return this.value; }
	public VisNode.NodeType getNodeType() { return this.nodeType; }
	
	// Setters
	public void setID(Integer newId) { this.id = newId; }
	public void setValue(String newValue) { this.value = newValue; }
	public void setNodeType(String newNodeType) { 
		switch(newNodeType) {
			case "title":
				this.nodeType = VisNode.NodeType.TITLE;
				break;
				
			case "author":
				this.nodeType = VisNode.NodeType.AUTHOR;
				break;
			
			case "editor":
				this.nodeType = VisNode.NodeType.EDITOR;
				break;
			
				
			case "venue":
				this.nodeType = VisNode.NodeType.VENUE;
				break;
			
			default:
				break;
		}
	}
	
}
