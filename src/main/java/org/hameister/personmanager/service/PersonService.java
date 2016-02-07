package org.hameister.personmanager.service;

import org.hameister.personmanager.model.Person;
import org.hameister.personmanager.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

/**
 * Created by Joern Hameister on 24.01.16.
 */
@Service
public class PersonService implements PersonInterface {


    @Autowired
    PersonRepository personRepository;

    public double  averageSalary() {

        double salarySum = 0;
        List<Person> personList= personRepository.findAll();

        for( Person person : personList) {

            salarySum =  salarySum +person.getSalary();


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            if(person.getBirthday()!=null) {
                String birthday = person.getBirthday().format(formatter);
                System.out.println("Der Geburtstag von " + person.getName() + " ist: " + birthday+ " und der Verdienst ist:"+ person.getSalary());
            }
        }

        return  salarySum / personList.size();
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Collection<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findOne(Long id) {
        return personRepository.findOne(id);
    }

    @Override
    public Person create(Person person) {

        if (person.getId() != null) {
            return null;
        }
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person) {
        Person persistedPerson =personRepository.findOne(person.getId());

        if (persistedPerson == null) {
            return null;
        }

        return personRepository.save(person);
    }

    @Override
    public void delete(long id) {
        personRepository.delete(id);
    }
}
