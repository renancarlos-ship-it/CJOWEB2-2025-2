package br.edu.ifspcjo.ads.cjoweb2.project3.controller;

import br.edu.ifspcjo.ads.cjoweb2.project3.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String message = "";

        // Verificação simples de validade
        if (email.equalsIgnoreCase("renan.carlos@aluno.ifsp.edu.br") && password.equals("1234")) {
            message = "Login válido.";
        } else {
            message = "Login inválido.";
        }

        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }
}
