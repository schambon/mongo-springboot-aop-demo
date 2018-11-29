package com.mongodb.test.service;


import com.mongodb.test.dao.PersonDAO;
import com.mongodb.test.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonService {

    @Autowired
    PersonDAO dao;

    @RequestMapping(method = RequestMethod.POST, value = "/person")
    public void create(@RequestBody Person p) {
        dao.insertPerson(p);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/person/{first}")
    public Person getByFirstName(@PathVariable String first) {
        return dao.findPersonByFirst(first);
    }

}
