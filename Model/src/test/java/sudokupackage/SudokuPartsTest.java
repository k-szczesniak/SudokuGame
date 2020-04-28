package sudokupackage;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SudokuPartsTest {

    @Test
    public void testEqual() {
        List<SudokuField> listFields = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            listFields.add(new SudokuField(i));
        }
        SudokuRow row1 = new SudokuRow(listFields);
        SudokuRow row2 = new SudokuRow(listFields);
        assertTrue(row1.equals(row2));

        List<SudokuField> listFields2 = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            listFields2.add(new SudokuField(0));
        }
        SudokuRow row3 = new SudokuRow(listFields2);
        assertFalse(row1.equals(row3));

        SudokuRow row4 = row1;
        assertEquals(row1, row4);
        SudokuRow row5 = null;
        assertFalse(row1.equals(row5));
        SudokuColumn sudokuColumn = new SudokuColumn(listFields);
        assertFalse(row1.equals(sudokuColumn));


    }

    @Test
    public void testHashCode() {
        List<SudokuField> listFields = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            listFields.add(new SudokuField(i));
        }
        SudokuBox sudokuBox1 = new SudokuBox(listFields);
        SudokuBox sudokuBox2 = sudokuBox1;
        assertEquals(sudokuBox1.hashCode(), sudokuBox2.hashCode());
    }

    @Test
    public void testToString() {
        List<SudokuField> listFields = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            listFields.add(new SudokuField(i));
        }
        SudokuRow row1 = new SudokuRow(listFields);
//        String result = "0 1 2 3 4 5 6 7 8 ";
        List<Integer> result = Arrays.asList(0,1,2,3,4,5,6,7,8);
        assertEquals(row1.toString(), result.toString());
    }

    @Test
    public void testCloneSudokuRow() throws CloneNotSupportedException {
        List<SudokuField> listFields = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            listFields.add(new SudokuField(i));
        }
        SudokuRow sudokuRow1 = new SudokuRow(listFields);
        SudokuRow sudokuRow2 = (SudokuRow) sudokuRow1.clone();
        assertTrue(sudokuRow1.equals(sudokuRow2));
    }

    @Test
    public void testCloneSudokuColumn() throws CloneNotSupportedException {
        List<SudokuField> listFields = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            listFields.add(new SudokuField(i));
        }
        SudokuColumn sudokuColumn1 = new SudokuColumn(listFields);
        SudokuColumn sudokuColumn2 = (SudokuColumn) sudokuColumn1.clone();
        assertTrue(sudokuColumn1.equals(sudokuColumn2));
    }

    @Test
    public void testCloneSudokuBox() throws CloneNotSupportedException {
        List<SudokuField> listFields = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            listFields.add(new SudokuField(i));
        }
        SudokuBox sudokuBox1 = new SudokuBox(listFields);
        SudokuBox sudokuBox2 = (SudokuBox) sudokuBox1.clone();
        assertTrue(sudokuBox1.equals(sudokuBox2));
    }
}