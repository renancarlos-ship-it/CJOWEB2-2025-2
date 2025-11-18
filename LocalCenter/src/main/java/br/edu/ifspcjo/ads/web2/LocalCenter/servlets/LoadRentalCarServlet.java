package br.edu.ifspcjo.ads.web2.LocalCenter.servlets;

import br.edu.ifspcjo.ads.web2.LocalCenter.dao.ClientDao;
import br.edu.ifspcjo.ads.web2.LocalCenter.dao.CarDao; 
import br.edu.ifspcjo.ads.web2.LocalCenter.utils.DataSourceSearcher;
import br.edu.ifspcjo.ads.web2.LocalCenter.model.Car;
import br.edu.ifspcjo.ads.web2.LocalCenter.model.Client;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@WebServlet("/rentalCarForm")
public class LoadRentalCarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        if (req.getSession(false) == null || req.getSession(false).getAttribute("user") == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        try {
            ClientDao clientDao = new ClientDao(DataSourceSearcher.getInstance().getDataSource());
            CarDao carDao = new CarDao(DataSourceSearcher.getInstance().getDataSource());

            List<Client> clients = clientDao.findAll(); 
            List<Car> availableCars = carDao.findAll(); 

            req.setAttribute("clients", clients);
            req.setAttribute("availableCars", availableCars);
            
            req.getRequestDispatcher("/rental-car.jsp").forward(req, resp);
            
        } catch (Exception e) {
            req.setAttribute("error", "Erro ao carregar dados para a reserva: " + e.getMessage());
            req.getRequestDispatcher("/erro.jsp").forward(req, resp);
        }
    }
}
