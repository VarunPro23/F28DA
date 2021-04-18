package F28DA_CW1;

import static org.junit.Assert.*;

import org.junit.Test;

public class ModificationsTest {

	@Test
	public void test1() {
		// SUBSTITUTION
		
		WordsSet dict = new LLWordsSet(); // or new HTWordsSet();
		// ...
		try {
			dict.insWord("best");
		} catch (SpellCheckException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Spelling speller = new SpellingImpl();
		WordsSet sugg = speller.suggestions("test", dict);
		// ...
		assertTrue(sugg.wordExists("best"));
	}

	@Test
	public void test2() {
		// OMISSION
		
		WordsSet dict = new LLWordsSet(); // or new HTWordsSet();
		// ...
		try {
			dict.insWord("est");
		} catch (SpellCheckException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Spelling speller = new SpellingImpl();
		WordsSet sugg = speller.suggestions("test", dict);
		// ...
		assertTrue(sugg.wordExists("est"));
	}
	
	@Test
	public void test3() {
		// INSERTION
		
		WordsSet dict = new HTWordsSet(); // or new HTWordsSet();
		// ...
		try {
			dict.insWord("testa");
		} catch (SpellCheckException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Spelling speller = new SpellingImpl();
		WordsSet sugg = speller.suggestions("test", dict);
		// ...
		assertTrue(sugg.wordExists("testa"));
	}

	@Test
	public void test4() {
		// REVERSAL
		
		WordsSet dict = new LLWordsSet(); // or new HTWordsSet();
		// ...
		try {
			dict.insWord("tset");
		} catch (SpellCheckException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Spelling speller = new SpellingImpl();
		WordsSet sugg = speller.suggestions("test", dict);
		// ...
		assertTrue(sugg.wordExists("tset"));
	}
	
	@Test
	public void test5() {
		// INCORRECT SUGGESTION
		
		WordsSet dict = new LLWordsSet(); // or new HTWordsSet();
		// ...
		try {
			dict.insWord("test");
		} catch (SpellCheckException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Spelling speller = new SpellingImpl();
		WordsSet sugg = speller.suggestions("test", dict);
		// ...
		assertFalse(sugg.wordExists("fail"));
	}
	// ...

}