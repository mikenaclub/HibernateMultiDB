package com.example.demo.DAO.User;

import com.example.demo.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserDAO extends CrudRepository<User,Integer>{

    public List<User> findAll();
    public User findByName(String name);
}
