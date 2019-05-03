import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Author: Vladimir Kosik
//Date started and finished: 21st Nov 2016 and 16th Dec 2016
//Name of project: Room Booking System 

// this class represents operations  in Booking System
public class BookingSystem {

	static Scanner input = new Scanner(System.in);

	private static final Room[] rooms = new Room[17];

	public static void main(String[] args) throws Exception {

		loadRoom();

		String choice = "";
		//the user selection menu, which combines a do while loop with a switch case statement to allow the user to select an option
		do {
			System.out.println("\n-- MAIN MENU --");
			System.out.println("Room Booking System ");
			System.out.println("1 – Display Rooms");
			System.out.println("2 – Reserve and view rooms");
			System.out.println("3 – Cancel Rooms");
			System.out.println("Q - Quit");
			System.out.print("Pick : ");

			choice = input.next().toUpperCase();

			switch (choice) {
			case "1": {
				displayRoom("all");
				break;
			}
			case "2": {
				bookRoom("0","0","0");
				break;
			}
			case "3": {
				cancelRoom();
				break;
			}
			}
		} while (!choice.equals("Q"));
		System.out.println("-- Thank You  --");
	}
	// while loop to go through file 
	static void loadRoom() throws FileNotFoundException {
		Scanner file = new Scanner(new FileReader("rooms.txt"));//Setup a Scanner to read from a text file
		int index = 0;
		while (file.hasNext()) {
			int no = file.nextInt();
			String type = file.next();
			double price = file.nextDouble();
			boolean balcony = file.nextBoolean();
			boolean lounge = file.nextBoolean();
			String free = file.next();
			// loop and read the data from the file and use it in a constructor call to create Room objects
			rooms[index] = new Room(no, type, price, balcony, lounge, free);
			index++;
		}
		file.close();
	}
	// display rooms (available or reserved)
	private static void displayRoom(String isReserved) {
		System.out.println("\n-- Rooms in Hotel --");
		for (int i = 0; i < rooms.length; i++) {
			if (isReserved == "only available") {
				// Displays only available
				if (!rooms[i].toString().contains("reserved")) {
					System.out.println(rooms[i].toString());
				}
			}
			// Displays all rooms			
			if (isReserved == "all") {
				System.out.println(rooms[i].toString());
			}
		}
	}
	// user input and validation through file 
	private static void bookRoom(String found, String roomNum,String email) {
		boolean flag=false;
		if (found.equals("0")){
			System.out.println("AVAILABLE ROOMS:");
			displayRoom("only available");
			System.out.println("\n");

			System.out.println("Please insert number of room: ");// set number of room  entered by user
			int roomNo = input.nextInt();
			do{
				//check if inputs matches with file and then read it in
				if( 101<=roomNo && roomNo <= 105 || 201<=roomNo && roomNo <= 205 ||301<=roomNo && roomNo <= 307  
						&& roomNo != '\b'  )  {
					roomNum= Integer.toString(roomNo);
					validEmailAddress (roomNum);	
					break;
				}
				else {
					System.out.println("Invalid number of room. Please enter only available number of room.");
					roomNo =  input.nextInt();
					flag=false;
					break;
				}
			}while(flag==false);}

		if (found.equals("2")){
			System.out.println("Your reservation room: " + roomNum +  " has been confirmed.We will send you confirmation email on: " + email);
			return;
		}
		return;
	}		
	// validation for user input email using loops
	private static void validEmailAddress (String roomNum1){
		boolean flag=false;
		System.out.println("Please enter your email: ");
		do{		// set email to email entered by user	
			Pattern regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
			String yourEmail = input.next();
			Matcher regMatcher = regexPattern.matcher(yourEmail);if(regMatcher.matches()){
				bookRoom("2",roomNum1,yourEmail);
				flag=true;
				return;
			} else {
				System.out.println("Invalid Email. Please try to type correct format[  my.email@example.com] : ");
				yourEmail = input.next();
				flag=false;
				return;
			}
		}while(flag==false);
	}

	private static void cancelRoom() {
		System.out.println("\n-- CANCEL A ROOM --");
		System.out.print("Enter room number: ");
		int roomsNo = input.nextInt();
		for (int i = 0; i < rooms.length; i++) {
			if (rooms[i].getRoomNo() == roomsNo) {
				rooms[i].setEmail("free");
				System.out.println("Your room: " + roomsNo + " has been canceled! ");
			}}}}




