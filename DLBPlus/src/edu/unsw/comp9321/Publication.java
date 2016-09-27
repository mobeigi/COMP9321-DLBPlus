package edu.unsw.comp9321;

import java.util.ArrayList;
import java.util.List;

public class Publication {
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

	//Fields
	private Integer id;
	private Type type;
	private List<String> authors = new ArrayList<String>();
	private List<String> editors = new ArrayList<String>();
	private String title;
	private String pages;
	private Integer year;
	private String address;
	private String volume; //can be number or string
	private String number;
	private String month; //eg april
	private List<String> urls = new ArrayList<String>();
	private List<String> ees = new ArrayList<String>();
	private String cdrom;
	private List<String> cites = new ArrayList<String>();
	private String publisher;
	private String note;
	private String crossref;
	private List<String> isbns = new ArrayList<String>();
	private String series;
	private List<String> venues = new ArrayList<String>();  //school, booktitle and journal
	private String chapter;
	private Double recprice;
	private String rating;

	/**
	 * Default constructor
	 */
	public Publication(){}

	/**
	 * Alternative constructor
	 *
	 * @param p Publication
	 */
	public Publication(Publication p){
		this.id = p.getId();
		this.type = p.getType();
		this.authors = p.getAuthors();
		this.editors = p.getEditors();
		this.title = p.getTitle();
		this.pages = p.getPages();
		this.year = p.getYear();
		this.address = p.getAddress();
		this.volume = p.getVolume();
		this.number = p.getNumber();
		this.month = p.getMonth();
		this.urls = p.getUrls();
		this.ees = p.getEes();
		this.cdrom = p.getCdrom();
		this.cites = p.getCites();
		this.publisher = p.getPublisher();
		this.note = p.getNote();
		this.crossref = p.getCrossref();
		this.isbns = p.getIsbns();
		this.series = p.getSeries();
		this.venues = p.getVenues();
		this.chapter = p.getChapter();
		this.recprice = p.getRecprice();
		this.rating = p.getRating();
	}

	//Getters and Setters for all fields
	public Integer getId() {
			return id;
	}

	public void setId(Integer id) {
			this.id = id;
	}

	public Type getType() {
			return type;
	}

	public void setType(String type) {
			//Match type
			switch (type) {
					case "article":
							this.type = Type.ARTICLE;
							break;
					case "proceedings":
							this.type = Type.PROCEEDINGS;
							break;
					case "inproceedings":
							this.type = Type.INPROCEEDINGS;
							break;
					case "book":
							this.type = Type.BOOK;
							break;
					case "incollection":
							this.type = Type.INCOLLECTION;
							break;
					case "phdthesis":
							this.type = Type.PHDTHESIS;
							break;
					case "mastersthesis":
							this.type = Type.MASTERSTHESIS;
							break;
					case "www":
							this.type = Type.WWW;
							break;
					default:
							this.type = null;
			}
	}

	public List<String> getAuthors() {
			return this.authors;
	}

	public void setAuthor(String author) {
			this.authors.add(author);
	}

	public List<String> getEditors() {
			return this.editors;
	}

	public void setEditor(String editor) {
			this.editors.add(editor);
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
			return this.urls;
	}

	public void setUrl(String url) {
			this.urls.add(url);
	}

	public List<String> getEes() {
			return this.ees;
	}

	public void setEe(String ee) {
			this.ees.add(ee);
	}

	public String getCdrom() {
			return cdrom;
	}

	public void setCdrom(String cdrom) {
			this.cdrom = cdrom;
	}

	public List<String> getCites() {
			return this.cites;
	}

	public void setCite(String cite) {
			this.cites.add(cite);
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
			return this.isbns;
	}

	public void setIsbn(String isbn) {
			this.isbns.add(isbn);
	}

	public String getSeries() {
			return series;
	}

	public void setSeries(String series) {
			this.series = series;
	}

	public List<String> getVenues() {
			return this.venues;
	}

	public void setVenue(String school) {
			this.venues.add(school);
	}

	public String getChapter() {
			return chapter;
	}

	public void setChapter(String chapter) {
			this.chapter = chapter;
	}

	public Double getRecprice() {
		return recprice;
	}

	public void setRecprice(Double recprice) {
		this.recprice = recprice;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
}
