package F28DA_CW1;

import java.util.ArrayList;
import java.util.Iterator;

public class HTWordsSet implements WordsSet, Hashing, Monitor
{
	private String[] HT;
	private int size;
	private float maxLoad;
	private int words;
	private final String ph = ""; 
	/* ph is an empty string that is is used as a placeholder 
	   when a word is deleted from the hash table.*/
	private int noOfProbes;
	private int noOfOperations;
	
	// Constructor to set maxLoad to 0.5
	public HTWordsSet()
	{
		size = 7;
		HT = new String[size];
		words = 0;
		maxLoad = 0.5f;		// 0.5f is float while 0.5 is double
		noOfProbes = 0;
		noOfOperations = 0;
	}
	
	// Constructor to set maxLoad at construction time
	public HTWordsSet(float newMaxLoad)
	{
		size = 7;
		HT = new String[size];
		words = 0;
		maxLoad = newMaxLoad;
		noOfProbes = 0;
		noOfOperations = 0;
	}
	

	@Override
	public float getMaxLoadFactor()
	{
		// Function to return the max load factor
		return maxLoad;
	}

	@Override
	public float getLoadFactor() 
	{
		// Function to calculate the load factor
		return (float)words/size;
	}

	@Override
	public float getAverageProbes() 
	{
		// Function to calculate the average no. of probes
		return (float)noOfProbes/noOfOperations;
	}

	@Override
	public int giveHashCode(String s) 
	{
		// Function to return the hash code of a word.
		// Polynomial accumulation is being used in this function
		s.hashCode();
		int a = 0;		// Polynomial accumulator
		int pos = 1;
		for(int i=0;i<s.length();i++)
		{
			a += Integer.valueOf(s.charAt(i)) * pos;
			pos *= 33;
		}
		return Math.abs(a);		// returns the absolute value
	}

	@Override
	public void insWord(String word) throws SpellCheckException
	{
		// Function to add a word into the array
		
		// If the current load factor is greater than the max load factor, then call resize()
		if(getLoadFactor() > getMaxLoadFactor())
		{
			resize();
		}
		
		int hashCode = giveHashCode(word) % size;	// Gives the hashcode of the word
		int doubleHash = 5 - (giveHashCode(word) % 5);	// Gives the double hashing value in base 5
		noOfOperations++;		// Increase the count of operations by 1
		noOfProbes++;		// Increase the count of probes by 1
		
		// Checking if the position pointed by hashCode is not empty nor ph
		while(HT[hashCode] != null && !HT[hashCode].equals(ph))	
		{
			if(HT[hashCode].equals(word))	// Checks if the word is already present in the hash table
			{
				// Throws a exception if the word is already present in the Hash Table
				throw new SpellCheckException(word + " already present.");
			}
			
			hashCode = (hashCode + doubleHash) % size;
			noOfProbes++;		// Increases the number of probes every time the loop runs
		}
		
		HT[hashCode] = word;	// Adds the word to the array HT
	//	System.out.println("'" + word + "' has been added");
		words++;				// Increase the count of words
  	}

	@Override
	public void rmWord(String word) throws SpellCheckException
	{
		// Function to remove a word from the array
		
		int hashCode = giveHashCode(word) % size;	// Gives the hashcode of the word
		int doubleHash = 5 - (giveHashCode(word) % 5);	// Gives the double hashing value in base 5
		noOfOperations++;
		noOfProbes++;
		
		while(HT[hashCode] != null)		// Checks if the position pointed by hashcode is not null
		{
			if(HT[hashCode].equals(word))	
			// Checks if the word at the hashcode position is equal to the argument passed
			{
				HT[hashCode] = ph;	// Replace the word at that position with the value of the placeholder
				words--;			// Decrease the no of words count
				return;
			}
		
			hashCode = (hashCode + doubleHash) % size;
			noOfProbes++;		// Increases the number of probes every time the loop runs	
		}
		throw new SpellCheckException(word + " not present. Try again.");	// Throws a exception if the word is not found in the Hash Table
	}

	@Override
	public boolean wordExists(String word) 
	{
		// Function to check if a word exists in the array
		
		int hashCode = giveHashCode(word) % size;	// Gives the hashcode of the word
		int doubleHash = 5 - (giveHashCode(word) % 5);	// Gives the double hashing value in base 5
		noOfOperations++;
		noOfProbes++;
		
		while(HT[hashCode] != null)		// Checking if the array position is not equal to null
		{
			if(HT[hashCode].equals(word))	// Checking if the word at the array position is equal to the argument passed
			{
				return true;	// Returns true if the word is present in the array
			}
			
			hashCode = (hashCode + doubleHash) % size;
			noOfProbes++;		// Increases the number of probes every time the loop runs
		}
		return false;	// Returns false if the word is not present in the array
	}

	@Override
	public int getWordsCount() 
	{
		// Returns the total no. of words
		return words;
	}

	@Override
	public Iterator<String> getWordsIterator() 
	{
		ArrayList<String> wordList = new ArrayList();
		for(String s : HT)		// Loops through the hash table
		{
			if(s != null && !s.equals(ph))	// Checking if the word is not null and not a placeholder
			{
				wordList.add(s);	// Adds the word to the ArrayList
			}
		}
		return wordList.iterator();
	}
	
	private void resize()	
	// Increases array size to the next prime number larger than the current array by two times 
	{
		size *= 2;
		while(!checkPrime(size))
		{
			size++;
		}
		
		Iterator<String> HTIter = getWordsIterator();
		String[] newHT = new String[size];
		words = 0;
		HT = newHT;		// Overwrites the old array
		
		while(HTIter.hasNext())
		{
			try
			{
				insWord(HTIter.next());
			} catch (SpellCheckException e) {}
		}
	}
	
	private boolean checkPrime(int num)	// Checks if the given number is prime or not
	{
		for(int i = 2;i < num;i++)
		{
			if((num%i)==0)
			{
				return false;
			}
		}
		return true;
	}
}