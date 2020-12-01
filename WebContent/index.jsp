<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="javax.servlet.http.Cookie, dataAccess.*"
    import="java.util.List"
    import="models.Book"
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" type="text/css" href="indexStyle.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<title>BookBay | Home</title>
		
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
 						
 						<ul class="navbar-nav">
 							<li>
 								<a class="nav-link" href="login.jsp">Login</a>
 							</li>
 							
 							<li>
 								<a class="nav-link" href="registration.jsp">Register</a>
 							</li>
 						</ul>
 						
 						<a class="nav-link" href="shoppingCart.html">
							 <i class="fas fa-shopping-cart fa-2x"></i>
							 <span class="badge badge-dark badge-pill">4</span>
 						</a>
 					</div>
 				</nav>
			</div>
			
			<!-- Homepage content by Jonah Kim  -->
			<div class="contentContainer">
				<div class="content">
					<div id="featured">
						<h1 style="text-align: center; margin-bottom: 5%;">Featured Books</h1>
					</div>
					<!-- <div id="shelf" class="row" style="background-image: url(img/shelf.png); background-size: cover; background-repeat: no-repeat; background-position: center;">
						<div class="col-md-3 book-image1 book">
							<button type="button" class="btn btn-primary center" data-toggle="modal" data-target="#book1" style="background-color: transparent; border: none;">
								<img src="img/book1.png" class="rounded mx-auto d-block">
							</button>
						</div>
						<div class="col-md-3 book-image2 book">
							<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#book2" style="background-color: transparent; border: none;">
								<img src="img/book2.png" class="rounded mx-auto d-block">
							</button>
						</div>
						<div class="col-md-3 book-image3 book">
							<button type="button" class="btn btn-primary center" data-toggle="modal" data-target="#book3" style="background-color: transparent; border: none;">
								<img src="img/book3.png" class="rounded mx-auto d-block">
							</button>
						</div>
						<div class="col-md-3 book-image4 book">
							<button type="button" class="btn btn-primary center" data-toggle="modal" data-target="#book4" style="background-color: transparent; border: none;">
								<img src="img/book4.png" class="rounded mx-auto d-block">
							</button>
						</div>
					</div> -->
					
					<!-- FEATURED BOOKS -->
					<div class="row row-cols-4 shelf">
					
						<%
							List<Book> featuredBooks = (List<Book>) request.getAttribute("featuredBooks");
							List<Book> topSellingBooks = (List<Book>) request.getAttribute("topSellingBooks");
							
							int numOfBooksPerShelf = 4;
							
							String imgWidth = "158";
							String imgHeight = "239";
							
							for(int i = 0; i < numOfBooksPerShelf; i++) {
								Book currentBook = featuredBooks.get(i);
								
								out.println("<div class=\"col book\">");
								
								out.println("<div class=\"bookContainer\">");
								out.println("<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#book" + (i+1) + "\" style=\"background-color: transparent; border: none;\">");
								out.println("<img width=\"" + imgWidth + "\" height=\"" + imgHeight + "\" src=\"" + currentBook.getImagePath() + "\" class=\"rounded mx-auto\">");
								out.println("</button>");
								out.println("</div>");
								
								out.println("</div>");
							}
						%>
						
