<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="javax.servlet.http.Cookie, dataAccess.*"
    import="models.ErrorMessage"
    import="models.Message"
    import="models.User"
    import="models.Address"
    import="models.PaymentCard"
    import="models.Promotion"
%>

<%
	User user = (User) request.getAttribute("user");

	Promotion promotion = (Promotion) request.getAttribute("promotion");
	PromotionDA promotionDA = new PromotionDA();
%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" type="text/css" href="adminManagePromotionsStyle.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<title>BookBay | (Admin) Manage Promotions</title>
		
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
 								<a class="nav-link" href="adminManageBooks.html">Manage Books</a>
 							</li>
 							
 							<li>
 								<a class="nav-link" href="adminManageUsers.html">Manage Users</a>
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
				
					<div id="added" class="row row-cols-5">
						<div class="col-3">
                            <button type="button" class="btn btn-primary center" data-toggle="modal" data-target="#coupon1" style="background-color: transparent; border: none;">
                                <div class="coupon">
                                    <div class="coupon-container bg-danger">
                                        <h2 class="coupon-header"><b>20% OFF</b></h2>
                                    </div>
                                </div>
							</button>
						</div>
						<div class="col-2">
							<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#coupon2" style="background-color: transparent; border: none;">
                                <div class="coupon">
                                    <div class="coupon-container bg-success">
                                        <h2 class="coupon-header"><b>30% OFF</b></h2>
                                    </div>
                                </div>
							</button>
						</div>
						<div class="col-2">
							<button type="button" class="btn btn-primary center" data-toggle="modal" data-target="#coupon3" style="background-color: transparent; border: none;">
								<div class="coupon">
                                    <div class="coupon-container bg-warning">
                                        <h2 class="coupon-header"><b>40% OFF</b></h2>
                                    </div>
                                </div>
							</button>
						</div>
						<div class="col">
							<button type="button" class="btn btn-primary center" data-toggle="modal" data-target="#coupon4" style="background-color: transparent; border: none;">
								<div class="coupon">
                                    <div class="coupon-container bg-primary">
                                        <h2 class="coupon-header"><b>50% OFF</b></h2>
                                    </div>
                                </div>
							</button>
						</div>
                    </div>
                    <div id="adding" class="row row-cols-2">
						<div class="col" style="margin-top: 10%; margin-bottom: 10%;">
                            <button type="button" id="promotion" class="btn btn-primary center" data-toggle="modal" data-target="#addcoupon" style="background-color: transparent; border: none;">
                                <img src="img/addcoupon.png" class="rounded mx-auto d-block" alt="New Ticket icon by Icons8">
                            </button>
                        </div>					
					</div>
			
                    <!-- MODALS -->
                    <div class="modal fade" id="coupon1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">20% off coupons</h5>
                                </div>
                                <div class="modal-body">
                                
                               <%
                               Promotion promotion20 = new Promotion();
                               int couponCount20 = 0;

                               for (int i = 0; i < 100; i++){
                            	   promotion20 = promotionDA.getPromotionByCode(i);
                            	   if (promotion20 != null && promotion20.getPercentage() == 20){
                            		   out.println("<p>Promotion " + promotion20.getPromotionCode() + ": Discount=" + promotion20.getPercentage()
   									+ "% Expiration=" + promotion20.getExpDate() + " Start=" + promotion20.getStartDate() + "</p>");
                            		   couponCount20++;
                            	   }
                               }
                               if (couponCount20 == 0) {
                            	   out.println("No active coupons");
                               }
								%>
								
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal fade" id="coupon2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">30% off coupons</h5>
                                </div>
                                <div class="modal-body">                              
							<%
                               Promotion promotion30 = new Promotion();
                               int couponCount30 = 0;

                               for (int i = 0; i < 100; i++){
                            	   promotion30 = promotionDA.getPromotionByCode(i);
                            	   if (promotion30 != null && promotion30.getPercentage() == 30){
                            		   out.println("<p>Promotion " + promotion30.getPromotionCode() + ": Discount=" + promotion30.getPercentage()
   									+ "% Expiration=" + promotion30.getExpDate() + " Start=" + promotion30.getStartDate() + "</p>");
                            		   couponCount30++;
                            	   }
                               }
                               if (couponCount30 == 0) {
                            	   out.println("No active coupons");
                               }
							%>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal fade" id="coupon3" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">40% off coupons</h5>
                                </div>
                                <div class="modal-body">
                            <%
                               Promotion promotion40 = new Promotion();
                               int couponCount40 = 0;

                               for (int i = 0; i < 100; i++){
                            	   promotion40 = promotionDA.getPromotionByCode(i);
                            	   if (promotion40 != null && promotion40.getPercentage() == 40){
                            		   out.println("<p>Promotion " + promotion40.getPromotionCode() + ": Discount=" + promotion40.getPercentage()
   									+ "% Expiration=" + promotion40.getExpDate() + " Start=" + promotion40.getStartDate() + "</p>");
                            		   couponCount40++;
                            	   }
                               }
                               if (couponCount40 == 0) {
                            	   out.println("No active coupons");
                               }
							%>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal fade" id="coupon4" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">50% off coupons</h5>
                                </div>
                                <div class="modal-body">
                               <%
                               Promotion promotion50 = new Promotion();
                               int couponCount50 = 0;

                               for (int i = 0; i < 100; i++){
                            	   promotion50 = promotionDA.getPromotionByCode(i);
                            	   if (promotion50 != null && promotion50.getPercentage() == 50){
                            		   out.println("<p>Promotion " + promotion50.getPromotionCode() + ": Discount=" + promotion50.getPercentage()
   									+ "% Expiration=" + promotion50.getExpDate() + " Start=" + promotion50.getStartDate() + "</p>");
                            		   couponCount50++;
                            	   }
                               }
                               if (couponCount50 == 0) {
                            	   out.println("No active coupons");
                               }
								%>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal fade" id="addcoupon" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title mx-auto" id="exampleModalLabel">Add a new coupon</h5>
                                </div>
                                <form id="promotion" class="needs-validation" action="SendPromotion" method="POST" accept-charset="UTF-8" novalidate>
                                    <div class="modal-body">
                                        
                                        <div class="form-group">
                                            <label for="code">Code</label>
                                            <input type="text" class="form-control" name="promotionCode" required>
                                            <div class="invalid-feedback">
											Please provide a valid code that is unique and is less than 100.
										  </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="amount">Discount</label>
                                            <input type="text" class="form-control" name="percentage" required>
                                            <div class="invalid-feedback">
											Please choose 20, 30, 40, or 50.
										  </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="limit">Start Date</label>
                                            <input type="text" class="form-control" name="startDate" required>
                                            <div class="invalid-feedback">
											Please provide a start date.
										  </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="date">Expiration Date</label>
                                            <input type="text" class="form-control" name="expDate" required>
                                            <div class="invalid-feedback">
											Please provide an expiration date.
										  </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        <button id="add-coupon" type="submit" class="btn btn-primary">Add coupon</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
				</div>
	    	</div>
	    	<script type="text/javascript" src="main.js"></script>	
	    	<br>
	    	<br>
	    	
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
        <script type="text/javascript" src="adminManagePromotions.js"></script>
	</body>
</html>