package org.demo.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.demo.controller.PersonDTO;
import org.demo.domain.Person;
import org.demo.exception.PersonNotFoundException;
import org.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by Administrator on 2014/12/5.
 */
@Service
public class MongoDBPersonService implements PersonService {
    private static final Log log = LogFactory.getLog(MongoDBPersonService.class);

    private PersonRepository repository;

    @Autowired
    public MongoDBPersonService(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public PersonDTO create(PersonDTO personDTO) {
        Person person = Person.getBuilder().firstName(personDTO.getFirstName()).lastName(personDTO.getLastName()).build();
        person = repository.save(person);
        return convertToDTO(person);
    }

    @Override
    public PersonDTO delete(String id) {
        Person person = findPersonById(id);
        repository.delete(person);
        return convertToDTO(person);
    }

    @Override
    public List<PersonDTO> findAll() {
        List<Person> persons = repository.findAll();
        return convertToDTOs(persons);
    }

    private List<PersonDTO> convertToDTOs(List<Person> persons) {
        return persons.stream().map(this::convertToDTO).collect(toList());
    }

    @Override
    public PersonDTO findById(String id) {
        Person person = findPersonById(id);
        return convertToDTO(person);
    }

    @Override
    public PersonDTO update(PersonDTO personDTO) {
        Person person = findPersonById(personDTO.getId());
        person.update(personDTO.getFirstName(), personDTO.getLastName());
        person = repository.save(person);
        return convertToDTO(person);
    }

    private Person findPersonById(final String id) {
        Optional<Person> person = repository.findOne(id);
        return person.orElseThrow(() -> new PersonNotFoundException(id));
    }

    private PersonDTO convertToDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        return personDTO;
    }
}
