package br.edu.ifspcjo.ads.web2.Atividade03;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// configurar a codificação de caracteres
		req.setCharacterEncoding("UTF-8");
		// obter os dados
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String[] options = req.getParameterValues("options");
		String selectedOptions = "";
		//if(options != null) {
			for(String option : options) {
				selectedOptions += option + " ";
				
			}
		//}
		// gerar resposta
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.println("<!DOCTYPE html>");
		writer.println("<html lang=\"pt-BR\">");
		writer.println("<head>");
		writer.println("\t<meta charset=\"UTF-8\">");
		writer.println("\t<title>Página Principal</title>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("\t<h1>Cadastro realizado com sucesso!</h1>");
		writer.println("\t<h2>Nome: " + name + "</h2>");
		writer.println("\t<h2>E-mail: " + email + "</h2>");
		writer.println("\t<h2>E-mail: " + email + "</h2>");
		writer.println("\t<h2>Interesses: " + selectedOptions + "</h2>");
		writer.println("</body>");
		writer.println("</html>");
		writer.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
