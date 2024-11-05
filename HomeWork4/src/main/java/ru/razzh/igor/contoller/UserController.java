package ru.razzh.igor.contoller;


import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.razzh.igor.dto.ProductDto;
import ru.razzh.igor.dto.UserDto;
import ru.razzh.igor.service.UserService;

import java.util.List;

@RestController
@RequestMapping("v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable long id) {
        return userService.getById(id);
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getAll();
    }

}
