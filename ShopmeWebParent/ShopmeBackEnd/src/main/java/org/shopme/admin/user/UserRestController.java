package org.shopme.admin.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/api/v1/admin/users")
@Slf4j
public class UserRestController {

    private final UserService userService;

    @PostMapping("/users/check_email")
    public String checkDuplicateEmail(@Param("email") String email){
        return userService.isEmailUnique(email)?"OK":"DUPLICATE";
    }
}
