<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="javax.servlet.http.Cookie, dataAccess.*"
    import="java.util.List"
    import="java.text.DecimalFormat"
    import="models.ErrorMessage"
    import="models.Message"
    import="models.User"
    import="models.Address"
    import="models.PaymentCard"
    import="models.CardType"
    import="models.OrderItem"
    import="models.Book"
    import="models.Order"
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" type="text/css" href="orderConfirmationStyle.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<title>BookBay | Order Confirmation</title>
		
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
		<script src="https://kit.fontawesome.com/0318fd2bd1.js" crossorigin="anonymous"></script>
	</head>
	
	<body>
		<!-- Main container  -->
		<div class="mainContainer">
		
			<!-- Header -->
			<div class="headerContainer">
 				<nav class="navbar navbar-expand-lg navbar-custom">
 					<a class="navbar-brand" href="Index">
<!--  						<img class="logoImg" src="img/bookbayLogo.png"> -->
						BookBay
 					</a>
 					
 					<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
 						<span class="navbar-toggler-icon"></span>
 					</button>
 					
 					<div class="collapse navbar-collapse" id="navbarSupportedContent">
 						<ul class="navbar-nav mr-auto">
 							<li>
 								<a class="nav-link" href="#">Action</a>
 							</li>
 							
 							<li>
 								<a class="nav-link" href="#">Comedy</a>
 							</li>
 							
 							<li>
 								<a class="nav-link" href="#">Family</a>
 							</li>
 							
 						    <li>
 								<a class="nav-link" href="#">Kids</a>
 							</li>
 							
 							<li>
 								<a class="nav-link" href="#">Mystery</a>
 							</li>
 							
 							<li>
 								<a class="nav-link" href="#">Horror</a>
 							</li>
 							
 							<li>
 								<a class="nav-link" href="#">Romance</a>
 							</li>
 							
 							<li>
 								<a class="nav-link" href="#">Thriller</a>
 							</li>
 						</ul>
 						
 						<form class="form-inline mr-auto" action="/WebContent/search.html">
 							<input class="form-control mr-sm-2" type="search" placeholder="Enter Book Name, ISBN, Genre, etc..." aria-label="Search" size="36">
 							<button class="btn btn-light my-2 my-sm-0" type="submit">Search</button>
 						</form>
						 
						<div class="dropdown">
							<a class="nav-link dropdown-toggle" href="#" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<img src="img/user-icon.png" class="img-fluid user-icon" alt="User Icon">
							</a>
							<form id="logoutForm" action="Logout" method="post">
								<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
									<a id="logoutBtn" class="dropdown-item">Log off</a>
									<a class="dropdown-item" href="orderhistory.html">Order history</a>
									<a class="dropdown-item" href="accountSettings.html">Account settings</a>
								</div>
							</form>
						</div>
 						
 						<a class="nav-link" href="shoppingCart.html">
                            <i class="fas fa-shopping-cart fa-2x"></i>
                         </a>
 					</div>
 				</nav>
            </div>
            
            <main role="main" class="container">

                <div class="order-confirm">
                  <h1 class="font-weight-bold">Order Confirmed</h1>
                  	<p class="lead">
                      Thank you for ordering at BookBay!
                      <br> 
                      An order confirmation with the order details below has been sent to your email address.
                	</p>
                  <a href="Index" class="text-primary">Go to homepage</a>
				</div>
				
				<div class="container">
					<br>
					<h5 class="text-center">Order details</h5>
					<br>
					<ul class="list-group list-group-flush">
					
						<%
							Order order = (Order) request.getAttribute("order");
                    		List<OrderItem> orderItems = (List<OrderItem>) request.getAttribute("orderItems");
                    		List<Book> booksForOrderItems = (List<Book>) request.getAttribute("booksForOrderItems");
                    		User user = (User) request.getAttribute("user");
                    		Address address = (Address) request.getAttribute("address");
                    		
                    		int orderID = order.getOrderID();
                    		String orderDate = order.getOrderDateTime();
                    		String totalPrice = new DecimalFormat("0.00").format(order.getTotalPrice());
                    		String firstName = user.getFirstName();
                    		String lastName = user.getLastName();
                    		
                    		String street = address.getStreet();
                    		String city = address.getCity();
                    		String state = address.getState();
                    		int zipCode = address.getZipCode();
                    		
                    		String fullAddress = street + ", " + city + ", " + state + " " + zipCode;
						%>
					
						<li class="list-group-item">Order ID: <%= orderID %></li>
						<li class="list-group-item">Order Date: <%= orderDate %></li>
						<li class="list-group-item">Total Price: $<%= totalPrice %></li>
						<li class="list-group-item">Customer Name: <%= firstName %> <%= lastName %></li>
						<li class="list-group-item">Shipping Address: <%= fullAddress %></li>
						<li class="list-group-item">Confirmation number: 2277693063</li>
					</ul>
					<br>
					<div class="row">
						<div class="col text-center">
							<a href="orderhistory.html" role="button" class="btn btn-warning">Track Order</a>
						</div>
					</div>
					<br>
					<h6 class="text-center">Ordered Items</h6>
					<div id="ordered-items" class="list-group">
					
						<%
							for(int i = 0; i < orderItems.size(); i++) {
								OrderItem currentOrderItem = orderItems.get(i);
								Book currentBook = booksForOrderItems.get(i);
								
								out.println("<a href=\"#\" class=\"list-group-item list-group-item-action\" data-toggle=\"modal\" data-target=\"#book" + (i+1) +"\">");
								out.println(currentBook.getTitle());
								out.println("</a>");
							}
						%>
					
