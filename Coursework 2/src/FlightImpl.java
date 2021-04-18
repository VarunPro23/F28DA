package F28DA_CW2;

public class FlightImpl implements Flight 
{
	private String flightCode;		// String variable to store the flight code
	private String fromGMTime;		// String variable to store the departure time
	private String toGMTime;		// String variable to store the arrival time
	private AirportImpl to;			// AirportImpl object to store the departure airport
	private AirportImpl from;		// AirportImpl object to store the arrival airport
	private int cost;				// Variable to store the cost
	
	// Parametric constructor
	public FlightImpl(String[] data,AirportImpl from, AirportImpl to)
	{
		this.flightCode = data[0];		// First index is flight code
		this.fromGMTime = data[2];		// Third index is time at origin airport
		this.toGMTime = data[4];		// Fifth index is time at destination airport
		to = this.to;
		from = this.from;
		this.cost = Integer.parseInt(data[5]);	// Sixth index is cost
	}
	

	@Override
	// Method to get flight code
	public String getFlightCode() 
	{
		// TODO Auto-generated method stub
		return flightCode;
	}

	@Override
	// Method to get arrival airport
	public AirportImpl getTo() 
	{
		// TODO Auto-generated method stub
		return to;
	}

	@Override
	// Method to get departure airport
	public AirportImpl getFrom() 
	{
		// TODO Auto-generated method stub
		return this.from;
	}

	@Override
	// Method to get departure time
	public String getFromGMTime() 
	{
		// TODO Auto-generated method stub
		return fromGMTime;
	}

	@Override
	// Method to get arrival time
	public String getToGMTime() 
	{
		// TODO Auto-generated method stub
		return toGMTime;
	}

	@Override
	// Method to get cost of flight
	public int getCost() 
	{
		// TODO Auto-generated method stub
		return cost;
	}
	
}
