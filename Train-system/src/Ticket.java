import java.util.ArrayList;
import java.util.List;

public class Ticket {

	private String name;
	private Station start;
	private Station destination;
	private int wagon;
	private int no;
	private long ID;
	private Passenger passenger;
	private List<List<Boolean>> places;
	private static List<Ticket> TicketsList = new ArrayList<Ticket>();
	
	//(Passenger passenger, int wagon, int no, Station start, Station destination)
	public Ticket(Line line, Passenger passenger, Station start, Station destination) {
		this.ID = Generator.createTicketID();
		this.passenger = passenger;
		this.start = start;
		this.destination = destination;
		this.places = line.getPlaces();
		
		int place = findPlace(line, start, destination);

		if (place == -1) System.out.println("SORRY, NO MORE FREE PLACES IN THAT TRAIN");
		else {
			this.no = place;
			this.wagon = no / line.getTrain().getCapacityPerCar() + 1;
			TicketsList.add(this);
		}
	}
	
	public List<List<Boolean>> getPlaces() {
		return places;
	}

	public static List<Ticket> getTicketsList() {
		return TicketsList;
	}

	public int findPlace(Line line, Station start, Station end) {
		places = line.getPlaces();

		int st = findStationNumByNameFromList(line.getList(), start.getName());
		int en = findStationNumByNameFromList(line.getList(), end.getName());
		
		if (st == -1 || en == -1) return -1;
		for (int i = 0; i < places.get(0).size(); i++) {
			for (int j = st; j < en; j++) {
				if (places.get(j).get(i)) {
					break;
				}
				if (j == en - 1) {	
					for (int k = st; k < en; k++) {
							places.get(k).set(j, true);
					}
					places.get(j).set(i, true);
					return i;
				}
			}
		}
		return -1;
	}
	
	public static int findStationNumByNameFromList(List<Station> list, String name) {
		for (int i = 0; i < list.size(); i++) {
			if (name.equals(list.get(i).getName())) return i;
		}
		return -1;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public String getName() {
		return name;
	}

	public Station getStart() {
		return start;
	}

	public Station getDestination() {
		return destination;
	}
	
	public int getWagon() {
		return wagon;
	}

	public long getID() {
		return ID;
	}

	public int getNo() {
		return no;
	}
	
	public static List<Ticket> getTickets() {
		return TicketsList;
	}
	
	public static void printTickets() {
		List<Ticket> tickets = getTickets();
		System.out.println("\n##############################################################################################################");
		System.out.println("TICKETS LIST: ");
		for (int i = 0; i < tickets.size(); i++) {
			System.out.println("ID: " + tickets.get(i).getID() + ", name: " + tickets.get(i).getPassenger().getName() + " " +
					tickets.get(i).getPassenger().getSurname() + ", wagon: " + tickets.get(i).getWagon() +
					", no: " + (tickets.get(i).getNo() + 1) + ", startStation: " + tickets.get(i).getStart().getName() + ", endStation: " + 
					tickets.get(i).getDestination().getName());
		}
		System.out.println("##############################################################################################################");
	}
}