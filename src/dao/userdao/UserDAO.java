/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.userdao;

import com.marina.entity.user.User;
import dao.CRUDDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marik
 */
public class UserDAO extends CRUDDAO<User> {

    private User user;
    private static Logger userDAOLogger = Logger.getLogger(UserDAO.class.getName());
    
    @Override
    public User read(int id) {
        String sql = "SELECT * FROM user WHERE id = " + id;
        Statement statement;
        ResultSet resultSet;
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                user = new User();
                user.setID(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setIncome(resultSet.getInt("income"));
                user.setManagerID(resultSet.getInt("manager_id"));
                user.setLogin(resultSet.getString("login"));
                //user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
            }
        } catch (SQLException ex) {
            userDAOLogger.log(Level.SEVERE, "Exception in reading user info by id: ", ex);
        }

        return user;
    }

    public User read(User user) {
        String where = "";
        if (user.getID() != 0) {
            where += " id =" + user.getID() + " ";
        }
        if (!user.getLogin().isEmpty() && !user.getPassword().isEmpty()) {
            if (!where.isEmpty()) {
                where += "AND";
            }
            where += " login = '" + user.getLogin() + "' AND password = '" + user.getPassword() + "' ";
        }

        String sql = "SELECT * FROM user WHERE " + where;
        
        ResultSet resultSet = executeQuery(sql);
        try {
            while (resultSet.next()) {
                user = new User();
                user.setID(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setIncome(resultSet.getInt("income"));
                user.setManagerID(resultSet.getInt("manager_id"));
                user.setLogin(resultSet.getString("login"));
                //user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
            }
        } catch (SQLException ex) {
            userDAOLogger.log(Level.SEVERE, "Exception in reading credit program info by user with login and pass: ", ex);
        }

        return user;
    }

    public boolean isRegistered(User user) {
        boolean result = false;
        Statement statement;
        ResultSet resultSet;
        String sql = "SELECT id FROM user WHERE login = '" + user.getLogin() + "' AND password = '" + user.getPassword() + "'";
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(sql);
            result = resultSet.next();

        } catch (SQLException ex) {
            userDAOLogger.log(Level.SEVERE, "Exception in if user registered method: ", ex);
        }
        return result;

    }
}
