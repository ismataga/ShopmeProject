package org.shopme.admin.user;

import lombok.AllArgsConstructor;
import org.shopme.common.entity.Role;
import org.shopme.common.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private  UserRepository userRepository;
    private RoleRepository roleRepository;

    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }
    public  List<Role> listRoles() {
        return (List<Role>) roleRepository.findAll();

    }
    public void save(User user) {
        userRepository.save(user);
    }
}
