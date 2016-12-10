package com.github.ashim.documentation.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.ashim.documentation.model.Person;

/**
 * Created by ashimjk on 10/12/16.
 */
public interface PersonRepository extends CrudRepository<Person, Long> {
}
