/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
public class IndexController {

    protected ResponseMsg response;
    protected String name;
    protected String command;

    public ResponseMsg indexAction(RequestMsg msg) {
        try {
            //Reflective instance class creating and invokation of method indexAction in that class
            Class<?> className = Class.forName(route(msg.getCommand()));
            Constructor<?> cons = className.getConstructor(RequestMsg.class);
            Object classNameObject = cons.newInstance(msg);
            Method indexActionMethod = className.getDeclaredMethod("indexAction", RequestMsg.class);
            response = (ResponseMsg)indexActionMethod.invoke(classNameObject, msg);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response;
    }

    private String route(String command) {
        String[] classCommandArray = command.split("\\."); 
        return "controller." + classCommandArray[0];
    }

}
