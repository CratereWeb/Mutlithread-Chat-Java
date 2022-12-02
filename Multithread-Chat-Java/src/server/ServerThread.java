package server;

import java.net.Socket;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServerThread extends Thread {
    
    private Socket socket;
    private ArrayList<ServerThread> threadList;
    private PrintWriter output;
    //private String[] colors = new String[] {"\u001B[31m\\","\u001B[32m\\","\u001B[33m\\","\u001B[34m\\","\u001B[35m\\","\u001B[36m\\","\u001B[37m\\"};

    public ServerThread(Socket s, ArrayList<ServerThread> t) {
        this.socket = s;
        this.threadList = t;
    }

    @Override
    public void run() {
        try {
            // Lire l'input du Client
            // L'objet BufferedReader va lire le flux de caractères
                // que lui renvoie l'InputStreamReader après avoir décodé en caractères le flux de bytes
                    // que lui renvoie le flux d'entrée du Socket serveur.
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // On va stocker dans l'attribut output de notre classe ServerThread : 
                // Un objet PrintWriter qui imprime la traduction en caractères du flux de bytes
                	// que lui renvoie le flux de sortie du Socket serveur.
            this.output = new PrintWriter(this.socket.getOutputStream(), true);

            // boucle infinie d'écoute des messages client
            while(true) {
                String outputString = input.readLine();

                // sortir de la boucle si le client saisit la commande "/exit"
                if(outputString.equals("/exit")) {
                    break;
                }
                printToAllClients(outputString);
                System.out.println("Server received : " + outputString);
            }
              
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    private void printToAllClients(String outputString) {

        for (ServerThread sT : threadList ) {
            
            sT.output.println(outputString);

        }
    }


}
