package org.example.tests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.example.client.RequestProvider;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

	protected final RequestProvider api = new RequestProvider();

	@BeforeAll
	static void setUp() {
		RestAssured.replaceFiltersWith(new RequestLoggingFilter(), new ResponseLoggingFilter());
	}
}
