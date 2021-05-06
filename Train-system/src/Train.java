import java.util.ArrayList;
import java.util.List;

public class Train {

	private String ID;
	private int cars;
	private int capacityPerCar;
	private long time;
	private int speed;
	static List<Train> trainsList = new ArrayList<Train>();
	
	public Train(String ID, int cars, int capacityPerCar) {
		
		this.ID = ID;
		this.cars = cars;
		this.capacityPerCar = capacityPerCar;
		this.speed = 100;
		//creating a new entry to a trains list
		trainsList.add(this);
		
	}
	
	public int getSpeed() {
		return speed;
	}

	public String getID() {
		return ID;
	}

	public int getCars() {
		return cars;
	}

	public int getCapacityPerCar() {
		return capacityPerCar;
	}
	
	public long getTime() {
		return time;
	}

	public static List<Train> getTrainsList() {
		return trainsList;
	}
	
	public static void printTrains() {
		List<Train> trains = getTrainsList();
		System.out.println("\n##################################################");
		System.out.println("TRAINS LIST: ");
		for (int i = 0; i < trains.size(); i++) {
			System.out.println("ID: " + trains.get(i).getID() + ", cars: " + trains.get(i).getCars() +
					", capacityPerCar: " + trains.get(i).getCapacityPerCar());
		}
		System.out.println("##################################################");
	}
}
