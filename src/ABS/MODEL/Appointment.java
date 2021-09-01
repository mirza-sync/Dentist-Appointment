package ABS.MODEL;

public class Appointment {
	private int appointmentID;
	private String appointmentDate;
	private String startTime;
	private int patientID;
	private int treatmentID;
	private int dentistID;
	private int staffID;
	private boolean valid;
	private Treatment treatment;
	private Dentist dentist;
	private Patient patient;

	public Appointment() {
		super();

	}

	public Appointment(int staffID, int patientID, int treatmentID, int dentistID, int appointmentID, String appointmentDate, String startTime, boolean valid) {
		this.appointmentID = appointmentID;
		this.appointmentDate = appointmentDate;
		this.startTime = startTime;
		this.staffID = staffID;
		this.patientID = patientID;
		this.treatmentID = treatmentID;
		this.dentistID = dentistID;
	}

	public int getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public int getDentistID() {
		return dentistID;
	}

	public void setDentistID(int dentistID) {
		this.dentistID = dentistID;
	}

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public int getTreatmentID() {
		return treatmentID;
	}

	public void setTreatmentID(int treatmentID) {
		this.treatmentID = treatmentID;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public Treatment getTreatment() {
		return treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}
	
	public Dentist getDentist() {
		return dentist;
	}

	public void setDentist(Dentist dentist) {
		this.dentist = dentist;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
