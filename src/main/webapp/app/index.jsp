<%@page import="java.util.ArrayList"%>
<%@page import="anagrafiche.RestaurantSummary"%>
<%@page import="auth.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp"></jsp:include>

	<h1>Delivery app</h1>
	<hr>
	<p>Ciao <%= User.getUser(session).getNome() %>, <a href="../logout.jsp">Esci</a></p>
	
	<% ArrayList<RestaurantSummary> list = RestaurantSummary.getAll(); %>
	
	<h3>Ristoranti (<%= list.size() %>)</h3>
	
	<div class="row g-3">
		<% for(RestaurantSummary r : list) { %>
			<div class="col-3">
				<div class="card">
				  <img src="../img/ristorante.webp" class="card-img-top">
				  <div class="card-body">
				    <h5 class="card-title"><%= r.getNome() %></h5>
				    <p class="card-text">Menu (<%= r.getMenuCount() %>)</p>
				    <a href="./order.jsp?idRistorante=<%= r.getId() %>" class="btn btn-primary <%= r.getMenuCount() == 0 ? "disabled" : "" %>">Scegli</a>
				    <a href="./order_grouped.jsp?idRistorante=<%= r.getId() %>" class="btn btn-secondary <%= r.getMenuCount() == 0 ? "disabled" : "" %>">Scegli (2)</a>
				  </div>
				</div>
			</div>
		<% } %>
	</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>