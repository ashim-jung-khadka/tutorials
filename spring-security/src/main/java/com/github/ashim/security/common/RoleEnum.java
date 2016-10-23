package com.github.ashim.security.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum RoleEnum {

	ADMIN("ROLE_ADMIN", "ADMIN", "USER"), USER("ROLE_USER", "USER");

	private static Map<String, RoleEnum> roleMap = new HashMap<>();

	private String roleName;
	private List<String> privileges;

	static {
		for (RoleEnum state : RoleEnum.values()) {
			roleMap.put(state.roleName, state);
		}
	}

	public static List<String> getRoles(String roleName) {
		return roleMap.get(roleName).privileges;
	}

	private RoleEnum(String roleName, String... privilege) {
		this.roleName = roleName;
		this.privileges = Arrays.asList(privilege);
	}

}