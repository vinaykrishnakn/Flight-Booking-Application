package FlightbookingApplication;

public class Passenger {
	private String name;
	private int age;
	private String seatNo;
	private boolean mealPreferences;
	private String flightNumber;
	private int amount;
	private String seatClass;
	
	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}

	public Passenger(String name,int age,String seatNo,String flightNumber,boolean mealPreferences,int amount,String seatClass) {
		this.name=name;
		this.age=age;
		this.seatNo=seatNo;
		this.mealPreferences=mealPreferences;		
		this.amount=amount;
		this.flightNumber=flightNumber;
		this.seatClass=seatClass;
	}

	public String getSeatClass() {
		return seatClass;
	}
	
	public String getFlightNumber() {
		return flightNumber;
	}

	public int getAmount() {
		return amount;
	}

	public boolean getMealPreferences() {
		return mealPreferences;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getSeatNo() {
		return seatNo;
	}

}
