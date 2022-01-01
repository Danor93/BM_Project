import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entities.User;
import Entities.homeBranches;
import querys.DBCheck;
import querys.DBConnect;
import querys.UpdateDB;

/**
 * @author Danor
 * this class runs test for the login of users.
 */
class LoginTests {

	/**
	 * this is to update the users login status to 0 after the tests.
	 */
	@AfterEach
	void tearDown() throws Exception {
		UpdateDB.UpdateisLoggedIn("viv1");
		UpdateDB.UpdateisLoggedIn("h");
		UpdateDB.UpdateisLoggedIn("b");
		UpdateDB.UpdateisLoggedIn("c");
		UpdateDB.UpdateisLoggedIn("e");
		UpdateDB.UpdateisLoggedIn("adi");
		UpdateDB.UpdateisLoggedIn("f");
	}
	
	/************************* TEST Customer Login **************************/

	@Test
	/*
	 * Test Description:This test case check correct login of a customer
	 * Input: UserName = "b" | Password = "b"
	 * Expected result:true because we get the same user.
	 * */
	void testCustomerCorrectLogin() {
		User ExpectedCustomer = new User("Customer","134","Talia","Blum",homeBranches.toHomeBranchType("center"),"b","b","0");
		
		String [] DivedMsg = ((String) DBCheck.DBCheck("b","b")).split("-");
		User resCustomer = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		assertTrue(ExpectedCustomer.equals(resCustomer));
	}
	
	@Test
	/*
	 * Test Description:This test case check Incorrect login of a customer
	 * Input: UserName = "adi" | Password = "a1"
	 * Expected result:false because its not the same user.
	 * */
	void testCustomerInCorrectLogin() {
		User ExpectedCustomer = new User("Customer","134","Talia","Blum",homeBranches.toHomeBranchType("center"),"b","b","0");
		
		String [] DivedMsg = ((String) DBCheck.DBCheck("adi","a1")).split("-");
		User resCustomer = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		assertFalse(ExpectedCustomer.equals(resCustomer));
	}
	
	@Test
	/*
	 * Test Description:This test case check Null input login of a customer
	 * Input: UserName = NULL | Password = NULL
	 * Expected result:False because we don't get anything from the DB.
	 * */
	void testCustomerNullLogin() {
		User ExpectedCustomer = new User("Customer","134","Talia","Blum",homeBranches.toHomeBranchType("center"),"b","b","0");
		
		String [] DivedMsg = ((String) DBCheck.DBCheck(null,null)).split("-");
		User resCustomer = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		assertFalse(ExpectedCustomer.equals(resCustomer));
	}
	
	@Test
	/*
	 * Test Description:This test case check if a user can log in while he is already logged in.
	 * Input: UserName = "ny" | Password = "ny" , UserName = "ny" | Password = "ny" 
	 * Expected result:False because the user already logged in.
	 * */
	void testCustomerAlreadyLogin() {
		
		String [] FirstLogin = ((String) DBCheck.DBCheck("ny","ny")).split("-");
		User Customer = new User (FirstLogin[0], FirstLogin[1], FirstLogin[2], FirstLogin[3],
				homeBranches.toHomeBranchType(FirstLogin[4]), FirstLogin[5], FirstLogin[6], FirstLogin[7]);
		
		String [] SecondLogin = ((String) DBCheck.DBCheck("ny","ny")).split("-");
		User resCustomer = new User (SecondLogin[0], SecondLogin[1], SecondLogin[2], SecondLogin[3],
				homeBranches.toHomeBranchType(SecondLogin[4]), SecondLogin[5], SecondLogin[6], SecondLogin[7]);
		
		assertFalse(Customer.equals(resCustomer));
	}
	
