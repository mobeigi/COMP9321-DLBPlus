package edu.unsw.comp9321;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Servlet implementation class SetupServlet
 */
public class SetupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private LinkedList<Publication> db;
       private int noElements = 0;
       private String publicationType;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetupServlet() {
        super();
    }

    public LinkedList<Publication> loadPubs(){
    	db = new LinkedList<Publication>();
    	
    	try{
    		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder builder = factory.newDocumentBuilder();
    		Document document = builder.parse(new File("C:\\Users\\Erik-z3462813\\workspace\\ass1\\WebContent\\WEB-INF\\dblp.xml"));
    		document.getDocumentElement().normalize();
    		
    		Integer index = new Integer(0);
    		
    		NodeList article = document.getElementsByTagName("article");
    		for(int x = 0; x < article.getLength(); ++x){
    			Node node = article.item(x);
    			
    			if(node.getNodeType() == Node.ELEMENT_NODE){
    				Element element = (Element) node;
					addToDB(element, index, "article");
					index++;
    			}
    		}
    		
    		NodeList inproc = document.getElementsByTagName("inproceedings");
    		for(int x = 0; x < inproc.getLength(); ++x){
    			Node node = inproc.item(x);
    			
    			if(node.getNodeType() == Node.ELEMENT_NODE){
    				Element element = (Element) node;
					addToDB(element, index, "inproceedings");
					index++;
    			}
    		}
    		
    		NodeList proc = document.getElementsByTagName("proceedings");
    		for(int x = 0; x < proc.getLength(); ++x){
    			Node node = proc.item(x);
    			
    			if(node.getNodeType() == Node.ELEMENT_NODE){
    				Element element = (Element) node;
					addToDB(element, index, "proceedings");
					index++;
    			}
    		}
    		
    		NodeList book = document.getElementsByTagName("book");
    		for(int x = 0; x < book.getLength(); ++x){
    			Node node = book.item(x);
    			
    			if(node.getNodeType() == Node.ELEMENT_NODE){
    				Element element = (Element) node;
					addToDB(element, index, "book");
					index++;
    			}
    		}
    		
    		NodeList incollection = document.getElementsByTagName("incollection");
    		for(int x = 0; x < incollection.getLength(); ++x){
    			Node node = incollection.item(x);
    			
    			if(node.getNodeType() == Node.ELEMENT_NODE){
    				Element element = (Element) node;
					addToDB(element, index, "incollection");
					index++;
    			}
    		}	
    		
    		NodeList phdthesis = document.getElementsByTagName("phdthesis");
    		for(int x = 0; x < phdthesis.getLength(); ++x){
    			Node node = phdthesis.item(x);
    			
    			if(node.getNodeType() == Node.ELEMENT_NODE){
    				Element element = (Element) node;
					addToDB(element, index, "phdthesis");
					index++;
    			}
    		}
    		
    		NodeList mastersthesis = document.getElementsByTagName("mastersthesis");
    		for(int x = 0; x < mastersthesis.getLength(); ++x){
    			Node node = mastersthesis.item(x);
    			
    			if(node.getNodeType() == Node.ELEMENT_NODE){
    				Element element = (Element) node;
					addToDB(element, index, "mastersthesis");
					index++;
    			}
    		}
    		
    		NodeList www = document.getElementsByTagName("www");
    		for(int x = 0; x < www.getLength(); ++x){
    			Node node = www.item(x);
    			
    			if(node.getNodeType() == Node.ELEMENT_NODE){
    				Element element = (Element) node;
					addToDB(element, index, "www");
					index++;
    			}
    		}
    		
    		this.noElements = index;
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return db;
    }
    
    
	private void addToDB(Element elem, int index, String pubType) {
		int i = 0;
		String tag = null;
		HashMap<String, String> hMap = new HashMap<String, String>();
		Publication pub = new Publication(pubType, index);
		NodeList list = elem.getElementsByTagName("*");
		
		while(i < list.getLength()){
			tag = list.item(i).getNodeName();
			hMap.put(tag, elem.getElementsByTagName(tag).item(0).getTextContent());
			i++;
		}
		
		pub.setPubDetails(hMap);
		this.db.add(pub);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stringId = request.getParameter("id");

		if (stringId != null) {
			Integer intId = Integer.parseInt(request.getParameter("id"));
			LinkedList<String> entry = getEntryDeets(intId);
			request.setAttribute("pubEntry", entry);
			request.setAttribute("publicationID", intId);
		    RequestDispatcher rd = request.getRequestDispatcher("/publication.jsp");
		    rd.forward(request, response);
		} else {
			this.db = loadPubs();
			getRandomPub(request);
			request.getSession().invalidate();
		    RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		    rd.forward(request, response);
		}
	}

	private LinkedList<String> getEntryDeets(Integer intId) {
		Publication pub = this.db.get(intId);
		LinkedList<String> entryDetails = pub.getPubDetails();
		return entryDetails;	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String req = request.getParameter("action");
		String link = "result.jsp";
			
		if(req.equals("back")){
			getRandomPub(request);
			link = "index.jsp";
		} else if(req.equals("search")){
			String query = request.getParameter("searchQuery");
			String type = request.getParameter("pubType");
			LinkedList<Publication> result = search(query, type, request);
			SearchPageBean SPBean = new SearchPageBean(result);
			request.setAttribute("searchFound",SPBean);
			link = "result.jsp";
		} else if(req.equals("aSearch")){
			String title = request.getParameter("searchTitle");
			String author = request.getParameter("searchAuthor");
			String editor = request.getParameter("searchEditor");
			String volume = request.getParameter("searchVolume");
			String publisher = request.getParameter("searchPublisher");
			String isbn = request.getParameter("searchISBN");
			String year = request.getParameter("searchYear");
			String type = request.getParameter("searchPubType");
			LinkedList<Publication> result = aSearch(title, author, editor, volume, publisher, isbn, year, type);
			request.setAttribute("searchFound",result);
			link = "result.jsp";
			
		} else if(req.equals("remove")) {
			link = remove(request);
		} else if(req.equals("add")){
			Integer id = Integer.parseInt(request.getParameter("publicationID"));
			 ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("ShoppingCart");
			 LinkedList<Publication> shoppingCartItems = shoppingCart.getElements();
			 if (shoppingCartItems.contains(this.db.get(id))) {
				 request.setAttribute("isAlreadySelected", true);
			 }
			 else {
	    	 		request.setAttribute("isAlreadySelected", false);
	    	 		shoppingCartItems.add(this.db.get(id));
	    	 		shoppingCart.setElements(shoppingCartItems);
			 }
			 request.setAttribute("cartSize", shoppingCartItems.size());
			 link = "cart.jsp";
					
		} else if(req.equals("viewPreviousSearchPage")){
			navigateSearchPage(request);
			link = "result.jsp";
		} else if(req.equals("viewNextSearchPage")){
			navigateSearchPage(request);
			link = "result.jsp";
		}
		
		 RequestDispatcher rd = request.getRequestDispatcher("/"+link);
		 rd.forward(request, response);
	}
	private void navigateSearchPage(HttpServletRequest request) {
		String action = request.getParameter("action");
		SearchPageBean searchPageBean = (SearchPageBean) request.getSession().getAttribute("searchFound");
		if(action.equals("viewPreviousSearchPage")){
			searchPageBean.currPage = searchPageBean.currPage - 1;
		} else if(action.equals("viewNextSearchPage")){
			searchPageBean.currPage = searchPageBean.currPage + 1;
		}
	}

	private String remove(HttpServletRequest request){
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("ShoppingCart");
		LinkedList<Publication> itemsInCart = shoppingCart.getElements();
		
		String[] itemsToRemove = request.getParameterValues("removeFromCart");
		if (itemsToRemove == null) {
			return "cart.jsp";
		}
		for (String item : itemsToRemove) {
			itemsInCart.remove(this.db.get(Integer.parseInt(item)));
		}
		request.setAttribute("cartSize", itemsInCart.size());
		return "cart.jsp";
	}
	private LinkedList<Publication> aSearch(String title, String author, String editor, String volume, String publisher, String isbn, String year, String type) {
		LinkedList<Publication> result = new LinkedList<Publication>();
		boolean flag = true;
		publicationType = type;
		if(type.toLowerCase().equals("any")){
			for (Publication p : this.db) {
				flag = true;
				if(title != "" && flag == true){
					if(!p.getTitle().toLowerCase().contains(title.toLowerCase())){
						flag = false;
					}
				}
				if(author != "" && flag == true){
					if(p.getAuthor()== null || !p.getAuthor().toLowerCase().contains(author.toLowerCase())){
						flag = false;
					}
				}
				if(editor != "" && flag == true){
					if(p.getEditor()== null || !p.getEditor().toLowerCase().contains(editor.toLowerCase())){
						flag = false;
					}
				}
				if(volume != "" && flag == true){
					if(p.getVolume()== null || !p.getVolume().toLowerCase().contains(volume.toLowerCase())){
						flag = false;
					}
				}
				if(publisher != null && flag == true){
					if(p.getPublisher()== null || !p.getPublisher().toLowerCase().contains(publisher.toLowerCase())){
						flag = false;
					}
				}
				if(isbn != "" && flag == true){
					if(p.getISBN()== null || !p.getISBN().toLowerCase().contains(isbn.toLowerCase())){
						flag = false;
					}
				}
				if(year != "" && flag == true){
					if(p.getYear()== null || !p.getYear().toLowerCase().contains(year.toLowerCase())){
						flag = false;
					}
				}
				if(flag == true){
					result.add(p);
				}
			}
		}
		if(type.toLowerCase().equals("article")){
			advSearchHelper(title, author, editor, volume, publisher, isbn, year, result);
		}
		
		if(type.toLowerCase().equals("inproceedings")){
			advSearchHelper(title, author, editor, volume, publisher, isbn, year, result);
		}
		
		if(type.toLowerCase().equals("proceedings")){
			advSearchHelper(title, author, editor, volume, publisher, isbn, year, result);
		}
		
		if(type.toLowerCase().equals("book")){
			advSearchHelper(title, author, editor, volume, publisher, isbn, year, result);
		}
		
		if(type.toLowerCase().equals("incollection")){
			advSearchHelper(title, author, editor, volume, publisher, isbn, year, result);
		}
		
		if(type.toLowerCase().equals("phdthesis")){
			advSearchHelper(title, author, editor, volume, publisher, isbn, year, result);
		}
		
		if(type.toLowerCase().equals("phdthesis")){
			advSearchHelper(title, author, editor, volume, publisher, isbn, year, result);
		}
		
		if(type.toLowerCase().equals("phdthesis")){
			advSearchHelper(title, author, editor, volume, publisher, isbn, year, result);
		}
		
		return result;
	}
	
	private LinkedList<Publication> advSearchHelper(String title, String author, String editor, String volume, String publisher, String isbn, String year, LinkedList<Publication> results) {
		for (Publication p : this.db) {
			boolean flag = true;
			if(publicationType != "" && flag == true){
				if(!p.getPubType().toLowerCase().equals(publicationType.toLowerCase())){

					flag = false;
				}
			}
			if(title != "" && flag == true){
				System.out.print("title: " + title+ " == " + p.getTitle());
				if(!p.getTitle().toLowerCase().contains(title.toLowerCase())){

					flag = false;
				}
			}
			if(author != "" && flag == true){
				if(p.getAuthor() == null || !p.getAuthor().toLowerCase().contains(author.toLowerCase())){
				
					flag = false;
				}
			}
			if(editor != "" && flag == true){
				if(p.getEditor()== null || !p.getEditor().toLowerCase().contains(editor.toLowerCase())){
					flag = false;
				}
			}
			if(volume != "" && flag == true){
				if(p.getVolume()== null || !p.getVolume().toLowerCase().contains(volume.toLowerCase())){
					flag = false;
				}
			}
			if(publisher != null && flag == true){
				if(p.getPublisher()== null || !p.getPublisher().toLowerCase().contains(publisher.toLowerCase())){
					flag = false;
				}
			}
			if(isbn != "" && flag == true){
				if(p.getISBN() == null || !p.getISBN().toLowerCase().contains(isbn.toLowerCase())){
					flag = false;
				}
			}
			if(year != "" && flag == true){
				if(p.getYear()== null || !p.getYear().toLowerCase().contains(year.toLowerCase())){
					flag = false;
				}
			}
			if(flag == true){
				results.add(p);
			}	
		}
		return results;
	}
	private LinkedList<Publication> search(String searchQuery, String pubType, HttpServletRequest request){
		LinkedList<Publication> result = new LinkedList<Publication>();
		
		if(pubType.toLowerCase().equals("any")){
			for (Publication p : this.db) {
				if (p.getTitle().toLowerCase().contains(searchQuery.toLowerCase())|| (p.getAuthor() != null && p.getAuthor().toLowerCase().contains(searchQuery.toLowerCase()))){
					result.add(p);
				}
			}
		}
		
		if(pubType.toLowerCase().equals("article")){
			basicSearchHelper(searchQuery, pubType, result);
		}
		
		if(pubType.toLowerCase().equals("inproceedings")){
			basicSearchHelper(searchQuery, pubType, result);
		}
		
		if(pubType.toLowerCase().equals("proceedings")){
			basicSearchHelper(searchQuery, pubType, result);
		}
		
		if(pubType.toLowerCase().equals("book")){
			basicSearchHelper(searchQuery, pubType, result);
		}
		
		if(pubType.toLowerCase().equals("incollection")){
			basicSearchHelper(searchQuery, pubType, result);
		}
		
		if(pubType.toLowerCase().equals("phdthesis")){
			basicSearchHelper(searchQuery, pubType, result);
		}
		
		if(pubType.toLowerCase().equals("phdthesis")){
			basicSearchHelper(searchQuery, pubType, result);
		}
		
		if(pubType.toLowerCase().equals("phdthesis")){
			basicSearchHelper(searchQuery, pubType, result);
		}
		return result;
		
	}	
	private LinkedList<Publication> basicSearchHelper(String query, String pubType, LinkedList<Publication> results){
		for (Publication p : this.db) {
			if (p.getTitle().toLowerCase().contains(query.toLowerCase()) || (p.getAuthor() != null && p.getAuthor().toLowerCase().contains(query.toLowerCase()) && p.getPubType().toLowerCase().equals(pubType))){
				System.out.println(p.getAuthor());
				results.add(p);
			}
		}
		return results;
	}
	private void getRandomPub(HttpServletRequest request) {
		LinkedList<Publication> random = new LinkedList<Publication>();
		Random rnd = new Random();
		for(int i = 0; i < 10; i++){
			int rndNum = rnd.nextInt((this.noElements) + 1);
			random.add(this.db.get(rndNum));
		}
		
		request.setAttribute("found",random);
	}
	
}
