
# Restful service to request products in category 600001506 that have a price reduction

## How to build and run the application ?

1. Download the code from the github and run the following command

		mvn clean package

2. Run the following command to start the service

		mvn spring-boot:run

## REST Endpoint Definition:

### 3. To find the products that have a price reduction

	This endpoint returns an array of products that have a price reduction and sorted to show highest price reduction
	 first. Price reduction is calculated using price.was - price.now
	
		Method  :   GET

		URL :   http://localhost:8080/categories/600001506/products
		
		Query Param :  labelType
		
		    An optional query parm can be set to any of:
		    
		       * ShowWasNow
		       * ShowWasThenNow
		       * ShowPercentDiscount
		
	
	    Eg: URL with Query Param: 
	    http://localhost:8080/categories/600001506/products?labelType=ShowWasNow
	    
	    * If the query param is not set then the default format is ShowWasNow
