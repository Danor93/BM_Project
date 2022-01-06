import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entities.Message;
import Entities.MessageType;
import Entities.User;
import Entities.homeBranches;
import Interfaces.ILoginInterface;
import Parsing.Parsing;
import client.controllers.LoginScreenController;
import javafx.event.ActionEvent;

public class LoginTests {

	public ILoginInterface ILIF;

	/*
	 * LoginStub() Describe: this class implements ILoginInterface in this class the
	 * method ConnectSystem is implemented like the implementation in the controller
	 * accept the fxml and server components. this method inject data with the class
	 * Parsing that implements method that called Message that get a Message from
	 * the server and initialize the static flag according to the data
	 */
	class LoginStub implements ILoginInterface {
		public String role;
		public String statusUser;
		public String id;
		public String firstN;
		public String lastN;
		public homeBranches homeBranch;
		public String userName;
		public String password;
		public String isLoggedIn;
		public String UserInjection;


		/**
		 * Initialize the relevant details for injection and make a StringBuilder for injected server answer
		 */
		@Override
		public void setUser(String role, String id, String firstN, String lastN, String homeBranch, String userName,
				String password, String isLoggedIn) {
			this.role = role;
			this.id = id;
			this.firstN = firstN;
			this.lastN = lastN;
			this.homeBranch = homeBranches.toHomeBranchType(homeBranch);
			this.userName = userName;
			this.password = password;
			this.isLoggedIn = isLoggedIn;
			StringBuilder sb = new StringBuilder();
			sb.append(role);
			sb.append("@");
			sb.append(id);
			sb.append("@");
			sb.append(firstN);
			sb.append("@");
			sb.append(lastN);
			sb.append("@");
			sb.append(homeBranch);
			sb.append("@");
			sb.append(userName);
			sb.append("@");
			sb.append(password);
			sb.append("@");
			sb.append(isLoggedIn);

			UserInjection = sb.toString();

		}

		/*
		 * Describe: This method get the user name and the password from the variables
		 * instead of the fxml components.
		 */
		@Override
		public void ConnectSystem(ActionEvent event) throws Exception {
			if (userName.isEmpty() || password.isEmpty()) {
				LoginScreenController.user = null;
				LoginScreenController.statusUser = "WrongInput";
			} else {
				if (!statusUser.equals("Active")) {
					statusUser = "Freeze";
					LoginScreenController.statusUser = statusUser;
				} else if (statusUser.equals("Freeze"))
					LoginScreenController.statusUser = "Freeze";
				else {
					Message serverAnswerInjection = new Message(MessageType.loginSystem, UserInjection);
					Parsing.Message(serverAnswerInjection);
				}
			}
		}

		@Override
		public void setStatus(String status) {
			LoginScreenController.statusUser = status;
			statusUser = status;
		}

		@Override
		public String getStatus() {
			return statusUser;
		}

		@Override
		public User getUser() {
			return LoginScreenController.user;
		}

	}

	/* Before each test we will create the Stub object */
	@BeforeEach
	void setUp() throws Exception {
		ILIF = new LoginStub();
	}

	
	/************************* this tests check good login for all the users **************************/
	
	@Test
	/*
	 * Test: This test check if the injected User initialized correctly and could login the system
	 * Input:injectedUser(role="Customer",id="123",firstN="Moshe",lastN="Cohen",
	 * homeBranch="center",userName="mosh",password="mosh",isLoggedIn="0"); Excepted
	 * Result :
	 * CorrectLoginUser(role="Customer",id="123",firstN="Moshe",lastN="Cohen",
	 * homeBranch="center",userName="mosh",password="mosh",isLoggedIn="0"); Output :
	 * True (injectedUser is equals to CorrectLoginUser
	 */
	void CorrectCustomerLoginTest() throws Exception {
		ILIF.setUser("Customer", "123", "Moshe", "Cohen", "center", "mosh", "mosh", "0");
		ILIF.setStatus("Active");
		LoginScreenController logincontrol = new LoginScreenController(ILIF);
		logincontrol.ConnectSystem(new ActionEvent());
		User CorrectLoginUser = new User("Customer", "123", "Moshe", "Cohen", homeBranches.toHomeBranchType("center"),
				"mosh", "mosh", "0");

		assertTrue(CorrectLoginUser.equals(LoginScreenController.user));
	}

	
	@Test
	/*
	 * Test: This test check if the injected User initialized correctly and could
	 * login the system
	 * Input:injectedUser(role="BranchManager",id="123",firstN="Moshe",lastN="Cohen"
	 * ,homeBranch="center",userName="mosh",password="mosh",isLoggedIn="0");
	 * Excepted Result :
	 * CorrectLoginUser(role="BranchManager",id="123",firstN="Moshe",lastN="Cohen",
	 * homeBranch="center",userName="mosh",password="mosh",isLoggedIn="0"); Output :
	 * True (injectedUser is equals to CorrectLoginUser
	 */
	void CorrectBranchManagerLoginTest() throws Exception {
		ILIF.setUser("BranchManager", "123", "Moshe", "Cohen", "center", "mosh", "mosh", "0");
		ILIF.setStatus("Active");
		LoginScreenController logincontrol = new LoginScreenController(ILIF);
		logincontrol.ConnectSystem(new ActionEvent());
		User CorrectLoginUser = new User("BranchManager", "123", "Moshe", "Cohen",
				homeBranches.toHomeBranchType("center"), "mosh", "mosh", "0");

		assertTrue(CorrectLoginUser.equals(LoginScreenController.user));
	}


