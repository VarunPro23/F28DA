package F28DA_CW1;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * Main class for the program
 */
public class SpellCheck 
{

	/**
	 * Main method for the program. The program takes two input filenames in the
	 * command line: the word dictionary file and the file containing the words to
	 * spell-check.
	 */
	public static void main(String[] args) 
	{
		if (args.length != 2) {
			System.err.println("Usage: SpellCheck dictionaryFile.txt inputFile.txt ");
			System.exit(1);
		}

		try 
		{
			BufferedInputStream dict, file;
			SpellingImpl speller;

			dict = new BufferedInputStream(new FileInputStream(args[0]));

			// TO IMPLEMENT
			
			long startTime = System.currentTimeMillis();	// Variable to store the start time
			
			HTWordsSet dictionary = new HTWordsSet();			// Hash table implementation
			//LLWordsSet dictionary = new LLWordsSet();		   // Linked List implementation	
			FileWordRead readW = new FileWordRead(dict);		
			
			while(readW.hasNextWord())		// while loop to check if the file has a word
			{
				try		
				{
					dictionary.insWord(readW.nextWord());		// Adds the word from the file into the dictionary
				}
				catch(SpellCheckException e)		// Catches any exceptions thrown
				{}
			}
			
			long finalTime = System.currentTimeMillis();	// Variable to store the end time
				
			System.out.println();
			System.out.println("Number of words: " + dictionary.getWordsCount());		// Print the number of words in the file

			dict.close();

			speller = new SpellingImpl();		

			file = new BufferedInputStream(new FileInputStream(args[1]));	

			// TO IMPLEMENT
			readW = new FileWordRead(file);		// Reads the file
						
			while(readW.hasNextWord())		// while loop to check if the file contains words
			{
				String word = readW.nextWord();		// String to store the word from the file
				if(!dictionary.wordExists(word))		// Checking if the word in the file doesnt exist in the dictionary
				{
					System.out.print("Misspelled word:- " + word + ", ");		// Prints out the misspelled word
					
					WordsSet rightW = speller.suggestions(word, dictionary);	// All the suggestions for the word is stored in this WordsSet
					Iterator<String> iter = rightW.getWordsIterator();		// Iterator to go through the WordsSet 
					
					while(iter.hasNext())	// while loop to go through the WordsSet
					{
						System.out.println("Suggestion:" + iter.next());	// Prints out all the suggestions for the word
					}
				}
				System.out.println();
			}
			
			file.close();		// Closes the file
			long runTime = finalTime - startTime; 	
			// The total run time is calculated by subtracting the start time from the end time 
			System.out.println("Time: " + runTime + "ms");	// Print out the run time

		} 
		catch (IOException e) 
		{ // catch exceptions caused by file input/output errors
			System.err.println("Missing input file, check your filenames");
			System.exit(1);
		}
	}

}
