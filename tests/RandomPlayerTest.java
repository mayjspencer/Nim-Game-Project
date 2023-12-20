import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RandomPlayerTest {

	@Test
	void testInheritance() {
		RandomPlayer randomPlayer = new RandomPlayer("J.A.R.V.I.S.");
		assertTrue(randomPlayer instanceof Player);
	}

	@Test
	void testGetMoveIndex() {
		Player player = new RandomPlayer("Multivac");

		int[] pileSizes = {1, 1, 1};
		boolean[] idxGenerated = {false, false, false};
		for (int i = 0; i < 20; ++i) {
			int[] move = player.getMove(pileSizes);
			int index = move[0];
			assertTrue(index >= 0);
			assertTrue(index <= 2);
			idxGenerated[index] = true;
		}

		pileSizes = new int[] {0, 2, 0, 0, 2, 2};
		idxGenerated = new boolean[] {false, false, false, false, false, false};
		for (int i = 0; i < 20; ++i) {
			int[] move = player.getMove(pileSizes);
			int index = move[0];
			assertTrue(index >= 0);
			assertTrue(index <= 5);
			idxGenerated[index] = true;
		}
		assertArrayEquals(new boolean[] {false, true, false, false, true, true},
				idxGenerated);

		pileSizes = new int[] {3, 0, 3, 3, 0, 0};
		idxGenerated = new boolean[] {false, false, false, false, false, false};
		for (int i = 0; i < 20; ++i) {
			int[] move = player.getMove(pileSizes);
			int index = move[0];
			assertTrue(index >= 0);
			assertTrue(index <= 5);
			idxGenerated[index] = true;
		}
		assertArrayEquals(new boolean[] {true, false, true, true, false, false},
				idxGenerated);
	}

	@Test
	void testGetMoveNumber() {
		Player player = new RandomPlayer("Wintermute");

		int[] pileSizes = {3};
		boolean[] numGenerated = {false, false, false};
		for (int i = 0; i < 20; ++i) {
			int[] move = player.getMove(pileSizes);
			int number = move[1];
			assertTrue(number >= 1);
			assertTrue(number <= 3);
			numGenerated[number-1] = true;
		}
		assertArrayEquals(new boolean[] {true, true, true}, numGenerated);

		pileSizes = new int[] {6};
		numGenerated = new boolean[] {false, false, false, false, false, false};
		for (int i = 0; i < 40; ++i) {
			int[] move = player.getMove(pileSizes);
			int number = move[1];
			assertTrue(number >= 1);
			assertTrue(number <= 6);
			numGenerated[number-1] = true;
		}
		assertArrayEquals(new boolean[] {true, true, true, true, true, true},
				numGenerated);
	}
}
