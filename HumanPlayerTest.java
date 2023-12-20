import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class HumanPlayerTest {

	@Test
	void testGetUserInput() throws IOException {
		// Redirect standard out to check the messages printed by getUserInput.
		PrintStream console = System.out;
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		System.setOut(new PrintStream(stdout));

		// Simulate keyboard input by constructing a HumanPlayer with a Scanner
		// that reads a String.
		Scanner input = new Scanner("1");
		HumanPlayer player = new HumanPlayer("name", input);

		// Check that getUserInput prompts the user once and returns the input.
		assertEquals(1, player.getUserInput("Enter an integer: "));
		stdout.flush();
		assertEquals("Enter an integer: ", stdout.toString());
		stdout.reset();

		// This time, simulate an invalid input followed by a valid input.
		input = new Scanner("not an int" + System.lineSeparator() + "451");
		player = new HumanPlayer("name", input);

		// Check that getUserInput prompts twice and returns the valid input.
		assertEquals(451, player.getUserInput("prompt"));
		stdout.flush();
		assertEquals("promptprompt", stdout.toString());
		stdout.reset();

		// One more test...
		input = new Scanner(
				"  123abc " + System.lineSeparator() +
				"   cba321" + System.lineSeparator() +
				" -38  ");
		player = new HumanPlayer("name", input);
		assertEquals(-38, player.getUserInput("Hi! "));
		stdout.flush();
		assertEquals("Hi! Hi! Hi! ", stdout.toString());
		stdout.reset();

		// Revert standard out to the console.
		System.setOut(console);
	}
}
