package br.edu.ifspcjo.ads.web2.LocalCenter.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import br.edu.ifspcjo.ads.web2.LocalCenter.model.RentalCar;

public class RentalCarDao {
	private DataSource dataSource;

	public RentalCarDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public Boolean save(RentalCar rentalCar) {
		String sql = "insert into rentalCar (client_id,car_id,withdraw_date,return_date,daily_value,total_value) values(?,?,?,?,?,?)";
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

}
