<!--  
	orderSummary.jsp
	
	Austin Schultz (als93978)
	
	This page implements the Order Summary and Confirmation page in
	the Outline document on the Google Drive.
-->

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
		<link rel="stylesheet" type="text/css" href="orderSummaryStyle.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<title>BookBay | Order Summary</title>
		
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
						<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-book-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
						  <path fill-rule="evenodd" d="M8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783z"/>
						</svg>
						
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
 						
<!--  						<div class="dropdown"> -->
<!-- 							<a class="nav-link dropdown-toggle" href="#" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
<!-- 								<img src="img/user-icon.png" class="img-fluid user-icon" alt="User Icon"> -->
<!-- 							</a> -->
<!-- 							<form id="logoutForm" action="Logout" method="post"> -->
<!-- 								<div class="dropdown-menu" aria-labelledby="dropdownMenuButton"> -->
<!-- 									<a id="logoutBtn" class="dropdown-item">Log off</a> -->
<!-- 									<a class="dropdown-item" href="orderhistory.html">Order history</a> -->
<!-- 									<a class="dropdown-item" href="EditProfile">Account settings</a> -->
<!-- 								</div> -->
<!-- 							</form> -->
<!-- 						</div> -->

						<%
							out.println("<div class=\"dropdown\">");
							out.println("<a class=\"nav-link dropdown-toggle\" href=\"#\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">");
							out.println("<img src=\"img/user-icon.png\" class=\"img-fluid user-icon\" alt=\"User Icon\">");
							out.println("</a>");
							out.println("<form id=\"logoutForm\" action=\"Logout\" method=\"post\">");
							out.println("<div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\">");
							out.println("<a id=\"logoutBtn\" class=\"dropdown-item\" href=\"Logout\">Log off</a>");
							out.println("<a class=\"dropdown-item\" href=\"orderhistory.html\">Order history</a>");
							out.println("<a class=\"dropdown-item\" href=\"EditProfile\">Account settings</a>");
							out.println("</div>");
							out.println("</form>");
							out.println("</div>");
						%>
 						
 						<a class="nav-link" href="ShoppingCart">
							 <i class="fas fa-shopping-cart fa-2x"></i>
 						</a>
 					</div>
 				</nav>
			</div>
			
			<!-- Content  -->
			<div class="contentContainer">
				<div class="content">
				
					<%
						Message message = (Message) request.getAttribute("message");
										
						if(message != null) {
							out.println("<div class=\"alert alert-success\" role=\"alert\" style=\"display: block\">");
							out.println("\t" + message.getMessage());
							out.println("</div>");
						}
					%>
					
					<%
						ErrorMessage errorMessage = (ErrorMessage) request.getAttribute("errorMessage");
										
						if(errorMessage != null) {
							out.println("<div class=\"alert alert-danger\" role=\"alert\" style=\"display: block\">");
							out.println("\t" + errorMessage.getMessage());
							out.println("</div>");
						}
					%>
				
					<div class="orderSummaryArea align-middle mx-auto">
						<div class="card orderSummaryCard">
							<div class="card-header">
								Order Summary
							</div>
							
							<div class="card-body">
								<ul class="list-group">
								
									<%
										List<OrderItem> orderItems = (List<OrderItem>) request.getAttribute("orderItems");
										List<Book> booksForOrderItems = (List<Book>) request.getAttribute("booksForOrderItems");
										Order order = (Order) request.getAttribute("order");
										
										String subtotal = (String) request.getAttribute("subtotal");										
										String tax = (String) request.getAttribute("tax");
										String total = (String) request.getAttribute("total");
										
										int promoRawPercent = -1;
										float promoDiscount = -1;
										if(order.getPromotionCode() != 0) {
											promoRawPercent = (int) request.getAttribute("promoRawPercent");
											promoDiscount = (float) request.getAttribute("promoDiscount");
										}
										
										for(int i = 0; i < orderItems.size(); i++) {
											OrderItem currentOrderItem = orderItems.get(i);
											Book currentBook = booksForOrderItems.get(i);
											String totalPrice = new DecimalFormat("0.00").format(currentOrderItem.getQuantity() * currentBook.getSellingPrice());
											
											out.println("<li class=\"list-group-item d-flex justify-content-between lh-condensed\">");
											
											out.println("<div>");
											
											if(currentOrderItem.getQuantity() > 1)
												out.println("<h6>" + currentBook.getTitle() + " (" + currentOrderItem.getQuantity() + ")" + "</h6>");
											else
												out.println("<h6>" + currentBook.getTitle() + "</h6>");
											
											out.println("<small class=\"text-muted\">" + currentBook.getAuthor() + "</small>");
											out.println("</div>");
											out.println("<span class=\"text-muted\">$" + totalPrice + "</span>");
											
											out.println("</li>");
										}
									%>
								
<!-- 									<li class="list-group-item d-flex justify-content-between lh-condensed"> -->
<!-- 										<div> -->
<!-- 											<h6>Head First Web Design</h6> -->
<!-- 											<small class="text-muted">Ethan Watrall, Jeff Siarto</small> -->
<!-- 										</div> -->
<!-- 										<span class="text-muted">$9.99</span> -->
<!-- 									</li> -->

<!-- 									<li class="list-group-item d-flex justify-content-between lh-condensed"> -->
<!-- 										<div> -->
<!-- 											<h6>The Great Gatsby (2)</h6> -->
<!-- 											<small class="text-muted">F. Scott Fitzgerald</small> -->
<!-- 										</div> -->
<!-- 										<span class="text-muted">$19.98</span> -->
<!-- 									</li> -->

<!-- 									<li class="list-group-item d-flex justify-content-between lh-condensed"> -->
<!-- 										<div> -->
<!-- 											<h6>The Catcher in the Rye</h6> -->
<!-- 											<small class="text-muted">J.D. Saligner</small> -->
<!-- 										</div> -->
<!-- 										<span class="text-muted">$9.99</span> -->
<!-- 									</li> -->

<!-- 									<li class="list-group-item d-flex justify-content-between lh-condensed"> -->
<!-- 										<div> -->
<!-- 											<h6>Catch-22</h6> -->
<!-- 											<small class="text-muted">Joseph Heller</small> -->
<!-- 										</div> -->
<!-- 										<span class="text-muted">$9.99</span> -->
<!-- 									</li> -->

									<li class="list-group-item">
										<h6>Subtotal: $<%= subtotal %></h6>
									</li>
									
									<li class="list-group-item">
										<h6>Tax: $<%= tax %></h6>
									</li>

									<%
										if(promoRawPercent != -1 && promoDiscount != -1) {
											out.println("<li class=\"list-group-item d-flex justify-content-between bg-light\">");
											
											out.println("<div class=\"text-success\">");
											out.println("<h6 class=\"my-0\">Promo Code</h6>");
											out.println("<small>" + promoRawPercent + "OFFCOUP</small>");
											out.println("</div>");
											out.println("<span class=\"text-success\">-$" + new DecimalFormat("0.00").format(promoDiscount) + "</span>");
											
											out.println("</li>");
										}
									%>

<!-- 									<li class="list-group-item d-flex justify-content-between bg-light"> -->
<!-- 										<div class="text-success"> -->
<!-- 											<h6 class="my-0">Promo Code</h6> -->
<!-- 											<small>20OFFCOUP</small> -->
<!-- 										</div> -->
<!-- 										<span class="text-success">-$20.00</span> -->
<!-- 									</li> -->
									
									<li class="list-group-item">
										<h6>Total: $<%= total %></h6>
									</li>
								</ul>
								
								<%
									HttpSession infoSession = request.getSession();
										
									infoSession.setAttribute("orderItems", orderItems);
									infoSession.setAttribute("booksForOrderItems", booksForOrderItems);
									infoSession.setAttribute("order", order);
									
									infoSession.setAttribute("subtotal", subtotal);
									infoSession.setAttribute("tax", tax);
									infoSession.setAttribute("total", total);
								%>
								
								<form action="ApplyPromo" method="POST" accept-charset="UTF-8">
									<div class="input-group mb-3 mt-3 promoCode">
										<div class="input-group-prepend">
											<span class="input-group-text" id="promoAddon">Promo Code</span>
										</div>
											<input type="text" class="form-control" name="promoCode" aria-label="promoCode" aria-describedby="promoAddon">
										
											<button type="submit" class="btn btn-secondary ml-2">Submit</button>
									</div>
								</form>
								
								<form method="POST" accept-charset="UTF-8">
									<button type="submit" class="btn btn-success" formaction="SubmitOrder">Submit Order</button>
									<button type="submit" class="btn btn-info ml-3" formaction="ShoppingCart">Update Order</button>
								</form>
							</div>
						</div>
					</div>
				</div>
	    	</div>
	    	
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
	</body>
</html>