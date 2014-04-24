/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.marina.entity.user.User;
import dao.userdao.ClientDAO;
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

    public AbstractDAO(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    protected void executeUpdate(String sql) {
        Statement statement;
        try {
            statement = dbConnection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CRUDDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultSet;
    }

}
