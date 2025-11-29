package br.edu.ifspcjo.ads.web2.LocalCenter.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;

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

@WebServlet("/rentalServlet")
public class RentalServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public RentalServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		CarDao carDao = new CarDao(DataSourceSearcher.getInstance().getDataSource());
		ClientDao clientDao = new ClientDao(DataSourceSearcher.getInstance().getDataSource());
		RentalCarDao rentalDao = new RentalCarDao(DataSourceSearcher.getInstance().getDataSource());

		if (action == null || action.equals("new") || action.equals("update")) {
			List<Car> cars = carDao.findAll();
			List<Client> clients = clientDao.findAll();
			req.setAttribute("cars", cars);
			req.setAttribute("clients", clients);
		}

		if (action != null && action.equals("update")) {
			Long id = Long.parseLong(req.getParameter("rental-id"));
			Optional<RentalCar> rental = rentalDao.findById(id);
			
			if (rental.isPresent()) {
				req.setAttribute("rental", rental.get());
				RequestDispatcher dispatcher = req.getRequestDispatcher("/rental.jsp");
				dispatcher.forward(req, resp);
				return;
			}
		}
		
		else if (action != null && action.equals("delete")) {
			Long id = Long.parseLong(req.getParameter("rental-id"));
			Boolean response = rentalDao.delete(id);
			
			Gson gson = new Gson();
			String json = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(json);
			return;
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("/rental.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String idParam = req.getParameter("id");
			Long id = (idParam != null && !idParam.isEmpty()) ? Long.parseLong(idParam) : 0L;

			Long clientId = Long.parseLong(req.getParameter("clientId"));
			Long carId = Long.parseLong(req.getParameter("carId"));
			LocalDate withdrawDate = LocalDate.parse(req.getParameter("withdrawDate"));
			LocalDate returnDate = LocalDate.parse(req.getParameter("returnDate"));
			
			CarDao carDao = new CarDao(DataSourceSearcher.getInstance().getDataSource());
			Car car = carDao.findById(carId).orElseThrow(() -> new RuntimeException("Carro não encontrado"));
			
			long days = ChronoUnit.DAYS.between(withdrawDate, returnDate);
			if (days < 1) days = 1;
			Double totalValue = days * car.getDailyRate();
			
			RentalCar rental = new RentalCar();
			if (id > 0) rental.setId(id); 
			
			Client client = new Client();
			client.setId(clientId);
			rental.setClient(client);
			rental.setCar(car);
			rental.setWithdrawDate(withdrawDate);
			rental.setReturnDate(returnDate);
			rental.setTotalValue(totalValue);
			
			RentalCarDao rentalDao = new RentalCarDao(DataSourceSearcher.getInstance().getDataSource());
			
			if (id == 0) {
				rentalDao.save(rental);
			} else {
				rentalDao.update(rental);
			}
			
			req.setAttribute("result", "success");
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "error");
		}
		
		doGet(req, resp);
	}
}