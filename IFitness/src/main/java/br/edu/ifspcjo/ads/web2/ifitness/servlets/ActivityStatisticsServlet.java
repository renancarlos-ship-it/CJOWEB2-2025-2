package br.edu.ifspcjo.ads.web2.ifitness.servlets;

import java.io.IOException;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.edu.ifspcjo.ads.web2.ifitness.dao.ActivityDao;
import br.edu.ifspcjo.ads.web2.ifitness.model.User;
import br.edu.ifspcjo.ads.web2.ifitness.utils.DataSourceSearcher;
import br.edu.ifspcjo.ads.web2.ifitness.utils.LocalDateTypeAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/activityStatistics")
public class ActivityStatisticsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String category = req.getParameter("category");

		HttpSession session = req.getSession(false);
		User user = (User) session.getAttribute("user");
		ActivityDao activityDao = new ActivityDao(DataSourceSearcher.getInstance().getDataSource());
		resp.setContentType("application/json");
		switch (category) {
		case "byType":
			resp.getWriter().write(new Gson().toJson(activityDao.getActivitiesStatisticsByType(user)));
			break;
		case "byDay":
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create();
			resp.setContentType("application/json");
			resp.getWriter().write(gson.toJson(activityDao.getActivitiesStatisticsByDay(user)));
		}
	}

}