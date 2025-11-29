<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!doctype html>
<html lang="pt-BR" data-bs-theme="light">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/styles.css" rel="stylesheet"> 

<title>LocalCenter - Cadastro de Funcionário</title>
</head>
<body>
	<div class="container">
		<div class="col-lg-6 offset-lg-3 col-sm-12 mt-5">
			
			<c:if test="${result == 'notRegistered'}">
				<div class="alert alert-danger alert-dismissible fade show"
					role="alert">
					E-mail já cadastrado. Tente novamente.
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
			</c:if>
			
			<div class="card shadow">
				<div class="card-body">
					<form action="userRegisterServlet" method="post" id="form1">
						<h2 class="text-center mb-4">Novo Funcionário</h2>

						<div class="mb-3">
							<label for="name" class="form-label">Nome completo*</label> 
							<input type="text" name="name" id="name" class="form-control" 
								minlength="3" maxlength="50" required="required"> 
							<span id="0" class="text-danger"></span>
						</div>

						<div class="mb-3">
							<label for="email" class="form-label">E-mail*</label> 
							<input type="email" name="email" id="email" class="form-control" required="required"> 
							<span id="1" class="text-danger"></span>
						</div>

						<div class="mb-3">
							<label for="password" class="form-label">Senha*</label> 
							<input type="password" name="password" id="password" class="form-control" 
								minlength="6" maxlength="12" required="required"> 
							<span id="2" class="text-danger"></span>
						</div>

						<div class="mb-3">
							<label for="confirmPassword" class="form-label">Confirmação de Senha*</label> 
							<input type="password" name="confirmPassword" id="confirmPassword"
								class="form-control" minlength="6" maxlength="12" required="required"> 
							<span id="3" class="text-danger"></span>
						</div>

						<div class="mb-3">
							<label for="dateOfBirth" class="form-label">Data de Nascimento*</label> 
							<input type="date" name="dateOfBirth" id="dateOfBirth"
								class="form-control" max="2007-12-31" required="required">
							<span id="4" class="text-danger"></span>
						</div>

						<div class="mb-3">
							<label for="gender" class="form-label">Gênero*</label> 
							<select class="form-select" name="gender" id="gender" required="required">
								<option value="" selected>Selecione</option>
								<option value="MASCULINO">Masculino</option>
								<option value="FEMININO">Feminino</option>
								<option value="OUTRO">Outro</option>
								<option value="PREFIRO_NAO_DIZER">Prefiro não dizer</option>
							</select> 
							<span id="5" class="text-danger"></span>
						</div>

						<div class="d-grid gap-2">
							<button type="submit" class="btn btn-primary">Cadastrar Funcionário</button>
						</div>
					</form>
				</div>
			</div>
			
		</div>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="js/user-register.js"></script>
</body>
</html>