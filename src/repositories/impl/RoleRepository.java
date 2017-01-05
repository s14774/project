package repositories.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import domain.Role;
import unitofwork.IUnitOfWork;

public class RoleRepository extends Repository<Role> {
	
	protected RoleRepository(Connection connection,
			IEntityBuilder<Role> builder, IUnitOfWork uow) {
		super(connection, builder, uow);
	}

	@Override
	protected void setUpUpdateQuery(Role entity) throws SQLException {
		update.setString(1, entity.getName());
		if(entity.getPrivilege() == null)
			update.setNull(2, Types.INTEGER);
		else
			update.setInt(2, entity.getPrivilege().getId());
		update.setInt(3, entity.getId());
	}

	@Override
	protected void setUpInsertQuery(Role entity) throws SQLException {
		insert.setString(1, entity.getName());
		if(entity.getPrivilege() == null)
			insert.setNull(2, Types.INTEGER);
		else
			insert.setInt(2, entity.getPrivilege().getId());
	}

	@Override
	protected String getTableName() {
		return "userRoles";
	}

	@Override
	protected String getUpdateQuery() {
		return "update "+ getTableName() +" set (name,privilegeId)=(?,?) where id=?;";
	}

	@Override
	protected String getInsertQuery() {
		return "insert into "+ getTableName() +"(name,privilegeId) values(?,?)";
	}
}
