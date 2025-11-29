package br.edu.ifspcjo.ads.web2.LocalCenter.servlets;

import java.io.IOException;
import java.util.Optional;

import com.google.gson.Gson;


import br.edu.ifspcjo.ads.web2.LocalCenter.dao.CarDao;
import br.edu.ifspcjo.ads.web2.LocalCenter.model.Car;
import br.edu.ifspcjo.ads.web2.LocalCenter.utils.DataSourceSearcher;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/carRegisterServlet")
public class CarRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public CarRegisterServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String idParam = req.getParameter("id");
		Long id = (idParam != null && !idParam.isEmpty()) ? Long.parseLong(idParam) : 0L;
		
		String brand = req.getParameter("brand");
		String model = req.getParameter("model");
		String color = req.getParameter("color");
		String plate = req.getParameter("plate");
		
		
		Integer year = Integer.parseInt(req.getParameter("year"));
		Double dailyRate = Double.parseDouble(req.getParameter("dailyRate"));

		
		CarDao carDao = new CarDao(DataSourceSearcher.getInstance().getDataSource());
		Car car = new Car();
		
		car.setBrand(brand);
		car.setModel(model);
		car.setColor(color);
		car.setPlate(plate);
		car.setYear(year);
		car.setDailyRate(dailyRate);

		
		boolean success = false;
		
		if (id == 0) {
			
			success = carDao.save(car);
		} else {
			
			car.setId(id);
			success = carDao.update(car);
		}
		
		if (success) {
			req.setAttribute("result", "registered");
		} else {
			req.setAttribute("result", "error");
		}
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/car-register.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		
		if (action == null) {
			resp.sendRedirect("homeServlet");
			return;
		}

		
		CarDao carDao = new CarDao(DataSourceSearcher.getInstance().getDataSource());
		String url = null;
		
		if (action.equals("list")) {
		    RequestDispatcher dispatcher = req.getRequestDispatcher("/car-register.jsp");
		    dispatcher.forward(req, resp);
		    return;
		}
		
		
		if (action.equals("update")) {
			
			Long carId = Long.parseLong(req.getParameter("car-id"));
			
			Optional<Car> carOptional = carDao.findById(carId);
			if (carOptional.isPresent()) {
				req.setAttribute("car", carOptional.get());
				url = "/car-register.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, resp);
			} else {
				
				resp.sendRedirect("homeServlet");
			}
		}
		
		
		else if (action.equals("delete")) {
			
			Long carId = Long.parseLong(req.getParameter("car-id"));
			
			
			Boolean response = carDao.delete(carId);
			
			
			Gson gson = new Gson();
			String json = gson.toJson(response);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(json);
		}
	}
}