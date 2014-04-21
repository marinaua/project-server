package dao;

import com.marina.entity.Entity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Marik
 */
public abstract class CRUDDAO<T> {

    protected final Connection dbConnection;
    protected Statement statement;
    protected ResultSet resultSet;
    protected Entity entity;

    public CRUDDAO(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public abstract T read(int id);

    public void create(Entity entity) {

        String sql = "INSTERT INTO " + entity.getTable() + " (" + entity.getColumns() + ") VALUES (" + entity.getValues() + ")";
        executeSQL(sql);
    }

    public void update(Entity entity) {
        String sql = "UPDATE " + entity.getTable() + " (" + entity.getColumns() + ") VALUES (" + entity.getValues() + ") WHERE id = " + entity.getID();
        executeSQL(sql);
    }

    public void delete(int id) {
        String sql = "DELETE FROM " + entity.getTable() + " WHERE id = " + id;
        executeSQL(sql);
    }

    public void delete(Entity entity) {
        String sql = "DELETE FROM " + entity.getTable() + " WHERE id = " + entity.getID();
        executeSQL(sql);
    }

    private void executeSQL(String sql) {
        try {
            statement = dbConnection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CRUDDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
