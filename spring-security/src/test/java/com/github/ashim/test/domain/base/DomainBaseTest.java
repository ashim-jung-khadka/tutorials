package com.github.ashim.test.domain.base;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Before;
import org.junit.Test;

import com.github.ashim.persistence.common.DomainBase;

public class DomainBaseTest {

	private String TO_STRING;
	private DomainBase domainBase;

	@Before
	public void setUp() {
		domainBase = new DomainBase();
		TO_STRING = ReflectionToStringBuilder.toString(domainBase);
	}

	@Test
	public void callingDomainBaseToStringReturnsExpectedObject() {
		assertThat(domainBase.toString(), is(TO_STRING));
	}

}
