package sudokupackage;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {
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
    public int compareTo(SudokuField o) {
        if (this.equals(o)) {
            return 0;
        }
        return this.getValue() - o.getValue();
    }

    @Override
    protected SudokuField clone() throws CloneNotSupportedException {
        SudokuField cloneField = new SudokuField(this.getValue());
        return cloneField;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder
                .ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append("value", value)
                .toString();
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

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(value, that.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(value)
                .toHashCode();
    }

}

