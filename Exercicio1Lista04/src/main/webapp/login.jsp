<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <form action="login" method="post">
        Email: <input type="text" name="email" /><br><br>
        Senha: <input type="password" name="password" /><br><br>
        <input type="submit" value="Entrar" />
    </form>

    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
        <p style="color: red;"><%= message %></p>
    <%
        }
    %>
</body>
</html>