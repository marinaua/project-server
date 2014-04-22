/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.userdao;

import com.marina.entity.user.Administrator;
import dao.CRUDDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marik
 */
public class AdministratorDAO extends CRUDDAO<Administrator>{
    
    private Administrator administrator;

    public AdministratorDAO(Connection dbConnection) {
        super(dbConnection);
    }
    
    @Override
    public Administrator read(int id) {
        String sql = "SELECT * FROM user WHERE id = " + id;
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                resultSet.getString("name");
                administrator = new Administrator();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return administrator;
    }
    
}
