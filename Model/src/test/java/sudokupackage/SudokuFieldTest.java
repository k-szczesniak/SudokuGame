package sudokupackage;

import org.junit.jupiter.api.Test;
import sudokupackage.exceptions.BadFieldValueException;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldTest {

    @Test
    public void testEquals() {
        SudokuField field1 = new SudokuField(9);
        SudokuField field2 = new SudokuField(9);
        assertTrue(field1.equals(field2));
        SudokuField field3 = new SudokuField(7);
        assertFalse(field1.equals(field3));
        SudokuField field4 = null;
        assertFalse(field1.equals(field4));
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        assertFalse(field1.equals(sudokuBoard));
        SudokuField field5 = field1;
        assertTrue(field5.equals(field1));


    }

    @Test
    public void testHashCode() {
        SudokuField sudokuField1 = new SudokuField(1);
        SudokuField sudokuField2 = new SudokuField(1);
        assertEquals(sudokuField1.hashCode(), sudokuField2.hashCode());
    }

    @Test
    public void testToString() {
        SudokuField sudokuField = new SudokuField(1);
        assertEquals(sudokuField.toString(), "1");
    }

    @Test
    public void testSetter() {
        SudokuField sudokuField1 = new SudokuField(1);
        sudokuField1.setValue(2);
        assertEquals(sudokuField1.getValue(), 2);
        assertThrows(BadFieldValueException.class, () -> {
            sudokuField1.setValue(-1);
            assertEquals(sudokuField1.getValue(), 2);
        });
        assertThrows(BadFieldValueException.class, () -> {
            sudokuField1.setValue(11);
            assertEquals(sudokuField1.getValue(), 2);
        });
    }

    @Test
    public void testCompareTo() {
        SudokuField sudokuField1 = new SudokuField(5);
        SudokuField sudokuField2 = new SudokuField(3);
        SudokuField sudokuField3 = new SudokuField(7);
        SudokuField sudokuField4 = new SudokuField(5);

        assertTrue(sudokuField1.compareTo(sudokuField2) > 0);
        assertTrue(sudokuField1.compareTo(sudokuField3) < 0);
        assertTrue(sudokuField1.compareTo(sudokuField4) == 0);
        assertThrows(NullPointerException.class, () ->{
            sudokuField1.compareTo(null);
        });
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        SudokuField sudokuField1 = new SudokuField(5);
        SudokuField sudokuField2 = sudokuField1.clone();
        assertEquals(sudokuField1.getValue(), sudokuField2.getValue());
        assertNotSame(sudokuField1,sudokuField2);
    }

}