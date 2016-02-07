package org.hameister.personmanager.service;

import org.hameister.personmanager.model.Person;

import java.util.Collection;

/**
 * Created by hameister on 31.01.16.
 */
public interface PersonInterface {

        Collection<Person> findAll();

        Person findOne(Long id);

        Person create(Person person);

        Person update(Person person);

        void delete(long id);
}
