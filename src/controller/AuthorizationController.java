/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.marina.entity.user.User;
import com.marina.exception.AuthorizeException;
import com.marina.message.RequestMsg;
import com.marina.message.ResponseMsg;
import dao.userdao.UserDAO;

/**
 *
 * @author Marik
 */
public class AuthorizationController extends AbstractController {

    public ResponseMsg login(RequestMsg msg) {
        boolean registered;
        User user = (User) msg.getData();
        UserDAO userDAO = new UserDAO();
        
        registered = userDAO.isRegistered(user);     
        
        if (registered) {
            user = userDAO.read(user);
            return new ResponseMsg(true, "ok", user);
        } else {
            return new ResponseMsg(false, "login fail", new AuthorizeException());
        }
    }
    
}
