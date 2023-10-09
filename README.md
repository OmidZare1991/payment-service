# Payment Processing Microservice

**This project consists of three microservices:**

**Logger Microservice**: Responsible for handling and storing log data.


**Kafka Handler Microservice:** Consumes messages from Kafka topics related to online and offline payments.


**Payment Validation Microservice:** Validates payments based on their type and sends log data to the Logger Microservice.


These microservices communicate with each other via **Kafka**, and data is stored in a **PostgreSQL** database. **Redis** is used for caching, enhancing performance. 

Third-party API calls are made using the Feign client.


**Prerequisites**


Before running this application, ensure you have the following prerequisites:


Docker Compose: You must have Docker Compose installed on your server.



With Docker Compose, you can conveniently set up the required services like Redis, PostgreSQL, and Kafka. In the root directory of the project, you will find a Docker Compose file that automates the startup of these services. You don't need to configure anything; everything will be ready when the application starts up.



You can also extend the Docker Compose file to include additional services like PostgreSQL and Kafka. This ensures that when the application starts, all necessary dependencies are available.



**Future Improvements**


For **future improvements**, consider implementing the following:


**CQRS** (Command Query Responsibility Segregation) Pattern with Axon: Enhance the application's scalability and separation of concerns by implementing CQRS. This pattern separates command-side and query-side operations.


**SAGA Pattern:** Introduce the SAGA pattern to handle distributed transactions. This pattern helps maintain data consistency in distributed systems by orchestrating a series of local transactions.


By implementing these patterns, the project can become more resilient, scalable, and better suited to handle complex transactional workflows.





