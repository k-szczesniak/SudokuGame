package sudokupackage;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.builder.ToStringStyle;


public abstract class SudokuParts implements Serializable, Cloneable {
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
        return new org.apache.commons.lang3.builder
                .ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append("values", values)
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

        SudokuParts that = (SudokuParts) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(values, that.values)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(values)
                .toHashCode();
    }


    public int getValues(int x) {
        return values.get(x).getValue();
    }
}
