package airline_package;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Traveler {
	int id;
	String firstName;
	String lastName;
	int age;
	Date dateOfBirth;
	String placeOfDeparture;
	String destination;
	Date travelDate;
	
	
//	This will be our ongoing assignment. We can add login screen for user and admin, admin will have privilege to add
//		flights for eg. St louis to Chicago, flight no DL231 etc. That will be displayed on booking confirmafion.
	
//	1.     Accept passenger first name, last name, age, dob, destination from, destination to, date of travel
//	2.     Create flight capacity with 50 passengers
//	3.      Add confirmed passengers and waiting list passengers separately and allow cancellations.
//	4.     Move waiting list passenger to confirmed passenger as soon as confirmed passenger is removed from the list.
//	5.     Display seat assignment as blue color, and change it to red after a particular passenger is assigned to seat.
	
	
//	Delete the following later...
//	public static void main(String[] args) {
//		SimpleDateFormat sqlDate = new SimpleDateFormat("YYYY-MM-DD");
//		java.sql.Date travelDate;
//		java.util.Date travDate;
//		travDate = sqlDate.parse("2001-11-11");
//		travelDate = new java.sql.Date(travDate.getTime());
//		Traveler abe = Traveler(1, "Abraham", "Lincoln", 99, travelDate, "ST LOUIS", "CHICAGO", travelDate);
//		airlineDAO.addToDB(abe);
//	}
	
	
	Traveler(int id, String firstName, String lastName, int age, java.sql.Date dateOfBirth, 
			String placeOfDeparture, String destination, java.sql.Date travelDate) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.dateOfBirth = dateOfBirth;
		this.placeOfDeparture = placeOfDeparture;
		this.destination = destination;
		this.travelDate = travelDate;
		
	}
	
	public int getID() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public int getAge() {
		return age;
	}
	
	public Date getDOB() {
		return dateOfBirth;
	}
	
	public String getDeparture() {
		return placeOfDeparture;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public Date getTravelDate() {
		return travelDate;
	}
	
	public String toString() {
		
		String name = firstName + " " + lastName;
		String str = id + "    " + firstName + " " + lastName + "    " + age + " years old    " + dateOfBirth +
				"    Departure: " + placeOfDeparture + "    Destination: " + destination + "    " + travelDate;
		//return str;
		return String.format("%-5d%-14s%-16s%-3d years old           %-14s      %-10s  to    %-10s  on    %-12s", 
				id, firstName, lastName, age, dateOfBirth, placeOfDeparture, destination, travelDate);
	}
}
