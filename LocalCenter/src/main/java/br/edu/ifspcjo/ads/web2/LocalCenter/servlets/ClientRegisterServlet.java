package br.edu.ifspcjo.ads.web2.LocalCenter.servlets;

import java.io.IOException;
import java.time.LocalDate;

import br.edu.ifspcjo.ads.web2.LocalCenter.dao.ClientDao;
import br.edu.ifspcjo.ads.web2.LocalCenter.model.Client;
import br.edu.ifspcjo.ads.web2.LocalCenter.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/clientRegister")
public class ClientRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ClientRegisterServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		String name = (req.getParameter("name"));
		LocalDate dateOfBirth = LocalDate.parse(req.getParameter("dateOfBirth"));
		String cpf = (req.getParameter("cpf"));
		String cnh = (req.getParameter("cnh"));
		String address = (req.getParameter("address"));
		String phoneNumber = (req.getParameter("phoneNumber"));
		
		String url;
		HttpSession session = req.getSession(false);
		if(session.getAttribute("user") == null) {
			url = "/login.jsp";
		}
		else {
			ClientDao clientDao = new ClientDao(DataSourceSearcher.getInstance().getDataSource());
			Client client = new Client();
			client.setName(name);
			client.setDateOfBirth(dateOfBirth);
			client.setCpf(cpf);
			client.setCnh(cnh);
			client.setAddress(address);
			client.setPhoneNumber(phoneNumber);
			if(id == 0) {
				if(clientDao.save(client)) {
					req.setAttribute("result", "registered");
				}
			}
			url = "/client-register.jsp";
		}
 
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
}	
