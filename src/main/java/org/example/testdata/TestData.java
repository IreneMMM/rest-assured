package org.example.testdata;

import org.example.dto.AdminRequest;
import org.example.dto.ChangeStatusRequest;
import org.example.dto.UserRequest;

public final class TestData {

	private TestData() {
	}

	public static UserRequest user(String suffix) {
		UserRequest body = new UserRequest();
		body.mode = "marriage";
		body.personalLastName = "Ivanov";
		body.personalMiddleName = "Ivanovich";
		body.personalPhoneNumber = "375" + suffix.substring(suffix.length() - 9);
		body.personalNumberOfPassport = "MP" + suffix;
		body.citizenLastName = "Petrova";
		body.citizenFirstName = "Anna";
		body.citizenMiddleName = "Petrovna";
		body.citizenBirthDate = "1995-05-12";
		body.citizenNumberOfPassport = "MC" + suffix;
		body.citizenGender = "female";
		body.dateOfMarriage = "2024-06-01";
		body.newLastName = "Ivanova";
		body.anotherPersonLastName = "Ivanov";
		body.anotherPersonFirstName = "Ivan";
		body.anotherPersonMiddleName = "Ivanovich";
		body.birth_of_anotoherPerson = "1990-01-01";
		body.anotherPersonPassport = "MA" + suffix;
		body.birth_place = "Minsk";
		body.birth_mother = "Petrova Olga";
		body.birth_father = "Petrov Petr";
		body.death_dateOfDeath = "2020-01-01";
		body.death_placeOfDeath = "Minsk";
		return body;
	}

	public static AdminRequest admin(String suffix) {
		AdminRequest body = new AdminRequest();
		body.dateofbirth = "1990-01-01";
		body.personalFirstName = "Ivan";
		body.personalLastName = "Ivanov";
		body.personalMiddleName = "Ivanovich";
		body.personalNumberOfPassport = "AD" + suffix;
		body.personalPhoneNumber = "375" + suffix.substring(suffix.length() - 9);
		return body;
	}

	public static ChangeStatusRequest statusChange(int applicationId, int staffId) {
		ChangeStatusRequest body = new ChangeStatusRequest();
		body.applId = applicationId;
		body.staffid = staffId;
		body.action = "approve";
		return body;
	}
}
