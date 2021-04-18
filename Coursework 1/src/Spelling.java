package F28DA_CW1;

/**
 * Interface for spelling suggestions.
 */
public interface Spelling {

	/** Suggests word modifications for a given word and a given word dictionary. */
	public WordsSet suggestions(String word, WordsSet dict);

}
