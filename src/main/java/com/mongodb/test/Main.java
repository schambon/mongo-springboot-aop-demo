package com.mongodb.test;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.test.dao.PersonDAO;
import com.mongodb.test.dao.PersonDAOImpl;
import com.mongodb.test.support.AuditableAspect;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public MongoClient mongoClient() {

        CodecRegistry registry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost",
                MongoClientOptions.builder().codecRegistry(registry)));

        return client;
    }

    @Autowired
    PersonDAOImpl personDAO;

    @Autowired
    BeanFactory beanFactory;

    @Autowired
    AuditableAspect auditableAspect;

    @Bean
    @Primary
    public PersonDAO auditedPersonDAO() throws ClassNotFoundException {
        ProxyFactoryBean proxy = new ProxyFactoryBean();
        proxy.setBeanFactory(beanFactory);
        proxy.setProxyInterfaces(new Class<?>[] {PersonDAO.class});
        proxy.setTarget(personDAO);

        proxy.addAdvice(auditableAspect);

        return (PersonDAO) proxy.getObject();
    }
}
