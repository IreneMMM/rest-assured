package org.example.tests;

import org.example.api.Specifications;
import org.example.data.AdminRequest;
import org.example.data.TestData;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AdminTest extends BaseTest {

	@Test
	void sendAdminRequest_validData_returnsStaffId() {
		api.sendAdminRequest(TestData.validAdmin())
				.spec(Specifications.okResponseSpec())
				.body("data.staffid", greaterThan(0));
	}

	@Test
	void sendAdminRequest_twoRequests_returnDifferentStaffIds() {
		int firstId = createAdmin();
		int secondId = createAdmin();

		assertNotEquals(firstId, secondId, "У двух сотрудников не может быть одинаковый staffid");
	}

	@Test
	void sendAdminRequest_withoutFirstName_returnsClientError() {
		AdminRequest admin = TestData.validAdmin();
		admin.personalFirstName = null;

		api.sendAdminRequest(admin)
				.spec(Specifications.clientErrorResponseSpec());
	}
}
