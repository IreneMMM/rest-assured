package org.example.tests;

import io.restassured.response.Response;
import org.example.client.Endpoints;
import org.example.dto.UserResponse;
import org.example.testdata.TestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest extends BaseTest {

	@Test
	void sendUserRequest_returnsIds() {
		String suffix = String.valueOf(System.currentTimeMillis());

		Response response = api.post(Endpoints.SEND_USER_REQUEST, TestData.user(suffix));
		UserResponse body = response.as(UserResponse.class);

		assertEquals(200, response.statusCode());
		assertNotNull(body.applicantid);
		assertNotNull(body.applicationid);
		assertNotNull(body.citizenid);
		assertNotNull(body.merrigecertificateid);
	}
}
