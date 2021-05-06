import java.util.ArrayList;
import java.util.List;

public class Passenger {
	private String name;
	private String surname;
	private String pass;
	private long ID;
	private String mail;
	static List<Passenger> passengersList = new ArrayList<Passenger>();
	
	public Passenger(String name, String surname, String mail, String pass) {
		this.ID = Generator.createPassengerID();
		this.name = name;
		this.surname = surname;
		this.pass = pass;
		this.mail = mail;
		
		//creating a new entry to a passengers list
		passengersList.add(this);
	}
	
	public String getMail() {
		return mail;
	}

	public static List<Passenger> getPassengers() {
		return passengersList;
	}
	
	public static Passenger findPassengerByID(long ID) {
		List<Passenger> passengersList = getPassengers();
		for (int i = 0; i < passengersList.size(); i++) {
			if (ID == passengersList.get(i).getID()) return passengersList.get(i);
		}
		return null;
	}
	
	public static boolean checkLoginPassenger(long ID, String pass) {
		Passenger passenger = findPassengerByID(ID);
		if (passenger.getPass().equals(pass)) return true;
		else return false;
	}
	
	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getPass() {
		return pass;
	}

	public long getID() {
		return ID;
	}

	public static List<Passenger> getPassengersList() {
		return passengersList;
	}

	public static void printPassengers() {
		List<Passenger> passengers = getPassengers();
		System.out.println("\n##################################################");
		System.out.println("PASSENGER ACCOUNTS LIST: ");
		for (int i = 0; i < passengers.size(); i++) {
			System.out.println("ID: " + passengers.get(i).getID() + ", name: " + passengers.get(i).getName() +
					" " + passengers.get(i).getSurname() + ", e-mail: " + passengers.get(i).getMail());
		}
		System.out.println("##################################################");
	}
}