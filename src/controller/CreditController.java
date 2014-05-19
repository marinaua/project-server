/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import com.marina.entity.credit.Credit;
import com.marina.entity.user.User;
import com.marina.message.RequestMsg;
import com.marina.message.ResponseMsg;
import dao.creditdao.CreditDAO;
import dao.orderDAO.OrderDAO;
import java.util.ArrayList;

/**
 *
 * @author Marik
 */
public class CreditController extends AbstractController {
    public ResponseMsg getCredits(RequestMsg msg) {
        User user = msg.getUser();
        int userId = user.getID();
        CreditDAO creditDAO = new CreditDAO();
        ArrayList<Credit> creditList = creditDAO.readAll(userId);
        if(creditList != null){
            return new ResponseMsg(true, "OK", creditList);
        }

        return new ResponseMsg(false, "getting program failed", "bye");
    }
    
    public ResponseMsg payCredit(RequestMsg msg) {
        Credit credit = (Credit)msg.getData();
        credit.setStatusID(2);
        CreditDAO creditDAO = new CreditDAO();
        
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.updateStatusAccepted(credit.getID(), 5);
        
        creditDAO.update(credit);
        
        //orderDAO.update(credit);
        
        return new ResponseMsg(true, "ok", null);
    }
    
}
