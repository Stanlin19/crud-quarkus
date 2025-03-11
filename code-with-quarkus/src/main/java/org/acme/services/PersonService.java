package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.models.Person;
import org.acme.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PersonService {

    @Inject
    PersonRepository personRepository;

    public List<Person> getAll(){
        return personRepository.listAll();
    }

    public Optional<Person> getPersonById(Long id){
        return Optional.ofNullable(personRepository.findById(id));
    }

    @Transactional
    public Person save(Person person){
        personRepository.persist(person);
        return person;
    }

    @Transactional
    public Person update(Person person, Long id){
        Optional<Person> existingPerson = getPersonById(id);
        if (existingPerson.isPresent()) {
            Person updatedPerson = existingPerson.get();
            updatedPerson.setName(person.getName());
            updatedPerson.setAge(person.getAge());
            return updatedPerson;
        }
        return null;
    }

    @Transactional
    public boolean deletePerson(Long id) {
        Optional<Person> person = getPersonById(id);
        person.ifPresent(personRepository::delete);
        return person.isPresent();
    }
}
