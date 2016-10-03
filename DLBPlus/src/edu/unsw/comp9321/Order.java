package edu.unsw.comp9321;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class Order {
  private Integer id;
  private Integer buyerid;
  private Integer sellerid;
  private String pubTitle;
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

	public String getPubTitle() {
		return pubTitle;
	}

	public void setPubTitle(String pubTitle) {
		this.pubTitle = pubTitle;
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
  
  //Helper getters for JLST
  public String getOrderDateString() {
    return new SimpleDateFormat("dd/MM/yyyy").format(this.getOrder_date());
  }
  
  public String getPriceString() {
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    String moneyString = formatter.format(this.price);
    return moneyString;
  }
  
  public String getSellerUsername() {
    DBHelper db = new DBHelper();
    db.init();
    User u = db.GetUser(this.getSellerid());
    String username = null;
    
    if (u != null) {
      username = u.getUsername();
    }
    
    db.close();
    
    return username;
  }
}
