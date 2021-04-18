package F28DA_CW2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlyPlannerMain {

	public static void main(String[] args) {

		// Your implementation should be in FlyPlannerImpl.java, this class is only to
		// run the user interface of your programme.

		FlyPlannerImpl fi;
		fi = new FlyPlannerImpl();
		try {
			fi.populate(new FlightsReader());

			// Implement here your user interface using the methods of Part A. You could
			// optionally expand it to use the methods of Part B.
			
			Scanner sc = new Scanner(System.in);	// Scanner object to get user input
			
			// Welcoming the user
			System.out.println("Hey Dude! I see you have come here to find out more about that trip you were planning to take.\n");
			System.out.println("Follow the steps below and you'll get what you came for!\n");
			System.out.println("\n");
			
			String deptAirCode , deptAirName, arrAirCode, arrAirName;	// String variables to store the departure and arrival airport's name and code
			
			System.out.println("Please enter the airport code from where you would like to depart : ");
			deptAirCode = sc.nextLine();	// Recieve input from user
			
			while(!fi.graph.containsVertex(fi.airport(deptAirCode)))	// Checking if the airport code entered by the user is in the graph
			{
				System.err.println("Please enter a valid airport code. (Check your spelling or your CAPS LOCK)");
				deptAirCode = sc.nextLine();	// Recieve input from user
			}
			
			System.out.println("Please enter the airport code where you would like to end your trip : ");
			arrAirCode = sc.nextLine();		// Recieve input from user
			
			while(!fi.graph.containsVertex(fi.airport(arrAirCode)))		// Checking if the airport code entered by the user is in the graph
			{
				System.err.println("Please enter a valid airport code. (Check your spelling or your CAPS LOCK)");
				arrAirCode = sc.nextLine();		// Recieve input from user
			}
			
			if(arrAirCode.equals(deptAirCode))		// Checking if the destination and origin airport codes are the same
			{
				System.err.println("You are starting and ending at the same place. Change your destination dude!");
				arrAirCode = sc.nextLine();		// Recieve input from user
			}
			
			deptAirName = fi.airport(deptAirCode).getName() + "(" + deptAirCode + ")";	// Storing the departing airport name
			arrAirName = fi.airport(arrAirCode).getName() + "(" + arrAirCode +")";		// Storing the arrival airport name	
			
			// Printing the information to the user
			System.out.println("You are travelling from " + deptAirName + " to " + arrAirName + ". Nice choice dude!\n");
			
			//int leastCost = 0,leastHops = 0,shortCost = 0,shortHops = 0;
			List<String> flightList = null;		// Initializing the list of flights to null
			int totalHops = 0;		// Total hops is 0 at the start
			
			String tableFormat =  "%-15.15s" + "" + "%-20.20s" + "" + " %-15.15s" + "" + "%-15.15s" + "" + "%-20.20s" + "" + "%-15.15s";	// Format for the table to be displayed
				
			System.out.println("Do you want to exclude any airports? Enter YES OR NO");		// Asking the user if they want to exclude any airports
			String option = sc.nextLine();	// String variable to store user's input
			
			if(option.equals("YES"))	// If user inputs YES
			{
				List <String> excluding = new ArrayList<>();	// Instantiate a new array list to hold all airports to be excluded
				System.out.println("Enter" + " DONE " + "if you dont want to exclude any more airports");
				String excl = sc.nextLine();	// String variable to store the user input(airport code to be excluded)
				
				if(!excl.equals("DONE"))
				{
					if(!fi.graph.containsVertex(fi.airport(excl)))	// Checks if the airport code entered is present in the graph
					{
						System.out.println("Please enter a valid airport code");
						excl = sc.nextLine();
					}
					else
					{
						excluding.add(excl);	// Adds the airport to the excluding list
					}
				}
				
				flightList = fi.leastCost(deptAirCode, arrAirCode, excluding).getFlights();	// Calls leastCost with the excluding list passed as an argument
			}
			
			else 
			{
				flightList = fi.leastCost(deptAirCode, arrAirCode).getFlights();	// Calls leastCost without excluding list
			}
			
			System.out.println(String.format(tableFormat, "Leg","Leave","At","On","Arrive","At"));	// Prints the table using the format
			
			for(String f : flightList)	// Iterates through the flightList
			{
				FlightImpl temp = fi.flight(f);
							
				String hopNo = String.valueOf(totalHops+1);		// String variable to store the no. of hops
				String dept = null; // temp.getFrom().getName(); //+ "(" + temp.getFrom().getCode() + ")";	// Variable to store the departure airport's name
				String deptTime = temp.getFromGMTime();		// Variable to store the departure time
				String flightCode = temp.getFlightCode();	// Variable to store the flight code
				String arr = null; // temp.getTo().getName(); // "(" + temp.getTo().getCode() + ")";		// Variable to store the arrival airport's name
				String arrTime = temp.getToGMTime();		// Variable to store the arrival time
				
				//String deptAir = String.valueOf(dept);
								
				System.out.format(tableFormat,hopNo,dept,deptTime,flightCode,arr,arrTime);	// Prints the data with accordance to the table format
				System.out.println();
				
				
				totalHops++;	// Increment the no. of hops
			}
			
			int totalCost = fi.leastCost(deptAirCode, arrAirCode).totalCost();				// Variable to store the total cost of the trip
			int totalAirTime = fi.leastCost(deptAirCode, arrAirCode).airTime();				// Variable to store the total air time in the trip
			int totalConTime = fi.leastCost(deptAirCode, arrAirCode).connectingTime();		// Variable to store the total connection time in the trip
			int totalTime = fi.leastCost(deptAirCode, arrAirCode).totalTime();				// Variable to store the total time of the trip
			
			System.out.println();
			System.out.println("Total Cost: $" + totalCost);								// Print the total cost of the trip
			System.out.println("Total Time in the Air: " + totalAirTime +" mins");			// Print the total air time in the trip
			System.out.println("Total Connection Time: " + totalConTime + " mins");			// Print the total connection time in the trip
			System.out.println("Total connections: " + totalHops);							// Print the total no of connections in the trip
			System.out.println("Total time of the trip: " + totalTime + " mins");			// Print the total time of the trip

		} catch (FileNotFoundException | FlyPlannerException e) {
			e.printStackTrace();
		}

	}

}
