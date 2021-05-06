import java.sql.Time;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static List<String> stations;
	private static Line line1;
	List<List<String>> passengerList = new ArrayList<List<String>>();
	static Passenger passenger;
	
	public static void main(String args[]) {
		
		//LOADING...
		Generator.generate();
		
		//PRINT SETTINGS
//		City.printCities();
//		Station.printStations();
//		Train.printTrains();
//		Passenger.printPassengers();
//		Line.printLinesList();
//		Ticket.printTickets();
//		Line.printTimetablesList();
		
		//main program
		while (true) {
			do {
				passenger = Menu.mainMenu();
			} while (passenger == null);
			Menu.buyTicketMenu(passenger, sc);
		}
		
	}
		
		
	public static Passenger getPassenger() {
		return passenger;
	}
}