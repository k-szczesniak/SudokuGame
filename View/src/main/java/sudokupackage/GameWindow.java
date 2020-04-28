package sudokupackage;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.w3c.dom.ls.LSOutput;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameWindow {

    @FXML
    Canvas canvas;

    private SudokuBoard board;
    private Set<Integer> setOfPositions = new HashSet<Integer>();
    Levels choice = StartMenu.getChoice();

    Random rand = new Random();

    public void initialize() throws CloneNotSupportedException {
        board = new SudokuBoard(new BacktrackingSudokuSolver());
        GraphicsContext context = canvas.getGraphicsContext2D();
        drawOnCanvas(context);
    }

    private SudokuBoard calculateHiddenPostions(SudokuBoard board, int counter) {
        while(counter > 0) {
            if(setOfPositions.add(rand.nextInt(81)))
                counter--;
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(setOfPositions.contains(9 * i +j)) {
                    board.set(i,j,0);
                }
            }
        }
        return board;
    }

    private SudokuBoard specifyLevel() throws CloneNotSupportedException {
        SudokuBoard cloneBoard = (SudokuBoard) board.clone();
        switch (choice) {
            case Easy:
                return calculateHiddenPostions(cloneBoard,27);
            case Medium:
                return calculateHiddenPostions(cloneBoard, 45);
            case Hard:
                return calculateHiddenPostions(cloneBoard, 63);
        }
        return cloneBoard;
    }

    public void drawOnCanvas(GraphicsContext context) throws CloneNotSupportedException {
        context.clearRect(0, 0, 450, 450);
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                int positionY = row * 50 + 2;

                int positionX = col * 50 + 2;

                int width = 46;

                context.setFill(Color.PINK);

                context.fillRoundRect(positionX, positionY, width, width, 10, 10);

            }
        }

        board.solveGame();
        SudokuBoard boardToDisplay = specifyLevel();
//        specifyLevel();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                int positionY = row * 50 + 30;
                int positionX = col * 50 + 20;

                context.setFill(Color.BLACK);

                context.setFont(new Font(20));

                if (boardToDisplay.get(row, col) != 0) {
                    context.fillText(boardToDisplay.get(row, col) + "", positionX, positionY);
                }
            }

        }
    }
}
