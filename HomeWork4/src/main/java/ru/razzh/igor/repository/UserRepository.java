package ru.razzh.igor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.razzh.igor.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
}
