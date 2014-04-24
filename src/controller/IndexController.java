/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.marina.message.RequestMsg;
import com.marina.message.ResponseMsg;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marik
 */
public class IndexController extends AbstractController {

    @Override
    public ResponseMsg indexAction(RequestMsg msg) {
        try {
            //Reflective instance class creating and invokation of method indexAction in that class
            String route = route(msg.getCommand());            
            Class<?> className = Class.forName(route);
            Object classNameObject = className.newInstance();
            Method indexActionMethod = className.getMethod("indexAction", RequestMsg.class);
            response = (ResponseMsg) indexActionMethod.invoke(classNameObject, msg);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response;
    }
}
