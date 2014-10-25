import java.util.List;

import domain.*;
import repositories.IRepositoryCatalog;
import repositories.impl.DummyRepositoryCatalog;


public class Main {

	public static void main(String[] args) {

		IRepositoryCatalog catalog = new DummyRepositoryCatalog();
		
		Person jan = new Person();
		jan.setFirstName("Jan");
		jan.setSurname("Nowak");
		
		catalog.getPersons().save(jan);
		
		User jnowak = new User();
		jnowak.setLogin("jnowak");
		jnowak.setPassword("password");
		catalog.getUsers().save(jnowak);
		
		Role admin = new Role();
		admin.setName("administrator");
		catalog.getRoles().save(admin);
		
		jan.setUser(jnowak);
		jnowak.getRoles().add(admin);
		admin.getUsers().add(jnowak);
		
		for(User user: catalog.getUsers().withRole("administrator"))
			System.out.print(user.getLogin());
		
		
	}

}
