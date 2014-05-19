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
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Marik
 */
public class CreditProgramController extends AbstractController {
    
    
    
    public ResponseMsg getCreditPrograms(RequestMsg msg) {
        CreditProgramDAO creditProgramDAO = new CreditProgramDAO();
        ArrayList<CreditProgram> creditProgramList = creditProgramDAO.readAll();
        if(creditProgramList != null){
            return new ResponseMsg(true, "OK", creditProgramList);
        }

        return new ResponseMsg(false, "getting program failed", "bye");
    }
    
    public ResponseMsg updateCreditProgram(RequestMsg msg){
        CreditProgramDAO creditProgramDAO = new CreditProgramDAO();
        creditProgramDAO.update((CreditProgram)msg.getData());
        return new ResponseMsg(true, "OK", null);
    }

}
