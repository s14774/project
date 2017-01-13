package repositories.impl;

import java.util.ArrayList;
import java.util.List;

import domain.Role;
import domain.User;
import repositories.IUserRepository;

public class DummyUserRepository implements IUserRepository{

	private DummyDb db;
	
	public DummyUserRepository(DummyDb db) {
		super();
		this.db = db;
	}

	@Override
	public void save(User entity) {
		db.users.add(entity);
		
	}

	@Override
	public void update(User entity) {

		
	}

	@Override
	public void delete(User entity) {
		db.users.remove(entity);
		
	}

	@Override
	public User get(int id) {
		for(User u:db.users)
			if(u.getId()==id)
				return u;
		return null;
	}

	@Override
	public List<User> getAll() {

		return db.users;
	}

	@Override
	public List<User> withRole(Role role) {
		return withRole(role.getId());
	}

	@Override
	public List<User> withRole(String roleName) {
		ArrayList<User> list = new ArrayList<User>();

		for(User u:db.users)
			if(u.getRole().getName().equals(roleName))
				list.add(u);
		return list;
	}

	@Override
	public List<User> withRole(int roleId) {
		ArrayList<User> list = new ArrayList<User>();

		for(User u:db.users)
			if(u.getRole().getId() == roleId)
				list.add(u);
		return list;
	}

	@Override
	public List<User> withName(String name) {
		ArrayList<User> list = new ArrayList<User>();

		for(User u:db.users)
			if(u.getRole().getName() == name)
				list.add(u);
		return list;
	}
}
