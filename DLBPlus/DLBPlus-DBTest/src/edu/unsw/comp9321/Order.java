package edu.unsw.comp9321;

import java.sql.Timestamp;

public class Order {
  private Integer id;
  private Integer buyerid;
  private Integer sellerid;
  private Integer itemid;
  private Timestamp order_date;
  private Double price;
  
  public Order() {}
  
  //Getters and setters
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public Integer getBuyerid() {
    return buyerid;
  }
  
  public void setBuyerid(Integer buyerid) {
    this.buyerid = buyerid;
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
  
  public Timestamp getOrder_date() {
    return order_date;
  }
  
  public void setOrder_date(Timestamp order_date) {
    this.order_date = order_date;
  }
  
  public Double getPrice() {
    return price;
  }
  
  public void setPrice(Double price) {
    this.price = price;
  }
}
