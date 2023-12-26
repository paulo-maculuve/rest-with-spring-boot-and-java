package mz.com.maculuve.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.com.maculuve.exceptions.ResourceNotFondExecption;
import mz.com.maculuve.model.Person;
import mz.com.maculuve.repositories.PersonRepository;

@Service
public class PersonService {
	private AtomicLong atomicLong = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	private PersonRepository personRepository;
	
	
	public List<Person> findAll(){
		logger.info("Finding All Person");
		List<Person> listPerson = personRepository.findAll();
		
		return listPerson;
	}
	
	public Person findById(Long id) {
		logger.info("Find one person!!");
		
		return personRepository.findById(id).orElseThrow(() -> new ResourceNotFondExecption("No records found this ID"));
	}
	
	public Person create(Person person) {
		logger.info("Creating one person!");
		return personRepository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("Updating one person!");
		
		var entity = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFondExecption("No records found this ID"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return personRepository.save(person);
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person!");
		
		var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFondExecption("No records found this ID"));
		personRepository.delete(entity);
	}
	

}
