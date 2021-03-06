import java.sql.*;
import domain.*;
import repositories.*;
import repositories.impl.*;
import unitofwork.IUnitOfWork;
import unitofwork.UnitOfWork;


public class Main {

	public static void main(String[] args) {

		String url="jdbc:hsqldb:hsql://localhost/workdb";
		
		try {
			
			Connection connection = DriverManager.getConnection(url);
			IUnitOfWork uow = new UnitOfWork(connection);
			
			String clearDBSQL = 
					"DROP SCHEMA PUBLIC CASCADE";
			Statement clearDB = connection.createStatement();
			clearDB.executeUpdate(clearDBSQL);
			
			IRepositoryCatalog catalog = new RepositoryCatalog(connection, uow);
			
			Privilege pr = new Privilege();
			pr.setName("Pelen dostep");
			catalog.getPrivileges().save(pr);
			//uow.commit();
			
			Role r = new Role();
			r.setName("rolaAdmin");
			catalog.getRoles().save(r);
			//uow.commit();
			
			Person p = new Person();
			p.setFirstName("Mateusz");
			p.setSurname("Sz");
			catalog.getPersons().save(p);
			//uow.commit();
			
			User jnowak = new User();
			jnowak.setLogin("aurbanow");
			jnowak.setPassword("qwerty");
			catalog.getUsers().save(jnowak);
			uow.commit();
			
			for(User userFromDb: catalog.getUsers().getAll())
				System.out.println(userFromDb.toString());
			
			User u = catalog.getUsers().get(0);
			u.setPassword("1qaz2wsx");
			catalog.getUsers().update(u);
			uow.commit();
			
			//catalog.getUsers().delete(u);
			//uow.commit();
			
			u.setRole(r);
			catalog.getUsers().update(u);
			uow.commit();
			
			u.setPerson(p);
			catalog.getUsers().update(u);
			uow.commit();
			p.setAddress("Gdańsk");
			p.setPesel("92080813876");
			catalog.getPersons().update(p);
			uow.commit();
			
			for(User userFromDb: catalog.getUsers().getAll())
				System.out.println(userFromDb.toString());
			
			User u2 = new User();
			u2.setLogin("s14774");
			u2.setPassword("alaNiEmak0ta13");
			u2.setRole(catalog.getRoles().get(0));
			u2.setPerson(p);
			catalog.getUsers().save(u2);
			
			r.setPrivilege(pr);
			catalog.getRoles().update(r);
			uow.commit();
			
			for(User userFromDb: catalog.getUsers().withLogin("s14774"))
				System.out.println(userFromDb.toString());
			
			System.out.println(catalog.getUsers().withRole(r.getName()).toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("koniec");
	}

}
