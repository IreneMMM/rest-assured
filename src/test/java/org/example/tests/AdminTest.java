package org.example.tests;

import io.restassured.response.Response;
import org.example.client.Endpoints;
import org.example.dto.AdminResponse;
import org.example.testdata.TestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AdminTest extends BaseTest {

	@Test
	void sendAdminRequest_returnsStaffId() {
		String suffix = String.valueOf(System.currentTimeMillis());

		Response response = api.post(Endpoints.SEND_ADMIN_REQUEST, TestData.admin(suffix));
		AdminResponse body = response.as(AdminResponse.class);

		assertEquals(200, response.statusCode());
		assertNotNull(body.staffid);
	}
}
