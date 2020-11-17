<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="models.User"
    import="models.UserStatus"
    import="models.ErrorMessage"
    import="models.Message"
    import="java.util.List"
    import="java.lang.Integer"
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" type="text/css" href="adminManageUsersStyle.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<title>BookBay | (Admin) Manage Users</title>
		
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
 								<a class="nav-link" href="#">Manage Books</a>
 							</li>
 							
 							<li>
 								<a class="nav-link" href="#">Manage Users</a>
 							</li>
 							
 							<li>
 								<a class="nav-link" href="#">Manage Promotions</a>
 							</li>
 						</ul>
 						
 						<form class="form-inline mr-auto" action="/WebContent/search.html">
 							<input class="form-control mr-sm-2" type="search" placeholder="Enter Book Name, ISBN, Genre, etc..." aria-label="Search" size="36">
 							<button class="btn btn-light my-2 my-sm-0" type="submit">Search</button>
 						</form>
 						<b style="margin-right: 3%; font-size: 15pt;">Admin</b>
 						<ul class="navbar-nav">
 							<li>
 								<a class="nav-link" href="index.html">Logout</a>
 							</li>
 						</ul>
 						
 					</div>
 				</nav>
			</div>
			
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

<!-- 			<div id="user-suspended" class="modal fade"> -->
<!-- 				<div class="modal-dialog modal-dialog-centered"> -->
<!-- 				  <div class="modal-content"> -->
<!-- 					<div class="modal-header"> -->
<!-- 					  <h5 class="modal-title">User Management</h5> -->
<!-- 					  <button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
<!-- 						<span aria-hidden="true">&times;</span> -->
<!-- 					  </button> -->
<!-- 					</div> -->
<!-- 					<div class="modal-body"> -->
<!-- 					  <p>User has been suspended.</p> -->
<!-- 					</div> -->
<!-- 					<div class="modal-footer"> -->
<!-- 						<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!-- 					</div> -->
<!-- 				  </div> -->
<!-- 				</div> -->
<!-- 			</div> -->

<!-- 			<div id="user-unsuspended" class="modal fade"> -->
<!-- 				<div class="modal-dialog modal-dialog-centered"> -->
<!-- 				  <div class="modal-content"> -->
<!-- 					<div class="modal-header"> -->
<!-- 					  <h5 class="modal-title">User Management</h5> -->
<!-- 					  <button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
<!-- 						<span aria-hidden="true">&times;</span> -->
<!-- 					  </button> -->
<!-- 					</div> -->
<!-- 					<div class="modal-body"> -->
<!-- 					  <p>User has been unsuspended.</p> -->
<!-- 					</div> -->
<!-- 					<div class="modal-footer"> -->
<!-- 						<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!-- 					</div> -->
<!-- 				  </div> -->
<!-- 				</div> -->
<!-- 			</div> -->

            <h1 id="user-title" class="font-weight-bold">Users</h1>
            <table id="user" class="table table-responsive-sm table-sm">
                <thead class="thead-dark">
                  <tr>
                    <th scope="col">ID</th>
                    <th scope="col">First</th>
                    <th scope="col">Last</th>
					<th scope="col">User Status</th>
					<th scope="col">Email Address</th>
					<th scope="col"></th>
                  </tr>
                </thead>
                <tbody>
                
                	<%
                	
                		List<User> customers = (List<User>) request.getAttribute("customers");
                	
                		for(int i = 0; i < customers.size(); i++) {
                			User currentCustomer = customers.get(i);
                			
                			out.println("<tr class=\"user-row\">");
                			out.println("<th scope=\"row\">" + currentCustomer.getUserID() + "</th>");
                			out.println("<td>" + currentCustomer.getFirstName() + "</td>");
                			out.println("<td>" + currentCustomer.getLastName() + "</td>");
                			out.println("<td>" + currentCustomer.getStatus() + "</td>");
                			out.println("<td>" + currentCustomer.getEmail() + "</td>");
                			out.println("<td>");
                			
                			if(currentCustomer.getStatus() != UserStatus.SUSPENDED) {
                				out.println("<form action=\"SuspendUser\" method=\"POST\" accept-charset=\"UTF-8\">");
                				out.println("<button type=\"submit\" class=\"btn btn-danger suspended\">Suspend user</button>");
                				out.println("<input type=\"hidden\" name=\"userID\" value=\"" + currentCustomer.getUserID() + "\"/>");
                				out.println("</form>");
                			}
                			
                			else {
                				out.println("<form action=\"DesuspendUser\" method=\"POST\" accept-charset=\"UTF-8\">");
								out.println("<button type=\"submit\" class=\"btn btn-success unsuspended\">Unsuspend user</button>");
								out.println("<input type=\"hidden\" name=\"userID\" value=\"" + currentCustomer.getUserID() + "\"/>");
								out.println("</form>");
                			}
                			
                			out.println("</td>");
                			out.println("</tr>");
                		}
                	
                	%>
                