	@Test
	/*
	 * Test: This test check if the injected User initialized correctly and could
	 * login the system
	 * Input:injectedUser(role="CEO",id="123",firstN="Sophia",lastN="Silverman",
	 * homeBranch="center",userName="mosh",password="mosh",isLoggedIn="0"); Excepted
	 * Result :
	 * CorrectLoginUser(role="CEO",id="123",firstN="Sophia",lastN="Silverman",
	 * homeBranch="center",userName="mosh",password="mosh",isLoggedIn="0"); Output :
	 * True (injectedUser is equals to CorrectLoginUser
	 */
	void CorrectCEOLoginTest() throws Exception {
		ILIF.setUser("CEO", "123", "Sophia", "Silverman", "center", "mosh", "mosh", "0");
		ILIF.setStatus("Active");
		LoginScreenController logincontrol = new LoginScreenController(ILIF);
		logincontrol.ConnectSystem(new ActionEvent());
		User CorrectLoginUser = new User("CEO", "123", "Sophia", "Silverman", homeBranches.toHomeBranchType("center"),
				"mosh", "mosh", "0");

		assertTrue(CorrectLoginUser.equals(LoginScreenController.user));
	}


	@Test
	/*
	 * Test: This test check if the injected User initialized correctly and could
	 * login the system
	 * Input:injectedUser(role="HR-Intel",id="123",firstN="Emily",lastN="Abutbul",
	 * homeBranch="center",userName="mosh",password="mosh",isLoggedIn="0"); Excepted
	 * Result :
	 * CorrectLoginUser(role="HR-Intel",id="123",firstN="Emily",lastN="Abutbul",
	 * homeBranch="center",userName="mosh",password="mosh",isLoggedIn="0"); Output :
	 * True (injectedUser is equals to CorrectLoginUser
	 */
	void CorrectHRManagerLoginTest() throws Exception {
		ILIF.setUser("HR-Intel", "123", "Emily", "Abutbul", "center", "mosh", "mosh", "0");
		ILIF.setStatus("Active");
		LoginScreenController logincontrol = new LoginScreenController(ILIF);
		logincontrol.ConnectSystem(new ActionEvent());
		User CorrectLoginUser = new User("HR-Intel", "123", "Emily", "Abutbul", homeBranches.toHomeBranchType("center"),
				"mosh", "mosh", "0");

		assertTrue(CorrectLoginUser.equals(LoginScreenController.user));
	}
	
	/************************* this tests check failure of login for all the users **************************/


	@Test
	/*
	 * Test: This test check if the injected User initialized incorrectly and could
	 * login the system
	 * Input:injectedUser(role="Customer",id="123",firstN="Moshe",lastN="Cohen",
	 * homeBranch="center",userName="mosh",password="MISTAKE",isLoggedIn="0");
	 * Excepted Result :
	 * CorrectLoginUser(role="Customer",id="123",firstN="Moshe",lastN="Cohen",
	 * homeBranch="center",userName="mosh",password="mosh",isLoggedIn="0"); Output :
	 * True (injectedUser is not equals to CorrectLoginUser
	 */
	void InCorrectCustomerLoginTest() throws Exception {
		ILIF.setUser("Customer", "123", "Moshe", "Cohen", "center", "mosh", "MISTAKE", "0");
		ILIF.setStatus("Active");
		LoginScreenController logincontrol = new LoginScreenController(ILIF);
		logincontrol.ConnectSystem(new ActionEvent());
		User CorrectLoginUser = new User("Customer", "123", "Moshe", "Cohen", homeBranches.toHomeBranchType("center"),
				"mosh", "mosh", "0");

		assertFalse(CorrectLoginUser.equals(LoginScreenController.user));
	}

	
	@Test
	/* 
	 * Test: This test check if the injected User initialized incorrectly and
	 * could not login the system
	 * Input:injectedUser(role="",id="",firstN="",lastN="",homeBranch="",userName="",password="",isLoggedIn="");
	 * Excepted Result : null Output : null (injectedUser is equals to null)
	 */
	void NullLoginTest() throws Exception {
		ILIF.setUser("", "", "", "", "", "", "", "");
		ILIF.setStatus("");
		LoginScreenController logincontrol = new LoginScreenController(ILIF);
		logincontrol.ConnectSystem(new ActionEvent());

		assertNull(LoginScreenController.user);
	}


