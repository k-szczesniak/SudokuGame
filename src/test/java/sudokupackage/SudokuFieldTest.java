package sudokupackage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SudokuFieldTest {

    @Test
    public void testEquals() {
        SudokuField field1 = new SudokuField(9);
        SudokuField field2 = new SudokuField(9);
        assertTrue(field1.equals(field2));
        SudokuField field3 = new SudokuField(7);
        assertFalse(field1.equals(field3));
    }

}