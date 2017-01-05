package domain;

public class Privilege extends Entity {

	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Privilege [Id=" + getId() + ", name=" + name + "]";
	}
}
