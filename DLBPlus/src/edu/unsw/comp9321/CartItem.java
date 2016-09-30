package edu.unsw.comp9321;

import java.sql.Timestamp;

public class CartItem {
  private Integer cartid;
  private Integer listingid;
  private Timestamp addedts;
  private Timestamp removedts; //only used if removed cart item

  private String sellerName;
  private String publicationName;
  private Listing.Type publicationType;
  private Double price;
  
  public CartItem() {}
  
  //Getters and setters
  public Integer getCartid() {
    return cartid;
  }
  
  public void setCartid(Integer cartid) {
    this.cartid = cartid;
  }
  
  public Integer getListingid() {
    return listingid;
  }
  
  public void setListingid(Integer listingid) {
    this.listingid = listingid;
  }
  
  public String getSellerName() {
    return sellerName;
  }
  
  public void setSellerName(String sellerName) {
    this.sellerName = sellerName;
  }
  
  public String getPublicationName() {
    return publicationName;
  }
  
  public void setPublicationName(String publicationName) {
    this.publicationName = publicationName;
  }
  
  public Listing.Type getPublicationType() {
    return publicationType;
  }
  
  public void setPublicationType(Listing.Type publicationType) {
    this.publicationType = publicationType;
  }
  
  public Double getPrice() {
    return price;
  }
  
  public void setPrice(Double price) {
    this.price = price;
  }
  
  public Timestamp getAddedts() {
    return addedts;
  }
  
  public void setAddedts(Timestamp addedts) {
    this.addedts = addedts;
  }
  
  public Timestamp getRemovedts() {
    return removedts;
  }
  
  public void setRemovedts(Timestamp removedts) {
    this.removedts = removedts;
  }

  public boolean isActive() {
		return (removedts == null);
	}
  
  // debugging
  public void showDetails() {
	  System.out.println("id: " + this.cartid);
	  System.out.println("listing id: " + this.listingid);
	  System.out.println("Seller name: " + this.sellerName);
	  System.out.println("pub name: " + this.publicationName);
	  System.out.println("pub type: " + this.publicationType);
	  System.out.println("listing price: " + this.price);
	  System.out.println("added ts: " + this.addedts);
	  System.out.println("removed ts: " + this.removedts);
  }
}
