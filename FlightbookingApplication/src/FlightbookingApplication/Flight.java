package FlightbookingApplication;

import java.util.*;

public class Flight {
	private String flightNumber;
	private int economySeatsBooked=0;
	private int businessSeatsBooked=0;
	private int mealCharge=200;
	private int cancellationCharge=200;
	private Map<Integer,Boolean> mealPreferences;
	
	private List<Integer> economySeatArrangement;
	private int maxEconomyRow;
	private int maxEconomyColumn;
	private int basePriceEconomy;
	private int economyPriceSurge;
	private int count1=0;
	private boolean [][]economySeatsAvailable;
	
	private List<Integer> businessSeatArrangement;
	private int maxBusinessRow;
	private int maxBusinessColumn;	
	private int basePriceBusiness;
	private int businessPriceSurge;
	private int count2=0;
	private boolean [][]businessSeatsAvailable;

    //constructor
	public Flight(String flightNumber, List<Integer> economySeatArrangement, int maxEconomyRow, int basePriceEconomy,
			int economyPriceSurge, List<Integer> businessSeatArrangement, int maxBusinessRow, int basePriceBusiness,
			int businessPriceSurge) {

		this.flightNumber = flightNumber;
		this.economySeatArrangement = economySeatArrangement;
		this.maxEconomyRow = maxEconomyRow;
		this.basePriceEconomy = basePriceEconomy;
		this.economyPriceSurge = economyPriceSurge;
		this.businessSeatArrangement = businessSeatArrangement;
		this.maxBusinessRow = maxBusinessRow;
		this.basePriceBusiness = basePriceBusiness;
		this.businessPriceSurge = businessPriceSurge;
		
		int maxEconomyColumn=0;
		for(Integer i:economySeatArrangement) {
			maxEconomyColumn+=i;
		}
		this.maxEconomyColumn=maxEconomyColumn;
		int maxBusinessColumn=0;
		for(Integer i:businessSeatArrangement) {
			maxBusinessColumn+=i;
		}
		this.maxBusinessColumn=maxBusinessColumn;
		
		this.economySeatsAvailable=new boolean[maxEconomyRow][maxEconomyColumn];
		this.businessSeatsAvailable=new boolean[maxBusinessRow][maxBusinessColumn];
		this.mealPreferences=new HashMap<Integer,Boolean>();	
	}

	public String getFlightNumber() {
		return flightNumber;
	}
	
	public int getMaxEconomyRow() {
		return maxEconomyRow;
	}

	public int getMaxEconomyColumn() {
		return maxEconomyColumn;
	}

	public int getMaxBusinessRow() {
		return maxBusinessRow;
	}

	public int getMaxBusinessColumn() {
		return maxBusinessColumn;
	}

	public boolean[][] getEconomySeatsAvailable() {
		return economySeatsAvailable;
	}

	public boolean[][] getBusinessSeatsAvailable() {
		return businessSeatsAvailable;
	}

	public int isEconomySeatAvailable(){
		return (maxEconomyRow*maxEconomyColumn-economySeatsBooked);
	}
	
	public int isBusinessSeatAvailable(){
		return (maxBusinessRow*maxBusinessColumn-businessSeatsBooked);
	}
	
	public int getEconomySeatsBooked() {
		return economySeatsBooked;
	}

	public int getBusinessSeatsBooked() {
		return businessSeatsBooked;
	}
	
	public Map<Integer, Boolean> getMealPreferences() {
		return mealPreferences;
	}

	public void addBooking(int r,int c,String seatClass, int bookingId,boolean meals) {
		if(seatClass.equalsIgnoreCase("business")) {
			this.businessSeatsBooked++;
			this.count2++;
			this.businessSeatsAvailable[r][c]=true;
			this.mealPreferences.put(bookingId,meals);
		}
		else if(seatClass.equalsIgnoreCase("economy")) {
			this.economySeatsBooked++;
			this.count1++;
			this.economySeatsAvailable[r][c]=true;
			this.mealPreferences.put(bookingId,meals);
		}
	}

	public int cancelBooking(int r,int c,String seatClass, int bookingId,int amount) {
		if(seatClass.equalsIgnoreCase("business")) {
			this.businessSeatsBooked--;
			this.businessSeatsAvailable[r][c]=false;
			this.mealPreferences.remove(bookingId);
			return amount-cancellationCharge;
		}
		else if(seatClass.equalsIgnoreCase("economy")) {
			this.economySeatsBooked--;
			this.economySeatsAvailable[r][c]=false;
			this.mealPreferences.remove(bookingId);
			return amount-cancellationCharge;
		}
		else {
			return 0;
		}
	}
	
	public int getBasePriceEconomy() {
		return basePriceEconomy;
	}

	public int getEconomyPriceSurge() {
		return economyPriceSurge;
	}

	public int getBasePriceBusiness() {
		return basePriceBusiness;
	}

	public int getBusinessPriceSurge() {
		return businessPriceSurge;
	}
	
	public int economyTicketAmount(boolean meals) {
		if(meals)
			return basePriceEconomy+count1*economyPriceSurge+mealCharge;
		return (basePriceEconomy+count1*economyPriceSurge);
	}
	
	public int businessTicketAmount(boolean meals) {
		if(meals)
			return basePriceBusiness+count2*businessPriceSurge+mealCharge;
		return (basePriceBusiness+count2*businessPriceSurge);
	}

}

