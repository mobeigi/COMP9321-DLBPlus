# This program will read in a sample DBLP xml file,
# and insert each publication into the postgres database

import os
import sys
import xml.sax as SAX
import psycopg2
import random
import datetime

# ----------------------------
# This class represents a publication and its information
class Listing():
	# == Listing Attributes
	sellerid = 0
	quantity = 0
	listdate = ""
	enddate = ""
	sellprice = 0.0
	image = ""
	paused = False
	numviews = 0

	# == Publication Attributes
	type = ""
	authors = []
	editors = []
	title = ""
	venues = []				# booktitle, journal, school
	pages = ""
	year = 0
	address = ""
	volume = ""
	number = ""
	month = ""
	urls = []
	ees = []
	cdrom = ""
	cites = []
	publisher = ""
	note = ""
	crossref = ""
	isbns = []
	series = ""
	chapter = ""
	rating = ""
	
	def __init__(self):
	
		# Initialise listing attributes
		self.sellerid = 0
		self.quantity = 0
		self.listdate = ""
		self.enddate = ""
		self.sellprice = 0.0
		self.image = ""
		self.paused = False
		self.numviews = 0
	
		# Initialise publication attributes
		self.type = ""
		self.authors = []
		self.editors = []
		self.title = ""
		self.venues = []				# booktitle, journal, school
		self.pages = ""
		self.year = 0
		self.address = ""
		self.volume = ""
		self.number = ""
		self.month = ""
		self.urls = []
		self.ees = []
		self.cdrom = ""
		self.cites = []
		self.publisher = ""
		self.note = ""
		self.crossref = ""
		self.isbns = []
		self.series = ""
		self.chapter = ""
		self.rating = ""
		
	# Finalises the publication object
	def finalise(self):
		# Join lists together
		joinedAuthors = "|".join(self.authors)
		self.authors = joinedAuthors
		
		joinedEditors = "|".join(self.editors)
		self.editors = joinedEditors
		
		joinedVenues = "|".join(self.venues)
		self.venues = joinedVenues
		
		joinedURLs = "|".join(self.urls)
		self.urls = joinedURLs
		
		joinedEEs = "|".join(self.ees)
		self.ees = joinedEEs
		
		joinedISBNs = "|".join(self.isbns)
		self.isbns = joinedISBNs
		
		joinedCites = "|".join(self.cites)
		self.cites = joinedCites
		
	def showDetails(self):
		print "Authors: ", self.authors
		
