import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entities.Supplier;
import Entities.User;
import client.controllers.LoginScreenController;
import javafx.event.ActionEvent;

/**
 * @author Danor
 * this Unit Test Class is for testing the login Screen Controller.
 */
class LoginTest {

	
	private class StubLogin extends LoginScreenController {
		User Person;
		User resPerson;
		
		@Override
		public void ConnectSystem(ActionEvent event) throws IOException{
		
			if(user.getUserName().equals("")||user.getPassword().equals("")) {
				System.out.println("you must enter UserName and Password!");
			}
			else {
				switch(user.getRole()) {
				
				case "Customer":{
					resPerson = new User();
					resPerson=Person;
					break;
				}
				
				}
			}
			}
		}
	
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
