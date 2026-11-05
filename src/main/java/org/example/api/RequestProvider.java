package org.example.api;

import io.restassured.response.ValidatableResponse;
import org.example.data.AdminRequest;
import org.example.data.ChangeStatusRequest;
import org.example.data.UserRequest;

import static io.restassured.RestAssured.given;

public class RequestProvider {

	public ValidatableResponse sendUserRequest(UserRequest user) {
		return given()
				.spec(Specifications.requestSpec())
				.body(user)
				.when()
				.post(Endpoints.SEND_USER_REQUEST)
				.then();
	}

	public ValidatableResponse sendAdminRequest(AdminRequest admin) {
		return given()
				.spec(Specifications.requestSpec())
				.body(admin)
				.when()
				.post(Endpoints.SEND_ADMIN_REQUEST)
				.then();
	}

	public ValidatableResponse getApplications() {
		return given()
				.spec(Specifications.requestSpec())
				.when()
				.get(Endpoints.GET_APPLICATIONS)
				.then();
	}

	public ValidatableResponse getApplications(String username, String password) {
		return given()
				.spec(Specifications.requestSpec(username, password))
				.when()
				.get(Endpoints.GET_APPLICATIONS)
				.then();
	}

	public ValidatableResponse getApplStatus(Object applicationId) {
		return given()
				.spec(Specifications.requestSpec())
				.pathParam("applicationId", applicationId)
				.when()
				.get(Endpoints.GET_APPL_STATUS)
				.then();
	}

	public ValidatableResponse requestProcess(ChangeStatusRequest change) {
		return given()
				.spec(Specifications.requestSpec())
				.body(change)
				.when()
				.post(Endpoints.REQUEST_PROCESS)
				.then();
	}
}
