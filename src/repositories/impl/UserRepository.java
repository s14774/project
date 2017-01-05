package repositories.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import repositories.IUserRepository;
import unitofwork.IUnitOfWork;
import domain.Person;
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
		return "UPDATE users SET (login,password,roleId,personId)=(?,?,?,?) WHERE id=?";
	}

	@Override
	protected String getInsertQuery() {
		return "INSERT INTO users(login,password,roleId,personId)"
				+ "VALUES(?,?,?,?)";
	}


	@Override
	protected void setUpInsertQuery(User entity) throws SQLException {

		insert.setString(1, entity.getLogin());
		insert.setString(2, entity.getPassword());
		if(entity.getRole() == null)
			insert.setNull(3, Types.INTEGER);
		else
			insert.setInt(3, entity.getRole().getId());
		if(entity.getPerson() == null)
			insert.setNull(4, Types.INTEGER);
		else
			insert.setInt(4, entity.getPerson().getId());
	}

	@Override
	protected void setUpUpdateQuery(User entity) throws SQLException {
		update.setString(1, entity.getLogin());
		update.setString(2, entity.getPassword());
		if(entity.getRole() == null)
			update.setNull(3, Types.INTEGER);
		else
			update.setInt(3, entity.getRole().getId());
		if(entity.getPerson() == null)
			update.setNull(4, Types.INTEGER);
		else
			update.setInt(4, entity.getPerson().getId());
		update.setInt(5, entity.getId());
		
	}

	@Override
	public List<User> withRole(Role role) {
		return withRole(role.getId());
	}

	@Override
	public List<User> withRole(String roleName) {
		String selectByRoleNameSql="SELECT "
				+ "u.*,"
				+ "r.id as roleId,"
				+ "r.name as roleName "
				+ "FROM users u,userRoles r "
				+ "WHERE r.name = ?";
		PreparedStatement selectRole;
		
		String selectPersonById="SELECT *"
				+ "FROM person p "
				+ "WHERE p.id = ?";
		PreparedStatement selectPerson;

		ResultSet rs,rsPerson;
		List<User> list = new ArrayList<>();
		try {
			selectRole = connection.prepareStatement(selectByRoleNameSql);
			selectRole.setString(1, roleName);
			rs = selectRole.executeQuery();
			int personId;
			while (rs.next()){
				User u = new User();
				Role r = new Role();
				u.setLogin(rs.getString("login"));
				u.setPassword(rs.getString("password"));
				u.setId(rs.getInt("id"));
				r.setId(rs.getInt("roleId"));
				r.setName(rs.getString("roleName"));
				u.setRole(r);
				
				personId = rs.getInt("personId");
				if(rs.wasNull() == false){
					selectPerson = connection.prepareStatement(selectPersonById);
					selectPerson.setInt(1, personId);
					rsPerson = selectPerson.executeQuery();
					if(rsPerson.next()){
						Person p = new Person();
						p.setFirstName(rsPerson.getString("firstname"));
						p.setSurname(rsPerson.getString("surname"));
						p.setAddress(rsPerson.getString("address"));
						p.setPesel(rsPerson.getString("pesel"));
						u.setPerson(p);
					}
				}
				
				list.add(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<User> withRole(int roleId) {
		String selectByRoleNameSql="SELECT "
				+ "u.*,"
				+ "r.id as roleId,"
				+ "r.name as roleName "
				+ "FROM users u,userRoles r "
				+ "WHERE r.id = ?";
		PreparedStatement selectRole;
		
		String selectPersonById="SELECT *"
				+ "FROM person p "
				+ "WHERE p.id = ?";
		PreparedStatement selectPerson;

		ResultSet rs,rsPerson;
		List<User> list = new ArrayList<>();
		try {
			selectRole = connection.prepareStatement(selectByRoleNameSql);
			selectRole.setInt(1, roleId);
			rs = selectRole.executeQuery();
			int personId;
			while (rs.next()){
				User u = new User();
				Role r = new Role();
				u.setLogin(rs.getString("login"));
				u.setPassword(rs.getString("password"));
				u.setId(rs.getInt("id"));
				r.setId(rs.getInt("roleId"));
				r.setName(rs.getString("roleName"));
				u.setRole(r);
				
				personId = rs.getInt("personId");
				if(rs.wasNull() == false){
					selectPerson = connection.prepareStatement(selectPersonById);
					selectPerson.setInt(1, personId);
					rsPerson = selectPerson.executeQuery();
					if(rsPerson.next()){
						Person p = new Person();
						p.setFirstName(rsPerson.getString("firstname"));
						p.setSurname(rsPerson.getString("surname"));
						p.setAddress(rsPerson.getString("address"));
						p.setPesel(rsPerson.getString("pesel"));
						u.setPerson(p);
					}
				}
				list.add(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
