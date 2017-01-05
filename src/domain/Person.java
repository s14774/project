package domain;

import domain.helpers.PeselHelper;

public class Person extends Entity {
	
	private String firstName;
	private String surname;
	private String pesel;
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
		//if(PeselHelper.check(pesel))
			this.pesel = pesel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Person [Id=" + getId() + ", firstName=" + firstName + ", surname=" + surname
				+ ", pesel=" + pesel + ", address=" + address + "]";
	}
}
