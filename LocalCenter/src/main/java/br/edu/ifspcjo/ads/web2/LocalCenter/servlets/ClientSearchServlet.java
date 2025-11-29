package br.edu.ifspcjo.ads.web2.LocalCenter.servlets;

import java.io.IOException;
import java.util.List;

import br.edu.ifspcjo.ads.web2.LocalCenter.dao.CarDao;
import br.edu.ifspcjo.ads.web2.LocalCenter.dao.ClientDao;
import br.edu.ifspcjo.ads.web2.LocalCenter.dao.RentalCarDao;
import br.edu.ifspcjo.ads.web2.LocalCenter.model.Car;
import br.edu.ifspcjo.ads.web2.LocalCenter.model.Client;
import br.edu.ifspcjo.ads.web2.LocalCenter.model.RentalCar;
import br.edu.ifspcjo.ads.web2.LocalCenter.utils.DataSourceSearcher;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/clientSearch")
public class ClientSearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ClientSearchServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nameSearch = req.getParameter("nameSearch");
		String cpfSearch = req.getParameter("cpfSearch");
		
		ClientDao clientDao = new ClientDao(DataSourceSearcher.getInstance().getDataSource());
		List<Client> clientList = clientDao.findWithFilters(nameSearch, cpfSearch);
		
		CarDao carDao = new CarDao(DataSourceSearcher.getInstance().getDataSource());
		List<Car> carList = carDao.findAll();
		
		RentalCarDao rentalDao = new RentalCarDao(DataSourceSearcher.getInstance().getDataSource());
		List<RentalCar> rentalList = rentalDao.findAll();

		req.setAttribute("clientList", clientList);
		req.setAttribute("carList", carList);
		req.setAttribute("rentalList", rentalList);
		req.setAttribute("lastNameSearch", nameSearch);
		req.setAttribute("lastCpfSearch", cpfSearch);
		req.setAttribute("activeTab", "clients");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
		dispatcher.forward(req, resp);
	}
}