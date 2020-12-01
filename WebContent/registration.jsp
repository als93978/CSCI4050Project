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
 								<a class="nav-link" href="login.jsp">Login</a>
 							</li>
 						</ul>
 						
 						<a class="nav-link" href="shoppingCart.jsp">
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
							<div class="col-sm-12 col-md-12 col-xs-12 col-lg-6 mb--30 mb-lg--0 registrationForm">
								<ul class="nav nav-tabs justify-content-center">
									<li class="nav-item">
										<a class="nav-link active text-white" href="registration.html">Registration</a>
									</li>
									<li class="nav-item">
										<a class="nav-link" href="login.jsp">Login</a>
									</li>
								</ul>
								<form id="registration" class="needs-validation" action="Register" method="POST" accept-charset="UTF-8" novalidate>
									
									<%
										ErrorMessage errorMessage = (ErrorMessage) request.getAttribute("errorMessage");
										
										if(errorMessage != null) {
											out.println("<div class=\"alert alert-danger\" role=\"alert\" style=\"display: block\">");
											out.println("\t" + errorMessage.getMessage());
											out.println("</div>");
										}
									%>
									
									<br>
									<div class="form-group row">
										<label for="inputUser" class="col-sm-2 col-form-label">First Name:</label>
										<div class="col-sm-10">
										  <input type="text" class="form-control" id="inputUser" pattern=".{1,}" name="firstName" required>
										  <div class="invalid-feedback">
											Please provide a valid first name that is at least 1 characters or more.
										  </div>
										</div>
									</div>
									<div class="form-group row">
										<label for="inputUser" class="col-sm-2 col-form-label">Last Name:</label>
										<div class="col-sm-10">
										  <input type="text" class="form-control" id="inputUser" pattern=".{1,}" name="lastName" required>
										  <div class="invalid-feedback">
											Please provide a valid last name that is at least 1 characters or more.
										  </div>
										</div>
									</div>
									<div class="form-group row">
										<label for="inputPassword" class="col-sm-2 col-form-label">Password:</label>
										<div class="col-sm-10">
										  <input type="password" class="form-control" id="inputPassword" pattern=".{6,}" name="password" required>
										  <div class="invalid-feedback">
											Enter a password that is at least 6 characters.
										  </div>
										  <div id="passwordAlert1" class="alert alert-danger" role="alert">
											<strong>Make sure Password and Confirm Password have the same input</strong>
										  </div>
										</div>
									</div>
									<div class="form-group row">
										<label for="inputConfirmPassword" class="col-sm-2 col-form-label">Confirm Password:</label>
										<div class="col-sm-10">
										  <input type="password" class="form-control" id="inputConfirmPassword" name="confirmPassword" required>
										  <div id="passwordAlert2" class="alert alert-danger" role="alert">
											<strong>Password and Confirm Password are not the same</strong>
										  </div>
										</div>
									</div>
									<div class="form-group row">
										<label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
										<div class="col-sm-10">
										  <input type="email" class="form-control" id="inputEmail" pattern="^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$" name="email" required>
										  <div class="invalid-feedback">
											Enter a valid email address.
										  </div>
										</div>
									</div>
									<div class="form-group form-check">
										<input type="checkbox" class="form-check-input" id="promotionCheck" name="enrollmentForPromotions">
										<label class="form-check-label" for="promotionCheck">Opt-in for email promotions?</label>
									</div>
									<hr class="mb-4">
									<label class="form-check-label">Would you like to enter your payment information?</label>
									<div class="form-check form-check-inline">
										<input id="yes" class="form-check-input payment-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1" required="">
										<label class="form-check-label" for="inlineRadio1">Yes</label>
									</div>
									<div class="form-check form-check-inline">
										<input id="no" class="form-check-input payment-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2" required="">
										<label class="form-check-label" for="inlineRadio2">No</label>
									</div>
									<br>
									<br>
									<fieldset id="payment" disabled>
										<h4 class="mb-3">Payment</h4>
										<div class="d-block my-3">
											<div class="custom-control custom-radio">
												<input id="discover" name="paymentMethod" type="radio" class="custom-control-input" value="1" required="">
												<label class="custom-control-label" for="discover">
													<img src="https://i.imgur.com/qkW8Dcz.png" title="source: imgur.com" width="35" height="22" />
													Discover
												</label>
											</div>
											<div class="custom-control custom-radio">
												<input id="visa" name="paymentMethod" type="radio" class="custom-control-input" value="2" required="">
												<label class="custom-control-label" for="visa">
													<a href="http://www.credit-card-logos.com/"><img alt="Credit Card Logos" title="Credit Card Logos" src="http://www.credit-card-logos.com/images/visa_credit-card-logos/visa_logo_3.gif" width="35" height="22" /></a>
													Visa
												</label>
											</div>
											<div class="custom-control custom-radio">
												<input id="mastercard" name="paymentMethod" type="radio" class="custom-control-input" value="3" required="">
												<label class="custom-control-label" for="mastercard">
													<a href="http://www.credit-card-logos.com/"><img alt="Credit Card Logos" title="Credit Card Logos" src="http://www.credit-card-logos.com/images/mastercard_credit-card-logos/mastercard_logo_4.gif" width="35" height="22" /></a>
													Mastercard
												</label>
											</div>
											<div class="custom-control custom-radio">
												<input id="american-express" name="paymentMethod" type="radio" class="custom-control-input" value="4" required="">
												<label class="custom-control-label" for="american-express">
													<a href="https://imgur.com/zpRZtD6"><img src="https://i.imgur.com/zpRZtD6.png" title="source: imgur.com" width="35" height="22" /></a>
													American Express
												</label>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6 mb-3">
												<label for="cc-number">Credit card number</label>
												<input type="text" class="form-control" id="cc-number" placeholder="" name="cardNum" required="">
												<div class="invalid-feedback">
													Credit card number is required
												</div>
											</div>
											<div class="col-md-3 mb-3">
												<label for="cc-expiration">Expiration</label>
												<input type="text" class="form-control" id="cc-expiration" placeholder="" name="expDate" required="">
												<div class="invalid-feedback">
													Expiration date required
												</div>
											</div>
										</div>
									</fieldset>
									<hr class="mb-4">
									<div class="custom-control custom-checkbox">
										<input type="checkbox" class="custom-control-input" id="enter-shipping" name="shippingOption">
										<label class="custom-control-label" for="enter-shipping">Enter shipping address</label>
									</div>
									<br>
									<fieldset id="shipping" disabled>
										<h4 class="mb-3">Shipping address</h4>
										<div class="mb-3">
											<label for="address">Address</label>
											<input type="text" class="form-control" id="address" placeholder="1234 Main St" name="street" required="">
											<div class="invalid-feedback">
												Please enter your shipping address.
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 mb-3">
												<label for="city">City</label>
												<input type="text" class="form-control" id="city" placeholder="" name="city" required="">
												<div class="invalid-feedback">
													Please enter a valid city.
												</div>
											</div>
											<div class="col-md-4 mb-3">
											<label for="state">State</label>
											<select class="custom-select d-block w-100" id="state" name="state" required="">
												<option value="">Choose...</option>
												<option value="AL">Alabama</option>
												<option value="AK">Alaska</option>
												<option value="AZ">Arizona</option>
												<option value="AR">Arkansas</option>
												<option value="CA">California</option>
												<option value="CO">Colorado</option>
												<option value="CT">Connecticut</option>
												<option value="DE">Delaware</option>
												<option value="DC">District Of Columbia</option>
												<option value="FL">Florida</option>
												<option value="GA">Georgia</option>
												<option value="HI">Hawaii</option>
												<option value="ID">Idaho</option>
												<option value="IL">Illinois</option>
												<option value="IN">Indiana</option>
												<option value="IA">Iowa</option>
												<option value="KS">Kansas</option>
												<option value="KY">Kentucky</option>
												<option value="LA">Louisiana</option>
												<option value="ME">Maine</option>
												<option value="MD">Maryland</option>
												<option value="MA">Massachusetts</option>
												<option value="MI">Michigan</option>
												<option value="MN">Minnesota</option>
												<option value="MS">Mississippi</option>
												<option value="MO">Missouri</option>
												<option value="MT">Montana</option>
												<option value="NE">Nebraska</option>
												<option value="NV">Nevada</option>
												<option value="NH">New Hampshire</option>
												<option value="NJ">New Jersey</option>
												<option value="NM">New Mexico</option>
												<option value="NY">New York</option>
												<option value="NC">North Carolina</option>
												<option value="ND">North Dakota</option>
												<option value="OH">Ohio</option>
												<option value="OK">Oklahoma</option>
												<option value="OR">Oregon</option>
												<option value="PA">Pennsylvania</option>
												<option value="RI">Rhode Island</option>
												<option value="SC">South Carolina</option>
												<option value="SD">South Dakota</option>
												<option value="TN">Tennessee</option>
												<option value="TX">Texas</option>
												<option value="UT">Utah</option>
												<option value="VT">Vermont</option>
												<option value="VA">Virginia</option>
												<option value="WA">Washington</option>
												<option value="WV">West Virginia</option>
												<option value="WI">Wisconsin</option>
												<option value="WY">Wyoming</option>
											</select>
											<div class="invalid-feedback">
												Please provide a valid state.
											</div>
											</div>
											<div class="col-md-3 mb-3">
											<label for="zip">Zip</label>
											<input type="text" class="form-control" id="zip" placeholder="" name="zipCode" required="">
											<div class="invalid-feedback">
												Zip code required.
											</div>
											</div>
										</div>
									</fieldset>
									<hr class="mb-4">
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