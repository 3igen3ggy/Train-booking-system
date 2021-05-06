import java.util.ArrayList;
import java.util.List;

public class City {
	private String name;
	private double lat;
	private double lon;
	private static List<City> list= new ArrayList<City>();
	
	public City (String name, double lat, double lon) {
		this.name = name;
		this.lat = lat;
		this.lon = lon;
		
		list.add(this);
	}
	
	public static void printCities() {
		System.out.println("\n##################################################");
		System.out.println("CITIES LIST: ");
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getName() + ", lat: " + list.get(i).getLat() + ", lon: " + list.get(i).getLon());
		}
		System.out.println("##################################################");
	}
	public City findCityByName(String name) {
		List<City> listForSearch = list;
		
		for (int i = 0; i < listForSearch.size(); i++) {
			if (name.equals(listForSearch.get(i).getName())) return listForSearch.get(i);
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}
	
	//calculate distance between two cities on a globe (along geodesic on a surface)
	public static double distance(City city1, City city2) {
		double lat1 = city1.getLat();
		double lat2 = city2.getLat();
		double lon1 = city1.getLon();
		double lon2 = city2.getLon();
		double R = 6371e3; // metres
		double phi1 = lat1 * Math.PI / (double) 180; // phi, lambda in radians
		double phi2 = lat2 * Math.PI / (double) 180;
		double dphi = (lat2 - lat1) * Math.PI / (double) 180;
		double dlamb = (lon2 - lon1) * Math.PI / (double) 180;

		double a = Math.sin(dphi / 2) * Math.sin(dphi / 2) +
		          Math.cos(phi1) * Math.cos(phi2) *
		          Math.sin(dlamb / 2) * Math.sin(dlamb / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		double d = R * c / (double) 1000; // in km
		return d;
	}
}