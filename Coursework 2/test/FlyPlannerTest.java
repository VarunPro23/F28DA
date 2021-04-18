package F28DA_CW2;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class FlyPlannerTest {

	FlyPlannerImpl fi;

	@Before
	public void initialize() {
		fi = new FlyPlannerImpl();
		try {
			fi.populate(new FlightsReader());
		} catch (FileNotFoundException | FlyPlannerException e) {
			e.printStackTrace();
		}
	}

	// Add your own tests here!
	//
	// You can get inspiration from the tests in FlyPlannerProvidedTest
	// that uses the provided data set but also from the
	// leastCostCustomTest test that uses a custom-made graph

	@Test
	public void leastCostTest() 
	{
		try 
		{
			TripImpl trip = fi.leastCost("MAA", "EDI");
			assertEquals(2,trip.totalHop());
			assertEquals(519,trip.totalCost());
		} 
		catch (FlyPlannerException e) 
		{
			// TODO Auto-generated catch block
			fail();
		}
	}

	@Test
	public void leastCostExcTest()
	{
		try 
		{
			LinkedList<String> exclude = new LinkedList<String>();
			exclude.add("DXB");
			TripImpl trip = fi.leastCost("NCL", "NTL",exclude);
			
			assertEquals(4,trip.totalHop());
			assertEquals(1035,trip.totalCost());
			assertEquals(1061,trip.airTime());
		}
		catch (FlyPlannerException e) 
		{
			// TODO Auto-generated catch block
			fail();
		}
	}
	
	@Test
	public void leastHopTest()
	{
		try
		{
			TripImpl trip = fi.leastHop("DXB", "BGO");
			assertEquals(2,trip.totalHop());
		}
		catch (FlyPlannerException e)
		{
			fail();
		}
	}
		
	@Test
	public void leastHopMeetUpTest()
	{
		try
		{
			String meet = fi.leastHopMeetUp("MAA","EDI");
			assertEquals("FRA",meet);
		}
		catch (FlyPlannerException e)
		{
			fail();
		}
	}
	
	@Test
	public void leastCostMeetUpTest()
	{
		try
		{
			String meetPoint = fi.leastCostMeetUp("MEL", "BGO");
			assertEquals("KUL",meetPoint);
		}
		catch(FlyPlannerException e)
		{
			fail();
		}
	}
	
	@Test
	public void directlyConnectedTest()
	{
		AirportImpl airport = fi.airport("MEL");
		Set<AirportImpl> set = fi.directlyConnected(airport);
		assertEquals(29,set.size());
		//System.out.println(set.size());
	}
	
	@Test
	public void customTest()
	{
		try
		{
			TripImpl trip1 = fi.leastCost("MAA", "MEL");
		
			System.out.println(trip1.airTime());
			assertEquals(727,trip1.airTime());
			assertEquals(530,trip1.connectingTime());
			assertEquals(2,trip1.totalHop());
			assertEquals(637,trip1.totalCost());
			assertEquals(1257,trip1.totalTime());
			
		}
		catch (FlyPlannerException e)
		{
			fail();
		}
	}
}
