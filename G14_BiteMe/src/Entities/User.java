package Entities;

public class User {

	private String userName;
	private String Password;
	private String ID;
	private String firstName;
	private String familyName;
	private String Email;
	private String TelephoneNumber;
	private String role;

	public User(String userName, String Password, String ID, String firstName, String familyName, String Email,
			String role, String TelephoneNumber) {
		this.setUserName(userName);
		this.setPassword(Password);
		this.setID(ID);
		this.setFirstName(firstName);
		this.setFamilyName(familyName);
		this.setEmail(Email);
		this.setTelephoneNumber(TelephoneNumber);
		this.setRole(role);

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getTelephoneNumber() {
		return TelephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		TelephoneNumber = telephoneNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

}
