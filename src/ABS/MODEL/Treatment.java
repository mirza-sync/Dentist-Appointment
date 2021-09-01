package ABS.MODEL;

public class Treatment {
	private int treatmentID;
	private String treatmentName;
	private int treatmentDuration;
	private double treatmentPrice;
	
	public Treatment() {
		super();
	}

	public Treatment(int treatmentID, String treatmentName, int treatmentDuration, double treatmentPrice) {
		super();
		this.treatmentID = treatmentID;
		this.treatmentName = treatmentName;
		this.treatmentDuration = treatmentDuration;
		this.treatmentPrice = treatmentPrice;
	}

	public int getTreatmentID() {
		return treatmentID;
	}

	public void setTreatmentID(int treatmentID) {
		this.treatmentID = treatmentID;
	}

	public String getTreatmentName() {
		return treatmentName;
	}

	public void setTreatmentName(String treatmentName) {
		this.treatmentName = treatmentName;
	}

	public int getTreatmentDuration() {
		return treatmentDuration;
	}

	public void setTreatmentDuration(int treatmentDuration) {
		this.treatmentDuration = treatmentDuration;
	}

	public double getTreatmentPrice() {
		return treatmentPrice;
	}

	public void setTreatmentPrice(double treatmentPrice) {
		this.treatmentPrice = treatmentPrice;
	}
}
