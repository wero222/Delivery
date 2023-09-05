<%@page import="anagrafiche.Product"%>
<%@page import="anagrafiche.RestaurantDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page import="anagrafiche.RestaurantSummary"%>
<%@page import="auth.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp"></jsp:include>

	<h1>Delivery app</h1>
	<hr>
	<p>Ciao <%= User.getUser(session).getNome() %>, <a href="../logout.jsp">Esci</a></p>
	
	<div class="bg-success text-light my-5 text-center p-5">
		<h1 class="display-1">Grazie</h1>
		<p>Il tuo ordine è stato ricevuto, buon appetito!</p>
		<small>NUMERO ORDINE #<%= request.getParameter("codOrdine") %></small>
		<hr>
		<a href="./" class="text-light">Fai un nuovo ordine</a>
	</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>