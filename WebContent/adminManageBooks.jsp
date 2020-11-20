<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="javax.servlet.http.Cookie, dataAccess.*"
    import="models.ErrorMessage"
    import="models.Message"
    import="models.Book"
    import="java.util.List"
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" type="text/css" href="adminManageBooksStyle.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<title>BookBay | (Admin) Manage Books</title>
		
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
 					<a class="navbar-brand" href="#">
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
 								<a class="nav-link" href="ManageUsers">Manage Users</a>
 							</li>
 							
 							<li>
 								<a class="nav-link" href="adminManagePromotions.html">Manage Promotions</a>
 							</li>
 						</ul>
 						
 						<form class="form-inline mr-auto" action="/WebContent/search.html">
 							<input class="form-control mr-sm-2" type="search" placeholder="Enter Book Name, ISBN, Genre, etc..." aria-label="Search" size="36">
 							<button class="btn btn-light my-2 my-sm-0" type="submit">Search</button>
 						</form>
 						<b style="margin-right: 3%; font-size: 15pt;">Admin</b>
 						<ul class="navbar-nav">
 							<li>
 								<a class="nav-link" href="Logout">Logout</a>
 							</li>
 						</ul>
 						
 					</div>
 				</nav>
			</div>
			
			<!-- Homepage content by Jonah Kim  -->
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
				
					<%
						List<Book> books = (List<Book>) request.getAttribute("books");
					
						int booksPerRow = 4;
						
						int numOfRows = books.size() / booksPerRow;
						
						int numOfLeftovers = books.size() % booksPerRow;
						
						if(books.size() < booksPerRow)
							booksPerRow = books.size();
						
						for(int i = 0; i < numOfRows; i++) {
							out.println("<div id=\"added\" class=\"row row-cols-" + booksPerRow + "\">");
							
							for(int j = 0; j < booksPerRow; j++) {
								int index = (booksPerRow * i) + j;
								Book currentBook = books.get(index);
								
								out.println("<div class=\"col\">");
								
								out.println("<div class=\"bookContainer\">");
								
								out.println("<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\""
										+ " data-target=\"#book" + (index+1) + "\" style=\"background-color: transparent; border: none;\">");
								
								out.println("<img src=\"" + currentBook.getImagePath() + "\" class=\"rounded mx-auto d-block shelfImg\">");
								
								out.println("</button>");
								
								out.println("</div>");
								
								out.println("</div>");
							}
							
							out.println("</div>");
						}

						out.println("<div id=\"added\" class=\"row row-cols-" + booksPerRow + "\">");
						for(int k = 0; k < numOfLeftovers; k++) {
							int index = (booksPerRow * numOfRows) + k;
							Book currentBook = books.get(index);
							
							out.println("<div class=\"col\">");
							
							out.println("<div class=\"bookContainer\">");
							
							out.println("<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\""
									+ " data-target=\"#book" + (index+1) + "\" style=\"background-color: transparent; border: none;\">");
							
							out.println("<img src=\"" + currentBook.getImagePath() + "\" class=\"rounded mx-auto d-block shelfImg\">");
							
							out.println("</button>");
							
							out.println("</div>");
							
							out.println("</div>");
						}
						out.println("</div>");
					%>
				
