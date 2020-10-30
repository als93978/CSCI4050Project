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
	</head>

	<body>
		<div class="mainContainer">

			<div class="headerContainer">
 				<nav class="navbar navbar-expand-lg navbar-custom">
 					<a class="navbar-brand" href="homepageWithUserIcon.html">
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

 						<div class="dropdown">
							<a class="nav-link dropdown-toggle" href="#" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<img src="img/user-icon.png" class="img-fluid user-icon" alt="User Icon">
							</a>
							<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
								<a class="dropdown-item" href="index.html">Log off</a>
								<a class="dropdown-item" href="orderhistory.html">Order history</a>
								<a class="dropdown-item" href="accountSettings.html">Account settings</a>
							</div>
						</div>

 						<a class="nav-link" href="shoppingCart.html">
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
                                                        <form action="./InsertData" method="post" name="passwordForm">
                                                        <div class="form-group row">
															<label for="inputPassword" class="col-sm-2 col-form-label">Password:</label>
															<div class="col-sm-10">
																<input type="password" class="form-control" id="inputPassword">
															</div>
														</div>
														<div class="form-group row">
															<label for="inputConfirmPassword" class="col-sm-2 col-form-label">Confirm Password:</label>
															<div class="col-sm-10">
																<input type="password" class="form-control" id="inputConfirmPassword">
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
                                                    <form action="./InsertData" method="post" name = "nameForm">
                                                        <div class="form-group row">
										<label for="inputFirstName" class="col-sm-2 col-form-label">First Name:</label>
										<div class="col-sm-10">
										  <input type="firstName" class="form-control" id="inputFirstName">
										</div>
									</div>
									<div class="form-group row">
										<label for="inputLastName" class="col-sm-2 col-form-label">Last Name:</label>
										<div class="col-sm-10">
										  <input type="lastName" class="form-control" id="inputLastName">
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
                                                    <form action="./InsertData" method="post" name = "addressForm">
									<div class="form-group row">
										<label for="inputStreet" class="col-sm-2 col-form-label">Street:</label>
										<div class="col-sm-10">
										  <input type="street" class="form-control" id="inputStreet">
										</div>
									</div>
                                    <div class="form-group row">
										<label for="inputCity" class="col-sm-2 col-form-label">City:</label>
										<div class="col-sm-10">
										  <input type="city" class="form-control" id="inputCity">
										</div>
									</div>
                                    <div class="form-group row">
										<label for="inputState" class="col-sm-2 col-form-label">State:</label>
										<div class="col-sm-10">
										  <input type="state" class="form-control" id="inputState">
										</div>
									</div>
                                    <div class="form-group row">
										<label for="inputZipCode" class="col-sm-2 col-form-label">Zip Code:</label>
										<div class="col-sm-10">
										  <input type="zipCode" class="form-control" id="inputZipCode">
										</div>
									</div>
                                    <div class="form-group row">
                                        <label for="inputCountry" class="col-sm-2 col-form-label">Country:</label>
                                        <div class="col-sm-10">
                                          <input type="country" class="form-control" id="inputCountry">
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
                                                    <form action="./InsertData" method="post" name = "cardForm">
                                                        <div class="form-group row">
                    										<label for="inputFirstName" class="col-sm-2 col-form-label">First Name:</label>
                    										<div class="col-sm-10">
                    										  <input type="firstName" class="form-control" id="inputFirstName">
                    										</div>
                    									</div>
                    									<div class="form-group row">
                    										<label for="inputLastName" class="col-sm-2 col-form-label">Last Name:</label>
                    										<div class="col-sm-10">
                    										  <input type="lastName" class="form-control" id="inputLastName">
                    										</div>
                    									</div>
                                                        <div class="form-group row">
                    										<label for="inputCardNumber" class="col-sm-2 col-form-label">Card Number:</label>
                    										<div class="col-sm-10">
                    										  <input type="cardNumber" class="form-control" id="inputCardNumber">
                    										</div>
                    									</div>
                                                        <div class="form-group row">
                    										<label for="inputSecurityNumber" class="col-sm-2 col-form-label">Card Security Number:</label>
                    										<div class="col-sm-10">
                    										  <input type="securityNumber" class="form-control" id="inputSecurityNumber">
                    										</div>
                    									</div>
                                                        <div class="form-group row">
                    										<label for="inputExpiration" class="col-sm-2 col-form-label">Card Expiration Date:</label>
                    										<div class="col-sm-10">
                    										  <input type="cardExpiration" class="form-control" id="inputExpiration">
                    										</div>
                    									</div>
                                                        <div class="form-group row">
                    										<label for="inputCardType" class="col-sm-2 col-form-label">Card Type (Visa, MasterCard, Discover):</label>
                    										<div class="col-sm-10">
                    										  <input type="cardType" class="form-control" id="inputCardType">
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