# This program will accept a username, and a password
# Then generate a salt, and the salted password (using the SHA1 algorithm)

import hashlib
import os
import base64
# -------------------------
def generatePassword():

	# Obtain user details
	inputUsername = raw_input("Username: ")
	inputPassword = raw_input("Password: ")
	
	# Obtain a random salt
	salt = base64.b64encode(os.urandom(33))
	password = hashlib.sha1(salt + inputPassword).hexdigest()
	
	print "== Your details =="
	print "* Username: %s" % inputUsername
	print "* Input password: %s" % inputPassword
	print "* Salt: %s" % salt
	print "* Password: %s" % password
	

# -------------------------
if (__name__ == '__main__'):
	generatePassword()
	print "Exiting..."
	exit()