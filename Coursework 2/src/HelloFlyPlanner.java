package F28DA_CW2;

import java.util.Scanner;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleGraph;

public class HelloFlyPlanner 
{

	public static void main(String[] args) 
	{
		
		// The following code is from HelloJGraphT.java of the org.jgrapth.demo package
		
		System.err.println("The example code is from HelloJGraphT.java from the org.jgrapt.demo package.");
		System.err.println("Use similar code to build the small graph from Preliminary Part by hand.");
		System.err.println("Note that you will need to use a different graph class as SimpleGraph since your graph is not just a Simple Graph.");
		System.err.println("Once you understand how to build such graph by hand, move to Part A to build a more substantial graph.");
		// Code is from HelloJGraphT.java of the org.jgrapth.demo package (start)
        Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);

        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";

        // add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        // add edges to create a circuit
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v4, v1);

        // note undirected edges are printed as: {<v1>,<v2>}
        System.out.println("-- toString output");
        // @example:toString:begin
        System.out.println(g.toString());
        // @example:toString:end
        System.out.println();
		// Code is from HelloJGraphT.java of the org.jgrapth.demo package (start)
        
        String[] cities = {"Edinburgh","Heathrow","Dubai","Sydney","Kuala Lumpur"};
        
        Scanner sc = new Scanner(System.in);
        String start, end;
        
        // Instantiating a graph that takes String as vertex and DefaultWeightedEdge as edge
        SimpleDirectedWeightedGraph<String,DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        
        // Add the vertices to the graph, with each index representing an airport
        graph.addVertex(cities[0]);		// Edinburgh
        graph.addVertex(cities[1]);		// Heathrow
        graph.addVertex(cities[2]);		// Dubai
        graph.addVertex(cities[3]);		// Sydney
        graph.addVertex(cities[4]);		// Kuala Lumpur
        
        // Creating edges for the graph and assigning their prices
        DefaultWeightedEdge EtoH = graph.addEdge(cities[0],cities[1]);
        graph.setEdgeWeight(EtoH,80);									// Edinburgh -> Heathrow
        
        DefaultWeightedEdge HtoE = graph.addEdge(cities[1],cities[0]);
        graph.setEdgeWeight(HtoE,80);									// Heathrow -> Edinburgh
        
        DefaultWeightedEdge DtoK = graph.addEdge(cities[2],cities[4]);
        graph.setEdgeWeight(DtoK,170);									// Dubai -> Kuala Lumpur
        
        DefaultWeightedEdge KtoD = graph.addEdge(cities[4],cities[2]);
        graph.setEdgeWeight(KtoD,170); 									// Kuala Lumpur - > Dubai				
        
        DefaultWeightedEdge HtoS = graph.addEdge(cities[1],cities[3]);
        graph.setEdgeWeight(HtoS,570);									// Heathrow -> Sydney
        
        DefaultWeightedEdge StoH = graph.addEdge(cities[3],cities[1]);
        graph.setEdgeWeight(StoH,570);									// Sydney -> Heathrow
        
        DefaultWeightedEdge HtoD = graph.addEdge(cities[1],cities[2]);
        graph.setEdgeWeight(HtoD,130);									// Heathrow -> Dubai
        
        DefaultWeightedEdge DtoH = graph.addEdge(cities[2],cities[1]);
        graph.setEdgeWeight(DtoH,130);									// Dubai -> Heathrow
        
        DefaultWeightedEdge KtoS = graph.addEdge(cities[4],cities[3]);
        graph.setEdgeWeight(KtoS,150);									// Kuala Lumpur -> Sydney
        
        DefaultWeightedEdge StoK = graph.addEdge(cities[3],cities[4]);
        graph.setEdgeWeight(StoK,150);									// Sydney -> Kuala Lumpur	
        
        DefaultWeightedEdge DtoE = graph.addEdge(cities[2],cities[0]);
        graph.setEdgeWeight(DtoE,190);									// Dubai -> Edinburgh
        
        DefaultWeightedEdge EtoD = graph.addEdge(cities[0],cities[2]);
        graph.setEdgeWeight(EtoD,190);									// Edinburgh -> Dubai	
        
        // String.format is used from here onwards. It is used to align the text for aesthetic display. 
        System.out.println(String.format("\n\n--------------------------------------------------------------\n"));
        System.out.println(String.format("The table below shows all the possible flights with their prices."));
        System.out.println(String.format("\n----------------------------------------------------------------\n"));
        
        System.out.println(String.format("%22s %22s" , "Flights", "\tCost"));	// The header 
        System.out.println(String.format("-------------------------------------------|---------"));
        
        for(String s : graph.vertexSet())	// Iterating through the graph's vertices for all strings
        {
        	for(DefaultWeightedEdge edge : graph.outgoingEdgesOf(s))	// Get the edges from the string
        	{	
        		// Prints the information to be displayed on the table
        		System.out.println(String.format("%20s %5s %-20s",s,"--->",graph.getEdgeTarget(edge) + "\t\t$" + graph.getEdgeWeight(edge)));
        	}
        }
        
        // LEAST COST TRIP BETWEEN TWO CITIES
        
        System.out.println("\n\n");
        
        System.out.println("Enter your departure city from the table:");
        start = sc.nextLine();	// User enters the departing city
        
        while(!graph.containsVertex(start))	// Checks if the city entered by the user is in the graph
        {
        	System.err.println("City is not present in the table.\n");	// Error thrown 
        	System.out.println("Please enter your start city again.(Make sure your spellings are correct!");
        	start = sc.nextLine();	// User asked to enter city again
        }
        
        System.out.println("Enter your destination city from the table:");
        end = sc.nextLine();	// User enters the destination city
        
        while(!graph.containsVertex(end))	// Checks if the city entered by the user is in the graph
        {
        	System.err.println("City is not present in the table.\n");	// Error thrown 
        	System.out.println("Please enter your destination city again.(Make sure your spellings are correct!");
        	end = sc.nextLine();	// User asked to enter city again
        }
        
        if(end.equals(start))	// Checks if the origin and destination cities are the same
        {
        	System.out.println("You dont need flights to reach the place where you already are. Enter a different destination please.");
        	end = sc.nextLine();	// Error thrown and user asked to enter city again
        }
        
        // Informs the user that the data is stored
        System.out.println("\nYou are starting your trip from " + start + " and ending it at " + end);
        
        // Instantiates a dijkstra shortest path to store the graph
        DijkstraShortestPath<String,DefaultWeightedEdge> dsp = new DijkstraShortestPath<String, DefaultWeightedEdge>(graph);
        GraphPath<String,DefaultWeightedEdge> gp = dsp.getPath(start, end);	// Instantiates a graph path using the dijkstra graph
        
        double cost = 0;	// Variable to store the total cost of the trip
        int noOfFlights = 1;	// Variable to store the no of connections in the trip
        
        System.out.println("\n The shortest path from " + start + " to " + end + " is: \n");
        
        for(DefaultWeightedEdge x : gp.getEdgeList())	// Iterate through all the edges in the graph path
        {
        	// Print the flight information
        	System.out.println("Connection " + noOfFlights + ": " + graph.getEdgeSource(x) + " ---> " + graph.getEdgeTarget(x));
        	
        	cost += graph.getEdgeWeight(x);	// Add the flight's cost to the total cost
        	noOfFlights++;	// Increment the no of connections by 1, since a flight is taken now
        }
        
        System.out.println("\nTotal cost: " + "$" + cost);        // Print the total cost of the trip
	}

}
