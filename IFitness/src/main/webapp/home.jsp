<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import = "java.util.List,br.edu.ifspcjo.ads.web2.ifitness.model.Activity"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="pt-BR" data-bs-theme="light">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/styles.css">
<title>iFitness - Página principal</title>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="container">
		<div class="center col-lg-10 col-sm-12">
			<div class="col-12">
				<h1 class="text-center">Listagem de Atividades</h1>
			</div>
			<form action="activitySearch" method="post">
				<div class="row">
					<div class="col-12 col-lg-3">
					  	<div class="mb-2">
							<label for="type">Tipo</label> 
							<select class="form-select"
								name="type" id="type">
								<option value="" selected>Selecione</option>
								<option value="CAMINHADA">Caminhada</option>
								<option value="CICLISMO">Ciclismo</option>
								<option value="CORRIDA">Corrida</option>
								<option value="NATACAO">Natação</option>
							</select>
						</div>
					</div>
					<div class="col-12 col-lg-3">
						<div class="mb-2">
							<label for="initial-date">Data inicial</label> 
							<input type="date" name="initial-date" id="initial-date"
								class="form-control">
						</div>
					</div>  
					<div class="col-12 col-lg-3">
						<div class="mb-2">
							<label for="final-date">Data final</label>
							<input type="date" name="final-date" id="final-date"
								class="form-control">
						</div>
					</div>
					<div class="col-12 col-lg-3 mt-4">
						<button type="submit" class="btn btn-primary">Filtrar</button>
					</div>  
				</div>
			</form>
			<c:choose>
				<c:when test="${fn:length(userActivities) > 0}">
					<table class="table table-responsive table-striped table-hover" >
						<tr>
							<th>#</th>
							<th>Tipo</th>
							<th>Data</th>
							<th>Distância</th>
							<th>Duração</th>
							<th>Ações</th>
						</tr>
						<c:forEach var="activity" items="${userActivities}" varStatus="index">
							<tr>
								<td>${index.count}</td>
								<td>
									<c:choose>
										<c:when test="${activity.type == 'CORRIDA'}">
											<jsp:include page="img/running_icon.jsp" />
										</c:when>
										<c:when test="${activity.type == 'CAMINHADA'}">
											<jsp:include page="img/walking_icon.jsp" />
										</c:when>
										<c:when test="${activity.type == 'CICLISMO'}">
											<jsp:include page="img/cycling_icon.jsp" />
										</c:when>
										<c:when test="${activity.type == 'NATACAO'}">
											<jsp:include page="img/swimming_icon.jsp" />
										</c:when>
									</c:choose>
								</td>
								<td>
									<fmt:parseDate value="${activity.date}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
									<fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="dd/MM/yyyy" />
									${newParsedDate}
								</td>
								<td>${activity.distance}</td>
								<td>${activity.duration}</td>
								<td>
									<a class="btn" data-bs-toggle="tooltip" data-bs-placement="top" title="Editar"
                						href="activityRegister?action=update&activity-id=${activity.id}">
                						<jsp:include page="img/pencil_icon.jsp" />
                					</a>
                					<span data-bs-toggle="tooltip" data-bs-placement="top" title="Excluir">
            							<a type="button" class="btn" data-bs-toggle="modal" data-bs-target="#myModal" data-bs-id="${activity.id}">
             								<jsp:include page="img/trash_icon.jsp" />
             							</a>
            						</span>

								</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<c:out value="Sem nenhuma atividade registrada."></c:out>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	
	<!-- Modal -->
	<div class="modal" tabindex="-1" id="myModal">
		<div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title">Exclusão</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        <p>Tem certeza que deseja excluir a atividade?</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
		        <button type="button" id="delete" class="btn btn-danger">Excluir</button>
		      </div>
		    </div>
	  	</div>
	</div>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript" src="js/home.js"></script>
	<script type="text/javascript" src="js/theme.js"></script>
</body>
</html>