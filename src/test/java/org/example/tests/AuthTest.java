package org.example.tests;

import org.example.config.ConfigReader;
import org.junit.jupiter.api.Test;

public class AuthTest extends BaseTest {

	@Test
	void getApplications_wrongPassword_returnsUnauthorized() {
		api.getApplications(ConfigReader.getUsername(), "wrong_password")
				.statusCode(401);
	}

	@Test
	void getApplications_unknownUser_returnsUnauthorized() {
		api.getApplications("unknown_user", ConfigReader.getPassword())
				.statusCode(401);
	}
}
