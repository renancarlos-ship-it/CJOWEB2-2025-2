package br.edu.ifspcjo.ads.web2.LocalCenter.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import br.edu.ifspcjo.ads.web2.LocalCenter.model.Car;
import br.edu.ifspcjo.ads.web2.LocalCenter.model.Client;
import br.edu.ifspcjo.ads.web2.LocalCenter.model.RentalCar;

public class RentalCarDao {

	private DataSource dataSource;

	public RentalCarDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Boolean save(RentalCar rental) {
		String sql = "insert into rental_car (client_id, car_id, withdraw_date, return_date, total_value) values (?,?,?,?,?)";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, rental.getClient().getId());
			ps.setLong(2, rental.getCar().getId());
			ps.setDate(3, Date.valueOf(rental.getWithdrawDate()));
			ps.setDate(4, Date.valueOf(rental.getReturnDate()));
			ps.setDouble(5, rental.getTotalValue());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar locação", e);
		}
	}

	public List<RentalCar> findAll() {
		return findWithFilters(null, null);
	}
	
	
	public List<RentalCar> findWithFilters(Long clientId, Long carId) {
		StringBuilder sql = new StringBuilder(
				"SELECT r.id, r.withdraw_date, r.return_date, r.total_value, "
			  + "c.id as client_id, c.name as client_name, "
			  + "v.id as car_id, v.brand, v.model, v.plate, v.daily_rate "
			  + "FROM rental_car r "
			  + "JOIN client c ON r.client_id = c.id "
			  + "JOIN car v ON r.car_id = v.id "
			  + "WHERE 1=1 ");

		List<Object> params = new ArrayList<>();

		if (clientId != null && clientId > 0) {
			sql.append("AND r.client_id = ? ");
			params.add(clientId);
		}
		
		if (carId != null && carId > 0) {
			sql.append("AND r.car_id = ? ");
			params.add(carId);
		}
		
		sql.append("ORDER BY r.withdraw_date DESC");
		
		List<RentalCar> rentals = new ArrayList<>();
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql.toString())) {
			for (int i = 0; i < params.size(); i++) {
				ps.setObject(i + 1, params.get(i));
			}
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					rentals.add(mapResultSetToRental(rs));
				}
			}
			return rentals;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar locações", e);
		}
	}
	
	

	public Optional<RentalCar> findById(Long id) {
		String sql = "SELECT r.id, r.withdraw_date, r.return_date, r.total_value, "
				   + "c.id as client_id, c.name as client_name, "
				   + "v.id as car_id, v.brand, v.model, v.plate, v.daily_rate "
				   + "FROM rental_car r "
				   + "JOIN client c ON r.client_id = c.id "
				   + "JOIN car v ON r.car_id = v.id "
				   + "WHERE r.id = ?";
		
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return Optional.of(mapResultSetToRental(rs));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar locação", e);
		}
		return Optional.empty();
	}

	public Boolean update(RentalCar rental) {
		String sql = "UPDATE rental_car SET client_id=?, car_id=?, withdraw_date=?, return_date=?, total_value=? WHERE id=?";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, rental.getClient().getId());
			ps.setLong(2, rental.getCar().getId());
			ps.setDate(3, Date.valueOf(rental.getWithdrawDate()));
			ps.setDate(4, Date.valueOf(rental.getReturnDate()));
			ps.setDouble(5, rental.getTotalValue());
			ps.setLong(6, rental.getId());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar locação", e);
		}
	}

	public Boolean delete(Long id) {
		String sql = "DELETE FROM rental_car WHERE id=?";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao deletar locação", e);
		}
	}
	
	
	private RentalCar mapResultSetToRental(ResultSet rs) throws SQLException {
		RentalCar rental = new RentalCar();
		rental.setId(rs.getLong("id"));
		rental.setWithdrawDate(rs.getDate("withdraw_date").toLocalDate());
		rental.setReturnDate(rs.getDate("return_date").toLocalDate());
		rental.setTotalValue(rs.getDouble("total_value"));

		Client client = new Client();
		client.setId(rs.getLong("client_id"));
		client.setName(rs.getString("client_name"));
		rental.setClient(client);

		Car car = new Car();
		car.setId(rs.getLong("car_id"));
		car.setBrand(rs.getString("brand"));
		car.setModel(rs.getString("model"));
		car.setPlate(rs.getString("plate"));
		car.setDailyRate(rs.getDouble("daily_rate")); 
		rental.setCar(car);
		
		return rental;
	}
}