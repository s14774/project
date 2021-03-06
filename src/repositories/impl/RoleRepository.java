package repositories.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import repositories.IRoleRepository;
import domain.Person;
import domain.Role;
import unitofwork.IUnitOfWork;

public class RoleRepository extends Repository<Role> implements IRoleRepository{
	
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

	@Override
	protected String getCreateTableQuery() {
		return "CREATE TABLE IF NOT EXISTS userRoles("
				+ "id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
				+ "privilegeId INT REFERENCES privileges(id),"
				+ "name VARCHAR(20) UNIQUE" 
				+ ")";
	}

	@Override
	public List<Role> withName(String name) {
		String selectByNameSql="SELECT * FROM "
				+ getTableName()
				+ " WHERE name = ?";
		PreparedStatement selectRole;
		ResultSet rs;
		List<Role> list = new ArrayList<>();
		try {
			selectRole = connection.prepareStatement(selectByNameSql);
			selectRole.setString(1, name);
			rs = selectRole.executeQuery();
			while (rs.next()){
				list.add(builder.build(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
