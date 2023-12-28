package mz.com.maculuve.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mz.com.maculuve.data.vo.v1.security.AccountCredentialsVO;
import mz.com.maculuve.data.vo.v1.security.TokenVO;
import mz.com.maculuve.repositories.UserRepository;
import mz.com.maculuve.security.jwt.JwtTokenProvider;

@Service
public class AuthService {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	

	@Autowired
	private UserRepository userRepository;

	@SuppressWarnings("rawtypes")
	public ResponseEntity signIn(AccountCredentialsVO accountCredentialsVO) {
		try {
			var usarname = accountCredentialsVO.getUsername();
			var password = accountCredentialsVO.getPassword();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usarname, password));
			var user = userRepository.findByUsername(usarname);

			var tokenResponse = new TokenVO();
			if (user != null) {
				tokenResponse = jwtTokenProvider.createAccessToken(usarname, user.getRole());
			} else {
				throw new UsernameNotFoundException("Username " + usarname + " not found!");
			}

			return ResponseEntity.ok(tokenResponse);

		} catch (Exception e) {
			throw new BadCredentialsException("Invalid username or password supplied!");
		}
	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity refreshToken(String username, String refreshToken) {
		var user = userRepository.findByUsername(username);

		var tokenResponse = new TokenVO();
		if (user != null) {
			tokenResponse = jwtTokenProvider.refreshToken(refreshToken);
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found!");
		}
		return ResponseEntity.ok(tokenResponse);
	}
}
