package com.sample.posa;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
public class Dogs
{
    public static void main( String[] args )
    {
        SpringApplication.run(Dogs.class, args);
    }
}

@RestController
@RequestMapping("animals/api/")
class DogController {

    @Autowired
    private DogRepository DogRepository;

    @RequestMapping(value = "dogs", method = RequestMethod.GET)
    public List<Dog> list() {
        return DogRepository.findAll();
    }

    @RequestMapping(value = "dogs", method = RequestMethod.POST)
    public Dog create(@RequestBody Dog dog) {
        return DogRepository.saveAndFlush(dog);
    }

    @RequestMapping(value = "dogs/{id}", method = RequestMethod.GET)
    public Dog get(@PathVariable Long id) {
        return DogRepository.findOne(id);
    }

    @RequestMapping(value = "dogs/{id}", method = RequestMethod.PUT)
    public Dog update(@PathVariable Long id, @RequestBody Dog dog) {
        Dog existingDog = DogRepository.findOne(id);
        BeanUtils.copyProperties(dog, existingDog);
        return DogRepository.saveAndFlush(existingDog);
    }

    @RequestMapping(value = "dogs/{id}", method = RequestMethod.DELETE)
    public Dog delete(@PathVariable Long id) {
        Dog existingDog = DogRepository.findOne(id);
        DogRepository.delete(existingDog);
        return existingDog;
    }

}

@Configuration
class PersistenceConfiguration {
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix="datasource.flyway")
    @FlywayDataSource
    public DataSource flywayDataSource() {
        return DataSourceBuilder.create().build();
    }

}

@Repository
interface DogRepository extends JpaRepository<Dog, Long> {

}

@Entity
class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String firstName;
    String lastName;
    int age;

    public Dog() { }

    public Dog(Long id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }
}