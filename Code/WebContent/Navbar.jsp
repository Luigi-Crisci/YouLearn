<%@page import="bean.AccountBean.Ruolo"%>
<%@page import="bean.AccountBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<!-- Bootstrap-->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<!-- CSS Style & Font-->
<link rel="stylesheet" href="CSS/Navbar.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">
<link
	href="https://fonts.googleapis.com/css?family=Permanent+Marker|Ubuntu"
	rel="stylesheet">

<!-- Script-->

<script type="text/javascript" src="JS/jquery-3.3.1.js"></script>
<script type="text/javascript" src="JS/jquery-ui.js"></script>
<!-- include the core styles -->
<link rel="stylesheet" href="JS/alertify.js-0.3.11/themes/alertify.core.css" />
<!-- include a theme, can be included into the core instead of 2 separate files -->
<link rel="stylesheet" href="JS/alertify.js-0.3.11/themes/alertify.default.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>
	
	<link rel="apple-touch-icon" sizes="57x57" href="Images/Favicon/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60" href="Images/Favicon/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72" href="Images/Favicon/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76" href="Images/Favicon/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114" href="Images/Favicon/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120" href="Images/Favicon/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144" href="Images/Favicon/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152" href="Images/Favicon/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180" href="Images/Favicon/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192"  href="Images/Favicon/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32" href="Images/Favicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96" href="Images/Favicon/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16" href="Images/Favicon/favicon-16x16.png">
<link rel="manifest" href="/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="Images/Favicons/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">

<title>YouLearn</title>
</head>

<body>
	<%                    	AccountBean account=(AccountBean)request.getSession().getAttribute("account");
 %>
	<nav id="nav" class="navbar navbar-expand-lg navbar-light bg-light">
		<%if(account.getTipo().equals(Ruolo.Utente)){ %>
		<a class="navbar-brand title"
			href="http://localhost:8080/YouLearn/HomepageUtente.jsp"> <%}else { %>
			<a class="navbar-brand title"
			href="http://localhost:8080/YouLearn/HomepageSupervisore.jsp"> <%} %>
				<img class="logo" src="Images/Logo1.png" width="50px" height="50px"
				class="d-inline-block align-center" alt="">
				<p>YouLearn</p>
				<p class="sub-title">Increse your knowledges</p>
		</a>

			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarNavDropdown">

				<%
		if(account.getTipo().equals(Ruolo.Utente)){
			
			 %>
				<form class="form-inline mx-auto">
					<input class="form-control mr-sm-2 mx-auto" type="search"
						name="search" placeholder="Search" aria-label="Search">
					<button class="btn btn-outline-success my-2 my-sm-0 mx-auto"
						type="submit"
						formaction="http://localhost:8080/YouLearn/SearchCorso">Search</button>
				</form>

				<ul class="navbar-nav" style="float: right">

					<li class="nav-item active "><i class="fas fa-home fa-2x "
						style="float: left"></i> <!-- Il pulsante di homepage varia in base al tipo di utente -->
						<%
                    	if(account.getTipo().equals(Ruolo.Utente)){
                    %> <a class="nav-link"
						href="http://localhost:8080/YouLearn/HomepageUtente.jsp"
						style="float: right">Homepage <span class="sr-only">(current)</span></a>
						<% }
                    	else{
                    %> <a class="nav-link"
						href="http://localhost:8080/YouLearn/HomepageSupervisore.jsp"
						style="float: right">Homepage <span class="sr-only">(current)</span></a>
						<%} %></li>

					<li class="nav-item active"><i
						class="fas fa-sign-out-alt fa-2x" style="float: left"></i> <a
						class="nav-link"
						href="http://localhost:8080/YouLearn/LogoutServlet"
						style="float: right">Logout</a></li>
				</ul>
				<%} else { %>

				<ul class="navbar-nav ml-auto" style="float: right">

					<li class="nav-item active "><i class="fas fa-home fa-2x "
						style="float: left"></i> <!-- Il pulsante di homepage varia in base al tipo di utente -->
						<%
                    	if(account.getTipo().equals(Ruolo.Utente)){
                    %> <a class="nav-link"
						href="http://localhost:8080/YouLearn/HomepageUtente.jsp"
						style="float: right">Homepage <span class="sr-only">(current)</span></a>
						<% }
                    	else{
                    %> <a class="nav-link"
						href="http://localhost:8080/YouLearn/HomepageSupervisore.jsp"
						style="float: right">Homepage <span class="sr-only">(current)</span></a>
						<%} %></li>

					<li class="nav-item active"><i
						class="fas fa-sign-out-alt fa-2x" style="float: left"></i> <a
						class="nav-link"
						href="http://localhost:8080/YouLearn/LogoutServlet"
						style="float: right">Logout</a></li>
				</ul>
				<%} %>
			</div>
	</nav>


</body>

</html>