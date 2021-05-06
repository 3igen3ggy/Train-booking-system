import java.util.ArrayList;
import java.util.List;

public class Station {

	private long ID;
	private String name;
	private double pos;
	private int platforms;
	private static List<Station> stationsList = new ArrayList<Station>();
	public Station st;
	public Station end;
	private City city;
	
	public Station(City city) {
		this.ID = Generator.createStationID();
		this.name = city.getName() + " " + "MS"; //MS stands for "Main Station"
		this.city = city;
		
		//creating a new entry to a stations list
		stationsList.add(this);
	}
	
	public Station(City city, String name) {
		this.ID = Generator.createStationID();
		this.name = city.getName() + " " + name;
		this.city = city;
		
		//creating a new entry to a stations list
		stationsList.add(this);
	}

	public String getName() {
		return name;
	}

	public double getPos() {
		return pos;
	}

	public int getPlatforms() {
		return platforms;
	}

	public long getID() {
		return ID;
	}
	
	public City getCity() {
		return city;
	}
	
	public static List<Station> getStations() {
		return stationsList;
	}
	
	//print all stations
	public static void printStations() {
		List<Station> stations = getStations();
		System.out.println("\n########################################");
		System.out.println("STATIONS LIST: ");
		for (int i = 0; i < stations.size(); i++) {
			System.out.println("ID: " + stations.get(i).getID() + ", Station: " + stations.get(i).getName());
		}
		System.out.println("########################################");
	}
	
	//not working... yet
	public static Station findStationByName(String name) {
		System.out.println(Station.getStationsList());
		for (int i = 0; i < Station.getStationsList().size(); i++) {
			if (name.equals(Station.getStationsList().get(i).getName())) return Station.getStationsList().get(i);
		}
		return null;
	}
	
	public static Station findStationByID(long ID) {
		for (int i = 0; i < Station.getStationsList().size(); i++) {
			if (Station.getStationsList().get(i).getID() == ID) return Station.getStationsList().get(i);
		}
		return null;
	}

	public static List<Station> getStationsList() {
		return stationsList;
	}
}