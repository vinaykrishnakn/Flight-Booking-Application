package FlightbookingApplication;

import java.util.*;

public class Flightbookingapplication {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Booking booking=new Booking();
		List<Integer> economySeatArrangement=new ArrayList<Integer>();
		economySeatArrangement.add(3);
        economySeatArrangement.add(4);
        economySeatArrangement.add(4);
        List<Integer> businessSeatArrangement=new ArrayList<Integer>();
        businessSeatArrangement.add(2);
        businessSeatArrangement.add(3);
        businessSeatArrangement.add(2);
        
		booking.addFlight("Flight-A112-Chennai-Mumbai",economySeatArrangement,20,1000,100,businessSeatArrangement,12,2000,200);
		booking.addFlight("Flight-A113-Chennai-Kolkata",economySeatArrangement,20,1000,100,businessSeatArrangement,12,2000,200);
		booking.addFlight("Flight-A114-Chennai-Delhi",economySeatArrangement,20,1000,100,businessSeatArrangement,12,2000,200);
		booking.addFlight("Flight-A115-Kolkata-Mumbai",economySeatArrangement,20,1000,100,businessSeatArrangement,12,2000,200);
		booking.addFlight("Flight-A116-Kolkata-Delhi",economySeatArrangement,20,1000,100,businessSeatArrangement,12,2000,200);
		booking.addFlight("Flight-A117-Mumbai-Delhi",economySeatArrangement,20,1000,100,businessSeatArrangement,12,2000,200);
		
		System.out.println("Press 1 for searching flight by source and destination");
		System.out.println("press 2 for searching flight by business class");
		System.out.println("press 3 for displaying all flight");
		System.out.println("press 4 for Booking ticket");
		System.out.println("press 5 for cancelling ticket");
		System.out.println("press 6 for print ticket by booking Id");
		System.out.println("press 7 for flight wise meals requirement");
		System.out.println("press 8 for flight primary summary");
		
		while (true) {
			System.out.println("\nEnter your choice");
			int choice=sc.nextInt();
			
			switch(choice) {
			  case 1:
				  System.out.println("Enter Source");
				  String source=sc.next();
				  System.out.println("Enter Destination");
				  String Destination=sc.next();
				  List<String> flight=booking.filterFlightsByLocation(source, Destination);
				  for(String i:flight) {
					  System.out.println(i);
				  }
				  break;
				  
			  case 2:
					List<String>flight1=booking.filterFlightByBusinessClass();
					for(String i:flight1) {
						System.out.println(i);
					}
					break;
					
			  case 3:
				  List<String> f=booking.viewFlights();
				  for(String i:f) {
					  System.out.println(i);
				  }
				  break;
				  
			  case 4:
				  System.out.println("Enter Name:");
				  String name=sc.next();
				  System.out.println("Enter Age:");
				  int age=sc.nextInt();
				  System.out.println("Enter FlightNo:");
				  String flightNumber=sc.next();
				  System.out.println("Enter ClassType:");
				  String classType=sc.next();
				  
				  System.out.println("Showing available seats in a class");
				  List<String>flight2=booking.getAvailableSeats(flightNumber,classType);
				  if(classType.equalsIgnoreCase("business")) {
					  for(int i=0;i<flight2.size();i+=7) {
						  for(int j=i;j<i+7 && j<flight2.size();j++) {
							  System.out.print(flight2.get(j)+" ");
						  }			
						  System.out.println();
					  }					  
				  }else if(classType.equalsIgnoreCase("economy")){
					  for(int i=0;i<flight2.size();i+=11) {
							for(int j=i;j<i+11 && j<flight2.size();j++) {
								System.out.print(flight2.get(j)+" ");
							}			
							System.out.println();
						}					  
				  }	  				  
				  System.out.println("Enter SeatNo:");
				  String seatNo=sc.next();
				  System.out.println("Enter Meals:");
				  boolean meals=sc.next().equals("yes")?true:false;
				  System.out.println(booking.bookSeats(name, age, flightNumber, classType, seatNo, meals));	
				  break;	
				  
			  case 5:
				  System.out.println("Enter BookingId: ");
				  int id=sc.nextInt();
				  System.out.println(booking.cancelSeats(id));
				  break;
			  case 6:
				  System.out.println("Enter BookingId: ");
				  int id1=sc.nextInt();
				  booking.flightTicketByBookingId(id1);
				  break;
			  case 7:
				  booking.mealRequirementInFlight();
				  break;
			  case 8:
				  booking.flightPrimarySummary();
				  break;			
			}			
			
		}
	}

}
