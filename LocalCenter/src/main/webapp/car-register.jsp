<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LocalCenter - Página de Cadastro de Carro</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<div class="container ">
		<div class="center col-lg-6 offset-lg-3 col-sm-12">
				<c:if test="${result == 'registered'}">
					<div class="alert alert-success alert-dismissible fade show"
						role="alert">
						Carro cadastrado com sucesso.
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:if>
				<c:if test="${result == 'notRegistered'}">
					<div class="alert alert-danger alert-dismissible fade show"
						role="alert">
						Carro não cadastrado. Faça o login.
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:if>
			<form action="carRegister" method="post" id="form1">

					<c:choose>
						<c:when test="${car == null}">
							<h1 class="text-center">Novo Carro</h1>
						</c:when>
						<c:when test="${car != null}">
							<h1 class="text-center">Edição de Carro</h1>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${car == null}">
							<input type="hidden" name="id" value="0">
						</c:when>
						<c:when test="${car != null}">
							<input type="hidden" name="id" value="${car.id}">
						</c:when>
					</c:choose>
					
					<div class="mb-2">
						<label for="brand">Marca*</label> <input type="text"
						name="brand" id="brand" class="form-control" minlength="3"
						maxlength="50" required="required"> <span id="0"></span>
					</div>
					<div class="mb-2">
						<label for="model">Modelo*</label> <input type="text"
						name="model" id="model" class="form-control" minlength="3"
						maxlength="50" required="required"> <span id="0"></span>
					</div>
					<div class="mb-2">
						<label for="color">Cor*</label> <input type="text"
						name="color" id="color" class="form-control" minlength="3"
						maxlength="50" required="required"> <span id="0"></span>
					</div>
					<div class="mb-2">
						<label for="year">Ano*</label> <input type="text"
						name="year" id="year" class="form-control" minlength="3"
						maxlength="50" required="required"> <span id="0"></span>
					</div>
					<div class="mb-2">
						<label for="plate">Placa*</label> <input type="text"
						name="plate" id="plate" class="form-control" minlength="3"
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