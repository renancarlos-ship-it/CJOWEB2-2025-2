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

@WebServlet("/carSearch")
public class CarSearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public CarSearchServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String modelSearch = req.getParameter("modelSearch");
		String minPriceStr = req.getParameter("minPrice");
		String maxPriceStr = req.getParameter("maxPrice");
		
		Double minPrice = (minPriceStr != null && !minPriceStr.isEmpty()) ? Double.parseDouble(minPriceStr) : null;
		Double maxPrice = (maxPriceStr != null && !maxPriceStr.isEmpty()) ? Double.parseDouble(maxPriceStr) : null;
		
		CarDao carDao = new CarDao(DataSourceSearcher.getInstance().getDataSource());
		List<Car> carList = carDao.findWithFilters(modelSearch, minPrice, maxPrice);
		
		RentalCarDao rentalDao = new RentalCarDao(DataSourceSearcher.getInstance().getDataSource());
		List<RentalCar> rentalList = rentalDao.findAll();
		
		ClientDao clientDao = new ClientDao(DataSourceSearcher.getInstance().getDataSource());
		List<Client> clientList = clientDao.findAll();

		req.setAttribute("carList", carList);
		req.setAttribute("rentalList", rentalList);
		req.setAttribute("clientList", clientList);
		
		
		req.setAttribute("lastModelSearch", modelSearch);
		req.setAttribute("lastMinPrice", minPrice);
		req.setAttribute("lastMaxPrice", maxPrice);
		
		
		req.setAttribute("activeTab", "cars");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
		dispatcher.forward(req, resp);
	}
}