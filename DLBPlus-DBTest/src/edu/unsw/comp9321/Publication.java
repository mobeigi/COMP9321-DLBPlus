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
	private List<String> author = new ArrayList<>();  //multiple, comma safe
	private List<String> editor = new ArrayList<>(); //multiple, comma safe
	private String title;
	private String booktitle; //part of venue search
	private String pages;
	private Integer year;
	private String address;
	private String journal; //part of venue search
	private String volume; //can be number or string
	private String number;  //integer
	private String month; //eg april
	private List<String> url = new ArrayList<>(); //multiple, comma safe
	private List<String> ee = new ArrayList<>(); //multiple, vertical bar safe
	private String cdrom;
	private List<String> cite = new ArrayList<>(); //multiple, comma safe
	private String publisher;
	private String note;
	private String crossref;
	private List<String> isbn = new ArrayList<>(); //multiple, comma safe
	private String series;
	private List<String> school = new ArrayList<>();  //multiple, semicolon safe, part of venue search
	private String chapter;

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
		this.author = p.getAuthor();
		this.editor = p.getEditor();
		this.title = p.getTitle();
		this.booktitle = p.getBooktitle();
		this.pages = p.getPages();
		this.year = p.getYear();
		this.address = p.getAddress();
		this.journal = p.getJournal();
		this.volume = p.getVolume();
		this.number = p.getNumber();
		this.month = p.getMonth();
		this.url = p.getUrl();
		this.ee = p.getEe();
		this.cdrom = p.getCdrom();
		this.cite = p.getCite();
		this.publisher = p.getPublisher();
		this.note = p.getNote();
		this.crossref = p.getCrossref();
		this.isbn = p.getIsbn();
		this.series = p.getSeries();
		this.school = p.getSchool();
		this.chapter = p.getChapter();
	}

	//Getters and Setters for all fields
	public int getId() {
			return id;
	}

	public void setId(int id) {
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

	public List<String> getAuthor() {
			return this.author;
	}

	public void setAuthor(String author) {
			this.author.add(author);
	}

	public List<String> getEditor() {
			return this.editor;
	}

	public void setEditor(String editor) {
			this.editor.add(editor);
	}

	public String getTitle() {
			return title;
	}

	public void setTitle(String title) {
			this.title = title;
	}

	public String getBooktitle() {
			return booktitle;
	}

	public void setBooktitle(String booktitle) {
			this.booktitle = booktitle;
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

	public String getJournal() {
			return journal;
	}

	public void setJournal(String journal) {
			this.journal = journal;
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

	public List<String> getUrl() {
			return this.url;
	}

	public void setUrl(String url) {
			this.url.add(url);
	}

	public List<String> getEe() {
			return this.ee;
	}

	public void setEe(String ee) {
			this.ee.add(ee);
	}

	public String getCdrom() {
			return cdrom;
	}

	public void setCdrom(String cdrom) {
			this.cdrom = cdrom;
	}

	public List<String> getCite() {
			return this.cite;
	}

	public void setCite(String cite) {
			this.cite.add(cite);
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

	public List<String> getIsbn() {
			return this.isbn;
	}

	public void setIsbn(String isbn) {
			this.isbn.add(isbn);
	}

	public String getSeries() {
			return series;
	}

	public void setSeries(String series) {
			this.series = series;
	}

	public List<String> getSchool() {
			return this.school;
	}

	public void setSchool(String school) {
			this.school.add(school);
	}

	public String getChapter() {
			return chapter;
	}

	public void setChapter(String chapter) {
			this.chapter = chapter;
	}
}
