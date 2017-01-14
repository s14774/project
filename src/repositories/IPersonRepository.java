package repositories;

import java.util.List;

import domain.*;

public interface IPersonRepository extends IRepository<Person>{

	public List<Person> withSurname(String surname);

}
