package sudokupackage;

import java.util.Objects;

public class SudokuField {
    private int value;

    public SudokuField(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value >= 0 && value < 10) {
            this.value = value;
        }
    }

    @Override
    public String toString() {
        StringBuilder fieldString = new StringBuilder();
        fieldString.append(this.value).toString();
        return fieldString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuField that = (SudokuField) o;
        return this.getValue() == that.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

