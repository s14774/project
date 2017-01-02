package repositories.impl;

import java.sql.Connection;
import java.sql.SQLException;

import domain.Role;
import unitofwork.IUnitOfWork;

public class RoleRepository extends Repository<Role> {
	
	protected RoleRepository(Connection connection,
			IEntityBuilder<Role> builder, IUnitOfWork uow) {
		super(connection, builder, uow);
	}

	@Override
	protected void setUpUpdateQuery(Role entity) throws SQLException {
		insert.setString(1, entity.getName());
		update.setInt(2, entity.getId());
	}

	@Override
	protected void setUpInsertQuery(Role entity) throws SQLException {
		insert.setString(1, entity.getName());
	}

	@Override
	protected String getTableName() {
		return "userRoles";
	}

	@Override
	protected String getUpdateQuery() {
		return "update userRoles set (name)=(?) where id=?;";
	}

	@Override
	protected String getInsertQuery() {
		return "insert into userRoles(name) values(?)";
	}
}
