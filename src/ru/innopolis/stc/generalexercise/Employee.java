package ru.innopolis.stc.generalexercise;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Employee implements Serializable {
    private String name;
    private int age;
    private double salary;
    private String job;

    public Employee(String name, int age, double salary, String job) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.job = job;
    }

    public Employee() {
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

    private ArrayList<Employee> readListFromFile(String filename) {
        ArrayList<Employee> readPersonList = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename))) {
            readPersonList = (ArrayList<Employee>) objectInputStream.readObject();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return readPersonList;
    }

    private boolean writeListToFile(ArrayList<Employee> personList, String filename) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            objectOutputStream.writeObject(personList);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    public boolean save(Employee newEmployee, String inputFileName) {
        ArrayList<Employee> readPersonList = null;

        readPersonList = readListFromFile(inputFileName);

        if (!readPersonList.isEmpty()) {
            readPersonList.add(newEmployee);

            if (writeListToFile(readPersonList, inputFileName)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean delete(Employee oldEmployee, String inputFileName) {
        ArrayList<Employee> readPersonList = null;
        readPersonList = readListFromFile(inputFileName);
        if (!readPersonList.isEmpty()) {
            readPersonList.remove(oldEmployee);

            if (writeListToFile(readPersonList, inputFileName)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    Employee getByName(String name, String inputFileName) {
        ArrayList<Employee> readPersonList = null;
        Employee resulPerson = null;
        readPersonList = readListFromFile(inputFileName);

        for (Employee p : readPersonList) {
            if (p.getName().equals(name)) {
                resulPerson = p;
                break;
            }
        }

        return resulPerson;
    }

    List<Employee> getByJob(String job, String inputFileName) {
        List<Employee> resultPersonList = new ArrayList<>();

        ArrayList<Employee> readPersonList = null;
        Employee resulPerson = null;
        readPersonList = readListFromFile(inputFileName);

        for (Employee p : readPersonList) {
            if (p.getJob().equals(job)) {
                resultPersonList.add(p);
            }
        }
        return resultPersonList;
    }

    boolean saveOrUpdate(Employee person, String inputFileName) {
        boolean flRewritePerson = false;
        ArrayList<Employee> readPersonList = null;
        Employee resulPerson = null;
        readPersonList = readListFromFile(inputFileName);
        for (int i = 0; i < readPersonList.size(); i++) {
            if (readPersonList.get(i).equals(person)) {
                readPersonList.set(i, person);
                flRewritePerson = true;
                break;
            }
        }
        if (!flRewritePerson) {
            readPersonList.add(person);
            writeListToFile(readPersonList, inputFileName);
            return true;
        } else {
            return false;
        }
    }

    boolean changeAllWork(String oldJob, String newJob, String inputFileName) {
        boolean flRewriteJob = false;
        ArrayList<Employee> readPersonList = null;
        Employee resulPerson = null;
        readPersonList = readListFromFile(inputFileName);
        for (int i = 0; i < readPersonList.size(); i++) {
            if (readPersonList.get(i).getJob().equals(oldJob)) {
                readPersonList.get(i).setJob(newJob);
                flRewriteJob = true;
            }
        }
        if (flRewriteJob) {
            writeListToFile(readPersonList, inputFileName);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", job='" + job + '\'' +
                '}';
    }
}