	@Test
	/*
	 * Test: This test check if the injected User initialized incorrectly and could
	 * not login the system
	 * Input:injectedUser(role="Customer",id="123",firstN="Moshe",lastN="Cohen",
	 * homeBranch="center",userName="mosh",password="",isLoggedIn="0"); Excepted
	 * Result : null Output : null (injectedUser is equals to null)
	 */
	void NullPasswordLoginTest() throws Exception {
		ILIF.setUser("Customer", "123", "Moshe", "Cohen", "center", "mosh", "", "0");
		ILIF.setStatus("Active");
		LoginScreenController logincontrol = new LoginScreenController(ILIF);
		logincontrol.ConnectSystem(new ActionEvent());

		assertNull(LoginScreenController.user);
	}

	
	@Test
	/*
	 * Test: This test check if the injected User initialized incorrectly and could
	 * not login the system
	 * Input:injectedUser(role="Customer",id="123",firstN="Moshe",lastN="Cohen",
	 * homeBranch="center",userName="",password="mosh",isLoggedIn="0"); Excepted
	 * Result : null Output : null (injectedUser is equals to null)
	 */
	void NullUserNameLoginTest() throws Exception {
		ILIF.setUser("Customer", "123", "Moshe", "Cohen", "center", "", "mosh", "0");
		ILIF.setStatus("Active");
		LoginScreenController logincontrol = new LoginScreenController(ILIF);
		logincontrol.ConnectSystem(new ActionEvent());

		assertNull(LoginScreenController.user);
	}

	@Test
	/*
	 * Test: This test check if the injected User initialized correctly and could
	 * not login the system
	 * Input:injectedUser(role="Customer",id="123",firstN="Moshe",lastN="Cohen",
	 * homeBranch="center",userName="mosh",password="mosh",isLoggedIn="1"); Excepted
	 * Result :
	 * CorrectLoginUser(role="Customer",id="123",firstN="Moshe",lastN="Cohen",
	 * homeBranch="north",userName="mosh",password="mosh",isLoggedIn="0"); Output :
	 * False (injectedUser is not equals to CorrectLoginUser because the homeBranch
	 * is different, one is 'center', and the other is 'north')
	 */
	void DiffrentHomeBranchLoginTest() throws Exception {
		ILIF.setUser("Customer", "123", "Moshe", "Cohen", "center", "mosh", "mosh", "0");
		ILIF.setStatus("Active");
		LoginScreenController logincontrol = new LoginScreenController(ILIF);
		logincontrol.ConnectSystem(new ActionEvent());
		User CorrectLoginUser = new User("Customer", "123", "Moshe", "Cohen", homeBranches.toHomeBranchType("north"),
				"mosh", "mosh", "0");

		assertFalse(CorrectLoginUser.equals(LoginScreenController.user));
	}

	@Test
	/*
	 * Test: This test check if the injected User initialized correctly and could
	 * login the system
	 * Input:injectedUser(role="Customer",id="123",firstN="Moshe",lastN="Cohen",
	 * homeBranch="center",userName="mosh",password="mosh",isLoggedIn="1"); Excepted
	 * Result :
	 * CorrectLoginUser(role="Customer",id="111",firstN="Aviel",lastN="Gabay",
	 * homeBranch="center",userName="avi",password="avi",isLoggedIn="0"); Output :
	 * False (injectedUser is not equals to CorrectLoginUser because they are two
	 * different users)
	 */
	void DiffrentUsersLoginTest() throws Exception {
		ILIF.setUser("Customer", "123", "Moshe", "Cohen", "center", "mosh", "mosh", "0");
		ILIF.setStatus("Active");
		LoginScreenController logincontrol = new LoginScreenController(ILIF);
		logincontrol.ConnectSystem(new ActionEvent());
		User CorrectLoginUser = new User("Customer", "111", "Aviel", "Gabay", homeBranches.toHomeBranchType("center"),
				"avi", "avi", "0");

		assertFalse(CorrectLoginUser.equals(LoginScreenController.user));
	}

