package F28DA_CW1;

import static org.junit.Assert.*;

import org.junit.Test;

public class ModificationsProvidedTest {

	@Test
	public void testOmission() {

		WordsSet dict = new LLWordsSet();
		try {
			dict.insWord("cats");
			dict.insWord("like");
			dict.insWord("on");
			dict.insWord("of");
			dict.insWord("to");
			dict.insWord("play");
		} catch (SpellCheckException e) {
			fail("Error with linked list implementation");
		}
		Spelling speller = new SpellingImpl();
		WordsSet sugg = speller.suggestions("catts", dict);
		assertTrue(sugg.wordExists("cats"));
	}

}
