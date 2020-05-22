package sudokupackage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudokupackage.exceptions.DatabaseDaoException;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

    private Connection connection = null;

    {
            try {
                Class.forName("org.h2.Driver");
                connection = DriverManager.getConnection("jdbc:h2:~/sudokudb");
            } catch (ClassNotFoundException e) {
                logger.error("Driver not found.");
                logger.debug("Driver not found", e);
            } catch (SQLException e) {
                logger.error("Cannot establish connection");
                logger.debug("Cannot establish connection", e);
            }
    }

    private static final Logger logger = LoggerFactory.getLogger(FileSudokuBoardDao.class);

    private String fileName;

    public JdbcSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() throws DatabaseDaoException {

        String selectData = "select tableName, board from "
                + "SudokuBoard" + " where tableName=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectData)) {
            preparedStatement.setString(1, fileName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            byte[] buf = resultSet.getBytes(2);
            ObjectInputStream objectIn = null;
            if (buf != null) {
                objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
            }
            Object deserializedBoard = objectIn.readObject();

            return (SudokuBoard) deserializedBoard;
        } catch (SQLException e) {
            logger.error("Failed to execute query in read method.");
            logger.debug("Failed to execute query in read method.", e);
            throw new DatabaseDaoException("queryException", e);
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Failed to read an object from database.");
            logger.debug("Failed to read an object from database.", e);
            throw new DatabaseDaoException(e);
        }

    }

    @Override
    public void write(SudokuBoard obj) throws DatabaseDaoException {

        String createTable = "create table if not exists " + "SudokuBoard"
                + "(tableName varchar(100) primary key not null, "
                + "board blob)";


        String insertData = "INSERT INTO SudokuBoard(tableName, "
                + "board) VALUES (?, ?)";

        try {
            connection.setAutoCommit(false);
        } catch (SQLException throwables) {
            throw new DatabaseDaoException(throwables);
        }

        try (Statement st = connection.createStatement();
             PreparedStatement preparedStatement = connection.prepareStatement(insertData)) {
            st.execute(createTable);
            preparedStatement.setString(1, fileName);
            preparedStatement.setObject(2, obj);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.error("Failed to execute query in write method.");
            logger.debug("Failed to execute query in write method.", e);
            throw new DatabaseDaoException("queryException", e);
        }

    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
