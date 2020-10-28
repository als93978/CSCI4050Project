<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" type="text/css" href="registrationStyle.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<title>BookBay | Registration</title>
		
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
 								<a class="nav-link" href="#">Login</a>
 							</li>
 						</ul>
 						
 						<a class="nav-link" href="#">
 							<i class="fas fa-shopping-cart fa-2x"></i>
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
										<a class="nav-link active text-white" href="registration.html">Registration</a>
									</li>
									<li class="nav-item">
										<a class="nav-link" href="login.html">Login</a>
									</li>
								</ul>
								<form id="registration">
									<br>
									<div class="form-group row">
										<label for="inputUser" class="col-sm-2 col-form-label">Username:</label>
										<div class="col-sm-10">
										  <input type="text" class="form-control" id="inputUser" required>
										</div>
									</div>
									<div class="form-group row">
										<label for="inputPassword" class="col-sm-2 col-form-label">Password:</label>
										<div class="col-sm-10">
										  <input type="password" class="form-control" id="inputPassword" required>
										</div>
									</div>
									<div class="form-group row">
										<label for="inputConfirmPassword" class="col-sm-2 col-form-label">Confirm Password:</label>
										<div class="col-sm-10">
										  <input type="password" class="form-control" id="inputConfirmPassword" required>
										</div>
									</div>
									<div class="form-group row">
										<label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
										<div class="col-sm-10">
										  <input type="email" class="form-control" id="inputEmail" required>
										</div>
									  </div>
									  <div class="form-group form-check">
										<input type="checkbox" class="form-check-input" id="promotionCheck">
										<label class="form-check-label" for="promotionCheck">Opt-in for email promotions?</label>
									  </div>
									  <button type="submit" class="btn btn-white form-btn text-white">Create Account</button>
								</form>
							</div>
						</div>
					</div>
					<script type="text/javascript" src="main.js"></script>	  
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