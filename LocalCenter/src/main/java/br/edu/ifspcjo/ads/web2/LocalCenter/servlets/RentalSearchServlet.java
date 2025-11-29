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

@WebServlet("/rentalSearch")
public class RentalSearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public RentalSearchServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String clientIdStr = req.getParameter("filterClient");
		String carIdStr = req.getParameter("filterCar");
		
		Long clientId = (clientIdStr != null && !clientIdStr.isEmpty()) ? Long.parseLong(clientIdStr) : null;
		Long carId = (carIdStr != null && !carIdStr.isEmpty()) ? Long.parseLong(carIdStr) : null;
		
		RentalCarDao rentalDao = new RentalCarDao(DataSourceSearcher.getInstance().getDataSource());
		List<RentalCar> rentalList = rentalDao.findWithFilters(clientId, carId);
		
		CarDao carDao = new CarDao(DataSourceSearcher.getInstance().getDataSource());
		List<Car> carList = carDao.findAll();
		
		ClientDao clientDao = new ClientDao(DataSourceSearcher.getInstance().getDataSource());
		List<Client> clientList = clientDao.findAll();

		req.setAttribute("rentalList", rentalList);
		req.setAttribute("carList", carList);
		req.setAttribute("clientList", clientList);
		
		req.setAttribute("selectedClient", clientId);
		req.setAttribute("selectedCar", carId);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
		req.setAttribute("activeTab", "rentals");
		dispatcher.forward(req, resp);
	}
}