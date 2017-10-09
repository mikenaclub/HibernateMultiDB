package com.example.demo.controller;
import org.hibernate.*;
import com.example.demo.DAO.Iser.IserDAO;
import com.example.demo.models.Iser;
import com.example.demo.models.User;
import com.example.demo.DAO.User.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    @Qualifier("iserSessionFactory")
    SessionFactory iserSessionFactory;

    @Autowired
    @Qualifier("userSessionFactory")
    SessionFactory userSessionFactory;

    @Autowired
    @Qualifier("iserEntityManager")
    EntityManager iserEntityManager;

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
            /*User user = new User("Mike4",30.0);
            System.out.println(user.toString());
            userDAO.save(user);*/

            List<User> userList = new ArrayList<>();
            User user1 = new User();
            user1.setName("batch11");
            user1.setSalary(10.0);
            userList.add(user1);
            User user2 = new User();
            user2.setName("batch22");
            userList.add(user2);
            System.out.println(userList.toString());
            Session session1 = userSessionFactory.openSession();
            Transaction tx1 = session1.beginTransaction();
            for(User user: userList){
                session1.save(user);
            }
            tx1.commit();
            session1.close();


            List<Iser> iserList = new ArrayList<>();
            Iser iser1 = new Iser();
            iser1.setName("batch11");
            iser1.setSalary(10.0);
            iserList.add(iser1);
            Iser iser2 = new Iser();
            iser2.setName("batch22");
            iser2.setSalary(20.0);
            iserList.add(iser2);
            System.out.println(iserList.toString());
            Session session2 = iserSessionFactory.openSession();
            Transaction tx2 = session2.beginTransaction();
            for (Iser iser: iserList)
            {
                session2.save(iser);
            }
            tx2.commit();
            session2.close();


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
