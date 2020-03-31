package sudokupackage;

public class SudokuBoard {

    private SudokuField[][] board = new SudokuField[9][9];
    private SudokuSolver sudokuSolver;


    public SudokuBoard(SudokuSolver sudokusolver) {
        this.sudokuSolver = sudokusolver;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuField(0);
            }
        }
    }

    public int get(int x, int y) {
        return board[x][y].getValue();
    }

    public void set(int x, int y, int value) {
        board[x][y].setValue(value);
        if (!this.checkBoard(x, y)) {
            board[x][y].setValue(0);
        }
    }

    public SudokuRow getRow(int row) {
        SudokuField[] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField(0);
        }

        for (int i = 0; i < 9; i++) {
            fields[i].setValue(board[row][i].getValue());
        }
        SudokuRow singleRow = new SudokuRow(fields);
        return singleRow;
    }

    public SudokuColumn getColumn(int column) {
        SudokuField[] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField(0);
        }

        for (int i = 0; i < 9; i++) {
            fields[i].setValue(board[i][column].getValue());
        }
        SudokuColumn singleColumn = new SudokuColumn(fields);
        return singleColumn;
    }

    public SudokuBox getBox(int row, int col) {
        SudokuField[] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            fields[i] = new SudokuField(0);
        }

        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int realRow = (row / 3) * 3 + i;
                int realCol = (col / 3) * 3 + j;
                fields[k++].setValue(board[realRow][realCol].getValue());
            }
        }
        SudokuBox singleBox = new SudokuBox(fields);
        return singleBox;
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
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuBoard that = (SudokuBoard) o;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.board[i][j].getValue() != that.board[i][j].getValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
