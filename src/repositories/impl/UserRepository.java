package repositories.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import repositories.IUserRepository;
import unitofwork.IUnitOfWork;
import domain.Role;
import domain.User;

public class UserRepository 
extends Repository<User> implements IUserRepository{

	public UserRepository(Connection connection, IEntityBuilder<User> builder, IUnitOfWork uow) {
		super(connection,builder, uow);
	}

	@Override
	protected String getTableName() {
		return "users";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE users SET (login,password)=(?,?) WHERE id=?";
	}

	@Override
	protected String getInsertQuery() {
		return "INSERT INTO users(login,password)"
				+ "VALUES(?,?)";
	}


	@Override
	protected void setUpInsertQuery(User entity) throws SQLException {

		insert.setString(1, entity.getLogin());
		insert.setString(2, entity.getPassword());
		
	}

	@Override
	protected void setUpUpdateQuery(User entity) throws SQLException {
		update.setString(1, entity.getLogin());
		update.setString(2, entity.getPassword());
		update.setInt(3, entity.getId());
		
	}

	@Override
	public List<User> withRole(Role role) {
		return withRole(role.getId());
	}

	@Override
	public List<User> withRole(String roleName) {
		String selectByRoleNameSql="SELECT u.* FROM USERS u,userRoles r "
				+ "WHERE r.name = ?";
		PreparedStatement selectRole;
		ResultSet rs;
		List<User> list = new ArrayList<>();
		try {
			selectRole = connection.prepareStatement(selectByRoleNameSql);
			selectRole.setString(1, roleName);
			rs = selectRole.executeQuery();
			while (rs.next()){
				User u = new User();
				u.setLogin(rs.getString("login"));
				u.setPassword(rs.getString("password"));
				u.setId(rs.getInt("id"));
				list.add(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<User> withRole(int roleId) {
		String selectByRoleNameSql="SELECT u.* FROM USERS  u,userRoles r "
				+ "WHERE r.id = ?";
		PreparedStatement selectRole;
		ResultSet rs;
		List<User> list = new ArrayList<>();
		try {
			selectRole = connection.prepareStatement(selectByRoleNameSql);
			selectRole.setInt(1, roleId);
			rs = selectRole.executeQuery();
			while (rs.next()){
				User u = new User();
				u.setLogin(rs.getString("login"));
				u.setPassword(rs.getString("password"));
				u.setId(rs.getInt("id"));
				list.add(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
