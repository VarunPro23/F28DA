package F28DA_CW1;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * This class is for reading words form input file.
 * <ul>
 * <li>First open input file for reading:
 * {@code in = new BufferedInputStream(new FileInputStream(String fileName));}
 * <li>Next create an object of class {@code FileWordRead}:
 * {@code FileWordRead readWords = new FileWordRead(in);}
 * <li>To read a word, first check that there is a next word in file:
 * {@code if (readWords.hasNextWord())}
 * <li>Finally, to read a word, use:
 * {@code String nextWord = readWords.nextWord();}
 * </ul>
 */
public class FileWordRead {
	private BufferedInputStream in;
	private String nextWord;
	private boolean endOfFile;

	public FileWordRead(BufferedInputStream inFile) throws IOException {
		in = inFile;
		endOfFile = false;
		nextWord = readWord();
	}

	private String readWord() throws IOException {
		int ch;
		char nextChar;
		StringBuffer buf = new StringBuffer();

		ch = in.read();
		if (ch == -1) {
			endOfFile = true;
			return (null);
		}

		nextChar = Character.toLowerCase((char) ch);
		while (!(nextChar >= 'a' && nextChar <= 'z')) {
			ch = in.read();
			if (ch == -1) {
				endOfFile = true;
				return (null);
			}
			nextChar = Character.toLowerCase((char) ch);
		}

		while (nextChar >= 'a' && nextChar <= 'z') {
			buf.append(nextChar);
			ch = in.read();
			if (ch == -1) {
				endOfFile = true;
				return (buf.toString());
			}

			nextChar = Character.toLowerCase((char) ch);
		}
		return (buf.toString());
	}

	public boolean hasNextWord() {
		if (nextWord != null)
			return (true);
		else
			return (false);
	}

	public String nextWord() throws java.io.IOException {
		String toReturn = nextWord;
		if (!endOfFile)
			nextWord = readWord();
		else
			nextWord = null;
		return (toReturn);
	}

}
