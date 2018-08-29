package ru.innopolis.stc.bufferedreader;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        final String FILE_PATH = "bufperson.txt";

        ArrayList<BufEmployee> personList = new ArrayList<>();
        personList.add(new BufEmployee("Tom", 31, 1345.23, "Programmer"));
        personList.add(new BufEmployee("Bill", 33, 2345.23, "Tester"));
        personList.add(new BufEmployee("Jack", 38, 4345.23, "Engineer"));
        personList.add(new BufEmployee("Alex", 39, 345.23, "Director"));
        personList.add(new BufEmployee("Rav", 43, 45.23, "Linguist"));
        personList.add(new BufEmployee("Rus", 34, 545.23, "Translator"));
        personList.add(new BufEmployee("Tat", 54, 845.23, "CEO"));
        personList.add(new BufEmployee("Ann", 28, 745.23, "Artist"));
        personList.add(new BufEmployee("Dina", 25, 845.23, "Actor"));
        personList.add(new BufEmployee("Kate", 23, 1945.23, "Writer"));

        BufEmployee bufEmployee = new BufEmployee();
        bufEmployee.writeData(personList, FILE_PATH);

        System.out.println(bufEmployee.readData(FILE_PATH));

    }
}
