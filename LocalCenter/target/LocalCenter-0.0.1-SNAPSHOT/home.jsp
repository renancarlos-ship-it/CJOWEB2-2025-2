<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import = "java.util.List,br.edu.ifspcjo.ads.web2.LocalCenter.model.Car"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/home.css">
<title>LocalCenter - Página principal</title>
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
	          <a class="nav-link" href="car-register.jsp">Carro</a>
	        </li>
	        <li class="nav-item dropdown">
	          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	            ${name}
	          </a>
	          <ul class="dropdown-menu">
	            <li><a class="dropdown-item" href="#">Minha Conta</a></li>
	            <li><hr class="dropdown-divider"></li>
	            <li><a class="dropdown-item" href="#">Sair</a></li>
	          </ul>
	        </li>
	      </ul>
	      <form class="d-flex">
	        <input class="form-control me-2" type="search" placeholder="Pesquisar">
	        <button class="btn btn-outline-success" type="submit">Pesquisar</button>
	      </form>
	    </div>
	  </div>
	</nav>
	<div class="container">
		<div class="center col-lg-10 col-sm-12">
			<div class="col-12">
				<h1 class="text-center">Listagem de Carros</h1>
			</div>
			<c:choose>
				<c:when test="${fn:length(cars) > 0}">
					<table class="table table-responsive table-striped table-hover" >
						<tr>
							<th>#</th>
							<th>Marca</th>
							<th>Modelo</th>
							<th>Cor</th>
							<th>Ano</th>
							<th>Placa</th>
						</tr>
						<c:forEach var="car" items="${cars}" varStatus="index">
							<tr>
								<td>${index.count}</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<c:out value="Sem nenhum carro cadastrado."></c:out>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
