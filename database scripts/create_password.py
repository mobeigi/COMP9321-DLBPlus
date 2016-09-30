# This program will accept a username, and a password
# Then generate a salt, and the salted password (using the SHA1 algorithm)

import hashlib
# -------------------------
def generatePassword():
	inputUsername = raw_input("Username: ")
	inputPassword = raw_input("Password: ")
	

# -------------------------
if (__name__ == '__main__'):
	generatePassword()
	print "Exiting..."
	exit()