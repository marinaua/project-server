/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import com.marina.message.RequestMsg;
import com.marina.message.ResponseMsg;

/**
 *
 * @author Marik
 */
public interface iController {
    public ResponseMsg indexAction(RequestMsg msg);
}
