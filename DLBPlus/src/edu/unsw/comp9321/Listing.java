package edu.unsw.comp9321;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Listing{
  //Enums
  public enum Type {
    ARTICLE,
    INPROCEEDINGS,
    PROCEEDINGS,
    BOOK,
    INCOLLECTION,
    PHDTHESIS,
    MASTERSTHESIS,
    WWW
  }

  private Integer id;
  private Integer sellerid;
  private Integer quantity;
  private Timestamp listdate;
  private Timestamp enddate;
  private Double sellprice;
  private String image;
  private Boolean paused;
  private Integer numviews;

	private Listing.Type type;
	private List<String> authors = new ArrayList<>();
	private List<String> editors = new ArrayList<>();
	private String title;
	private String pages;
	private Integer year;
	private String address;
	private String volume; //can be number or string
	private String number;
	private String month; //eg april
	private List<String> urls = new ArrayList<>();
	private List<String> ees = new ArrayList<>();
	private String cdrom;
	private List<String> cites = new ArrayList<>();
	private String publisher;
	private String note;
	private String crossref;
	private List<String> isbns = new ArrayList<>();
	private String series;
	private List<String> venues = new ArrayList<>();  //school, booktitle and journal
	private String chapter;
	private String rating;

  public Listing() {}
  
  //Getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
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

	public Type getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = stringToType(type);
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public List<String> getEditors() {
		return editors;
	}

	public void setEditors(List<String> editors) {
		this.editors = editors;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public List<String> getEes() {
		return ees;
	}

	public void setEes(List<String> ees) {
		this.ees = ees;
	}

	public String getCdrom() {
		return cdrom;
	}

	public void setCdrom(String cdrom) {
		this.cdrom = cdrom;
	}

	public List<String> getCites() {
		return cites;
	}

	public void setCites(List<String> cites) {
		this.cites = cites;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCrossref() {
		return crossref;
	}

	public void setCrossref(String crossref) {
		this.crossref = crossref;
	}

	public List<String> getIsbns() {
		return isbns;
	}

	public void setIsbns(List<String> isbns) {
		this.isbns = isbns;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public List<String> getVenues() {
		return venues;
	}

	public void setVenues(List<String> venues) {
		this.venues = venues;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public static Listing.Type stringToType(String strType) {
		Listing.Type type;

		//Match type
		switch (strType.toLowerCase()) {
			case "article":
				type = Type.ARTICLE;
				break;
			case "proceedings":
				type = Type.PROCEEDINGS;
				break;
			case "inproceedings":
				type = Type.INPROCEEDINGS;
				break;
			case "book":
				type = Type.BOOK;
				break;
			case "incollection":
				type = Type.INCOLLECTION;
				break;
			case "phdthesis":
				type = Type.PHDTHESIS;
				break;
			case "mastersthesis":
				type = Type.MASTERSTHESIS;
				break;
			case "www":
				type = Type.WWW;
				break;
			default:
				type = null;
		}

		return type;
	}

	//Helper getters for JLST
  public String getArrayAuthors() {
    String authors = joinMultiString(this.authors);
    if (authors == "")
      return "N/A";
    
    return authors;
  }
  
  private String joinMultiString(List<String> list) {
    String result = "";
    
    if (list.isEmpty())
      return result;
    
    for(String s : list){
      result += s + "; ";
    }
    return result.trim().substring(0, result.length()-2);
  }
  
  public String getYearString() {
    if (this.getYear() != null) {
      return this.getYear().toString();
    }
    return "N/A";
  }
  
  public String getTypeString() {
    String typeStr = "";
    Listing.Type type = this.getType();
    
    //Match type
    if (type == Type.ARTICLE)
      typeStr = "Article";
    else if (type == Type.PROCEEDINGS)
      typeStr = "Proceedings";
    else if (type == Type.INPROCEEDINGS)
      typeStr = "Inproceedings";
    else if (type == Type.BOOK)
      typeStr = "Book";
    else if (type == Type.INCOLLECTION)
      typeStr = "Incollection";
    else if (type == Type.PHDTHESIS)
      typeStr = "PHD Thesis";
    else if (type == Type.MASTERSTHESIS)
      typeStr = "Masters Thesis";
    else if (type == Type.WWW)
      typeStr = "Website";
    else
      typeStr = "N/A";
    
    return typeStr;
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
  
  public String getListDateString() {
    return new SimpleDateFormat("dd/MM/yyyy").format(this.getListdate());
  }
  
  public String getEndDateString() {
    return new SimpleDateFormat("dd/MM/yyyy").format(this.getEnddate());
  }
  
  public String getSellpriceString() {
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    String moneyString = formatter.format(this.sellprice);
    return moneyString;
  }
  
  /**
   * Image if it exists or default image otherwise (base64 encoded)
   *
   * @return base64 encoding string representing image or default image
   */
  public String getImageOrDefault() {
    if (image != null && !image.isEmpty())
      return image;
    
    return "data:image/png;base64,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";
  }
  
}