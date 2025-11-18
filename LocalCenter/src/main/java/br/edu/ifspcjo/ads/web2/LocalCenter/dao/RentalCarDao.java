package br.edu.ifspcjo.ads.web2.LocalCenter.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import br.edu.ifspcjo.ads.web2.LocalCenter.model.Car;
import br.edu.ifspcjo.ads.web2.LocalCenter.model.Client;
import br.edu.ifspcjo.ads.web2.LocalCenter.model.RentalCar;
import br.edu.ifspcjo.ads.web2.LocalCenter.utils.DataSourceSearcher;

public class RentalCarDao {
	private DataSource dataSource;

	public RentalCarDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public Boolean save(RentalCar rentalCar) {
		String sql = "insert into rental_car (client_id,car_id,withdraw_date,return_date,daily_value,total_value) values(?,?,?,?,?,?)";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1,rentalCar.getClient().getId());
			ps.setLong(2, rentalCar.getCar().getId());
			ps.setDate(3, Date.valueOf(rentalCar.getWithdrawDate()));
			ps.setDate(4, Date.valueOf(rentalCar.getReturnDate()));
			ps.setDouble(5, rentalCar.getDailyValue());
			ps.setDouble(6, rentalCar.getTotalValue());
			ps.executeUpdate();
			return true;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro ao inserir dados", sqlException);
		}
	}

	public List<RentalCar> findAll() {
	    List<RentalCar> rentalList = new ArrayList<>();
	    String sql = "SELECT id, client_id, car_id, withdraw_date, return_date, daily_value, total_value FROM rental_car";
	    
	    try (Connection con = dataSource.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {
	    	
	    	
	    	ClientDao clientDao = new ClientDao(DataSourceSearcher.getInstance().getDataSource());
	    	CarDao carDao = new CarDao(DataSourceSearcher.getInstance().getDataSource());

	        while (rs.next()) {
	            RentalCar rental = new RentalCar();
	            
	            rental.setId(rs.getLong("id"));
	            
	           
	            Client client = clientDao.findById(rs.getLong("client_id"));
	            rental.setClient(client);
	            
	          
	            Car car = carDao.findById(rs.getLong("car_id"));
	            rental.setCar(car);

	            
	            if (rs.getDate("withdraw_date") != null) {
	                rental.setWithdrawDate(rs.getDate("withdraw_date").toLocalDate());
	            }
	            if (rs.getDate("return_date") != null) {
	                rental.setReturnDate(rs.getDate("return_date").toLocalDate());
	            }
	            
	            rental.setDailyValue(rs.getDouble("daily_value"));
	            rental.setTotalValue(rs.getDouble("total_value"));
	            
	            rentalList.add(rental);
	        }
	        return rentalList;

	    } catch (SQLException e) {
	        throw new RuntimeException("Erro ao buscar todas as reservas", e);
	    }
	}
	
	public RentalCar findById(Long id) {
	    String sql = "SELECT client_id, car_id, withdraw_date, return_date, daily_value, total_value FROM rental_car WHERE id = ?";
	    
	    try (Connection con = dataSource.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	    	
	        ps.setLong(1, id);
	    	
	    	ClientDao clientDao = new ClientDao(DataSourceSearcher.getInstance().getDataSource());
	    	CarDao carDao = new CarDao(DataSourceSearcher.getInstance().getDataSource());
	    	
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                RentalCar rental = new RentalCar();
	                rental.setId(id);
	                
	                
	                Client client = clientDao.findById(rs.getLong("client_id"));
	                rental.setClient(client);
	                
	            
	                Car car = carDao.findById(rs.getLong("car_id"));
	                rental.setCar(car);

	                if (rs.getDate("withdraw_date") != null) {
	                    rental.setWithdrawDate(rs.getDate("withdraw_date").toLocalDate());
	                }
	                if (rs.getDate("return_date") != null) {
	                    rental.setReturnDate(rs.getDate("return_date").toLocalDate());
	                }
	                
	                rental.setDailyValue(rs.getDouble("daily_value"));
	                rental.setTotalValue(rs.getDouble("total_value"));
	                
	                return rental;
	            }
	        }
	        return null; 
	        
	    } catch (SQLException e) {
	        throw new RuntimeException("Erro ao buscar reserva por ID", e);
	    }
	}
}