# ----------------------------
# This class is responsible for the semantic parsing of the publication
# XML file
class SaxPublicationHandler(SAX.ContentHandler):

		db_conn = None				# for manipulating database
		currListing = None			# Keep track of current publication being parsed
		currString = ""				# Keep track of current string to append into publication list
		tag_stack = []				# for parsing tags correctly
		publication_types = []		# Holds the list of valid publication types
		venue_list = []				# Holds the types of publication venue encoded
		numPublicationsParsed = 0	# Keep track of how many publications were parsed
		db_cursor = None			# Cursor used to insert publications into db
		seller_ids = []
		
		# Constructor
		def __init__(self, cursor):
			# Set list of publication types
			self.publication_types.append("article")
			self.publication_types.append("inproceedings")
			self.publication_types.append("proceedings")
			self.publication_types.append("book")
			self.publication_types.append("incollection")
			self.publication_types.append("phdthesis")
			self.publication_types.append("mastersthesis")
			self.publication_types.append("www")
			
			# Set the list of venues
			self.venue_list.append("journal")
			self.venue_list.append("booktitle")
			self.venue_list.append("school")
			
			# Set list of possible seller ids
			# Assumes these exist in the database
			self.seller_ids.append(4)
			self.seller_ids.append(5)
			self.seller_ids.append(6)
			
			# Set cursor
			self.db_cursor = cursor
			
		# Handles start tags
		def startElement(self, tagname, attrs):
			
			# Initiate publication object for a publication type start tag
			if tagname in self.publication_types:
				self.currListing = Listing()
				
				# Set the type
				self.currListing.type = tagname
				
				# Obtain and set rating (if exists)
				if "rating" in attrs.keys():
					self.currListing.rating = attrs['rating']
			
			# Push start tag
			self.tag_stack.append(tagname)
			
		# Handles end tags
		def endElement(self, tagname):
		
			# Add publications if closing tagname is a publication type
			if tagname in self.publication_types:
			
				# By destiny, it has chosen this publication to be created as a listing
				if (self.insertDestiny()):
					
					# Generate listing information
					(sellerID, quantity, listdate, enddate, sellprice, image, paused, numviews) = self.generateListingInfo()

					# Set listing information
					self.currListing.sellerid = sellerID
					self.currListing.quantity = quantity
					self.currListing.listdate = listdate
					self.currListing.enddate = enddate
					self.currListing.sellprice = sellprice
					self.currListing.image = image
					self.currListing.paused = paused
					self.currListing.numviews = numviews
					
					# Finalise listing object
					self.currListing.finalise()
					
					# Insert listing into database
					#self.currListing.showDetails()
					query = """
								INSERT INTO
									listings(sellerid,quantity,listdate,enddate,sellprice,image,paused,numviews,type,title,authors,editors,pages,year,address,volume,number,month,urls,ees,cdrom,cites,publisher,note,crossref,isbns,series,venues,chapter,rating)
								VALUES
									(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)
							"""
					listings_fields = (self.currListing.sellerid,self.currListing.quantity,self.currListing.listdate,self.currListing.enddate,self.currListing.sellprice,self.currListing.image,self.currListing.paused,self.currListing.numviews,self.currListing.type,self.currListing.title,self.currListing.authors,self.currListing.editors,self.currListing.pages,self.currListing.year,self.currListing.address,self.currListing.volume,self.currListing.number,self.currListing.month,self.currListing.urls,self.currListing.ees,self.currListing.cdrom,self.currListing.cites,self.currListing.publisher,self.currListing.note,self.currListing.crossref,self.currListing.isbns,self.currListing.series,self.currListing.venues,self.currListing.chapter,self.currListing.rating)
					self.db_cursor.execute(query, listings_fields)
					
					# PARSE LISTING INTO VISNODE AND VISRELATIONSHIP				
					# Insert title (only insert when it does not already exist in database)
					if not self.visNodeExists('title',self.currListing.title):
						self.createVisNode('title', self.currListing.title)
					visNodeTitleID = self.getVisNodeID('title', self.currListing.title)
					
					# Insert authors (only insert when it does not already exist in database)
					for author in self.currListing.authors.split('|'):
						if author: 
							if not self.visNodeExists('author', author):
								self.createVisNode('author', author)
							visNodeAuthorID = self.getVisNodeID('author', author)
							if not self.visRelationshipExists(visNodeTitleID, 'authoredBy', visNodeAuthorID):
								self.createVisRelationship(visNodeTitleID, 'authoredBy', visNodeAuthorID)
					
					# Insert editors (only insert when it does not already exist in database)
					for editor in self.currListing.editors.split('|'):
						if editor:
							if not self.visNodeExists('editor', editor):
								self.createVisNode('editor', editor)
							visNodeEditorID = self.getVisNodeID('editor', editor)
							if not self.visRelationshipExists(visNodeTitleID, 'editedBy', visNodeEditorID):
								self.createVisRelationship(visNodeTitleID, 'editedBy', visNodeEditorID)
							
					# Insert venues (only insert when it does not already exist in database)
					for venue in self.currListing.venues.split('|'):
						if venue:
							if not self.visNodeExists('venue', venue):
								self.createVisNode('venue', venue)
							visNodeVenueID = self.getVisNodeID('venue', venue)
							if not self.visRelationshipExists(visNodeTitleID, 'editedBy', visNodeVenueID):
								self.createVisRelationship(visNodeTitleID, 'editedBy', visNodeVenueID)
					
					self.numPublicationsParsed += 1
					print "Num pubs parsed so far: ", self.numPublicationsParsed
				
				# Reset publication to none
				self.currListing = None
				
			# Consider other end tags
			else:
				
				# Tags that may involve content splitting on different lines
				# Add current author
				if tagname == 'author':
					self.currListing.authors.append(self.currString)
					self.currString = ""
				
				# Add current editor
				elif tagname == 'editor':
					self.currListing.editors.append(self.currString)
					self.currString = ""
				
				# Add current venue
				elif tagname in self.venue_list:
					self.currListing.venues.append(self.currString)
					self.currString = ""
				
			# Pop tag
			self.tag_stack.pop()
			
		# Handles all the characters between start and end tags
		# ie element values
		def characters(self, contentStr):			
			# Remove whitespaces
			content = contentStr.strip().encode("utf-8")
			
			# Ignore if original content is whitespace
			if content == "":
				return
				
			# Determine semantics of element value, depending on what was the last
			# start element tag
			tagname = self.tag_stack[-1]
			
			# == GENERAL PUBLICATION FIELDS
			# Case title
			if tagname == 'title':
				self.currListing.title += content
			
			# HTML formatting tags for title
			if tagname == 'sup':
				self.currListing.title += "<sup>%s</sup>" % content			
			if tagname == 'sub':
				self.currListing.title += "<sub>%s</sub>" % content
			if tagname == 'i':
				self.currListing.title += "<i>%s</i>" % content
			if tagname == 'tt':
				self.currListing.title += "<tt>%s</tt>" % content
				
			# Case author
			if tagname == 'author':
				self.currString += content		# author can be placed on multiple lines due to html characters
				
			# Case editor
			if tagname == 'editor':
				self.currString += content		# editor can be placed on multiple lines due to html characters
			
			# Case pages
			if tagname == 'pages':
				self.currListing.pages = content 
			
			# Case year
			if tagname == 'year':
				self.currListing.year = int(content)
				
			# Case address
			if tagname == 'address':
				self.currListing.address = content
				
			# Case volume
			if tagname == 'volume':
				self.currListing.volume = content
			
			# Case number
			if tagname == 'number':
				self.currListing.number = content
			
			# Case month
			if tagname == 'month':
				self.currListing.month = content
			
			# Case url
			if tagname == 'url':
				self.currListing.urls.append(content)
				
			# Case ee
			if tagname == 'ee':
				self.currListing.ees.append(content)
				
			# Case cdrom
			if tagname == 'cdrom':
				self.currListing.cdrom = content
				
			# Case crossref
			if tagname == 'crossref':
				self.currListing.crossref = content
				
			# Case isbn
			if tagname == 'isbn':
				self.currListing.isbns.append(content)
				
			# Case series
			if tagname == 'series':
				self.currListing.series = content
				
			# Case venue
			if tagname in self.venue_list:
				self.currString += " " + content
				self.currString.strip()
				
			# Case chapter
			if tagname == 'chapter':
				self.currListing.chapter = content
		
		# Generates random listing overhead info
		def generateListingInfo(self):
			# Generate random seller ID
			# Based on number of users
			numUsers = 6;
			sellerID = random.randint(1,numUsers)
			
			# Generate random quantity
			quantity = random.randint(1,100)
			
			# Generate random listing and end date
			listdate = datetime.date.fromordinal(datetime.date.today().toordinal() - random.randint(0,20)).strftime("%Y-%m-%d %H:%M:%S")
			enddate = datetime.date.fromordinal(datetime.date.today().toordinal() + random.randint(5,20)).strftime("%Y-%m-%d %H:%M:%S")
			
			# Generate random sellprice
			minPrice = 499.0
			maxPrice = 99999.0
			sellprice = random.randint(minPrice, maxPrice) / 100.0
			
			# Image string
			image = ""
			
			# Paused status
			paused = bool(random.getrandbits(1))
			
			# Numviews
			numviews = random.randint(0,999999)
			
			return (sellerID, quantity, listdate, enddate, sellprice, image, paused, numviews)
		
		def insertDestiny(self):
			return True
			
		# Determine if a visNode exists
		def visNodeExists(self, attrtype, value):
			query = """
						SELECT * FROM vis_nodes WHERE (attrtype = %s AND value = %s);
					"""
			query_values = (attrtype, value)
			self.db_cursor.execute(query,query_values)
			return self.db_cursor.fetchone() is not None
		
		# Create a vis node entry
		def createVisNode(self, nodeType, nodeValue):
			query = """
						INSERT INTO vis_nodes(attrtype,value) VALUES (%s,%s);
					"""
			node_values = (nodeType, nodeValue)
			self.db_cursor.execute(query, node_values)
		
		# Obtain the id of a particular visNode
		def getVisNodeID(self, nodeType, nodeValue):
			query = """
						SELECT * FROM vis_nodes WHERE (attrtype = %s AND value = %s);
					"""
			query_values = (nodeType, nodeValue)
			self.db_cursor.execute(query,query_values)
			result = self.db_cursor.fetchone()
			if (result is None):
				return None
			(id, _, _) = result
			return id
		
		# Determine if a visRelationship exists
		def visRelationshipExists(self, firstnodeid, reltype, secondnodeid):
			query = """
						SELECT * FROM vis_relationships 
						WHERE (firstnode = %s AND reltype = %s AND secondnode = %s);
					"""
			query_values = (firstnodeid, reltype, secondnodeid)
			self.db_cursor.execute(query,query_values)
			return self.db_cursor.fetchone() is not None
		
		# Create a vis relationship entry
		def createVisRelationship(self, firstnodeid, reltype, secondnodeid):
			query = """
						INSERT INTO vis_relationships(firstnode,reltype, secondnode)
						VALUES (%s,%s,%s);
					"""
			relationship_values = (firstnodeid, reltype, secondnodeid)
			self.db_cursor.execute(query, relationship_values)
			
