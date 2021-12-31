package Entities;

import java.io.Serializable;

public class Customer extends Account implements Serializable{

	private static final long serialVersionUID = 1098533277300168454L;
	private String Type;

	public Customer(String userName, String password, String firstName, String lastName, String role, String iD,
			String email, String phone) {
		super(userName, password, firstName, lastName, role, iD, email, phone);
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}
}