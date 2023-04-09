// https://www.geeksforgeeks.org/multithreaded-servers-in-java/

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    private static ServerSocket server = null;

    public static void main(String[] args) {
        try {
            server = new ServerSocket(1234);
            server.setReuseAddress(true);

            Database.connect();
            Database.recreateDatabase(); //DETRUIT ET RECREER LA DATABASE, TOUTE MODIF MANUELLE ANNULEE

            UserDAO userDAO = new UserDAO(Database.getConnection());
            userDAO.add(new User());
            userDAO.add(new User());

            System.out.println(userDAO.get(0));
            System.out.println(userDAO.get(1));

            userDAO.modifyUserField(0, "username", "pedro");
            userDAO.modifyUserField(0, "password", "1234");

            System.out.println(userDAO.get(0));
            System.out.println(userDAO.get(1));

            new Thread(() -> {
                try {
                    newConnectionHandler();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            Scanner sc = new Scanner(System.in);
            String command = "";
            while (commandHandler(command) != 0) {
                command = sc.nextLine();
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

    public static void newConnectionHandler() throws IOException {
        while (true) {
            Socket client = server.accept();
            System.out.println("New client connected " + client.getInetAddress().getHostAddress());
            ClientHandler clientSock = new ClientHandler(client);
            new Thread(clientSock).start();
        }
    }

    public static int commandHandler(String command) {
        switch (command) {
            case "exit" -> {
                return 0;
            }
            case "help" -> {
                System.out.println("help");
                return 1;
            }
            default -> {
                System.out.println("Unknown command");
                return -1;
            }
        }
    }
}
