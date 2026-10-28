package org.example.specification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.example.config.ConfigReader;

import static io.restassured.RestAssured.basic;

public class Specifications {

	public static RequestSpecification requestSpec() {
		return new RequestSpecBuilder()
				.setBaseUri(ConfigReader.get("baseUri"))
				.setAuth(basic(ConfigReader.get("username"), ConfigReader.get("password")))
				.setContentType(ContentType.JSON)
				.build();
	}

	public static ResponseSpecification responseSpec() {
		return new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.build();
	}
}
