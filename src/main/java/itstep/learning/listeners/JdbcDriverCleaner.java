package itstep.learning.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class JdbcDriverCleaner implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // No action needed on context initialization
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                sce.getServletContext().log("Deregistering JDBC driver: " + driver);
            } catch (SQLException e) {
                sce.getServletContext().log("Error deregistering JDBC driver: " + driver, e);
            }
        }
        // Shutdown MySQL Connector/J's thread
        com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();
    }
}