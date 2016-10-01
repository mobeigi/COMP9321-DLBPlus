-- This file will insert some listings


INSERT INTO
	listings(
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
		6,
		3,
		'Sat Oct 1 11:00:00 AEST 2016',
		'Sat Oct 3 11:00:00 AEST 2016',
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