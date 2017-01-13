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
	public void test() throws SQLException {
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
				
				Person p = new Person();
				p.setFirstName("Test Person Name");
				p.setSurname("Test Person Surname");
				catalog.getPersons().save(p);
				
				User jnowak = new User();
				jnowak.setLogin("Test User Login");
				jnowak.setPassword("Test User Password");
				catalog.getUsers().save(jnowak);
				uow.commit();
	}

}
