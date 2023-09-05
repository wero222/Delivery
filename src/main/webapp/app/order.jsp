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
	
	<jsp:include page="../includes/cartWidget.jsp"></jsp:include>
	
	<%
		String idRistorante = request.getParameter("idRistorante");
		RestaurantDetail r = new RestaurantDetail(Integer.parseInt(idRistorante), false);
	%>
	
	<jsp:include page="../includes/alert.jsp"></jsp:include>
	<%
		// Provo a prendere l'attribute "PRODUCT_ADDED"
		// che contiene l'oggetto che ho aggiunto al cart
		Product product = (Product)request.getAttribute("PRODUCT_ADDED");
		if(product != null){
	%>
		<div class="alert alert-success">
			<%= product.getNome() %> aggiunto al carrello!
		</div>
	<% } %>
	
	<p>Stai ordinando su</p>
	<h3><%= r.getNome() %></h3>
	<% if(r.getIndirizzo() != null) { %>
		<p><%= r.getIndirizzo().getIndirizzo() %> - <%= r.getIndirizzo().getCivico() %></p>
	<% } %>
	
	<h5>Cosa vuoi mangiare oggi?</h5>
	<% for(Product p : r.getMenu()) { %>
		<div class="bg-light border p-3 d-flex align-items-center">
			<div>
				<strong><%= p.getNome() %></strong>
				<p class="mb-0"><%= p.getDescrizione() %></p>
			</div>
			<!-- 
			<a href="#" class="btn btn-outline-primary ms-auto">€ <%= String.format("%.2f", p.getPrezzo()) %></a>
			-->
			<form class="w-25 ms-auto" method="post" action="./add-to-cart">
				<div class="input-group">
				  <input type="text" class="form-control" placeholder="Qtà..." name="qta">
				  <input type="hidden" name="idRistorante" value="<%= idRistorante %>">
				  <input type="hidden" name="pid" value="<%= p.getId() %>">
				  <button class="btn btn-outline-secondary" type="submit">€ <%= String.format("%.2f", p.getPrezzo()) %></button>
				</div>
			</form>
			
			<% if(User.getUser(session).getCart().productExists(p.getId())) { %>
				<a href="#" class="btn btn-danger ms-1">Rimuovi</a>
			<% } %>
			
		</div>
	<% } %>

<jsp:include page="../layout/footer.jsp"></jsp:include>