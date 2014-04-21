/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDBConnection {

    private static Connection connection = null;
    private final static String ADRESS = "localhost";
    private final static String DATABASE = "creditsystem";
    private final static String USER = "rootadmin";
    private final static String PASSWORD = "admin";
    private final static String DRIVER = "com.mysql.jdbc.Driver";
    public static int count = 0;

    /**
     *
     * Private constructor to forbid creating MyDBConnection instance
     *
     */
    private MyDBConnection() {

    }

    /**
     * Method that loads the specified driver
     *
     * @return void
     *
     */
    private static void loadDriver() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            errorHandler("Failed to load the driver " + DRIVER, e);
        }
    }

    /**
     * Method that loads the connection into the right property
     *
     * @return void
     *
     */
    private static void loadConnection() {
        try {
            connection = DriverManager.getConnection(getFormatedUrl(), USER, PASSWORD);
        } catch (SQLException e) {
            errorHandler("Failed to connect to the database " + getFormatedUrl(), e);
        }
    }

    /**
     * Method that shows the errors thrown by the singleton
     *
     * @param {String} Message
     * @option {Exception} e
     *
     *
     */
    private static void errorHandler(String message, Exception e) {
        System.out.println(message);
        if (e != null) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method that returns the formated URL to connect to the database
     *
     * @return {String}
     *
     */
    public static String getFormatedUrl() {
        return "jdbc:mysql://" + ADRESS + "/" + DATABASE;
    }

    /**
     * Static method that returns the instance for the singleton
     *
     * @return {Connection} connection
     *
     */
    public static Connection getConnection() {
        if (connection == null) {
            loadDriver();
            loadConnection();
            count++;
        }
        return connection;
    }

    /**
     * Static method that close the connection to the database
     *
     *
     */
    public static void closeConnection() {
        if (connection == null) {
            errorHandler("No connection found", null);
        } else {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                errorHandler("Failed to close the connection", e);
            }
        }
    }
}
