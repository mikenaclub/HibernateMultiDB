package com.example.demo.controller;

import com.example.demo.DAO.Iser.IserDAO;
import com.example.demo.models.Iser;
import com.example.demo.models.User;
import com.example.demo.DAO.User.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserDAO userDAO;

    @Autowired
    IserDAO iserDAO;

    @RequestMapping("/")
    public String home(){
        return "home.html";
    }

    @RequestMapping("findByName")
    public String findUserByName (){
        String userId = "";
        try {
            User user = userDAO.findByName("Mike");
            userId = String.valueOf(user.getId());

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(userId);
        return "home.html";
    }

    @RequestMapping("findAll")
    public String findAll(){
        try {
            List<User> userList = new ArrayList<>();
            userList = userDAO.findAll();
            System.out.println(userList.toString());

            List<Iser> iserList = new ArrayList<>();
            iserList = iserDAO.findAll();
            System.out.println(iserList.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "home.html";
    }

    @RequestMapping("insertUser")
    public String insertUser(){
        try {
            User user = new User("Mike2",20.0);
            userDAO.save(user);

        }catch (Exception e){
            e.printStackTrace();
        }
        return "home.html";
    }

    @RequestMapping("updateUser")
    public String updateUser(){
        try {
            User user = userDAO.findByName("Mike2");
            user.setSalary(30.0);
            userDAO.save(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "home.html";
    }
}
