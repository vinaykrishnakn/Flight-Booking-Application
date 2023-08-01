package FlightbookingApplication;

import java.util.List;

enum SeatType{
	AISLE,MIDDLE,WINDOW
}
public class Seat {
//	private int row;
//	private int column;
	private SeatType type;
	
	public SeatType economySeatType(int c) {
		if(c==0 || c==10)type=SeatType.WINDOW;
		else if(c==2 ||c==3 || c==6 ||c==7)type=SeatType.AISLE;
		else type=SeatType.MIDDLE;
		return type;
	}
	public SeatType businessSeatType(int c) {
		if(c==0 || c==6)type=SeatType.WINDOW;
		else if(c==1 || c==2 || c==4 ||c==5)type=SeatType.AISLE;
		else type=SeatType.MIDDLE;
		return type;
	}

}
