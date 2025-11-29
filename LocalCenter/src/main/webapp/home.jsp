<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="pt-BR" data-bs-theme="light">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LocalCenter - Painel Principal</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	
	<div class="container mt-4">
		<div class="col-lg-12 col-sm-12">
			
			<h2 class="text-center mb-4">Painel de Controle</h2>
			
			<div class="row mb-4">
			    <div class="col-md-3">
			        <div class="card text-white bg-success shadow-sm h-100">
			            <div class="card-body">
			                <h6 class="card-title text-uppercase mb-2">Faturamento</h6>
			                <c:set var="totalRevenue" value="0" />
			                <c:forEach var="rental" items="${rentalList}">
			                    <c:set var="totalRevenue" value="${totalRevenue + rental.totalValue}" />
			                </c:forEach>
			                <h3 class="card-text fw-bold">
			                    R$ <fmt:formatNumber value="${totalRevenue}" minFractionDigits="2" maxFractionDigits="2"/>
			                </h3>
			            </div>
			        </div>
			    </div>
			    <div class="col-md-3">
			        <div class="card text-dark bg-info bg-opacity-25 shadow-sm h-100 border-info">
			            <div class="card-body">
			                <h6 class="card-title text-uppercase mb-2">Frota Total</h6>
			                <h3 class="card-text fw-bold text-info text-emphasis-info">
			                    ${fn:length(carList)} <span class="fs-6 text-muted">veículos</span>
			                </h3>
			            </div>
			        </div>
			    </div>
			    <div class="col-md-3">
			        <div class="card text-dark bg-warning bg-opacity-25 shadow-sm h-100 border-warning">
			            <div class="card-body">
			                <h6 class="card-title text-uppercase mb-2">Clientes</h6>
			                <h3 class="card-text fw-bold text-warning text-emphasis-warning">
			                    ${fn:length(clientList)} <span class="fs-6 text-muted">ativos</span>
			                </h3>
			            </div>
			        </div>
			    </div>
			    <div class="col-md-3">
			        <div class="card text-white bg-primary shadow-sm h-100">
			            <div class="card-body">
			                <h6 class="card-title text-uppercase mb-2">Locações</h6>
			                <h3 class="card-text fw-bold">
			                    ${fn:length(rentalList)} <span class="fs-6 text-white-50">registros</span>
			                </h3>
			            </div>
			        </div>
			    </div>
			</div>
			
			<ul class="nav nav-tabs" id="myTab" role="tablist">
			  <li class="nav-item" role="presentation">
			    <button class="nav-link ${empty activeTab or activeTab == 'cars' ? 'active' : ''}" 
			    		id="cars-tab" data-bs-toggle="tab" data-bs-target="#cars-pane" type="button" role="tab">
			    	Frota de Veículos
			    </button>
			  </li>
			  
			  <li class="nav-item" role="presentation">
			    <button class="nav-link ${activeTab == 'clients' ? 'active' : ''}" 
			    		id="clients-tab" data-bs-toggle="tab" data-bs-target="#clients-pane" type="button" role="tab">
			    	Clientes Cadastrados
			    </button>
			  </li>
			  
			  <li class="nav-item" role="presentation">
			    <button class="nav-link ${activeTab == 'rentals' ? 'active' : ''}" 
			    		id="rentals-tab" data-bs-toggle="tab" data-bs-target="#rentals-pane" type="button" role="tab">
			    	Locações Recentes
			    </button>
			  </li>
			</ul>
			
			<div class="tab-content p-3 border border-top-0 rounded-bottom shadow-sm bg-body" id="myTabContent">
			  
			  <div class="tab-pane fade ${empty activeTab or activeTab == 'cars' ? 'show active' : ''}" id="cars-pane" role="tabpanel">
			  	<div class="d-flex justify-content-between align-items-center mb-3">
			  		<h4>Veículos Disponíveis</h4>
			  		<a href="car-register.jsp" class="btn btn-primary btn-sm">+ Novo Veículo</a>
			  	</div>
			  	
			  	<form action="carSearch" method="post" class="row g-2 mb-3 align-items-end bg-body-tertiary p-2 rounded">
					<div class="col-md-4">
						<label for="modelSearch" class="form-label small">Modelo ou Marca</label>
						<input type="text" name="modelSearch" id="modelSearch" class="form-control form-control-sm" 
							placeholder="Ex: Fiat" value="${lastModelSearch}">
					</div>
					<div class="col-md-2">
						<label for="minPrice" class="form-label small">Preço Mín.</label>
						<input type="number" step="0.01" name="minPrice" id="minPrice" class="form-control form-control-sm" 
							placeholder="0.00" value="${lastMinPrice}">
					</div>
					<div class="col-md-2">
						<label for="maxPrice" class="form-label small">Preço Máx.</label>
						<input type="number" step="0.01" name="maxPrice" id="maxPrice" class="form-control form-control-sm" 
							placeholder="0.00" value="${lastMaxPrice}">
					</div>
					<div class="col-auto">
						<button type="submit" class="btn btn-primary btn-sm">Filtrar</button>
					</div>
					<div class="col-auto">
						<a href="homeServlet?tab=cars" class="btn btn-secondary btn-sm">Limpar</a>
					</div>
				</form>
			  	
			  	<c:choose>
					<c:when test="${not empty carList}">
						<div class="table-responsive">
							<table class="table table-hover align-middle">
								<thead>
									<tr>
										<th>Modelo</th>
										<th>Placa</th>
										<th>Ano</th>
										<th>Cor</th>
										<th>Diária</th>
										<th class="text-end">Ações</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="car" items="${carList}">
										<tr id="row-car-${car.id}">
											<td><strong>${car.brand}</strong> ${car.model}</td>
											<td><span class="badge bg-secondary">${car.plate}</span></td>
											<td>${car.year}</td>
											<td>${car.color}</td>
											<td class="text-success fw-bold">
												R$ <fmt:formatNumber value="${car.dailyRate}" minFractionDigits="2" maxFractionDigits="2"/>
											</td>
											<td class="text-end">
												<a href="carRegisterServlet?action=update&car-id=${car.id}" class="btn btn-outline-primary btn-sm me-1">
													<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16"><path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/><path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/></svg>
												</a>
												<a type="button" class="btn btn-outline-danger btn-sm" 
														data-bs-toggle="modal" data-bs-target="#deleteModal" 
														data-bs-id="${car.id}" data-bs-type="car">
													<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16"><path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"/><path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"/></svg>
												</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:when>
					<c:otherwise>
						<div class="alert alert-info mt-3">Nenhum veículo encontrado.</div>
					</c:otherwise>
				</c:choose>
			  </div>

			  <div class="tab-pane fade ${activeTab == 'clients' ? 'show active' : ''}" id="clients-pane" role="tabpanel">
			  	<div class="d-flex justify-content-between align-items-center mb-3">
			  		<h4>Clientes Cadastrados</h4>
			  		<a href="client-register.jsp" class="btn btn-primary btn-sm">+ Novo Cliente</a>
			  	</div>
			  	
			  	<form action="clientSearch" method="post" class="row g-2 mb-3 align-items-end bg-body-tertiary p-2 rounded">
					<div class="col-md-5">
						<label for="nameSearch" class="form-label small">Nome</label>
						<input type="text" name="nameSearch" id="nameSearch" class="form-control form-control-sm" 
							placeholder="Nome do cliente" value="${lastNameSearch}">
					</div>
					<div class="col-md-3">
						<label for="cpfSearch" class="form-label small">CPF</label>
						<input type="text" name="cpfSearch" id="cpfSearch" class="form-control form-control-sm" 
							placeholder="CPF" value="${lastCpfSearch}">
					</div>
					<div class="col-auto">
						<button type="submit" class="btn btn-primary btn-sm">Filtrar</button>
					</div>
					<div class="col-auto">
						<a href="homeServlet?tab=clients" class="btn btn-secondary btn-sm">Limpar</a>
					</div>
				</form>
			  	
			  	<c:choose>
					<c:when test="${not empty clientList}">
						<div class="table-responsive">
							<table class="table table-hover align-middle">
								<thead>
									<tr>
										<th>Nome</th>
										<th>CPF</th>
										<th>CNH</th>
										<th>Telefone</th>
										<th class="text-end">Ações</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="client" items="${clientList}">
										<tr id="row-client-${client.id}">
											<td>
												<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-person me-2" viewBox="0 0 16 16">
												  <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0m4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4m-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10s-3.516.68-4.168 1.332c-.678.678-.83 1.418-.832 1.664z"/>
												</svg>
												<strong>${client.name}</strong>
											</td>
											<td>${client.cpf}</td>
											<td>${client.cnh}</td>
											<td>${client.phoneNumber}</td>
											<td class="text-end">
												<a href="clientRegisterServlet?action=update&client-id=${client.id}" class="btn btn-outline-primary btn-sm me-1">
													<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16"><path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/><path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/></svg>
												</a>
												<a type="button" class="btn btn-outline-danger btn-sm" 
														data-bs-toggle="modal" data-bs-target="#deleteModal" 
														data-bs-id="${client.id}" data-bs-type="client">
													<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16"><path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"/><path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"/></svg>
												</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:when>
					<c:otherwise>
						<div class="alert alert-info mt-3">Nenhum cliente encontrado.</div>
					</c:otherwise>
				</c:choose>
			  </div>
			  
			  <div class="tab-pane fade ${activeTab == 'rentals' ? 'show active' : ''}" id="rentals-pane" role="tabpanel">
			  	<div class="d-flex justify-content-between align-items-center mb-3">
			  		<h4>Histórico de Locações</h4>
			  		<a href="rentalServlet" class="btn btn-success btn-sm">+ Nova Locação</a>
			  	</div>
			  	
			  	<form action="rentalSearch" method="post" class="row g-2 mb-3 align-items-end bg-body-tertiary p-2 rounded">
					<div class="col-md-4">
						<label for="filterClient" class="form-label small">Cliente</label>
						<select class="form-select form-select-sm" name="filterClient" id="filterClient">
							<option value="">Todos</option>
							<c:forEach items="${clientList}" var="c">
								<option value="${c.id}" ${c.id == selectedClient ? 'selected' : ''}>${c.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-4">
						<label for="filterCar" class="form-label small">Veículo</label>
						<select class="form-select form-select-sm" name="filterCar" id="filterCar">
							<option value="">Todos</option>
							<c:forEach items="${carList}" var="car">
								<option value="${car.id}" ${car.id == selectedCar ? 'selected' : ''}>${car.brand} ${car.model} (${car.plate})</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-auto">
						<button type="submit" class="btn btn-primary btn-sm">Filtrar</button>
					</div>
					<div class="col-auto">
						<a href="homeServlet?tab=rentals" class="btn btn-secondary btn-sm">Limpar</a>
					</div>
				</form>
			  	
			  	<c:choose>
					<c:when test="${not empty rentalList}">
						<div class="table-responsive">
							<table class="table table-hover align-middle">
								<thead>
									<tr>
										<th>Retirada</th>
										<th>Devolução</th>
										<th>Cliente</th>
										<th>Veículo</th>
										<th>Total</th>
										<th class="text-end">Ações</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="rental" items="${rentalList}">
										<tr id="row-rental-${rental.id}">
											<td>
												<fmt:parseDate value="${rental.withdrawDate}" pattern="yyyy-MM-dd" var="wDate" type="date" />
												<fmt:formatDate value="${wDate}" pattern="dd/MM/yyyy" />
											</td>
											<td>
												<fmt:parseDate value="${rental.returnDate}" pattern="yyyy-MM-dd" var="rDate" type="date" />
												<fmt:formatDate value="${rDate}" pattern="dd/MM/yyyy" />
											</td>
											<td>${rental.client.name}</td>
											<td>${rental.car.model} (${rental.car.plate})</td>
											<td class="fw-bold">
												R$ <fmt:formatNumber value="${rental.totalValue}" minFractionDigits="2" maxFractionDigits="2"/>
											</td>
											<td class="text-end">
												<a href="rentalServlet?action=update&rental-id=${rental.id}" class="btn btn-outline-primary btn-sm me-1">
													<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16"><path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/><path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/></svg>
												</a>
												<a type="button" class="btn btn-outline-danger btn-sm" 
														data-bs-toggle="modal" data-bs-target="#deleteModal" 
														data-bs-id="${rental.id}" data-bs-type="rental">
													<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16"><path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"/><path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"/></svg>
												</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:when>
					<c:otherwise>
						<div class="alert alert-info mt-3">Nenhuma locação encontrada.</div>
					</c:otherwise>
				</c:choose>
			  </div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="deleteModal" tabindex="-1">
		<div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title">Confirmar Exclusão</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        <p>Tem certeza que deseja excluir este item? Esta ação não pode ser desfeita.</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
		        <button type="button" id="confirmDeleteBtn" class="btn btn-danger">Excluir</button>
		      </div>
		    </div>
	 	</div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="js/home.js"></script>
	<script src="js/theme.js"></script>
</body>
</html>