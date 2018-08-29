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

    @Override
    public String toString() {
        return "BufEmployee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", job='" + job + '\'' +
                '}';
    }

    public ArrayList<BufEmployee> readData(String fileName) {
        ArrayList<BufEmployee> readPersonList = new ArrayList<>();

        try (BufferedReader bufReader = new BufferedReader(new FileReader(fileName))) {

            int cntByte;
            int cntPerson = 0;
            int firstLength;
            String tempgname = "";
            String tempjob = "";
            double tempsalary = 0;
            while ((firstLength = bufReader.read()) != -1) {
                char name[] = new char[firstLength];

                readPersonList.add(new BufEmployee("", 0, 0.0, ""));
                for (cntByte = 0; cntByte < firstLength; cntByte++) {
                    name[cntByte] = (char) bufReader.read();
                    tempgname += (String) Character.toString(name[cntByte]);
                }
                readPersonList.get(cntPerson).setName(tempgname);
                readPersonList.get(cntPerson).setAge(bufReader.read());

                for (int i = 0; i < 8; i++) {
                    tempsalary = bufReader.read();//Salary

                }

                int cntByteJob = bufReader.read();
                char job[] = new char[cntByteJob];
                for (int i = 0; i < cntByteJob; i++) {
                    job[i] = (char) bufReader.read();//Job
                    tempjob += (String) Character.toString(job[i]);
                }
                readPersonList.get(cntPerson).setJob(tempjob);
                tempgname = "";
                tempjob = "";
                cntPerson++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return readPersonList;
    }

    public void writeData(ArrayList<BufEmployee> listEmployee, String fileName) {
        try (BufferedWriter bufWriter = new BufferedWriter(new FileWriter(fileName))) {

            for (int i = 0; i < listEmployee.size(); i++) {
                bufWriter.write(listEmployee.get(i).getName().getBytes().length);
                bufWriter.write(listEmployee.get(i).getName());
                bufWriter.write(listEmployee.get(i).getAge());
                bufWriter.write((int) (listEmployee.get(i).getSalary()) >> 56);
                bufWriter.write((int) (listEmployee.get(i).getSalary()) >> 48);
                bufWriter.write((int) (listEmployee.get(i).getSalary()) >> 40);
                bufWriter.write((int) (listEmployee.get(i).getSalary()) >> 32);
                bufWriter.write((int) (listEmployee.get(i).getSalary()) >> 24);
                bufWriter.write((int) (listEmployee.get(i).getSalary()) >> 16);
                bufWriter.write((int) (listEmployee.get(i).getSalary()) >> 8);
                bufWriter.write((int) (listEmployee.get(i).getSalary()));
                bufWriter.write(listEmployee.get(i).getJob().getBytes().length);
                bufWriter.write(listEmployee.get(i).getJob());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
