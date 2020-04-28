package sudokupackage;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameWindow {

    @FXML
    Canvas canvas;

    private SudokuBoard board;

    public void initialize() {
        board = new SudokuBoard(new BacktrackingSudokuSolver());
        GraphicsContext context = canvas.getGraphicsContext2D();
        drawOnCanvas(context);
    }

    public void drawOnCanvas(GraphicsContext context) {
        context.clearRect(0, 0, 450, 450);
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // finds the y position of the cell, by multiplying the row number by 50, which is the height of a row 					// in pixels
                // then adds 2, to add some offset
                int position_y = row * 50 + 2;

                // finds the x position of the cell, by multiplying the column number by 50, which is the width of a 					// column in pixels
                // then add 2, to add some offset
                int position_x = col * 50 + 2;

                // defines the width of the square as 46 instead of 50, to account for the 4px total of blank space 					// caused by the offset
                // as we are drawing squares, the height is going to be the same
                int width = 46;

                // set the fill color to white (you could set it to whatever you want)
                context.setFill(Color.PINK);

                // draw a rounded rectangle with the calculated position and width. The last two arguments specify the 					// rounded corner arcs width and height.
                // Play around with those if you want.
                context.fillRoundRect(position_x, position_y, width, width, 10, 10);


                // draw the initial numbers from our GameBoard instance
//                int[][] initial = gameboard.getInitial();

            }
        }
        board.solveGame();
        // for loop is the same as before
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                // finds the y position of the cell, by multiplying the row number by 50, which
                // is the height of a row in pixels then adds 2, to add some offset
                int position_y = row * 50 + 30;

                // finds the x position of the cell, by multiplying the column number by 50,
                // which is the width of a column in pixels then add 2, to add some offset
                int position_x = col * 50 + 20;

                // set the fill color to white (you could set it to whatever you want)
                context.setFill(Color.BLACK);

                // set the font, from a new font, constructed from the system one, with size 20
                context.setFont(new Font(20));

                // check if value of coressponding initial array position is not 0, remember that
                // we treat zeroes as squares with no values.
                if (board.get(row, col) != 0) {

                    // draw the number using the fillText method
                    context.fillText(board.get(row, col) + "", position_x, position_y);
                }
            }

        }
    }
}
