package org.example.tests;

import org.example.api.RequestProvider;
import org.example.api.Specifications;
import org.example.data.TestData;

public abstract class BaseTest {

	protected RequestProvider api = new RequestProvider();

	protected int createApplication() {
		return api.sendUserRequest(TestData.validUser())
				.spec(Specifications.okResponseSpec())
				.extract()
				.path("data.applicationid");
	}

	protected int createAdmin() {
		return api.sendAdminRequest(TestData.validAdmin())
				.spec(Specifications.okResponseSpec())
				.extract()
				.path("data.staffid");
	}
}
