package sudokupackage;


import java.util.*;

public class SudokuBoard {

    private List<List<SudokuField>> board;
    private SudokuSolver sudokuSolver;
    private List<SudokuRow> rows;
    private List<SudokuColumn> columns;
    private List<SudokuBox> boxes;

    public SudokuBoard(SudokuSolver sudokusolver) {
        this.sudokuSolver = sudokusolver;

        board = Arrays.asList(new List[9]);
        for(int i=0; i<9; i++){
            board.set(i, Arrays.asList(new SudokuField[9]));
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.get(i).set(j, new SudokuField(0));
            }
        }

        SudokuRow[] sudokuRows = new SudokuRow[9];
        for(int i=0; i<9; i++){
            sudokuRows[i] = new SudokuRow(board.get(i));
        }
        rows = new ArrayList<>(Arrays.asList(sudokuRows));

        SudokuColumn[] sudokuColumns = new SudokuColumn[9];
        for(int i=0; i<9; i++){
            List<SudokuField> col = new ArrayList<>();
            for(int j=0; j<9; j++){
                col.add(j, board.get(j).get(i));
            }
            sudokuColumns[i] = new SudokuColumn(col);
        }
        columns = new ArrayList<SudokuColumn>(Arrays.asList(sudokuColumns));

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
        return boxes.get((col/3)+3*(row/3));
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
                if (this.board.get(i).get(j).getValue() != that.board.get(i).get(j).getValue()) {
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

    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board.get(i).get(j).getValue() + " ");
            }
            System.out.println();
        }
    }

}
