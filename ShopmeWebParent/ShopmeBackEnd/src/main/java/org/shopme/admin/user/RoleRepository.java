package org.shopme.admin.user;


import org.shopme.common.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
