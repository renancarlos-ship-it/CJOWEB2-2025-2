package br.edu.ifspcjo.ads.web2.LocalCenter.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	public Client findById(Long id) {
		String sql = "SELECT id, name, birth_of_date, cpf, cnh, address, phone_number, email, gender FROM client WHERE id = ?";
	    
	    try (Connection con = dataSource.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        
	        ps.setLong(1, id);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                Client client = new Client();
	                client.setId(rs.getLong("id"));
	                client.setName(rs.getString("name"));
	                client.setDateOfBirth(rs.getDate("birth_of_date").toLocalDate());
	                client.setCpf(rs.getString("cpf"));
	                client.setCnh(rs.getString("cnh"));
	                client.setAddress(rs.getString("address"));
	                client.setPhoneNumber(rs.getString("phone_number"));
	                return client;
	            }
	        }
	        return null;
	    } catch (SQLException e) {
	        throw new RuntimeException("Erro ao buscar cliente por ID", e);
	    }
	}
	
	public List<Client> findAll() {
	    List<Client> clientList = new ArrayList<>();
	    String sql = "SELECT id, name, birth_of_date, cpf, cnh, address, phone_number FROM client";
	    
	    try (Connection con = dataSource.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {
	        
	        while (rs.next()) {
	            Client client = new Client();
	            client.setId(rs.getLong("id"));
	            client.setName(rs.getString("name"));
	            client.setDateOfBirth(rs.getDate("birth_of_date").toLocalDate());
	            client.setCpf(rs.getString("cpf"));
	            client.setCnh(rs.getString("cnh"));
	            client.setAddress(rs.getString("address"));
	            client.setPhoneNumber(rs.getString("phone_number"));
	            clientList.add(client);
	        }
	        return clientList;
	    } catch (SQLException e) {
	        throw new RuntimeException("Erro ao buscar todos os clientes", e);
	    }
	}
}