<!--                   <tr class="user-row"> -->
<!--                     <th scope="row">1</th> -->
<!--                     <td>Mark</td> -->
<!--                     <td>Otto</td> -->
<!-- 					<td>Inactive</td> -->
<!-- 					<td>Unsuspended</td> -->
<!-- 					<td>markotto@gmail.com</td> -->
<!-- 					<td> -->
<!-- 						<button type="button" class="btn btn-danger suspended">Suspend user</button> -->
<!-- 					  	<button type="button" class="btn btn-success unsuspended">Unsuspend user</button> -->
<!-- 					</td> -->
<!--                   </tr> -->
<!--                   <tr class="user-row"> -->
<!--                     <th scope="row">2</th> -->
<!--                     <td>Jacob</td> -->
<!--                     <td>Thornton</td> -->
<!-- 					<td>Inactive</td> -->
<!-- 					<td>Unsuspended</td> -->
<!-- 					<td>JacobThron@yahoo.com</td> -->
<!-- 					<td> -->
<!-- 						<button type="button" class="btn btn-danger suspended">Suspend user</button> -->
<!-- 					  	<button type="button" class="btn btn-success unsuspended">Unsuspend user</button> -->
<!-- 					</td> -->
<!--                   </tr> -->
<!--                   <tr class="user-row"> -->
<!--                     <th scope="row">3</th> -->
<!--                     <td>Larry</td>  -->
<!--                     <td>Bird</td> -->
<!-- 					<td>Active</td> -->
<!-- 					<td>Suspended</td> -->
<!-- 					<td>Lbird@gmail.com</td> -->
<!-- 					<td> -->
<!-- 						<button type="button" class="btn btn-danger suspended">Suspend user</button> -->
<!-- 					  	<button type="button" class="btn btn-success unsuspended">Unsuspend user</button> -->
<!-- 					</td> -->
<!--                   </tr> -->
                </tbody>
            </table>
			<br>
			
<!-- 			<div id="admin-demoted" class="modal fade"> -->
<!-- 				<div class="modal-dialog modal-dialog-centered"> -->
<!-- 				  <div class="modal-content"> -->
<!-- 					<div class="modal-header"> -->
<!-- 					  <h5 class="modal-title">Admin Management</h5> -->
<!-- 					  <button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
<!-- 						<span aria-hidden="true">&times;</span> -->
<!-- 					  </button> -->
<!-- 					</div> -->
<!-- 					<div class="modal-body"> -->
<!-- 					  <p>This admin has received a depromotion!</p> -->
<!-- 					</div> -->
<!-- 					<div class="modal-footer"> -->
<!-- 						<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!-- 					</div> -->
<!-- 				  </div> -->
<!-- 				</div> -->
<!-- 			</div> -->

			<h1 id="admin-title" class="font-weight-bold">Admins</h1>
            <table id="admin" class="table table-responsive-sm table-sm">
                <thead class="thead-light">
                  <tr>
                    <th scope="col">ID</th>
                    <th scope="col">First</th>
                    <th scope="col">Last</th>
					<th scope="col">User Status</th>
					<th scope="col">Email Address</th>
					<th scope="col"></th>
                  </tr>
                </thead>
                <tbody>
                
                	<%
                	
                		List<User> admins = (List<User>) request.getAttribute("admins");
                	
                		Cookie sessionCookie = request.getCookies()[1];
                		int cookieUserID = Integer.parseInt(sessionCookie.getValue());
                	
	            		for(int i = 0; i < admins.size(); i++) {
	            			User currentAdmin = admins.get(i);
	            			
	            			out.println("<tr class=\"user-row\">");
	            			out.println("<th scope=\"row\">" + currentAdmin.getUserID() + "</th>");
	            			out.println("<td>" + currentAdmin.getFirstName() + "</td>");
	            			out.println("<td>" + currentAdmin.getLastName() + "</td>");
	            			out.println("<td>" + currentAdmin.getStatus() + "</td>");
	            			out.println("<td>" + currentAdmin.getEmail() + "</td>");
	            			out.println("<td>");
	            			
	            			if(cookieUserID != currentAdmin.getUserID()) {
		            			out.println("<form action=\"DepromoteAdmin\" method=\"POST\" accept-charset=\"UTF-8\">");
		            			out.println("<button type=\"submit\" class=\"btn btn-danger demoted\">Depromote admin</button>");
		            			out.println("<input type=\"hidden\" name=\"userID\" value=\"" + currentAdmin.getUserID() + "\"/>");
		            			out.println("</form>");
	            			}
	            			
	            			else {
	            				out.println("(Current User)");
	            			}
	            		
	            			out.println("</td>");
	            			out.println("</tr>");
	            		}
                	
                	%>
                
