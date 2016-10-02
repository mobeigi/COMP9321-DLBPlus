/*
 * THIS SCRIPT PREPARES THE DATABASE BY INPUTTING VALUES
 * THAT SIMULATE A BASIC SITUATION / STATE OF THE ONLINE STORE.
 * 
 * MAKE SURE TO FOLLOW THE ORDER OF WHICH VALUES ARE CREATED, I.E.:
 * 		ADMINS -> USERS -> LISTINGS -> ORDERS -> ACTIVE CART ITEMS -> REMOVED CART ITEMS
 */

-- Prepare datestyle in format: dd/mm/yyyy
SET datestyle = dmy;

-- CREATE ADMINS
--------------------------------------------------

/*
 * = Admin
 * Username: 'admin'
 * Password: 'password'
 */
INSERT INTO admins (username,salt,password) VALUES ('admin', 'w5dNhgBbQJtdp8Oprd5yRdhPFaOxd8UwUtTjaQbf7qY=', 'e8951dc256818ba6f8281f6cdebf29926a9a05ff');

-- CREATE USERS
--------------------------------------------------

/*
 * == User 1
 * Username: joe1
 * Inputpwd: joe1
 */
INSERT INTO 
	users (id, username, salt, password, fname, lname, nickname, email, address, dob, creditcard, cartid, dp, acctstatus, acctconfrm, acctcreated) 
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
	20,
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
	users (id, username, salt, password, fname, lname, nickname, email, address, dob, creditcard, cartid, dp, acctstatus, acctconfrm, acctcreated) 
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
	21,
	null,
	't',	-- DO NOT CHANGE
	't',	-- DO NOT CHANGE
	'Fri Sep 30 18:18:21 AEST 2016'
);

/*
 * == User 3
 * Username: comp9321
 * Inputpwd: comp9321
 */
INSERT INTO 
	users (id, username, salt, password, fname, lname, nickname, email, address, dob, creditcard, cartid, dp, acctstatus, acctconfrm, acctcreated) 
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
	22,
	NULL,									-- DP
	't',	-- DO NOT CHANGE
	't',	-- DO NOT CHANGE
	'Sat Oct 1 10:11:56 AEST 2016'							-- DATE CREATED
);

/*
 * == User 4
 * Username: userX
 * Inputpwd: userX
 */
INSERT INTO 
	users (id, username, salt, password, fname, lname, nickname, email, address, dob, creditcard, cartid, dp, acctstatus, acctconfrm, acctcreated) 
VALUES (
	4,												-- ID
	'userX',										-- USERNAME
	'tYgMN8gbgyHSUBcFknbpI5G1wEH94GHj/26Buvtt4i4=',	-- SALT
	'f28ced829560d4f8ffd87299749771357b9ff72e',		-- ENCRYPTED PASSWORD
	'John',											-- FIRST NAME
	'Smith',									-- LAST NAME
	'Johno',								-- NICKNAME
	'john@smith.com',						-- EMAIL
	'9,U st, V',							-- ADDRESS
	'19/5/1994',							-- DOB
	'22222222',								-- CREDIT CARD
	23,										-- CART ID
	NULL,									-- DP
	't',	-- DO NOT CHANGE
	't',	-- DO NOT CHANGE
	'Sat Oct 1 10:15:00 AEST 2016'							-- DATE CREATED
);

/*
 * == User 5
 * Username: userY
 * Inputpwd: userY
 */
INSERT INTO 
	users (id, username, salt, password, fname, lname, nickname, email, address, dob, creditcard, cartid, dp, acctstatus, acctconfrm, acctcreated) 
VALUES (
	5,												-- ID
	'userY',										-- USERNAME
	'KlthVvsHyCBNC3mGWzEytg/kO2cyXEu9Pa5M1Qvnfjk=',	-- SALT
	'2a15fc0becd79279720c3c458685d43d0407662b',		-- ENCRYPTED PASSWORD
	'Sam',											-- FIRST NAME
	'Smith',									-- LAST NAME
	'Sammo',								-- NICKNAME
	'sam@smith.com',						-- EMAIL
	'9,U st, V',							-- ADDRESS
	'19/5/1994',							-- DOB
	'33333333',								-- CREDIT CARD
	24,										-- CART ID
	NULL,									-- DP
	't',	-- DO NOT CHANGE
	't',	-- DO NOT CHANGE
	'Sat Oct 1 10:18:00 AEST 2016'							-- DATE CREATED
);

/*
 * == User 6
 * Username: isellstuff
 * Inputpwd: isellstuff
 */
INSERT INTO 
	users (id, username, salt, password, fname, lname, nickname, email, address, dob, creditcard, cartid, dp, acctstatus, acctconfrm, acctcreated) 