	/************************* TEST Branch Manager Login **************************/
	
	
	@Test
	/*
	 * Test Description:This test case check correct login of a Branch Manager.
	 * Input: UserName = "c" | Password = "c",ExpectedCustomer(c,c);
	 * Expected result:true because we get the same user.
	 * */
	void testBMCorrectLogin() {
		User ExpectedCustomer = new User("Branch Manager","456","Sahar","Oz",homeBranches.toHomeBranchType("north"),"c","c","0");
		
		String [] DivedMsg = ((String) DBCheck.DBCheck("c","c")).split("-");
		User resCustomer = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		assertTrue(ExpectedCustomer.equals(resCustomer));
	}
	
	@Test
	/*
	 * Test Description:This test case check Incorrect login of a Branch Manager
	 * Input: UserName = "f" | Password = "f"
	 * Expected result:false because its not the same user.
	 * */
	void testBMInCorrectLogin() {
		User ExpectedCustomer = new User("Branch Manager","456","Sahar","Oz",homeBranches.toHomeBranchType("north"),"c","c","0");
		
		String [] DivedMsg = ((String) DBCheck.DBCheck("f","f")).split("-");
		User resCustomer = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		assertFalse(ExpectedCustomer.equals(resCustomer));
	}
	
	@Test
	/*
	 * Test Description:This test case check Null input login of a customer
	 * Input: UserName = NULL | Password = NULL
	 * Expected result:False because we don't get anything from the DB.
	 * */
	void testBMNullLogin() {
		User ExpectedCustomer = new User("Branch Manager","456","Sahar","Oz",homeBranches.toHomeBranchType("north"),"c","c","0");
		
		String [] DivedMsg = ((String) DBCheck.DBCheck(null,null)).split("-");
		User resCustomer = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		assertFalse(ExpectedCustomer.equals(resCustomer));
	}
	
	@Test
	/*
	 * Test Description:This test case check if a user can log in while he is already logged in.
	 * Input: UserName = "s" | Password = "s" , UserName = "s" | Password = "s" 
	 * Expected result:False because the user already logged in.
	 * */
	void testBMAlreadyLogin() {
		
		String [] FirstLogin = ((String) DBCheck.DBCheck("s","s")).split("-");
		User Customer = new User (FirstLogin[0], FirstLogin[1], FirstLogin[2], FirstLogin[3],
				homeBranches.toHomeBranchType(FirstLogin[4]), FirstLogin[5], FirstLogin[6], FirstLogin[7]);
		
		String [] SecondLogin = ((String) DBCheck.DBCheck("s","s")).split("-");
		User resCustomer = new User (SecondLogin[0], SecondLogin[1], SecondLogin[2], SecondLogin[3],
				homeBranches.toHomeBranchType(SecondLogin[4]), SecondLogin[5], SecondLogin[6], SecondLogin[7]);
		
		assertFalse(Customer.equals(resCustomer));
	}
	
	/************************* TEST Supplier Login **************************/
	
	
	@Test
	/*
	 * Test Description:This test case check correct login of a Supplier.
	 * Input: UserName = "viv1" | Password = "viv1",ExpectedCustomer(viv1,viv1);
	 * Expected result:true because we get the same user.
	 * */
	void testSupplierCorrectLogin() {
		User ExpectedCustomer = new User("Supplier-Certified-vivino","45678","Ron","Abu",homeBranches.toHomeBranchType("center"),"viv1","viv1","0");
		
		String [] DivedMsg = ((String) DBCheck.DBCheck("viv1","viv1")).split("-");
		User resCustomer = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		assertTrue(ExpectedCustomer.equals(resCustomer));
	}
	
	@Test
	/*
	 * Test Description:This test case check Incorrect login of a Supplier.
	 * Input: UserName = "viv2" | Password = "viv2"
	 * Expected result:false because its not the same user.
	 * */
	void testSupplierInCorrectLogin() {
		User ExpectedCustomer = new User("Supplier-Certified-vivino","45678","Ron","Abu",homeBranches.toHomeBranchType("center"),"viv1","viv1","0");
		
		String [] DivedMsg = ((String) DBCheck.DBCheck("viv2","viv2")).split("-");
		User resCustomer = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		assertFalse(ExpectedCustomer.equals(resCustomer));
	}
	
