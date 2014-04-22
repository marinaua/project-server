/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.marina.entity.user.AbstractUser;
import com.marina.entity.user.Client;
import com.marina.message.RequestMsg;
import com.marina.message.ResponseMsg;
import controller.IndexController;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 *
 * @author Marik
 */
public class ClientServiceThread extends Thread {

    Socket clientSocket;
    int clientID = -1;
    boolean running = true;

    ClientServiceThread(Socket s, int i) {
        clientSocket = s;
        clientID = i;
    }

    @Override
    public void run() {
        System.out.println("Accepted Client : ID - " + clientID + " : Address - "
                + clientSocket.getInetAddress().getHostName());
        try {
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            while (running) {
                Object clientCommand = in.readObject();
                if(clientCommand instanceof RequestMsg){
                    RequestMsg msg = (RequestMsg) clientCommand;
                    IndexController controller = new IndexController();
                    ResponseMsg response =  controller.indexAction(msg); 
                    out.writeObject(response);
                    out.flush();
                    //AbstractUser user = (Client) msg.getData();
                    
                    System.out.println("command: " + msg.getCommand() + msg.getData() + "\n" + response);
                }else{
                    throw new Exception("Request conteiner must be instanceof RequestMsg");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
