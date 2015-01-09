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

    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    PersonDTO create(@RequestBody @Valid PersonDTO personDTO) {
        logger.info("Creating a new person entry with information: {}", personDTO);

        PersonDTO created = service.create(personDTO);

        logger.info("Created a new person entry with information: {}", created);

        return created;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    PersonDTO delete(@PathVariable("id") String id) {
        logger.info("Deleting person entry with id: {}", id);

        PersonDTO deleted = service.delete(id);

        logger.info("Deleted person entry with information: {}", deleted);
        return deleted;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<PersonDTO> findAll() {
        logger.info("Finding all person entries");

        List<PersonDTO> personEntries = service.findAll();

        logger.info("Found {} person entries", personEntries);

        return personEntries;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    PersonDTO findById(@PathVariable("id") String id) {
        logger.info("Finding person entry with id: {}", id);

        PersonDTO personEntry = service.findById(id);

        logger.info("Found person entry with information: {}", personEntry);

        return personEntry;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    PersonDTO update(@RequestBody @Valid PersonDTO personDTO) {
        logger.info("Updating person entry with information: {}", personDTO);

        PersonDTO updated = service.update(personDTO);

        logger.info("Updated person entry with infomation: {}", updated);

        return updated;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handlePersonNotFound(PersonNotFoundException exception) {
        logger.error("Handling error with message: {}", exception.getMessage());
    }
}
