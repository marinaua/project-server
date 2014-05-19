/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.marina.entity.credit.Credit;
import com.marina.entity.creditprogram.CreditProgram;
import com.marina.entity.order.Order;
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
public class OrderController extends AbstractController {

    OrderDAO orderDAO = new OrderDAO();

    public ResponseMsg getOrders(RequestMsg msg) {
        User user = msg.getUser();
        int userId = user.getID();
        ArrayList<Order> ordersList = orderDAO.readAll(userId);
        if (ordersList != null) {
            return new ResponseMsg(true, "OK", ordersList);
        }

        return new ResponseMsg(false, "getting orders failed", "bye");
    }

    public ResponseMsg getManagersOrders(RequestMsg msg) {
        User user = msg.getUser();
        int userId = user.getID();
        ArrayList<Order> managersOrdersList = orderDAO.readAllManagersOrders(userId);
        if (managersOrdersList != null) {
            return new ResponseMsg(true, "OK", managersOrdersList);
        }

        return new ResponseMsg(false, "getting orders failed", "bye");
    }

    public ResponseMsg makeOrder(RequestMsg msg) {
        Order order = new Order();
        User user = msg.getUser();
        CreditProgram creditProgram = (CreditProgram) msg.getData();
        order.setClientID(user.getID());
        order.setStatusID(2);
        order.setManagerID(user.getManagerID());
        order.setCreditProgrammID(creditProgram.getID());

        orderDAO.create(order);

        return new ResponseMsg(true, "ok", null);
    }

    public ResponseMsg updateStatusAccepted(RequestMsg msg) {
        Order order = (Order) msg.getData();
        
        //Make new credit
        Credit credit = new Credit();
        credit.setClientID(order.getClientID());
        credit.setCreditProgramID(order.getCreditProgrammID());
        credit.setStatusID(1);
        
        //Add new credit to database
        CreditDAO creditDAO = new CreditDAO();
        int creditId = creditDAO.createAndReturnId(credit);
        System.out.println("-------> " + creditId);
        
        //Add credit status to accepted order
        order.setCreditID(creditId);
        
        //Set order status to 3 = Accepted
        order.setStatusID(3);
        
        orderDAO.update(order);

        return new ResponseMsg(true, "ok", null);
    }

    public ResponseMsg updateStatusDeclined(RequestMsg msg) {
        Order order = (Order) msg.getData();
        int orderId = order.getID();
        orderDAO.updateStatusAccepted(orderId, 4);

        return new ResponseMsg(true, "ok", null);
    }

}
