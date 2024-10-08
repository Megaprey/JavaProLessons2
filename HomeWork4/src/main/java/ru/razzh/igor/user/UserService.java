package ru.razzh.igor.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void insertUser(String username) {
        userDao.insertUser(username);
    }

    public void delete(Long id) {
        userDao.delete(id);
    }

    public User getById(Long id) {
        return userDao.getById(id);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public void createUsersTable() {
        userDao.createUserTable();
    }
}
