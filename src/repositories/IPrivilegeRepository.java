package repositories;

import java.util.List;

import domain.*;

public interface IPrivilegeRepository extends IRepository<Privilege>{

	public List<Privilege> withName(String name);

}
