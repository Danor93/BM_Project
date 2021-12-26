package Entities;

import java.io.Serializable;
import java.sql.Blob;

public class MyFile implements Serializable {

	
	private static final long serialVersionUID = 3037364578344496376L;
	private String Description = null;
	private String fileName = null;
	private String quertar;
	private String date;
	private String year;
	private homeBranches homebranch;

	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public String getQuertar() {
		return quertar;
	}

	public void setQuertar(String quertar) {
		this.quertar = quertar;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHomebranch() {
		return homebranch.toString();
	}

	public void setHomebranch(homeBranches homebranch) {
		this.homebranch = homebranch;
	}

	private int size = 0;
	public byte[] mybytearray;

	public void initArray(int size) {
		mybytearray = new byte[size];
	}

	public MyFile(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public byte[] getMybytearray() {
		return mybytearray;
	}

	public byte getMybytearray(int i) {
		return mybytearray[i];
	}

	public void setMybytearray(byte[] mybytearray) {

		for (int i = 0; i < mybytearray.length; i++)
			this.mybytearray[i] = mybytearray[i];
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
}
