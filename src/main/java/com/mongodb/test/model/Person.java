package com.mongodb.test.model;

import com.mongodb.test.support.Auditable;
import org.bson.types.ObjectId;

import java.time.Instant;

public class Person implements Auditable {

    ObjectId _id;

    String first, last;

    // for Auditable
    Instant lastUpdated;
    String lastUpdateUser;

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public Instant getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    @Override
    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }
}
