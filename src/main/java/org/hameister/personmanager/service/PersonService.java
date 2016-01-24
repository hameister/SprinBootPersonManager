package org.hameister.personmanager.service;

import org.hameister.personmanager.model.Person;
import org.hameister.personmanager.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by Joern Hameister on 24.01.16.
 */
@Service
public class PersonService {


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

    public Person  addPerson(Person person) {

        return personRepository.save(person);
    }
}
