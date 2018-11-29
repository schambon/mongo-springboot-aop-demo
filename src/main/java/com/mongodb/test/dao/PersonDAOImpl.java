package com.mongodb.test.dao;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.test.model.Person;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.mongodb.client.model.Filters.eq;

@Service
public class PersonDAOImpl implements PersonDAO {

    @Autowired
    MongoClient mongoClient;

    @Override
    public void insertPerson(Person person) {
        mongoClient.getDatabase("test").getCollection("leshalles_201811", Person.class).insertOne(person);
    }

    @Override
    public void updatePerson(Person person) {
        MongoCollection<Person> collection = mongoClient.getDatabase("test").getCollection("leshalles_201811", Person.class);

        collection.replaceOne(eq("_id", person.getId()), person);
    }

    @Override
    public Person findPersonById(ObjectId id) {
        MongoCollection<Person> collection = mongoClient.getDatabase("test").getCollection("leshalles_201811", Person.class);
        return collection.find(eq("_id", id)).first();
    }

    @Override
    public Person findPersonByFirst(String first) {
        MongoCollection<Person> collection = mongoClient.getDatabase("test").getCollection("leshalles_201811", Person.class);
        return collection.find(eq("first", first)).first();
    }
}
