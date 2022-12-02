package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
// import java.io.PrintWriter;
import java.io.IOException;

public class ClientThread extends Thread {

    private Socket socket;
    private BufferedReader input;
    // private PrintWriter output;

    public ClientThread(Socket s) throws IOException {
        this.socket = s;
        // Le thread envoie les entrées de la console utilisateur vers le flux d'entrée du socket
        this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        // this.output = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {

        try {

            while(true) {
                String response = input.readLine();
                System.out.println(response);
            }
        
        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                input.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
//    private String defineUserColor() {
//    	
//    	
//    	//return colors[]
//    }
    
}
