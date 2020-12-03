<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="javax.servlet.http.Cookie, dataAccess.*"
    import="models.ErrorMessage"
    import="models.Message"
    import="models.User"
    import="models.Address"
    import="models.PaymentCard"
%>

<%
	User user = (User) request.getAttribute("user");

	Address address = (Address) request.getAttribute("address");
	
	PaymentCard paymentCard = (PaymentCard) request.getAttribute("paymentCard");
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" type="text/css" href="accountSettingsStyle.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<title>BookBay | Account Settings</title>

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

 						<div class="dropdown">
							<a class="nav-link dropdown-toggle" href="#" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<img src="img/user-icon.png" class="img-fluid user-icon" alt="User Icon">
							</a>
							<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
								<a class="dropdown-item" href="Logout">Log off</a>
								<a class="dropdown-item" href="orderhistory.html">Order history</a>
								<a class="dropdown-item" href="EditProfile">Account settings</a>
							</div>
						</div>

 						<a class="nav-link" href="ShoppingCart">
							 <i class="fas fa-shopping-cart fa-2x"></i>
							 <span class="badge badge-dark badge-pill">4</span>
						 </a>
 					</div>
 				</nav>
			</div>

			<div class="contentContainer">
	      		<div class="content">
					<br>
					<h1 id="edit-profile-title" class="font-weight-bold">Edit Profile</h1>
				  	<br>
					<div class="container">
					
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
					
						<div class="accordion" id="accordionEditAccount">
							<div class="card">
								<div class="card-header" id="headingOne">
									<h2 class="mb-0">
										<button class="btn btn-link btn-block text-left text-white collapsed" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
											Change Password
										</button>
									</h2>
								</div>
								<div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionEditAccount">
									<div class="card-body">
										<div class="accordion" id="accordionInfo1">
											<div class="card bg-white">
												<div class="card-header" id="headingInfo1">
													<h2 class="mb-0">
														<a class="btn btn-link btn-block text-left text-dark collapsed" type="button" data-toggle="collapse" data-target="#collapseInfo1" aria-expanded="false" aria-controls="collapseInfo1">
															<i class="fa fa-plus"></i>Change Password
														</a>
													</h2>
                                                </div>

												<div id="collapseInfo1" class="collapse show" aria-labelledby="headingInfo1" data-parent="#accordionInfo1">
													<div class="card-body">
                                                        <form action="<%= request.getContextPath() %>/UpdatePassword" method="post" name="passwordForm" class="needs-validation" novalidate>
                                                        <div class="form-group row">
															<label for="inputPassword" class="col-sm-2 col-form-label">Password:</label>
															<div class="col-sm-10">
																<input type="password" class="form-control" id="inputPassword" name="password" pattern=".{6,}" required>
																 <div class="invalid-feedback">
																	Enter a password that is at least 6 characters.
										  						</div>
																<div class="alert alert-danger" role="alert">
																	<strong>Make sure Password and Confirm Password have the same input</strong>
										  						</div>
															</div>
														</div>
														<div class="form-group row">
															<label for="inputConfirmPassword" class="col-sm-2 col-form-label">Confirm Password:</label>
															<div class="col-sm-10">
																<input type="password" class="form-control" id="inputConfirmPassword" pattern=".{6,}" required>
																<div class="alert alert-danger" role="alert">
																	<strong>Password and Confirm Password are not the same</strong>
										  						</div>
															</div>
														</div>
                                                        <button type="submit" class="btn text-white" style="background-color:#4f5cbf">Save Changes</button>
                                                         </form>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="card">
								<div class="card-header" id="headingTwo">
									<h2 class="mb-0">
										<button class="btn btn-link btn-block text-left text-white collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
											Personal Information
										</button>
									</h2>
								</div>
								<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionEditAccount">
									<div class="card-body">
										<div class="accordion" id="accordionInfo2">
											<div class="card bg-white">
												<div class="card-header" id="headingInfo2">
													<h2 class="mb-0">
														<button class="btn btn-link btn-block text-left text-dark collapsed" type="button" data-toggle="collapse" data-target="#collapseInfo2" aria-expanded="false" aria-controls="collapseInfo2">
															Edit Name
														</button>
													</h2>
												</div>
												<div id="collapseInfo2" class="collapse" aria-labelledby="headingInfo2" data-parent="#accordionInfo2">
													<div class="card-body">
													
														<%
															out.println("<div class=\"alert alert-primary\" role=\"alert\" style=\"display: block\">");
															if(user != null) {	
																out.println("<p>" + "Current first name: " + user.getFirstName() + "</p>");
																out.println("<p>" + "Current last name: " + user.getLastName() + "</p>");
															}
															else {
																out.println("\t" + "No current name information found.");
															}
															out.println("</div>");
														%>
													
                                                    <form action="<%= request.getContextPath() %>/UpdateName" method="post" name = "nameForm" class="needs-validation" novalidate>
                                                        <div class="form-group row">
										<label for="inputFirstName" class="col-sm-2 col-form-label">First Name:</label>
										<div class="col-sm-10">
										  <input type="firstName" class="form-control" id="inputFirstName" pattern=".{1,}" name="firstName" required placeholder="">
										  <div class="invalid-feedback">
											Please provide a valid first name that is at least 1 characters or more.
										  </div>
										</div>
									</div>
									<div class="form-group row">
										<label for="inputLastName" class="col-sm-2 col-form-label">Last Name:</label>
										<div class="col-sm-10">
										  <input type="lastName" class="form-control" id="inputLastName" pattern=".{1,}" name="lastName" required placeholder="">
										  <div class="invalid-feedback">
											Please provide a valid last name that is at least 1 characters or more.
										  </div>
										</div>
									</div>
                                    <button type="submit" class="btn btn-white form-btn text-white" style="background-color:#4f5cbf">Save Changes</button>
                                    </form>
													</div>
												</div>
											</div>
										</div>
										<div class="accordion" id="accordionBook2">
											<div class="card bg-white">
												<div class="card-header" id="headingBook2">
													<h2 class="mb-0">
														<button class="btn btn-link btn-block text-left text-dark collapsed" type="button" data-toggle="collapse" data-target="#collapseBook2" aria-expanded="false" aria-controls="collapseBook2">
															Update Current Mailing Address
														</button>
													</h2>
												</div>
												<div id="collapseBook2" class="collapse" aria-labelledby="headingBook2" data-parent="#accordionBook2">
													<div class="card-body">
													
														<%
															out.println("<div class=\"alert alert-primary\" role=\"alert\" style=\"display: block\">");
															if(address != null) {	
																out.println("<p>" + "Current Street: " + address.getStreet() + "</p>");
																out.println("<p>" + "Current City: " + address.getCity() + "</p>");
																out.println("<p>" + "Current State: " + address.getState() + "</p>");
																out.println("<p>" + "Current ZipCode: " + address.getZipCode() + "</p>");
															}
															else {
																out.println("\t" + "No current address information found.");
															}
															out.println("</div>");
														%>
													
                                                    <form action="<%= request.getContextPath() %>/UpdateAddress" method="post" name = "addressForm" class="needs-validation" novalidate>
									<div class="form-group row">
										<label for="inputStreet" class="col-sm-2 col-form-label">Street:</label>
										<div class="col-sm-10">
										  <input type="street" class="form-control" id="inputStreet" required="" name="street" placeholder="">
										  <div class="invalid-feedback">
											Please enter your shipping address.
											</div>
										</div>
									</div>
                                    <div class="form-group row">
										<label for="inputCity" class="col-sm-2 col-form-label">City:</label>
										<div class="col-sm-10">
										  <input type="city" class="form-control" id="inputCity" required="" name="city" placeholder="">
										  <div class="invalid-feedback">
												Please enter a valid city.
											</div>
										</div>
									</div>
                                    <div class="form-group row">
										<label for="inputState" class="col-sm-2 col-form-label">State:</label>
										<div class="col-sm-10">
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
									</div>
                                    <div class="form-group row">
										<label for="inputZipCode" class="col-sm-2 col-form-label">Zip Code:</label>
										<div class="col-sm-10">
										  <input type="zipCode" class="form-control" id="inputZipCode" required="" name="zipCode" placeholder="">
										  <div class="invalid-feedback">
												Zip code required.
											</div>
										</div>
									</div>
                                    <button type="submit" class="btn btn-white form-btn text-white" style="background-color:#4f5cbf">Save Changes</button>
                                    </form>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card">
								<div class="card-header" id="headingThree">
									<h2 class="mb-0">
										<button class="btn btn-link btn-block text-left text-white collapsed" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
											Update Payment Information
										</button>
									</h2>
								</div>
								<div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionEditAccount">
									<div class="card-body">
										<div class="accordion" id="accordionInfo3">
											<div class="card bg-white">
												<div class="card-header" id="headingInfo3">
													<h2 class="mb-0">

														<a class="btn btn-link btn-block text-left text-dark collapsed" type="button" data-toggle="collapse" data-target="#collapseInfo3" aria-expanded="false" aria-controls="collapseInfo3">
															<i class="fa fa-plus"></i>Update Card Information
														</a>
													</h2>
												</div>
												<div id="collapseInfo3" class="collapse show" aria-labelledby="headingInfo3" data-parent="#accordionInfo3">

													<div class="card-body">
													
													   <%
															out.println("<div class=\"alert alert-primary\" role=\"alert\" style=\"display: block\">");
															if(paymentCard != null) {
																String cardNum = paymentCard.getCardNum();
													  			out.println("<p>" + "Current Card Number: **** **** **** " + cardNum.substring(cardNum.length() - 4) + "</p>");
																out.println("<p>" + "Current Card Expiration Date: " + paymentCard.getExpDate() + "</p>");
																out.println("<p>" + "Current Card Type: " + paymentCard.getCardType().name() + "</p>");
															}
															else {
																out.println("\t" + "No current payment card information found.");
															}
															out.println("</div>");
														%>
													
                                                    <form action="<%= request.getContextPath() %>/UpdateCard" method="post" name = "cardForm" class="needs-validation" novalidate>
                                                        <div class="form-group row">
                    										<label for="inputCardNumber" class="col-sm-2 col-form-label">Card Number:</label>
                    										<div class="col-sm-10">
                    										  <input type="cardNumber" class="form-control" id="inputCardNumber" required="" name="cardNum" placeholder="">
															  <div class="invalid-feedback">
																	Credit card number is required
																</div>
                    										</div>
                    									</div>
                                                        <div class="form-group row">
                    										<label for="inputExpiration" class="col-sm-2 col-form-label">Card Expiration Date:</label>
                    										<div class="col-sm-10">
                    										  <input type="cardExpiration" class="form-control" id="inputExpiration" name="expDate" required="" placeholder="">
															  <div class="invalid-feedback">
																	Expiration date required
																</div>
                    										</div>
                    									</div>
                                                        <div class="form-group row">
                    										<label for="inputCardType" class="col-sm-2 col-form-label">Card Type (Visa, MasterCard, Discover):</label>
                    										<div class="col-sm-10">
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
                    									</div>
                                                        <button type="submit" class="btn btn-white form-btn text-white" style="background-color:#4f5cbf">Save Changes</button>
                                                        </form>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<script type="text/javascript" src="main.js"></script>
	      		</div>
	    	</div>



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
		<script type="text/javascript" src="accountsettings.js"></script>	 
	</body>
</html>
