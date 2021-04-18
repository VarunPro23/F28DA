package F28DA_CW2;

import java.util.Set;

public class AirportImpl implements AirportA, AirportB 
{

	private String code;	// Variable to store the airport code
	private String name;	// Variable to store the airport name
	
	// Parametric constructor
	public AirportImpl(String[] data)
	{
		this.code = data[0];	// First index is the airport code
		this.name = data[1];	// Second index is the airport name
	}

	@Override
	// Method to get airport code
	public String getCode() 
	{
		// TODO Auto-generated method stub
		return code;
	}

	@Override
	// Method to get airport name
	public String getName() 
	{
		// TODO Auto-generated method stub
		return this.name;
	}
	
	@Override
	public Set<AirportImpl> getDicrectlyConnected() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDicrectlyConnected(Set<AirportImpl> dicrectlyConnected) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void setDicrectlyConnectedOrder(int order) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public int getDirectlyConnectedOrder() 
	{
		// TODO Auto-generated method stub
		return 0;
	}


} 
