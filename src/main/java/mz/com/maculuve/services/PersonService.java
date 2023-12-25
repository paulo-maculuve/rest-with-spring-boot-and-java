package mz.com.maculuve.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import mz.com.maculuve.model.Person;

@Service
public class PersonService {
	private AtomicLong atomicLong = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	
	public List<Person> findAll(){
		logger.info("Finding All Person");
		List<Person> listPerson = new ArrayList();
		for(int i = 0; i< 8; i++) {
			Person person = mockPerson(i);
			listPerson.add(person);
		}
		return listPerson;
	}
	
	private Person mockPerson(int i) {
		// TODO Auto-generated method stub
		Person person = new Person();
		
		person.setId(atomicLong.incrementAndGet());
		person.setFirstName("First Name " + i);
		person.setLastName("Last Name " + i);
		person.setAddress("Address " + i);
		person.setGender("Gender " + i);
		return person;
	}

	public Person findById(String id) {
		Person person = new Person();
		logger.info("Find one person!!");
		
		person.setId(atomicLong.incrementAndGet());
		person.setFirstName("Paulo ");
		person.setLastName("Maculuve");
		person.setAddress("1 de Maio");
		person.setGender("Masculino");
		
		return person;
	}
	
	public Person create(Person person) {
		logger.info("Creating one person!");
		return person;
	}
	
	public Person update(Person person) {
		logger.info("Update one person!");
		return person;
	}
	

}