# ----------------------------
# parses an XML Publications file, and inserts each publication
# into the database
def insertPublications(xmlFilename):
	# Set encoding to UTF-8
	reload(sys)
	sys.setdefaultencoding('utf8')
	
	# Obtain connection and cursor to database
	db_host = "localhost"
	db_port = "5432"
	db_database = "dlbplus"
	db_user = "postgres"
	db_password = "password"

	# connect to database
	print "Connecting to the database..."
	conn = psycopg2.connect(host=db_host, port=db_port, database=db_database, user=db_user, password=db_password)
	
	# Open cursor to perform database operations
	cursor = conn.cursor()
	
	# Create a SAX XML reader
	xmlParser = SAX.make_parser()
	
	# Set the content handler
	saxPublicationHandler = SaxPublicationHandler(cursor)
	xmlParser.setContentHandler(saxPublicationHandler)

	# Parse the XML file (will also insert into database)
	print "Parsing XML file..."
	xmlParser.parse(xmlFilename)
	
	# Commit changes
	conn.commit()
	
	print "%d publications inserted! Exiting..." % saxPublicationHandler.numPublicationsParsed
	
	# close db connections
	conn.close()
	cursor.close()
	
# This function returns a random price for a publication
def generateRandomPrice():
	minPrice = 499.0
	maxPrice = 99999.0
	randPrice = random.randint(minPrice, maxPrice) / 100.0
	return randPrice

# ----------------------------
if (__name__ == '__main__'):
	if (len(sys.argv) < 2):
		print "Usage: %s <xml filename>" % sys.argv[0]
		exit()
	
	xmlFilename = sys.argv[1]
	if not os.path.isfile(xmlFilename):
		print "Error: File %s not found." % xmlFilename
		exit()

	insertPublications(xmlFilename)
	exit()