package repositories;

import java.util.List;

import domain.*;

public interface IRoleRepository extends IRepository<Role>{

	public List<Role> withName(String name);

}
