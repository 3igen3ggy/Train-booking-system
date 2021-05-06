import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Line {

	private List<Station> list;
	private long ID;
	private long time;
	private long timePlus;
	private Train train;
	List<String> header = new ArrayList<String>();
	private static List<Line> linesList = new ArrayList<Line>();
	private Time timing;
	private List<List<String>> timetable = new ArrayList<List<String>>();
	private static List<List<List<String>>> timetablesList = new ArrayList<List<List<String>>>();
	private List<List<Boolean>> places = new ArrayList<List<Boolean>>();

	public Line(List<Station> list, Train train, long time) {
		this.list = list;
		this.ID = Generator.createLineID();
		this.time = time;
		this.train = train;
		this.places = generatePlaces();
		this.timetable = createTimetable();
		
		linesList.add(this);
	}
	
	public List<List<String>> createTimetable() {
		List<String> entry = new ArrayList<String>();
		int n = list.size();
		//time train stops each station
		int stopTime = 5;
		
		List<Integer> dist = new ArrayList<Integer>();
		int distance = 0;
		dist.add(0);
		for (int i = 0; i < n - 1; i++) { 
			distance += City.distance(list.get(i).getCity(), list.get(i + 1).getCity());
			dist.add(distance);
		}
		
		entry.add(String.valueOf(ID));
		entry.add(train.getID());
		timetable.add(entry);
		
		for (int i = 0; i < n; i++) {
			entry = new ArrayList<String>();
			entry.add(list.get(i).getName());
			timePlus = (long) ((3600000 * dist.get(i) / (long) train.getSpeed() + stopTime * i * 60000) + time);
			entry.add(String.valueOf(timePlus));
			timetable.add(entry);
		}
		return timetable;
	}

	public long getTime() {
		return time;
	}

	public List<List<String>> createTimetables(Line line) {
		List<Station> listOfStations = line.getList();
		timetablesList.add(timetable);
		return timetable;
	}
	
	public long getTimePlus() {
		return timePlus;
	}

	public List<String> getHeader() {
		return header;
	}

	public Time getTiming() {
		return timing;
	}

	public List<List<String>> getTimetable() {
		return timetable;
	}

	public static List<List<List<String>>> getTimetablesList() {
		return timetablesList;
	}

	public static void printTimetable(List<List<String>> timetable) {
		int n = timetable.size();

		System.out.println("\nTIMETABLES LIST FOR " + timetable.get(0).get(0) + ", " + timetable.get(0).get(1) + ": ");
		for (int i = 1; i < n; i++) {
			Time timing = new Time(Long.parseLong(timetable.get(i).get(1)));
			System.out.println(i + ") "  + timetable.get(i).get(0) + ", at: " + timing.getHours() + ":" + timing.getMinutes());
		}
	}
	
	public static List<Line> getLinesList() {
		return linesList;
	}

	List<Station> getList() {
		return list;
	}

	public Train getTrain() {
		return train;
	}

	public long getID() {
		return ID;
	}

	public Station getFirstStation() {
		return list.get(0);
	}
	
	public Station getLastStation() {
		return list.get(list.size() - 1);
	}

	public static void printLinesList() {
		int n = linesList.size();
		System.out.println("\n##################################################");
		System.out.println("LINES LIST: ");
		for (int i = 0; i < n; i++) {
			Time startTime = new Time(linesList.get(i).getTime());
			System.out.println("ID: " + linesList.get(i).getID() + ", " + linesList.get(i).getFirstStation().getName() +
					" (" + startTime.getHours() + ":" + startTime.getMinutes() + ")" + 
					" --> " + linesList.get(i).getLastStation().getName());
		}
		System.out.println("##################################################");
	}
	
	public static void printTimetablesList() {
		List<List<List<String>>> mainTimetable = timetablesList;
		System.out.println("\n##############################");
		System.out.println("TIMETABLES LIST:\n");
		int m = mainTimetable.size();
		
		for (int i = 0; i < m; i++) {
			int n = mainTimetable.get(i).size();
			System.out.println("####################");
			System.out.println(mainTimetable.get(i).get(0).get(0) + " " + mainTimetable.get(i).get(0).get(1) + "\n");
			for (int j = 1; j < n; j++) {
				Time timing = new Time(Long.parseLong(mainTimetable.get(i).get(j).get(1)));
				System.out.println(mainTimetable.get(i).get(j).get(0) + " " + timing.getHours() + ":" + timing.getMinutes());
			}
			System.out.println("####################\n");
		}
		System.out.println("##############################");
	}
	
	public List<List<Boolean>> generatePlaces() {
		Train train = getTrain();
		int len = list.size() - 1;
		int capacityPerCar = train.getCapacityPerCar();
		int cars = train.getCars();
		
		for (int j = 0; j < len; j++) {
			List<Boolean> entry = new ArrayList<Boolean>();
			for (int i = 0; i < cars * capacityPerCar; i++) {
				entry.add(false);
			}
			places.add(entry);
		}
		return places;
	}

	public List<List<Boolean>> getPlaces() {
		return places;
	}
	
	public static Line findLineByID(long ID) {
		for (int i = 0; i < linesList.size(); i++) {
			if (linesList.get(i).getID() == ID) return linesList.get(i);
		}
		return null;
	}
}