package Test;



import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Main.Contact;

import static Main.ContactService.*;



class ContactServiceTest {
	
	@Test
	void testStringLengthsInAddContact() {
		//test length exception throws
		IllegalArgumentException thrown1 = Assertions.assertThrows(IllegalArgumentException.class, () -> {addContact("ContactId10+Char", "Anon", "Anonymous", "1234567891", "123 Main Street");});
		assertTrue(thrown1.getMessage().contains("Maximum of 10 characters."));
		IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {addContact("1234567891", "FirstName10+Char", "Anonymous", "1234567891", "123 Main Street");});
		assertTrue(thrown2.getMessage().contains("Maximum of 10 characters."));
		IllegalArgumentException thrown3 = Assertions.assertThrows(IllegalArgumentException.class, () -> {addContact("1234567891", "Anon", "LastName10+Char", "1234567891", "123 Main Street");});
		assertTrue(thrown3.getMessage().contains("Maximum of 10 characters."));
		IllegalArgumentException thrown4 = Assertions.assertThrows(IllegalArgumentException.class, () -> {addContact("1234567891", "Anon", "Anonymous", "PhoneNumberLessThan10Digits", "123 Main Street");});
		assertTrue(thrown4.getMessage().contains("Please enter a valid 10-digit phone number."));
		IllegalArgumentException thrown5 = Assertions.assertThrows(IllegalArgumentException.class, () -> {addContact("1234567891", "Anon", "Anonymous", "PhoneNumberMoreThan10Digits: 1234567891", "123 Main Street");});
		assertTrue(thrown5.getMessage().contains("Please enter a valid 10-digit phone number."));
		IllegalArgumentException thrown6 = Assertions.assertThrows(IllegalArgumentException.class, () -> {addContact("1234567891", "Anon", "Anonymous", "1234567891", "An address that is more than thirty characters");});
		assertTrue(thrown6.getMessage().contains("Maximum of 30 characters."));
	}
	
	@Test
	void testBlankInAddContact() {
		//test "blank" exception throw
		IllegalArgumentException thrown1 = Assertions.assertThrows(IllegalArgumentException.class, () -> {addContact("1234567891", "", "Anonymous", "1234567891", "123 Main Street");});
		assertTrue(thrown1.getMessage().contains("Field cannot be blank."));
		IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {addContact("1234567891", "Anon", "", "1234567891", "123 Main Street");});
		assertTrue(thrown2.getMessage().contains("Field cannot be blank."));
		IllegalArgumentException thrown3 = Assertions.assertThrows(IllegalArgumentException.class, () -> {addContact("1234567891", "Anon", "Anonymous", "", "123 Main Street");});
		assertTrue(thrown3.getMessage().contains("Field cannot be blank."));
		IllegalArgumentException thrown4 = Assertions.assertThrows(IllegalArgumentException.class, () -> {addContact("1234567891", "Anon", "Anonymous", "1234567891", "");});
		assertTrue(thrown4.getMessage().contains("Field cannot be blank."));
		IllegalArgumentException thrown5 = Assertions.assertThrows(IllegalArgumentException.class, () -> {addContact("", "Anon", "Anonymous", "1234567891", "123 Main Street");});
		assertTrue(thrown5.getMessage().contains("Field cannot be blank."));
	}

	@Test
	void testContactUpdates() {
		//populate the list
		Contact contact1 = addContact("1234567891", "A", "A", "(123)456-7811", "123 Main Street");
		
		//assign the randomly generated contact Id to a usable variable
		String contactId = contact1.getContactId();
		
		//Assert that the changes have been made will follow the rules
		//first name
		updateFirstName(contactId, "Bob");
		Assertions.assertTrue(contact1.getFirstName().equals("Bob"));
		//if the updated name is blank, an exception is thrown
		IllegalArgumentException thrown1 = Assertions.assertThrows(IllegalArgumentException.class, () -> {updateFirstName(contactId, "");});
		assertTrue(thrown1.getMessage().contains("Field cannot be blank."));
		//if the updated name is longer than 10 characters, an exception is thrown
		IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {updateFirstName(contactId, "FirstName10+Characters");});
		assertTrue(thrown2.getMessage().contains("Maximum of 10 characters."));
		
		//last name
		updateLastName(contactId, "Lee");
		Assertions.assertTrue(contact1.getLastName().equals("Lee"));
		//if the updated name is blank, an exception is thrown
		IllegalArgumentException thrown3 = Assertions.assertThrows(IllegalArgumentException.class, () -> {updateLastName(contactId, "");});
		assertTrue(thrown3.getMessage().contains("Field cannot be blank."));
		//if the updated name is longer than 10 characters, an exception is thrown
		IllegalArgumentException thrown4 = Assertions.assertThrows(IllegalArgumentException.class, () -> {updateLastName(contactId, "LastName10+Characters");});
		assertTrue(thrown4.getMessage().contains("Maximum of 10 characters."));
		
		//phone
		updateNumber(contactId, "9871253125");
		Assertions.assertTrue(contact1.getNumber().equals("(987) 125-3125"));
		//if the updated number is blank, an exception is thrown
		IllegalArgumentException thrown5 = Assertions.assertThrows(IllegalArgumentException.class, () -> {updateNumber(contactId, "");});
		assertTrue(thrown5.getMessage().contains("Field cannot be blank."));
		//if the updated number is shorter or longer than 10 characters, an exception is thrown
		IllegalArgumentException thrown6 = Assertions.assertThrows(IllegalArgumentException.class, () -> {updateNumber(contactId, "NumberMoreThan10+Digits1234567891");});
		assertTrue(thrown6.getMessage().contains("Please enter a valid 10-digit phone number."));
		IllegalArgumentException thrown7 = Assertions.assertThrows(IllegalArgumentException.class, () -> {updateNumber(contactId, "NumberLessThan10+Digits");});
		assertTrue(thrown7.getMessage().contains("Please enter a valid 10-digit phone number."));
		
		//address
		updateAddress(contactId, "123 Main Avenue Boulevard");
		Assertions.assertTrue(contact1.getAddress().equals("123 Main Avenue Boulevard"));
	}
	
	@Test
	void testContactDeletion() {
		//populate the list
		addContact("1234567892", "A", "A", "(123)456-7811", "123 Main Street");
		addContact("1234567893", "A", "A", "(123)456-7811", "123 Main Street");
		
		//delete the object
		deleteContact("1234567891");
		
		//search for the deleted contact in the list
		boolean findContact = list.stream().anyMatch(c -> c.getContactId().equals("1234567891"));
		
		//prove that the element has been removed
		Assertions.assertFalse(findContact);
	}
	
	@Test
	void testContactIdSimilarity() {
		//add a contact
		addContact("1234567894", "A", "A", "(123)456-7811", "123 Main Street");
		
		//test throw for adding a contact with the same ID
		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {addContact("1234567894", "A", "A", "(123)456-7811", "123 Main Street");});		
		assertTrue(thrown.getMessage().contains("Contact ID already exists. Please try again."));
	}
}
