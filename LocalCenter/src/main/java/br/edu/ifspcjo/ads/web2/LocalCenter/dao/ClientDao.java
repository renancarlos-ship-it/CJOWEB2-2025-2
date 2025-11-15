package br.edu.ifspcjo.ads.web2.LocalCenter.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

import br.edu.ifspcjo.ads.web2.LocalCenter.model.Client;

public class ClientDao {

	private DataSource dataSource;

	public ClientDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Boolean save(Client client) {
		String sql = "insert into client (name,"+"birth_of_date,cpf,cnh,address,phone_number) values(?,?,?,?,?,?)";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, client.getName().toString());
			ps.setDate(2, Date.valueOf(client.getDateOfBirth()));
			ps.setString(3, client.getCpf().toString());
			ps.setString(4, client.getCnh());
			ps.setString(5, client.getAddress().toString());
			ps.setString(6, client.getPhoneNumber().toString());
			ps.executeUpdate();
			return true;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro ao inserir dados", sqlException);
		}
	}

	public Client findById(Long clientId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	}
