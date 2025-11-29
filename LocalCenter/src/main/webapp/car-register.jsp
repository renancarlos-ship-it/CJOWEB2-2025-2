<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html lang="pt-BR" data-bs-theme="light">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LocalCenter - Cadastro de Veículo</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
	<link href="css/styles.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	
	<div class="container mt-5">
		<div class="card shadow col-lg-6 offset-lg-3 col-sm-12">
			<div class="card-body p-4">
				
				<c:if test="${result == 'registered'}">
					<div class="alert alert-success alert-dismissible fade show" role="alert">
						Veículo salvo com sucesso!
						<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
				</c:if>
				<c:if test="${result == 'error'}">
					<div class="alert alert-danger alert-dismissible fade show" role="alert">
						Erro ao salvar o veículo. Verifique os dados e tente novamente.
						<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
				</c:if>
				
				<form action="carRegisterServlet" method="post" id="formCar">
					
					<c:choose>
						<c:when test="${car == null}">
							<h2 class="text-center mb-4">Novo Veículo</h2>
						</c:when>
						<c:when test="${car != null}">
							<h2 class="text-center mb-4">Editar Veículo</h2>
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

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="brand" class="form-label">Marca*</label> 
							<input type="text" name="brand" id="brand" class="form-control" 
								required="required" placeholder="Ex: Fiat" value="${car.brand}">
						</div>
						
						<div class="col-md-6 mb-3">
							<label for="model" class="form-label">Modelo*</label> 
							<input type="text" name="model" id="model" class="form-control" 
								required="required" placeholder="Ex: Uno" value="${car.model}">
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="color" class="form-label">Cor*</label> 
							<input type="text" name="color" id="color" class="form-control" 
								required="required" placeholder="Ex: Prata" value="${car.color}">
						</div>

						<div class="col-md-6 mb-3">
							<label for="year" class="form-label">Ano Fabricação*</label> 
							<input type="number" name="year" id="year" class="form-control" 
								required="required" min="1900" max="2100" value="${car.year}">
						</div>
					</div>

					<div class="mb-3">
						<label for="plate" class="form-label">Placa*</label> 
						<input type="text" name="plate" id="plate" class="form-control" 
							required="required" placeholder="ABC-1234" value="${car.plate}" maxlength="8" style="text-transform: uppercase;">
					</div>

					<div class="mb-3">
						<label for="dailyRate" class="form-label">Valor da Diária (R$)*</label> 
						<div class="input-group">
							<span class="input-group-text">R$</span>
							<input type="number" name="dailyRate" id="dailyRate" class="form-control" 
								required="required" step="0.01" min="0" placeholder="0.00" value="${car.dailyRate}">
						</div>
					</div>

					<div class="d-grid gap-2 mt-4">
						<button type="submit" class="btn btn-primary btn-lg">Salvar Veículo</button>
						<a href="homeServlet" class="btn btn-secondary">Cancelar</a>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript" src="js/theme.js"></script>
</body>
</html>