package br.edu.ifspcjo.ads.web2.ifitness.servlets;
import java.io.IOException;
import java.util.List;
import br.edu.ifspcjo.ads.web2.ifitness.dao.ActivityDao;
import br.edu.ifspcjo.ads.web2.ifitness.model.Activity;
import br.edu.ifspcjo.ads.web2.ifitness.model.User;
import br.edu.ifspcjo.ads.web2.ifitness.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/homeServlet")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public HomeServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		User user = (User)session.getAttribute("user");
		ActivityDao activityDao = new ActivityDao(DataSourceSearcher.getInstance().getDataSource());
		List<Activity> userActivities = activityDao.getActivitiesByUser(user);
		req.setAttribute("userActivities", userActivities);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
		dispatcher.forward(req, resp);
		
	}
}