package sudokupackage;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    }

}