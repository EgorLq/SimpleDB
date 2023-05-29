import org.example.operation.dbOperation;
import org.example.exception.DuplicateUserException;
import org.example.exception.InvalidDataException;
import org.example.exception.UserNotFoundException;
import org.example.struct.Struct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DbOperationTest {
    private dbOperation db;

    @BeforeEach
    public void setUp() {
        db = new dbOperation();
    }

    @Test
    public void testAddUser() {
        // Тест добавления пользователей в базу данных

        // Создание пользователей
        Struct user1 = new Struct(1L, "John", 100.0);
        Struct user2 = new Struct(2L, "Alice", 200.0);
        Struct user3 = new Struct(3L, "Bob", 300.0);

        try {
            db.add(user1);
            db.add(user2);
            db.add(user3);
        } catch (DuplicateUserException e) {
            Assertions.fail("Не удалось добавить пользователя: " + e.getMessage());
        }

        // Проверка наличия пользователей в базе данных
        Assertions.assertTrue(db.userExists(user1));
        Assertions.assertTrue(db.userExists(user2));
        Assertions.assertTrue(db.userExists(user3));
    }

    @Test
    public void testPrintDetails() {
        // Тест вывода деталей пользователей из базы данных

        // Создание пользователей
        Struct user1 = new Struct(1L, "John", 100.0);
        Struct user2 = new Struct(2L, "Alice", 200.0);
        Struct user3 = new Struct(3L, "Bob", 300.0);

        try {
            db.add(user1);
            db.add(user2);
            db.add(user3);
        } catch (DuplicateUserException e) {
            Assertions.fail("Не удалось добавить пользователя: " + e.getMessage());
        }

        db.printDetails(); // Проверка вывода вручную
    }

    @Test
    public void testFindUserByAccount() {
        // Тест поиска пользователя по аккаунту

        // Создание пользователей
        Struct user1 = new Struct(1L, "John", 100.0);
        Struct user2 = new Struct(2L, "Alice", 200.0);
        Struct user3 = new Struct(3L, "Bob", 300.0);

        try {
            db.add(user1);
            db.add(user2);
            db.add(user3);
        } catch (DuplicateUserException e) {
            Assertions.fail("Не удалось добавить пользователя: " + e.getMessage());
        }

        try {
            Struct foundUser = db.findUserByAccount(2L);
            Assertions.assertEquals(user2.getName(), foundUser.getName());
        } catch (UserNotFoundException e) {
            Assertions.fail("Пользователь не найден: " + e.getMessage());
        }
    }


    @Test
    public void testUpdate() {
        // Тест обновления данных пользователя в базе данных

        // Добавление пользователя в базу данных
        Struct user = new Struct(1L, "John", 100.0);
        try {
            db.add(user);
        } catch (DuplicateUserException e) {
            Assertions.fail("Не удалось добавить пользователя: " + e.getMessage());
        }

        // Обновление данных пользователя
        Long newAccount = 1L;
        String newName = "Robert";
        Double newValue = 350.0;
        try {
            db.update(1L, newAccount, newName, newValue);
        } catch (InvalidDataException | UserNotFoundException e) {
            Assertions.fail("Не удалось обновить пользователя: " + e.getMessage());
        }

        // Проверка обновленных данных пользователя в базе данных
        try {
            Struct updatedUser = db.findUserByAccount(1L);
            Assertions.assertEquals(newAccount, updatedUser.getAccount());
            Assertions.assertEquals(newName, updatedUser.getName());
            Assertions.assertEquals(newValue, updatedUser.getValue());
        } catch (UserNotFoundException e) {
            Assertions.fail("Пользователь не найден: " + e.getMessage());
        }
    }

    @Test
    public void testRemoveUser() {
        // Тест удаления пользователя из базы данных

        // Создание пользователей
        Struct user1 = new Struct(1L, "John", 100.0);
        Struct user2 = new Struct(2L, "Alice", 200.0);
        Struct user3 = new Struct(3L, "Bob", 300.0);

        try {
            db.add(user1);
            db.add(user2);
            db.add(user3);
        } catch (DuplicateUserException e) {
            Assertions.fail("Не удалось добавить пользователя: " + e.getMessage());
        }

        try {
            db.remove(user2);
        } catch (UserNotFoundException e) {
            Assertions.fail("Пользователь не найден: " + e.getMessage());
        }

        // Проверка отсутствия удаленного пользователя в базе данных
        Assertions.assertFalse(db.userExists(user2));
    }
}
