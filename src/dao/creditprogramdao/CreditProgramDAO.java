/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.creditprogramdao;

import com.marina.entity.creditprogram.CreditProgram;
import dao.CRUDDAO;
import dao.userdao.ClientDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marik
 */
public class CreditProgramDAO extends CRUDDAO<CreditProgram>{
    
    private CreditProgram creditProgram;
    private ArrayList<CreditProgram> creditProgramList;

    public CreditProgramDAO(Connection dbConnection) {
        super(dbConnection);
    }

    @Override
    public CreditProgram read(int id) {
        String sql = "SELECT * FROM credit_program WHERE id = " + id;
        Statement statement;
        ResultSet resultSet;
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                creditProgram = new CreditProgram();
                creditProgram.setTitle(resultSet.getString("title"));
                creditProgram.setShortDescription(resultSet.getString("short_description"));
                creditProgram.setFullDescription(resultSet.getString("full_description"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return creditProgram;
    }
    
    public ArrayList<CreditProgram> readAll() {
        creditProgramList = new ArrayList<>();
        String sql = "SELECT * FROM credit_program";
        Statement statement;
        ResultSet resultSet;
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                creditProgram = new CreditProgram();
                creditProgram.setTitle(resultSet.getString("title"));
                creditProgram.setShortDescription(resultSet.getString("short_description"));
                creditProgram.setFullDescription(resultSet.getString("full_description"));
                creditProgramList.add(creditProgram);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return creditProgramList;
    }
    
    
}
