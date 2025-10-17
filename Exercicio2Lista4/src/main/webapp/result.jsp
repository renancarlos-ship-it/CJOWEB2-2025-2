<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="br.edu.ifspcjo.ads.todo.model.Task" %>
<html>
<head>
    <title>Lista de Tarefas</title>
</head>
<body>
    <h1>Tarefas Inseridas:</h1>
    <ul>
        <%
            List<Task> tasks = (List<Task>) request.getAttribute("taskList");
            if (tasks != null && !tasks.isEmpty()) {
                for (Task t : tasks) {
        %>
                    <li><%= t.getId() %></li>
                    <li><%= t.getDescription() %></li>
                    <li><%= t.getDate() %></li>
        <%
                }
            } else {
        %>
                <li>Nenhuma tarefa inserida.</li>
        <%
            }
        %>
    </ul>
</body>
</html>