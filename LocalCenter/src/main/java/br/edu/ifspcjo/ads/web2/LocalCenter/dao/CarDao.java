package br.edu.ifspcjo.ads.web2.LocalCenter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import br.edu.ifspcjo.ads.web2.LocalCenter.model.Car;

public class CarDao {

	private DataSource dataSource;

	public CarDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Boolean save(Car car) {
		String sql = "insert into car (brand,model,color,year,plate) values(?,?,?,?,?)";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, car.getBrand().toString());
			ps.setString(2, car.getModel().toString());
			ps.setString(3, car.getColor().toString());
			ps.setInt(4, car.getYear());
			ps.setString(5, car.getPlate().toString());
			ps.executeUpdate();
			return true;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro ao inserir dados", sqlException);
		}
	}
	public List<Car> getCars() {
		String sql = "select * from car";
		List<Car> cars = new ArrayList<>();
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Car car = new Car();
					car.setId(rs.getLong(1));
					car.setBrand(rs.getString(2));
					car.setModel(rs.getString(3));
					car.setColor(rs.getString(4));
					car.setYear(rs.getInt(5));
					car.setPlate(rs.getString(6));
					cars.add(car);
				}
			}
			return cars;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro durante a consulta", sqlException);
		}
	}


	public Car findById(Long carId) {
		String sql = "SELECT id, brand, model, color, year, plate FROM car WHERE id = ?";
		try (Connection con = dataSource.getConnection(); 
			 PreparedStatement ps = con.prepareStatement(sql)) {
			
			ps.setLong(1, carId);
			
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Car car = new Car();
					car.setId(rs.getLong("id"));
					car.setBrand(rs.getString("brand"));
					car.setModel(rs.getString("model"));
					car.setColor(rs.getString("color"));
					car.setYear(rs.getInt("year"));
					car.setPlate(rs.getString("plate"));
					return car;
				}
			}
			return null; 
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro ao buscar carro por ID", sqlException);
		}
	}
	public List<Car> findAll() {
		String sql = "select id, brand, model, color, year, plate from car";
		List<Car> cars = new ArrayList<>();
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Car car = new Car();
					car.setId(rs.getLong(1));
					car.setBrand(rs.getString(2));
					car.setModel(rs.getString(3));
					car.setColor(rs.getString(4));
					car.setYear(rs.getInt(5));
					car.setPlate(rs.getString(6));
					cars.add(car);
				}
			}
			return cars;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro durante a consulta", sqlException);
		}
	}
}