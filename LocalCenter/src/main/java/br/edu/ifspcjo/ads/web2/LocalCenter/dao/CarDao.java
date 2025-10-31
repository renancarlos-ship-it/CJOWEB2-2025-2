package br.edu.ifspcjo.ads.web2.LocalCenter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}