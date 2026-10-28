package org.example.tests;

import io.restassured.response.Response;
import org.example.client.Endpoints;
import org.example.dto.AdminResponse;
import org.example.dto.ApplicationResponse;
import org.example.dto.ApplicationStatusResponse;
import org.example.dto.UserResponse;
import org.example.testdata.TestData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplicationTest extends BaseTest {

	@Test
	void getApplications_containsCreatedApplication() {
		int applicationId = createUser().applicationid;

		Response response = api.get(Endpoints.GET_APPLICATIONS);

		assertEquals(200, response.statusCode());
		assertTrue(containsApplication(response, applicationId));
	}

	@Test
	void getApplStatus_returnsCreatedApplication() {
		UserResponse created = createUser();

		Response response = api.getWithParam(
				Endpoints.GET_APPL_STATUS, "applicationId", created.applicationid);
		ApplicationStatusResponse body = response.as(ApplicationStatusResponse.class);

		assertEquals(200, response.statusCode());
		assertEquals(created.applicantid, body.applicantid);
		assertNotNull(body.statusofapplication);
	}

	@Test
	void requestProcess_returnsUpdatedApplication() {
		int applicationId = createUser().applicationid;
		int staffId = createAdmin().staffid;

		Response response = api.post(
				Endpoints.REQUEST_PROCESS,
				TestData.statusChange(applicationId, staffId));
		ApplicationResponse body = response.as(ApplicationResponse.class);

		assertEquals(200, response.statusCode());
		assertEquals(Integer.valueOf(applicationId), body.applicationid);
		assertEquals(Integer.valueOf(staffId), body.staffid);
	}

	private UserResponse createUser() {
		String suffix = String.valueOf(System.currentTimeMillis());
		Response response = api.post(Endpoints.SEND_USER_REQUEST, TestData.user(suffix));
		assertEquals(200, response.statusCode());
		return response.as(UserResponse.class);
	}

	private AdminResponse createAdmin() {
		String suffix = String.valueOf(System.currentTimeMillis());
		Response response = api.post(Endpoints.SEND_ADMIN_REQUEST, TestData.admin(suffix));
		assertEquals(200, response.statusCode());
		return response.as(AdminResponse.class);
	}

	private boolean containsApplication(Response response, int applicationId) {
		String raw = response.asString().trim();
		if (raw.startsWith("[")) {
			List<ApplicationResponse> applications = response.jsonPath().getList("$", ApplicationResponse.class);
			return applications.stream()
					.anyMatch(item -> Integer.valueOf(applicationId).equals(item.applicationid));
		}
		return Integer.valueOf(applicationId).equals(response.as(ApplicationResponse.class).applicationid);
	}
}
