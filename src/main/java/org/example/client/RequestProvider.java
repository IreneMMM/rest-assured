package org.example.client;

import io.restassured.response.Response;
import org.example.specification.Specifications;

import static io.restassured.RestAssured.given;

public class RequestProvider {

	public Response get(String path) {
		return given()
				.spec(Specifications.requestSpec())
				.when()
				.get(path)
				.then()
				.spec(Specifications.responseSpec())
				.extract()
				.response();
	}

	public Response getWithParam(String path, String paramName, Object value) {
		return given()
				.spec(Specifications.requestSpec())
				.pathParam(paramName, value)
				.when()
				.get(path)
				.then()
				.spec(Specifications.responseSpec())
				.extract()
				.response();
	}

	public Response post(String path, Object body) {
		return given()
				.spec(Specifications.requestSpec())
				.body(body)
				.when()
				.post(path)
				.then()
				.spec(Specifications.responseSpec())
				.extract()
				.response();
	}
}
