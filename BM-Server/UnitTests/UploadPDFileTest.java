import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entities.MyFile;
import Entities.homeBranches;
import querys.DBConnect;
import querys.Query;

/**
 * @author danor
 * this class handles the tests for the uploading of PDF file in the server.
 */
class UploadPDFileTest {

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
	
	public String path;
	public File f;
	public MyFile file;
	public Boolean Flag;
	
	/**
	 * this is a set up for the tests including an connect to the DB.
	 */
	@BeforeEach
	void setUp () throws Exception{
		testDBConnect tbd = new testDBConnect();//for set up a connection to the DB
		testConn = tbd.testConnect();
		DBConnect.conn = testConn;
		path = "C:\\Users\\danor\\Desktop\\Quarterly-Report";
		f= new File(path);
		file = new MyFile(f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\") + 1));
		byte[] mybytearray = new byte[(int) f.length()];
		file.initArray(mybytearray.length);
		file.setSize(mybytearray.length);
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	/*
	 * Test Description:this is a test case to check if the updateFile returns true and the file is correct.
	 * Input: UserName = year = 2022 | quarter = 4 | homebranch = north | date = current.
	 * Expected result:true because the myFile is correct and updateFile returns true.
	 * */
	void testCorrectFile() {	
		file.setQuarter("4");
		file.setYear("2022");
		file.setHomebranch(homeBranches.toHomeBranchType("north"));
		file.setDate(new java.sql.Timestamp(new Date().getTime()).toString());
		
		Flag = Query.updateFile(file);
		
		assertTrue(Flag);
	}
	
	@Test
	/*
	 * Test Description:this is a test case to check if the updateFile returns false if Byte Array is null.
	 * Input: UserName = year = 2021 | quarter = 1 | homebranch = north | byte Array = null | date = current.
	 * Expected result:False because byte Array doesn't implement and parameter "is" in update file is null.
	 * */
	void testInCorrectFileWithByteArrayNULL() {
		MyFile resFile = new MyFile(f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\") + 1));
		resFile.setQuarter("1");
		resFile.setYear("2021");
		resFile.setHomebranch(homeBranches.toHomeBranchType("north"));
		resFile.setDate(new java.sql.Timestamp(new Date().getTime()).toString());
		
		try {
			Flag=Query.updateFile(resFile);
		} catch (Exception e) {
			assertFalse(Flag);
		}
	}
	
	@Test
	/*
	 * Test Description:this is a test case to check updateFile method returns false if the year is null.
	 * Input: UserName = year = null | quarter = "1" | homebranch = north | date = current.
	 * Expected result:False because year is null and the Query doesn't happen.
	 * */
	void testNullYearForFile() {
		file.setQuarter("1");
		file.setYear(null);
		file.setHomebranch(homeBranches.toHomeBranchType("north"));
		file.setDate(new java.sql.Timestamp(new Date().getTime()).toString());
		
		try {
			Flag = Query.updateFile(file);
		} catch (Exception e) {
			assertFalse(false);
		}
	}
	
	@Test
	/*
	 * Test Description:this is a test case to check updateFile method returns false if the Quarter is null.
	 * Input: UserName = year = "2022" | quarter = null | homebranch = "north" | date = current.
	 * Expected result:False because Quarter is null and the Query doesn't happen.
	 * */
	void testNullQuarterForFile() {
		file.setQuarter(null);
		file.setYear("2022");
		file.setHomebranch(homeBranches.toHomeBranchType("north"));
		file.setDate(new java.sql.Timestamp(new Date().getTime()).toString());
		
		try {
			Flag = Query.updateFile(file);
		} catch (Exception e) {
			assertFalse(false);
		}
	}
	
	@Test
	/*
	 * Test Description:this is a test case to check updateFile method returns false if the date is null.
	 * Input: UserName = year = "2020" | quarter = "2" | homebranch = "north" | date = null.
	 * Expected result:False because HomeBranch is null and the Query doesn't happen.
	 * */
	void testNullDateForFile() {
		file.setQuarter("2");
		file.setYear("2020");
		file.setHomebranch(homeBranches.toHomeBranchType("north"));
		file.setDate(null);
		
		try {
			Flag = Query.updateFile(file);
		} catch (Exception e) {
			assertFalse(false);
		}
	}
	
	@Test
	/*
	 * Test Description:this is a test case to check updateFile method returns false if the HomeBranch is null.
	 * Input: UserName = year = "2021" | quarter = "3" | homebranch = null.
	 * Expected result:False because HomeBranch is null and the Query doesn't happen.
	 * */
	void testNullHomeBranchForFile() {
		file.setQuarter("3");
		file.setYear("2021");
		file.setHomebranch(homeBranches.toHomeBranchType(""));
		file.setDate(new java.sql.Timestamp(new Date().getTime()).toString());
		
		try {
			Flag = Query.updateFile(file);
		} catch (Exception e) {
			assertFalse(false);
		}
	}
	
	@Test
	/*
	 * Test Description:this is a test case to check updateFile method returns false if the HomeBranch is incorrect.
	 * Input: UserName = year = "2021" | quarter = "2" | homebranch = "east".
	 * Expected result:False because "east" doesn't recognize as as a proper value in homeBrances entity (BM-Shared ->src) and returns null.
	 * */
	void testIncorrectHomeBranch() {
		file.setQuarter("2");
		file.setYear("2021");
		file.setHomebranch(homeBranches.toHomeBranchType("east"));
		file.setDate(new java.sql.Timestamp(new Date().getTime()).toString());
		
		try {
			Flag = Query.updateFile(file);
		} catch (Exception e) {
			assertFalse(false);
		}
	}
	
	@Test
	/*
	 * Test Description:this is a test case to check if we put the Null in the FileName,we get false.
	 * Input: UserName = fileName = null | year = "2020" | quarter = "4" | homebranch = "north"
	 * Expected result:False because FileName is null.
	 * */
	void testNULLFileName() {
		MyFile resFile = new MyFile("");
		resFile.setQuarter("4");
		resFile.setYear("2020");
		resFile.setHomebranch(homeBranches.toHomeBranchType("north"));
		resFile.setDate(new java.sql.Timestamp(new Date().getTime()).toString());
		byte[] mybytearray = new byte[(int) f.length()];
		resFile.initArray(mybytearray.length);
		resFile.setSize(mybytearray.length);
		
		try {
			Flag=Query.updateFile(resFile);
		} catch (Exception e) {
			assertFalse(Flag);
		}
	}
	
	@Test
	/*
	 * Test Description:this is a test case to check if we put the Null in the FileName,we get false.
	 * Input: UserName = fileName = null | year = "2020" | quarter = "4" | homebranch = "north"
	 * Expected result:False because FileName is null.
	 * */
	void testEqualFiles() {
		MyFile ExecptedFile = new MyFile(f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\") + 1));
		ExecptedFile.setQuarter("1");
		ExecptedFile.setYear("2019");
		ExecptedFile.setHomebranch(homeBranches.toHomeBranchType("north"));
		ExecptedFile.setDate(new java.sql.Timestamp(new Date().getTime()).toString());
		byte[] mybytearray = new byte[(int) f.length()];
		ExecptedFile.initArray(mybytearray.length);
		ExecptedFile.setSize(mybytearray.length);
		
		Query.updateFile(ExecptedFile);
		
		MyFile resFile = Query.downloadFile("north","2019" ,"1");
		
		assertTrue(ExecptedFile.equals(resFile));
	}
}
