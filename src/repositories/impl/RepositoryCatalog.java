package repositories.impl;

import java.sql.Connection;

import domain.Person;
import domain.Privilege;
import domain.Role;
import repositories.IPersonRepository;
import repositories.IPrivilegeRepository;
import repositories.IRepository;
import repositories.IRepositoryCatalog;
import repositories.IRoleRepository;
import repositories.IUserRepository;
import unitofwork.IUnitOfWork;

public class RepositoryCatalog implements IRepositoryCatalog{

	private Connection connection;
	private IUnitOfWork uow;
	
	public RepositoryCatalog(Connection connection, IUnitOfWork uow) {
		super();
		this.connection = connection;
		this.uow = uow;
	}

	@Override
	public void commit() {
		uow.commit();
	}

	@Override
	public IUserRepository getUsers() {
		return new UserRepository(connection, new UserBuilder(this), uow);
	}

	@Override
	public IPersonRepository getPersons() {
		return new PersonRepository(connection, new PersonBuilder(), uow);
	}

	@Override
	public IRoleRepository getRoles() {
		return new RoleRepository(connection, new RoleBuilder(this), uow);
	}

	@Override
	public IPrivilegeRepository getPrivileges() {
		return new PrivilegeRepository(connection, new PrivilegeBuilder(), uow);
	}

}
