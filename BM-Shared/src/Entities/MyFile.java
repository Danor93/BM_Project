package Entities;

import java.io.Serializable;
import java.security.Timestamp;

public class MyFile implements Serializable {

	private static final long serialVersionUID = 3037364578344496376L;
	private String Description = null;
	private String fileName = null;
	private String quarter;
	private String date;
	private String year;
	private homeBranches homebranch;
	private int size = 0;
	public byte[] mybytearray;

	public MyFile(String fileName) {
		this.fileName = fileName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quertar) {
		this.quarter = quertar;
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

	public void initArray(int size) {
		mybytearray = new byte[size];
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

	// only for test use
	@Override
	public boolean equals(Object obj) {
		MyFile f = (MyFile) obj;
		if (f.getYear().equals(this.getYear()))
			if (f.getQuarter().equals(this.getQuarter()))
				if (f.getDate().equals(this.getDate())) {
					if (f.mybytearray.length == this.mybytearray.length) {
						for (int i = 0; i < this.getMybytearray().length; i++) {
							if (!(this.getMybytearray(i) == f.getMybytearray(i))) {
								return false;
							}
						}
						return true;
					}
				}
		return false;
	}
}
