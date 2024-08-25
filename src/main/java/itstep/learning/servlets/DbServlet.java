package itstep.learning.servlets;

import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DbServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Підключення до БД
        // JDBC (~ADO/PDO) - уніфікована технологія доступу до БД
        // підключення - інструкція - результат
        Connection connection = null;
        Driver mysqlDriver = null;
        try {
            mysqlDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3308/JAVA_SPU_221?useUnicode=true&characterEncoding=UTF-8",
                "user_221",
                "pass_221"
            );
            String sql = "SHOW DATABASES";
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            List<String> databases = new ArrayList<>();
            while (res.next()) {
                databases.add(res.getString(1));
            }
            req.setAttribute("databases", databases);
            res.close();
            stmt.close();
        } catch (SQLException e) {
            req.setAttribute("error", e.getMessage());
        }

        req.setAttribute("pageBody", "db.jsp");
        req.getRequestDispatcher("WEB-INF/views/_layout.jsp").forward(req, resp);
    }

}