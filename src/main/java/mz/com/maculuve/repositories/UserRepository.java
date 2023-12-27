package mz.com.maculuve.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mz.com.maculuve.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User WHERE u.userName =:userName")
	User findUsername(@Param("username") String userName);

}
