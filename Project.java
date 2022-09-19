
public class Project {
	String projectName;
	int projectNumber;
	String projectERFnumber;
	String projectTOB;
	String projectAddress;
	String projectDeadline;
	double amtDue;
	double amtPaid;
	boolean isCompleted = false;
		
	public Project(String projectName, int projectNumber, String projectERFnumber, String projectTOB, String projectAddress, String projectDeadline, double amtDue, double amtPaid, boolean isCompleted) {
		this.projectName = projectName;
		this.projectNumber = projectNumber;
		this.projectERFnumber = projectERFnumber;
		this.projectTOB = projectTOB;
		this.projectAddress = projectAddress;
		this.projectDeadline = projectDeadline;
		this.amtDue = amtDue;
		this.amtPaid = amtPaid;
	}
	
	public boolean isCompleted() {
		return isCompleted = true;
	}
	
	public StringBuilder output() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nProject number: " + projectNumber);
		sb.append("\nProject name: " + projectName);
		sb.append("\nBuilding type: " + projectTOB);
		sb.append("\nProject Address: " + projectAddress);
		sb.append("\nERF number: " + projectERFnumber);
		sb.append("\nProject fee: " + amtDue);
		return sb;
		}
}