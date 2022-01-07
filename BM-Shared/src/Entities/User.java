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
		this.isLoggedIn = isLoggedIn;
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

	/*
	 * private String userName,password,isLoggedIn,role,id,firstN,lastN,email,phone;
	 * private homeBranches homeBranch;
	 */
	public void PrintUser() {
		System.out.println("User Name : " + userName);
		System.out.println("password :" + password);
		System.out.println("Role : " + role);
		System.out.println("id: " + id);
		System.out.println("First Name: " + firstN);
		System.out.println("Last Name: " + lastN);
		System.out.println("String Branch :" + homeBranches.BranchToString(homeBranch));
		System.out.println("home Branch: " + homeBranch);
	}

//only for test use
	@Override
	public boolean equals(Object obj) {
		User u = (User) obj;
		if (u.getUserName().equals(this.getUserName()))
			if (u.getPassword().equals(this.getPassword()))
				if (u.getRole().equals(this.getRole()))
					if (u.getFirstN().equals(this.firstN))
						if (u.getLastN().equals(this.lastN))
							if (u.getHomeBranch().equals(this.getHomeBranch()))
								if (u.getId().equals(this.getId()))
									if (u.getIsLoggedIn().equals(this.getIsLoggedIn()))
										return true;

		return false;
	}
}