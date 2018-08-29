package ru.innopolis.stc.bufferedreader;

import java.io.*;
import java.util.ArrayList;

public class BufEmployee {
    private String name;
    private int age;
    private double salary;
    private String job;

    public BufEmployee(String name, int age, double salary, String job) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.job = job;
    }

    public BufEmployee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


    public ArrayList<BufEmployee> readData(String fileName) {
        ArrayList<BufEmployee> readPersonList = new ArrayList<>();
        ;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            int cntByte;
            int temp;
            int cntPerson = 0;
            int firstLength;
            while ((firstLength = br.read()) != -1) {
                readPersonList.add(new BufEmployee("", 0, 0.0, ""));
                for (cntByte = 0; cntByte < firstLength; cntByte++) {
                    readPersonList.toArray()[cntByte] = br.read();//Name
                }
                readPersonList.get(cntPerson).setName(name);
                readPersonList.toArray()[cntByte] = br.read();//Age

                for (int i = 0; i < 8; i++) {
                    readPersonList.toArray()[cntByte + i] = br.read();//Salary
                }

                int cntByteJob = br.read();
                for (int i = 0; i < cntByteJob; i++) {
                    readPersonList.toArray()[cntByte] = br.read();//Job
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return readPersonList;
    }

    public void writeData(ArrayList<BufEmployee> listEmployee, String fileName) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(fileName))) {

            for (int i = 0; i < listEmployee.size(); i++) {
                br.write(listEmployee.get(i).getName().getBytes().length);
                br.write(listEmployee.get(i).getName());
                br.write(listEmployee.get(i).getAge());
                br.write((int) (listEmployee.get(i).getSalary()) >> 56);
                br.write((int) (listEmployee.get(i).getSalary()) >> 48);
                br.write((int) (listEmployee.get(i).getSalary()) >> 40);
                br.write((int) (listEmployee.get(i).getSalary()) >> 32);
                br.write((int) (listEmployee.get(i).getSalary()) >> 24);
                br.write((int) (listEmployee.get(i).getSalary()) >> 16);
                br.write((int) (listEmployee.get(i).getSalary()) >> 8);
                br.write((int) (listEmployee.get(i).getSalary()));
                br.write(listEmployee.get(i).getJob().getBytes().length);
                br.write(listEmployee.get(i).getJob());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
