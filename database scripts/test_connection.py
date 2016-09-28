# This program tests whether connection to the postgreSQL 
# database can be made
import psycopg2

# ----------------------------
def test_connection():
	db_host = "localhost"
	db_port = "5432"
	db_database = "dlbplus"
	db_user = "postgres"
	db_password = "password"
	
	# connect to database
	print "Connecting to the database..."
	conn = psycopg2.connect(host=db_host, port=db_port, database=db_database, user=db_user, password=db_password)
	
	print "Successfully connected! Closing connection..."
	conn.close()

# ----------------------------
if (__name__ == "__main__"):
	test_connection()
	print "Finished testing! Exiting..."
	exit()