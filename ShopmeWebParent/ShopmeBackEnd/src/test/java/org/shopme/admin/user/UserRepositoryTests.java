package org.shopme.admin.user;

import org.junit.jupiter.api.Test;
import org.shopme.common.entity.Role;
import org.shopme.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateNewUserWithOneRole() {
        Role role = entityManager.find(Role.class, 1);
        Set<Role> adminRole = new HashSet<>();
        adminRole.add(role);

        User user = User.builder()
                .email("test@example.com")
                .password("test123")
                .firstName("John")
                .lastName("Doe")
                .enabled(true)
                .roles(adminRole)
                .build();
        User savedUser = repo.save(user);

        assertThat(savedUser.getId()).isGreaterThan(0);

    }

    @Test
    public void testCreateNewUserWithTwoRole() {
//        Role role = entityManager.find(Role.class, 1);
        Role roleEditor = Role.builder().id(3).build();
        Role roleAssistant = Role.builder().id(5).build();
        Set<Role> roles = new HashSet<>();
        roles.add(roleEditor);
        roles.add(roleAssistant);

        User user = User.builder()
                .email("test2@example.com")
                .password("test2")
                .firstName("John2")
                .lastName("Doe2")
                .enabled(true)
                .roles(roles)
                .build();
        User savedUser = repo.save(user);

        assertThat(savedUser.getId()).isGreaterThan(0);

    }

    @Test
    public void testListAllUsers() {
        Iterable<User> listUsers = repo.findAll();
        listUsers.forEach(user -> System.out.println(user));

    }

    @Test
    public void testGetById() {
        User user = repo.findById(1).get();
        System.out.println("---------------------------------------------------------------- " + user);
        assertThat(user).isNotNull();

    }

    @Test
    public void testUpdateUserDetails() {
        User user = repo.findById(1).get();
        user.setFirstName("Johnsss");
        assertThat(user).isNotNull();
        repo.save(user);

    }

    @Test
    public void testUpdateUserRoles() {
        Role roleEditor = Role.builder().id(3).build();
        Role roleAssistant = Role.builder().id(5).build();
        Set<Role> roles = new HashSet<>();
        roles.add(roleEditor);
        roles.add(roleAssistant);


        User user = repo.findById(1).get();
        user.setRoles(roles);
        assertThat(user).isNotNull();
        repo.save(user);

    }

    @Test
    public void testDeleteUserDetails() {
        User user = repo.findById(1).get();
        repo.delete(user);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "test2@example.com";
        User user = repo.getUserByEmail(email);
        assertThat(user).isNotNull();
    }
}
