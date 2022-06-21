package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import Main.Contact;

class ContactTest {

	@Test
	void testContact() {
		Contact contact = new Contact("1234567891", "Anon", "Anonymous", "1234567891", "123 Main Street");
		//Test object creation
		assertNotNull(contact);
		
		//Variable Assignment Test
		assertTrue(contact.getContactId().equals("1234567891"));
		assertTrue(contact.getFirstName().equals("Anon"));
		assertTrue(contact.getLastName().equals("Anonymous"));
		assertTrue(contact.getNumber().equals("(123) 456-7891"));
		assertTrue(contact.getAddress().equals("123 Main Street"));
		
		//Another Variable Assignment Test
		assertFalse(contact.getContactId().equals("1234567889"));
		assertFalse(contact.getFirstName().equals("A"));
		assertFalse(contact.getLastName().equals("A"));
		assertFalse(contact.getNumber().equals("1234567891"));
		assertFalse(contact.getAddress().equals("123 Main Avenue"));
	}
	
	@Test
	void testStringLengthExceptions() {
		//test length exception throws
		IllegalArgumentException thrown1 = Assertions.assertThrows(IllegalArgumentException.class, () -> {new Contact("1234567891", "FirstName10+Char", "Anonymous", "1234567891", "123 Main Street");});
		assertTrue(thrown1.getMessage().contains("Maximum of 10 characters."));
		IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {new Contact("1234567891", "Anon", "LastName10+Char", "1234567891", "123 Main Street");});
		assertTrue(thrown2.getMessage().contains("Maximum of 10 characters."));
		IllegalArgumentException thrown3 = Assertions.assertThrows(IllegalArgumentException.class, () -> {new Contact("1234567891", "Anon", "Anonymous", "PhoneNumberLessThan10Digits", "123 Main Street");});
		assertTrue(thrown3.getMessage().contains("Please enter a valid 10-digit phone number."));
		IllegalArgumentException thrown4 = Assertions.assertThrows(IllegalArgumentException.class, () -> {new Contact("1234567891", "Anon", "Anonymous", "PhoneNumberMoreThan10Digits: 1234567891", "123 Main Street");});
		assertTrue(thrown4.getMessage().contains("Please enter a valid 10-digit phone number."));
		IllegalArgumentException thrown5 = Assertions.assertThrows(IllegalArgumentException.class, () -> {new Contact("1234567891", "Anon", "Anonymous", "1234567891", "An address that is more than thirty characters");});
		assertTrue(thrown5.getMessage().contains("Maximum of 30 characters."));
		IllegalArgumentException thrown6 = Assertions.assertThrows(IllegalArgumentException.class, () -> {new Contact("IDLongerThan10Digits12345678910", "Anon", "Anonymous", "1234567891", "123 Main Street");});
		assertTrue(thrown6.getMessage().contains("Maximum of 10 characters."));
	}
	
	@Test
	void testBlankExceptions() {
		//test "blank" exception throw
		IllegalArgumentException thrown1 = Assertions.assertThrows(IllegalArgumentException.class, () -> {new Contact("1234567891", "", "Anonymous", "1234567891", "123 Main Street");});
		assertTrue(thrown1.getMessage().contains("Field cannot be blank."));
		IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {new Contact("1234567891", "Anon", "", "1234567891", "123 Main Street");});
		assertTrue(thrown2.getMessage().contains("Field cannot be blank."));
		IllegalArgumentException thrown3 = Assertions.assertThrows(IllegalArgumentException.class, () -> {new Contact("1234567891", "Anon", "Anonymous", "", "123 Main Street");});
		assertTrue(thrown3.getMessage().contains("Field cannot be blank."));
		IllegalArgumentException thrown4 = Assertions.assertThrows(IllegalArgumentException.class, () -> {new Contact("1234567891", "Anon", "Anonymous", "1234567891", "");});
		assertTrue(thrown4.getMessage().contains("Field cannot be blank."));
		IllegalArgumentException thrown5 = Assertions.assertThrows(IllegalArgumentException.class, () -> {new Contact("", "Anon", "Anonymous", "1234567891", "123 Main Street");});
		assertTrue(thrown5.getMessage().contains("Field cannot be blank."));
	}
}
