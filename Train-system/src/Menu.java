import java.util.Scanner;

public class Menu {

	Scanner sc = new Scanner(System.in);

	static Station st;
	static Station end;

	public static void adminMode() {
		Scanner sc = new Scanner(System.in);
		System.out.println("### ADMIN MODE ###");
		System.out.println("Input admin password: ");
		String adminPassword = sc.next();
		if (adminPassword.equals("3igen3ggy")) {
			adminPanel();
		} else {
			System.out.print("\nWrong password\n");
		}
	}

	private static void adminPanel() {
		int choice;
		do {
			Scanner sc = new Scanner(System.in);
			System.out.println("3igen3ggy's train lines ADMIN PANEL\n");
			System.out.println("1) Show cities database");
			System.out.println("2) Show stations database");
			System.out.println("3) Show trains list");
			System.out.println("4) Show passengers database");
			System.out.println("5) Show train lines");
			System.out.println("6) Show tickets in database");
			System.out.println("7) Show timetables");
			System.out.println("0) EXIT\n");
			System.out.println("Choice: ");
			choice = sc.nextInt();
			
			if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6 && choice != 7 && choice != 0) {
				System.out.println("###Incorrect choice###");
			} else {
				if (choice == 1) City.printCities();
				if (choice == 2) Station.printStations();
				if (choice == 3) Train.printTrains();
				if (choice == 4) Passenger.printPassengers();
				if (choice == 5) Line.printLinesList();
				if (choice == 6) Ticket.printTickets();
				if (choice == 7) Line.printTimetablesList();	
				if (choice == 0) System.out.println("LOGGING OUT\n");
			} 
		} while (choice != 0);
	}

	public static Passenger mainMenu() {
		Passenger passenger = null;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.printf("\n\nWelcome to 3igen3ggy's Train Lines\n\n");
			System.out.println("1) LOGIN");
			System.out.println("2) CREATE ACCOUNT");
			System.out.println("3) ADMIN PANEL");
			System.out.println("CHOICE: ");
			int choice = sc.nextInt();
			if (choice != 1 && choice != 2 && choice != 3) {
				System.out.println("###Incorrect choice###");
			} else {
				if (choice == 1) passenger = loginMenu();
				if (choice == 2) createAccountMenu(); 
				if (choice == 3) adminMode(); 
			}
			
		} while (passenger == null);
		
		System.out.print("\n###SUCCESFULLY LOGGED IN### \n");
		return passenger;
	}
	
	public static Station getSt() {
		return st;
	}

	public static Station getEnd() {
		return end;
	}

	public static void buyTicketMenu(Passenger passenger, Scanner sc) {
				
		do {
			Station.printStations();
			System.out.print("\nPassenger: " + passenger.getName() + " " + passenger.getSurname() + ", please enter start station: \n");		
			long start = sc.nextLong();
			System.out.print("Please enter destination station: \n");
			long dest = sc.nextLong();
			
			if (start == 0 || dest == 0) {
				break;
			}
			
			st = Station.findStationByID(start);
			end = Station.findStationByID(dest);
			
			if (st == null || end == null) {
				System.out.println("Incorrect starting point or destination");
			} else {
				break;
			}
			
		} while (true);
		BuyTicket.findLines(st.getName(), end.getName());
	}
	
	public static Passenger loginMenu() {
		Passenger passenger;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.printf("\n\nLOG IN TO ACCOUNT\n\n");	
			System.out.print("Please enter your account ID: \n");
			long ID = sc.nextLong();
			System.out.print("Please enter your account password: \n");
			String pass = sc.next();
			passenger = Passenger.findPassengerByID(ID);
			if (passenger == null || !passenger.getPass().equals(pass)) {
				System.out.println("WRONG ID OR PASSWORD");
			}
		} while (passenger == null);
		return passenger;
	}
	
	public static void createAccountMenu() {
		Scanner sc = new Scanner(System.in);

		System.out.printf("\n\nCREATE NEW ACCOUNT\n\n");
		System.out.println("Enter your first name: ");
		String name = sc.next();
		System.out.println("Enter your last name: ");
		String surname = sc.next();
		System.out.println("Enter your email: ");
		String mail = sc.next();
		System.out.println("Enter your password: ");
		String pass = sc.next();
		
		Passenger p = new Passenger(name, surname, mail, pass);
			
		System.out.println("\nYour ID for login is: " + p.getID());
		System.out.println("You can now login");
	}
}