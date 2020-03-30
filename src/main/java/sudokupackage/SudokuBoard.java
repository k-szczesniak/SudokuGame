package sudokupackage;

public class SudokuBoard {

    private SudokuField[][] board = new SudokuField[9][9];
    private SudokuSolver sudokuSolver;

    public SudokuBoard(SudokuSolver sudokusolver) {
        this.sudokuSolver = sudokusolver;
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                board[i][j]=new SudokuField(0);
            }
        }
    }

    public int get(int x, int y) {
        return board[x][y].getValue();
    }

    public void set(int x, int y, int value) {
        board[x][y].setValue(value);
    }

//    public SudokuRow getRow(int row){
//
//    }













    private boolean checkRow(int row, int col, int value) {
        for (int i = 0; i < 9; i++) {
            if (value == board[row][i].getValue()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int row, int col, int value) {
        for (int i = 0; i < 9; i++) {
            if (value == board[i][col].getValue()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkSquare(int row, int col, int value) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int realRow = (row / 3) * 3 + i;
                int realCol = (col / 3) * 3 + j;
                if (board[realRow][realCol].getValue() == value
                        && (realRow * 9 + realCol) < row * 9 + col) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkBoard(int row, int col, int value) {
        return this.checkRow(row, col, value)
                && this.checkColumn(row, col, value)
                && this.checkSquare(row, col, value);
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
                if (this.board[i][j] != that.board[i][j]) {
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
