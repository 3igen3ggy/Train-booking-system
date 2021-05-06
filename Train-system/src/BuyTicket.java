import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuyTicket {

	private static List<Line> lines = Line.getLinesList();
	private static List<List<Long>> optionsTime = new ArrayList<List<Long>>();
	private static List<List<List<String>>> timetablesList = Line.getTimetablesList();
	private static List<List<List<String>>> specificTimetablesList = new ArrayList<List<List<String>>>();
	static long a;
	static String b;

	public static List<Line> findLines(String start, String destination) {
		List<Train> foundTrains = new ArrayList<Train>();
		List<Line> foundLines = new ArrayList<Line>();
		
		int st = 0;
		int end = 0;
		boolean stFlag = false;
		boolean endFlag = false;
		
		//finding all transport possibilities from point A to point B, they will be listed for a passenger to choose from
		for (int i = 0; i < lines .size(); i++) {
			List<List<String>> entryentry = new ArrayList<List<String>>();
			List<String> entry = new ArrayList<String>();
			Line checkLine = lines.get(i);
			List<Station> stations = checkLine.getList();
			int n = stations.size();
			for (int j = 0; j < n; j++) {
				if (stations.get(j).getName().equals(start)) {
					st = j;
					stFlag = true;
				}

				if (stations.get(j).getName().equals(destination)) {
					end = j;
					endFlag = true;
				}
			}
			if (st < end && end !=  0 && stFlag && endFlag) {
				for (int j = st; j <= end; j++) {
					entry = timetablesList.get(i).get(j + 1);
					entryentry.add(entry);
				}
				entryentry.add(0, timetablesList.get(i).get(0));
			}
			
			if (end > st && stFlag && endFlag) foundLines.add(checkLine);
			foundTrains.add(checkLine.getTrain());
			stFlag = false;
			endFlag = false;
			st = 0;
			end = 0;
			if (!entryentry.isEmpty()) specificTimetablesList.add(entryentry);		
		}
		
		//add to options table times: Start time, End time, Travel time
		for (int i = 0; i < specificTimetablesList.size(); i++) {
			List<Long> optionsEntry = new ArrayList<Long>();
			int len = specificTimetablesList.get(i).size();
			long stTime = Long.parseLong(specificTimetablesList.get(i).get(1).get(1));
			optionsEntry.add(stTime);
			long enTime = Long.parseLong(specificTimetablesList.get(i).get(len - 1).get(1)); 
			optionsEntry.add(enTime);
			long dt = enTime - stTime;
			optionsEntry.add(dt);
			optionsTime.add(optionsEntry);
		}
		printOptions();
		return foundLines;
	}
	
	//print options for passenger to choose from
	public static void printOptions() {
		
		int len = optionsTime.size();
		
		//list transport possibilities and hours of start, end and total time 
		for (int i = 0; i < len; i++) {
			System.out.print("\n" + (i + 1) + ") (" + specificTimetablesList.get(i).get(0).get(0) + ") " + specificTimetablesList.get(i).get(0).get(1));
			for (int j = 0; j < 3; j++) {
				Time time = new Time(optionsTime.get(i).get(j));
				if (j == 0) System.out.print(": start: " + (time.getHours() - 1) + ":" + time.getMinutes());
				if (j == 1) System.out.print(", end: " + (time.getHours() - 1) + ":" + time.getMinutes());
				if (j == 2) System.out.print(", travel time: " + (time.getHours() - 1) + ":" + time.getMinutes() + "\n");
			}			
		}
		Scanner sc = new Scanner(System.in);
		if (len == 0) {
			System.out.print("\nSorry, no direct connection found\nLOGGING OUT");
			return;
		}
		System.out.println("Choose train (1-" + len + "):");
		int option = sc.nextInt();
		
		if (option >= 1 && option <= len) {
			System.out.println("BOOKING TICKET FOR TRAIN: ");
			System.out.print("\n" + (option) + ") (" + specificTimetablesList.get(option - 1).get(0).get(0) + ") " + specificTimetablesList.get(option - 1).get(0).get(1));
			
			//line ID
			a = Integer.parseInt(specificTimetablesList.get(option - 1).get(0).get(0));
			
			//train ID
			b = specificTimetablesList.get(option - 1).get(0).get(1);
			
			//getting times again for ticket printing purpose
			for (int j = 0; j < 3; j++) {
				Time time = new Time(optionsTime.get(option - 1).get(j));
				if (j == 0) System.out.print(": start: " + (time.getHours() - 1) + ":" + time.getMinutes());
				if (j == 1) System.out.print(", end: " + (time.getHours() - 1) + ":" + time.getMinutes());
				if (j == 2) System.out.print(", travel time: " + (time.getHours() - 1) + ":" + time.getMinutes() + "\n");
			}	
			Time time = new Time(optionsTime.get(option - 1).get(2));
			long price = ((time.getHours() - 1) * 60 + time.getMinutes()) / (long) 2L;
			System.out.println("price: " + price + "PLN, approve?");
			System.out.println("(Y/N)");
			
			String choice = sc.next();
			
			//if ticket is approved 
			if (choice.equals("y") || choice.equals("Y")) {
				Ticket tick = new Ticket(Line.findLineByID(a), Main.getPassenger(), Menu.getSt(), Menu.getEnd());
				System.out.println("### TICKET CONFIRMED ###");
				System.out.println("Going back to main menu, please print following ticket:\n");
				
				Time startingTime = new Time(optionsTime.get(option - 1).get(0));
				Time endingTime = new Time(optionsTime.get(option - 1).get(1));
				Time duration = new Time(optionsTime.get(option - 1).get(2));
				
				//PRINT TICKET:
				System.out.println("######################################################################\n#");
				System.out.println("#   ## 3igen3ggy train lines ##   ##Line ID: " + "(" + a + ") \n#   ## Train ID: " + b + " ##\n" +
						"#   ## Ticket ID: " + tick.getID() + " ##\n#");
				System.out.println("#   " + Menu.st.getName() + " (" + Menu.st.getID() + ") [" + (startingTime.getHours() - 1) + ":" + startingTime.getMinutes() + "] --> " +
						Menu.end.getName() + " (" + Menu.end.getID() + ") [" + (endingTime.getHours() - 1) + ":" + endingTime.getMinutes() + "]");
				System.out.println("#   Total travel time: [" + (duration.getHours() - 1) + ":" + duration.getMinutes() + "]");
				System.out.println("#   Passenger ID: " + Main.passenger.getID());
				System.out.println("#   Name: " + Main.getPassenger().getName());
				System.out.println("#   Last name: " + Main.getPassenger().getSurname());			
				System.out.println("#   E-mail: " + Main.getPassenger().getMail() + "                    Price: " + price + "PLN\n#");
				System.out.println("######################################################################");
				
				System.out.println();
				specificTimetablesList.clear();
				optionsTime.clear();
			}
			//if ticket is not approved
			else {
				System.out.println("### TICKET NOT BOUGHT ###");
				System.out.println("Going back to main menu");
			}
		}
	}
}
