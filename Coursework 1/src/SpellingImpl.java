package F28DA_CW1;

public class SpellingImpl implements Spelling 
{
	public WordsSet suggestions(String word, WordsSet dict) 
	{
		// TO IMPLEMENT
		// Referenced from https://stackoverflow.com/questions/51513284/spell-checker-in-java-file-open-but-no-output
		
		HTWordsSet totalSuggest = new HTWordsSet();		// Creating a new HashTable to store all the suggestions
	
		char[] wordChar = word.toCharArray();		// A char array to store the letters of the word
		
		String nWord;	// String to store the new word
		char letter;	// Counter variable
		
		// Letter substitution
		for(int i = 0;i < word.length();i++)
		{
			wordChar = word.toCharArray();		// Storing the letters of the word in the char array
			for(letter = 'a'; letter <= 'z';letter++)
			{
				wordChar[i] = letter;	// Substituting each index with a letter to try out different combinations.
				nWord = String.valueOf(wordChar);	// nWord stores the string representation of the char array
				if(dict.wordExists(nWord))	// Checks if the new suggested word exists in the dictionary
				{
					try
					{
						totalSuggest.insWord(nWord);	// Tries to add it to the Hashtable if the word exists
					}
					catch(SpellCheckException e) {}		// Catches any exceptions thrown
				}
			}
		}	
		
		// Letter omit
		for(int i = 0;i < word.length();i++)
		{
			// Omits one letter each time the loop occurs to see if the word exists in the dictionary 
			nWord = word.substring(0, i) + word.substring(i+1, word.length());
			
			if(dict.wordExists(nWord))		// Checks if the new suggested word exists in the dictionary
			{
				try
				{
					totalSuggest.insWord(nWord);	// Tries to add it to the Hashtable if the word exists
				}
				catch(SpellCheckException e) {}		// Catches any exceptions thrown
			}
		}
		
		// Letter add
		for(int i = 0;i < word.length();i++)
		{
			for(letter = 'a';letter <= 'z';letter++)
			{
				// Adds a letter between the two substrings to create a word
				nWord = word.substring(0, i) + letter + word.substring(i, word.length());
				if(dict.wordExists(nWord))		// Checks if the new suggested word exists in the dictionary
				{
					try
					{
						totalSuggest.insWord(nWord);	// Tries to add it to the Hashtable if the word exists
					}
					catch(SpellCheckException e) {}		// Catches any exceptions thrown
				}
			}
		}
		
		// Letter reversal
		wordChar = word.toCharArray();
		char temp;		// temporary variable to swap the letters
		for(int i = 0;i < word.length() - 1;i++)
		{
			wordChar = word.toCharArray();
			
			temp = wordChar[i];
			wordChar[i] = wordChar[i+1];
			wordChar[i+1] = temp;
			
			nWord = String.valueOf(wordChar);		// nWord stores the string value of wordChar
			
			if(dict.wordExists(nWord))		// Checks if the new suggested word exists in the dictionary
			{
				try
				{
					totalSuggest.insWord(nWord);	// Tries to add it to the Hashtable if the word exists
				}
				catch(SpellCheckException e) {}		// Catches any exceptions thrown
			}
		}
		return totalSuggest;		// Returns all the suggestions
	}
}
