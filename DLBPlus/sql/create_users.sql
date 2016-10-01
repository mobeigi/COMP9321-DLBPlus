-- This script will create some users, whose account status is active, account confirmed

-------------------------------------------------------------------------------------------
/*
== Template (Make sure to put single quotation marks around each field!)
/*
 * == User
 * Username: username
 * Inputpwd: inputpwd
 */
INSERT INTO 
	users (id, username, salt, password, fname, lname, nickname, email, address, dob, creditcard, dp, acctstatus, acctconfrm, acctcreated) 
VALUES (
	id,										-- ID
	username,								-- USERNAME
	salt,									-- SALT
	password,								-- ENCRYPTED PASSWORD
	fname,									-- FIRST NAME
	lname,									-- LAST NAME
	nickname,								-- NICKNAME
	email,									-- EMAIL
	address,								-- ADDRESS
	dob (format: dd/mm/yyyy),				-- DOB
	credit card,							-- CREDIT CARD
	null,									-- DP
	't',	-- DO NOT CHANGE
	't',	-- DO NOT CHANGE
	date created							-- DATE CREATED
);

*/
------------------------------------------------------------------------------------------

/*
 * == User 1
 * Username: joe1
 * Inputpwd: joe1
 */
INSERT INTO 
	users (id, username, salt, password, fname, lname, nickname, email, address, dob, creditcard, dp, acctstatus, acctconfrm, acctcreated) 
VALUES (
	1,
	'joe1',
	'vUyhETbtN4T9BJMtct0rbfcAptukjAwjN/5lAtmkkTQ=',
	'195d486056b9a4d980c7db6dcffab06c190cfe01',
	'joe',
	'1',
	'Joe-boy',
	'joe@joe.com',
	'10 Joe St, Joetown',
	'1/1/1990',
	'00000000',
	null,
	't',	-- DO NOT CHANGE
	't',	-- DO NOT CHANGE
	'Thu Sep 29 22:18:40 AEST 2016'
);

/*
 * == User 2
 * Username: Intellect
 * Inputpwd: boostedanimal
 */
INSERT INTO 
	users (id, username, salt, password, fname, lname, nickname, email, address, dob, creditcard, dp, acctstatus, acctconfrm, acctcreated) 
VALUES (
	2,
	'Intellect',
	'T8BpJ7dq0fQG2AlWgexbJDq2fJCW39R/5lAtmkkTQ=',
	'20d1d58ad733eb772dfac1ff06adbc36b3a4cb61',
	'Boosted',
	'Animal',
	'Mahammad',
	'intellect@boostedanimal.com',
	'69 Boosted St, Animal, Elohell',
	'27/09/1997',
	'99999999',
	null,
	't',	-- DO NOT CHANGE
	't',	-- DO NOT CHANGE
	'Fri Sep 30 18:18:21 AEST 2016'
);

/*
 * == User 1
 * Username: comp9321
 * Inputpwd: comp9321
 */
INSERT INTO 
	users (id, username, salt, password, fname, lname, nickname, email, address, dob, creditcard, dp, acctstatus, acctconfrm, acctcreated) 
VALUES (
	3,
	'comp9321',										-- USERNAME
	'mCWY0a7R+zK7X9bslkvAkCgwKDeoBlYz5D9NAPf6SzU=',	-- SALT
	'5d85d0e75ef96f62509cad0b3ab1c11ae446ecb2',		-- ENCRYPTED PASSWORD
	'comp',											-- FIRST NAME
	'9321',									-- LAST NAME
	'Web apps',								-- NICKNAME
	'comp9321@comp9321.com',				-- EMAIL
	'webapps, UNSW',						-- ADDRESS
	'1/07/2016',							-- DOB
	'11111111',								-- CREDIT CARD
	NULL,									-- DP
	't',	-- DO NOT CHANGE
	't',	-- DO NOT CHANGE
	'Sat Oct 1 10:11:56 AEST 2016'							-- DATE CREATED
);


