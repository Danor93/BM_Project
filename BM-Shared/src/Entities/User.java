package Entities;

public class User {
	
	private String userName,password,isLoggedIn,role,id,firstN,lastN,w4c,email,phone;
	private homeBranches homeBranch;
	
	public User(String role,String id, String firstN, String lastN, String w4c, homeBranches homeBranch, String userName, String password, String isLoggedIn) {
		this.role=role;
		this.id=id;
		this.firstN = firstN;
		this.lastN = lastN;
		this.w4c = w4c;
		this.homeBranch = homeBranch;
		setUserName(userName);
		setPassword(password);
		this.isLoggedIn="0";
	}

	public String getId() {
		return id;
	}

	public String getFirstN() {
		return firstN;
	}

	public String getLastN() {
		return lastN;
	}

	public String getW4c() {
		return w4c;
	}

	public homeBranches getHomeBranch() {
		return homeBranch;
	}

	public String getRole() {
		return role;
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