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
 * @param <T>
 */
public abstract class CRUDDAO<T extends Entity> extends AbstractDAO {

    public CRUDDAO(Connection dbConnection) {
        super(dbConnection);
    }

    public abstract T read(int id);

    public void create(Entity entity) {

        String sql = "INSTERT INTO " + entity.getTable() + " (" + entity.getColumns() + ") VALUES (" + entity.getValues() + ")";
        executeUpdate(sql);
    }

    public void update(Entity entity) {
        String sql = "UPDATE " + entity.getTable() + " (" + entity.getColumns() + ") VALUES (" + entity.getValues() + ") WHERE id = " + entity.getID();
        executeUpdate(sql);
    }

    public void delete(Entity entity) {
        String sql = "DELETE FROM " + entity.getTable() + " WHERE id = " + entity.getID();
        executeUpdate(sql);
    }

}
