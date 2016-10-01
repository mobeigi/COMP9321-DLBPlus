package edu.unsw.comp9321;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Listing {
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
	
	public String getArrayAuthors() {
		String authorString = "";
		for(String author : this.authors){
			authorString += author + "; ";
		}
		return authorString.trim().substring(0,authorString.length()-2);
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
}