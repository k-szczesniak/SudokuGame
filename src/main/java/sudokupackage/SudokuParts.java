package sudokupackage;

import java.util.HashSet;
import java.util.Set;

public abstract class SudokuParts {
    protected SudokuField[] values = new SudokuField[9];

    public SudokuParts(SudokuField[] values) {
        this.values = values;
    }

    public boolean verify() {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < 9; i++) {
            if (!set.add(values[i].getValue())) {
                return false;
            }
        }
        return true;
    }
}
