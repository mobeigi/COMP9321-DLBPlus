package edu.unsw.comp9321;

import java.sql.Timestamp;

public class CartItem {
  private Integer cartid;
  private Integer listingid;
  
  private String sellerName;
  private String publicationName;
  private String publicationType;
  private Double price;
  private Timestamp addedts;
  private Timestamp removedts; //only used if removed cart item, aka isActive = false
  private Boolean isActive;
  
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
  
  public String getPublicationType() {
    return publicationType;
  }
  
  public void setPublicationType(String publicationType) {
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
  
  public Boolean isActive() {
    return isActive;
  }
  
  public void setActive(Boolean active) {
    isActive = active;
  }
}
