<%@page import="auth.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="bg-primary text-light p-3 my-2">
	<%= User.getUser(session).getCart().getCount() %> oggetti nel carrello, 
	totale: € <%= String.format("%.2f", User.getUser(session).getCart().getTotal()) %> - 
	<a href="./checkout?idRistorante=<%= request.getParameter("idRistorante") %>" class="fw-bold text-light">Checkout</a>
</div>