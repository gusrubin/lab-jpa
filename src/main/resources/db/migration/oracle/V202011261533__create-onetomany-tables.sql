CREATE TABLE CART 
 (
 	ID	RAW(16)	PRIMARY KEY
 );
 
CREATE TABLE ITEM 
 (
 	ID		RAW(16)	PRIMARY KEY,
 	CART_ID	RAW(16),
 	CONSTRAINT FK_CART
 		FOREIGN KEY(CART_ID)
 		REFERENCES CART(ID)
 );