package org.demo.exception;

/**
 * Created by Administrator on 2014/12/19.
 */
public class PersonNotFoundException extends RuntimeException{
    public PersonNotFoundException(String id) {
        super(String.format("No person entry found with id: <%s>", id));
    }
}
