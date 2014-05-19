/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dbconnection.MyDBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marik
 */
public abstract class AbstractDAO {

    protected final Connection dbConnection;
    private static Logger abstractDAOLogger = Logger.getLogger(AbstractDAO.class.getName());

    public AbstractDAO() {
        this.dbConnection = MyDBConnection.getConnection();
    }

    protected void executeUpdate(String sql) {
        Statement statement;
        try {
            statement = dbConnection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            abstractDAOLogger.log(Level.SEVERE, "Exception in executing update: ", ex);
        }
    }

    protected ResultSet executeQuery(String sql) {
        ResultSet resultSet = null;
        Statement statement;
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(sql);
            abstractDAOLogger.log(Level.SEVERE, "Exception in executing query: ", ex);
        }

        return resultSet;
    }

}