<!-- 						<div class="col book"> -->
<!-- 							<div class="bookContainer"> -->
<!-- 								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#book1" style="background-color: transparent; border: none;"> -->
<!-- 									<img width="158" height="239" src="img/book1.png" class="rounded mx-auto"> -->
<!-- 								</button> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col book"> -->
<!-- 							<div class="bookContainer"> -->
<!-- 								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#book2" style="background-color: transparent; border: none;"> -->
<!-- 									<img width="158" height="239" src="img/book2.png" class="rounded mx-auto"> -->
<!-- 								</button> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col book"> -->
<!-- 							<div class="bookContainer"> -->
<!-- 								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#book3" style="background-color: transparent; border: none;"> -->
<!-- 									<img width="158" height="239" src="img/book3.png" class="rounded mx-auto"> -->
<!-- 								</button> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col book"> -->
<!-- 							<div class="bookContainer"> -->
<!-- 								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#book4" style="background-color: transparent; border: none;"> -->
<!-- 									<img width="158" height="239" src="img/book4.png" class="rounded mx-auto"> -->
<!-- 								</button> -->
<!-- 							</div> -->
<!-- 						</div> -->
					</div>
					<!-- <div class="col-md-3 book-image1 book">
						<button type="button" class="btn btn-primary center" data-toggle="modal" data-target="#book1" style="background-color: transparent; border: none;">
							<img src="img/book1.png" class="rounded mx-auto d-block">
						</button>
					</div>
					<div class="col-md-3 book-image2 book">
						<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#book2" style="background-color: transparent; border: none;">
							<img src="img/book2.png" class="rounded mx-auto d-block">
						</button>
					</div>
					<div class="col-md-3 book-image3 book">
						<button type="button" class="btn btn-primary center" data-toggle="modal" data-target="#book3" style="background-color: transparent; border: none;">
							<img src="img/book3.png" class="rounded mx-auto d-block">
						</button>
					</div>
					<div class="col-md-3 book-image4 book">
						<button type="button" class="btn btn-primary center" data-toggle="modal" data-target="#book4" style="background-color: transparent; border: none;">
							<img src="img/book4.png" class="rounded mx-auto d-block">
						</button>
					</div> -->
					<br>
					<div id="top">
						<h1 style="text-align: center; margin-bottom: 5%;">Top Sellers</h1>
					</div>
					
					<!-- TOP SELLERS -->
					<div class="row row-cols-4 shelf">
					
						<%
							for(int i = 0; i < numOfBooksPerShelf; i++) {
								Book currentBook = topSellingBooks.get(i);
								
								out.println("<div class=\"col book\">");
								
								out.println("<div class=\"bookContainer\">");
								out.println("<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#book" + (numOfBooksPerShelf + (i+1)) + "\" style=\"background-color: transparent; border: none;\">");
								out.println("<img width=\"" + imgWidth + "\" height=\"" + imgHeight + "\" src=\"" + currentBook.getImagePath() + "\" class=\"rounded mx-auto\">");
								out.println("</button>");
								out.println("</div>");
								
								out.println("</div>");
							}
						%>
					
