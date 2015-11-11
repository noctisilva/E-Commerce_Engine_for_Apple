# E-Commerce_Engine_for_Apple


	Table of Contents
	------------------

	I) What is it?

   	II) Logging in
		A) Logging in
	    B) Customer Interface
	    C) Employee Interface
	    D) Manager Interface 

	III) Sign up

	IV) Shop

  	V) Exit

  	VI) Test Cases
  	------------------

  	*********************************************************************

  		PLEASE READ THE SCANNED PAGES IN FOLDER. Thank you.
  		SCANNED PAGES HOLD: 
  		- TEST CASES
  		- INPUT TREE
  		- LEGEND
  		- KEY INFORMATION

  	*********************************************************************

	I) What is it?
	------------------

	This will be a documentation on how the software will work. 
	You will be able to see how and why some of the these functions 
	work and where these methods exist.

	Every user input has a fail safe system where the user will alaways have a chance to redo the input.

	I chose Apple (either use a real one as your model or a made-up one). To keep the project within bounds, I ignored issues of employees, corporate finance, etc., and focus on the retail sales activities. Apple sells a large variety of products at multiple stores. Not all products are at all stores. Each store has its own inventory of products and decides when to reorder and in what quantity. Customers may identify themselves by joining your frequent-shopper program. Others may identified only by the credit card used. Others may remain anonymous and pay by cash. Appler has a Web site that accepts orders. From a database perspective it is just a special store that has no physical location and has no anonymous customers. The database tracks inventory at each store, customer purchases (by market basket and by customer, where possible), sales history by store, etc. Various user interfaces and applications access the database to record sales, initiate reorders, process new orders that arrive, etc.


   	II) Documentation
	------------------

		A)  Logging in

			Before the actual app even begins after the user runs "", the user will have to enter in Tae Hong Min's Lehigh credentials to boot up the software in the terminal. Tae Hong Min's credentials can be found on coursesite.

			After being authenticated using Tae's credentials, the user will be prompted to chose from the following options: Log in, Sign in, or quit. 

			If chosen to log in, the user will have to choose between customer, employee, and manager and will have to enter in the username and password to log into their respective dashboard.

			If chosen to sign up, the user will enter the their name, email, and be generated a password for their credentials.

			If chose to quit, the software will exit the program.

		B) Customer Interface

			If the user chooses to log in as the customer interfaces the customer will be prompted to choose between: Shop, Cart, Check Out, Store, Account, Quit

			i)  Shop

				If the customer chooses the shop option, the user will have the options: Macbook, iPhone, iPad, iPod, Go Back, and Log Out

					a)  Macbook, iPhone, iPad, iPod 

						User will be asked a series of questions about the preference of the specs of the device and will hav the confirmation option to definitely put it into cart or not.

						The user cart, billing, and inventory will be updated.

						These actions will be logged into the transactions.

				    b)  Go back

						User will be able to go back a step in the menu interface.

				    c)  Log Out

				  		User will be logged out and the software will shut down.
			ii) Cart

				If the customer chooses the cart option, the user will have the options: Check Out, Remove Items, Shop, and Go Back

					a)  Checkout

						User will be able to view the cart automatically and have the option to: Pay, Edit Cart, Shop, and Quit.

						  - Pay

						  	User has the option to pay in either Debit, Credit and Cash. If chosen Debit of Cash, the user has to put in the correct length of the debit/credit card number in order to authenticate the credentials. If choose cash, you can simply just pay cash.
						  
					b)  Remove Items

						 User will be able to remove from cart a mac, iphone, ipad, or ipod.

					c)  Shop

						User will be able to go back to shop.

					d)  Quit

						The software will quit.

		 	iii)Check Out

		 		See above.

		  	iv) Store

		  		User will be able to see the email, number, address and hours depending on the city inp666666666666666666666632 ut that they type in. 

		   	v)	Account

		   		User is able to see his/her account information.

		C) Employee Interface

			If the user chooses to log in as the employee interfaces the customer will be prompted to choose between: Inventory, Transactions, Store, Log Out, Quit

		   	i)	Inventory

		   		Employee is able to view the inventory of the store that the he/she works at. Employee is also able to view the general online inventory. Employee is then either able to go back or quit.

		   	ii) Transactions

				Employee is able to input any customer id and see all of their transaction history.

		  	iii) Store

		   		Employee is able to see the store information of his or her employment location. 

		   	iv) Log Out

		   		Employee is able to go back and one directory menu.

		   	v)  Quit

		   		The software quits.

		D)  Manager Interface

			If the user chooses to log in as the manager interface, the manager can choose from Inventory, Transactions, Store, Analytics, or Log Out options.  
			i)	Inventory

				Manager is able to view the inventory of the store that the he/she works at. Manager is also able to view the general online inventory. 

				Manager is able to restock the inventory to the inventory of any stores.Manager is then either able to go back or quit.

		   	ii) Transactions

				Manager is able to input any customer id and see all of their transaction history.

		   	iii) Store

		   		Manager is able to see the store information of his or her employment location. 

		   	iv) Analytics

		   		Manager can see all the OLAP Analytics for Sales by product sales by type sales by month:

		   			a) Product   Sales Per Year    Total%         Best Selling Rank

		   			b) Total Sales (Quantity)   Total Sales ($) 

		   			c) Macbook analytics:

		   				- Top 5 Highest Bought Month  Ranking    Highest Ranking  Frequency% per day

		   			d) iPhone analytics:

		   				- Top 5 Highest Bought Month  Ranking    Highest Ranking  Frequency% per day

		   			e) iPod analytics:

		   				- Top 5 Highest Bought Month  Ranking    Highest Ranking  Frequency% per day

		   			f) iPad analytics:

		   				- Top 5 Highest Bought Month  Ranking    Highest Ranking  Frequency% per day

		   	v)  Log Out

		   		Manager is able to go back and one directory menu.

		   	vi) Quit

		   		The software quits.

  	III) Test Cases
	-----------------

	Test Cases

	Logging in: 

	Enter your user id for Oracle: 
	Enter password for user: ************* 

	The rest is in the scanned PDF file. Please check there for test cases and use input tree.

