import org.example.Operation.dbOperation;
import org.example.exception.DuplicateUserException;
import org.example.exception.InvalidDataException;
import org.example.exception.UserNotFoundException;
import org.example.struct.Struct;

import javax.print.PrintException;

public class dbOperationTest {
    public static void main(String[] args) {
        dbOperation db = new dbOperation();

        // Создание и добавление пользователей
        Struct user1 = new Struct(1L, "John", 100.0);
        Struct user2 = new Struct(2L, "Alice", 200.0);
        Struct user3 = new Struct(3L, "Bob", 300.0);

        try {
            db.add(user1);
            db.add(user2);
            db.add(user3);
        } catch (DuplicateUserException e) {
            System.out.println("Failed to add user: " + e.getMessage());
        }

        // Вывод информации о пользователях
        db.printDetails();

        // Поиск пользователя по счету
        try {
            Struct foundUser = db.findUserByAccount(2L);
            System.out.println("Found user: " + foundUser.getName());
        } catch (UserNotFoundException e) {
            System.out.println("User not found: " + e.getMessage());
        }

        // Обновление данных пользователя
        try {
            db.update(user3, 3L, "Robert", 350.0);
            System.out.println("User updated successfully");
        } catch (InvalidDataException e) {
            System.out.println("Failed to update user: " + e.getMessage());
        } catch (UserNotFoundException e) {
            System.out.println("User not found: " + e.getMessage());
        }

        // Удаление пользователя
        try {
            db.remove(user2);
            System.out.println("User removed successfully");
        } catch (UserNotFoundException e) {
            System.out.println("User not found: " + e.getMessage());
        }
    }
}
