<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>IFitness - Estatísticas</title>
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
		rel="stylesheet">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="container">
	    <div class="row my-3">
	        <div class="col">
	            <h4>Estatísticas das Atividades Físicas Realizadas</h4>
	        </div>
	    </div>
	    <div class="row my-2">
	        <div class="col-md-6 py-1">
	            <div class="card">
	            	<h5 class="card-title">Atividades por tipo</h5>
	                <div class="card-body">
	                    <canvas id="chDonut1"></canvas>
	                </div>
	            </div>
	        </div>
	        <div class="col-md-6 py-1">
	            <div class="card">
	            	<h5 class="card-title">Distância por dia (em Km)</h5>
	                <div class="card-body">
	                    <canvas id="chBar"></canvas>
	                </div>
	            </div>
	        </div>
	    </div>
	    
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.js'></script>
	<script type="text/javascript" src="js/statistics.js"></script>
	<script type="text/javascript" src="js/theme.js"></script>
</body>
</html>