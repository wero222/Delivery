<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="./layout/header.jsp"></jsp:include>

<h1>Crea un account</h1>
<jsp:include page="./includes/alert.jsp"></jsp:include>
<form action="./register" method="post">

	<div class="mb-3">
		<label for="nome">Nome</label>
		<input type="text" name="nome" id="nome" class="form-control">
	</div>
	<div class="mb-3">
		<label for="cognome">Cognome</label>
		<input type="text" name="cognome" id="cognome" class="form-control">
	</div>
	<div class="mb-3">
		<label for="email">Email</label>
		<input type="email" name="email" id="email" class="form-control">
	</div>
	<div class="mb-3">
		<label for="pwd">Password</label>
		<input type="password" name="pwd" id="pwd" class="form-control">
	</div>
	<button type="submit" class="btn btn-primary">Crea account</button>
	
	<br><br>
	<a href="./login.jsp">Hai già un account? Accedi</a>
	
</form>

<jsp:include page="./layout/footer.jsp"></jsp:include>