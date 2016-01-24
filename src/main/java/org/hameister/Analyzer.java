package org.hameister;

import org.hameister.personmanager.model.Person;
import org.hameister.personmanager.repo.PersonRepository;
import org.hameister.personmanager.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by Joern Hameister on 24.01.16.
 */

@Component
public class Analyzer {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    @Autowired
    public Analyzer(PersonRepository repository, PersonService service) {
        this.personRepository = repository;
        this.personService = service;

        if (personRepository != null) {
            System.out.println("Number of Persons in DB:" + personRepository.findAll().size());


            System.out.println("Avg. Salary:" + personService.averageSalary());


            // Insert new Person into the Database
            Person person = new Person();
            person.setName("Lisa Simpson");
            person.setSalary(400l);
            person.setBirthday(LocalDate.of(1980, 12, 24));

            Person newPerson = personService.addPerson(person);


            if (newPerson != null) {

                System.out.println("Number of Persons in DB:" + personRepository.findAll().size());

                // Recalculate Average Salary
                System.out.println("Avg. Salary:" + personService.averageSalary());

            }


        }
    }


}
