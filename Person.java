
public class Person {
	String personName;
	String personSurname;
	String telephone;
	String eMail;
	String address;
		
	public Person(String personSurname, String personName, String telephone, String eMail, String address)
	{
	this.personName = personName;
	this.personSurname = personSurname;
	this.telephone = telephone;
	this.eMail = eMail;
	this.address = address;
	}

	public StringBuilder output() {
		StringBuilder toString = new StringBuilder();
		toString.append("\nName:  " + personName);
		toString.append("\nTelehone:  " + telephone);
		toString.append("\nE-Mail:  " + eMail);
		toString.append("\nAddress:  " + address);
		return toString; 
	}
}