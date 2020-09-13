package ood;

public class ParkingSpot {
	private VehicleSize size;
	private Vehicle currentVehicle;  // null if no vehicle is parked inside
	
	public ParkingSpot(VehicleSize size) {
		this.size = size;
		currentVehicle = null;
	}
	
	
	
	public boolean canPark(Vehicle v) {
		if (currentVehicle == null) {
			return canFit(this, v);
		}
		return false;
	}
	
	private boolean canFit(ParkingSpot pkSpot, Vehicle v) {
		if (pkSpot.size.equals(v.getSize())) {  // here, we only define that 
			return true;
		}
		return false;
	}
	
	public void park(Vehicle v) {
		this.currentVehicle = v;
	}
	
	public void leave() {
		this.currentVehicle = null;
	}
}
