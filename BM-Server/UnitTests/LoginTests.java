import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entities.User;
import Entities.homeBranches;
import querys.DBConnect;
import querys.Query;

/**
 * @author Danor
 * @author Sahar
 * @author Aviel
 * this class runs test for the login of users.
 */

public class LoginTests  {	
	public String loginResult;
	public static Connection testConn;	
	
	public class testDBConnect {
		public Connection testConn;
		
		/**
		 * this is an inner class that implements the connection of the DB without the gui.
		 * @return - a Connection parameter.
		 */
		public Connection testConnect() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			} catch (Exception ex) {
				/* handle the error */
				ex.printStackTrace();
			}

			try {
				testConn = DriverManager.getConnection("jdbc:mysql://localhost/bitemedb?serverTimezone=IST", "root","Aa123456");
			} catch (SQLException ex) {/* handle any errors */
			}
			return testConn;
		}
	}

	
	/**
	 * this is a set up for the tests including an connect to the DB.
	 */
	@BeforeEach
	void setUp () throws Exception{
		testDBConnect tbd = new testDBConnect();//for set up a connection to the DB
		testConn = tbd.testConnect();
		DBConnect.conn = testConn;
	}
	
	
	
	/************************* TEST Customer Login **************************/
	
	@Test
	/*
	 * Test Description:This test case check correct login of a customer
	 * Input: UserName = "b" | Password = "b"
	 * Expected result:true because we get the same user.
	 * */
	void testCustomerCorrectLoginByID() {
		User ExpectedCustomer = new User("Customer","3115467","Talia","Blum",homeBranches.toHomeBranchType("Center"),"b","b","0");
		
		String [] DivedMsg = Query.Login("b","b").split("@");
		
		User resCustomer = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		assertTrue(ExpectedCustomer.getId().equals(resCustomer.getId()));
		Query.UpdateisLoggedIn("b");
	}
	
	@Test
	/*
	 * Test Description:This test case check Incorrect login of a customer
	 * Input: UserName = "ns" | Password = "ns"
	 * Expected result:false because its not the same user.
	 * */
	void testCustomerInCorrectLoginByID() {
		User ExpectedCustomer = new User("Customer","3115467","Talia","Blum",homeBranches.toHomeBranchType("center"),"b","b","0");
		
		String [] DivedMsg = ((String) Query.Login("ns","ns")).split("@");
		User resCustomer = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		
		assertFalse(ExpectedCustomer.getId().equals(resCustomer.getId()));
		Query.UpdateisLoggedIn("ns");
	}
	
	@Test
	/*
	 * Test Description:This test case check Null input login of a customer
	 * Input: UserName = NULL | Password = NULL
	 * Expected result:False because we don't get anything from the DB.
	 * */
	void testCustomerNullLogin() {
		User resCustomer = new User(null, null, null, null, null, null, null, null);
		User ExpectedCustomer = new User("Customer","3115467","Talia","Blum",homeBranches.toHomeBranchType("center"),"b","b","0");
		
		String [] DivedMsg = ((String) Query.Login(null,null)).split("@");
		try {
			resCustomer = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
					homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		} catch (Exception e) {
			assertFalse(ExpectedCustomer.getId().equals(resCustomer.getId()));	
		}
	}
	
	@Test
	/*
	 * Test Description:This test case check if a user can log in while he is already logged in.
	 * Input: UserName = "b" | Password = "b" , UserName = "b" | Password = "b" 
	 * Expected result:False because the user already logged in.
	 * */
	void testCustomerAlreadyLogin() {
		
		Query.Login("b","b");		
		String SecondLogin = ((String) Query.Login("b","b"));
		
		assertTrue(SecondLogin.equals("Already"));
		Query.UpdateisLoggedIn("b");
	}
	
	/************************* TEST Branch Manager Login **************************/
	
	
	@Test
	/*
	 * Test Description:This test case check correct login of a Branch Manager.
	 * Input: UserName = "c" | Password = "c",ExpectedCustomer(c,c);
	 * Expected result:true because we get the same user.
	 * */
	void testBMCorrectLoginByID() {
		User ExpectedBM = new User("BranchManager","456","Sahar","Oz",homeBranches.toHomeBranchType("north"),"c","c","0");
		
		String [] DivedMsg = ((String) Query.Login("c","c")).split("@");
		User resBM = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		Query.UpdateisLoggedIn("c");
		assertTrue(ExpectedBM.getId().equals(resBM.getId()));
	}
	
	@Test
	/*
	 * Test Description:This test case check Incorrect login of a Branch Manager
	 * Input: UserName = "f" | Password = "f"
	 * Expected result:false because its not the same user.
	 * */
	void testBMInCorrectLoginByID() {
		User ExpectedBM = new User("BranchManager","456","Sahar","Oz",homeBranches.toHomeBranchType("north"),"c","c","0");
		
		String [] DivedMsg = ((String) Query.Login("f","f")).split("@");
		User resBM = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		Query.UpdateisLoggedIn("f");
		assertFalse(ExpectedBM.getId().equals(resBM.getId()));
	}
	
	@Test
	/*
	 * Test Description:This test case check Null input login of a customer
	 * Input: UserName = NULL | Password = NULL
	 * Expected result:False because we don't get anything from the DB.
	 * */
	void testBMNullLogin() {
		User resBM = new User(null, null, null, null, null, null, null, null);
		User ExpectedBM = new User("BranchManager","456","Sahar","Oz",homeBranches.toHomeBranchType("north"),"c","c","0");
		
		String [] DivedMsg = ((String) Query.Login(null,null)).split("@");
		try {
			resBM = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		} catch (Exception e) {
			assertFalse(ExpectedBM.getId().equals(resBM.getId()));
		}		
	}
	
	@Test
	/*
	 * Test Description:This test case check if a user can log in while he is already logged in.
	 * Input: UserName = "s" | Password = "s" , UserName = "s" | Password = "s" 
	 * Expected result:False because the user already logged in.
	 * */
	void testBMAlreadyLogin() {
		
		Query.Login("s","s");
		String SecondLogin = ((String) Query.Login("s","s"));
		
		Query.UpdateisLoggedIn("s");
		assertTrue(SecondLogin.equals("Already"));
	}
	
	/************************* TEST Supplier Login **************************/
	
	
	@Test
	/*
	 * Test Description:This test case check correct login of a Supplier.
	 * Input: UserName = "viv1" | Password = "viv1",ExpectedCustomer(viv1,viv1);
	 * Expected result:true because we get the same user.
	 * */
	void testSupplierCorrectLoginByID() {
		User ExpectedSupplier = new User("Supplier-Certified-vivino","3115645","Ron","Abu",homeBranches.toHomeBranchType("center"),"viv1","viv1","0");
		
		String [] DivedMsg = ((String) Query.Login("viv1","viv1")).split("@");
		User resSupplier = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		Query.UpdateisLoggedIn("viv1");
		assertTrue(ExpectedSupplier.getId().equals(resSupplier.getId()));
	}
	
	@Test
	/*
	 * Test Description:This test case check Incorrect login of a Supplier.
	 * Input: UserName = "viv2" | Password = "viv2"
	 * Expected result:false because its not the same user.
	 * */
	void testSupplierInCorrectLoginByID() {
		User ExpectedSupplier = new User("Supplier-Certified-vivino","3115645","Ron","Abu",homeBranches.toHomeBranchType("center"),"viv1","viv1","0");
		
		String [] DivedMsg = ((String) Query.Login("viv2","viv2")).split("@");
		User resSupplier = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		Query.UpdateisLoggedIn("viv2");
		assertFalse(ExpectedSupplier.getId().equals(resSupplier.getId()));
	}
	
	@Test
	/*
	 * Test Description:This test case check Null input login of a Supplier
	 * Input: UserName = NULL | Password = NULL
	 * Expected result:False because we don't get anything from the DB.
	 * */
	void testSupplierNullLogin() {
		User resSupp = new User(null, null, null, null, null, null, null, null);
		User ExpectedSupplier = new User("Supplier-Certified-vivino","3115645","Ron","Abu",homeBranches.toHomeBranchType("center"),"viv1","viv1","0");
		
		String [] DivedMsg = ((String) Query.Login(null,null)).split("@");
		try {
			resSupp = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		} catch (Exception e) {
			assertFalse(ExpectedSupplier.getId().equals(resSupp.getId()));
		}		
	}
	
	@Test
	/*
	 * Test Description:This test case check if a user can log in while he is already logged in.
	 * Input: UserName = "ref1" | Password = "ref1" , UserName = "ref1" | Password = "ref1" 
	 * Expected result:False because the user already logged in.
	 * */
	void testSupplierAlreadyLogin() {
		
		Query.Login("ref1","ref1");		
		String SecondLogin = ((String) Query.Login("ref1","ref1"));
		
		Query.UpdateisLoggedIn("ref1");
		assertTrue(SecondLogin.equals("Already"));
	}
	
	/************************* TEST HR Login **************************/
	
	
	@Test
	/*
	 * Test Description:This test case check correct login of a HR.
	 * Input: UserName = "h" | Password = "h",ExpectedCustomer(h,h);
	 * Expected result:true because we get the same user.
	 * */
	void testHRCorrectLoginByID() {
		User ExpectedHR = new User("HR-Intel","1211","Avi","Sofer",homeBranches.toHomeBranchType("north"),"h","h","0");
		
		String [] DivedMsg = ((String) Query.Login("h","h")).split("@");
		User resHR = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		Query.UpdateisLoggedIn("h");
		assertTrue(ExpectedHR.getId().equals(resHR.getId()));
	}
	
	@Test
	/*
	 * Test Description:This test case check Incorrect login of a HR.
	 * Input: UserName = "h1" | Password = "h1"
	 * Expected result:false because its not the same user.
	 * */
	void testHRInCorrectLoginByID() {
		User ExpectedHR = new User("HR-Intel","1211","Avi","Sofer",homeBranches.toHomeBranchType("north"),"h","h","0");
		
		String [] DivedMsg = ((String) Query.Login("h1","h1")).split("@");
		User resHR = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		Query.UpdateisLoggedIn("h1");
		assertFalse(ExpectedHR.getId().equals(resHR.getId()));
	}
	
	@Test
	/*
	 * Test Description:This test case check Null input login of a HR
	 * Input: UserName = NULL | Password = NULL
	 * Expected result:False because we don't get anything from the DB.
	 * */
	void testHRNullLogin() {
		User resHR = new User(null, null, null, null, null, null, null, null);
		User ExpectedHR = new User("HR-Intel","1211","Avi","Sofer",homeBranches.toHomeBranchType("north"),"h","h","0");
		
		String [] DivedMsg = ((String) Query.Login(null,null)).split("@");
		try {
			resHR = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		} catch (Exception e) {
			assertFalse(ExpectedHR.getId().equals(resHR.getId()));
		}		
	}
	
	@Test
	/*
	 * Test Description:This test case check if a user can log in while he is already logged in.
	 * Input: UserName = "ref1" | Password = "ref1" , UserName = "ref1" | Password = "ref1" 
	 * Expected result:False because the user already logged in.
	 * */
	void testHRAlreadyLogin() {
		Query.Login("h2","h2");		
		String SecondLogin = ((String)Query.Login("h2","h2"));

		Query.UpdateisLoggedIn("h2");
		assertTrue(SecondLogin.equals("Already"));
	}
	
	/************************* TEST CEO Login **************************/
	
	@Test
	/*
	 * Test Description:This test case check correct login of a CEO.
	 * Input: UserName = "e" | Password = "e",ExpectedCustomer(e,e);
	 * Expected result:true because we get the same user.
	 * */
	void testCEOCorrectLoginByID() {
		User ExpectedCEO = new User("CEO","689","Lior","Shauli",homeBranches.toHomeBranchType("north"),"e","e","0");
		
		String [] DivedMsg = ((String) Query.Login("e","e")).split("@");
		User resCEO = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		Query.UpdateisLoggedIn("e");
		assertTrue(ExpectedCEO.getId().equals(resCEO.getId()));
	}
	
	@Test
	/*
	 * Test Description:This test case check Incorrect login of a CEO.
	 * Input: UserName = "h1" | Password = "h1"
	 * Expected result:false because its not the same user.
	 * */
	void testCEOInCorrectLoginByID() {
		User ExpectedCEO = new User("CEO","689","Lior","Shauli",homeBranches.toHomeBranchType("north"),"e","e","0");
		
		String [] DivedMsg = ((String) Query.Login("ceo2","ceo2")).split("@");
		User resCEO = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		Query.UpdateisLoggedIn("ceo2");
		assertFalse(ExpectedCEO.getId().equals(resCEO.getId()));
	}
	
	@Test
	/*
	 * Test Description:This test case check Null input login of a CEO
	 * Input: UserName = NULL | Password = NULL
	 * Expected result:False because we don't get anything from the DB.
	 * */
	void testCEONullLogin() {
		User resCEO = new User(null, null, null, null, null, null, null, null);
		User ExpectedCEO = new User("CEO","689","Lior","Shauli",homeBranches.toHomeBranchType("north"),"e","e","0");
		
		String [] DivedMsg = ((String) Query.Login(null,null)).split("@");
		try {
			resCEO = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		} catch (Exception e) {
			assertFalse(ExpectedCEO.getId().equals(resCEO.getId()));
		}		
	}
	
	@Test
	/*
	 * Test Description:This test case check if a user can log in while he is already logged in.
	 * Input: UserName = "ceo3" | Password = "ceo3" , UserName = "ceo3" | Password = "ceo3" 
	 * Expected result:False because the user already logged in.
	 * */
	void testCEOAlreadyLogin() {
		
		Query.Login("ceo3","ceo3");		
		String SecondLogin = ((String) Query.Login("ceo3","ceo3"));
		
		Query.UpdateisLoggedIn("ceo2");
		assertTrue(SecondLogin.equals("Already"));
	}
	
	/************************* TEST Errors in Login **************************/

	@Test
	/*
	 * Test Description:This test case check if the account not exits in the DB.
	 * Input: UserName = "ortBraude" | Password = "ortBraude".
	 * Expected result:True because the user "ortBraude" is not exits in the DB.
	 * */
	void testNotExistingAccount() {
		String testLogin = ((String) Query.Login("ortBraude","ortBraude"));
		assertTrue(testLogin.equals("WrongInput"));
	}
	
	@Test
	/*
	 * Test Description:This test case check if the account is freeze.
	 * Input: UserName = "matan" | Password = "matan".
	 * Expected result:True because the user is defined as "Freeze" account in the DB and the login method will return "Freeze".
	 * */
	void testFreezeAccount() {
		String testLogin = ((String) Query.Login("daniel","daniel"));
		assertTrue(testLogin.equals("Freeze"));
	}
	
	
	@Test
	/*
	 * Test Description:This test case check wrong password.
	 * Input: UserName = "matan" | Password = "123".
	 * Expected result:True because the the password of this user is wrong and login method will return "WrongInput". 
	 * */
	void testWrongPassword() {
		String testLogin = ((String) Query.Login("matan","123"));
		assertTrue(testLogin.equals("WrongInput"));
	}
	
	@Test
	/*
	 * Test Description:This test case check wrong UserName.
	 * Input: UserName = "123" | Password = "matan".
	 * Expected result:True because the the UserName of this user is wrong and login method will return "WrongInput". 
	 * */
	void testWrongUserName() {
		String testLogin = ((String) Query.Login("matan2","matan"));
		assertTrue(testLogin.equals("WrongInput"));
	}
	
	@Test
	/*
	 * Test Description:This test case check a case when the user doesn't insert userName and password at all.
	 * Input: UserName = "" | Password = "".
	 * Expected result:True because when there is no input,login method will return "WrongInput".
	 * */
	void testWithoutFields() {
		String testLogin = ((String) Query.Login("",""));
		assertTrue(testLogin.equals("WrongInput"));
	}
	
	@Test
	/*
	 * Test Description:This test case check a case when the user doesn't insert userName.
	 * Input: UserName = "" | Password = "b".
	 * Expected result:True because when there is no userName,login method will return "WrongInput".
	 * */
	void testWithoutUserNameField() {
		String testLogin = ((String) Query.Login("","b"));
		assertTrue(testLogin.equals("WrongInput"));
	}
	
	@Test
	/*
	 * Test Description:This test case check a case when the user doesn't insert password.
	 * Input: UserName = "b" | Password = "".
	 * Expected result:True because when there is no password,login method will return "WrongInput".
	 * */
	void testWithoutPasswordField() {
		String testLogin = ((String) Query.Login("b",""));
		assertTrue(testLogin.equals("WrongInput"));
	}
}