package repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Privilege;

public class PrivilegeBuilder implements IEntityBuilder<Privilege> {

	@Override
	public Privilege build(ResultSet rs) throws SQLException {
		Privilege pr = new Privilege();
		pr.setName(rs.getString("name"));
		pr.setId(rs.getInt("id"));
		return pr;
	}

}
