/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.LogManager;

/**
 *
 * @author Marik
 */
public class Main {

    public static void main(String[] args) throws Exception {
        try {
            LogManager.getLogManager().readConfiguration(
               Main.class.getResourceAsStream("/loggerconfig/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }

        ServerSocket m_ServerSocket = new ServerSocket(12111);
        int id = 0;
        while (true) {
            Socket clientSocket = m_ServerSocket.accept();
            ClientServiceThread cliThread = new ClientServiceThread(clientSocket, id++);
            cliThread.start();
        }
    }
}
