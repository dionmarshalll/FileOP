import java.sql.*;

public class MyJDBC {
    private Connection conn;
    private Statement statement;

    public MyJDBC() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/akademic", "root", "password");
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        return statement.executeQuery(query);
    }

    public int executeUpdate(String query) throws SQLException {
        return statement.executeUpdate(query);
    }

    public void close() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
