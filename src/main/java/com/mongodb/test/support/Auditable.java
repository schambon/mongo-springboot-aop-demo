package com.mongodb.test.support;

import java.time.Instant;

public interface Auditable {

    Instant getLastUpdated();

    void setLastUpdated(Instant instant);

    String getLastUpdateUser();

    void setLastUpdateUser(String user);



}
