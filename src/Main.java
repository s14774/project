import java.util.List;

import domain.User;
import repositories.IRepositoryCatalog;
import repositories.impl.DummyRepositoryCatalog;


public class Main {

	public static void main(String[] args) {

		IRepositoryCatalog catalog = new DummyRepositoryCatalog();
		
		List<User> admins = catalog.getUsers().withRole("administrator");
	}

}
