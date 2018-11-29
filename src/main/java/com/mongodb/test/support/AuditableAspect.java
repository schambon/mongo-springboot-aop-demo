package com.mongodb.test.support;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class AuditableAspect implements MethodInterceptor {

    static final Logger logger = LoggerFactory.getLogger(AuditableAspect.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        String name = invocation.getMethod().getName();
        if (name.startsWith("insert") || name.startsWith("update")) {

            Object[] arguments = invocation.getArguments();

            for (Object arg: arguments) {
                if (arg instanceof Auditable) {
                    logger.info("Decorating Auditable argument with audit information");
                    Auditable a = (Auditable) arg;
                    a.setLastUpdated(Instant.now());
                    a.setLastUpdateUser("some user name");
                }
            }
        }

        return invocation.proceed();

    }
}
