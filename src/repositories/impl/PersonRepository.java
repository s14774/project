package repositories.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Person;
import repositories.IRepository;
import unitofwork.IUnitOfWork;

public class PersonRepository extends Repository<Person>{

	protected PersonRepository(Connection connection,
			IEntityBuilder<Person> builder, IUnitOfWork uow) {
		super(connection, builder, uow);
	}

	@Override
	protected void setUpUpdateQuery(Person entity) throws SQLException {
		update.setString(1, entity.getFirstName());
		update.setString(2, entity.getSurname());
		update.setString(3, entity.getPesel());
		update.setString(4, entity.getAddress());
		update.setInt(5, entity.getId());
	}

	@Override
	protected void setUpInsertQuery(Person entity) throws SQLException {
		insert.setString(1, entity.getFirstName());
		insert.setString(2, entity.getSurname());
		insert.setString(3, entity.getPesel());
		insert.setString(4, entity.getAddress());
	}

	@Override
	protected String getTableName() {
		return "person";
	}

	@Override
	protected String getUpdateQuery() {
		return "update person set (firstname,surname,pesel,address)=(?,?,?,?)"
				+ "where id=?";
	}

	@Override
	protected String getInsertQuery() {
		return "insert into person(firstname,surname,pesel,address) values(?,?,?,?)";
	}
	
}