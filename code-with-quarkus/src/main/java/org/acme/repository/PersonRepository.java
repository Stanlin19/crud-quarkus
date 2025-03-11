package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.models.Person;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {
}