<!-- 						<div class="col book"> -->
<!-- 							<div class="bookContainer"> -->
<!-- 								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#book1" style="background-color: transparent; border: none;"> -->
<!-- 									<img width="158" height="239" src="img/book1.png" class="rounded mx-auto"> -->
<!-- 								</button> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col book"> -->
<!-- 							<div class="bookContainer"> -->
<!-- 								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#book2" style="background-color: transparent; border: none;"> -->
<!-- 									<img width="158" height="239" src="img/book2.png" class="rounded mx-auto"> -->
<!-- 								</button> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col book"> -->
<!-- 							<div class="bookContainer"> -->
<!-- 								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#book3" style="background-color: transparent; border: none;"> -->
<!-- 									<img width="158" height="239" src="img/book3.png" class="rounded mx-auto"> -->
<!-- 								</button> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col book"> -->
<!-- 							<div class="bookContainer"> -->
<!-- 								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#book4" style="background-color: transparent; border: none;"> -->
<!-- 									<img width="158" height="239" src="img/book4.png" class="rounded mx-auto"> -->
<!-- 								</button> -->
<!-- 							</div> -->
<!-- 						</div> -->
					</div>			
			
					<!-- MODALS -->
					<div class="modalContainer">
					
						<%
							// Featured books modals
							for(int i = 0; i < numOfBooksPerShelf; i++) {
								Book currentBook = featuredBooks.get(i);
								
								out.println("<div class=\"modal fade\" id=\"book" + (i+1) + "\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">");
								out.println("<div class=\"modal-dialog\">");
								out.println("<div class=\"modal-content\">");
								out.println("<div class=\"modal-header\">");
								out.println("<h5 class=\"modal-title\" id=\"exampleModalLabel\">" + currentBook.getTitle() + "</h5>");
								out.println("</div>");
								out.println("<form id=\"book1ModalForm\" method=\"POST\" accept-charset=\"UTF-8\">");
								out.println("<input type=\"hidden\" name=\"bookID\" value=\"" + currentBook.getBookID() + "\"/>");
								out.println("<div class=\"modal-body\">");
								out.println("<img width=\"" + imgWidth + "\" height=\"" + imgHeight + "\" src=\"" + currentBook.getImagePath() + "\">");
								out.println("<p class=\"modalText\">");
								out.println("Author: " + currentBook.getAuthor());
								out.println("<br><br>");
								out.println("Genre: " + currentBook.getGenre());
								out.println("<br><br>");
								out.println("Price: $" + currentBook.getSellingPrice());
								out.println("<br><br>");
								out.println("ISBN: " + currentBook.getIsbn());
								out.println("<br><br>");
								out.println("Description: " + currentBook.getDescription());
								out.println("</p>");
								out.println("</div>");
								out.println("<div class=\"modal-footer\">");
								out.println("<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>");
								out.println("<button type=\"button\" class=\"btn btn-primary\" formaction=\"\">Add to cart</button>");
								out.println("</div>");
								out.println("</form>");
								out.println("</div>");
								out.println("</div>");
								out.println("</div>");
							}
						
							// Top Selling books modals
							for(int i = 0; i < numOfBooksPerShelf; i++) {
								Book currentBook = topSellingBooks.get(i);
								
								out.println("<div class=\"modal fade\" id=\"book" + (numOfBooksPerShelf + (i+1)) + "\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">");
								out.println("<div class=\"modal-dialog\">");
								out.println("<div class=\"modal-content\">");
								out.println("<div class=\"modal-header\">");
								out.println("<h5 class=\"modal-title\" id=\"exampleModalLabel\">" + currentBook.getTitle() + "</h5>");
								out.println("</div>");
								out.println("<form id=\"book1ModalForm\" method=\"POST\" accept-charset=\"UTF-8\">");
								out.println("<input type=\"hidden\" name=\"bookID\" value=\"" + currentBook.getBookID() + "\"/>");
								out.println("<div class=\"modal-body\">");
								out.println("<img width=\"" + imgWidth + "\" height=\"" + imgHeight + "\" src=\"" + currentBook.getImagePath() + "\">");
								out.println("<p class=\"modalText\">");
								out.println("Author: " + currentBook.getAuthor());
								out.println("<br><br>");
								out.println("Genre: " + currentBook.getGenre());
								out.println("<br><br>");
								out.println("Price: $" + currentBook.getSellingPrice());
								out.println("<br><br>");
								out.println("ISBN: " + currentBook.getIsbn());
								out.println("<br><br>");
								out.println("Description: " + currentBook.getDescription());
								out.println("</p>");
								out.println("</div>");
								out.println("<div class=\"modal-footer\">");
								out.println("<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>");
								out.println("<button type=\"button\" class=\"btn btn-primary\" formaction=\"\">Add to cart</button>");
								out.println("</div>");
								out.println("</form>");
								out.println("</div>");
								out.println("</div>");
								out.println("</div>");
							}
						%>
					
<!-- 						<div class="modal fade" id="book1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
<!-- 							<div class="modal-dialog"> -->
<!-- 								<div class="modal-content"> -->
<!-- 									<div class="modal-header"> -->
<!-- 										<h5 class="modal-title" id="exampleModalLabel">Harry Potter and the Sorcerer's Stone by J.K. Rowling</h5> -->
<!-- 									</div> -->
<!-- 									<form id="book1ModalForm" method="POST" accept-charset="UTF-8"> -->
<!-- 										<div class="modal-body"> -->
<!-- 											<img src="img/book1.png"> -->
											
<!-- 											<p class="modalText"> -->
<!-- 												Author: J.K. Rowling -->
<!-- 												<br> -->
<!-- 												Genre: Action -->
<!-- 												<br> -->
<!-- 												Price: $19.99 -->
<!-- 												<br> -->
<!-- 												ISBN: 876543456 -->
<!-- 												<br> -->
<!-- 												Description: Enter the world of Harry Potter. -->
<!-- 											</p> -->
<!-- 										</div> -->
<!-- 										<div class="modal-footer"> -->
<!-- 											<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!-- 											<button type="button" class="btn btn-primary" formaction="">Add to cart</button> -->
<!-- 										</div> -->
<!-- 									</form> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="modal fade" id="book2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
<!-- 							<div class="modal-dialog"> -->
<!-- 								<div class="modal-content"> -->
<!-- 									<div class="modal-header"> -->
<!-- 										<h5 class="modal-title" id="exampleModalLabel">The Outsider by Stephen King</h5> -->
<!-- 									</div> -->
									
<!-- 									<div class="modal-body"> -->
<!-- 										<img src="img/book2.png"> -->
										
<!-- 										<p class="modalText"> -->
<!-- 											Author: Stephen King -->
<!-- 											<br> -->
<!-- 											Genre: Horror -->
<!-- 											<br> -->
<!-- 											Price: $15.99 -->
<!-- 											<br> -->
<!-- 											ISBN: 828765678 -->
<!-- 											<br> -->
<!-- 											Description: Scary stuff. -->
<!-- 										</p> -->
<!-- 									</div> -->
<!-- 									<div class="modal-footer"> -->
<!-- 										<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!-- 										<button type="button" class="btn btn-primary">Add to cart</button> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="modal fade" id="book3" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
<!-- 							<div class="modal-dialog"> -->
<!-- 								<div class="modal-content"> -->
<!-- 									<div class="modal-header"> -->
<!-- 										<h5 class="modal-title" id="exampleModalLabel">Crazy Rich Asians by Kevin Kwan</h5> -->
<!-- 									</div> -->
<!-- 									<div class="modal-body"> -->
<!-- 										<img src="img/book3.png"> -->
										
<!-- 										<p class="modal-text"> -->
<!-- 											Author: Kevin Kwan -->
<!-- 											<br> -->
<!-- 											Genre: Romance -->
<!-- 											<br> -->
<!-- 											Price: $20.99 -->
<!-- 											<br> -->
<!-- 											ISBN: 876543445 -->
<!-- 											<br> -->
<!-- 											Description: Rich asians. -->
<!-- 										</p> -->
<!-- 									</div> -->
<!-- 									<div class="modal-footer"> -->
<!-- 										<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!-- 										<button type="button" class="btn btn-primary">Add to cart</button> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="modal fade" id="book4" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
<!-- 							<div class="modal-dialog"> -->
<!-- 								<div class="modal-content"> -->
<!-- 									<div class="modal-header"> -->
<!-- 										<h5 class="modal-title" id="exampleModalLabel">Little Fires Everywhere by Celeste NG</h5> -->
<!-- 									</div> -->
<!-- 									<div class="modal-body"> -->
<!-- 										<img src="img/book4.png"> -->
										
<!-- 										<p class="modal-text"> -->
<!-- 											Author: Celeste NG -->
<!-- 											<br> -->
<!-- 											Genre: Family -->
<!-- 											<br> -->
<!-- 											Price: $9.99 -->
<!-- 											<br> -->
<!-- 											ISBN: 876543444 -->
<!-- 											<br> -->
<!-- 											Description: Nature lovers. -->
<!-- 										</p> -->
<!-- 									</div> -->
<!-- 									<div class="modal-footer"> -->
<!-- 										<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!-- 										<button type="button" class="btn btn-primary">Add to cart</button> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
		    	</div>
		    	
		    	<br>
				<br>
				
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
 					</div>
 				</nav>
	     	</div>
		</div>
	</body>
</html>