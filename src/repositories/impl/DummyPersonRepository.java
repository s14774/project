package repositories.impl;

import java.util.ArrayList;
import java.util.List;

import domain.Person;
import domain.User;
import repositories.IPersonRepository;
import repositories.IRepository;

public class DummyPersonRepository implements IPersonRepository {

	private DummyDb db;
	
	public DummyPersonRepository(DummyDb db) {
		this.db = db;
	}

	@Override
	public void save(Person entity) {

		db.persons.add(entity);
		
	}

	@Override
	public void update(Person entity) {
		
	}

	@Override
	public void delete(Person entity) {

		db.persons.remove(entity);
		
	}

	@Override
	public Person get(int id) {

		for(Person p: db.persons)
			if(p.getId()==id)
				return p;
		return null;
	}

	@Override
	public List<Person> getAll() {
		return db.persons;
	}

	@Override
	public List<Person> withSurname(String surname) {
		ArrayList<Person> list = new ArrayList<Person>();

		for(Person p:db.persons)
			if(p.getSurname() == surname)
				list.add(p);
		return list;
	}

}
