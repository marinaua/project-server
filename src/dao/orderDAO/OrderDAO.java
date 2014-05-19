/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.orderDAO;

import com.marina.entity.order.Order;
import dao.CRUDDAO;
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
public class OrderDAO extends CRUDDAO<Order> {

    private Order order;
    private ArrayList<Order> orderList;
    private static Logger orderDAOLogger = Logger.getLogger(OrderDAO.class.getName());

    @Override
    public Order read(int id) {
        String sql = "SELECT title, status FROM order "
                + "JOIN credit_program "
                + "ON order.credit_program_id = credit_program.id "
                + "JOIN order_status "
                + "ON order.status_id = order_status.id "
                + "WHERE id = " + id;
        Statement statement;
        ResultSet resultSet;
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                order = new Order();
                order.setTitle(resultSet.getString("title"));
                order.setStatus(resultSet.getString("status"));
            }
        } catch (SQLException ex) {
            orderDAOLogger.log(Level.SEVERE, "Exception in reading order info by id: ", ex);
        }

        return order;
    }

    public ArrayList<Order> readAll(int userId) {
        orderList = new ArrayList<>();
        String sql = "SELECT order.client_id, order.status_id, order.manager_id, order.credit_program_id, order.credit_id, title, status FROM `order` "
                + "JOIN credit_program "
                + "ON order.credit_program_id = credit_program.id "
                + "JOIN order_status "
                + "ON order.status_id = order_status.id "
                + "WHERE client_id = " + userId;
        Statement statement;
        ResultSet resultSet;
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                order = new Order();
                order.setClientID(resultSet.getInt("client_id"));
                order.setStatusID(resultSet.getInt("status_id"));
                order.setManagerID(resultSet.getInt("manager_id"));
                order.setCreditProgrammID(resultSet.getInt("credit_program_id"));
                order.setCreditID(resultSet.getInt("credit_id"));
                order.setTitle(resultSet.getString("title"));
                order.setStatus(resultSet.getString("status"));
                orderList.add(order);
            }
        } catch (SQLException ex) {
            orderDAOLogger.log(Level.SEVERE, "Exception in reading all order info by user id: ", ex);
        }

        return orderList;
    }

    public void updateStatusAccepted(int id, int statusId) {
        String sql = "UPDATE `order` SET status_id = " + statusId + " WHERE credit_id = " + id;
        executeUpdate(sql);
    }

    public ArrayList<Order> readAllManagersOrders(int userId) {
        orderList = new ArrayList<>();
        String sql = "SELECT order.id, order.client_id, order.status_id, order.manager_id, order.credit_program_id, order.credit_id, title, status, name, surname FROM `order` "
                + "JOIN credit_program ON order.credit_program_id = credit_program.id "
                + "JOIN order_status ON order.status_id = order_status.id "
                + "JOIN user ON  order.client_id = user.id "
                + "WHERE order.manager_id = " + userId;
        Statement statement;
        ResultSet resultSet;
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setClientID(resultSet.getInt("client_id"));
                order.setStatusID(resultSet.getInt("status_id"));
                order.setManagerID(resultSet.getInt("manager_id"));
                order.setCreditProgrammID(resultSet.getInt("credit_program_id"));
                order.setCreditID(resultSet.getInt("credit_id"));
                order.setTitle(resultSet.getString("title"));
                order.setStatus(resultSet.getString("status"));
                order.setClientName(resultSet.getString("name"));
                order.setClientSurname(resultSet.getString("surname"));
                orderList.add(order);
            }
        } catch (SQLException ex) {
            orderDAOLogger.log(Level.SEVERE, "Exception in reading all orders info by manager id: ", ex);
        }

        return orderList;
    }

}
