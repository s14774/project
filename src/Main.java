import java.sql.*;
import domain.*;
import repositories.*;
import repositories.impl.*;
import unitofwork.IUnitOfWork;
import unitofwork.UnitOfWork;


public class Main {

	public static void main(String[] args) {

		String url="jdbc:hsqldb:hsql://localhost/workdb";
		User jnowak = new User();
		jnowak.setLogin("aurbanow");
		jnowak.setPassword("qwerty");
		
		try {
			
			Connection connection = DriverManager.getConnection(url);
			IUnitOfWork uow = new UnitOfWork(connection);
			
			String clearDBSQL = 
					"DROP SCHEMA PUBLIC CASCADE";
			Statement clearDB = connection.createStatement();
			clearDB.executeUpdate(clearDBSQL);
			
			String createTableSql;
			Statement createTable;
			createTable = connection.createStatement();
			
			createTableSql = 
					"CREATE TABLE IF NOT EXISTS userRoles("
					+ "id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
					+ "name VARCHAR(20) UNIQUE" 
					+ ")";
			createTable.executeQuery(createTableSql);
			
			createTableSql = 
					"CREATE TABLE IF NOT EXISTS person("
					+ "id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
					+ "firstname VARCHAR(40)," 
					+ "surname VARCHAR(40)," 
					+ "pesel VARCHAR(12)," 
					+ "address VARCHAR(255)" 
					+ ")";
			createTable.executeQuery(createTableSql);
			
			createTableSql = 
					"CREATE TABLE IF NOT EXISTS users("
					+ "id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
					+ "login VARCHAR(20),"
					+ "password VARCHAR(20),"
					+ "roleId INT REFERENCES userRoles(id),"
					+ "personId INT REFERENCES person(id)"
					+ ")";
			createTable.executeUpdate(createTableSql);
			
			/*createTableSql = 
					"CREATE TABLE IF NOT EXISTS users_userRoles("
					+ "usersId INT REFERENCES users(id),"
					+ "userRolesId INT REFERENCES userRoles(id),"
					+ "CONSTRAINT id PRIMARY KEY (usersId,userRolesId)" 
					+ ")";
			createTable.executeQuery(createTableSql);*/
			
			IRepositoryCatalog catalog = new RepositoryCatalog(connection, uow);
			
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
			
			for(User userFromDb: catalog.getUsers().getAll())
				System.out.println(userFromDb.toString());
			
			Role r = new Role();
			r.setName("rolaAdmin");
			catalog.getRoles().save(r);
			uow.commit();
			
			u.setRole(r);
			catalog.getUsers().update(u);
			uow.commit();
			
			Person p = new Person();
			p.setFirstName("Mateusz");
			p.setSurname("Sz");
			catalog.getPersons().save(p);
			u.setPerson(p);
			catalog.getUsers().update(u);
			uow.commit();
			p.setAddress("Gdańsk");
			p.setPesel("012345678901");
			catalog.getPersons().update(p);
			uow.commit();
			
			for(User userFromDb: catalog.getUsers().getAll())
				System.out.println(userFromDb.toString());
			
			System.out.println(catalog.getUsers().withRole(r).toString());
			
			//System.out.println(catalog.getUsers().withRole(0).get(0).getLogin());
			
			//insert = "INSERT INTO users_userRoles(usersId,userRolesId) VALUES(0,0)";
			//createTable = connection.createStatement();
			//createTable.executeQuery(insert);
			
			//catalog.getUsers().get(0).getRoles();
			//catalog.getRoles().get(0).getUsers();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("koniec");
	}

}
