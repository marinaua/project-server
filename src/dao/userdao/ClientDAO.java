/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.userdao;

import com.marina.entity.user.DeprecatedAbstractUser;
import com.marina.entity.user.DeprecatedClient;
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
public class ClientDAO extends CRUDDAO<DeprecatedClient>{
    
    private DeprecatedClient client;
    
    public ClientDAO(Connection dbConnection) {
        super(dbConnection);
    }
    
    @Override
    public DeprecatedClient read(int id) {
        String sql = "SELECT * FROM user WHERE id = " + id;
        Statement statement;
        ResultSet resultSet;
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                resultSet.getString("name");
                client = new DeprecatedClient();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return client;
    }  
}
