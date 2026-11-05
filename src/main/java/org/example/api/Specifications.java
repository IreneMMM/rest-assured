package org.example.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.example.config.ConfigReader;

import static io.restassured.RestAssured.basic;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

public class Specifications {

	public static RequestSpecification requestSpec() {
		return requestSpec(ConfigReader.getUsername(), ConfigReader.getPassword());
	}

	public static RequestSpecification requestSpec(String username, String password) {
		return new RequestSpecBuilder()
				.setBaseUri(ConfigReader.getBaseUri())
				.setAuth(basic(username, password))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.setConfig(RestAssuredConfig.config()
				.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
				.build();
	}

	public static ResponseSpecification okResponseSpec() {
		return new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
	}

	public static ResponseSpecification clientErrorResponseSpec() {
		return new ResponseSpecBuilder()
				.expectStatusCode(allOf(greaterThanOrEqualTo(400), lessThan(500), not(401), not(403)))
				.build();
	}
}