<!-- 						<a href="#" class="list-group-item list-group-item-action" data-toggle="modal" data-target="#item-modal"> -->
<!-- 						  Harry Potter and the Chamber of Secrets by J.K. Rowling -->
<!-- 						</a> -->
						
<!-- 						<a href="#" class="list-group-item list-group-item-action" data-toggle="modal" data-target="#item-modal"> -->
<!-- 						  Harry Potter and the Chamber of Secrets by J.K. Rowling -->
<!-- 						</a> -->
						
<!-- 						<a href="#" class="list-group-item list-group-item-action" data-toggle="modal" data-target="#item-modal"> -->
<!-- 						  Harry Potter and the Chamber of Secrets by J.K. Rowling -->
<!-- 						</a> -->
					</div>
					<br>
				</div>
              
			</main>

			<p id="spacing"></p>

			<%
				for(int i = 0; i < orderItems.size(); i++) {
					OrderItem currentOrderItem = orderItems.get(i);
					Book currentBook = booksForOrderItems.get(i);
					
					String formattedPrice = new DecimalFormat("0.00").format(currentBook.getSellingPrice());
					
					out.println("<div class=\"modal fade\" id=\"book" + (i+1) + "\" tabindex=\"-1\" aria-labelledby=\"itemModalLabel\" aria-hidden=\"true\">");
					out.println("<div class=\"modal-dialog modal-dialog-centered\">");
					out.println("<div class=\"modal-content\">");
					out.println("<div class=\"modal-header\">");
					out.println("<h5 class=\"modal-title\" id=\"itemModalLabel\">" + currentBook.getTitle() + "</h5>");
					out.println("<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">");
					out.println("<span aria-hidden=\"true\">&times;</span>");
					out.println("</button>");
					out.println("</div>");
					out.println("<div class=\"modal-body text-center\">");
					out.println("<img class=\"img-fluid book-image\" src=\"" + currentBook.getImagePath() + "\">");
					out.println("<br>");
					out.println("<br>");
					out.println("<p class=\"modalText\">");
					out.println("Author: " + currentBook.getAuthor());
					out.println("<br>");
					out.println("Genre: " + currentBook.getGenre());
					out.println("<br>");
					out.println("Price: $" + formattedPrice);
					out.println("<br>");
					out.println("ISBN: " + currentBook.getIsbn());
					out.println("<br>");
					out.println("Description: " + currentBook.getDescription());
					out.println("</p>");
					out.println("</div>");
					out.println("<div class=\"modal-footer\">");
					out.println("<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">Close</button>");
					out.println("</div>");
					out.println("</div>");
					out.println("</div>");
					out.println("</div>");
				}
			%>

<!-- 			<div class="modal fade" id="item-modal" tabindex="-1" aria-labelledby="itemModalLabel" aria-hidden="true"> -->
<!-- 				<div class="modal-dialog modal-dialog-centered"> -->
<!-- 				  <div class="modal-content"> -->
<!-- 					<div class="modal-header"> -->
<!-- 					  <h5 class="modal-title" id="itemModalLabel">Harry Potter and the Chamber of Secrets</h5> -->
<!-- 					  <button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
<!-- 						<span aria-hidden="true">&times;</span> -->
<!-- 					  </button> -->
<!-- 					</div> -->
<!-- 					<div class="modal-body text-center"> -->
<!-- 						<img class="img-fluid book-image" src="img/harry potter CoS.jpg"> -->
<!-- 						<br> -->
<!-- 						<br> -->
<!-- 						<p class="modalText"> -->
<!-- 							Author: J.K. Rowling -->
<!-- 							<br> -->
<!-- 							Genre: Action -->
<!-- 							<br> -->
<!-- 							Price: $6.97 -->
<!-- 							<br> -->
<!-- 							ISBN: 876543456 -->
<!-- 							<br> -->
<!-- 							Description: Enter the world of Harry Potter. -->
<!-- 						</p> -->
<!-- 					</div> -->
<!-- 					<div class="modal-footer"> -->
<!-- 					  <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button> -->
<!-- 					</div> -->
<!-- 				  </div> -->
<!-- 				</div> -->
<!-- 			</div> -->

            <!-- Footer -->
	     	<div class="footerContainer">
                <nav class="navbar navbar-expand-lg navbar-custom">
                   <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                       <span class="navbar-toggler-icon"></span>
                   </button>
                   
                   <div class="collapse navbar-collapse" id="navbarSupportedContent">
                       <span class="navbar-text mr-auto">
                           Team A5 - CSCI4050
                       </span>
                   
                       <ul class="navbar-nav ml-auto">
                           <li>
                               <a class="nav-link" href="#">About</a>
                           </li>
                           
                           <li>
                               <a class="nav-link" href="#">Careers</a>
                           </li>
                           
                           <li>
                               <a class="nav-link" href="#">Contact</a>
                           </li>
                           
                           <li>
                               <a class="nav-link" href="#">Privacy</a>
                           </li>
                           
                           <li>
                               <a class="nav-link" href="#">Sitemap</a>
                           </li>
                           
                           <li>
                               <a class="nav-link" href="#">Terms of Use</a>
                           </li>
                       </ul>
                   </div>
               </nav>
           </div>
      </div>
      <script type="text/javascript" src="index.js"></script>
  </body>
</html>