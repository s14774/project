package domain;

public class Role extends Entity {

	private String name;
	
	private Privilege privilege;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Privilege getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}
	@Override
	public String toString() {
		return "Role [Id=" + getId() + ", name=" + name + ", privilege=" + privilege + "]";
	}
	
}
