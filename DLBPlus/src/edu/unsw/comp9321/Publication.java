package edu.unsw.comp9321;

import java.util.HashMap;
import java.util.LinkedList;

public class Publication {
		private String pubType = null;
		private String author = null;
		private String editor = null;
		private String title = null;
		private String booktitle = null;
		private String pages = null;
		private String year = null;
		private String address = null;
		private String journal = null;
		private String volume = null;
		private String number = null;
		private String month = null;
		private String url = null;
		private String ee = null;
		private String cdrom = null;
		private String cite = null;
		private String publisher = null;
		private String note = null;
		private String crossref = null;
		private String isbn = null;
		private String series = null;
		private String school = null;
		private String chapter = null;
		private String id = null;
	
	
	public Publication(String type, Integer id){
		this.pubType = type;
		this.id = id.toString();
	}
	
	public Publication(Publication p) {
		this.pubType = p.getPubType();
		this.author = p.getAuthor();
		this.editor = p.getEditor();
		this.title = p.getTitle();
		this.booktitle = p.getBookTitle();
		this.pages = p.getPages();
		this.year = p.getYear();
		this.address = p.getAddress();
		this.journal = p.getJournal();
		this.volume = p.getVolume();
		this.number = p.getNumber();
		this.month = p.getMonth();
		this.url = p.getUrl();
		this.ee = p.getEE();
		this.cdrom = p.getCDRom();
		this.cite = p.getCite();
		this.publisher = p.getPublisher();
		this.note = p.getNote();
		this.crossref = p.getCrossRef();
		this.isbn = p.getISBN();
		this.series = p.getSeries();
		this.school = p.getSchool();
		this.chapter = p.getChapter();
		this.id = p.getID();
	}

	private String getID() {
		return this.id;
	}

	private String getChapter() {
		return this.chapter;
	}

	private String getSchool() {
		return this.school;
	}

	private String getSeries() {
		return this.series;
	}

	private String getCrossRef() {
		return this.crossref;
	}

	private String getNote() {
		return this.note;
	}

	private String getCite() {
		return this.cite;
	}

	private String getCDRom() {
		return this.cdrom;
	}

	private String getEE() {
		return this.ee;
	}

	private String getUrl() {
		return this.url;
	}

	private String getMonth() {
		return this.month;
	}

	private String getNumber() {
		return this.number;
	}

	private String getJournal() {
		return this.journal;
	}

	private String getAddress() {
		return this.address;
	}

	private String getPages() {
		return this.pages;
	}

	private String getBookTitle() {
		return this.booktitle;
	}

	public String getPubType(){
		return this.pubType;
	}
	
	public String getAuthor(){
		return this.author;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getId(){
		return this.id;
	}
	
	public String getVolume() {
		return this.volume;
	}
	
	public String getPublisher() {
		return this.publisher;
	}
	public String getISBN() {
		return this.isbn;
	}
	public String getYear() {
		return this.year;
	}	
	
	public String getEditor() {
		return this.editor;
	}

	public LinkedList<String> getPubDetails(){
		LinkedList<String> pubDetails = new LinkedList<String>();
		pubDetails.add("Publication type : " + this.pubType);
    	pubDetails.add("Author : " + this.author);
    	pubDetails.add("Title : " + this.title);
    	if (this.address != null)
    		pubDetails.add("Address : " + this.address);
    	if (this.booktitle != null)
    		pubDetails.add("Book Title : " + this.booktitle);
    	if (this.cdrom != null)
    		pubDetails.add("CD ROM : " + this.cdrom);
    	if (this.chapter != null)
    		pubDetails.add("Chapter : " + this.chapter);
    	if (this.cite != null)
    		pubDetails.add("Cite : " + this.cite);
    	if (this.crossref != null)
    		pubDetails.add("Cross Reference : " + this.crossref);
    	if (this.editor != null)
    		pubDetails.add("Editor : " + this.editor);
    	if (this.ee != null)
    		pubDetails.add("EE : " + this.ee);
    	if (this.isbn != null)
    		pubDetails.add("ISBN : " + this.isbn);
    	if (this.journal != null)
    		pubDetails.add("Journal : " + this.journal);
    	if (this.month != null)
    		pubDetails.add("Month : " + this.month);
    	if (this.year != null)
    		pubDetails.add("Year : " + this.year);
    	if (this.note != null)
    		pubDetails.add("Note : " + this.note);
    	if (this.number != null)
    		pubDetails.add("Number : " + this.number);
    	if (this.pages != null)
    		pubDetails.add("Pages : " + this.pages);
    	if (this.publisher != null)
    		pubDetails.add("Publisher : " + this.publisher);
    	if (this.school != null)
    		pubDetails.add("School : " + this.school);
    	if (this.series != null)
    		pubDetails.add("Series : " + this.series);
    	if (this.url != null)
    		pubDetails.add("URL : " + this.url);
    	if (this.volume != null)
    		pubDetails.add("Volume : " + this.volume);
    	return pubDetails;
	}
	
	public void setPubDetails(HashMap<String, String> input){
		int numberFields = input.size();
		int counter = 0;
		for (counter = 0; counter < numberFields; counter ++) {
			if (input.get("author") != null)
				this.author = input.get("author");
			if (input.get("dblp") != null)
				this.pubType = input.get("pubtype");
			if (input.get("title") != null)
				this.title = input.get("title");
	    	if (input.get("address") != null)
	    		this.address = input.get("address");
	    	if (input.get("booktitle") != null)
	    		this.booktitle = input.get("booktitle");
	    	if (input.get("cdrom") != null)
	    		this.cdrom = input.get("cdrom");
	    	if (input.get("chapter") != null)
	    		this.chapter = input.get("chapter");
	    	if (input.get("cite") != null)
	    		this.cite = input.get("cite");
	    	if (input.get("crossref") != null)
	    		this.crossref = input.get("crossref");
	    	if (input.get("editor") != null)
	    		this.editor = input.get("editor");
	    	if (input.get("ee") != null)
	    		this.ee = input.get("ee");
	    	if (input.get("isbn") != null)
	    		this.isbn = input.get("isbn");
	    	if (input.get("journal") != null)
	    		this.journal = input.get("journal");
	    	if (input.get("month") != null)
	    		this.month = input.get("month");
	    	if (input.get("year") != null)
	    		this.year = input.get("year");
	    	if (input.get("note") != null)
	    		this.note = input.get("note");
	    	if (input.get("number") != null)
	    		this.number = input.get("number");
	    	if (input.get("pages") != null)
	    		this.pages = input.get("pages");
	    	if (input.get("publisher") != null)
	    		this.publisher = input.get("publisher");
	    	if (input.get("school") != null)
	    		this.school = input.get("school");
	    	if (input.get("series") != null)
	    		this.series = input.get("series");
	    	if (input.get("url") != null)
	    		this.url = input.get("url");
	    	if (input.get("volume") != null)
	    		this.volume = input.get("volume");
		}
	}


}
