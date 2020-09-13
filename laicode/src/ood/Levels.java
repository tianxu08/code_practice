package ood;

public class Levels {
	
	public static int numOfSpots = 10;
	
	private Level[] levels;
	
	public Levels(int numOfLevels) {
		levels = new Level[numOfLevels];
		for(int i = 0; i < levels.length;i ++) {
			// initialize the levels level
			levels[i] = new Level(numOfSpots);
		}
	}
	
	/*
	 * given a vehicle, tell me whether I can park
	 * if true, park it and return true
	 * otherwise, return false
	 */
	public boolean hasSpot(Vehicle v) {
		// TODO: traverse levels, call Level.canPark(Vehicle)
		for(Level lev : levels) {
			if (lev.canPark(v)) {
				return true;
			}
		}
		return false;
	}
	
	public void park(Vehicle v) {
		for(Level lev: levels) {
			if (lev.canPark(v)) {
				lev.park(v);
			}
		}
	}
	
}
