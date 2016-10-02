package edu.unsw.comp9321;

import java.util.Date;

public class User {
	private Integer id;
	private String username;
	private String fname;
	private String lname;
	private String nickname;
	private String email;
	private String address;
	private Date dob;
	private String creditcard;
	private Integer cartid;
	private String dp;
	private Boolean acctstatus; // true = "active", false = "suspended";
  private Boolean acctconfrm; //true = confirmed, false = unconfirmed
  private Date acctcreated;
  
	public User() {}

	//Getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreditcard() {
		return creditcard;
	}

	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}

	public Integer getCartid() {
		return cartid;
	}

	public void setCartid(Integer cartid) {
		this.cartid = cartid;
	}

	public String getDp() {
		return dp;
	}

	public void setDp(String dp) {
		this.dp = dp;
	}

	public Boolean getAcctstatus() {
		return acctstatus;
	}

	public void setAcctstatus(Boolean acctstatus) {
		this.acctstatus = acctstatus;
	}
  
  public Boolean getAcctconfrm() {
    return acctconfrm;
  }
  
  public void setAcctconfrm(Boolean acctconfrm) {
    this.acctconfrm = acctconfrm;
  }
  
  public Date getDob() {
    return dob;
  }
  
  public void setDob(Date dob) {
    this.dob = dob;
  }
  
  public Date getAcctcreated() {
    return acctcreated;
  }
  
  public void setAcctcreated(Date acctcreated) {
    this.acctcreated = acctcreated;
  }

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
