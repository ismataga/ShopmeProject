package org.shopme.admin.user;

import org.junit.jupiter.api.Test;
import org.shopme.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
    @Autowired
    private RoleRepository repository;

    @Test
    public void testCreateFirstRole() {
        Role roleAdmin = Role.builder()
                .name("Admin")
                .description("manage everything")
                .build();

        Role saveRole = repository.save(roleAdmin);
        assertThat(saveRole.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateRestRole() {
        Role roleSalesPerson = Role.builder()
                .name("SalesPerson")
                .description("manage product price ")
                .build();

        Role roleEditor = Role.builder()
                .name("Editor")
                .description("manage product categories ")
                .build();

        Role roleShipper = Role.builder()
                .name("Shipper")
                .description("manage product view products ")
                .build();
        Role roleAssistant = Role.builder()
                .name("Assistant")
                .description("manage questions view review ")
                .build();
        repository.saveAll(List.of(roleSalesPerson, roleEditor, roleShipper, roleAssistant));
    }
}
