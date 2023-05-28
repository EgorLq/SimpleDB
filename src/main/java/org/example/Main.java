package org.example;

import org.example.Operation.dbOperation;
import org.example.exception.DuplicateUserException;
import org.example.exception.InvalidDataException;
import org.example.struct.Struct;


public class Main {

    public static void main(String[] args) throws DuplicateUserException, InvalidDataException {

        dbOperation entry = new dbOperation();

        System.out.println("добавление записи  бд :");

        entry.add(new Struct(4L, "Арнольд тогвард", 44.1));
        entry.add(new Struct(3L, "Степан Шелби", 1.3));
        entry.add(new Struct(123L, "Виталий ", 14.1));
        entry.add(new Struct(14L, "Саша ", 18.1321));

        entry.printDetails();

        System.out.println("удаление записи в бд :");
        entry.remove(new Struct(123L, "Виталий ", 14.1));

        entry.printDetails();

        System.out.println("редактирование записи в бд :");
        entry.update(new Struct(4L, "Арнольд тогвард", 44.1), 9L, "Ингвар зверский", 22.8);

        entry.printDetails();

        System.out.println(entry.findUserByName("Ингвар зверский").toString());
        System.out.println(entry.findUserByAccount(14L).toString());
        System.out.println(entry.findUserByValue(1.3).toString());
    }
}