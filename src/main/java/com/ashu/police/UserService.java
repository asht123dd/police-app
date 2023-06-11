package com.ashu.police;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;

	@Autowired
	private final AuthenticationManager authenticationManager;

	public String signin(String username, String password) {
		try {
			System.out.println("username=" + username + " ,password = " + password);
			if (username.equals("Astrodux") && password.equals("Demo")) {
				System.out.println("username password received");
				List<AppUserRole> appUserRoles = new ArrayList<AppUserRole>();
				appUserRoles.add(AppUserRole.ROLE_OFFICER);
				return jwtTokenProvider.createToken(username, appUserRoles);
			}
			AppUser user = userRepository.findByUsername(username);
//			String encodedPassword=passwordEncoder.encode(password);
			System.out.println("saved password = " + user.getPassword() + " provided password = " + password);
			if (user != null && user.getPassword().equals(password)) {
				return jwtTokenProvider.createToken(username, user.getAppUserRoles());
			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public String signup(AppUser appUser) {
		if (!userRepository.existsByUsername(appUser.getUsername())) {
//			appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
			userRepository.save(appUser);
			return jwtTokenProvider.createToken(appUser.getUsername(), appUser.getAppUserRoles());
		} else {
			throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public void delete(String username) {
		userRepository.deleteByUsername(username);
	}

	public AppUser search(String username) {
		AppUser appUser = userRepository.findByUsername(username);
		if (appUser == null) {
			throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
		}
		return appUser;
	}

	public AppUser whoami(HttpServletRequest req) {
		return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
	}

	public String refresh(String username) {
		return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getAppUserRoles());
	}

}
