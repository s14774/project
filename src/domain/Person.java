package domain;

public class Person extends Entity {
	
	private String firstName;
	private String surname;
	private String pesel;
	
	private User user;
	private String address;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		if(!this.equals(user.getPerson()))
			user.setPerson(this);
		this.user = user;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
