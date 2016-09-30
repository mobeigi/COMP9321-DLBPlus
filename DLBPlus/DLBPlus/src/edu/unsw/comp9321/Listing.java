package edu.unsw.comp9321;

import java.sql.Timestamp;

public class Listing {
  private Integer listingid;
  private Integer sellerid;
  private Integer itemid;
  private Integer quantity;
  private Timestamp listdate;
  private Timestamp enddate;
  private Double sellprice;
  private String image;
  private Boolean paused;
  private Integer numviews;
  
  public Listing() {}
  
  //Getters and setters
  public Integer getListingid() {
    return listingid;
  }
  
  public void setListingid(Integer listingid) {
    this.listingid = listingid;
  }
  
  public Integer getSellerid() {
    return sellerid;
  }
  
  public void setSellerid(Integer sellerid) {
    this.sellerid = sellerid;
  }
  
  public Integer getItemid() {
    return itemid;
  }
  
  public void setItemid(Integer itemid) {
    this.itemid = itemid;
  }
  
  public Integer getQuantity() {
    return quantity;
  }
  
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
  
  public Timestamp getListdate() {
    return listdate;
  }
  
  public void setListdate(Timestamp listdate) {
    this.listdate = listdate;
  }
  
  public Timestamp getEnddate() {
    return enddate;
  }
  
  public void setEnddate(Timestamp enddate) {
    this.enddate = enddate;
  }
  
  public Double getSellprice() {
    return sellprice;
  }
  
  public void setSellprice(Double sellprice) {
    this.sellprice = sellprice;
  }
  
  public String getImage() {
    return image;
  }
  
  public void setImage(String image) {
    this.image = image;
  }
  
  public Boolean getPaused() {
    return paused;
  }
  
  public void setPaused(Boolean paused) {
    this.paused = paused;
  }
  
  public Integer getNumviews() {
    return numviews;
  }
  
  public void setNumviews(Integer numviews) {
    this.numviews = numviews;
  }
}
