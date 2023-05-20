package com.ashu.police;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfficerDetails implements UserDetailsService {
	private final OfficerRepository officerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	    final Officer officer = officerRepository.findByUsername(username);
		final Officer officer = new Officer();
		if (username.equals("Astrodux")) {
			System.out.println("Officer identified");
			List<AppUserRole> appUserRoles = new ArrayList<AppUserRole>();
			appUserRoles.add(AppUserRole.ROLE_OFFICER);
			officer.setAppUserRoles(appUserRoles);
			officer.setLocation("NY");
			officer.setName("Astrodux");
			officer.setOfficerId(1);
			officer.setPassword("demo");
		} else {
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}

		return org.springframework.security.core.userdetails.User//
				.withUsername(username)//
				.password(officer.getPassword())//
				.authorities(officer.getAppUserRoles())//
				.accountExpired(false)//
				.accountLocked(false)//
				.credentialsExpired(false)//
				.disabled(false)//
				.build();
	}
}
