package repositories.impl;

import domain.Person;
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
	public IRepository<Person> getPersons() {
		return new DummyPersonRepository(db);
	}

}
