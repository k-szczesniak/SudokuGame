package sudokupackage;

public class SudokuField {
    private int value;

    public SudokuField(int value){
        this.value=value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value > 0 && value < 10) {
            this.value = value = value;
        } else {
            this.value = value = 0;
        }
    }
}
