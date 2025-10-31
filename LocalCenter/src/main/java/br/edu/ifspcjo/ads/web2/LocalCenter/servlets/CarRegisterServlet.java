package br.edu.ifspcjo.ads.web2.LocalCenter.servlets;

import java.io.IOException;

import br.edu.ifspcjo.ads.web2.LocalCenter.dao.CarDao;

import br.edu.ifspcjo.ads.web2.LocalCenter.model.Car;
import br.edu.ifspcjo.ads.web2.LocalCenter.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/carRegister")
public class CarRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public CarRegisterServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		String brand = (req.getParameter("brand"));
		String model = (req.getParameter("model"));
		String color = (req.getParameter("color"));
		Integer year = Integer.parseInt(req.getParameter("year"));
		String plate = (req.getParameter("plate"));
		
		String url;
		HttpSession session = req.getSession(false);
		if(session.getAttribute("user") == null) {
			url = "/login.jsp";
		}
		else {
			CarDao carDao = new CarDao(DataSourceSearcher.getInstance().getDataSource());
			Car car = new Car();
			car.setBrand(brand);
			car.setModel(model);
			car.setColor(color);
			car.setYear(year);
			car.setPlate(plate);
			if(id == 0) {
				if(carDao.save(car)) {
					req.setAttribute("result", "registered");
				}
			}
			url = "/car-register.jsp";
		}
 
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
}	
