package mz.com.maculuve.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mz.com.maculuve.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{
	private Logger logger = Logger.getLogger(UserService.class.getName());

	@Autowired
	UserRepository userRepository;
	

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Finding username " + username);
		var user = userRepository.findByUsername(username);
		if(user != null) {
			return user;
		}else {
			throw new UsernameNotFoundException("Username " + username + "not found!");
		}
	}

	
}
