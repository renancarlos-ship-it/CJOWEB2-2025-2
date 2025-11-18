<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LocalCenter - Registrar Locação</title>
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
					Locação registrada com sucesso!
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
			</c:if>
			<c:if test="${not empty error}">
				<div class="alert alert-danger alert-dismissible fade show"
					role="alert">
					Erro ao registrar locação: ${error}
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
			</c:if>
			
			<form action="rentalCar" method="post" id="form1"> 

					<c:choose>
						<c:when test="${rentalCar == null}">
							<h1 class="text-center">Nova Locação</h1>
						</c:when>
						<c:when test="${rentalCar != null}">
							<h1 class="text-center">Edição de Locação</h1>
						</c:when>
					</c:choose>
					
					<c:choose>
						<c:when test="${rentalCar == null}">
							<input type="hidden" name="id" value="0">
						</c:when>
						<c:when test="${rentalCar != null}">
							<input type="hidden" name="id" value="${rentalCar.id}">
						</c:when>
					</c:choose>
					
					
					<div class="mb-2">
						<label for="clientId">Cliente*</label>
						<select id="clientId" name="clientId" class="form-control" required="required">
							<option value="">Selecione o Cliente</option>
							<c:forEach var="client" items="${clients}">
								<option value="${client.id}" <c:if test="${rentalCar.client.id == client.id}">selected</c:if>>
									${client.name} (CPF: ${client.cpf})
								</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="mb-2">
						<label for="carId">Carro Disponível*</label>
						<select id="carId" name="carId" class="form-control" required="required">
							<option value="">Selecione o Carro</option>
							<c:forEach var="car" items="${availableCars}">
								<option value="${car.id}" <c:if test="${rentalCar.car.id == car.id}">selected</c:if>>
									${car.model} (${car.plate})
								</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="mb-2">
						<label for="dailyRateValue">Valor da Diária (R$)*</label> 
						<input type="number" step="0.01" name="dailyRateValue" id="dailyRateValue" class="form-control" 
						required="required" value="${rentalCar.dailyValue}">
					</div>
					<div class="mb-2">
						<label for="withdrawDate">Data de Retirada*</label> 
						<input type="date" name="withdrawDate" id="withdrawDate" class="form-control" 
						required="required" value="${rentalCar.withdrawDate}">
					</div>
					<div class="mb-2">
						<label for="returnDate">Data Prevista de Devolução*</label> 
						<input type="date" name="returnDate" id="returnDate" class="form-control" 
						required="required" value="${rentalCar.returnDate}">
					</div>

					<div class="col-12 mb-2">
						<button type="submit" class="btn btn-primary">Confirmar Reserva</button>
					</div>
			</form>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>