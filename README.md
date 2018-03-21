# DOG SERVICE - Simple and Basic Spring boot Application

This service is used to manage dogs. This will be used for the demo.

In order to build this project just run:

	mvn clean install
	
## Run Locally

	mvn spring-boot:run

## Steps followed

* Go to start.spring.io
* Choose spring-boot-starter-web, spring-boot-starter-data-jpa, h2, flyway-core
* Download the mvn project.
* Open with your favourite editor. I chose IntelliJ
* Add SpringBootApplication, RestController, Configuration for Persistence, Repository and Entity classes
* Add application properties in resources. Add Spring datasource and Flyway configurations
* Add db migration sql files. /src/resources/db/migration/V2_create_dog.sql