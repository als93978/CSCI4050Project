<!--  
	search.html
	
	Austin Schultz (als93978)
	
	This page implements SearchPageDesignPrototype.png found
	on the Google Drive.
	
	Allows the user to search for books and filter the results.
-->

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
		<link rel="stylesheet" type="text/css" href="searchStyle.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<title>BookBay | Search</title>
		
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
 						
 						<form class="form-inline mr-auto" action="Search" method="POST" accept-charset="UTF-8">
 							<input class="form-control mr-sm-2" type="search" name="searchInput" placeholder="Enter Book Name, ISBN, Genre, etc..." aria-label="Search" size="36">
 							<button class="btn btn-light my-2 my-sm-0" type="submit">Search</button>
 						</form>
 						
 						<ul class="navbar-nav">
 							<li>
 								<a class="nav-link" href="login.html">Login</a>
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
			
			<!-- Content  -->
			<div class="contentContainer">
				<div class="content">
					<div class="sidebar">
						<div class="accordion" id="filterAccordion">
							<form action="Filter" method="POST" accept-charset="UTF-8">
								<div class="card">
									<div class="card-header" id="genreHeader">
										<h5 class="mb-0">
											Genre
										</h5>
									</div>
									
									<div class="card-body">
										<input type="text" class="form-control" id="filterGenre" aria-describedby="filterGenre" name="genre">
									</div>
								</div>
								
								<div class="card">
									<div class="card-header">
										<h5 class="mb-0">
											ISBN
										</h5>
									</div>
									
																	
									<div class="card-body">
										<input type="number" class="form-control" id="filterISBN" aria-describedby="filterISBN" name="isbn">
										<small id="filterISBN" class="form-text text-muted">Only ISBN-13 accepted.</small>
									</div>
								</div>
								
															
								<div class="card">
									<div class="card-header">
										<h5 class="mb-0">
											Author
										</h5>
									</div>
									
																	
									<div class="card-body">
										<input type="text" class="form-control" id="filterAuthor" aria-describedby="filterAuthor" name="author">
									</div>
								</div>
															
								<div class="card">
									<div class="card-header">
										<h5 class="mb-0">
											Price
										</h5>
									</div>
									
																	
									<div class="card-body">
										<div class="input-group mb-3">
											<div class="input-group-prepend">
												<span class="input-group-text" id="basic-addon1">$</span>
											</div>
											
											<input type="number" class="form-control" step="0.01" id="filterPriceFrom" placeholder="From" aria-label="filterPriceFrom" aria-describedby="basic-addon1" name="fromPrice">
										</div>
										
										<div class="input-group mb-3">
											<div class="input-group-prepend">
												<span class="input-group-text" id="basic-addon2">$</span>
											</div>
											
											<input type="number" class="form-control" step="0.01" id="filterPriceTo" placeholder="To" aria-label="filterPriceTo" aria-describedby="basic-addon2" name="toPrice">
										</div>
									</div>
								</div>
								
															
								<div class="card">
									<div class="card-header">
										<h5 class="mb-0">
											Edition
										</h5>
									</div>
									
																	
									<div class="card-body">
										<input type="number" class="form-control" id="filterEdition" aria-describedby="filterEdition" name="edition">
										<small id="filterEdition" class="form-text text-muted">1 indicates 1st edition, 2 indicates 2nd, etc. Leave blank if N/A.</small>
									</div>
								</div>
								
															
								<div class="card">
									<div class="card-header">
										<h5 class="mb-0">
											Publisher
										</h5>
									</div>
									
																	
									<div class="card-body">
										<input type="text" class="form-control" id="filterPublisher" aria-describedby="filterPublisher" name="publisher">
									</div>
								</div>
								
															
								<div class="card">
									<div class="card-header">
										<h5 class="mb-0">
											Publication Year
										</h5>
									</div>
									
																	
									<div class="card-body">
										<input type="number" class="form-control" id="filterPublicationYear" aria-describedby="filterPublicationYear" name="publicationYear">
									</div>
								</div>
								
<!-- 								<div style="text-align: center;"> -->
									<button type="submit" class="btn btn-primary btn-block mt-2">Filter</button>
<!-- 								</div> -->
							</form>
						</div>
					</div>
					
										
				<div class="searchArea">
					<%
						List<Book> keywordBooks = (List<Book>) request.getAttribute("keywordBooks");
					%>
				
					<h6><b><%= keywordBooks.size() %></b> Result(s) Found</h6>
					
					<div class="searchResultGrid">
					
						<!-- Result books -->
						<%											
							int booksPerRow = 4;
											
							int numOfRows = keywordBooks.size() / booksPerRow;
							
							int numOfLeftovers = keywordBooks.size() % booksPerRow;
							
							if(keywordBooks.size() < booksPerRow)
								booksPerRow = keywordBooks.size();
							
							String imgWidth = "158";
							String imgHeight = "239";
							
							for(int i = 0; i < numOfRows; i++) {
								out.println("<div class=\"row\">");
								
								for(int j = 0; j < booksPerRow; j++) {
									int index = (booksPerRow * i) + j;
									Book currentBook = keywordBooks.get(index);
									
									out.println("<div class=\"col\">");
									
									out.println("<div class=\"card card-searchgrid\" data-toggle=\"modal\" data-target=\"#book" + (index+1) + "\" style=\"cursor: pointer;\">");
									out.println("<img src=\"" + currentBook.getImagePath() + "\" class=\"card-img-top\" width=\"" + imgWidth + "\" height=\"" + imgHeight + "\">");
									out.println("<div class=\"card-body\">");
									out.println("<h5 class=\"card-title\">" + currentBook.getTitle() + "</h5>");
									out.println("<p>" + currentBook.getAuthor() + "</p>");
									out.println("<p>$" + currentBook.getSellingPrice() + "</p>");
									out.println("</div>");
									out.println("</div>");
									
									out.println("</div>");
								}
								
								out.println("</div>");
							}
							
							out.println("<div class=\"row\">");
							for(int k = 0; k < numOfLeftovers; k++) {
								int index = (booksPerRow * numOfRows) + k;
								Book currentBook = keywordBooks.get(index);
								
								out.println("<div class=\"col\">");
								
								out.println("<div class=\"card card-searchgrid\" data-toggle=\"modal\" data-target=\"#book" + (index+1) + "\" style=\"cursor: pointer;\">");
								out.println("<img src=\"" + currentBook.getImagePath() + "\" class=\"card-img-top\" width=\"" + imgWidth + "\" height=\"" + imgHeight + "\">");
								out.println("<div class=\"card-body\">");
								out.println("<h5 class=\"card-title\">" + currentBook.getTitle() + "</h5>");
								out.println("<p>" + currentBook.getAuthor() + "</p>");
								out.println("<p>$" + currentBook.getSellingPrice() + "</p>");
								out.println("</div>");
								out.println("</div>");
								
								out.println("</div>");
							}
							out.println("</div>");
						%>
					