VALUES (
	6,												-- ID
	'isellstuff',										-- USERNAME
	'ofq+FUksns/ScT2go5GHgwdyOxWmdxVp08xktImq4lA=',	-- SALT
	'8bf3a8133d566feb8bc0cf6d50b0a39bab11e66e',		-- ENCRYPTED PASSWORD
	'Drug',											-- FIRST NAME
	'Dealer',									-- LAST NAME
	'Sello',								-- NICKNAME
	'drug@drugs.com',						-- EMAIL
	'99, Suss Ave, Drugsville',				-- ADDRESS
	'1/1/1100',								-- DOB
	'12345678',								-- CREDIT CARD
	25,										-- CART ID
	NULL,									-- DP
	't',	-- DO NOT CHANGE
	't',	-- DO NOT CHANGE
	'Sat Oct 1 10:21:00 AEST 2016'							-- DATE CREATED
);

-- CREATE LISTINGS
--------------------------------------------------

/*
 * = Listing 1
 */
INSERT INTO
	listings(
		id,
		sellerid,
		quantity,
		listdate,
		enddate,
		sellprice,
		paused,
		numviews,
		"type",
		authors,
		editors,
		title,
		venues,
		year,
		address,
		month,
		publisher,
		rating
	)
VALUES (
		1,
		6,
		3,
		'Sat Oct 1 11:00:00 AEST 2016',
		'Tue Oct 3 11:00:00 AEST 2016',
		'69.00',
		'f',
		15,
		'article',
		'Matthew Tan',
		'Ian Wong',
		'How to get HDs at uni',
		'UNSW Australia',
		2016,
		'Sydney',
		'April',
		'CSE',
		'EXCELLENT'
);

/*
 * = Listing 2
 */
INSERT INTO
	listings(
		id,
		sellerid,
		quantity,
		listdate,
		enddate,
		sellprice,
		paused,
		numviews,
		"type",
		authors,
		title,
		year,
		month
	)
VALUES (
		2,
		4,				-- user: userX
		10,
		'Tue Aug 9 19:00:00 AEST 2016',		
		'Thu Nov 17 19:00:00 AEST 2016',
		'99.99',
		'f',
		103,
		'www',
		'Amin Behesti|Erik Zhong',
		'Web applications for Beginners',
		2009,
		'June'
);

/*
 * Listing 3
 */
INSERT INTO
	listings(
		id,
		sellerid,					-- seller: userX
		quantity,					-- quantity
		listdate,
		enddate,
		sellprice,			-- sellprice
		paused,				-- paused		
		numviews,					-- num views
		"type",	-- type
		authors,	-- authors
		editors,					-- editors
		title, -- title
		venues,		-- venues
		year,					-- year
		volume,					-- vol
		month,				-- month
		note,	-- note
		series,			-- series
		rating							-- rating
	)
VALUES (
		3,
		4,					-- seller: userX
		10,					-- quantity
		'Sat Oct 1 09:00:00 AEST 2016',
		'Sat Oct 5 11:00:00 AEST 2016',
		'55.00',			-- sellprice
		't',				-- paused		
		90,					-- num views
		'inproceedings',	-- type
		'Sandeep|Teuku|Ali',	-- authors
		'Amin',					-- editors
		'How to punish students when it comes to giving out creative marks', -- title
		'Scientia Hall',		-- venues
		'2010',					-- year
		'XII',					-- vol
		'January',				-- month
		'Should use this for web apps',	-- note
		'Training individuals',			-- series
		'POOR'							-- rating
);

-- CREATE ORDERS
--------------------------------------------------

/*
 * = Order 1
*/
INSERT INTO 
	orders(id, buyerid, sellerid, pubtitle, order_date, price)
VALUES (
	1,	
	1,		-- buyer: joe1
	4,		-- seller: userX
	'How to get HDs at uni',
	'Friday Sep 30 11:00:00 AEST 2016',
	'69.00'
);

/*
 * = Order 2
*/
INSERT INTO 
	orders(id, buyerid, sellerid, pubtitle, order_date, price)
VALUES (
	2,	
	2,		-- buyer: intellect
	4,		-- seller: userX
	'How to get HDs at uni',
	'Sat Oct 1 20:15:38 AEST 2016',
	'69.00'
);

-- CREATE ACTIVE CART ITEMS
--------------------------------------------------

/*
 * = activecartitem 1
*/
INSERT INTO activecartitems(cartid, listingid, addedts)
VALUES (
	20, 			-- username: joe1
	1, 
	'1/10/2016'
);

-- CREATE REMOVED CART ITEMS
--------------------------------------------------

/*
 * = removed cart item 1
 */
INSERT INTO
	removedcartitems(cartid,listingid,addedts,removedts)
VALUES (
	21,			-- username: Intellect
	2,
	'Mon Sep 26 20:00:00 AEST 2016',
	'Tue Sep 27 06:08:07 AEST 2016'
);
