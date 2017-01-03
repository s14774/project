package repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Role;

public class RoleBuilder implements IEntityBuilder<Role> {

	@Override
	public Role build(ResultSet rs) throws SQLException {
		Role role = new Role();
		role.setName(rs.getString("name"));
		role.setId(rs.getInt("id"));
		return role;
	}

}