<!-- 						<div class="row"> -->
<!-- 							<div class="col"> -->
<!-- 								<div class="card card-searchgrid"> -->
<!-- 									<img src="img/bookCover1.jpg" class="card-img-top" width="158" height="239"> -->
									
<!-- 									<div class="card-body"> -->
<!-- 										<h5 class="card-title">Head First Web Design</h5> -->
<!-- 										<p>Ethan Watrall, Jeff Siarto</p> -->

<!-- 										<br/> -->
<!-- 										<br/> -->
										
<!-- 										<p>$9.99</p> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
							
<!-- 							<div class="col"> -->
<!-- 								<div class="card card-searchgrid"> -->
<!-- 									<img src="img/bookCover2.jpg" class="card-img-top" width="158" height="239"> -->
									
<!-- 									<div class="card-body"> -->
<!-- 										<h5 class="card-title">The Great Gatsby</h5> -->
<!-- 										<p>F. Scott Fitzgerald</p> -->
										
<!-- 										<br/> -->
<!-- 										<br/> -->
										
<!-- 										<p>$9.99</p> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
							
<!-- 							<div class="col"> -->
<!-- 								<div class="card card-searchgrid"> -->
<!-- 									<img src="img/bookCover3.jpg" class="card-img-top" width="158" height="239"> -->
									
<!-- 									<div class="card-body"> -->
<!-- 										<h5 class="card-title">The Catcher in the Rye</h5> -->
<!-- 										<p>J.D. Salinger</p>								 -->
										
<!-- 										<br/> -->
<!-- 										<br/> -->
										
<!-- 										<p>$9.99</p> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
							
<!-- 							<div class="col"> -->
<!-- 								<div class="card card-searchgrid"> -->
<!-- 									<img src="img/bookCover4.jpg" class="card-img-top" width="158" height="239"> -->
									
<!-- 									<div class="card-body"> -->
<!-- 										<h5 class="card-title">Catch-22</h5> -->
<!-- 										<p>Joseph Heller</p>							 -->
										
<!-- 										<br/> -->
<!-- 										<br/> -->
										
<!-- 										<p>$9.99</p> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
						
<!-- 						<div class="row">			 -->
<!-- 							<div class="col"> -->
<!-- 								<div class="card card-searchgrid"> -->
<!-- 									<img src="img/bookCover5.jpg" class="card-img-top" width="158" height="239"> -->
									
<!-- 									<div class="card-body"> -->
<!-- 										<h5 class="card-title">To Kill a Mockingbird</h5> -->
<!-- 										<p>Harper Lee</p>				 -->
										
<!-- 										<br/> -->
<!-- 										<br/> -->
										
<!-- 										<p>$9.99</p> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
							
<!-- 							<div class="col"> -->
<!-- 								<div class="card card-searchgrid"> -->
<!-- 									<img src="img/bookCover6.png" class="card-img-top" width="158" height="239"> -->
									
<!-- 									<div class="card-body"> -->
<!-- 										<h5 class="card-title">Harry Potter and the Sorcerer's Stone</h5> -->
<!-- 										<p>J.K. Rowling</p> -->
										
<!-- 										<br/> -->
<!-- 										<br/> -->
										
<!-- 										<p>$9.99</p> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
							
<!-- 							<div class="col"> -->
<!-- 								<div class="card card-searchgrid"> -->
<!-- 									<img src="img/bookCover7.png" class="card-img-top" width="158" height="239"> -->
									
<!-- 									<div class="card-body"> -->
<!-- 										<h5 class="card-title">The Outsider</h5> -->
<!-- 										<p>Stephen King</p>						 -->
										
<!-- 										<br/> -->
<!-- 										<br/> -->
										
<!-- 										<p>$9.99</p> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
							
<!-- 							<div class="col"> -->
<!-- 								<div class="card card-searchgrid"> -->
<!-- 									<img src="img/bookCover8.png" class="card-img-top" width="158" height="239"> -->
									
<!-- 									<div class="card-body"> -->
<!-- 										<h5 class="card-title">Crazy Rich Asians</h5> -->
<!-- 										<p>Kevin Kwan</p>		 -->
										
<!-- 										<br/> -->
<!-- 										<br/> -->
										
<!-- 										<p>$9.99</p> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
	
				</div>
    		</div>
    		
    		<!-- Modals -->
    		<div class="modalContainer">
    			
    			<%
					for(int i = 0; i < keywordBooks.size(); i++) {
						Book currentBook = keywordBooks.get(i);
						
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
    			%>
    			
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
    	</div>
	</body>
</html>