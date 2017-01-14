package repositories;

import domain.*;

public interface IRepositoryCatalog {

	public IUserRepository getUsers();
	public IPersonRepository getPersons();
	public IRoleRepository getRoles();
	public IPrivilegeRepository getPrivileges();
	public void commit();
}
