package org.demo.controller;

import org.demo.exception.PersonNotFoundException;
import org.demo.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Administrator on 2014/12/17.
 */
@RestController
@RequestMapping("/api/person")
final public class PersonController {
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    PersonDTO create(@RequestBody @Valid PersonDTO personDTO) {

        PersonDTO created = service.create(personDTO);

        return created;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    PersonDTO delete(@PathVariable("id") String id) {

        PersonDTO deleted = service.delete(id);

        return deleted;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<PersonDTO> findAll() {

        List<PersonDTO> personEntries = service.findAll();

        return personEntries;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    PersonDTO findById(@PathVariable("id") String id) {

        PersonDTO personEntry = service.findById(id);

        return personEntry;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    PersonDTO update(@RequestBody @Valid PersonDTO personDTO) {

        PersonDTO updated = service.update(personDTO);

        return updated;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handlePersonNotFound(PersonNotFoundException exception) {
        logger.error("Handling error with message: {}", exception.getMessage());
    }
}
