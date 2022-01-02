package Interfaces;

import Entities.User;
import javafx.event.ActionEvent;

public interface ILoginInterface {
	
	public void setUser(String UserName, String Password, String Role);
	public void ConnectSystem(ActionEvent event) throws Exception;
	public User getUser();
	public void setStatus(String status);
	public String getStatus();
}
