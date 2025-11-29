<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="pt-BR" data-bs-theme="light">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LocalCenter - ${rental == null ? 'Nova Locação' : 'Editar Locação'}</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="navbar.jsp" />

	<div class="container mt-5">
		<div class="card shadow col-lg-8 offset-lg-2 col-sm-12">
			<div class="card-header bg-primary text-white">
				<h3 class="mb-0">${rental == null ? 'Registrar Nova Locação' : 'Editar Locação #' += rental.id}</h3>
			</div>
			<div class="card-body p-4">

				<c:if test="${result == 'success'}">
					<div class="alert alert-success alert-dismissible fade show">
						Locação salva com sucesso!
						<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
					</div>
				</c:if>

				<form action="rentalServlet" method="post" id="rentalForm">
					<input type="hidden" name="id" value="${rental != null ? rental.id : '0'}">
					
					<div class="mb-3">
						<label for="clientId" class="form-label">Cliente*</label>
						<select class="form-select" name="clientId" id="clientId" required>
							<option value="" disabled ${rental == null ? 'selected' : ''}>Selecione...</option>
							<c:forEach items="${clients}" var="c">
								<option value="${c.id}" ${rental != null && rental.client.id == c.id ? 'selected' : ''}>
									${c.name} (CPF: ${c.cpf})
								</option>
							</c:forEach>
						</select>
					</div>

					<div class="mb-3">
						<label for="carId" class="form-label">Veículo*</label>
						<select class="form-select" name="carId" id="carId" required onchange="calculateTotal()">
							<option value="" disabled ${rental == null ? 'selected' : ''}>Selecione...</option>
							<c:forEach items="${cars}" var="car">
								<option value="${car.id}" data-price="${car.dailyRate}" 
									${rental != null && rental.car.id == car.id ? 'selected' : ''}>
									${car.brand} ${car.model} - R$ ${car.dailyRate}/dia
								</option>
							</c:forEach>
						</select>
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="withdrawDate" class="form-label">Data de Retirada*</label>
							<input type="date" name="withdrawDate" id="withdrawDate" class="form-control" 
								required onchange="calculateTotal()" value="${rental.withdrawDate}">
						</div>
						<div class="col-md-6 mb-3">
							<label for="returnDate" class="form-label">Data de Devolução*</label>
							<input type="date" name="returnDate" id="returnDate" class="form-control" 
								required onchange="calculateTotal()" value="${rental.returnDate}">
						</div>
					</div>

					<div class="alert alert-secondary text-center mt-3">
						<h4>Valor Total: <span id="totalDisplay" class="text-success fw-bold">R$ 0,00</span></h4>
						<small class="text-muted" id="daysDisplay">0 diárias</small>
					</div>

					<div class="d-grid gap-2 mt-4">
						<button type="submit" class="btn btn-success btn-lg">Salvar</button>
						<a href="homeServlet?tab=rentals" class="btn btn-secondary">Cancelar</a>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="js/theme.js"></script>

	<script>
		function calculateTotal() {
			const carSelect = document.getElementById('carId');
			const withdrawInput = document.getElementById('withdrawDate');
			const returnInput = document.getElementById('returnDate');
			
			if (carSelect.selectedIndex < 0 || !withdrawInput.value || !returnInput.value) return;

			const option = carSelect.options[carSelect.selectedIndex];
			if(!option || !option.getAttribute('data-price')) return;

			const pricePerDay = parseFloat(option.getAttribute('data-price'));
			const start = new Date(withdrawInput.value);
			const end = new Date(returnInput.value);
			
			const diffTime = end - start;
			let diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); 
			if (diffDays < 1) diffDays = 1;
			
			if (end < start) {
				document.getElementById('totalDisplay').innerText = "Data Inválida";
				return;
			}

			const total = diffDays * pricePerDay;
			document.getElementById('totalDisplay').innerText = total.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
			document.getElementById('daysDisplay').innerText = diffDays + (diffDays === 1 ? ' diária' : ' diárias');
		}
		
		window.onload = calculateTotal;
	</script>
</body>
</html>