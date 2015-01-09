package org.demo.repository;

import org.demo.domain.Person;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Administrator on 2014/12/11.
 */
public interface PersonRepository extends Repository<Person, String> {
    List<Person> findByLastName(String name);

    List<Person> findByFirstName(String name);

    void delete(Person person);

    List<Person> findAll();

    Optional<Person> findOne(String id);

    Person save(Person person);
}
