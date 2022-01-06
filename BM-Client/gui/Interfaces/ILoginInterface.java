package Interfaces;

import Entities.User;
import javafx.event.ActionEvent;

public interface ILoginInterface {

	public void setUser(String role, String id, String firstN, String lastN, String homeBranch, String userName,
			String password, String isLoggedIn);

	public void ConnectSystem(ActionEvent event) throws Exception;

	public User getUser();

	public void setStatus(String status);

	public String getStatus();
}
