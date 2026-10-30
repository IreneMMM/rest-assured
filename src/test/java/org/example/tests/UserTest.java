package org.example.tests;

import org.example.api.Specifications;
import org.example.data.TestData;
import org.example.data.UserRequest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

public class UserTest extends BaseTest {

	@Test
	void sendUserRequest_validData_returnsIds() {
		api.sendUserRequest(TestData.validUser())
				.spec(Specifications.okResponseSpec())
				.body("data.applicationid", greaterThan(0))
				.body("data.applicantid", greaterThan(0))
				.body("data.citizenid", greaterThan(0))
				.body("data.merrigecertificateid", notNullValue());
	}

	@Test
	void sendUserRequest_withoutLastName_returnsClientError() {
		UserRequest user = TestData.validUser();
		user.personalLastName = null;

		api.sendUserRequest(user)
				.spec(Specifications.clientErrorResponseSpec());
	}

	@Test
	void sendUserRequest_unknownMode_returnsClientError() {
		UserRequest user = TestData.validUser();
		user.mode = "unknown_mode";

		api.sendUserRequest(user)
				.spec(Specifications.clientErrorResponseSpec());
	}
}
