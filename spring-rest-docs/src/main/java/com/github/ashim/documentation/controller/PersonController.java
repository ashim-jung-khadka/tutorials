package com.github.ashim.documentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.ashim.documentation.model.Person;
import com.github.ashim.documentation.repository.PersonRepository;

/**
 * Created by ashimjk on 10/12/16.
 */
@RestController
@RequestMapping("/people")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Iterable<Person> listPeople() {
		return this.personRepository.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Person getPerson(@PathVariable("id") Long id) {
		return this.personRepository.findOne(id);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createPerson(@RequestBody Person person) {
		this.personRepository.save(new Person(person.getFirstName(), person.getLastName()));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePerson(@PathVariable("id") Long id, @RequestBody Person person) {
		Person existingPerson = this.personRepository.findOne(id);
		existingPerson.setFirstName(person.getFirstName());
		existingPerson.setLastName(person.getLastName());
		this.personRepository.save(existingPerson);
	}
}
