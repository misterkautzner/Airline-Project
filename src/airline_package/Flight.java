package airline_package;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Flight {

	private static int capacity = 50;
	private static List<Traveler> passengerList = new ArrayList<Traveler>();
	private static List<Traveler> waitingList = new ArrayList<Traveler>();
	private static AirlineDAO airlineDAO = new AirlineDAO();
	
	public static void main(String args[]) {
		
//		printPassengers();
//		System.out.println("");
//		System.out.println("");
//		System.out.println("");
//		
//		Traveler sadGuy = passengerList.get(22);
//		System.out.println(sadGuy);
//		airlineDAO.cancelFlight(sadGuy);
		
//		System.out.println(getPassengerList());
		
		
	}
	
	static void printPassengers() {
		passengerList = airlineDAO.showPassengers();
		for (Traveler passenger: passengerList) {
			System.out.println(passenger);
		}
	}
	
	public static List<Traveler> getPassengerList() {
		return airlineDAO.showPassengers();
	}
	

	
	
	
}
