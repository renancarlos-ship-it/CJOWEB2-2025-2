package br.edu.ifspcjo.ads.web2.LocalCenter.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import com.google.gson.Gson;

import br.edu.ifspcjo.ads.web2.LocalCenter.dao.ClientDao;
import br.edu.ifspcjo.ads.web2.LocalCenter.model.Client;
import br.edu.ifspcjo.ads.web2.LocalCenter.utils.DataSourceSearcher;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/clientRegisterServlet")
public class ClientRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ClientRegisterServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idParam = req.getParameter("id");
		Long id = (idParam != null && !idParam.isEmpty()) ? Long.parseLong(idParam) : 0L;

		String name = req.getParameter("name");
		String cpf = req.getParameter("cpf");
		String cnh = req.getParameter("cnh");
		String address = req.getParameter("address");
		String phoneNumber = req.getParameter("phoneNumber");
		LocalDate dateOfBirth = LocalDate.parse(req.getParameter("dateOfBirth"));

		Client client = new Client();
		client.setName(name);
		client.setCpf(cpf);
		client.setCnh(cnh);
		client.setAddress(address);
		client.setPhoneNumber(phoneNumber);
		client.setDateOfBirth(dateOfBirth);

		ClientDao clientDao = new ClientDao(DataSourceSearcher.getInstance().getDataSource());
		boolean success = false;

		if (id == 0) {
			success = clientDao.save(client);
		} else {
			client.setId(id);
			success = clientDao.update(client);
		}

		if (success) {
			req.setAttribute("result", "registered");
		} else {
			req.setAttribute("result", "error");
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("/client-register.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action == null) {
			resp.sendRedirect("homeServlet");
			return;
		}

		if (action.equals("list")) {
			resp.sendRedirect("client-register.jsp");
			return;
		}

		Long clientId = Long.parseLong(req.getParameter("client-id"));
		ClientDao clientDao = new ClientDao(DataSourceSearcher.getInstance().getDataSource());

		if (action.equals("update")) {
			Optional<Client> clientOptional = clientDao.findById(clientId);
			if (clientOptional.isPresent()) {
				req.setAttribute("client", clientOptional.get());
				RequestDispatcher dispatcher = req.getRequestDispatcher("/client-register.jsp");
				dispatcher.forward(req, resp);
			} else {
				resp.sendRedirect("homeServlet");
			}
		} 
		else if (action.equals("delete")) {
			Boolean response = clientDao.delete(clientId);
			Gson gson = new Gson();
			String json = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(json);
		}
	}
}