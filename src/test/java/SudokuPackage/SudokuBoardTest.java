package SudokuPackage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @Test
    public void testBoard(){

        SudokuBoard a=new SudokuBoard();
        a.fillBoard();
        a.printBoard();

    }



}