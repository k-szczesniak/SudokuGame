package sudokupackage;

import java.io.Serializable;
import java.util.ResourceBundle;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudokupackage.exceptions.BadFieldValueException;
import sudokupackage.exceptions.CompareException;


public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {

    private static final Logger logger = LoggerFactory.getLogger(SudokuField.class);

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
        } else {
            throw new BadFieldValueException("badFieldExceptionMsg");
        }
    }

    @Override
    public int compareTo(SudokuField o) {
        if (o == null) {
            logger.error("Compared object is null.");
            throw new CompareException("compareExceptionMsg");
        }
        if (this.equals(o)) {
            return 0;
        }
        return this.getValue() - o.getValue();
    }

    @Override
    protected SudokuField clone() {
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
            logger.info("Null or other class object.");
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

