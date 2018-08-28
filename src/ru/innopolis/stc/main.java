package ru.innopolis.stc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class main {
    public static void main(String[] args) {
        final String FILE_PATH = "person.txt";
        List<Employee> personList = new ArrayList<>();
        personList.add(new Employee("Tom", 31, 1345.23, "Programmer"));
        personList.add(new Employee("Bill", 33, 2345.23, "Tester"));
        personList.add(new Employee("Jack", 38, 4345.23, "Engineer"));
        personList.add(new Employee("Alex", 39, 345.23, "Director"));
        personList.add(new Employee("Rav", 43, 45.23, "Linguist"));
        personList.add(new Employee("Rus", 34, 545.23, "Translator"));
        personList.add(new Employee("Tat", 54, 845.23, "CEO"));
        personList.add(new Employee("Ann", 28, 745.23, "Artist"));
        personList.add(new Employee("Dina", 25, 845.23, "Actor"));
        personList.add(new Employee("Kate", 23, 1945.23, "Writer"));


        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            objectOutputStream.writeObject(personList);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        ArrayList<Employee> readPersonList = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            readPersonList = (ArrayList<Employee>) objectInputStream.readObject();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        Employee employee = new Employee();
        System.out.println(employee.getByName("Dina", FILE_PATH).toString());
        System.out.println(employee.getByJob("Writer", FILE_PATH).toString());
    }
}
