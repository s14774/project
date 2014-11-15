package repositories.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import domain.User;
import repositories.IRepository;

public class UserRepository implements IRepository<User>{

	private Connection connection;
	
	private String insertSql=
			"INSERT INTO users(login,password)"
			+ "VALUES(?,?)";
	
	private String selectByIDSql=
			"SELECT * FROM users WHERE id=?";
	private String updateSql=
		"UPDATE users SET (login,password)=(?,?) WHERE id=?";
	private String deleteSql=
			"DELETE FROM users WHERE id=?";
	private String selectAllSql=
			"SELECT * FROM users";
	
	private PreparedStatement selectByID;
	private PreparedStatement insert;
	private PreparedStatement delete;
	private PreparedStatement update;
	private PreparedStatement selectAll;
	
	public UserRepository(Connection connection) {
		
		this.connection = connection;
		try {
			selectByID=connection.prepareStatement(selectByIDSql);
			insert = connection.prepareStatement(insertSql);
			delete = connection.prepareStatement(deleteSql);
			update = connection.prepareStatement(updateSql);
			selectAll = connection.prepareStatement(selectAllSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void save(User entity) {

		try {
			insert.setString(1, entity.getLogin());
			insert.setString(2, entity.getPassword());
			insert.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(User entity) {

		try {
			update.setString(1, entity.getLogin());
			update.setString(2, entity.getPassword());
			update.setInt(3, entity.getId());
			update.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(User entity) {

		try {
			delete.setInt(1, entity.getId());
			delete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public User get(int id) {

		try {
			selectByID.setInt(1, id);
			ResultSet rs = selectByID.executeQuery();
			while(rs.next())
			{
				User result = new User();
				result.setId(rs.getInt("id"));
				result.setLogin(rs.getString("login"));
				result.setPassword(rs.getString("password"));
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<User> getAll() {

		List<User> result = new ArrayList<User>();
		
		try {
			ResultSet rs= selectAll.executeQuery();
			while(rs.next())
			{
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				result.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