	@Test
	/*
	 * Test: This test check if the injected User initialized correctly and could
	 * login the system
	 * Input:injectedUser(role="Customer",id="123",firstN="Moshe",lastN="Cohen",
	 * homeBranch="center",userName="mosh",password="mosh",isLoggedIn="1"); Excepted
	 * Result :
	 * CorrectLoginUser(role="BranchManager",id="123",firstN="Moshe",lastN="Cohen",
	 * homeBranch="center",userName="mosh",password="mosh",isLoggedIn="0"); Output :
	 * False (injectedUser is not equals to CorrectLoginUser because they have two
	 * different roles)
	 */
	void DiffrentRolesLoginTest() throws Exception {
		ILIF.setUser("Customer", "123", "Moshe", "Cohen", "center", "mosh", "mosh", "0");
		ILIF.setStatus("Active");
		LoginScreenController logincontrol = new LoginScreenController(ILIF);
		logincontrol.ConnectSystem(new ActionEvent());
		User CorrectLoginUser = new User("BranchManager", "123", "Moshe", "Cohen",
				homeBranches.toHomeBranchType("center"), "mosh", "mosh", "0");

		assertFalse(CorrectLoginUser.equals(LoginScreenController.user));
	}

	@Test
	/*
	 * Test: This test check if the injected User initialized correctly and could
	 * login the system
	 * Input:injectedUser(role="Customer",id="123",firstN="Moshe",lastN="Cohen",
	 * homeBranch="center",userName="mosh",password="mosh",isLoggedIn="1"); Excepted
	 * Result :
	 * CorrectLoginUser(role="BranchManager",id="111",firstN="Moshe",lastN="Cohen",
	 * homeBranch="center",userName="mosh",password="mosh",isLoggedIn="0"); Output :
	 * False (injectedUser is not equals to CorrectLoginUser because they have two
	 * different ID)
	 */
	void DiffrentIDPrimaryKeyLoginTest() throws Exception {
		ILIF.setUser("Customer", "123", "Moshe", "Cohen", "center", "mosh", "mosh", "0");
		ILIF.setStatus("Active");
		LoginScreenController logincontrol = new LoginScreenController(ILIF);
		logincontrol.ConnectSystem(new ActionEvent());
		User CorrectLoginUser = new User("Customer", "111", "Moshe", "Cohen", homeBranches.toHomeBranchType("center"),
				"mosh", "mosh", "0");

		assertFalse(CorrectLoginUser.equals(LoginScreenController.user));
	}

	@Test
	/*
	 * Test: This test check if the injected User initialized correctly and could
	 * login the system
	 * Input:injectedUser(role="Customer",id="123",firstN="Moshe",lastN="Cohen",
	 * homeBranch="center",userName="mosh",password="mosh",isLoggedIn="1"); Excepted
	 * Result :
	 * CorrectLoginUser(role="Customer",id="123",firstN="Moshe",lastN="Cohen",
	 * homeBranch="center",userName="mosh",password="mosh",isLoggedIn="1"); Output :
	 * False (injectedUser is not equals to CorrectLoginUser because one is logged
	 * in to the system, while the other cannot login)
	 */
	void AlreadyLoginTest() throws Exception {
		ILIF.setUser("Customer", "123", "Moshe", "Cohen", "center", "mosh", "mosh", "0");
		ILIF.setStatus("Active");
		LoginScreenController logincontrol = new LoginScreenController(ILIF);
		logincontrol.ConnectSystem(new ActionEvent());
		User CorrectLoginUser = new User("Customer", "123", "Moshe", "Cohen", homeBranches.toHomeBranchType("center"),
				"mosh", "mosh", "1");

		assertFalse(CorrectLoginUser.equals(LoginScreenController.user));
	}

	@Test
	/*
	 * Test: This test check if the injected User initialized incorrectly and could
	 * not login the system
	 * Input:injectedUser(role="Customer",id="123",firstN="Moshe",lastN="Cohen",
	 * homeBranch="center",userName="",password="mosh",isLoggedIn="0"); Excepted
	 * Result : String 'Freeze' Output : True (status of injectedUser is equals to
	 * 'Freeze')
	 */
	void FrozenLoginTest() throws Exception {
		ILIF.setUser("Customer", "123", "Moshe", "Cohen", "center", "mosh", "mosh", "0");
		ILIF.setStatus("Freeze");
		LoginScreenController logincontrol = new LoginScreenController(ILIF);
		logincontrol.ConnectSystem(new ActionEvent());

		assertTrue(LoginScreenController.statusUser.equals("Freeze"));
	}
}
