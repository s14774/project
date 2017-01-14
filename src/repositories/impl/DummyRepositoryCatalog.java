package repositories.impl;

import domain.Person;
import domain.Privilege;
import domain.Role;
import repositories.IPersonRepository;
import repositories.IRepository;
import repositories.IRepositoryCatalog;
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
	public IRepository<Role> getRoles() {
		// TODO Auto-generated method stub
		return new DummyRoleRepository(db);
	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IRepository<Privilege> getPrivileges() {
		// TODO Auto-generated method stub
		return null;
	}

}
