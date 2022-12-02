package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 6500)) {
        	// Le socket créé, on définit comment les données transitent entre la console de l'utilisateur et le socket
            // Pour les données entrantes (de la console vers le socket)
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Pour les données sortantes (du socket vers la console)
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true); // l'objet PrintWriter imprimera des données vers le flux de sortie du socket serveur. Le second argument autoFlush : true permet de vider le flux après l'impression.
            
            // variables définissant l'input utilisateur
            Scanner scanner = new Scanner(System.in);
            String userInput;
            String response;
            String clientName = "?";
            
            
            ClientThread clientThread = new ClientThread(socket);
            // démarrer le thread client
            clientThread.start();
            
            // la boucle tourne jusqu'à ce que l'utilisateur saisisse la commande "/exit"
            do {
                
                if ( clientName.equals("?") ) {
                    // l'utilisateur peut définir son pseudo
                    System.out.println("Entrez votre nom : ");
                    userInput = scanner.nextLine();
                    clientName = userInput;
                    output.println(userInput + " s'est connecté-e."); // impression dans le flux de sortie du socket client.

                    if( userInput.equals("/exit") ) {
                        break;
                    }
                
                } else {
                    // on récupère le message que l'utilisateur veut envoyer
                    String message = ( clientName + " :  " );
                    //System.out.println(message);
                    userInput = scanner.nextLine();
                    output.println(message + " " + userInput); // impression dans le flux de sortie du socket client.

                    if( userInput.equals("/exit") ) {
                        break;
                    }
                }

            } while(!userInput.equals("/exit"));

            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
