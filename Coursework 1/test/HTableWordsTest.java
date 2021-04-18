package F28DA_CW1;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.junit.Test;

public class HTableWordsTest
{

	@Test
	public void test1() 
	{
		float maxLF = (float) 0.5;
		HTWordsSet h = new HTWordsSet(maxLF);
		String word = "abc";
		int hash = h.giveHashCode(word);
		System.out.println(hash);
		assertTrue(hash == 3);
		
		
	}	
		
	@Test
	public void test2() 
	{
		try 
		{
		float maxLF = (float) 0.5;
		HTWordsSet h = new HTWordsSet(maxLF);
		String word = "s";
		h.insWord(word);
		assertTrue(h.getWordsCount() == 1);
		}
		catch(SpellCheckException e) 
		{
		fail();
		}
	}

	// ...
}