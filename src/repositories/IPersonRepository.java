package repositories;

import java.util.List;

import domain.*;

public interface IPersonRepository extends IRepository<Person>{

	public List<Person> withSurname(String surname);
	//public Person withSurname(String string);
	//public IRepository<Privilege> withSurname(String string);
	//public List<User> withRole(String roleName);
	//public List<User> withRole(int roleId);
	//public List<User> withLogin(String login);
}
