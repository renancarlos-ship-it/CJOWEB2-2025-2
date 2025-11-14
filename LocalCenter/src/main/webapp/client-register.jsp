<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LocalCenter - Página de Cadastro de Cliente</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="homeServlet">LocalCenter</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link" href="homeServlet">Home</a>
	        </li>
	      </ul>
	    </div>
	  </div>
	</nav>
	<div class="container ">
		<div class="center col-lg-6 offset-lg-3 col-sm-12">
				<c:if test="${result == 'registered'}">
					<div class="alert alert-success alert-dismissible fade show"
						role="alert">
						Cliente cadastrado com sucesso.
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:if>
				<c:if test="${result == 'notRegistered'}">
					<div class="alert alert-danger alert-dismissible fade show"
						role="alert">
						Cliente não cadastrado. Faça o login.
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:if>
			<form action="clientRegister" method="post" id="form1">

					<c:choose>
						<c:when test="${client == null}">
							<h1 class="text-center">Novo Cliente</h1>
						</c:when>
						<c:when test="${client != null}">
							<h1 class="text-center">Edição de Cliente</h1>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${client == null}">
							<input type="hidden" name="id" value="0">
						</c:when>
						<c:when test="${client != null}">
							<input type="hidden" name="id" value="${car.id}">
						</c:when>
					</c:choose>
					
					<div class="mb-2">
						<label for="name">Nome Completo*</label> <input type="text"
						name="name" id="name" class="form-control" minlength="3"
						maxlength="50" required="required"> <span id="0"></span>
					</div>
					<div class="mb-2">
						<label for="dateOfBirth">Data de Nascimento*</label> <input type="date"
						name="dateOfBirth" id="dateOfBirth" class="form-control" required="required">
						 <span id="0"></span>
					</div>
					<div class="mb-2">
						<label for="cpf">CPF*</label> <input type="text"
						name="cpf" id="cpf" class="form-control" minlength="3"
						maxlength="50" required="required"> <span id="0"></span>
					</div>
					<div class="mb-2">
						<label for="cnh">CNH*</label> <input type="text"
						name="cnh" id="cnh" class="form-control" minlength="3"
						maxlength="50" required="required"> <span id="0"></span>
					</div>
					<div class="mb-2">
						<label for="address">Endereço*</label> <input type="text"
						name="address" id="address" class="form-control" minlength="3"
						maxlength="50" required="required"> <span id="0"></span>
					</div>
					<div class="mb-2">
						<label for="phoneNumber">Celular*</label> <input type="text"
						name="phoneNumber" id="phoneNumber" class="form-control" minlength="3"
						maxlength="50" required="required"> <span id="0"></span>
					</div>
					<div class="col-12 mb-2">
						<button type="submit" class="btn btn-primary">Salvar</button>
					</div>
			</form>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>