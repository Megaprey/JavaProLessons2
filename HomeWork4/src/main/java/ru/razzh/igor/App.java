package ru.razzh.igor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.razzh.igor.user.User;
import ru.razzh.igor.user.UserService;

import java.util.List;

@ComponentScan
public class App
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        UserService userService = context.getBean(UserService.class);

        userService.createUsersTable();
        User user = new User(1, "Artem");
        // Создание пользователя
        userService.insertUser(user.getUsername());
        System.out.println("Создан пользователь: " + user);

        // Получение пользователя по ID
        User fetchedUser = userService.getById(user.getId());
        System.out.println("Полученный пользователь: " + fetchedUser);

        // Получение всех пользователей
        List<User> allUsers = userService.getAll();
        System.out.println("Все пользователи: " + allUsers);

        // Удаление пользователя
        userService.delete(user.getId());
        System.out.println("Пользователь удален.");

        context.close();
    }
}