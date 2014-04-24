/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.marina.entity.user.DeprecatedClient;
import com.marina.entity.user.DeprecatedGuest;
import com.marina.entity.user.IUser;
import com.marina.entity.user.User;
import com.marina.exception.AuthorizeException;
import com.marina.message.RequestMsg;
import com.marina.message.ResponseMsg;
import dao.userdao.UserDAO;
import dbconnection.MyDBConnection;
import java.sql.Connection;

/**
 *
 * @author Marik
 */
public class AuthorizationController extends AbstractController {

    public ResponseMsg login(RequestMsg msg) {
        boolean registered;
        User user = (User) msg.getData();
        Connection connection = MyDBConnection.getConnection();
        UserDAO userDAO = new UserDAO(connection);
        
        registered = userDAO.isRegistered(user);     
        
        if (registered) {
            user = userDAO.read(user);
            return new ResponseMsg(true, "ok", user);
        } else {
            return new ResponseMsg(false, "login fail", new AuthorizeException());
        }
    }
    
}
