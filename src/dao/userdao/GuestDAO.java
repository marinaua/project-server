/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.userdao;

import com.marina.entity.user.AbstractUser;
import com.marina.entity.user.Client;
import com.marina.entity.user.Guest;
import com.marina.entity.user.Manager;
import dao.CRUDDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marik
 */
public class GuestDAO extends CRUDDAO<Guest> {

    protected AbstractUser user;
    protected String role;

    public GuestDAO(Connection dbConnection) {
        super(dbConnection);
    }

    public boolean read(AbstractUser guest) {
        String sql = "SELECT role FROM user WHERE login = '" + guest.getLogin() + "' AND password = '" + guest.getPassword() + "'";
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                role = resultSet.getString("role");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return role != null;
    }
    
    public String getRole(AbstractUser guest) {
        String sql = "SELECT role FROM user WHERE login = '" + guest.getLogin() + "' AND password = '" + guest.getPassword() + "'";
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                role = resultSet.getString("role");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public Guest read(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
