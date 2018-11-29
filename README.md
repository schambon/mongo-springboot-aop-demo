Spring AOP / MongoDB Driver auditability sample
===============================================

This sample code illustrates how to use Spring AOP to implement auditability in MongoDB in a Spring Boot 2.1 project.

In practice this does the following:
- expose REST POST/GET endpoints to create/get Person objects
- access to MongoDB is done through the PojoCodec. The driver is used in a PersonDAO.
- an AuditableAspect is used to add auditability information (last update time / last update user) on insert / update method calls on the DAO

