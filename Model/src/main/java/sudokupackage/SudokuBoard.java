package sudokupackage;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SudokuBoard implements Serializable {

    private final List<List<SudokuField>> board;
    private SudokuSolver sudokuSolver;
    private List<SudokuRow> rows;
    private List<SudokuColumn> columns;
    private List<SudokuBox> boxes;

    {
        board = Arrays.asList(new List[9]);
        for (int i = 0; i < 9; i++) {
            board.set(i, Arrays.asList(new SudokuField[9]));
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.get(i).set(j, new SudokuField(0));
            }
        }
        initRows();
        initColumns();
        initBoxes();
    }

    public SudokuBoard(SudokuSolver sudokusolver) {
        this.sudokuSolver = sudokusolver;
    }

    public int get(int x, int y) {
        return board.get(x).get(y).getValue();
    }

    public void set(int x, int y, int value) {
        board.get(x).get(y).setValue(value);
        if (!this.checkBoard(x, y)) {
            board.get(x).get(y).setValue(0);
        }
    }

    public SudokuRow getRow(int row) {
        return rows.get(row);
    }

    public SudokuColumn getColumn(int column) {
        return columns.get(column);
    }

    public SudokuBox getBox(int row, int col) {
        return boxes.get((col / 3) + 3 * (row / 3));
    }

    private boolean checkBoard(int row, int col) {
        return this.getRow(row).verify()
                && this.getColumn(col).verify()
                && this.getBox(row, col).verify();
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder
                .ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append("board", board)
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

        SudokuBoard that = (SudokuBoard) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(rows, that.rows)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(rows)
                .toHashCode();
    }


    private void initRows() {
        SudokuRow[] sudokuRows = new SudokuRow[9];
        for (int i = 0; i < 9; i++) {
            sudokuRows[i] = new SudokuRow(board.get(i));
        }
        rows = new ArrayList<>(Arrays.asList(sudokuRows));
    }

    private void initColumns() {
        SudokuColumn[] sudokuColumns = new SudokuColumn[9];
        for (int i = 0; i < 9; i++) {
            List<SudokuField> col = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                col.add(j, board.get(j).get(i));
            }
            sudokuColumns[i] = new SudokuColumn(col);
        }
        columns = new ArrayList<>(Arrays.asList(sudokuColumns));
    }

    private void initBoxes() {
        int k = 0;
        SudokuBox[] sudokuBoxes = new SudokuBox[9];
        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                List<SudokuField> boxList = new ArrayList<>();
                int iterator = 0;
                for (int r = row; r < row + 3; r++) {
                    for (int c = col; c < col + 3; c++) {
                        boxList.add(iterator, board.get(r).get(c));
                        iterator++;
                    }
                }
                sudokuBoxes[k] = new SudokuBox(boxList);
                k++;
            }
        }
        boxes = new ArrayList<>(Arrays.asList(sudokuBoxes));
    }
}