	@Test
	/*
	 * Test Description:This test case check Null input login of a Supplier
	 * Input: UserName = NULL | Password = NULL
	 * Expected result:False because we don't get anything from the DB.
	 * */
	void testSupplierNullLogin() {
		User ExpectedCustomer = new User("Supplier-Certified-vivino","45678","Ron","Abu",homeBranches.toHomeBranchType("center"),"viv1","viv1","0");
		
		String [] DivedMsg = ((String) DBCheck.DBCheck(null,null)).split("-");
		User resCustomer = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		assertFalse(ExpectedCustomer.equals(resCustomer));
	}
	
	@Test
	/*
	 * Test Description:This test case check if a user can log in while he is already logged in.
	 * Input: UserName = "ref1" | Password = "ref1" , UserName = "ref1" | Password = "ref1" 
	 * Expected result:False because the user already logged in.
	 * */
	void testSupplierAlreadyLogin() {
		
		String [] FirstLogin = ((String) DBCheck.DBCheck("ref1","ref1")).split("-");
		User Customer = new User (FirstLogin[0], FirstLogin[1], FirstLogin[2], FirstLogin[3],
				homeBranches.toHomeBranchType(FirstLogin[4]), FirstLogin[5], FirstLogin[6], FirstLogin[7]);
		
		String [] SecondLogin = ((String) DBCheck.DBCheck("ref1","ref1")).split("-");
		User resCustomer = new User (SecondLogin[0], SecondLogin[1], SecondLogin[2], SecondLogin[3],
				homeBranches.toHomeBranchType(SecondLogin[4]), SecondLogin[5], SecondLogin[6], SecondLogin[7]);
		
		assertFalse(Customer.equals(resCustomer));
	}
	
	/************************* TEST HR Login **************************/
	
	
	@Test
	/*
	 * Test Description:This test case check correct login of a HR.
	 * Input: UserName = "h" | Password = "h",ExpectedCustomer(h,h);
	 * Expected result:true because we get the same user.
	 * */
	void testHRCorrectLogin() {
		User ExpectedCustomer = new User("HR-Intel","1211","Avi","Sofer",homeBranches.toHomeBranchType("north"),"h","h","0");
		
		String [] DivedMsg = ((String) DBCheck.DBCheck("h","h")).split("-");
		User resCustomer = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		assertTrue(ExpectedCustomer.equals(resCustomer));
	}
	
	@Test
	/*
	 * Test Description:This test case check Incorrect login of a HR.
	 * Input: UserName = "h1" | Password = "h1"
	 * Expected result:false because its not the same user.
	 * */
	void testHRInCorrectLogin() {
		User ExpectedCustomer = new User("HR-Intel","1211","Avi","Sofer",homeBranches.toHomeBranchType("north"),"h","h","0");
		
		String [] DivedMsg = ((String) DBCheck.DBCheck("h1","h1")).split("-");
		User resCustomer = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		assertFalse(ExpectedCustomer.equals(resCustomer));
	}
	
	@Test
	/*
	 * Test Description:This test case check Null input login of a HR
	 * Input: UserName = NULL | Password = NULL
	 * Expected result:False because we don't get anything from the DB.
	 * */
	void testHRNullLogin() {
		User ExpectedCustomer = new User("HR-Intel","1211","Avi","Sofer",homeBranches.toHomeBranchType("north"),"h","h","0");
		
		String [] DivedMsg = ((String) DBCheck.DBCheck(null,null)).split("-");
		User resCustomer = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		assertFalse(ExpectedCustomer.equals(resCustomer));
	}
	
