/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.userdao;

import com.marina.entity.user.DeprecatedAbstractUser;
import com.marina.entity.user.DeprecatedClient;
import com.marina.entity.user.DeprecatedGuest;
import com.marina.entity.user.DeprecatedManager;
import dao.CRUDDAO;
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
public class GuestDAO extends CRUDDAO<DeprecatedGuest> {

    protected DeprecatedAbstractUser user;
    protected String role;

    public GuestDAO(Connection dbConnection) {
        super(dbConnection);
    }

    public boolean read(DeprecatedGuest guest) {
        String sql = "SELECT role FROM user WHERE login = '" + guest.getLogin() + "' AND password = '" + guest.getPassword() + "'";
        Statement statement;
        ResultSet resultSet;
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
    
    public String getRole(DeprecatedAbstractUser guest) {
        String sql = "SELECT role FROM user WHERE login = '" + guest.getLogin() + "' AND password = '" + guest.getPassword() + "'";
        Statement statement;
        ResultSet resultSet;
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
    public DeprecatedGuest read(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
