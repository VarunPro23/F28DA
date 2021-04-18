package F28DA_CW1;

import java.util.Iterator;

/**
 * Interface your set of words has to implement
 */
public interface WordsSet {

	/**
	 * Adds a word to the set.
	 * 
	 * @param word Word to add.
	 * @throws SpellCheckException If {@code word} is already present.
	 **/
	public void insWord(String word) throws SpellCheckException;

	/**
	 * Deletes the word from the set.
	 * 
	 * @param word Word to delete.
	 * @throws SpellCheckException If the word is not present.
	 */
	public void rmWord(String word) throws SpellCheckException;

	/**
	 * Returns true if a word is present.
	 * 
	 * @param word Word being checked.
	 */
	public boolean wordExists(String word);

	/**
	 * Returns the number of words stored in the set.
	 */
	public int getWordsCount();

	/**
	 * Returns an iterator over all words stored in the set.
	 */
	public Iterator<String> getWordsIterator();

}