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
		<title>BookBay | Login</title>
		
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
		<script src="https://kit.fontawesome.com/0318fd2bd1.js" crossorigin="anonymous"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	</head>
	
	<body>
		<div class="mainContainer">
		
			<div class="headerContainer">
 				<nav class="navbar navbar-expand-lg navbar-custom">
 					<a class="navbar-brand" href="index.html">
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
 						
 						<form class="form-inline mr-auto">
 							<input class="form-control mr-sm-2" type="search" placeholder="Enter Book Name, ISBN, Genre, etc..." aria-label="Search" size="36">
 							<button class="btn btn-light my-2 my-sm-0" type="submit">Search</button>
 						</form>
 						
 						<ul class="navbar-nav">			
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

			<div class="contentContainer">
	      		<div class="content">
					<br>
					<div class="container">
						<div class="row justify-content-center">
							<div class="col-sm-12 col-md-12 col-xs-12 col-lg-6 mb--30 mb-lg--0">
								<ul class="nav nav-tabs justify-content-center">
									<li class="nav-item">
										<a class="nav-link" href="registration.jsp">Registration</a>
									</li>
									<li class="nav-item">
										<a class="nav-link active text-white" href="logintemplate.html">Login</a>
									</li>
								</ul>
								<form id="login" class="needs-validation" action="Login" method="POST" accept-charset="UTF-8" novalidate>
									<%
										ErrorMessage errorMessage = (ErrorMessage) request.getAttribute("errorMessage");
										
										if(errorMessage != null) {
											out.println("<div class=\"alert alert-danger\" role=\"alert\" style=\"display: block\">");
											out.println("\t" + errorMessage.getMessage());
											out.println("</div>");
										}
									%>
								
									<br>
									<div class="form-group">
										<label for="inputEmailUser">Email/Account ID</label>
										<input type="text" class="form-control" id="inputEmailUser" pattern=".{1,}" name="emailAccountID" required>
										<div class="invalid-feedback">
											Enter a valid email address or Account ID.
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword">Password</label>
										<input type="password" class="form-control" id="inputPassword" pattern=".{6,}" name="password" required>
										<div class="invalid-feedback">
											The password is either wrong or typed incorrectly.
										</div>
									</div>
									<div class="form-group">
										<a id="forgot" href="" data-toggle="modal" data-target="#exampleModal">Forget Password?</a>
									</div>
									<button type="submit" class="btn btn-white form-btn text-white">Login</button>
								</form>
							</div>
						</div>
					</div>
					<script type="text/javascript" src="main.js"></script>	  
				</div>
			</div>
			
			<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
				  <div class="modal-content">
					<div class="modal-header">
					  <h5 class="modal-title" id="exampleModalLabel">Password reset</h5>
					  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					  </button>
					</div>
					<div class="modal-body">
					  The password reset link or code has been sent to your email address.
					</div>
					<div class="modal-footer">
					  <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
					</div>
				  </div>
				</div>
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
	</body>
</html>
