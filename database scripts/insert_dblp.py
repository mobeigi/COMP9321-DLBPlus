# This program will read in a sample DBLP xml file,
# and insert each publication into the postgres database

import os
import sys
import xml.sax as SAX
import psycopg2

# ----------------------------
# This class represents a publication and its information
class Publication():

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
	price = 0
	rating = ""
	
	def __init__(self):
	
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
		self.price = 0
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
		
# ----------------------------
# This class is responsible for the semantic parsing of the publication
# XML file
class SaxPublicationHandler(SAX.ContentHandler):

		db_conn = None				# for manipulating database
		currPublication = None		# Keep track of current publication being parsed
		currString = ""				# Keep track of current string to append into publication list
		tag_stack = []				# for parsing tags correctly
		publication_types = []		# Holds the list of valid publication types
		venue_list = []				# Holds the types of publication venue encoded
		numPublicationsParsed = 0	# Keep track of how many publications were parsed
		db_cursor = None			# Cursor used to insert publications into db
		
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
			
			# Set cursor
			self.db_cursor = cursor
			
		# Handles start tags
		def startElement(self, tagname, attrs):
			
			# Initiate publication object for a publication type start tag
			if tagname in self.publication_types:
				self.currPublication = Publication()
				
				# Set the type
				self.currPublication.type = tagname
				
				# Obtain and set rating (if exists)
				if "rating" in attrs.keys():
					self.currPublication.rating = attrs['rating']
			
			# Push start tag
			self.tag_stack.append(tagname)
			
		# Handles end tags
		def endElement(self, tagname):
		
			# Add publications if closing tagname is a publication type
			if tagname in self.publication_types:

				# Set a random price for publication
				self.currPublication.price = generateRandomPrice()
				
				# Finalise publication object
				self.currPublication.finalise()
				
				# Insert publication into database
				#self.currPublication.showDetails()
				query = """
							INSERT INTO
								publications(type,title,authors,editors,pages,year,address,volume,number,month,urls,ees,cdrom,cites,publisher,note,crossref,isbns,series,venues,chapter,recprice,rating)
							VALUES
								(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s);
						"""
				publication_fields = (self.currPublication.type,self.currPublication.title,self.currPublication.authors,self.currPublication.editors,self.currPublication.pages,self.currPublication.year,self.currPublication.address,self.currPublication.volume,self.currPublication.number,self.currPublication.month,self.currPublication.urls,self.currPublication.ees,self.currPublication.cdrom,self.currPublication.cites,self.currPublication.publisher,self.currPublication.note,self.currPublication.crossref,self.currPublication.isbns,self.currPublication.series,self.currPublication.venues,self.currPublication.chapter,self.currPublication.price,self.currPublication.rating)
				self.db_cursor.execute(query, publication_fields)
				
				self.numPublicationsParsed += 1
				
				# Reset publication to none
				self.currPublication = None
				
			# Consider other end tags
			else:
				
				# Tags that may involve content splitting on different lines
				# Add current author
				if tagname == 'author':
					self.currPublication.authors.append(self.currString)
					self.currString = ""
				
				# Add current editor
				elif tagname == 'editor':
					self.currPublication.editors.append(self.currString)
					self.currString = ""
				
				# Add current venue
				elif tagname in self.venue_list:
					self.currPublication.venues.append(self.currString)
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
				self.currPublication.title += content
			
			# HTML formatting tags for title
			if tagname == 'sup':
				self.currPublication.title += "<sup>%s</sup>" % content			
			if tagname == 'sub':
				self.currPublication.title += "<sub>%s</sub>" % content
			if tagname == 'i':
				self.currPublication.title += "<i>%s</i>" % content
			if tagname == 'tt':
				self.currPublication.title += "<tt>%s</tt>" % content
				
			# Case author
			if tagname == 'author':
				self.currString += content		# author can be placed on multiple lines due to html characters
				
			# Case editor
			if tagname == 'editor':
				self.currString += content		# editor can be placed on multiple lines due to html characters
			
			# Case pages
			if tagname == 'pages':
				self.currPublication.pages = content 
			
			# Case year
			if tagname == 'year':
				self.currPublication.year = int(content)
				
			# Case address
			if tagname == 'address':
				self.currPublication.address = content
				
			# Case volume
			if tagname == 'volume':
				self.currPublication.volume = content
			
			# Case number
			if tagname == 'number':
				self.currPublication.number = content
			
			# Case month
			if tagname == 'month':
				self.currPublication.month = content
			
			# Case url
			if tagname == 'url':
				self.currPublication.urls.append(content)
				
			# Case ee
			if tagname == 'ee':
				self.currPublication.ees.append(content)
				
			# Case cdrom
			if tagname == 'cdrom':
				self.currPublication.cdrom = content
				
			# Case crossref
			if tagname == 'crossref':
				self.currPublication.crossref = content
				
			# Case isbn
			if tagname == 'isbn':
				self.currPublication.isbns.append(content)
				
			# Case series
			if tagname == 'series':
				self.currPublication.series = content
				
			# Case venue
			if tagname in self.venue_list:
				self.currString += " " + content
				self.currString.strip()
				
			# Case chapter
			if tagname == 'chapter':
				self.currPublication.chapter = content
				
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
	return 0.0

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