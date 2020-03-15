package SudokuPackage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @Test
    public void TestBoardRepeats(){
        SudokuBoard s1= new SudokuBoard();
        SudokuBoard s2= new SudokuBoard();

        s1.fillBoard();
        s2.fillBoard();
        boolean equal=true;
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(s1.getBoard()[i][j]!=s2.getBoard()[i][j]){
                    equal=false;
                }
            }
        }
        assertEquals(equal, false);
    }



}