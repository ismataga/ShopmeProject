package org.shopme.admin.user;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.shopme.common.entity.Role;
import org.shopme.common.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    public List<Role> listRoles() {
        return (List<Role>) roleRepository.findAll();

    }

    public void save(User user) {
        encodePassword(user);
        userRepository.save(user);
    }

    private void encodePassword(User user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);

    }

    public boolean isEmailUnique(String email) {
        User userByEmail = userRepository.getUserByEmail(email);
        return userByEmail == null;
    }
}
