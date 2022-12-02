package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        
        // Un thread pour chaque client connecté au serveur
        ArrayList<ServerThread> threadList = new ArrayList<>();
        
        try(ServerSocket serverSocket = new ServerSocket(6500)) {
            
            while(true) {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket, threadList);
                // ajout du thread à la liste
                threadList.add(serverThread);
                serverThread.start();
                System.out.println(threadList.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
