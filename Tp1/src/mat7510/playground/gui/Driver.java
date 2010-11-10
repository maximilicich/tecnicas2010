package mat7510.playground.gui;


public class Driver
{
	private String driverName;

	public Driver(String driverName) {
		this.setDriverName(driverName);
	}
	
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverName() {
		return driverName;
	}
	
	@Override
	public String toString() {
		return this.driverName;
	}
	
}
