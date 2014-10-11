package repositories;

import domain.Person;

public interface IRepositoryCatalog {

	public IUserRepository getUsers();
	public IRepository<Person> getPersons();
}
