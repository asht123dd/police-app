package com.ashu.police;

import org.springframework.security.core.GrantedAuthority;

public enum AppUserRole implements GrantedAuthority {
	ROLE_USER, ROLE_OFFICER;

	public String getAuthority() {
		return name();
	}
}
