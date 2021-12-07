package Entities;

public class User {
	
	private String role,id,firstN,lastN,w4c;
	private homeBranches homeBranch;
	
	public User(String role,String id, String firstN, String lastN, String w4c, homeBranches homeBranch) {
		this.role=role;
		this.id=id;
		this.firstN = firstN;
		this.lastN = lastN;
		this.w4c = w4c;
		this.homeBranch = homeBranch;
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
	


}
