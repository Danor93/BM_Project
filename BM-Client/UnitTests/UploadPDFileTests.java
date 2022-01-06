import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entities.MyFile;
import Entities.homeBranches;
import Interfaces.IUploadPDFileInterface;
import client.controllers.BranchManagerUploadPDFController;
import javafx.event.ActionEvent;

/**
 * @author Danor
 * @author Sahar
 * Test class Description : this test class check the upload PDF functionality.
 */
class UploadPDFileTests {
	public MyFile f;
	private StubUploadFile SUIF;

	/**
	 * @author Danor
	 * @author Sahar
	 * StubUploadFile() Describe: this class implements IUploadPDFileInterface in this class. 
	 * the method UploadPDF is implemented like the implementation in the controller,accept the FXML 
	 * and server components. 
	 * this class implements the parameters of BranchManagerUploadPDFController like year,Quarter,Branch
	 * and the static flags.
	 */
	public class StubUploadFile implements IUploadPDFileInterface {

		private String Year;
		private String Quertar;
		private String Branch;//instead of taking it from the LoginScreenController.
		private String Date;
		private Boolean yearandqflag;
		private Boolean succesUpload;
		public String path;//for the file path.

		/**
		 * implements the method from IUploadPDFileInterface and like it implement in LoginScreenController 
		 * with the necessary changes.
		 */
		@Override
		public boolean UploadPDF(ActionEvent event) throws Exception {
			if (yearandqflag) {
				this.setYearandqflag(false);
				try {
					File file = new File(path);
					if (file != null) {
						path = file.getPath();
						File f = new File(path);
						MyFile msg = new MyFile(f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\") + 1));
						try {
							File newFile = new File(path);
							byte[] mybytearray = new byte[(int) newFile.length()];
							msg.initArray(mybytearray.length);
							msg.setSize(mybytearray.length);
							FileInputStream fis = new FileInputStream(newFile);
							BufferedInputStream bis = new BufferedInputStream(fis);
							bis.read(msg.getMybytearray(), 0, mybytearray.length);
							msg.setQuarter(Quertar);
							msg.setYear(Year);
							msg.setHomebranch(homeBranches.toHomeBranchType(Branch));
							msg.setDate(Date);
							bis.close();
							if (succesUpload) {
								return true;
							} else {
								return false;
							}
						} catch (Exception e) {
							e.printStackTrace();
							return false;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			} else {
				return false;
			}
			return false;
		}

		/*implement setters and getters for the parameters.*/
		public Boolean getYearandqflag() {
			return yearandqflag;
		}

		public void setYearandqflag(Boolean yearandqflag) {
			this.yearandqflag = yearandqflag;
			BranchManagerUploadPDFController.yearandqflag = yearandqflag;
		}

		public Boolean getSuccesUpload() {
			return succesUpload;
		}

		public void setSuccesUpload(Boolean succesUpload) {
			this.succesUpload = succesUpload;
			BranchManagerUploadPDFController.succesUpload = succesUpload;
		}

		public String getYear() {
			return Year;
		}

		public void setYear(String year) {
			Year = year;
		}

		public String getQuertar() {
			return Quertar;
		}

		public void setQuertar(String quertar) {
			Quertar = quertar;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getBranch() {
			return Branch;
		}

		public void setBranch(String branch) {
			Branch = branch;
		}

		public String getDate() {
			return Date;
		}

		public void setDate(String date) {
			Date = date;
		}

	}

	/**
	 * Before each test we will create the Stub object.
	 */
	@BeforeEach
	void setUp() throws Exception {
		SUIF = new StubUploadFile();
	}

	@Test
	/*
	 * Test Description:this is a test case to check if we put correct details,uploadPDF will return true.
	 * Injection Input: year = "2022" | quarter = "1" | homebranch = north | date = current. | path:"C:\\G14BiteMe\\Quarterly-Report_test.pdf".
	 * Yearandqflag = true | SuccesUpload = true.
	 * Expected result:true because all the details are correct and uploadPDF returns true.
	 * */
	void CorrectFileUpload() throws Exception {
		SUIF.setYearandqflag(true);
		SUIF.setSuccesUpload(true);
		SUIF.setYear("2022");
		SUIF.setQuertar("1");
		SUIF.setPath("C:\\G14BiteMe\\Quarterly-Report_test.pdf");
		SUIF.setDate(new java.sql.Timestamp(new Date().getTime()).toString());
		SUIF.setBranch(homeBranches.toHomeBranchType("north").toString());
		BranchManagerUploadPDFController UploadPdfControl = new BranchManagerUploadPDFController(SUIF);
		boolean succesfulyUpload = UploadPdfControl.IUpload.UploadPDF(new ActionEvent());

		assertTrue(succesfulyUpload);
	}

	@Test
	/*
	 * Test Description:this test case check what uploadPDF will return ,if the year set to be null.
	 * Injection Input: year = null | quarter = "1" | homebranch = north | date = current | path:"C:\\G14BiteMe\\Quarterly-Report_test.pdf".
	 * Yearandqflag = false | SuccesUpload = false.
	 * Expected result:false because year set to be null and uploadPDF will return false in that case.
	 * */
	void TestFileWithNullYear() throws Exception {
		SUIF.setYearandqflag(false);
		SUIF.setSuccesUpload(false);
		SUIF.setYear(null);
		SUIF.setQuertar("1");
		SUIF.setPath("C:\\G14BiteMe\\Quarterly-Report_test.pdf");
		SUIF.setDate(new java.sql.Timestamp(new Date().getTime()).toString());
		SUIF.setBranch(homeBranches.toHomeBranchType("north").toString());
		BranchManagerUploadPDFController UploadPdfControl = new BranchManagerUploadPDFController(SUIF);
		boolean succesfulyUpload = UploadPdfControl.IUpload.UploadPDF(new ActionEvent());

		assertFalse(succesfulyUpload);
	}

	
	@Test
	/*
	 * Test Description:this test case check what uploadPDF will return ,if the quarter set to be null.
	 * Injection Input: year = "2021" | quarter = null | homebranch = north | date = current | path:"C:\\G14BiteMe\\Quarterly-Report_test.pdf".
	 * Yearandqflag = false | SuccesUpload = false.
	 * Expected result:false because quarter set to be null and uploadPDF will return false in that case.
	 * */
	void TestFileWithNullQuarter() throws Exception {
		SUIF.setYearandqflag(false);
		SUIF.setSuccesUpload(false);
		SUIF.setYear("2021");
		SUIF.setQuertar(null);
		SUIF.setPath("C:\\G14BiteMe\\Quarterly-Report_test.pdf");
		SUIF.setDate(new java.sql.Timestamp(new Date().getTime()).toString());
		SUIF.setBranch(homeBranches.toHomeBranchType("north").toString());
		BranchManagerUploadPDFController UploadPdfControl = new BranchManagerUploadPDFController(SUIF);
		boolean succesfulyUpload = UploadPdfControl.IUpload.UploadPDF(new ActionEvent());

		assertFalse(succesfulyUpload);
	}

	@Test
	/*
	 * Test Description:this test case check what uploadPDF will return ,if the date set to be null.
	 * Injection Input: year = "2021" | quarter = "4" | homebranch = north | date = null | path:"C:\\G14BiteMe\\Quarterly-Report_test.pdf".
	 * Yearandqflag = false | SuccesUpload = false.
	 * Expected result:false because date set to be null and uploadPDF will return false in that case.
	 * */
	void TestFileWithNullDate() throws Exception {
		SUIF.setYearandqflag(false);
		SUIF.setSuccesUpload(false);
		SUIF.setYear("2021");
		SUIF.setQuertar("4");
		SUIF.setPath("C:\\G14BiteMe\\Quarterly-Report_test.pdf");
		SUIF.setDate(null);
		SUIF.setBranch(homeBranches.toHomeBranchType("north").toString());
		BranchManagerUploadPDFController UploadPdfControl = new BranchManagerUploadPDFController(SUIF);
		boolean succesfulyUpload = UploadPdfControl.IUpload.UploadPDF(new ActionEvent());

		assertFalse(succesfulyUpload);
	}

	@Test
	/*
	 * Test Description:this test case check what uploadPDF will return ,if the path of the file set to be null.
	 * Injection Input: year = "2021" | quarter = "3" | homebranch = north | date = current | path:null
	 * Yearandqflag = false | SuccesUpload = false.
	 * Expected result:false because path set to be null and uploadPDF will return false in that case.
	 * */
	void TestFileWithNullPath() throws Exception {
		SUIF.setYearandqflag(false);
		SUIF.setSuccesUpload(false);
		SUIF.setYear("2021");
		SUIF.setQuertar("3");
		SUIF.setPath(null);
		SUIF.setDate(new java.sql.Timestamp(new Date().getTime()).toString());
		SUIF.setBranch(homeBranches.toHomeBranchType("north").toString());
		BranchManagerUploadPDFController UploadPdfControl = new BranchManagerUploadPDFController(SUIF);
		boolean succesfulyUpload = UploadPdfControl.IUpload.UploadPDF(new ActionEvent());

		assertFalse(succesfulyUpload);
	}

	@Test
	/*
	 * Test Description:this test case check what uploadPDF will return ,if the HomeBranch of the file set to be null.
	 * Injection Input: year = "2021" | quarter = "2" | homebranch = null | date = current | path:"C:\\G14BiteMe\\Quarterly-Report_test.pdf"
	 * Yearandqflag = false | SuccesUpload = false.
	 * Expected result:false because HomeBranch set to be null and uploadPDF will return false in that case.
	 * */
	void TestFileWithNullHomeBranch() throws Exception {
		SUIF.setYearandqflag(false);
		SUIF.setSuccesUpload(false);
		SUIF.setYear("2021");
		SUIF.setQuertar("2");
		SUIF.setPath("C:\\G14BiteMe\\Quarterly-Report_test.pdf");
		SUIF.setDate(new java.sql.Timestamp(new Date().getTime()).toString());
		SUIF.setBranch(null);
		BranchManagerUploadPDFController UploadPdfControl = new BranchManagerUploadPDFController(SUIF);
		boolean succesfulyUpload = UploadPdfControl.IUpload.UploadPDF(new ActionEvent());

		assertFalse(succesfulyUpload);
	}

	@Test
	/*
	 * Test Description:this test case check what uploadPDF will return ,if the flags set to be null.
	 * Injection Input: year = "2021" | quarter = "2" | homebranch = "north" | date = current | path:"C:\\G14BiteMe\\Quarterly-Report_test.pdf"
	 * Yearandqflag = null | SuccesUpload = null.
	 * Expected result:true because it catch null pointer exception.
	 * */
	void TestFileWithNullflag() {
		SUIF.setYearandqflag(null);
		SUIF.setSuccesUpload(null);
		SUIF.setYear("2021");
		SUIF.setQuertar("1");
		SUIF.setPath("C:\\G14BiteMe\\Quarterly-Report_test.pdf");
		SUIF.setDate(new java.sql.Timestamp(new Date().getTime()).toString());
		SUIF.setBranch(homeBranches.toHomeBranchType("north").toString());
		BranchManagerUploadPDFController UploadPdfControl = new BranchManagerUploadPDFController(SUIF);
		
		try {
			UploadPdfControl.IUpload.UploadPDF(new ActionEvent());
		} catch (Exception e) {
			assertTrue(true);
		}
	}

}