	@Test
	/*
	 * Test Description:This test case check if a user can log in while he is already logged in.
	 * Input: UserName = "ref1" | Password = "ref1" , UserName = "ref1" | Password = "ref1" 
	 * Expected result:False because the user already logged in.
	 * */
	void testHRAlreadyLogin() {
		
		String [] FirstLogin = ((String) DBCheck.DBCheck("h2","h2")).split("-");
		User Customer = new User (FirstLogin[0], FirstLogin[1], FirstLogin[2], FirstLogin[3],
				homeBranches.toHomeBranchType(FirstLogin[4]), FirstLogin[5], FirstLogin[6], FirstLogin[7]);
		
		String [] SecondLogin = ((String) DBCheck.DBCheck("h2","h2")).split("-");
		User resCustomer = new User (SecondLogin[0], SecondLogin[1], SecondLogin[2], SecondLogin[3],
				homeBranches.toHomeBranchType(SecondLogin[4]), SecondLogin[5], SecondLogin[6], SecondLogin[7]);
		
		assertFalse(Customer.equals(resCustomer));
	}
	
	/************************* TEST CEO Login **************************/
	
	@Test
	/*
	 * Test Description:This test case check correct login of a CEO.
	 * Input: UserName = "e" | Password = "e",ExpectedCustomer(e,e);
	 * Expected result:true because we get the same user.
	 * */
	void testCEOCorrectLogin() {
		User ExpectedCustomer = new User("CEO","689","Lior","Shauli",homeBranches.toHomeBranchType("north"),"e","e","0");
		
		String [] DivedMsg = ((String) DBCheck.DBCheck("e","e")).split("-");
		User resCustomer = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		assertTrue(ExpectedCustomer.equals(resCustomer));
	}
	
	@Test
	/*
	 * Test Description:This test case check Incorrect login of a CEO.
	 * Input: UserName = "h1" | Password = "h1"
	 * Expected result:false because its not the same user.
	 * */
	void testCEOInCorrectLogin() {
		User ExpectedCustomer = new User("CEO","689","Lior","Shauli",homeBranches.toHomeBranchType("north"),"e","e","0");
		
		String [] DivedMsg = ((String) DBCheck.DBCheck("ceo2","ceo2")).split("-");
		User resCustomer = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		assertFalse(ExpectedCustomer.equals(resCustomer));
	}
	
	@Test
	/*
	 * Test Description:This test case check Null input login of a CEO
	 * Input: UserName = NULL | Password = NULL
	 * Expected result:False because we don't get anything from the DB.
	 * */
	void testCEONullLogin() {
		User ExpectedCustomer = new User("CEO","689","Lior","Shauli",homeBranches.toHomeBranchType("north"),"e","e","0");
		
		String [] DivedMsg = ((String) DBCheck.DBCheck(null,null)).split("-");
		User resCustomer = new User (DivedMsg[0], DivedMsg[1], DivedMsg[2], DivedMsg[3],
				homeBranches.toHomeBranchType(DivedMsg[4]), DivedMsg[5], DivedMsg[6], DivedMsg[7]);
		
		assertFalse(ExpectedCustomer.equals(resCustomer));
	}
	
	@Test
	/*
	 * Test Description:This test case check if a user can log in while he is already logged in.
	 * Input: UserName = "ceo3" | Password = "ceo3" , UserName = "ceo3" | Password = "ceo3" 
	 * Expected result:False because the user already logged in.
	 * */
	void testCEOAlreadyLogin() {
		
		String [] FirstLogin = ((String) DBCheck.DBCheck("ceo3","ceo3")).split("-");
		User Customer = new User (FirstLogin[0], FirstLogin[1], FirstLogin[2], FirstLogin[3],
				homeBranches.toHomeBranchType(FirstLogin[4]), FirstLogin[5], FirstLogin[6], FirstLogin[7]);
		
		String [] SecondLogin = ((String) DBCheck.DBCheck("ceo3","ceo3")).split("-");
		User resCustomer = new User (SecondLogin[0], SecondLogin[1], SecondLogin[2], SecondLogin[3],
				homeBranches.toHomeBranchType(SecondLogin[4]), SecondLogin[5], SecondLogin[6], SecondLogin[7]);
		
		assertFalse(Customer.equals(resCustomer));
	}
}
