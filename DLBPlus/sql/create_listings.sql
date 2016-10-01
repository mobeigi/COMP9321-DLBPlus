-- This file will insert some listings
SET datestyle = dmy;

/*
 * Template
INSERT INTO
	listings(
		id,
		sellerid,
		quantity,
		listdate,
		enddate,
		sellprice,
		image,
		paused,
		numviews,
		"type",
		authors,
		editors,
		title,
		venues,
		pages,
		year,
		address,
		volume,
		month,
		urls,
		ees,
		cdrom,
		cites,
		publisher,
		note,
		crossref,
		isbns,
		series,
		chapter,
		rating
	)
VALUES (
		id,
		sellerid,
		quantity,
		listdate,
		enddate,
		sellprice,
		image,
		paused,
		numviews,
		"type",
		authors,
		editors,
		title,
		venues,
		pages,
		year,
		address,
		volume,
		month,
		urls,
		ees,
		cdrom,
		cites,
		publisher,
		note,
		crossref,
		isbns,
		series,
		chapter,
		rating
);

 */

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

