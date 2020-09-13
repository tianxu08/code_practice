package system_design;

import java.util.*;

class Restaurant {
	public int id;
	public String name;
	public Location location;

	public static Restaurant create(String name, Location location) {
		return null;
	}
};

class Location {
	public double latitude;
	public double longitude;

	public static Location create(double lati, double longi) {
		// this will create a new location object
		return null;
	}
};

class Helper {
	public static double get_distance(Location location1, Location location2) {
		// return distance between location1 and location2.
		return 0.0;
	}
};


class Node {
    public double distance;
    public Restaurant restaurant;
    public Node(double d, Restaurant r) {
        distance = d;
        restaurant = r;
    }
};

public class MiniYelp {

	public NavigableMap<String, Restaurant> restaurants;
	public Map<Integer, String> ids;

	public MiniYelp() {
		// initialize your data structure here.
		ids = new HashMap<Integer, String>();
		restaurants = new TreeMap<String, Restaurant>();
	}

	// @param name a string
	// @param location a Location
	public int addRestaurant(String name, Location location) {
		// Write your code here
		Restaurant restaurant = Restaurant.create(name, location);
		String hashcode = GeoHash.encode(location.latitude, location.longitude, 12) + "." + restaurant.id;
		ids.put(restaurant.id, hashcode);
		restaurants.put(hashcode, restaurant);
		return restaurant.id;
	}

	// @param restaurant_id an integer
	public void removeRestaurant(int restaurant_id) {
		// Write your code here
		if (ids.containsKey(restaurant_id)) {
			String hashcode = ids.get(restaurant_id);
			ids.remove(restaurant_id);
			if (restaurants.containsKey(hashcode)) {
				restaurants.remove(hashcode);
			}
		}
	}

	// @param location a Location
	// @param k an integer, distance smaller than k miles
	// @return a list of restaurant's name and sort by
	// distance from near to far.
	public List<String> neighbors(Location location, double k) {
		// Write your code here
		int len = get_length(k);
		String hashcode = GeoHash.encode(location.latitude, location.longitude, 12);
		hashcode = hashcode.substring(0, len);

		List<Node> results = new ArrayList<Node>();
		for (Map.Entry<String, Restaurant> entry : restaurants.subMap(hashcode,
				true, hashcode + "{", true).entrySet()) {
			String key = entry.getKey();
			Restaurant value = entry.getValue();
			double distance = Helper.get_distance(location, value.location);
			if (distance <= k)
				results.add(new Node(distance, value));
		}
		Collections.sort(results, new Comparator<Node>() {
			public int compare(Node arg0, Node arg1) {
				if (arg0.distance < arg1.distance)
					return -1;
				else if (arg0.distance > arg1.distance)
					return 1;
				else
					return 0;
			}
		});
		List<String> rt = new ArrayList<String>();
		int n = results.size();
		for (int i = 0; i < n; ++i)
			rt.add(results.get(i).restaurant.name);
		return rt;
	}

	int get_length(double k) {
		double[] ERROR = { 2500, 630, 78, 20, 2.4, 0.61, 0.076, 0.01911,
				0.00478, 0.0005971, 0.0001492, 0.0000186 };
		for (int i = 0; i < 12; ++i)
			if (k > ERROR[i])
				return i;
		return 12;
	}
}
