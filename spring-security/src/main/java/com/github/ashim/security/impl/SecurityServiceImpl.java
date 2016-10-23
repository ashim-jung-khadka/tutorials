package com.github.ashim.security.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.github.ashim.security.common.RoleEnum;
import com.github.ashim.security.service.SecurityService;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Override
	public Boolean hasProtectedAccess(String roleName) {
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();

		for (GrantedAuthority authority : authorities) {
			List<String> roles = RoleEnum.getRoles(authority.getAuthority());
			if (roles != null && roles.contains(roleName)) {
				return true;
			}
		}

		return false;
	}

}