-- This file will insert some listings into particular users carts

SET datestyle = dmy;

/*
	cartid = 20
	listingid = 1
	timestamp = '1/10/2016'
*/
INSERT INTO activecartitems(cartid, listingid, addedts)
VALUES (20, 1, '1/10/2016');