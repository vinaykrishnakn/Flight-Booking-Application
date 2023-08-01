package FlightbookingApplication;

import java.util.*;

public class Booking {
	private HashMap<String,Flight> flights=new HashMap<String,Flight>();
	private HashMap<Integer,Passenger> passenger=new HashMap<Integer,Passenger>();
	private int bookingId=1000;
	public void addFlight(String flightNumber, List<Integer> economySeatArrangement, int maxEconomyRow, int basePriceEconomy,
			          int economyPriceSurge, List<Integer> businessSeatArrangement, int maxBusinessRow, int basePriceBusiness,
			          int businessPriceSurge) {
		
		Flight flight=new Flight(flightNumber,economySeatArrangement,maxEconomyRow,basePriceEconomy,economyPriceSurge, 
				businessSeatArrangement,maxBusinessRow,basePriceBusiness,businessPriceSurge);
		flights.put(flightNumber,flight);		
	}
	
	public List<String> viewFlights() {
		return new ArrayList<>(flights.keySet());
	}
	
	public List<String> filterFlightsByLocation(String source,String destination){
		List<String> filteredFlight=new ArrayList<>();
		for(Flight flight:flights.values()) {
			String flightNo=flight.getFlightNumber();
			if(flightNo.contains(source)&& flightNo.contains(destination)) {
				filteredFlight.add(flightNo);
			}
		}
		return filteredFlight;
	}
	
	public List<String> filterFlightByBusinessClass(){
		List<String> filteredFlight=new ArrayList<>();
		for(Flight flight:flights.values()) {
			if(flight.isBusinessSeatAvailable()>0) {
				filteredFlight.add(flight.getFlightNumber());
			}
		}
		return filteredFlight;
		
	}
	public List<String> getAvailableSeats(String flightNumber,String flightClass){
		Flight flight=flights.get(flightNumber);
		List<String> availableSeats=new ArrayList<>();
		Seat seat=new Seat();
		if(flightClass.equalsIgnoreCase("economy")) {
			boolean[][] seats=flight.getEconomySeatsAvailable();
			for(int i=0;i<flight.getMaxEconomyRow();i++) {
				for(int j=0;j<flight.getMaxEconomyColumn();j++) {
					if(!seats[i][j]) {
						char ch=(char) ('A'+j);
						availableSeats.add((i+1)+"_"+ch+"-"+seat.economySeatType(j));
					}
					
				}
			}					
		}
		else if(flightClass.equalsIgnoreCase("business")) {
			boolean[][] seats=flight.getBusinessSeatsAvailable();
			for(int i=0;i<flight.getMaxBusinessRow();i++) {
				for(int j=0;j<flight.getMaxBusinessColumn();j++) {
					if(!seats[i][j]) {
						char ch=(char) ('A'+j);
						availableSeats.add((i+1)+"_"+ch+"-"+seat.businessSeatType(j));
					}
				}
			}
		}
		return availableSeats;	
		
	}
	
	public String bookSeats(String name, int age,String flightNumber,String seatClass,String seatNo,boolean meals) {
		if (!flights.containsKey(flightNumber)) {
            return "Invalid flight number.";
        }
        Flight flight = flights.get(flightNumber);
        String[] seatCoordinate=seatNo.split("_");
        int r=Integer.parseInt(seatCoordinate[0]);
        int c=seatCoordinate[1].charAt(0)-'A';
        if (seatClass.equalsIgnoreCase("business")) {
        	boolean[][] seats=flight.getBusinessSeatsAvailable();
        	if(!seats[r-1][c]){        		
        		int amount=flight.businessTicketAmount(meals);
        		flight.addBooking(r-1, c, seatClass,bookingId,meals);
        		passenger.put(bookingId,new Passenger(name,age,seatNo,flightNumber,meals,amount,seatClass));
        		bookingId++;
        		return "Booked Successfully; Booking Id:"+(bookingId-1)+",Total amount :"+amount;
        	
        	}else {
        		return "Seat already Booked";
        	}
        	
        } else if (seatClass.equalsIgnoreCase("economy")) {
        	boolean[][] seats=flight.getEconomySeatsAvailable();
        	if(!seats[r-1][c]){       
        		int amount=flight.economyTicketAmount(meals);
        		flight.addBooking(r-1, c, seatClass,bookingId,meals);
        		passenger.put(bookingId,new Passenger(name,age,seatNo,flightNumber,meals,amount,seatClass));
        		bookingId++;
        		return "Booked Successfully; Booking Id:"+(bookingId-1)+",Total amount :"+amount;
        	
        	}else {
        		return "Seat already Booked";
        	}
        } else {
            return "Invalid seat class. Please choose either Business or Economy.";
        }
	}
	
	public String cancelSeats(int bookingId) {
		Passenger passengerDetails=passenger.get(bookingId);
		Flight flight=flights.get(passengerDetails.getFlightNumber());
		passenger.remove(bookingId);
		String[] seatCoordinate=passengerDetails.getSeatNo().split("_");
        int r=Integer.parseInt(seatCoordinate[0]);
        int c=seatCoordinate[1].charAt(0)-'A';
		int refundableAmount=flight.cancelBooking(r-1,c,passengerDetails.getSeatClass(),bookingId,passengerDetails.getAmount());
		return "Cancellation Successfully; Refund amount:"+refundableAmount;
	}

	public void mealRequirementInFlight() {
		for(Flight flight:flights.values()) {
			System.out.println("FlightNumber :"+flight.getFlightNumber());
			int check=0;
			for(int bookingId:flight.getMealPreferences().keySet()) {
				Passenger passengers=passenger.get(bookingId);
				if(passengers!=null && passengers.getMealPreferences()) {
					System.out.println("Booking ID " + bookingId + ", Seat: " + passengers.getSeatNo());
					check=1;
				}				
			}
			if(check==0)
				System.out.println("Meal requirement is 0");
			
			System.out.println();
		}
	}
	
	public void flightTicketByBookingId(int bookingId) {
		Passenger passengerDetails=passenger.get(bookingId);
		System.out.println("Name : "+passengerDetails.getName());
		System.out.println("Age : "+passengerDetails.getAge());
		System.out.println("FlightNumber : "+passengerDetails.getFlightNumber());
		System.out.println("class : "+passengerDetails.getSeatClass());
		System.out.println("Seat Number : "+passengerDetails.getSeatNo());
		System.out.println("Amount : "+passengerDetails.getAmount());		
	}
	
	public void flightPrimarySummary() {
		for(Flight flight:flights.values()) {
			System.out.println("FlightNumber :"+flight.getFlightNumber());
			System.out.println("Number of business class booked :"+flight.getBusinessSeatsBooked());
			System.out.println("Number of economy class booked :"+flight.getEconomySeatsBooked());
			for(int bookingId:flight.getMealPreferences().keySet()) {
				Passenger passengers=passenger.get(bookingId);
				System.out.println("Booking ID " + bookingId + ", Seat: " + passengers.getSeatNo());			
			}	
			System.out.println();
		}
	}
	
	
}
