// this class represents Rooms(things) in Booking System
public class Room {

	//Author: Vladimir Kosik
	//Date started and finished: 21st Nov 2016 and 16th Dec 2016
	//Name of project: Room Booking System 
	public static char[] toStrings;
	//Initialisation of global variables 
	int no;
	String type;
	double price;
	boolean balcony;
	boolean lounge;
	String free;
	String email;
	//  global class variable in the constructors.
	public Room(int no, String type, double price, boolean balcony, boolean lounge, String free) {
		this.no = no;
		this.type = type;
		this.price = price;
		this.balcony = balcony;
		this.lounge = lounge;
		this.free = free;
	}
	//provide a string representation of rooms
	public String toString() {
		String result="";
		result = "NUMBER OF ROOMS: "  + no +   " TYPE OF ROOMS:  " + type + " PRICE OF ROOM: " + price + " BALCONY:  "+  balcony + "LOUNGE : "+lounge+" "+free;
		return result;
	}
	//book operation through the array of rooms  and setting the appropriate value
	public void book(int room){
		for (int i=0; i<type.length(); i++) {
			if (no == 0) {
				no = room;
				return;
			}
		}
	}
	//cancel operation through the array of rooms  and setting the appropriate value
	public void cancel(int room){
		for (int i= 0; i<type.length(); i++) {
			if (no == room) {
				no = 0;
				return;
			}
		}
	}
	//return the number for a room
	public int getRoomNo(){
		return no;
	}

	public void setEmail(String email){
		this.email= email;
	}

}

