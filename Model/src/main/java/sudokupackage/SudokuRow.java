package sudokupackage;

import java.util.ArrayList;
import java.util.List;

public class SudokuRow extends SudokuParts {

    public SudokuRow(final List<SudokuField> values) {
        super(values);
    }

    @Override
    protected SudokuRow clone() throws CloneNotSupportedException {
        List<SudokuField> fieldList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            fieldList.add(new SudokuField(this.getValues(i)));
        }
        return new SudokuRow(fieldList);
    }

}
