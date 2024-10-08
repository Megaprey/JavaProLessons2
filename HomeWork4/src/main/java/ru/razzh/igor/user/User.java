package ru.razzh.igor.user;


public class User {
    Long id;
    String username;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public User(long id, String username) {
        this.id = id;
        this.username = username;
    }
}
