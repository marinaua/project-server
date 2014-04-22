/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.marina.entity.creditprogram.CreditProgram;
import com.marina.message.RequestMsg;
import com.marina.message.ResponseMsg;
import dao.creditprogramdao.CreditProgramDAO;
import dbconnection.MyDBConnection;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marik
 */
public class CreditProgramsData {

    protected String method;
    protected ResponseMsg response;

    public CreditProgramsData(RequestMsg msg) {

    }
    
    public ResponseMsg indexAction(RequestMsg msg) {

        method = route(msg.getCommand());

        Method indexActionMethod;
        try {
            Class<?> className = Class.forName("controller.CreditProgramsData");
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

    public ResponseMsg getCreditPrograms(RequestMsg msg) {
        Connection connection = MyDBConnection.getConnection();
        CreditProgramDAO creditProgramDAO = new CreditProgramDAO(connection);
        ArrayList<CreditProgram> creditProgramList = creditProgramDAO.readAll();
        if(creditProgramList != null){
            return new ResponseMsg(true, "OK", creditProgramList);
        }

        return new ResponseMsg(false, "login fail", "bye");
    }

}
