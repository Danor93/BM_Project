import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entities.MyFile;
import Entities.homeBranches;
import querys.DBConnect;
import querys.Query;

/**
 * @author danor
 * Test class Description:this test class handles the tests for the uploading of PDF file in the server.
 */
class UploadPDFileTests {

public static Connection testConn;
public String path;
public File f;
public MyFile file;
public Boolean Flag;
	
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
	 * method Description:this is a set up for the tests including an connect to the DB and initialize the file and myFile parameters.
	 */
	@BeforeEach
	void setUp () throws Exception{
		testDBConnect tbd = new testDBConnect();//for set up a connection to the DB
		testConn = tbd.testConnect();
		DBConnect.conn = testConn;
		path = "C:\\G14BiteMe\\Quarterly-Report_test.pdf";//file path.
		f= new File(path);
		file = new MyFile(f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\") + 1));//absolute path.
		/*for implement the byte array in the myFile parameter.*/
		byte[] mybytearray = new byte[(int) f.length()];
		file.initArray(mybytearray.length);
		file.setSize(mybytearray.length);
	}

	@Test
	/*
	 * Test Description:this is a test case to check if the updateFile returns true and the file is correct.
	 * Input:year = "2022" | quarter = "1" | homebranch = north | date = current.
	 * Expected result:true because the myFile is correct and updateFile returns true.
	 * */
	void testCorrectFile() {	
		file.setQuarter("1");
		file.setYear("2022");
		file.setHomebranch(homeBranches.toHomeBranchType("north"));
		file.setDate(new java.sql.Timestamp(new Date().getTime()).toString());
		
		Flag = Query.updateFile(file);
		
		assertTrue(Flag);
	}
	
	@Test
	/*
	 * Test Description:this is a test case to check if the updateFile returns false if Byte Array is null.
	 * Input: fileName = "Quarterly-Report" | year = "2021" | quarter = "4"  | homebranch = "north" | byte Array = null | date = current.
	 * Expected result:False because byte Array doesn't implement and parameter "is" in update file is null.
	 * */
	void testInCorrectFileWithByteArrayNULL() {
		MyFile resFile = new MyFile(f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\") + 1));
		resFile.setQuarter("4");
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
	 * Input: year = null | quarter = "3" | homebranch = "north" | date = current.
	 * Expected result:False because year is null and the Query doesn't happen.
	 * */
	void testNullYearForFile() {
		file.setQuarter("3");
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
	 * Input: year = "2021" | quarter = null | homebranch = "north" | date = current.
	 * Expected result:False because Quarter is null and the Query doesn't happen.
	 * */
	void testNullQuarterForFile() {
		file.setQuarter(null);
		file.setYear("2021");
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
	 * Input: year = "2021" | quarter = "2" | homebranch = "north" | date = null.
	 * Expected result:False because HomeBranch is null and the Query doesn't happen.
	 * */
	void testNullDateForFile() {
		file.setQuarter("2");
		file.setYear("2021");
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
	 * Input: year = "2021" | quarter = "1" | homebranch = null | date = current.
	 * Expected result:False because HomeBranch is null and the Query doesn't happen.
	 * */
	void testNullHomeBranchForFile() {
		file.setQuarter("1");
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
	 * Input: year = "2020" | quarter = "4" | homebranch = "east" | date = current.
	 * Expected result:False because "east" doesn't recognize as as a proper value in homeBrances entity (BM-Shared ->src->Entities) and returns null.
	 * */
	void testIncorrectHomeBranch() {
		file.setQuarter("4");
		file.setYear("2020");
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
	 * Test Description:this is a test case to check if we put the Null in the FileName,it return false.
	 * Input: fileName = null | year = "2020" | quarter = "3" | homebranch = "north" | date = current.
	 * Expected result:False because FileName is null.
	 * */
	void testNULLFileName() {
		/*initialization  of the file*/
		MyFile resFile = new MyFile("");
		resFile.setQuarter("3");
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
	 * Test Description:this is a test case to check if two files equals.
	 * Input: ExecptedFile:fileName = "Quarterly-Report"| year = "2020" | quarter = "2" | homebranch = "north" |date=current.
	 * 		resFile:fileName = "Quarterly-Report"| year = "2020" | quarter = "2" | homebranch = "north" |date=current.			
	 * Expected result:true because we entered the same details for the both files.
	 * */
	void testEqualFiles() {
		MyFile ExecptedFile = new MyFile(f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\") + 1));
		ExecptedFile.setQuarter("2");
		ExecptedFile.setYear("2020");
		ExecptedFile.setHomebranch(homeBranches.toHomeBranchType("north"));
		ExecptedFile.setDate(new java.sql.Timestamp(new Date().getTime()).toString());
		byte[] mybytearray = new byte[(int) f.length()];
		ExecptedFile.initArray(mybytearray.length);
		ExecptedFile.setSize(mybytearray.length);
	
		
		Query.updateFile(ExecptedFile);
		
		MyFile resFile = Query.downloadFile("north","2020" ,"2");//download the exact file from the DB.
		
		assertEquals(ExecptedFile, resFile);
	}
	
	@Test
	/*
	 * Test Description:this is a test case to check if two files not equals.
	 * Input: fileName = "Quarterly-Report" | year = "2020" | quarter = "1" | homebranch = "north" | date=current.
	 *		resFile:fileName = "Quarterly-Report" | year = "2020" | quarter = "2" | homebranch = "north" | date=current.	
	 * Expected result:fasle because we not entered the same details for both fields.
	 * */
	void testNotEqualFiles() {
		MyFile ExecptedFile = new MyFile(f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\") + 1));
		ExecptedFile.setQuarter("1");
		ExecptedFile.setYear("2020");
		ExecptedFile.setHomebranch(homeBranches.toHomeBranchType("north"));
		ExecptedFile.setDate(new java.sql.Timestamp(new Date().getTime()).toString());
		byte[] mybytearray = new byte[(int) f.length()];
		ExecptedFile.initArray(mybytearray.length);
		ExecptedFile.setSize(mybytearray.length);
	
		
		Query.updateFile(ExecptedFile);
		
		MyFile resFile = Query.downloadFile("north","2020","2");//download the exact file from the DB.
		
		assertNotEquals(ExecptedFile, resFile);
	}
}
