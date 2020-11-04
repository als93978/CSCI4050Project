<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="models.ErrorMessage"
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" type="text/css" href="registrationStyle.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<title>BookBay | Registration Confirmation</title>
		
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/0318fd2bd1.js" crossorigin="anonymous"></script>
	</head>
	
	<body>
		<div class="mainContainer">
		
			<div class="headerContainer">
 				<nav class="navbar navbar-expand-lg navbar-custom">
 					<a class="navbar-brand" href="index.html">
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
 						
 						<!-- <a class="navbar-brand ml-1" href="#">
							<i class="fas fa-shopping-cart"></i>
 						</a> -->
 					</div>
 				</nav>
			</div>

            <div id="confirmation" class="jumbotron">
                <h1 class="display-4">Welcome to BookBay!</h1>
                <p class="lead">The next step is to check your email address for an email confirmation code and verify your registered account. After entering the correct confirmation code, you will become an active BookBay customer.</p>
                <hr class="my-4">
                <p>Don't see the email confirmation code? Click on the resend confirmation button below.</p>
                <form id="submitConfirmationCode" action="ConfirmUser" method="POST" accept-charset="UTF-8">
	                <section class="form-group">
						<button type="button" id="resend" class="btn btn-primary btn-lg form-btn" role="button" data-toggle="popover" data-placement="bottom" title="Email confirmation code sent!" data-content="Another email confirmation code has been delivered to your email address, and it should be in your inbox soon.">Resend confirmation code</button>
						<div id="submit-code" class="form-group mx-sm-3 mb-2">
							<%
								ErrorMessage errorMessage = (ErrorMessage) request.getAttribute("errorMessage");
										
								if(errorMessage != null) {
									out.println("<div class=\"alert alert-danger\" role=\"alert\" style=\"display: block\">");
									out.println("\t" + errorMessage.getMessage());
									out.println("</div>");
								}
							%>
						
							<label for="inputEmailCode" class="sr-only">Email Address:</label>
							<input type="text" class="form-control" id="inputEmail" name="email" placeholder="Email Address">
						
							<label for="inputEmailCode" class="sr-only">Email Confirmation Code</label>
							<input type="password" class="form-control" id="inputEmailCode" name="code" placeholder="Code">
						</div>
						<button id="submitBtn" type="submit" class="btn btn-primary mb-2 form-btn">Submit Code</button>
					</section>
				</form>
            </div>
            
            <div class="footerContainer">
                <footer class="footer">
                   <nav class="navbar navbar-expand-lg navbar-custom">
                       <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                           <span class="navbar-toggler-icon"></span>
                       </button>
                       
                       <div class="collapse navbar-collapse" id="navbarSupportedContent">
                           <span class="navbar-text mr-auto text-white">
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
        <script type="text/javascript" src="registrationConfirmation.js"></script>
   </body>
</html>