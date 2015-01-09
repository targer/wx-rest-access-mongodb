package org.demo.controller;

import org.demo.domain.Person;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by Administrator on 2014/12/17.
 */
public final class PersonDTO {
    private String id;

    @Size(max= Person.MAX_LENGTH_FIRSTNAME)
    private String firstName;

    @NotEmpty
    @Size(max= Person.MAX_LENGTH_LASTNAME)
    private String lastName;

    public PersonDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
