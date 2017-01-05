package repositories.impl;

import java.sql.Connection;
import java.sql.SQLException;
import unitofwork.IUnitOfWork;
import domain.Privilege;

public class PrivilegeRepository extends Repository<Privilege>{

	protected PrivilegeRepository(Connection connection,
			IEntityBuilder<Privilege> builder, IUnitOfWork uow) {
		super(connection, builder, uow);
	}

	@Override
	protected void setUpUpdateQuery(Privilege entity) throws SQLException {
		update.setString(1, entity.getName());
		update.setInt(2, entity.getId());
	}

	@Override
	protected void setUpInsertQuery(Privilege entity) throws SQLException {
		insert.setString(1, entity.getName());
	}

	@Override
	protected String getTableName() {
		return "privileges";
	}

	@Override
	protected String getUpdateQuery() {
		return "update "+ getTableName() +" set (name)=(?) where id=?;";
	}

	@Override
	protected String getInsertQuery() {
		return "insert into "+ getTableName() +"(name) values(?)";
	}

}