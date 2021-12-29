package Entities;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 8530287836358627004L;
	private String userName, password, isLoggedIn, role, id, firstN, lastN, email, phone;
	private homeBranches homeBranch;

	public User(String role, String id, String firstN, String lastN, homeBranches homeBranch, String userName,
			String password, String isLoggedIn) {
		this.role = role;
		this.id = id;
		this.firstN = firstN;
		this.lastN = lastN;
		this.homeBranch = homeBranch;
		this.userName = userName;
		this.password = password;
		// setUserName(userName);
		// setPassword(password);
		this.isLoggedIn = "0";
	}

	public User() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstN() {
		return firstN;
	}

	public void setFirstN(String firstN) {
		this.firstN = firstN;
	}

	public String getLastN() {
		return lastN;
	}

	public void setLastN(String lastN) {
		this.lastN = lastN;
	}

	public homeBranches getHomeBranch() {
		return homeBranch;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsLoggedIn() {
		return isLoggedIn;
	}

	public void setIsLoggedIn(String isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}