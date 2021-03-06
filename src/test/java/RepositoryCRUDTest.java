package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import java.sql.*;

import domain.*;
import repositories.*;
import repositories.impl.*;
import unitofwork.IUnitOfWork;
import unitofwork.UnitOfWork;

public class RepositoryCRUDTest {

	@Test
	public void testCreateTable() throws SQLException {
				Connection connection = DriverManager.getConnection(
						"jdbc:hsqldb:hsql://localhost/workdb");
				IUnitOfWork uow = new UnitOfWork(connection);
				IRepositoryCatalog catalog = new RepositoryCatalog(connection, uow);
				
				Privilege pr = new Privilege();
				pr.setName("Test Privilege");
				catalog.getPrivileges().save(pr);
				
				Role r = new Role();
				r.setName("Test Role");
				catalog.getRoles().save(r);
				uow.commit();

				
				Person p = new Person();
				p.setFirstName("Testperson Name");
				p.setSurname("Testperson Surname");
				catalog.getPersons().save(p);
				uow.commit();

				
				User u = new User();
				u.setLogin("TestUserLogin");
				u.setPassword("Test User Password");
				catalog.getUsers().save(u);
				uow.commit();
				
				connection.close();
				connection = null;
				u = null;
				p = null;
				r = null;
				pr = null;
				uow = null;
				catalog = null;
				
				connection = DriverManager.getConnection(
						"jdbc:hsqldb:hsql://localhost/workdb");
				uow = new UnitOfWork(connection);
				catalog = new RepositoryCatalog(connection, uow);
				
				u = catalog.getUsers().withLogin("TestUserLogin").get(0);
				u.setPassword("UserSecondPassword");
				catalog.getUsers().update(u);
				uow.commit();
				
				assertEquals("UserSecondPassword", catalog.getUsers().withLogin("TestUserLogin").get(0).getPassword());
	}
	
	@Test
	public void testRemoveUser() throws SQLException {
		Connection connection = DriverManager.getConnection(
				"jdbc:hsqldb:hsql://localhost/workdb");
		IUnitOfWork uow = new UnitOfWork(connection);
		IRepositoryCatalog catalog = new RepositoryCatalog(connection, uow);
		
		catalog.getUsers().delete(catalog.getUsers().withLogin("TestUserLogin").get(0));
		uow.commit();
		
		catalog.getPersons().delete(catalog.getPersons().withSurname("Testperson Surname").get(0));
		uow.commit();
		
		catalog.getRoles().delete(catalog.getRoles().withName("Test Role").get(0));
		uow.commit();
		
		catalog.getPrivileges().delete(catalog.getPrivileges().withName("Test Privilege").get(0));
		uow.commit();
	}

}