<!-- 					<div id="added" class="row row-cols-5"> -->
<!-- 						<div class="col"> -->
<!-- 							<div class="bookContainer"> -->
<!-- 								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#book1" style="background-color: transparent; border: none;"> -->
<!-- 									<img src="img/book1.png" class="rounded mx-auto d-block shelfImg"> -->
<!-- 								</button> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col"> -->
<!-- 							<div class="bookContainer"> -->
<!-- 								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#book2" style="background-color: transparent; border: none;"> -->
<!-- 									<img src="img/book2.png" class="rounded mx-auto d-block shelfImg"> -->
<!-- 								</button> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col"> -->
<!-- 							<div class="bookContainer"> -->
<!-- 								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#book3" style="background-color: transparent; border: none;"> -->
<!-- 									<img src="img/book3.png" class="rounded mx-auto d-block shelfImg"> -->
<!-- 								</button> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col"> -->
<!-- 							<div class="bookContainer"> -->
<!-- 								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#book4" style="background-color: transparent; border: none;"> -->
<!-- 									<img src="img/book4.png" class="rounded mx-auto d-block shelfImg"> -->
<!-- 								</button> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col"> -->
<!-- 							<div class="bookContainer"> -->
<!-- 								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#book4" style="background-color: transparent; border: none;"> -->
<!-- 									<img src="img/book4.png" class="rounded mx-auto d-block shelfImg"> -->
<!-- 								</button> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!--                     </div> -->
                    <div id="adding" class="row row-cols-<%= booksPerRow %>">
						<div class="col" style="margin-top: 10%;">
							<div class="bookContainer">
	                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addbook" style="background-color: transparent; border: none;">
	                                <img src="img/addbook.png" class="rounded mx-auto d-block shelfImg">
	                            </button>
                            </div>
                        </div>					
					</div>

					
			
			
					<!-- MODALS -->
					<%
						for(int i = 0; i < books.size(); i++) {
							Book currentBook = books.get(i);
							
							out.println("<div class=\"modal fade\" id=\"book" + (i+1) + "\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">");
							
							out.println("<div class=\"modal-dialog\">");
							
							out.println("<div class=\"modal-content\">");
							
							out.println("<div class=\"modal-header\">");
							
							out.println("<h5 class=\"modal-title\" id=\"exampleModalLabel\">" + currentBook.getTitle() + "</h5>");
							
							out.println("</div>");
							
							out.println("<img class=\"modalImg mx-auto\" src=\"" + currentBook.getImagePath() + "\">");
							
							out.println("<form id=\"book" + (i+1) + "ModalForm\" method=\"POST\" accept-charset=\"UTF-8\">");
							
							out.println("<input type=\"hidden\" name=\"bookID\" value=\"" + currentBook.getBookID() + "\"/>");
							
							out.println("<div class=\"modal-body\">");
							
							out.println("<div class=\"form-group\">");
							
							out.println("<label for=\"title\">Title</label>");
									
							out.println("<input type=\"text\" class=\"form-control\" id=\"title\" name=\"title\" placeholder=\"" + currentBook.getTitle() + "\">");
									
							out.println("</div>");
							
							out.println("<div class=\"form-group\">");
							
							out.println("<label for=\"author\">Author</label>");
									
							out.println("<input type=\"text\" class=\"form-control\" id=\"author\" name=\"author\" placeholder=\"" + currentBook.getAuthor() + "\">");
									
							out.println("</div>");
							
							out.println("<div class=\"form-group\">");
							
							out.println("<label for=\"price\">Price</label>");
							
							out.println("<input type=\"text\" class=\"form-control\" id=\"price\" name=\"price\" placeholder=\"" + currentBook.getSellingPrice() + "\">");
							
							out.println("</div>");
							
							out.println("<div class=\"form-group\">");
							
							out.println("<label for=\"genre\">Genre</label>");
								
							out.println("<input type=\"text\" class=\"form-control\" id=\"genre\" name=\"genre\" placeholder=\"" + currentBook.getGenre() + "\">");
								
							out.println("</div>");
							
							out.println("<div class=\"form-group\">");
							
							out.println("<label for=\"description\">Description</label>");
							
							out.println("<input type=\"text\" class=\"form-control\" id=\"description\" name=\"description\" placeholder=\"" + currentBook.getDescription() + "\">");
							
							out.println("</div>");
							
							out.println("</div>");
							
							out.println("<div class=\"modal-footer\">");
						
							out.println("<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>");
							
							out.println("<button type=\"submit\" class=\"btn btn-primary\" formaction=\"EditBook\">Save</button>");
							
							out.println("<button type=\"submit\" class=\"btn btn-primary\" formaction=\"DeleteBook\">Delete</button>");
							
							out.println("</div>");
							
							out.println("</form>");
							
							out.println("</div>");
							
							out.println("</div>");
							
							out.println("</div>");
						}
					%>
							
					<!-- MODALS  -->
<!-- 					<div class="modal fade" id="book1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
<!-- 						<div class="modal-dialog"> -->
<!-- 							<div class="modal-content"> -->
<!-- 								<div class="modal-header"> -->
<!-- 									<h5 class="modal-title" id="exampleModalLabel">Harry Potter and the Sorcerer's Stone</h5> -->
<!-- 								</div> -->
								
<!-- 								<img class="modalImg mx-auto" src="img/book1.png"> -->
								
<!-- 								<form id="book1ModalForm" method="POST" accept-charset="UTF-8"> -->
<!-- 									<div class="modal-body">			 -->
<!-- 											<div class="form-group"> -->
<!-- 											<label for="title">Title</label> -->
<!-- 											<input type="text" class="form-control" id="title" placeholder="Harry Potter and the Sorcerer's Stone"> -->
<!-- 											</div> -->
<!-- 											<div class="form-group"> -->
<!-- 											<label for="author">Author</label> -->
<!-- 											<input type="text" class="form-control" id="author" placeholder="J.K. Rowling"> -->
<!-- 											</div> -->
<!-- 											<div class="form-group"> -->
<!-- 												<label for="price">Price</label> -->
<!-- 												<input type="text" class="form-control" id="price" placeholder="19.99"> -->
<!-- 											</div> -->
<!-- 											<div class="form-group"> -->
<!-- 												<label for="genre">Genre</label> -->
<!-- 												<input type="text" class="form-control" id="genre" placeholder="Action"> -->
<!-- 											</div> -->
<!-- 											<div class="form-group"> -->
<!-- 												<label for="description">Description</label> -->
<!-- 												<input type="text" class="form-control" id="description" placeholder="Enter the world of Harry Potter."> -->
<!-- 											</div> -->
<!-- 									</div> -->
<!-- 									<div class="modal-footer"> -->
<!-- 										<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!-- 										<button type="submit" class="btn btn-primary" formaction="EditBook">Save</button> -->
<!-- 										<button type="submit" class="btn btn-primary" formaction="DeleteBook">Delete</button> -->
<!-- 									</div> -->
<!-- 								</form> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="modal fade" id="book2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
<!-- 						<div class="modal-dialog"> -->
<!-- 							<div class="modal-content"> -->
<!-- 								<div class="modal-header"> -->
<!-- 									<h5 class="modal-title" id="exampleModalLabel">The Outsider</h5> -->
<!-- 								</div> -->
								
<!-- 								<img class="modalImg mx-auto" src="img/book2.png"> -->
								
<!-- 								<form> -->
<!-- 									<div class="modal-body">			 -->
<!-- 											<div class="form-group"> -->
<!-- 											<label for="title">Title</label> -->
<!-- 											<input type="text" class="form-control" id="title" placeholder="The Outsider"> -->
<!-- 											</div> -->
<!-- 											<div class="form-group"> -->
<!-- 											<label for="author">Author</label> -->
<!-- 											<input type="text" class="form-control" id="author" placeholder="Stephen King"> -->
<!-- 											</div> -->
<!-- 											<div class="form-group"> -->
<!-- 												<label for="price">Price</label> -->
<!-- 												<input type="text" class="form-control" id="price" placeholder="15.99"> -->
<!-- 											</div> -->
<!-- 											<div class="form-group"> -->
<!-- 												<label for="genre">Genre</label> -->
<!-- 												<input type="text" class="form-control" id="genre" placeholder="Horror"> -->
<!-- 											</div> -->
<!-- 											<div class="form-group"> -->
<!-- 												<label for="description">Description</label> -->
<!-- 												<input type="text" class="form-control" id="description" placeholder="Scary stuff."> -->
<!-- 											</div> -->
<!-- 										</form> -->
<!-- 									</div> -->
<!-- 									<div class="modal-footer"> -->
<!-- 										<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!-- 										<button type="button" class="btn btn-primary">Save</button> -->
<!-- 										<button type="button" class="btn btn-primary">Delete</button> -->
<!-- 									</div> -->
<!-- 								</form> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="modal fade" id="book3" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
<!-- 						<div class="modal-dialog"> -->
<!-- 							<div class="modal-content"> -->
<!-- 								<div class="modal-header"> -->
<!-- 									<h5 class="modal-title" id="exampleModalLabel">Crazy Rich Asians</h5> -->
<!-- 								</div> -->
								
<!-- 								<img class="modalImg mx-auto" src="img/book3.png"> -->
								
<!-- 								<form> -->
<!-- 									<div class="modal-body"> -->
<!-- 											<div class="form-group"> -->
<!-- 											<label for="title">Title</label> -->
<!-- 											<input type="text" class="form-control" id="title" placeholder="Crazy Rich Asians"> -->
<!-- 											</div> -->
<!-- 											<div class="form-group"> -->
<!-- 											<label for="author">Author</label> -->
<!-- 											<input type="text" class="form-control" id="author" placeholder="Kevin Kwan"> -->
<!-- 											</div> -->
<!-- 											<div class="form-group"> -->
<!-- 												<label for="price">Price</label> -->
<!-- 												<input type="text" class="form-control" id="price" placeholder="20.99"> -->
<!-- 											</div> -->
<!-- 											<div class="form-group"> -->
<!-- 												<label for="genre">Genre</label> -->
<!-- 												<input type="text" class="form-control" id="genre" placeholder="Romance"> -->
<!-- 											</div> -->
<!-- 											<div class="form-group"> -->
<!-- 												<label for="description">Description</label> -->
<!-- 												<input type="text" class="form-control" id="description" placeholder="Rich asians."> -->
<!-- 											</div> -->
<!-- 										</form> -->
<!-- 									</div> -->
<!-- 									<div class="modal-footer"> -->
<!-- 										<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!-- 										<button type="button" class="btn btn-primary">Save</button> -->
<!-- 										<button type="button" class="btn btn-primary">Delete</button> -->
<!-- 									</div> -->
<!-- 								</form> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="modal fade" id="book4" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
<!-- 						<div class="modal-dialog"> -->
<!-- 							<div class="modal-content"> -->
<!-- 								<div class="modal-header"> -->
<!-- 									<h5 class="modal-title" id="exampleModalLabel">Little Fires Everywhere</h5> -->
<!-- 								</div> -->
								
<!-- 								<img class="modalImg mx-auto" src="img/book4.png"> -->
								
<!-- 								<form> -->
<!-- 									<div class="modal-body"> -->
<!-- 										<div class="form-group"> -->
<!-- 											<label for="title">Title</label> -->
<!-- 											<input type="text" class="form-control" id="title" placeholder="Little Fires Everywhere"> -->
<!-- 										</div> -->
<!-- 										<div class="form-group"> -->
<!-- 											<label for="author">Author</label> -->
<!-- 											<input type="text" class="form-control" id="author" placeholder="Celeste NG"> -->
<!-- 										</div> -->
<!-- 										<div class="form-group"> -->
<!-- 											<label for="price">Price</label> -->
<!-- 											<input type="text" class="form-control" id="price" placeholder="9.99"> -->
<!-- 										</div> -->
<!-- 										<div class="form-group"> -->
<!-- 											<label for="genre">Genre</label> -->
<!-- 											<input type="text" class="form-control" id="genre" placeholder="Family"> -->
<!-- 										</div> -->
<!-- 										<div class="form-group"> -->
<!-- 											<label for="description">Description</label> -->
<!-- 											<input type="text" class="form-control" id="description" placeholder="Nature lovers."> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="modal-footer"> -->
<!-- 										<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!-- 										<button type="button" class="btn btn-primary">Save</button> -->
<!-- 										<button type="button" class="btn btn-primary">Delete</button> -->
<!-- 									</div> -->
<!-- 								</form> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
					<div class="modal fade" id="addbook" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Add a new book</h5>
								</div>
								<img src="img/addpicture.png" id="added-picture" class="mx-auto">
								<form id="addNewBookModal" method="POST" accept-charset="UTF-8" enctype="multipart/form-data" novalidate>
									<div class="modal-body">
										<div id="image-input" class="form-group">
                                            <input type="file" value="" id="filePhoto" accept="image/*" required hidden>
                                            <input id='upload-image' type='button' class="btn btn-info" value='Upload Image'/>
                                        </div>
										<div class="form-group">
											<label for="bookTitle">Book Title</label>
											<input type="text" class="form-control" id="bookTitle" name="title">
										</div>
										<div class="form-group">
											<label for="author">Author</label>
											<input type="text" class="form-control" id="author" name="author">
										</div>
										<div class="form-group">
											<label for="price">Price</label>
											<input type="text" class="form-control" id="price" name="price">
										</div>
										<div class="form-group">
											<label for="genre">Genre</label>
											<input type="text" class="form-control" id="genre" name="genre">
										</div>
										<div class="form-group">
											<label for="description">Description</label>
											<input type="text" class="form-control" id="description" name="description">
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
										<button id="add-book" type="submit" class="btn btn-primary" formaction="AddNewBook">Add book</button>
									</div>
								</form>
							</div>
						</div>
					</div>
<!-- 				</div> -->
<!-- 	    	</div> -->
<!-- 	    	<br> -->
<!-- 	    	<br> -->
	    	
	    	<!-- Footer -->
	     	<div class="footerContainer">
				 <footer class="footer">
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
				</footer>
	     	</div>
		</div>
		<script type="text/javascript" src="adminManageBooks.js"></script>
	</body>
</html>
