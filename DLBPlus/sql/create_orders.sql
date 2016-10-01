-- This file will create some orders

/*
 * TEMPLATE

INSERT INTO 
	orders(id, buyerid, sellerid, pubtitle, order_date, price)
VALUES (
	id,
	buyerid,
	sellerid,
	pubtitle,
	order_date,
	price
);
*/

/*
 * = Order 1
*/
INSERT INTO 
	orders(id, buyerid, sellerid, pubtitle, order_date, price)
VALUES (
	1,	
	1,		-- username: joe1
	4,		-- username: userX
	'How to get HDs at uni',
	'Friday Sep 30 11:00:00 AEST 2016',
	'69.00'
);

/*
 * = Order 1
*/
INSERT INTO 
	orders(id, buyerid, sellerid, pubtitle, order_date, price)
VALUES (
	2,	
	2,		-- username: intellect
	4,		-- username: userX
	'How to get HDs at uni',
	'Sat Oct 1 20:15:38 AEST 2016',
	'69.00'
);