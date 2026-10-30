package org.example.tests;

import org.example.api.Specifications;
import org.example.data.ChangeStatusRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;

public class ApplicationTest extends BaseTest {

	@Test
	void getApplications_containsCreatedApplication() {
		int applicationId = createApplication();

		api.getApplications()
				.spec(Specifications.okResponseSpec())
				.body("data.size()", greaterThan(0))
				.body("data.applicationid", hasItem(applicationId))
				.body("data.applicationid", everyItem(notNullValue()))
				.body("data.statusofapplication", everyItem(notNullValue()));
	}

	@Test
	void getApplStatus_createdApplication_returnsStatus() {
		int applicationId = createApplication();

		api.getApplStatus(applicationId)
				.spec(Specifications.okResponseSpec())
				.body("data.statusofapplication", notNullValue())
				.body("data.kindofapplication", notNullValue())
				.body("data.dateofapplication", notNullValue());
	}

	@ParameterizedTest
	@ValueSource(strings = {"0", "-1", "2147483647"})
	void getApplStatus_unknownId_returnsNotFound(String applicationId) {
		api.getApplStatus(applicationId)
				.spec(Specifications.okResponseSpec())
				.body("code", equalTo(404))
				.body("message", equalTo("Application not found"));
	}

	@Test
	void requestProcess_approve_changesStatus() {
		int applicationId = createApplication();
		int staffId = createAdmin();

		api.requestProcess(new ChangeStatusRequest(applicationId, staffId, "approved"))
				.spec(Specifications.okResponseSpec())
				.body("data.applicationid", equalTo(applicationId))
				.body("data.statusofapplication", equalTo("approved"));

		api.getApplStatus(applicationId)
				.spec(Specifications.okResponseSpec())
				.body("data.statusofapplication", equalTo("approved"));
	}
}
