package dao;

import com.marina.entity.Entity;

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

    public abstract T read(int id);

    public void create(Entity entity) {
        
        String sql = "INSERT INTO `" + entity.getTable() + "` SET " + entity.getSQLValues();
        
        executeUpdate(sql);
    }

    public void update(Entity entity) {
        String sql = "UPDATE `" + entity.getTable() + "` SET " + entity.getSQLValues() + " WHERE id = " + entity.getID();
        //System.out.println("---->" + sql);
        executeUpdate(sql);
    }

    public void delete(Entity entity) {
        String sql = "DELETE FROM `" + entity.getTable() + "` WHERE id = " + entity.getID();
        executeUpdate(sql);
    }

}
