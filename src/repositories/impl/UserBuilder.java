package repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import domain.User;

public class UserBuilder implements IEntityBuilder<User> {

	RepositoryCatalog catalog;
	
	public UserBuilder(RepositoryCatalog repositoryCatalog) {
		this.catalog = repositoryCatalog;
	}

	@Override
	public User build(ResultSet rs) throws SQLException {
		User result = new User();
		result.setId(rs.getInt("id"));
		result.setLogin(rs.getString("login"));
		result.setPassword(rs.getString("password"));
		result.setRole(catalog.getRoles().get(rs.getInt("roleId")));
		result.setPerson(catalog.getPersons().get(rs.getInt("personId")));
		return result;
	}

}
