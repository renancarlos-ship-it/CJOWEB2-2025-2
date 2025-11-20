package br.edu.ifspcjo.ads.web2.ifitness.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import br.edu.ifspcjo.ads.web2.ifitness.dao.ActivityDao;
import br.edu.ifspcjo.ads.web2.ifitness.dao.filters.ActivityFilter;
import br.edu.ifspcjo.ads.web2.ifitness.model.Activity;
import br.edu.ifspcjo.ads.web2.ifitness.model.ActivityType;
import br.edu.ifspcjo.ads.web2.ifitness.model.User;
import br.edu.ifspcjo.ads.web2.ifitness.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/activitySearch")
public class ActivitySearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ActivitySearchServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		ActivityType activityType = null;
		if(!type.isEmpty()) {
			activityType = ActivityType.valueOf(type);
		}
		String date = req.getParameter("initial-date");
		LocalDate initialDate = null;
		if(!date.isEmpty()) {
			initialDate = LocalDate.parse(date);
		}
		date = req.getParameter("final-date");
		LocalDate finalDate = null;
		if(!date.isEmpty()) {
			finalDate = LocalDate.parse(date);
		}
		
		HttpSession session = req.getSession(false);
		User user = (User)session.getAttribute("user");
		
		ActivityFilter filter = new ActivityFilter();
		filter.setUser(user);
		filter.setType(activityType);
		filter.setInitialDate(initialDate);
		filter.setFinalDate(finalDate);
		ActivityDao activityDao = new ActivityDao(DataSourceSearcher.getInstance().getDataSource());
		List<Activity> userActivities = null;
		try {
			userActivities = activityDao.getActivitiesByFilter(filter);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("userActivities", userActivities);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
		dispatcher.forward(req, resp);
	}
	
}