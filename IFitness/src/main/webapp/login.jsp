<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!doctype html>
<html lang="pt-BR" data-bs-theme="light">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="css/styles.css" rel="stylesheet">
    <title>IFitness - Página de Login</title>
  </head>
  <body>
  	<div class="container">
		<div class="col-lg-4 offset-lg-4 col-sm-12">
			<c:choose>
				<c:when test="${result == 'registered'}">
					<div class="alert alert-success alert-dismissible fade show"
						role="alert">
						Usuário cadastrado com sucesso. Faça o login.
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:when>
				<c:when test="${result == 'loginError'}">
					<div class="alert alert-danger alert-dismissible fade show"
						role="alert">
						E-mail e/ou senha inválidos.
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:when>
			</c:choose>
			
	  		<form action="loginServlet" method="post">
	  				<h1 class="text-center">Login</h1>
	  				
	  				<div class="input-group mb-3">
	  					<span class="input-group-text">
	  						<jsp:include page="img/envelope_icon.jsp" />
	  					</span>
	  					<input type="email" name="email" id="email"
	  						placeholder="E-mail" required="required"
	  						class="form-control">
	  				</div>
	  				
	  				<div class="input-group mb-3">
	  					<span class="input-group-text">
	  						<jsp:include page="img/file-lock_icon.jsp" />
	  					</span>
	  					<input type="password" name="password" id="password"
	  						placeholder="Senha" required="required"
	  						class="form-control">
	  				</div>
	  				
	  				<div class="mb-3">
	  					<button type="submit" class="btn btn-primary">Login</button>
	  				</div>
	  				
	  				<div class="mb-3">
	  					<a href="user-register.jsp" id="link"
	  						class="btn btn-secondary">Cadastrar</a>
	  				</div>
	  			
	  		</form>
  		</div>
  	</div>

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	<script type="text/javascript" src="js/theme.js"></script>
  </body>
</html>