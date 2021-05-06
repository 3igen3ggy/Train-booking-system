import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generator {

	private static long stationID = 100;
	private static long passengerID = 100000;
	private static long lineID = 0;
	private static long ticketID = 1000000;
	
	public static void generate() {
		
		//generate some cities in Poland
		City Wro = new City("Wroclaw", 51.107883, 17.038538);
		City Zlw = new City("Zelow", 51.47, 19.21);
		City Ldz = new City("Lodz", 51.759445, 19.457216);
		City Wwa = new City("Warsaw", 52.229676, 21.012229);
		City Bstok = new City("Bialystok", 53.1324886, 23.1688403);
		City Pn = new City("Poznan", 52.409538, 16.931992);
		City Klc = new City("Kielce", 50.87033, 20.62752);
		City Szcz = new City("Szczecin", 53.428544, 14.552812);
		City Zak = new City("Zakopane", 49.299181, 19.9495621);
		City Rz = new City("Rzeszow", 50.04132, 21.99901);
		City Cr = new City("Cracow", 50.049683, 19.944544);
		City Lb = new City("Lublin", 51.246452, 22.568445);
		
		//generate some stations 
		Station Wroclaw = new Station(Wro);
		Station Zelow = new Station(Zlw);
		Station Lodz1 = new Station(Ldz, "Widzew");
//		Station Lodz2 = new Station(Ldz, "Kaliska");
		Station Lodz3 = new Station(Ldz, "Fabryczna");
		Station Warsaw = new Station(Wwa);
		Station Bialystok = new Station(Bstok);
//		Station Poznan = new Station(Pn);
//		Station Kielce = new Station(Klc);
//		Station Szczecin = new Station(Szcz);
//		Station Zakopane = new Station(Zak);
//		Station Rzeszow = new Station(Rz);
//		Station Cracow = new Station(Cr);
//		Station Lublin = new Station(Lb);
		
		
		//generate some trains
		Train Morawiecki = new Train("MORAWIECKI666", 1, 20);
		Train Fichuj = new Train("FICHUJ6969", 1, 10);
		Train Mickiewicz = new Train ("MICKIEWICZ997", 3, 20);
		Train Asnyk = new Train ("ASNYK1337", 2, 25);
		
		
		//generate some passengers
		Passenger pas1 = new Passenger("Roger", "Zitron", "gmail@gmail.com", "69");
		Passenger pas2 = new Passenger("Damian", "Borkowski", "dibi@hotmail.com", "piwo");
		Passenger pas3 = new Passenger("Felipe", "Masa", "gmail@zelow.com", "1234");
		Passenger pas4 = new Passenger("Jakub", "Kuba", "badboy@gmail.com", "wiszco");
		Passenger pas5 = new Passenger("Seba", "Stian", "sebaszczyn@gmail.com", "kebab");
		Passenger pas6 = new Passenger("Krystian", "Czaka", "czakalaka@gmail.com", "0000");
		Passenger pas7 = new Passenger("Tumek", "Tomaho", "tommy@gmail.com", "wons");
		Passenger pas8 = new Passenger("Nixon", "Czarny", "negro@gmail.com", "mamo");
		
		
		//generate routes for lines
		List<Station> rout1 = new ArrayList<Station>(Arrays.asList(Wroclaw, Lodz1, Warsaw, Bialystok));
		List<Station> rout2 = new ArrayList<Station>(Arrays.asList(Wroclaw, Zelow, Lodz1));
		List<Station> rout3 = new ArrayList<Station>(Arrays.asList(Wroclaw, Zelow, Lodz3, Warsaw, Bialystok));
		List<Station> rout4 = new ArrayList<Station>(Arrays.asList(Bialystok, Warsaw, Lodz3, Zelow, Wroclaw));

		
		//generate lines
		//remember that time is UTC (without any time-zone)
		Line lin1 = new Line(rout1, Morawiecki, Time.UTC(2000, 7, 17, 5, 30, 0));
		Line lin2 = new Line(rout2, Fichuj, Time.UTC(2000, 7, 17, 10, 0, 0));
		Line lin3 = new Line(rout3, Mickiewicz, Time.UTC(2000, 7, 17, 13, 0, 0));
		Line lin4 = new Line(rout4, Asnyk, Time.UTC(2000, 7, 17, 13, 0, 0));

		
		//generate timetables
		List<List<String>> tlin1 = lin1.createTimetables(lin1);
		List<List<String>> tlin2 = lin2.createTimetables(lin2);
		List<List<String>> tlin3 = lin3.createTimetables(lin3);
		List<List<String>> tlin4 = lin4.createTimetables(lin4);
		
		
		//print timetables
//		Line.printTimetable(tlin1);
//		Line.printTimetable(tlin2);
//		Line.printTimetable(tlin3);
		
		
		//generate some tickets
		Ticket tick1 = new Ticket(lin2, pas1, Wroclaw, Lodz1);
		Ticket tick2 = new Ticket(lin3, pas2, Wroclaw, Lodz3);
		Ticket tick3 = new Ticket(lin3, pas3, Wroclaw, Zelow);
		Ticket tick4 = new Ticket(lin4, pas3, Zelow, Wroclaw);
		Ticket tick5 = new Ticket(lin2, pas1, Zelow, Lodz1);
		Ticket tick6 = new Ticket(lin2, pas2, Zelow, Lodz1);
		Ticket tick7 = new Ticket(lin2, pas3, Zelow, Lodz1);
		Ticket tick8 = new Ticket(lin2, pas4, Zelow, Lodz1);
		Ticket tick9 = new Ticket(lin2, pas5, Zelow, Lodz1);
		Ticket tick10 = new Ticket(lin2, pas6, Zelow, Lodz1);
		Ticket tick11 = new Ticket(lin2, pas7, Zelow, Lodz1);
		Ticket tick12 = new Ticket(lin2, pas8, Wroclaw, Zelow);
		Ticket tick13 = new Ticket(lin2, pas1, Wroclaw, Lodz1);
	}

	public static synchronized long createStationID() {
		stationID++;
	    return stationID;
	}    
	
	public static synchronized long createPassengerID() {
		passengerID++;
	    return passengerID;
	}   
	
	public static synchronized long createLineID() {
		lineID++;
	    return lineID;
	}  
	
	public static synchronized long createTicketID() {
		ticketID++;
	    return ticketID;
	}   
}