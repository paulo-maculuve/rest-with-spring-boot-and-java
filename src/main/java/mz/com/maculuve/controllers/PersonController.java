package mz.com.maculuve.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mz.com.maculuve.data.vo.v1.PersonVO;
import mz.com.maculuve.model.Person;
import mz.com.maculuve.services.PersonService;

@RestController
@RequestMapping("/PersonVO")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception {
		return personService.findById(id);

	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonVO> findAll() {
		return personService.findAll();

	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PersonVO create(@RequestBody PersonVO person){
		return personService.create(person);

	}
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PersonVO update(@RequestBody PersonVO person){
		return personService.update(person);

	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
		personService.delete(id);

		return ResponseEntity.noContent().build();
	}

}