package com.github.ashim.test.model.security;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.github.ashim.security.model.UserCredential;

public class UserCredentialTest {

	private final Integer ID = 1;
	private final String USERNAME = "username";
	private final String PASSWORD = "password";
	private final String EMAIL = "user@domain.tld";
	private final Date LAST_PASSWORD_RESET = new Date();
	private final Collection<? extends GrantedAuthority> AUTHORITIES = AuthorityUtils
			.commaSeparatedStringToAuthorityList("user,admin");
	private final Boolean ACCOUNT_NON_EXPIRED = false;
	private final Boolean ACCOUNT_NON_LOCKED = false;
	private final Boolean CREDENTIALS_NON_EXPIRED = false;
	private final Boolean ENABLED = false;

	@Test
	public void callingUserCredentialConstructorWithoutParametersCreatesExpectedObject() {
		UserCredential userCredential = new UserCredential();

		assertNull(userCredential.getId());
		assertNull(userCredential.getUsername());
		assertNull(userCredential.getPassword());
		assertNull(userCredential.getEmail());
		assertNull(userCredential.getLastPasswordReset());
		assertNull(userCredential.getAuthorities());
		assertTrue(userCredential.getAccountNonExpired());
		assertTrue(userCredential.getAccountNonLocked());
		assertTrue(userCredential.getCredentialsNonExpired());
		assertTrue(userCredential.getEnabled());
	}

	@Test
	public void callingUserCredentialConstructorWithParametersCreatesExpectedObject() {
		UserCredential userCredential = new UserCredential(ID, USERNAME, PASSWORD, EMAIL, LAST_PASSWORD_RESET,
				AUTHORITIES);

		assertThat(userCredential.getId(), is(ID));
		assertThat(userCredential.getUsername(), is(USERNAME));
		assertThat(userCredential.getPassword(), is(PASSWORD));
		assertThat(userCredential.getEmail(), is(EMAIL));
		assertThat(userCredential.getLastPasswordReset(), is(LAST_PASSWORD_RESET));
		assertEquals(userCredential.getAuthorities(), AUTHORITIES);
		assertTrue(userCredential.getAccountNonExpired());
		assertTrue(userCredential.getAccountNonLocked());
		assertTrue(userCredential.getCredentialsNonExpired());
		assertTrue(userCredential.getEnabled());
	}

	@Test
	public void callingUserCredentialGettersAndSettersReturnsExpectedObjects() {
		UserCredential userCredential = new UserCredential();

		userCredential.setId(ID);
		userCredential.setUsername(USERNAME);
		userCredential.setPassword(PASSWORD);
		userCredential.setEmail(EMAIL);
		userCredential.setLastPasswordReset(LAST_PASSWORD_RESET);
		userCredential.setAuthorities(AUTHORITIES);
		userCredential.setAccountNonExpired(ACCOUNT_NON_EXPIRED);
		userCredential.setAccountNonLocked(ACCOUNT_NON_LOCKED);
		userCredential.setCredentialsNonExpired(CREDENTIALS_NON_EXPIRED);
		userCredential.setEnabled(ENABLED);

		assertThat(userCredential.getId(), is(ID));
		assertThat(userCredential.getUsername(), is(USERNAME));
		assertThat(userCredential.getPassword(), is(PASSWORD));
		assertThat(userCredential.getEmail(), is(EMAIL));
		assertThat(userCredential.getLastPasswordReset(), is(LAST_PASSWORD_RESET));
		assertEquals(userCredential.getAuthorities(), AUTHORITIES);
		assertThat(userCredential.getAccountNonExpired(), is(ACCOUNT_NON_EXPIRED));
		assertThat(userCredential.getAccountNonLocked(), is(ACCOUNT_NON_LOCKED));
		assertThat(userCredential.getCredentialsNonExpired(), is(CREDENTIALS_NON_EXPIRED));
		assertThat(userCredential.getEnabled(), is(ENABLED));
	}

}
