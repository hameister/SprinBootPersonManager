package org.hameister.personmanager.controller;

import org.hameister.personmanager.model.Person;
import org.hameister.personmanager.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by hameister on 31.01.16.
 */

@RestController
public class PersonController {

    @Autowired
    PersonService personService;



    /**************
     FIND ALL
     **************/
    @RequestMapping(value = "/api/persons", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<Person>> getPersons() {
        Collection<Person> persons = personService.findAll();

        return new ResponseEntity<Collection<Person>>(persons, HttpStatus.OK);
    }




    /**************
     FIND ONE
     **************/
    @RequestMapping(value = "/api/persons/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Person> getPerson(@PathVariable("id") long id) {

        Person person =personService.findOne(id);

        if(person == null) {

            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }



    /**************
     CREATE
     **************/
    @RequestMapping(value = "/api/persons", method = RequestMethod.POST, consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Person> createPerson(@RequestBody Person person) {


        Person saved  = personService.create(person);

        return new ResponseEntity<Person>(saved, HttpStatus.CREATED);
    }

    /**************
     UPDATE
     **************/
    @RequestMapping(value = "/api/persons/{id}", method = RequestMethod.PUT, consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Person> updatePerson (@RequestBody Person person) {

        if (person.getId() == null) {
            return  new ResponseEntity<Person>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Person updatedGreeting = personService.update(person);

        if (updatedGreeting==null){
            return  new ResponseEntity<Person>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity<Person>(updatedGreeting, HttpStatus.OK);
    }


    /**************
     DELETE
     **************/
    @RequestMapping(value = "/api/persons/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Person> deletePerson(@PathVariable("id") long id,  @RequestBody Person person) {
        personService.delete(id);

        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }

}
