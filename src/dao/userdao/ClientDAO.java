/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.userdao;

import com.marina.entity.user.AbstractUser;
import com.marina.entity.user.Client;
import dao.CRUDDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marik
 */
public class ClientDAO extends CRUDDAO<Client>{
    
    private Client client;
    
    public ClientDAO(Connection dbConnection) {
        super(dbConnection);
    }
    
    @Override
    public Client read(int id) {
        String sql = "SELECT * FROM users WHERE id = " + id;
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                resultSet.getString("name");
                client = new Client();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return client;
    }
    
}
