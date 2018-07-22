package LoginSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class DataValidatorTest {

	@Test
	void LoginTest() {
		assertFalse(DataValidator.ValidateLogin("mm"));
		assertTrue(DataValidator.ValidateLogin("mmatwijcz"));
	}

	@Test
	void PasswordTest() {
		assertFalse(DataValidator.ValidatePassword("admin"));
		assertFalse(DataValidator.ValidatePassword("admin1"));
		assertFalse(DataValidator.ValidatePassword("Admin1"));
		assertTrue(DataValidator.ValidatePassword("$Admin21x"));
	}
	
	@Test
	void EmailTest() {
		assertFalse(DataValidator.ValidateEmailAddress("mmatwijcz@gmail"));
		assertFalse(DataValidator.ValidateEmailAddress("m@.pl"));
		assertFalse(DataValidator.ValidateEmailAddress("mmatwijcz.com"));
		assertTrue(DataValidator.ValidateEmailAddress("mmatwijcz@gmail.com"));
	}
	
	@Test
	void PhoneNumberTest() {
		assertFalse(DataValidator.ValidatePhoneNumber("512gk89sx0123"));
		assertTrue(DataValidator.ValidatePhoneNumber("886804532"));
	}
}
