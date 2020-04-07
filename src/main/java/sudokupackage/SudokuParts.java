package sudokupackage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class SudokuParts {
    private List<SudokuField> values;

    public SudokuParts(List<SudokuField> values) {
        this.values = values;
    }

    public boolean verify() {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < 9; i++) {
            if (values.get(i).getValue() != 0) {
                if (!set.add(values.get(i).getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

}
