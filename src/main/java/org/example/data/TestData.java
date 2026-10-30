package org.example.data;

import java.util.Random;

public class TestData {

	private static final Random random = new Random();

	public static UserRequest validUser() {
		UserRequest user = new UserRequest();
		user.mode = "wedding";
		user.personalLastName = "Ivanov";
		user.personalFirstName = "Ivan";
		user.personalMiddleName = "Ivanovich";
		user.personalPhoneNumber = randomPhone();
		user.personalNumberOfPassport = randomPassport("MP");
		user.personalAddress = "Minsk, Lenina 1";
		user.citizenLastName = "Petrov";
		user.citizenFirstName = "Petr";
		user.citizenMiddleName = "Petrovich";
		user.citizenBirthDate = "1995-05-12";
		user.citizenNumberOfPassport = randomPassport("MC");
		user.citizenGender = "male";
		user.citizenAddress = "Minsk, Lenina 2";
		user.dateOfMarriage = "2024-06-01";
		user.newLastName = "Ivanova";
		user.anotherPersonLastName = "Ivanov";
		user.anotherPersonFirstName = "Ivan";
		user.anotherPersonMiddleName = "Ivanovich";
		user.birth_of_anotoherPerson = "1990-01-01";
		user.anotherPersonPassport = randomPassport("MA");
		return user;
	}

	public static AdminRequest validAdmin() {
		AdminRequest admin = new AdminRequest();
		admin.dateofbirth = "1990-01-01";
		admin.personalFirstName = "Ivan";
		admin.personalLastName = "Ivanov";
		admin.personalMiddleName = "Ivanovich";
		admin.personalNumberOfPassport = randomPassport("AD");
		admin.personalPhoneNumber = randomPhone();
		return admin;
	}

	private static String randomPassport(String prefix) {
		return prefix + (10000 + random.nextInt(90000));
	}

	private static String randomPhone() {
		return "8029" + (1000000 + random.nextInt(9000000));
	}
}
