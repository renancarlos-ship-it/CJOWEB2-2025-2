package br.edu.ifspcjo.ads.web2.LocalCenter.servlets;

import java.io.IOException;
import java.util.Optional;

import br.edu.ifspcjo.ads.web2.LocalCenter.dao.UserDao;
import br.edu.ifspcjo.ads.web2.LocalCenter.model.User;
import br.edu.ifspcjo.ads.web2.LocalCenter.utils.DataSourceSearcher;
import br.edu.ifspcjo.ads.web2.LocalCenter.utils.PasswordEncoder;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public LoginServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		UserDao userDao = new UserDao(DataSourceSearcher.getInstance().getDataSource());
		Optional<User> optional = userDao.getUserByEmailAndPassword(email, PasswordEncoder.encode(password));
		String url;
		if(optional.isPresent()) {
			User user = optional.get();
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(100);
			session.setAttribute("user", user);
			url = "/home.jsp";
		}else {
			req.setAttribute("result", "loginError");
			url = "/login.jsp";
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}

}
