package org.demo.service;

import org.demo.controller.PersonDTO;

import java.util.List;

/**
 * Created by Administrator on 2014/12/17.
 */
public interface PersonService {
    PersonDTO create(PersonDTO personDTO);

    PersonDTO delete(String id);

    List<PersonDTO> findAll();

    PersonDTO findById(String id);

    PersonDTO update(PersonDTO personDTO);
}
