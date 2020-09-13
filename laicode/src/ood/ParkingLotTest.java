package ood;

public class ParkingLotTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Levels levels = new Levels(5);
		
		Vehicle car = new Car();
		boolean canPark = levels.hasSpot(car);
		
		for(int i = 0; i < 20; i ++) {
			Vehicle c = new Car();
			if (levels.hasSpot(c)) {
				levels.park(c);
			}
		}
	}
}
