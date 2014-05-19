/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.creditdao;

import com.marina.entity.credit.Credit;
import dao.CRUDDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marik
 */
public class CreditDAO extends CRUDDAO<Credit> {

    private Credit credit;
    private ArrayList<Credit> creditList;
    private static Logger creditDAOLogger = Logger.getLogger(CreditDAO.class.getName());

    @Override
    public Credit read(int id) {
        String sql = "SELECT title, status FROM credit "
                + "JOIN credit_program "
                + "ON credit.credit_program_id = credit_program.id "
                + "JOIN credit_status "
                + "ON credit.status_id = credit_status.id "
                + "WHERE id = " + id;
        Statement statement;
        ResultSet resultSet;
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                credit = new Credit();
                credit.setTitle(resultSet.getString("title"));
                credit.setStatus(resultSet.getString("status"));
            }
        } catch (SQLException ex) {
            creditDAOLogger.log(Level.SEVERE, "Exception in reading credit info by id: ", ex);
        }

        return credit;
    }

    public ArrayList<Credit> readAll(int userId) {
        creditList = new ArrayList<>();
        String sql = "SELECT client_credit.id, client_credit.client_id, client_credit.credit_program_id, client_credit.status_id, title, status FROM client_credit "
                + "JOIN credit_program "
                + "ON client_credit.credit_program_id = credit_program.id "
                + "JOIN credit_status "
                + "ON client_credit.status_id = credit_status.id "
                + "WHERE client_id = " + userId;
        Statement statement;
        ResultSet resultSet;
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                credit = new Credit();
                credit.setId(resultSet.getInt("client_credit.id"));
                credit.setClientID(resultSet.getInt("client_credit.client_id"));
                credit.setCreditProgramID(resultSet.getInt("client_credit.credit_program_id"));
                credit.setStatusID(resultSet.getInt("client_credit.status_id"));
                credit.setTitle(resultSet.getString("title"));
                credit.setStatus(resultSet.getString("status"));
                creditList.add(credit);
            }
        } catch (SQLException ex) {
            creditDAOLogger.log(Level.SEVERE, "Exception in reading all credit info by user id: ", ex);
        }
        return creditList;
    }

    public int createAndReturnId(Credit credit) {
        int id = 0;
        Lock lock = new ReentrantLock();
        try {
            lock.lock();

            create(credit);
            String sql = "SELECT id FROM  client_credit ORDER BY id DESC LIMIT 1";
            Statement statement;
            ResultSet resultSet;
            try {
                statement = dbConnection.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    id = resultSet.getInt("id");
                }
            } catch (SQLException ex) {
                creditDAOLogger.log(Level.SEVERE, "Exception in creating new credit and getting ID", ex);
            }
        } finally {
            lock.unlock();
        }

        return id;
    }

}
