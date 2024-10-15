package ru.razzh.igor.contoller;


import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.razzh.igor.user.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/createTable")
    public String createUsertable() {
        userService.createUsersTable();
        return "create User table";
    }

    @GetMapping("/add")
    public HttpEntity<String> insertUser(@RequestParam String username) {
        userService.insertUser(username);
        return ResponseEntity.ok("user add to table");
    }
}
