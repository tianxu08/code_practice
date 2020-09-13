package ood;

import java.util.ArrayList;
import java.util.List;

public class Level { // one level, is composed by several spots
	private final List<ParkingSpot> spots;
	
	// constructor of Level
	public Level(int numOfSpots) {
		spots = new ArrayList<ParkingSpot>(numOfSpots);
		for(int i = 0; i < numOfSpots; i ++) {
			int mid = numOfSpots/2;
			if (i < mid) {
				spots.add(new ParkingSpot(VehicleSize.Compact));
			} else {
				spots.add(new ParkingSpot(VehicleSize.Large));
			}
		}
	}
	// whether vehicle v can park in this level
	public boolean canPark(Vehicle v) {
		for(ParkingSpot s: spots) {
			if (s.canPark(v)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean park(Vehicle v) {
		for(ParkingSpot s: spots) {
			if (s.canPark(v)) {
				s.park(v);
				return true;
			}
		}
		return false;
	}
}
