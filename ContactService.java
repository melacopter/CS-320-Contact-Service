package Main;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactService extends Contact{
	//In-memory data structures
	static public List<Contact> list = new ArrayList<>();
	
	//add contact
	public static Contact addContact(String contactId, String firstName, String lastName, String phoneNumber, String address) {
		//call function to check the field parameters
		contactIdCheck(contactId);
		firstNameCheck(firstName);
		lastNameCheck(lastName);
		phoneCheck(phoneNumber);
		addressCheck(address);
		
		//look for repeat id, throw exception if true
		boolean findRepeat = list.stream().anyMatch(c -> c.getContactId().equals(contactId));
		if (findRepeat == true) {throw new IllegalArgumentException("Contact ID already exists. Please try again.");}
		
		//create a new contact object
		Contact contact = new Contact(contactId, firstName, lastName, phoneNumber, address);
	
		//create stream supplier
		Supplier<Contact> contactSupplier = () -> contact;
		
		//generate an "infinite" stream for each contact made using this function
		Stream<Contact> contactStream = Stream.generate(contactSupplier).limit(1);
	
		//collect the stream into the original list
		contactStream.collect(Collectors.toCollection(()->list));	
		
		return contact;		
	}
		
	//delete contact
	public static void deleteContact(String contactId) {
		//remove from list, and since there are no other references to it, it is deleted
		list.removeIf(c -> c.getContactId().equals(contactId));	
	}
	
	//update first name
	public static void updateFirstName(String contactId, String newFirstName) {
		Contact contact = list.stream().filter(c -> c.getContactId().equals(contactId)).findFirst().get();
		contact.setFirstName(newFirstName);
	}	
	
	//update last name
	public static void updateLastName(String contactId, String newLastName) {
		Contact contact = list.stream().filter(c -> c.getContactId().equals(contactId)).findFirst().get();
		contact.setLastName(newLastName);
	}
	
	//update number
	public static void updateNumber(String contactId, String newNumber) {
		Contact contact = list.stream().filter(c -> c.getContactId().equals(contactId)).findFirst().get();
		newNumber = phoneCheck(newNumber);
		contact.setNumber(newNumber);
	}
	
	//update address
	public static void updateAddress(String contactId, String newAddress) {
		Contact contact = list.stream().filter(c -> c.getContactId().equals(contactId)).findFirst().get();
		contact.setAddress(newAddress);
	}

}
