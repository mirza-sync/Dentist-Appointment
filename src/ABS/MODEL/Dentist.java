package ABS.MODEL;

public class Dentist 
{
	private int dentistID ;	
	private String dentistName ; 
	private String dentistPassword ;
	private String dentistPhoneNo ;
	private String dentistEmail ;
	private boolean valid ;
	
	public Dentist()
	{
		super();
	}

	//Normal Constructor
	public Dentist(int dentistID, String dentistName, String dentistPhoneNo, String dentistAddress, 
			String dentistEmail, String dentistPassword)
	{
		this.dentistID = dentistID;
		this.dentistName = dentistName;
		this.dentistPhoneNo = dentistPhoneNo;
		this.dentistEmail = dentistEmail;
		this.dentistPassword = dentistPassword;
	}

	//Getter and Setter
	public int getDentistID() {
		return dentistID;
	}
	public void setDentistID(int dentistID) {
		this.dentistID = dentistID;
	}
	public String getDentistName() {
		return dentistName;
	}
	public void setDentistName(String dentistName) {
		this.dentistName = dentistName;
	}
	public String getDentistPassword() {
		return dentistPassword;
	}
	public void setDentistPassword(String dentistPassword) {
		this.dentistPassword = dentistPassword;
	}
	public String getDentistPhoneNo() {
		return dentistPhoneNo;
	}
	public void setDentistPhoneNo(String dentistPhoneNo) {
		this.dentistPhoneNo = dentistPhoneNo;
	}
	public String getDentistEmail() {
		return dentistEmail;
	}
	public void setDentistEmail(String dentistEmail) {
		this.dentistEmail = dentistEmail;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}

