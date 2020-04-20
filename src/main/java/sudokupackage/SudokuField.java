package sudokupackage;

import org.apache.commons.lang3.builder.ToStringStyle;

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

