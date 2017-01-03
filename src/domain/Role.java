package domain;

import java.util.*;

public class Role extends Entity {

	private String name;
	
	private List<Privilege> privileges;
	
	public Role()
	{
		privileges=new ArrayList<Privilege>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	@Override
	public String toString() {
		return "Role [Id=" + getId() + ", name=" + name + ", privileges=" + privileges + "]";
	}
	
}
