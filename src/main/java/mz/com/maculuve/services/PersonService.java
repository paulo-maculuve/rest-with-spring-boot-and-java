package mz.com.maculuve.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.com.maculuve.data.vo.v1.PersonVO;
import mz.com.maculuve.exceptions.ResourceNotFondExecption;
import mz.com.maculuve.mapper.MapperConfiguration;
import mz.com.maculuve.model.Person;
import mz.com.maculuve.repositories.PersonRepository;

@Service
public class PersonService {
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	private PersonRepository personRepository;
	
	
	public List<PersonVO> findAll(){
		logger.info("Finding All person");
				
		return MapperConfiguration.parseListObjects(personRepository.findAll(), PersonVO.class);
	}
	
	public PersonVO findById(Long id) {
		logger.info("Find one person!!");
		var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFondExecption("No records found this ID"));
		return MapperConfiguration.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating one person!");
		var entity = MapperConfiguration.parseObject(person, Person.class);
		var vo = MapperConfiguration.parseObject(personRepository.save(entity), PersonVO.class);
		return vo;
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Updating one person!");
		
		var entity = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFondExecption("No records found this ID"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo = MapperConfiguration.parseObject(personRepository.save(entity), PersonVO.class);
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person!");
		
		var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFondExecption("No records found this ID"));
		personRepository.delete(entity);
	}
	

}
