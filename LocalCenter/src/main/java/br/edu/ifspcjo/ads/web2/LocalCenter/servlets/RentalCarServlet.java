package br.edu.ifspcjo.ads.web2.LocalCenter.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
import jakarta.servlet.http.HttpSession;

@WebServlet("/rentalCar")
public class RentalCarServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public RentalCarServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		Long clientId = Long.parseLong(req.getParameter("clientId"));
		Long carId = Long.parseLong(req.getParameter("carId"));
		LocalDate withdrawDate = LocalDate.parse(req.getParameter("withdrawDate"));
		LocalDate returnDate = LocalDate.parse(req.getParameter("returnDate"));
		String dailyRateStr = req.getParameter("dailyRateValue");
		
		
		String url;
		HttpSession session = req.getSession(false);
		if(session.getAttribute("user") == null) {
			url = "/login.jsp";
		}
	
		else {
			ClientDao clientDao = new ClientDao(DataSourceSearcher.getInstance().getDataSource());
			CarDao carDao = new CarDao(DataSourceSearcher.getInstance().getDataSource());
			RentalCarDao rentalCarDao = new RentalCarDao(DataSourceSearcher.getInstance().getDataSource());
			Client client = clientDao.findById(clientId);
			Car car = carDao.findById(carId);
			Double dailyRate = Double.parseDouble(dailyRateStr);
			
			long days = ChronoUnit.DAYS.between(withdrawDate,returnDate);
			if(days < 1)days = 1;
			
			Double totalValue = dailyRate * days;
			
			RentalCar rentalCar = new RentalCar();
			rentalCar.setClient(client);
			rentalCar.setCar(car);
			rentalCar.setWithdrawDate(withdrawDate);
			rentalCar.setReturnDate(returnDate);
			rentalCar.setReturnDate(returnDate);
			rentalCar.setDailyValue(dailyRate);
			rentalCar.setTotalValue(totalValue);
			
			if(id == 0) {
				if(rentalCarDao.save(rentalCar)) {
					req.setAttribute("result", "registered");
				}
			}
			url = "/rental-car.jsp";
		}
 
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
		}
		

