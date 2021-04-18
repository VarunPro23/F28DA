package F28DA_CW2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.jgrapht.GraphPath;

public class TripImpl implements TripA<AirportImpl, FlightImpl>, TripB<AirportImpl, FlightImpl> 
{	
	private List<FlightImpl> flightList;		// List of flights
	
	// Parametric constructor
	public TripImpl(List<FlightImpl> flightList)
	{
		this.flightList = flightList;
	}
	
	@Override
	// Method to calculate the time between two connecting flights
	public int connectingTime() 
	{
		// TODO Auto-generated method stub
		int connectingTime = 0;		// Variable to store the connection flight
		
		for(int i = 0;i < flightList.size() - 1;i++)		// Loop to iterate through the flight list
		{
			String arrTime = flightList.get(i).getToGMTime();		// String variable to store the arrival time
			String deptTime = flightList.get(i+1).getFromGMTime();	// String variable to store the departure time
			
			// The time between arrival and departure of the next flight is connection time
			connectingTime += calcTime(arrTime,deptTime);		// Calculate the connection time
		}
		
		return connectingTime;		// Return the final connection time
	}

	@Override
	public int totalTime() 		// Method to calculate the total time of the trip
	{
		// TODO Auto-generated method stub
		return airTime() + connectingTime();
	}

	@Override
	public List<String> getStops() 		// Method to return the codes of the airports where the flight stops in the trip 
	{
		// TODO Auto-generated method stub
		List<String> airCodeList = new ArrayList<>();		// ArrayList of all stops in the trip
		airCodeList.add(flightList.get(0).getFlightCode());	// Adds the flight's code to the list
		
		for(FlightImpl flight : flightList)		// Iterates through the flight list
		{
			airCodeList.add(flight.getTo().getCode());	// Adds the airport code to the list
		}
		
		return airCodeList;	// Return the list of stops
	}

	@Override
	public List<String> getFlights() 	// Method to return the list of flight codes in the trip
	{
		// TODO Auto-generated method stub
		List<String> flights = new ArrayList<>();	// ArrayList of all flights in the trip
		
		for(FlightImpl flight : flightList)		// Iterates through the flight list
		{
			flights.add(flight.getFlightCode());	// Adds the flight code to the flight list
		}
		
		return flights;		// Return the list of flights
	}

	@Override
	public int totalHop() 		// Method to return the total number of connections in the trip
	{
		// TODO Auto-generated method stub
		return flightList.size();	// The size of the flight list will be equal to the number of connections made
	}

	@Override
	public int totalCost() 
	{
		// TODO Auto-generated method stub
		int totalCost = 0;		// Variable to store the total cost of the trip
		
		for(FlightImpl flight : flightList)		// Iterate through the flight list
		{
			totalCost += flight.getCost();		// Add the cost of each flight from the list
		}
		
		return totalCost;		// Return the total cost
	}

	@Override
	public int airTime() 
	{
		// TODO Auto-generated method stub
		int airTime = 0;		// Variable to store the airtime
		
		for(int i = 0;i < flightList.size();i++)	// Iterate through the flight list
		{
			// String variables to store the departure and arrival time
			String deptTime = flightList.get(i).getFromGMTime();	
			String arrTime = flightList.get(i).getToGMTime();
			
			// The time between departure and arrival is the air time
			airTime += calcTime(deptTime,arrTime);		// Calculate the airtime using the helper function
		}
		return airTime;		// Return the air time 
	}

	/*
	 * Helper function
	 * Calculates the time between two given times
	 * Returns the time in minutes
	 * Referenced from https://stackoverflow.com/search?q=convert+time+from+string+to+int+in+java
	 */
	private int calcTime(String t1, String t2) 
	{
		// TODO Auto-generated method stub
		String start = t1;		// String variable to store the starting time
		// Int variables to store the starting hour and minutes 
		int hrAtStart = 0;
		int minAtStart = 0;
		
		String end = t2;		// String variable to store the end time
		// Int variables to store the end hour and minutes
		int hrAtEnd = 0;
		int minAtEnd = 0;
		
		// Prefix to be added to time display
		if(t1.length() == 1)
		{
			start = "000" + start;
		}
		else if(t1.length() == 2)
		{
			start = "00" + start;
		}
		else if(t1.length() == 3)
		{
			start = "0" + start;
		}
		
		hrAtStart = Integer.parseInt(start.substring(0, 2));	// First 2 numbers are hour
		minAtStart = Integer.parseInt(start.substring(2));		// Last 2 numbers are minutes

		// Prefix to be added to time display
		if(t2.length() == 1)
		{
			end = "000" + end;
		}
		else if(t2.length() == 2)
		{
			end = "00" + end;
		}
		else if(t2.length() == 3)
		{
			end = "0" + end;
		}
		
		hrAtEnd = Integer.parseInt(end.substring(0, 2));		// First 2 numbers are hour 
		minAtEnd = Integer.parseInt(end.substring(2));			// Last 2 numbers are minutes
		
		int diffHour = 0, diffMin = 0;		// The difference
		
		/*  If we travel from a day to another, the time at destination will be less than the start time.
		 *  Add 24 to get correct number.
		 */
		if(hrAtEnd < hrAtStart)	
		{
			diffHour = (hrAtEnd - hrAtStart) + 24;
		}
		else 
		{
			diffHour = hrAtEnd - hrAtStart;
		}
		
		/*
		 * If the end minute is less than start minute, we have to add 60 to get the correct
		 *  number. We also must decrement hour, since a full hour does not pass.
		 */
		if(minAtEnd < minAtStart)
		{
			diffMin = (minAtEnd - minAtStart) + 60;
			diffHour--;
		}
		else
		{
			diffMin = minAtEnd - minAtStart;
		}
		
		return (diffHour * 60) + diffMin;	// Return the time in minutes
	}

}
