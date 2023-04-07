// https://www.geeksforgeeks.org/multithreaded-servers-in-java/

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        ServerSocket server = null;

        try {
            server = new ServerSocket(1234);
            server.setReuseAddress(true);

            Database.connect();
            Database.recreateDatabase(); //DETRUIT ET RECREER LA DATABASE TOUTE MODIF MANUELLE ANNULEE
            UserDAO userDAO = new UserDAO(Database.getConnection());

            userDAO.add(new User(1, "John"));
            userDAO.add(new User(2, "Jane"));
            System.out.println(userDAO.get(1));
            System.out.println(userDAO.get(2));

            while (true) {
                Socket client = server.accept();
                System.out.println("New client connected " + client.getInetAddress().getHostAddress());
                ClientHandler clientSock = new ClientHandler(client);
                new Thread(clientSock).start();
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Database.disconnect();
        }
    }
}
