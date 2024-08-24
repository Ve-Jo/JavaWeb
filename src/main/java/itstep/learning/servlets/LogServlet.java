package itstep.learning.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/log")
public class LogServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            createTableIfNotExists();
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Failed to initialize LogServlet", e);
        }
    }

    private void createTableIfNotExists() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS log_entries (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "entry_time DATETIME NOT NULL" +
                ")";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> logEntries = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();

            String insertSql = "INSERT INTO log_entries (entry_time) VALUES (NOW())";
            try (PreparedStatement pstmt = connection.prepareStatement(insertSql)) {
                pstmt.executeUpdate();
            }

            String selectSql = "SELECT entry_time FROM log_entries ORDER BY entry_time DESC";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(selectSql)) {
                while (rs.next()) {
                    logEntries.add(rs.getString("entry_time"));
                }
            }

            req.setAttribute("logEntries", logEntries);
        } catch (SQLException e) {
            req.setAttribute("error", e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close connection");
                }
            }
        }

        req.setAttribute("pageBody", "log.jsp");
        req.getRequestDispatcher("WEB-INF/views/_layout.jsp").forward(req, resp);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3308/JAVA_SPU_221?useUnicode=true&characterEncoding=UTF-8",
            "user_221",
            "pass_221"
        );
    }

    @Override
    public void destroy() {
        try {
            Driver driver = DriverManager.getDriver("jdbc:mysql://localhost:3308/JAVA_SPU_221");
            DriverManager.deregisterDriver(driver);
        } catch (SQLException e) {
            System.out.println("Failed to deregister MySQL JDBC driver");
        }
    }
}