package ru.razzh.igor.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.razzh.igor.dto.ProductDto;
import ru.razzh.igor.dto.UserDto;
import ru.razzh.igor.entity.User;
import ru.razzh.igor.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDto getById(Long id) {
        return userToDto(userRepository.getById(id));
    }

    public List<UserDto> getAll() {
        List<UserDto> userDtoList = new ArrayList<>();
        userRepository.findAll().stream().forEach(product -> userDtoList.add(userToDto(product)));
        return userDtoList;
    }

    private UserDto userToDto(User user) {
        return new UserDto(user.getId(), user.getName());
    }

}
