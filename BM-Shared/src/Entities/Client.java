package Entities;

import java.io.Serializable;

public class Client extends User implements Serializable {

	private static final long serialVersionUID = -8058524030821504978L;
	private String w4c_private,status,creditCardNumber;
	private homeBranches Branch;
	
	public Client(String role, String id, String firstN, String lastN, homeBranches homeBranch, String userName,
			String password, String isLoggedIn, String w4c_private, String status, String creditCardNumber) {
		super(role, id, firstN, lastN, homeBranch, userName, password, isLoggedIn);
		this.w4c_private = w4c_private;
		this.status = status;
		this.creditCardNumber = creditCardNumber;
	}

	public String getW4c_private() {
		return w4c_private;
	}

	public void setW4c_private(String w4c_private) {
		this.w4c_private = w4c_private;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
	public homeBranches getBranch() {
		return Branch;
	}

	public void setBranch(homeBranches branch) {
		Branch = branch;
	}
}
