<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="./layout/header.jsp"></jsp:include>

<% session.invalidate(); %>

<h1>Ti sei disconnesso</h1>
<a href="./login.jsp">Torna alla pagina di login</a>

<jsp:include page="./layout/footer.jsp"></jsp:include>