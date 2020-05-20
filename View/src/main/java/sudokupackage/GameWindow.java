package sudokupackage;

import java.util.ResourceBundle;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudokupackage.exceptions.DaoException;
import sudokupackage.exceptions.OpenSaveException;


public class GameWindow {

    @FXML
    private GridPane sudokuGrid;

    private ResourceBundle bundle = ResourceBundle.getBundle("Language");

    private Levels choice;
    private JavaBeanIntegerProperty[][] integerProperty = new JavaBeanIntegerProperty[9][9];
    private TextField[][] field = new TextField[9][9];
    private SudokuBoardView board;
    private static final Logger logger = LoggerFactory.getLogger(GameWindow.class);

    public void initialize() {
        choice = StartMenu.getChoice();
        startSudoku();
    }

    private void startSudoku()  {
        board = new SudokuBoardView(new BacktrackingSudokuSolver());
        board.solveGame();
        board.calculateHiddenPostions(choice.getNumberOfcells());
        fillSudokuGrid();
    }

    private void fillSudoku() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                field[i][j].setDisable(false);
                field[i][j].setText("");
                integerProperty[i][j].setValue(board.get(i, j));
                if (!board.getIsEditable(i, j)) {
                    field[i][j].setDisable(true);
                }
            }
        }
    }

    private void fillSudokuGrid() {
        StringConverter<Number> converter = new StringConverter<Number>() {

            @Override
            public String toString(Number object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public Number fromString(String string) {
                if (string.length() != 1) {
                    return 0;
                } else {
                    try {
                        return Integer.parseInt(string);
                    } catch (NumberFormatException ex) {
                        return 0;
                    }
                }
            }

        };
        JavaBeanIntegerPropertyBuilder builder =
                JavaBeanIntegerPropertyBuilder.create();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                field[i][j] = new TextField();
                try {
                    integerProperty[i][j] = builder.bean(board
                            .getSudokuField(i, j)).name("value")
                            .getter("getValue")
                            .setter("setValue")
                            .build();
                    field[i][j].textProperty()
                            .bindBidirectional(integerProperty[i][j], converter);

                } catch (NoSuchMethodException e) {
                    logger.error("Problems with integerProperty build.");
                    logger.debug("Problems with integerProperty build.", e);
                }
                if (board.get(i, j) != 0) {
                    field[i][j].setDisable(true);
                    board.setIsEditable(i, j, false);
                    field[i][j].setText(String.valueOf(board.get(i, j)));
                } else {
                    field[i][j].setText("");
                }
                int finalI = i;
                int finalJ = j;
                field[i][j].textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String>
                                                observableValue, String s, String t1) {
                        if (!(t1.matches("[1-9]") || t1.matches(""))) {
                            field[finalI][finalJ].setText(s);
                        }
                    }
                });
                sudokuGrid.add(field[i][j], j, i);
            }
        }
    }

    @FXML
    private void handleButtonOpenAction(ActionEvent actionEvent) {
        String fileName;
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle(bundle.getString("openDialogWindow"));
            try {
                fileName = fileChooser.showOpenDialog(null).toString();
            } catch (RuntimeException e) {
                throw new OpenSaveException(e);
            }
            if (fileName != null) {
                Dao<SudokuBoard> fileSudokuBoardDao = SudokuBoardDaoFactory.getFileDao(fileName);
                board = (SudokuBoardView) fileSudokuBoardDao.read();
            }
            fillSudoku();
        } catch (OpenSaveException e) {
            logger.warn("File not selected");
            logger.debug("File not selected", e);
        } catch (DaoException e) {
            logger.error("Cannot open selected file");
            logger.debug("Cannot open selected file", e);
        }
    }

    @FXML
    private void handleButtonOpendbAction(ActionEvent actionEvent) {
        String fileName;
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle(bundle.getString("openDialogWindow"));
            try {
                fileName = fileChooser.showSaveDialog(null).toString();
            } catch (RuntimeException e) {
                throw new OpenSaveException(e);
            }
            if (fileName != null) {
                Dao<SudokuBoard> databaseSudokuBoardDao =
                        SudokuBoardDaoFactory.getDatabaseDao(fileName);
                board = (SudokuBoardView) databaseSudokuBoardDao.read();
            }
            fillSudoku();
        } catch (OpenSaveException e) {
            logger.warn("File not selected");
            logger.debug("File not selected", e);
        } catch (DaoException e) {
            logger.error("Cannot open selected from database file");
            logger.debug("Cannot open selected from database file", e);
        }
    }

    @FXML
    private void handleButtonSaveAction(ActionEvent actionEvent) {
        String fileName;
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle(bundle.getString("saveDialogWindow"));
            try {
                fileName = fileChooser.showSaveDialog(null).toString();
            } catch (RuntimeException e) {
                throw new OpenSaveException(e);
            }
            if (fileName != null) {
                Dao<SudokuBoard> fileSudokuBoardDao = SudokuBoardDaoFactory.getFileDao(fileName);
                fileSudokuBoardDao.write(board);
            }

        } catch (OpenSaveException e) {
            logger.warn("File not selected");
            logger.debug("File not selected", e);
        } catch (DaoException e) {
            logger.error("Cannot save file");
            logger.debug("Cannot save file", e);
        }
    }

    @FXML
    private void handleButtonSavedbAction(ActionEvent actionEvent) {

        String fileName;
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle(bundle.getString("saveDialogWindow"));
            try {
                fileName = fileChooser.showSaveDialog(null).toString();
            } catch (RuntimeException e) {
                throw new OpenSaveException(e);
            }
            if (fileName != null) {
                Dao<SudokuBoard> fileSudokuBoardDao =
                        SudokuBoardDaoFactory.getDatabaseDao(fileName);
                fileSudokuBoardDao.write(board);
            }
        } catch (OpenSaveException e) {
            logger.warn("File not selected");
            logger.debug("File not selected", e);
        } catch (DaoException e) {
            logger.error("Cannot save file to database");
            logger.debug("Cannot save file to database", e);
        }
    }

    @FXML
    private void handleButtonShowAuthorsAction(ActionEvent actionEvent) {
        ResourceBundle authors = ResourceBundle.getBundle("sudokupackage.Authors");
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                authors.getObject("1").toString() + ", "
                        + authors.getObject("2"), ButtonType.OK);
        alert.setHeaderText(null);
        alert.show();
    }

    @FXML
    private void handleButtonCheckAction(ActionEvent actionEvent) {
        if (board.checkBoard()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    bundle.getString("CheckResultInformationWin"), ButtonType.OK);
            alert.setTitle(null);
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    bundle.getString("CheckResultInformationLose"), ButtonType.OK);
            alert.setTitle(null);
            alert.show();
        }
    }

    @FXML
    private void handleButtonRandAction(ActionEvent actionEvent) {
        sudokuGrid.getChildren().clear();
        startSudoku();
    }

}