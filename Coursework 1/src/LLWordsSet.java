package F28DA_CW1;

import java.util.Iterator;
import java.util.LinkedList;

public class LLWordsSet implements WordsSet
{
	private LinkedList<String> W;
	
	public LLWordsSet()		// Constructor
	{
		W = new LinkedList<String>();	// Creates a linked list
	}

	@Override
	public void insWord(String word) throws SpellCheckException 
	{
		// Function to add a word to the Linked List
		
		for(String w : W) 	// Loops through the linked list
		{
			if(w.equals(word))		// Checks if the word is already there in the linked list
				throw new SpellCheckException(word + " is already present");	// Throws an exception
		}
		
		W.add(word);	// Else adds the word to the inked list
	//	System.out.println("'" + word + "' has been added");	// Confirmation message
	}

	@Override
	public void rmWord(String word) throws SpellCheckException 
	{
		// Function to remove a word from the linked list
		
		for(String w : W)	// Loops through the linked list
		{
			if(w.equals(word))	// Checks if the word is present in the linked list
			{
				W.remove(w);	// Removes the word from the linked list
				
				System.out.println("The word '" + w + "' has been removed.");	// Confirmation message
				return;
			}
		}
		throw new SpellCheckException("Word Not found");	// Throws an exception if word is not found
	}

	@Override
	public boolean wordExists(String word) 
	{
		// Function to check if word exists in the linked list
		
		for(String w : W)	// Loops through the linked list
		{
			if(w.contentEquals(word))	// Checks if the word is present in the linked list
				return true;	// Returns true if found
		}
		return false;	// Returns false if not found
	}

	@Override
	public int getWordsCount() 
	{
		// Function to return the word count
		return W.size();
	}

	@Override
	public Iterator<String> getWordsIterator() 
	{
		// Iterator to go through the linked list
		return W.iterator();
	}
}