package ABS.MODEL;

public class Patient 
{
	private int patientID;
	private String patientName;
	private String patientPhoneNo;
	private String patientEmail;
	private String patientPassword;
	private boolean valid;

	public Patient()
	{
		super();
	}
	
	//Normal Constructor
	public Patient(int patientID, String patientName, String patientPhoneNo, String patientEmail, String patientPassword)
	{
		this.patientID = patientID;
		this.patientName = patientName;
		this.patientPhoneNo = patientPhoneNo;
		this.patientEmail = patientEmail;
		this.patientPassword = patientPassword;
	}
	
	//Getter & Setter
	public int getPatientID() {
		return patientID;
	}
	
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	
	public String getPatientName() {
		return patientName;
	}
	
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	public String getPatientPhoneNo() {
		return patientPhoneNo;
	}
	
	public void setPatientPhoneNo(String patientPhoneNo) {
		this.patientPhoneNo = patientPhoneNo;
	}
	public String getPatientEmail() {
		return patientEmail;
	}
	
	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}
	
	public String getPatientPassword() {
		return patientPassword;
	}
	
	public void setPatientPassword(String patientPassword) {
		this.patientPassword = patientPassword;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}




