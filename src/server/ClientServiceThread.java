/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.marina.message.RequestMsg;
import com.marina.message.ResponseMsg;
import controller.IndexController;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marik
 */
public class ClientServiceThread extends Thread {

    Socket clientSocket;
    int clientID = -1;
    boolean running = true;
    private static Logger clientServiceThreadLogger = Logger.getLogger(ClientServiceThread.class.getName());
    private File visitHistoryFile;

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
                if (clientCommand instanceof RequestMsg) {
                    RequestMsg msg = (RequestMsg) clientCommand;
                    IndexController controller = new IndexController();
                    ResponseMsg response = controller.indexAction(msg);
                    out.writeObject(response);
                    out.flush();
                                        
                  
                   
                        out.close();
                        in.close();
                        clientSocket.close();
                    
                    //Log client requests
                    clientRequestLog(msg);
                } else {
                    throw new Exception("Request container must be instanceof RequestMsg");
                }
            }

        } catch (Exception ex) {
            //clientServiceThreadLogger.log(Level.SEVERE, "Exception in runnung client thread: ", ex);
        }
    }

    private void clientRequestLog(RequestMsg msg) {
        visitHistoryFile = new File(getPath());
        try {
            try (FileWriter writer = new FileWriter(visitHistoryFile, true)) {
                String message = "Command: " + msg.getCommand() + ". User " + msg.getUser();
                writer.write(getCurrentTime() + message + System.getProperty("line.separator") + System.getProperty("line.separator"));
            }
        } catch (IOException ex) {
            clientServiceThreadLogger.log(Level.SEVERE, "Exception in writing visit history: ", ex);
        }
    }

    private String getPath() {
        Path path = FileSystems.getDefault().getPath("logs", "visithistory", getCurrentDate() + ".txt");
        return path.toString();
    }

    private String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new Date());
    }

    private String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

}
