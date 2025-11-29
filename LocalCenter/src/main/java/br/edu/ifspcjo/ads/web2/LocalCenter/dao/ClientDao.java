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

import br.edu.ifspcjo.ads.web2.LocalCenter.model.Client;

public class ClientDao {

	private DataSource dataSource;

	public ClientDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Boolean save(Client client) {
		String sql = "insert into client (name, birth_date, cpf, cnh, address, phone_number) values (?,?,?,?,?,?)";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, client.getName());
			ps.setDate(2, Date.valueOf(client.getDateOfBirth()));
			ps.setString(3, client.getCpf());
			ps.setString(4, client.getCnh());
			ps.setString(5, client.getAddress());
			ps.setString(6, client.getPhoneNumber());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar cliente", e);
		}
	}

	public List<Client> findAll() {
		return findWithFilters(null, null);
	}
	
	public List<Client> findWithFilters(String name, String cpf) {
		StringBuilder sql = new StringBuilder("SELECT * FROM client WHERE 1=1 ");
		List<Object> params = new ArrayList<>();

		if (name != null && !name.trim().isEmpty()) {
			sql.append("AND name LIKE ? ");
			params.add("%" + name + "%");
		}
		
		if (cpf != null && !cpf.trim().isEmpty()) {
			sql.append("AND cpf LIKE ? ");
			params.add("%" + cpf + "%");
		}
		
		sql.append("ORDER BY name ASC");

		List<Client> clients = new ArrayList<>();
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql.toString())) {
			
			for (int i = 0; i < params.size(); i++) {
				ps.setObject(i + 1, params.get(i));
			}
			
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Client client = new Client();
					client.setId(rs.getLong("id"));
					client.setName(rs.getString("name"));
					client.setDateOfBirth(rs.getDate("birth_date").toLocalDate());
					client.setCpf(rs.getString("cpf"));
					client.setCnh(rs.getString("cnh"));
					client.setAddress(rs.getString("address"));
					client.setPhoneNumber(rs.getString("phone_number"));
					clients.add(client);
				}
			}
			return clients;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao filtrar clientes", e);
		}
	}
	
	public Optional<Client> findById(Long id) {
		String sql = "select * from client where id=?";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Client client = new Client();
					client.setId(rs.getLong("id"));
					client.setName(rs.getString("name"));
					client.setDateOfBirth(rs.getDate("birth_date").toLocalDate());
					client.setCpf(rs.getString("cpf"));
					client.setCnh(rs.getString("cnh"));
					client.setAddress(rs.getString("address"));
					client.setPhoneNumber(rs.getString("phone_number"));
					return Optional.of(client);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar cliente", e);
		}
		return Optional.empty();
	}

	public Boolean update(Client client) {
		String sql = "update client set name=?, birth_date=?, cpf=?, cnh=?, address=?, phone_number=? where id=?";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, client.getName());
			ps.setDate(2, Date.valueOf(client.getDateOfBirth()));
			ps.setString(3, client.getCpf());
			ps.setString(4, client.getCnh());
			ps.setString(5, client.getAddress());
			ps.setString(6, client.getPhoneNumber());
			ps.setLong(7, client.getId());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar cliente", e);
		}
	}
	
	public Boolean delete(Long id) {
		String sql = "delete from client where id=?";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao deletar cliente", e);
		}
	}
}