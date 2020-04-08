package sudokupackage;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class SudokuParts {
    private final List<SudokuField> values;

    public SudokuParts(final List<SudokuField> values) {
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

    @Override
    public String toString() {
        StringBuilder valuesString = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            valuesString.append(values.get(i).toString()).append(" ");
        }
        return valuesString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuParts that = (SudokuParts) o;
        for (int i = 0; i < 9; i++) {
            if (!values.get(i).equals(that.getField(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

    private SudokuField getField(int index) {
        return values.get(index);
    }
}
