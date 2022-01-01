import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entities.Supplier;
import Entities.User;
import Interfaces.ILoginInterface;
import client.controllers.LoginScreenController;
import javafx.event.ActionEvent;

/**
 * @author Danor 
 * this Unit Test Class is for testing the login Screen Controller.
 */
class LoginTest {

	private class StubLogin implements ILoginInterface {
		User user;
		User resUser;
		String statusUser;

		@Override
		public void ConnectSystem(ActionEvent event) throws Exception {

			if (user.getUserName().equals("") || user.getPassword().equals("")) {
				throw new Exception("You must enter username and password");
			} else {
				if (!statusUser.equals("Active")) {
					System.out.println("this account is Freeze!");
					statusUser = null;
				}
				switch (user.getRole()) {

				case "Customer": {
					resUser = new User();
					resUser = user;
					break;
				}

				case "BranchManager": {
					resUser = new User();
					resUser = user;
					break;
				}

				case "CEO": {
					resUser = new User();
					resUser = user;
					break;
				}

				case "": {
					throw new Exception("There is no One with this userName and Password.");
				}

				default: {
					String[] DivededRole = ((String) user.getRole()).split("-");
					if (DivededRole[0].equals("HR")) {
						resUser = new User();
						resUser = user;
						break;
					}
					if (DivededRole[0].equals("Supplier")) {
						resUser = new User();
						resUser = user;
						break;
					}
				}
				}
			}
		}

		@Override
		public void setUser(String UserName, String Password, String Role) {
			this.user = new User();
			this.user.setUserName(UserName);
			this.user.setPassword(Password);
			this.user.setRole(Role);
		}

		@Override
		public User getUser() {
			return resUser;
		}
		
		@Override
		public void setStatus(String status){
			statusUser=status;
		}
		
		@Override
		public String getStatus() {
			return statusUser;
		}

	}

	@Test
	void testCoustomerCorrectLogin() {
		StubLogin stub = new StubLogin();
		stub.setUser("avi","123","Customer");
		stub.setStatus("Active");
		
		/*LoginScreenController loginController = new LoginScreenController(stub);
		try {
			loginController.ConnectSystem(new ActionEvent());
		} catch (Exception e) {
		}
		
		User Customer = new User();
		Customer.setUserName("avi");
		Customer.setPassword("123");
		Customer.setRole("Customer");
		
		assertTrue(Customer.equals(stub.getUser()));*/
	}
}
