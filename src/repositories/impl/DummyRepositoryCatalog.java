package repositories.impl;

import domain.Person;
import domain.Privilege;
import domain.Role;
import repositories.IPersonRepository;
import repositories.IPrivilegeRepository;
import repositories.IRepository;
import repositories.IRepositoryCatalog;
import repositories.IRoleRepository;
import repositories.IUserRepository;

public class DummyRepositoryCatalog implements IRepositoryCatalog{

	private DummyDb db = new DummyDb();
	
	@Override
	public IUserRepository getUsers() {
		return new DummyUserRepository(db);
	}

	@Override
	public IPersonRepository getPersons() {
		return new DummyPersonRepository(db);
	}

	@Override
	public IRoleRepository getRoles() {
		return null;
	}

	@Override
	public void commit() {
		
	}

	@Override
	public IPrivilegeRepository getPrivileges() {
		return null;
	}

}
