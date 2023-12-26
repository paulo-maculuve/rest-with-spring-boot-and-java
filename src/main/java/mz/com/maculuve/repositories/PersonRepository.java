package mz.com.maculuve.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mz.com.maculuve.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
