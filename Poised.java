import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Poised {

	public static void main(String[] args) {
		
		try {
			Scanner scanner = new Scanner(System.in);
			Formatter f = new Formatter("Projects.txt");
			ArrayList<Object> completedProjects = new ArrayList<Object>();
			ArrayList<Object> currentProjects = new ArrayList<Object>();

			Scanner input = new Scanner(System.in);
			//Projects'details
			System.out.print("Project details:\n");
		
			System.out.print("Enter the project name:\n");
			String projectName = input.nextLine();
			System.out.print("Enter the project number:\n");
			int projectNumber = input.nextInt();
			input.nextLine();
			System.out.print("Enter the project ERF number:\n");
			String projectERFnumber = input.nextLine();
			System.out.print("Enter the project type of building:\n");
			String projectTOB = input.nextLine();
			System.out.print("Enter the project address:\n");
			String projectAddress = input.nextLine();
			System.out.print("Enter the project deadline: dd/mm/yyyy \n");
			String projectDeadline = input.nextLine();
			System.out.print("Enter the total fee:\n");
			double amtDue = input.nextDouble();
			input.nextLine();
			System.out.print("Enter the amount paid to date:\n");
			double amtPaid = input.nextDouble();
			input.nextLine();
		
			//Contractors' details
			System.out.print("\nContractors' details:\n");
		
			System.out.print("Enter the contractor of the projects' name: \n");
			String contractorName = input.nextLine();
			System.out.print("Enter the contractor of the projects' surname: \n");
			String contractorSurname = input.nextLine();
			System.out.print("Enter the contractor of the projects' telephone: \n");
			String contractorTelephone = input.nextLine();
			System.out.print("Enter the contractor of the projects' email: \n");
			String contractorEmail = input.nextLine();
			System.out.print("Enter the contractor of the projects' address: \n");
			String contractorAddress = input.nextLine();
		
		
			//Architects' details
			System.out.print("\nArchitects' details\n");
		
			System.out.print("Enter the architect of the projects' name: \n");
			String architectName = input.nextLine();
			System.out.print("Enter the architect of the projects' surname: \n");
			String architectSurname = input.nextLine();
			System.out.print("Enter the architect of the projects' telephone: \n");
			String architectTelephone = input.nextLine();
			System.out.print("Enter the architect of the projects' email: \n");
			String architectEmail = input.nextLine();
			System.out.print("Enter the architect of the projects' address: \n");
			String architectAddress = input.nextLine();
		
			//Customers' details
			System.out.print("\nCustomers' details:\n");
		
			System.out.println("Enter the name of the customer:");
			String cuName = input.nextLine(); 
			System.out.print("Enter the customers' surname:\n");
			String cuSurname = input.nextLine();
			System.out.println("Enter the customers' telephone number:");
			String cuTelephone = input.nextLine(); 
			System.out.println("Enter the customers' email address:");
			String cuEmail = input.nextLine(); 
			System.out.println("Enter the customers' address:");
			String cuAddress = input.nextLine(); 
		
			projectName = emptyProjectName(projectName, projectTOB, cuSurname);
			Project project = new Project(projectName, projectNumber, projectERFnumber, projectTOB, projectAddress, projectDeadline, amtDue, amtPaid, false);
			
			Person projectArchitect = new Person(architectName, architectSurname, architectTelephone, architectEmail, architectAddress);
			Person projectContractor = new Person(contractorName, contractorSurname, contractorTelephone, contractorEmail, contractorAddress);
			Person projectCustomer = new Person(cuName, cuSurname, cuTelephone, cuEmail, cuAddress);
			
			currentProjects.add(project.toString());
			currentProjects.add(projectArchitect.toString());
			currentProjects.add(projectContractor.toString());
			currentProjects.add(projectCustomer.toString());
			
			System.out.println("\nYour project has been successfully added as: \n" + projectName);
			
			int i = 1;
			while (i != 0) {
				System.out.println("\n1: Add a task \n2: Update details of a project \n3: See unfinished projects");
				System.out.println("\4:Find a project \n5: Quit\n");
				int firstChoice = scanner.nextInt();
					
				//Add a project
				if (firstChoice == 1) {
					addProject();
					currentProjects.add(project.toString());
					currentProjects.add(projectArchitect.toString());
					currentProjects.add(projectContractor.toString());
					currentProjects.add(projectCustomer.toString());
				}
				
				if (firstChoice == 2) {
					int j = 1;
					while (j != 0) {
						String menuOption = print(scanner);
				
						//Update projects deadline
						if (menuOption.equals("i")) {
							i(scanner, project);
							}
						
						//Change amount paid to date
						if (menuOption.equals("ii")) {
							ii(scanner, project);
							}
						
						//Update contractors' contact details
						if (menuOption.equals("iii")) {
							System.out.println("The contractor of the projects' details is: " + projectContractor.output());
							System.out.println("\nUpdate contractors': \n1: telephone \n2: email \n3: address \n4: Leave as is");
							int choice = scanner.nextInt();
							scanner.nextLine();
							//Update telephone
							if (choice == 1) {
								newTel(scanner, projectContractor);
							}
							//Update email
							if (choice == 2) {
								newEmail(scanner, projectContractor);
							}
							//Update address
							if(choice == 3) {
								newAddress(scanner, projectContractor);
							}
						}
					
						//Finalize a project
						if (menuOption.equals("vi")) {
							double outstanding = project.amtDue - project.amtPaid;
							//Money outstanding
							if (outstanding > 0) {
								System.out.print("\nInvoice: \nTotal Fee: " + project.amtDue);
								System.out.print("\nAmount paid: " + project.amtPaid);
								System.out.println("\nOutstanding: R" + project.amtDue + " - " + project.amtPaid + " = " + outstanding);
								System.out.println("\nCustomer's contact details: " + projectCustomer.output());
								System.out.println("\nThe invoice has been send to the customer.\n");
							}
							//Finances settled
							else {
								project.isCompleted();
								if (project.isCompleted()) {
									String date = date();
									System.out.println("\nProjects' completion date: " + date);
									System.out.println("Finances of this project has been fully settled.\n");
									currentProjects.remove(project);
									currentProjects.remove(projectArchitect);
									currentProjects.remove(projectContractor);
									currentProjects.remove(projectCustomer);
									completedProjects.add(project);
								}
							}
							//Breaking the update menu loop
							break;
						}
					}
				}
				//See current projects
				if (firstChoice == 3) {
					if (!project.isCompleted) {
						System.out.println("Project detials:" + project.output());
						System.out.println("\nArchitect details:" + projectArchitect.output());
						System.out.println("\nContractor details:" + projectContractor.output());
						System.out.println("\nCustomer details:" + projectCustomer.output());
					}
				}
				//Find a project
				if (firstChoice == 4) {
					System.out.println("name: Search by name \nnumber: Search by project number \n:");
					String nextChoice = input.nextLine();
					//Search by name
					if (nextChoice.equals("name")) {
						System.out.println("Enter the project name");
						String name = input.nextLine();
						if (name.equals(project.projectName)) {
							System.out.println("Project detials:" + project.output());
							System.out.println("\nArchitect details:" + projectArchitect.output());
							System.out.println("\nContractor details:" + projectContractor.output());
							System.out.println("\nCustomer details:" + projectCustomer.output());
						}
						else {
							System.out.print("No project with this name exists");
						}
					}
					//Search by number
					if (nextChoice.equals("number")) {
						System.out.println("Enter the project number");
						int number = input.nextInt();
						input.nextLine();
						if (number == project.projectNumber) {
							System.out.println("Project detials:" + project.output());
							System.out.println("\nArchitect details:" + projectArchitect.output());
							System.out.println("\nContractor details:" + projectContractor.output());
							System.out.println("\nCustomer details:" + projectCustomer.output());
						}
						else {
							System.out.println("No project with that number exists.");
						}
					}
				}
				
				//Quit
				if (firstChoice == 5) {
					try {
						f.format("%s %s %s %s", "Project details:" + project.output(), "\n\nArchitect details:" + projectArchitect.output(), "\n\nContractor details:" + projectContractor.output(), "\n\nCustomer details:" + projectCustomer.output());
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					System.out.print("Goodbye");
					f.close();
					break;
				}
			}
		}
		//Error message to display in case of an error
		catch (Exception e){
			System.out.print("Enter an integer for project number, total fee and amount paid.");
		}
	}
	
	//Currect date
	private static String date() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.now();
		String date = dateFormatter.format(localDate);
		return date;
	}
	
	//Menu for updating projects
	private static String print(Scanner scanner) {
		System.out.println("\ni: Update projects' deadline");
		System.out.println("ii: Update amount paid to date");
		System.out.println("iii: Update contractor's contact details");
		System.out.println("vi: finalise a project");
		System.out.println("When done select finalize");
		String menuOption = scanner.nextLine();
		return menuOption;
	}
	
	//No projectname provided
	private static String emptyProjectName(String projectName, String projectTOB, String cuSurname) {
		if (projectName.isEmpty()) {
			projectName = (projectTOB + cuSurname);
		}
		return projectName;
	}
	
	//Update contractors address
	private static void newAddress(Scanner scanner, Person projectContractor) {
		System.out.println("Enter the new address:\n");
		String newAddress = scanner.nextLine();
		projectContractor.address = newAddress;
	}
	
	//Update contractors email
	private static void newEmail(Scanner scanner, Person projectContractor) {
		System.out.println("Enter the new email:\n");
		String newEmail = scanner.nextLine();
		projectContractor.eMail = newEmail;
	}
	
	//Update contractors telephonr
	private static void newTel(Scanner scanner, Person projectContractor) {
		System.out.println("Enter the new telephone number:");
		String newTelephone = scanner.nextLine();
		projectContractor.telephone = newTelephone;
	}
	
	//Update deadline
	private static void i(Scanner scanner, Project project) {
		System.out.println("\nThe projects' deadline is " + project.projectDeadline);
		System.out.println("Enter the new deadline: dd/mm/yyyy");
		String newDeadline = scanner.nextLine();
		project.projectDeadline = newDeadline;
		}
	
	//Update amount paid
	private static void ii(Scanner scanner, Project project) {
		System.out.println("\nThe projects' amount paid to date is: \n" + project.amtPaid);
		System.out.println("Enter the new amount paid:\n");
		int newAmtPaid = scanner.nextInt();
		scanner.nextLine();
		project.amtPaid = newAmtPaid;
	}
	
	//Add project
	public static void addProject() {
		Scanner input = new Scanner(System.in);
		//Projects'details
		System.out.print("Project details:\n");
		System.out.print("Enter the project name:\n");
		String projectName = input.nextLine();
		System.out.print("Enter the project number:\n");
		int projectNumber = input.nextInt();
		input.nextLine();
		System.out.print("Enter the project ERF number:\n");
		String projectERFnumber = input.nextLine();
		System.out.print("Enter the project type of building:\n");
		String projectTOB = input.nextLine();
		System.out.print("Enter the project address:\n");
		String projectAddress = input.nextLine();
		System.out.print("Enter the project deadline: dd/mm/yyyy \n");
		String projectDeadline = input.nextLine();
		System.out.print("Enter the total fee:\n");
		double amtDue = input.nextDouble();
		input.nextLine();
		System.out.print("Enter the amount paid to date:\n");
		double amtPaid = input.nextDouble();
		input.nextLine();
	
		//Contractors' details
		System.out.print("\nContractors' details:\n");
		System.out.print("Enter the contractor of the projects' name: \n");
		String contractorName = input.nextLine();
		System.out.print("Enter the contractor of the projects' surname: \n");
		String contractorSurname = input.nextLine();
		System.out.print("Enter the contractor of the projects' telephone: \n");
		String contractorTelephone = input.nextLine();
		System.out.print("Enter the contractor of the projects' email: \n");
		String contractorEmail = input.nextLine();
		System.out.print("Enter the contractor of the projects' address: \n");
		String contractorAddress = input.nextLine();
	
		//Architects' details
		System.out.print("\nArchitects' details");
		System.out.print("Enter the architect of the projects' name: \n");
		String architectName = input.nextLine();
		System.out.print("Enter the architect of the projects' surname: \n");
		String architectSurname = input.nextLine();
		System.out.print("Enter the architect of the projects' telephone: \n");
		String architectTelephone = input.nextLine();
		System.out.print("Enter the architect of the projects' email: \n");
		String architectEmail = input.nextLine();
		System.out.print("Enter the architect of the projects' address: \n");
		String architectAddress = input.nextLine();
	
		//Customers' details
		System.out.print("\nCustomers' details:\n");
		System.out.print("Enter the customers' surname:\n");
		String cuSurname = input.nextLine();
		System.out.println("Enter the name of the customer:");
		String cuName = input.nextLine(); 
		System.out.println("Enter the customers' telephone number:");
		String cuTelephone = input.nextLine(); 
		System.out.println("Enter the customers' email address:");
		String cuEmail = input.nextLine(); 
		System.out.println("Enter the customers' address:");
		String cuAddress = input.nextLine(); 
	
		projectName = emptyProjectName(projectName, projectTOB, cuSurname);
		Project project = new Project(projectName, projectNumber, projectERFnumber, projectTOB, projectAddress, projectDeadline, amtDue, amtPaid, false);
		Person projectArchitect = new Person(architectName, architectSurname, architectTelephone, architectEmail, architectAddress);
		Person projectContractor = new Person(contractorName, contractorSurname, contractorTelephone, contractorEmail, contractorAddress);
		Person projectCustomer = new Person(cuName, cuSurname, cuTelephone, cuEmail, cuAddress);
		
		System.out.println("\nYour project has been successfully added as: \n" + projectName);
	}
}