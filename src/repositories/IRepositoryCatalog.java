package repositories;

import domain.*;

public interface IRepositoryCatalog {

	public IUserRepository getUsers();
	public IPersonRepository getPersons();
	public IRepository<Role> getRoles();
	public IRepository<Privilege> getPrivileges();
	public void commit();
}
