package repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Role;

public class RoleBuilder implements IEntityBuilder<Role> {

	RepositoryCatalog catalog;

	public RoleBuilder(RepositoryCatalog repositoryCatalog) {
		this.catalog = repositoryCatalog;
	}

	@Override
	public Role build(ResultSet rs) throws SQLException {
		Role role = new Role();
		role.setName(rs.getString("name"));
		role.setPrivilege(catalog.getPrivileges().get(rs.getInt("privilegeId")));
		role.setId(rs.getInt("id"));
		return role;
	}

}