<!--                   <tr> -->
<!--                     <th scope="row">1</th> -->
<!--                     <td>Mark</td> -->
<!--                     <td>Otto</td> -->
<!-- 					<td>Inactive</td> -->
<!-- 					<td>markotto@gmail.com</td> -->
<!-- 					<td><button type="button" class="btn btn-danger demoted">Depromote admin</button></td> -->
<!--                   </tr> -->
<!--                   <tr> -->
<!--                     <th scope="row">2</th> -->
<!--                     <td>Jacob</td> -->
<!--                     <td>Thornton</td> -->
<!-- 					<td>Active</td> -->
<!-- 					<td>JacobThron@yahoo.com</td> -->
<!-- 					<td><button type="button" class="btn btn-danger demoted">Depromote admin</button></td> -->
<!--                   </tr> -->
<!--                   <tr> -->
<!--                     <th scope="row">3</th> -->
<!--                     <td>Larry</td>  -->
<!--                     <td>Bird</td> -->
<!-- 					<td>Active</td> -->
<!-- 					<td>Lbird@gmail.com</td> -->
<!-- 					<td><button type="button" class="btn btn-danger demoted">Depromote admin</button></td> -->
<!--                   </tr> -->
                </tbody>
            </table>
			<br>

<!-- 			<div id="employee-promoted" class="modal fade"> -->
<!-- 				<div class="modal-dialog modal-dialog-centered"> -->
<!-- 				  <div class="modal-content"> -->
<!-- 					<div class="modal-header"> -->
<!-- 					  <h5 class="modal-title">Employee Management</h5> -->
<!-- 					  <button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
<!-- 						<span aria-hidden="true">&times;</span> -->
<!-- 					  </button> -->
<!-- 					</div> -->
<!-- 					<div class="modal-body"> -->
<!-- 					  <p>This employee has received a promotion!</p> -->
<!-- 					</div> -->
<!-- 					<div class="modal-footer"> -->
<!-- 						<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!-- 					</div> -->
<!-- 				  </div> -->
<!-- 				</div> -->
<!-- 			</div> -->
			
			<h1 id="employee-title" class="font-weight-bold">Employees</h1>
            <table id="employee" class="table table-responsive-sm table-sm">
                <thead class="thead-dark">
                  <tr>
                    <th scope="col">ID</th>
                    <th scope="col">First</th>
                    <th scope="col">Last</th>
					<th scope="col">User Status</th>
					<th scope="col">Email Address</th>
					<th scope="col"></th>
                  </tr>
                </thead>
                <tbody>
                
                	<%
                		
                		List<User> employees = (List<User>) request.getAttribute("employees");
                	
	            		for(int i = 0; i < employees.size(); i++) {
	            			User currentEmployee = employees.get(i);
	            			
	            			out.println("<tr class=\"user-row\">");
	            			out.println("<th scope=\"row\">" + currentEmployee.getUserID() + "</th>");
	            			out.println("<td>" + currentEmployee.getFirstName() + "</td>");
	            			out.println("<td>" + currentEmployee.getLastName() + "</td>");
	            			out.println("<td>" + currentEmployee.getStatus() + "</td>");
	            			out.println("<td>" + currentEmployee.getEmail() + "</td>");
	            			out.println("<td>");
	            			out.println("<form action=\"PromoteEmployee\" method=\"POST\" accept-charset=\"UTF-8\">");
	            			out.println("<button type=\"submit\" class=\"btn btn-success promoted\">Promote employee</button>");
	            			out.println("<input type=\"hidden\" name=\"userID\" value=\"" + currentEmployee.getUserID() + "\"/>");
	            			out.println("</form>");
	            			out.println("</td>");
	            			out.println("</tr>");
	            		}
                	
                	%>
                
<!--                   <tr class="employee-row"> -->
<!--                     <th scope="row">1</th> -->
<!--                     <td>Mark</td> -->
<!--                     <td>Otto</td> -->
<!-- 					<td>Inactive</td> -->
<!-- 					<td>markotto@gmail.com</td> -->
<!-- 					<td><button type="button" class="btn btn-success promoted">Promote employee</button></td> -->
<!--                   </tr> -->
<!--                   <tr class="employee-row"> -->
<!--                     <th scope="row">2</th> -->
<!--                     <td>Jacob</td> -->
<!--                     <td>Thornton</td> -->
<!-- 					<td>Active</td> -->
<!-- 					<td>JacobThron@yahoo.com</td> -->
<!-- 					<td><button type="button" class="btn btn-success promoted">Promote employee</button></td> -->
<!--                   </tr> -->
<!--                   <tr class="employee-row"> -->
<!--                     <th scope="row">3</th> -->
<!--                     <td>Larry</td>  -->
<!--                     <td>Bird</td> -->
<!-- 					<td>Inactive</td> -->
<!-- 					<td>Lbird@gmail.com</td> -->
<!-- 					<td><button type="button" class="btn btn-success promoted">Promote employee</button></td> -->
<!--                   </tr> -->
                </tbody>
            </table>
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
		<script type="text/javascript" src="adminManagerUsers.js"></script>
	</body>
</html>