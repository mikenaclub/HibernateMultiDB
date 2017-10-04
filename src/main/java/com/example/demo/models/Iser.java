package com.example.demo.models;

import com.sun.istack.internal.NotNull;
import javax.persistence.*;

@Entity
@Table(name = "info")
public class Iser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Iser(){

    }

    public Iser(String name,double salary){
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
