<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Tarefas</title>
</head>
<body>
    <h1>Insira suas tarefas</h1>
    <form action="TaskServlet" method="post">
        <textarea name="tasks" rows="10" cols="30" placeholder="Digite uma tarefa por linha..."></textarea><br><br>
        <input type="submit" value="Enviar">
    </form>
</body>
</html>