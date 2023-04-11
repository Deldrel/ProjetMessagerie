// https://www.geeksforgeeks.org/multithreaded-servers-in-java/

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.sql.*;

public class Server {
    private static ServerSocket server = null;

    public static void main(String[] args) {
        try {
            server = new ServerSocket(1234);
            server.setReuseAddress(true);

            Database.connect();
            Database.recreateDatabase(); //DETRUIT ET RECREER LA DATABASE, TOUTE MODIF MANUELLE ANNULEE

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
            LogDAO.add(new Log(0, 0, new Timestamp(System.currentTimeMillis()), "Connection: new connection"));
            ClientHandler clientSock = new ClientHandler(client);
            new Thread(clientSock).start();
        }
    }

    public static int commandHandler(String command) {
        switch (command) {
            case "exit" -> {
                LogDAO.add(new Log(0, 0, new Timestamp(System.currentTimeMillis()), "Server: shutdown"));
                return 0;
            }
            case "help" -> {
                LogDAO.add(new Log(0, 0, new Timestamp(System.currentTimeMillis()), "Server: help"));
                return 1;
            }
            default -> {
                System.out.println("Unknown command");
                return -1;
            }
        }
    }
}
