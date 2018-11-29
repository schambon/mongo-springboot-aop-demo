package com.mongodb.test.dao;

import com.mongodb.test.model.Person;
import org.bson.types.ObjectId;

public interface PersonDAO {
    void insertPerson(Person person);

    void updatePerson(Person person);

    Person findPersonById(ObjectId id);

    Person findPersonByFirst(String first);
}
