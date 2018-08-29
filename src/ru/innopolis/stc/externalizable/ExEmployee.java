package ru.innopolis.stc.externalizable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ExEmployee implements Externalizable {
    private String name;
    private int age;
    private double salary;
    private String job;

    public ExEmployee(String name, int age, double salary, String job) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.job = job;
    }

    public ExEmployee() {
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

    public void addSalaryToEndFile() {

    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeChars(name);
        out.writeInt(age);
        out.writeDouble(salary);
        out.writeChars(job);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = in.readLine();
        age = in.readInt();
        salary = in.readDouble();
        job = in.readLine();
    }
}
