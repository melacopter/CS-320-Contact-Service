package Main;

public class Contact {
	private String contactId;
	public String firstName;
	public String lastName;
	public String phoneNumber;
	public String address;
	
	//Default constructor
	public Contact() {}
	
	//Constructor
	public Contact(String contactId, String firstName, String lastName, String phoneNumber, String address) {
		//call function to check the field parameters
		contactIdCheck(contactId);
		firstNameCheck(firstName);
		lastNameCheck(lastName);
		addressCheck(address);
		//assign variables
		this.contactId = contactId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneCheck(phoneNumber);
		this.address = address;
	}
	
	//get methods
	public String getContactId() {return this.contactId;}
	public String getFirstName() {return this.firstName;}
	public String getLastName() {return this.lastName;}
	public String getAddress() {return this.address;}
	public String getNumber() {return this.phoneNumber;}
	
	//set methods
	public void setFirstName(String firstName) {firstNameCheck(firstName); this.firstName = firstName;}
	public void setLastName(String lastName) {lastNameCheck(lastName); this.lastName = lastName;}
	public void setAddress(String address) {addressCheck(address); this.address = address;}
	public void setNumber(String phoneNumber) {phoneCheck(phoneNumber); this.phoneNumber = phoneNumber;}
		
	//field checks
	//contact Id
	public static void contactIdCheck(String contactId) {
		if (contactId.length()>10) {throw new IllegalArgumentException("Maximum of 10 characters.");}
		if (contactId == "") {throw new IllegalArgumentException("Field cannot be blank.");}
	}
	
	//first name
	public static void firstNameCheck(String firstName) {
		if (firstName.length()>10) {throw new IllegalArgumentException("Maximum of 10 characters.");}
		if (firstName == "") {throw new IllegalArgumentException("Field cannot be blank.");}
	}
	
	//last name
	public static void lastNameCheck(String lastName) {
		if (lastName.length()>10) {throw new IllegalArgumentException("Maximum of 10 characters.");}
		if (lastName == "") {throw new IllegalArgumentException("Field cannot be blank.");}
	}
	
	//address
	public static void addressCheck(String address) {
		if (address.length()>30) {throw new IllegalArgumentException("Maximum of 30 characters.");}
		if (address == "") {throw new IllegalArgumentException("Field cannot be blank.");}
	}
	
	//phone
	public static String phoneCheck(String phoneNumber) {
		if (phoneNumber == "") {throw new IllegalArgumentException("Field cannot be blank.");}
		//phone number check and normalization
		String numbersOnly = phoneNumber.replaceAll("[^0-9]", ""); //filter out all non-digit characters
		if (numbersOnly.length()>10 || numbersOnly.length()<10) {throw new IllegalArgumentException("Please enter a valid 10-digit phone number.");} //check length of string, exception
		else { //normalize phone number data
			String areaCode = numbersOnly.substring(0, 3);
			String firstThree = numbersOnly.substring(3, 6);
			String lastFour = numbersOnly.substring(6, 10);
			String formattedNumber = String.format("(%s) %s-%s", areaCode, firstThree, lastFour);
			phoneNumber = formattedNumber;	
		}
		return phoneNumber;
	}
}
