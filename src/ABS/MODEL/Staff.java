package ABS.MODEL;

public class Staff 
{
	private int staffID;
	private String staffName;
	private String staffPhoneNo;
	private String staffEmail;
	private String staffPassword;
	private boolean valid;

	public Staff()
	{
		super();
	}
	
	//Normal Constructor
	public Staff(int staffID, String staffName, String staffPhoneNo, String staffEmail, String staffPassword)
	{
		this.staffID = staffID;
		this.staffName = staffName;
		this.staffPhoneNo = staffPhoneNo;
		this.staffEmail = staffEmail;
		this.staffPassword = staffPassword;
	}
	
	public int getStaffID() {
		return staffID;
	}
	
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}
	
	public String getStaffName() {
		return staffName;
	}
	
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	
	public String getStaffPhoneNo() {
		return staffPhoneNo;
	}
	
	public void setStaffPhoneNo(String staffPhoneNo) {
		this.staffPhoneNo = staffPhoneNo;
	}
	public String getStaffEmail() {
		return staffEmail;
	}
	
	public void setStaffEmail(String staffEmail) {
		this.staffEmail = staffEmail;
	}
	
	public String getStaffPassword() {
		return staffPassword;
	}
	
	public void setStaffPassword(String staffPassword) {
		this.staffPassword = staffPassword;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}

