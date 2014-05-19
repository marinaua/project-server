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
public class AbstractController implements iController {

    protected String method;
    protected ResponseMsg response;
    private static Logger abstractControllerLogger = Logger.getLogger(AbstractController.class.getName());

    @Override
    public ResponseMsg indexAction(RequestMsg msg) {

        method = route(msg.getCommand());

        Method indexActionMethod;
        try {
            Class<?> className = Class.forName(this.getClass().getName());
            Object classNameObject = className.newInstance();
            indexActionMethod = className.getDeclaredMethod(method, RequestMsg.class);
            response = (ResponseMsg) indexActionMethod.invoke(classNameObject, msg);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException | InstantiationException ex) {
            abstractControllerLogger.log(Level.SEVERE, "Exception in indexAction recursive method: ", ex);
        }
        return response;
    }

    protected String route(String command) {
        String[] classCommandArray = command.split("\\.");
        String className = "controller." + classCommandArray[0] + "Controller";
        String methodName = classCommandArray[1];
        if (this.getClass().getName().equals("controller.IndexController")) {
            return className;
        }

        return methodName;

    }

}
