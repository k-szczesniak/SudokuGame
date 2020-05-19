package sudokupackage;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sudokupackage.exceptions.DaoException;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

    private String fileName;

    public JdbcSudokuBoardDao() {
    }

    public JdbcSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() throws DaoException {

        var url = "jdbc:h2:~/sudokudb";
        var query = "select * from test";

        try (Connection con = DriverManager.getConnection(url);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {

                System.out.printf(String.valueOf(rs.getString(1)));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Connection connect() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/sudokudb");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void write(SudokuBoard obj) throws DaoException {

        var url = "jdbc:h2:~/sudokudb";
        var query = "select * from test";

        String createTable = "create table " + "SudokuBoard5"
                + "(tableName varchar(100) primary key not null, "
                + "board blob)";


        String insertData = "INSERT INTO SudokuBoard5(tableName, "
                + "board) VALUES (?, ?)";

        Connection con = connect();
        try (Statement st = con.createStatement()) {
            st.execute(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement preparedStatement = con.prepareStatement(insertData)) {
            preparedStatement.setString(1, fileName);
            preparedStatement.setObject(2, obj);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
    }
}
