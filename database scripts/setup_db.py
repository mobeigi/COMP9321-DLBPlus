# This program prepares the database for the DLBPlus web application
# as according to the database schema description
import psycopg2

# ----------------------------
def setup_db():
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
	
	# Drop the following table names, if they exist in the database
	print "Dropping tables, if they exist..."
	table_names = ['users', 'admins', 'publications', 'listings', 'activecartitems', 'removedcartitems', 'orders']
	for table_name in table_names:
		query = "DROP TABLE IF EXISTS %s CASCADE" % table_name
		cursor.execute(query)
	
	# Insert the tables
	print "Creating admin table..."
	query = "CREATE TABLE admins (" \
			"	id 			SERIAL	PRIMARY KEY," \
			"	username 	TEXT	UNIQUE	NOT NULL," \
			"	salt		TEXT	NOT NULL," \
			"	password	TEXT	NOT NULL" \
			");"
	cursor.execute(query)
	
	print "Creating publications table..."
	query = """
			CREATE TABLE publications (
				id			SERIAL	PRIMARY KEY,
				type		TEXT	NOT NULL,
				authors		TEXT,
				editors		TEXT,
				title		TEXT 	NOT NULL,
				venues		TEXT,
				pages		TEXT,
				year		INT,
				address		TEXT,
				volume		TEXT,
				number		TEXT,
				month		TEXT,
				urls		TEXT,
				ees			TEXT,
				cdrom		TEXT,
				cites		TEXT,
				publisher	TEXT,
				note		TEXT,
				crossref	TEXT,
				isbns		TEXT,
				series		TEXT,
				chapter		TEXT,
				recprice	TEXT,
				rating		TEXT
			);
		"""
	cursor.execute(query)
			
	print "Creating users table..."
	query = """
			CREATE TABLE users (
				id			SERIAL	PRIMARY KEY,
				username	TEXT	UNIQUE NOT NULL,
				salt		TEXT	NOT NULL,
				password	TEXT	NOT NULL,
				fname		TEXT	NOT NULL,
				lname		TEXT	NOT NULL,
				email		TEXT	NOT NULL,
				address		TEXT	NOT NULL,
				dob			DATE	NOT NULL,
				creditcard	TEXT	NOT NULL,
				cartid		SERIAL	UNIQUE NOT NULL,
				dp			TEXT,
				acctstatus	BOOLEAN	DEFAULT TRUE,
				acctconfrm	BOOLEAN DEFAULT	FALSE,
				acctcreated	DATE 	NOT NULL
			);
			"""
	cursor.execute(query)
	
	print "Creating orders table..."
	query = """
			CREATE TABLE orders (
				id 			SERIAL	PRIMARY KEY,
				buyerid		SERIAL	REFERENCES users (id),
				sellerid	SERIAL	REFERENCES users (id),
				itemid		SERIAL	REFERENCES publications (id),
				order_date	TIMESTAMP WITH TIME ZONE	NOT NULL,
				price		DOUBLE PRECISION	NOT NULL
			);
			"""
	cursor.execute(query)
	
	print "Creating listings table..."
	query = """
			CREATE TABLE listings (
				listingid	SERIAL	PRIMARY KEY,
				sellerid	SERIAL	REFERENCES users (id),
				itemid		SERIAL	REFERENCES publications (id),
				quantity	INT		NOT NULL,
				listdate	TIMESTAMP WITH TIME ZONE	NOT NULL,
				enddate		TIMESTAMP WITH TIME ZONE	NOT NULL,
				sellprice	DOUBLE PRECISION	NOT NULL,
				image		TEXT,
				paused		BOOLEAN	DEFAULT FALSE,
				numviews	INT		DEFAULT 0
			);
			"""
	cursor.execute(query)	

	print "Creating activecartitems table..."
	query = """
			CREATE TABLE activecartitems (
				cartid		SERIAL	REFERENCES users (cartid),
				itemid		SERIAL	REFERENCES publications (id),
				sellerid	SERIAL	REFERENCES users (id),
				itemprice	DOUBLE PRECISION	NOT NULL,
				addedts		TIMESTAMP WITH TIME ZONE NOT NULL
			);
			"""
	cursor.execute(query)
	
	print "Creating removedcartitems table..."
	query = """
			CREATE TABLE removedcartitems (
				cartid		SERIAL	REFERENCES users (cartid),
				itemid		SERIAL	REFERENCES publications (id),
				sellerid	SERIAL	REFERENCES users (id)
				itemprice	MONEY	NOT NULL,
				addedts		TIMESTAMP WITH TIME ZONE NOT NULL,
				removedts	TIMESTAMP WITH TIME ZONE
			);	
			"""
			
	# Persist the changes to database
	print "Persisting changes..."
	conn.commit()
	
	# Close communication with database
	print "Closing connections with database..."
	cursor.close()
	conn.close()

# ----------------------------
if (__name__ == "__main__"):
	setup_db()
	print "DB setup successful! Exiting..."
	exit()
