package org.example.operation;

import org.example.exception.DuplicateUserException;
import org.example.exception.InvalidDataException;
import org.example.exception.UserNotFoundException;
import org.example.struct.Struct;


import java.util.*;

public class dbOperation {
    private final Map<Long, Struct> userMap = new HashMap<>();

    /**
     * Проверяет, существует ли пользователь с заданными данными акаунта.
     *
     * @param struct объект Struct, представляющий пользователя
     * @return true, если пользователь существует, иначе false
     */
    public boolean userExists(Struct struct) {
        return userMap.containsKey(struct.getAccount());
    }

    /**
     * Добавляет пользователя в базу данных.
     *
     * @param struct объект Struct, представляющий пользователя
     * @throws DuplicateUserException если пользователь с такими же данными аканута уже существует
     */
    public void add(Struct struct) throws DuplicateUserException {
        if (userExists(struct)) {
            throw new DuplicateUserException("Пользователь с такими же данными акаунта уже существует.");
        }
        userMap.put(struct.getAccount(), struct);
    }

    /**
     * Удаляет пользователя из базы данных.
     *
     * @param struct объект Struct, представляющий пользователя
     * @throws UserNotFoundException если пользователь не найден
     */
    public void remove(Struct struct) throws UserNotFoundException {
        if (userMap.remove(struct.getAccount()) == null) {
            throw new UserNotFoundException("Пользователь не существует.");
        }
    }
   public void  update(Long id,Struct newStructForReplace) throws InvalidDataException {
        update(id,newStructForReplace.getAccount(),newStructForReplace.getName(),newStructForReplace.getValue());
   }
    /**
     * Обновляет данные пользователя в базе данных.
     *
     * @param id объект Struct, представляющий пользователя, данные которого нужно обновить
     * @param newAccount     новое значение аканута
     * @param newName        новое значение имени
     * @param newValue       новое значение значения
     * @throws InvalidDataException  если предоставлены неверные данные для операции обновления
     * @throws UserNotFoundException если пользователь не найден
     */
    public void update(Long id, Long newAccount, String newName, Double newValue)
            throws InvalidDataException, UserNotFoundException {

        if (newAccount == null || newName == null || newValue == null) {
            throw new InvalidDataException("Предоставлены неверные данные для операции обновления.");
        }

        Struct foundUser = userMap.get(id);
        if (foundUser == null) {
            throw new UserNotFoundException("Пользователь не найден.");
        }

        if (newAccount!=null){
          foundUser.setAccount(newAccount);
             }
        if (newName!=null){
            foundUser.setName(newName);
        }

        if (newValue!=null){
            foundUser.setValue(newValue);
        }
    }

    /**
     * Ищет пользователя по акануту  в базе данных.
     *
     * @param account акануту пользователя
     * @return объект Struct, представляющий найденного пользователя
     * @throws UserNotFoundException если пользователь не найден
     */
    public Struct findUserByAccount(Long account) throws UserNotFoundException {
        Struct struct = userMap.get(account);
        if (struct == null) {
            throw new UserNotFoundException("Пользователь с указанным аканутом не найден.");
        }
        return struct;
    }

    /**
     * Ищет пользователя по имени в базе данных.
     *
     * @param name имя пользователя
     * @return объект Struct, представляющий найденного пользователя, или null, если пользователь не найден
     */
    public Struct findUserByName(String name) {
        for (Struct struct : userMap.values()) {
            if (struct.getName().equals(name)) {
                return struct;
            }
        }
        return null;
    }

    /**
     * Ищет пользователя по значению в базе данных.
     *
     * @param value значение пользователя
     * @return объект Struct, представляющий найденного пользователя, или null, если пользователь не найден
     */
    public Struct findUserByValue(Double value) throws UserNotFoundException {
        for (Struct struct : userMap.values()) {
            if (Objects.equals(struct.getValue(), value)) {
                return struct;
            }
        }
        throw new UserNotFoundException("User not found with the specified value.");
    }


    /**
     * Выводит детали всех пользователей в базе данных.
     */
    public void printDetails() {
      userMap.values().forEach(struct -> System.out.println(struct.toString()));
        }
    }
