/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.marina.entity.user.Client;
import com.marina.message.RequestMsg;
import com.marina.message.ResponseMsg;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marik
 */
public class Authorization {

    public Authorization(RequestMsg msg) {

    }

    protected String method;
    protected ResponseMsg response;

    public ResponseMsg indexAction(RequestMsg msg) {

        method = route(msg.getCommand());
        
        Method indexActionMethod;
        try {
            Class<?> className = Class.forName("controller.Authorization");
            Constructor<?> cons = className.getConstructor(RequestMsg.class);
            Object classNameObject = cons.newInstance(msg);
            indexActionMethod = className.getDeclaredMethod(method);
            response = (ResponseMsg)indexActionMethod.invoke(classNameObject);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException | InstantiationException ex) {
            Logger.getLogger(Authorization.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    private String route(String command) {
        String[] classCommandArray = command.split("\\.");
        return classCommandArray[1];
    }

    public ResponseMsg login() {
        return new ResponseMsg(true, "ok", "hello");
    }
}
