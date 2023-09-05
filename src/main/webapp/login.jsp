<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="./layout/header.jsp"></jsp:include>

<h1>Accedi</h1>
<jsp:include page="./includes/alert.jsp"></jsp:include>
<form action="./login" method="post">

	<div class="mb-3">
		<label for="email">Email</label>
		<input type="email" name="email" id="email" class="form-control">
	</div>
	
	<div class="mb-3">
		<label for="pwd">Password</label>
		<input type="password" name="pwd" id="pwd" class="form-control">
	</div>
	
	<button type="submit" class="btn btn-primary">Accedi all'app</button>
	
	<br><br>
	<a href="./register.jsp">Non hai un account? Registrati</a>
	
</form>

<jsp:include page="./layout/footer.jsp"></jsp:include>