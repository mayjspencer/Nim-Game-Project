import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class IllegalMoveExceptionTest {

	@Test
	void testConstructor() {
		String moveInfo = "That move is not allowed!";
		Exception exception = new IllegalMoveException(moveInfo);
		assertEquals(moveInfo, exception.getMessage());

		moveInfo = "Pile 1 is empty.";
		exception = new IllegalMoveException(moveInfo);
		assertEquals(moveInfo, exception.getMessage());

		moveInfo = "Object number greater than pile size: 3 > 2";
		exception = new IllegalMoveException(moveInfo);
		assertEquals(moveInfo, exception.getMessage());
	}
}
