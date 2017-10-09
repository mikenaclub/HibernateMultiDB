package com.example.demo.models;

import com.sun.istack.internal.NotNull;
import javax.persistence.*;

@Entity
@Table(name = "infouser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    private double salary;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    public User(){

    }

    public User(String name,double salary){
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary(double v) {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
