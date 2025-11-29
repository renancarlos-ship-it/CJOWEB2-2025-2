<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="pt-BR" data-bs-theme="light">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LocalCenter - Cadastro de Cliente</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="navbar.jsp" />

	<div class="container mt-5">
		<div class="card shadow col-lg-8 offset-lg-2 col-sm-12">
			<div class="card-body p-4">

				<c:if test="${result == 'registered'}">
					<div class="alert alert-success alert-dismissible fade show" role="alert">
						Cliente salvo com sucesso!
						<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
				</c:if>
				<c:if test="${result == 'error'}">
					<div class="alert alert-danger alert-dismissible fade show" role="alert">
						Erro ao salvar. Verifique se CPF ou CNH já existem.
						<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
				</c:if>

				<form action="clientRegisterServlet" method="post">
					
					<h2 class="text-center mb-4">
						${client == null ? 'Novo Cliente' : 'Editar Cliente'}
					</h2>

					<input type="hidden" name="id" value="${client != null ? client.id : '0'}">

					<div class="mb-3">
						<label for="name" class="form-label">Nome Completo*</label>
						<input type="text" name="name" id="name" class="form-control" 
							required value="${client.name}">
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="cpf" class="form-label">CPF*</label>
							<input type="text" name="cpf" id="cpf" class="form-control" 
								required placeholder="000.000.000-00" value="${client.cpf}">
						</div>
						<div class="col-md-6 mb-3">
							<label for="dateOfBirth" class="form-label">Data de Nascimento*</label>
							<input type="date" name="dateOfBirth" id="dateOfBirth" class="form-control" 
								required value="${client.dateOfBirth}">
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="cnh" class="form-label">CNH*</label>
							<input type="text" name="cnh" id="cnh" class="form-control" 
								required placeholder="Número da CNH" value="${client.cnh}">
						</div>
						<div class="col-md-6 mb-3">
							<label for="phoneNumber" class="form-label">Telefone*</label>
							<input type="text" name="phoneNumber" id="phoneNumber" class="form-control" 
								required placeholder="(00) 00000-0000" value="${client.phoneNumber}">
						</div>
					</div>

					<div class="mb-3">
						<label for="address" class="form-label">Endereço Completo*</label>
						<input type="text" name="address" id="address" class="form-control" 
							required value="${client.address}">
					</div>

					<div class="d-grid gap-2 mt-4">
						<button type="submit" class="btn btn-primary btn-lg">Salvar Cliente</button>
						<a href="homeServlet" class="btn btn-secondary">Cancelar</a>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="js/theme.js"></script>
</body>
</html>