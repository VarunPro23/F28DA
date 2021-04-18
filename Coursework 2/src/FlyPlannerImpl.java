package F28DA_CW2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.AsUnweightedGraph;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class FlyPlannerImpl implements FlyPlannerA<AirportImpl,FlightImpl>, FlyPlannerB<AirportImpl,FlightImpl> 
{
	SimpleDirectedWeightedGraph<AirportImpl, FlightImpl> graph;
	
	public FlyPlannerImpl()
	{
		graph = new SimpleDirectedWeightedGraph<AirportImpl,FlightImpl>(FlightImpl.class);
	}
	
	/*
	 * Returns the set of airports directly connected to the given airport.
	 * They are said to be directly connected if two flights connecting them in a single hop
	 * in both directions exist. I have used the concept "Intersection of two sets" to find the 
	 * airports directly connected.
	 * 
	 * Referenced from https://stackoverflow.com/questions/51113134/union-or-intersection-of-java-sets 
	 */
	@Override
	public Set<AirportImpl> directlyConnected(AirportImpl airport) 
	{
		// TODO Auto-generated method stub
		Set<AirportImpl> deptAirport = new HashSet<AirportImpl>();		// Set of departure airports
		Set<AirportImpl> arrAirport = new HashSet<AirportImpl>();		// Set of arrival airports
		
		Set<FlightImpl> flights = graph.edgesOf(airport);		// Set of flights that goes the airport given
		
		for(FlightImpl i : flights)		// Iterating through the flight list
		{
			deptAirport.add(i.getFrom());	// Adds the departing flights to the departure set
			arrAirport.add(i.getTo());		// Adds the arriving flights to the arrival set
		}
		
		deptAirport.retainAll(arrAirport);	// Gets the intersection of the two sets
		deptAirport.remove(airport);		// Removes the airport given from the set, since its not a connection
		
		return deptAirport;		// Return one of the set, since both sets now only contain bi-connected flights
	}

	@Override
	public int setDirectlyConnected() 
	{
		// TODO Auto-generated method stub
		int noOfDirectConnect = 0;
		int size = 0;
		
		Set<AirportImpl> connections = new HashSet<AirportImpl>();
		
		for(AirportImpl a : graph.vertexSet())
		{
			connections = directlyConnected(a);
			size = connections.size();
			noOfDirectConnect += size;
		}
		
		return noOfDirectConnect;
	}

	@Override
	public int setDirectlyConnectedOrder() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<AirportImpl> getBetterConnectedInOrder(AirportImpl airport) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Returns the airport code of the airport for two people to meet, who start from 
	 * different airports. The airport is shown based on trip cost.
	 */
	@Override
	public String leastCostMeetUp(String at1, String at2) throws FlyPlannerException 
	{
		// TODO Auto-generated method stub
		AirportImpl air1 = airport(at1);	// Airport object to store departure airport of first traveller
		AirportImpl air2 = airport(at2);	// Airport object to store departure airport of second traveller
		
		if(air1 == air2)	// Checking if both travellers start at the same airport
		{
			throw new FlyPlannerException("Both travellers cannot start at the same airport");
		}
		
		// The graph path between both the travellers
		GraphPath<AirportImpl,FlightImpl> gp = DijkstraShortestPath.findPathBetween(graph, air1, air2);
		
		if(gp == null)		// if path not found
		{
			throw new FlyPlannerException("Path not found! Error 404!");	// Throw exception
		}
		
		List<AirportImpl> airportList = gp.getVertexList();		// List of airports between first and second traveller
		List<String> airCode = new ArrayList<>();				// String list to hold all airport codes
		
		for(AirportImpl i : airportList)	// Iterate through the airport list
		{
			airCode.add(i.getCode());	// Add the airport's code to the string list
		}
		
		int mid = (airCode.size() - 1)/2;	// Find the middle index to get the middle airport
		
		return airCode.get(mid);	// Return the airport code of the middle airport
	}

	/*
	 * Returns the airport code of an airport for two people to meet, who begin from 
	 * different airports. The airport is shown based on the number of connections.
	 */
	@Override
	public String leastHopMeetUp(String at1, String at2) throws FlyPlannerException 
	{
		// TODO Auto-generated method stub
		AirportImpl air1 = airport(at1);	// Airport object to store departure airport of first traveller
		AirportImpl air2 = airport(at2);	// Airport object to store departure airport of second traveller
		
		// Create a temporary unweighted graph
		AsUnweightedGraph<AirportImpl,FlightImpl> tempGraph = new AsUnweightedGraph<AirportImpl,FlightImpl>(graph);
		
		if(air1 == air2)	// Checking if both the travellers start at the same airport
		{
			throw new FlyPlannerException("Both travellers cannot start at the same airport");
		}
		
		// The graph path between both the travellers
		GraphPath<AirportImpl,FlightImpl> gp = DijkstraShortestPath.findPathBetween(tempGraph, air1, air2);
		
		if(gp == null)		// If path not found
		{
			throw new FlyPlannerException("Path not found! Error 404!");	// Throw exception
		}
		
		List<AirportImpl> airportList = gp.getVertexList();		// List of airports between first and second traveller 
		List<String> airCode = new ArrayList<>();				// String list to hold all airport codes
		
		for(AirportImpl i : airportList)		// Iterate through the airport list
		{
			airCode.add(i.getCode());			// Add the airport's code to the string list
		}
		
		int mid = (airCode.size() - 1)/2;		// Find the middle index to get the middle airport
		
		return airCode.get(mid);				// Return the airport code of the middle airport
	}

	@Override
	public String leastTimeMeetUp(String at1, String at2, String startTime) throws FlyPlannerException 
	{
		// TODO Auto-generated method stub
		AirportImpl airCode1 = airport(at1);		// Airport object to store the departure airport of traveller 1 
		AirportImpl airCode2 = airport(at2);		// Airport object to store the departure airport of traveller 2
		
		List<String> airCode = new ArrayList<>();	// List of airport codes
		
		// Create a temporary graph
		SimpleDirectedWeightedGraph <AirportImpl,FlightImpl> tempGraph = new SimpleDirectedWeightedGraph<AirportImpl,FlightImpl>(FlightImpl.class);
		
		for(AirportImpl x : graph.vertexSet())	// Iterate through the airports in the graph
		{
			tempGraph.addVertex(x);		// Add them to the temporary graph
		}
		
		for(FlightImpl f : graph.edgeSet())		// Iterate through the flights in the graph
		{
			tempGraph.addEdge(f.getFrom(), f.getTo(), f);	// Add them to the temporary graph
			
			List<FlightImpl> flightList = new ArrayList<>();	// List to store flights
			flightList.add(f);	// Add the flights to the list
			
			TripImpl t = new TripImpl(flightList);	// Instantiate a new trip with the flight list
			
			String deptTime = f.getFromGMTime();		// String variable to store the departure time
			int onTime = 0, deptTimeInMin = 0, startTimeInMin = 0;
			int totTime = t.totalTime();	// Int variable to store the total time of the trip
			
			onTime = 60 * 24;		// Variable to store the overnight time (60 minutes * 24 hours)
			deptTimeInMin = calcTime(deptTime);		// Int variable to store the departure time by converting from string to int
			startTimeInMin = calcTime(startTime);	// Int variable to store the start time by converting from string to int
			
			AirportImpl deptAirport = f.getFrom();	// Object to store the departure airport
			
			if(deptAirport.equals(airCode1) || deptAirport.equals(airCode2))
			{
				// Algorithm taken from TripImpl.java
				if(deptTimeInMin < startTimeInMin)
				{
					totTime += onTime + (deptTimeInMin - startTimeInMin);
				}
				else
				{
					totTime += deptTimeInMin - startTimeInMin;
				}
			}
		}
		
		int mid = (airCode.size() - 1)/2;
		
		return airCode.get(mid);
	}

	@Override
	public boolean populate(FlightsReader fr) 
	{
		// TODO Auto-generated method stub
		boolean isPop = false;		// Flag set as false
		
		if(!isPop)		// If it is not populated
		{
			populate(fr.getAirports(),fr.getFlights());		// Overloading method with the two sets
			isPop = true;	// Change flag to true
		}
		
		return isPop;	// Return isPop
	}

	@Override
	public boolean populate(HashSet<String[]> airports, HashSet<String[]> flights) 
	{
		// TODO Auto-generated method stub
		boolean isPop = false;		// Flag set as false
		
		if(!isPop)		// If it is not populated
		{
			for(String[] airportSet : airports)		// Iterates through the HashSet airports
			{
				graph.addVertex(new AirportImpl(airportSet));	// Add them as vertices
			}
			
			for(String[] flightList : flights)		// Iterates through the HashSet flights
			{
				AirportImpl deptAir = airport(flightList[1]);	// Get the departure airport from the flightList
				AirportImpl arrAir = airport(flightList[3]);	// Get the arrival airport from the flightList	
				
				FlightImpl temp = new FlightImpl(flightList,deptAir,arrAir);	// Create a temporary oject
				graph.addEdge(deptAir, arrAir, temp);	// Add the temporary object as an edge
			}
			
			for(FlightImpl flight : graph.edgeSet())	// Iterate through the flights in the edge set
			{
				graph.setEdgeWeight(flight,flight.getCost());	// Set edge weight as the cost
			}
			
			isPop = true;	// Change flag to true
		}
		
		return isPop;	// Return isPop
	}

	@Override
	public AirportImpl airport(String code) 
	{
		// TODO Auto-generated method stub
		Set<AirportImpl> airports = graph.vertexSet();	// Create an airport set of all vertices 
		
		for(AirportImpl i : airports)	// Iterate through the airport set
		{
			if(i.getCode().equals(code))	// Check if the airport code passed is present in the set
			{
				return i;	// Return the airport
			}
		}
		
		return null;	// Return null if airport code is not found
	}

	@Override
	public FlightImpl flight(String code) 
	{
		// TODO Auto-generated method stub
		Set<FlightImpl> flights = graph.edgeSet();	// Create a flight set of all edges
		
		for(FlightImpl i : flights)		// Iterate through the flight set
		{
			if(i.getFlightCode().equals(code))	// Check if the flight code passed is present in the set
			{
				return i;	// Return the flight
			}
		}
		
		return null;	// Return null if the flight is not found
	}

	@Override
	public TripImpl leastCost(String from, String to) throws FlyPlannerException 
	{
		// TODO Auto-generated method stub
		AirportImpl deptAirport = airport(from);	// Object to store departure airport
		AirportImpl arrAirport = airport(to);		// Object to store arrival airport
		
		// Create a shortest graph path, from departure airport to arrival airport
		GraphPath<AirportImpl,FlightImpl> fp = DijkstraShortestPath.findPathBetween(graph, deptAirport, arrAirport);
		
		if(fp == null)	// If path does not exist, throw exception
		{
			throw new FlyPlannerException("Path does not exist! Error 404!");
		}
		
		TripImpl trip = new TripImpl(fp.getEdgeList());		// Instantiate a trip object 
		
		return trip;		// Return the trip
	}

	@Override
	public TripImpl leastHop(String from, String to) throws FlyPlannerException 
	{
		// TODO Auto-generated method stub
		AirportImpl deptAirport = airport(from);	// Object to store departure airport
		AirportImpl arrAirport = airport(to);		// Object to store arrival airport
		
		// Temporary graph, which is made to be the unweighted graph of the original
		Graph<AirportImpl,FlightImpl> tempGraph = new AsUnweightedGraph<>(graph);
		
		// Create a shortest graph path, from departure airport to arrival airport
		GraphPath<AirportImpl,FlightImpl> fp = DijkstraShortestPath.findPathBetween(tempGraph, deptAirport, arrAirport);
		
		if(fp == null)	// If path does not exist, throw exception
		{
			throw new FlyPlannerException("Path does not exist! Error 404!");
		}
		
		TripImpl trip = new TripImpl(fp.getEdgeList());		// Instantiate a trip object 
		
		return trip;		// Return the trip		
	}

	@Override
	public TripImpl leastCost(String from, String to, List<String> excluding) throws FlyPlannerException 
	{
		// TODO Auto-generated method stub
		AirportImpl deptAirport = airport(from);	// Object to store departure airport
		AirportImpl arrAirport = airport(to);		// Object to store arrival airport
		
		// A temporary graph holding all the vertices
		SimpleDirectedWeightedGraph<AirportImpl,FlightImpl> tempGraph = graph;
		
		for(String airCode : excluding)	// Iterate through the excluding list
		{
			tempGraph.removeVertex(airport(airCode));	// Remove the airports present in the excluding list from the temp graph
		}
		
		// Create a shortest graph path, from departure airport to arrival airport
		GraphPath<AirportImpl,FlightImpl> fp = DijkstraShortestPath.findPathBetween(tempGraph, deptAirport, arrAirport);
		
		if(fp == null)		// If path does not exist, throw exception
		{
			throw new FlyPlannerException("Path does not exist! Error 404!");
		}
		
		TripImpl trip = new TripImpl(fp.getEdgeList());		// Instantiate a trip object 
		
		return trip;		// Return the trip
	}

	@Override
	public TripImpl leastHop(String from, String to, List<String> excluding) throws FlyPlannerException 
	{
		// TODO Auto-generated method stub
		AirportImpl deptAirport = airport(from);	// Object to store departure airport
		AirportImpl arrAirport = airport(to);		// Object to store arrival airport
		
		// Temporary graph, which is made to be the unweighted graph of the original
		Graph<AirportImpl,FlightImpl> tempGraph = new AsUnweightedGraph<>(graph);
		
		for(String airCode : excluding) 	// Iterate through the excluding list
		{
			tempGraph.removeVertex(airport(airCode));	// Remove the airports present in the excluding list from the temp graph
		}
		
		// Create a shortest graph path, from departure airport to arrival airport
		GraphPath<AirportImpl,FlightImpl> fp = DijkstraShortestPath.findPathBetween(graph, deptAirport, arrAirport);
		
		if(fp == null)	// If path does not exist, throw exception
		{
			throw new FlyPlannerException("Path does not exist! Error 404!");
		}
		
		TripImpl trip = new TripImpl(fp.getEdgeList());		// Instantiate a trip object 
		
		return trip;		// Return the trip
	}
	
	/*
	 * Helper function
	 * Used to convert time from hours and minutes to minutes only
	 * Code taken from TripImpl.java, calcTime()
	 */
	private int calcTime(String time)
	{
		if(time.length() == 1)
		{
			time = "000" + time;		
		}
		else if(time.length() == 2)
		{
			time = "00" + time;
		}
		else if(time.length() == 3)
		{
			time = "0" + time;
		}
		
		int hr = 0,min = 0;
		
		hr = Integer.parseInt(time.substring(0,2));
		min = Integer.parseInt(time.substring(2));
		
		return (hr * 60) + min;		// Returns time in minutes
	}

}
