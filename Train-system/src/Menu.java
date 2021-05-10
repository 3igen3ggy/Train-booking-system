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
			System.out.print("\n###########\n");
			System.out.println("3igen3ggy's\ntrain lines\nADMIN PANEL\n OVERVIEW\n###########\n");
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
			System.out.println("0) EXIT");
			System.out.println("CHOICE: ");
			int choice = sc.nextInt();
			if (choice != 1 && choice != 2 && choice != 3 && choice != 0) {
				System.out.println("###Incorrect choice###");
			} else {
				if (choice == 1) passenger = loginMenu();
				if (choice == 2) createAccountMenu(); 
				if (choice == 3) adminMode(); 
				if (choice == 0) System.exit(0);
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
		String name = "";
		String surname = "";
		String mail;
		String pass;
		boolean valid;
		
		System.out.printf("\n\nCREATE NEW ACCOUNT\n\n");
		do {
			System.out.println("Enter your first name: ");
			name = sc.next();
		} while(name.length() < 2);
		
		do {
			System.out.println("Enter your last name: ");
			surname = sc.next();
		} while (surname.length() < 2);
		
		do {
			System.out.println("Enter your email: ");
			mail = sc.next();
			valid = emailCheck(mail);
		} while (!valid);
		
		do {
			System.out.println("Enter your password (at least 5 characters): ");
			pass = sc.next();
		} while (pass.length() < 5);
		
		Passenger p = new Passenger(name, surname, mail, pass);
			
		System.out.println("\nYour ID for login is: " + p.getID());
		System.out.println("You can now login");
	}
	
	@SuppressWarnings("deprecation")
	public static boolean emailCheck(String email) {
		boolean atFlag = false;
		boolean dotFlag = false;
		
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@') atFlag = true;
			if (email.charAt(i) == '.') dotFlag = true;
		}
		if (!atFlag || !dotFlag) return false;
		
		String[] a = email.split("@");
		String[] b = a[1].split("\\.");
		
		String[] elementsOfEmail = new String[3];
		elementsOfEmail[0] = a[0];
		elementsOfEmail[1] = b[0];
		elementsOfEmail[2] = b[1];
		
		if (elementsOfEmail[0].length() < 3) return false;
		if (elementsOfEmail[2].length() != 2 && elementsOfEmail[2].length() != 3) return false;
		
		for (int i = 0; i < elementsOfEmail[0].length(); i++) {
			char c = elementsOfEmail[0].charAt(i);
			if (!Character.isJavaLetterOrDigit(c) && c != '_') return false;
		}
		return true;
	}
}