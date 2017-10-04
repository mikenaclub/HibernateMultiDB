package com.example.demo.DAO.Iser;

import com.example.demo.models.Iser;
import com.example.demo.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IserDAO extends CrudRepository<Iser,Integer>{

    public List<Iser> findAll();
    public Iser findByName(String name);
}
