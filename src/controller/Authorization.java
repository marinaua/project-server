/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.marina.entity.user.AbstractUser;
import com.marina.message.RequestMsg;
import com.marina.message.ResponseMsg;
import dao.userdao.GuestDAO;
import dbconnection.MyDBConnection;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marik
 */
public class Authorization {
    
    protected String method;
    protected ResponseMsg response;

    public Authorization(RequestMsg msg) {

    }

    public ResponseMsg indexAction(RequestMsg msg) {

        method = route(msg.getCommand());

        Method indexActionMethod;
        try {
            Class<?> className = Class.forName(this.getClass().getName());
            Constructor<?> cons = className.getConstructor(RequestMsg.class);
            Object classNameObject = cons.newInstance(msg);
            indexActionMethod = className.getDeclaredMethod(method, RequestMsg.class);
            response = (ResponseMsg) indexActionMethod.invoke(classNameObject, msg);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException | InstantiationException ex) {
            Logger.getLogger(Authorization.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    private String route(String command) {
        String[] classCommandArray = command.split("\\.");
        return classCommandArray[1];
    }

    public ResponseMsg login(RequestMsg msg) {
        boolean identification;
        AbstractUser user = (AbstractUser) msg.getData();

        Connection connection = MyDBConnection.getConnection();
        GuestDAO guestDAO = new GuestDAO(connection);
        identification = guestDAO.read(user);

        if (identification) {
            return new ResponseMsg(true, "ok", "hello");
        } else {
            return new ResponseMsg(false, "login fail", "bye");
        }
    }
    
}
