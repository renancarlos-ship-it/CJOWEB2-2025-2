package br.edu.ifspcjo.ads.web2.LocalCenter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import br.edu.ifspcjo.ads.web2.LocalCenter.model.Car;

public class CarDao {

	private DataSource dataSource;

	public CarDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Boolean save(Car car) {
		String sql = "insert into car (brand, model, color, year, plate, daily_rate) values (?,?,?,?,?,?)";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, car.getBrand());
			ps.setString(2, car.getModel());
			ps.setString(3, car.getColor());
			ps.setInt(4, car.getYear());
			ps.setString(5, car.getPlate());
			ps.setDouble(6, car.getDailyRate());
			ps.executeUpdate();
			return true;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro ao inserir carro", sqlException);
		}
	}

	public List<Car> findAll() {
		
		return findWithFilters(null, null, null);
	}
	
	
	public List<Car> findWithFilters(String modelSearch, Double minPrice, Double maxPrice) {
		StringBuilder sql = new StringBuilder("select * from car where 1=1 ");
		List<Object> params = new ArrayList<>();

		
		if (modelSearch != null && !modelSearch.trim().isEmpty()) {
			sql.append("AND (model LIKE ? OR brand LIKE ?) ");
			params.add("%" + modelSearch + "%");
			params.add("%" + modelSearch + "%");
		}
		
		
		if (minPrice != null) {
			sql.append("AND daily_rate >= ? ");
			params.add(minPrice);
		}
		
		
		if (maxPrice != null) {
			sql.append("AND daily_rate <= ? ");
			params.add(maxPrice);
		}
		
		sql.append("ORDER BY daily_rate ASC"); 

		List<Car> cars = new ArrayList<>();
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql.toString())) {
			
			for (int i = 0; i < params.size(); i++) {
				ps.setObject(i + 1, params.get(i));
			}
			
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Car car = new Car();
					car.setId(rs.getLong("id"));
					car.setBrand(rs.getString("brand"));
					car.setModel(rs.getString("model"));
					car.setColor(rs.getString("color"));
					car.setYear(rs.getInt("year"));
					car.setPlate(rs.getString("plate"));
					car.setDailyRate(rs.getDouble("daily_rate"));
					cars.add(car);
				}
			}
			return cars;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro ao filtrar carros", sqlException);
		}
	}
	
	public Optional<Car> findById(Long id) {
		String sql = "select * from car where id=?";
		Optional<Car> optional = Optional.empty();
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Car car = new Car();
					car.setId(rs.getLong("id"));
					car.setBrand(rs.getString("brand"));
					car.setModel(rs.getString("model"));
					car.setColor(rs.getString("color"));
					car.setYear(rs.getInt("year"));
					car.setPlate(rs.getString("plate"));
					car.setDailyRate(rs.getDouble("daily_rate"));
					optional = Optional.of(car);
				}
			}
			return optional;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro ao buscar carro por ID", sqlException);
		}
	}
	
	public Boolean update(Car car) {
		String sql = "update car set brand=?, model=?, color=?, year=?, plate=?, daily_rate=? where id=?";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, car.getBrand());
			ps.setString(2, car.getModel());
			ps.setString(3, car.getColor());
			ps.setInt(4, car.getYear());
			ps.setString(5, car.getPlate());
			ps.setDouble(6, car.getDailyRate());
			ps.setLong(7, car.getId());
			ps.executeUpdate();
			return true;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro ao atualizar carro", sqlException);
		}
	}
	
	public Boolean delete(Long id) {
		String sql = "delete from car where id=?";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.executeUpdate();
			return true;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro ao remover carro", sqlException);
		}
	}
}