import java.io.*;
import java.net.*;
import java.time.Duration;
import java.util.Scanner;
import java.sql.*;

//javadoc comment
/**
 * Server class
 * <p>
 * This class is the main class of the server.
 * It creates a server socket and waits for new connections.
 * It also handles the commands from the console.
*/

public class Server {
    private static ServerSocket server = null;

    public static void main(String[] args) {
        try {
            server = new ServerSocket(1234);
            server.setReuseAddress(true);

            Database.connect();
            Database.recreateDatabase(); //DETRUIT ET RECREER LA DATABASE, TOUTE MODIF MANUELLE ANNULEE

            UserDAO.add(new User(-1, "Deldrel", "Pedro", "LAPOLLA", "pedro.lap@edu.ece.fr", "aaaa", 1, Duration.ZERO, 0));
            UserDAO.add(new User(-1, "Rouxie", "PE", "DAUGUETTE", "pe.daug@edu.ece.fr", "bbbb", 1, Duration.ZERO, 0));
            UserDAO.add(new User(-1, "RiderSurfer", "Oscar", "CHAUVEREN", "oscar.chauve@edu.ece.fr", "cccc", 1, Duration.ZERO, 0));
            UserDAO.add(new User(-1, "Aurel3412", "Aurelie", "SENOYER", "aurelie.senoye@edu.ece.fr", "dddd", 1, Duration.ZERO, 0));
            UserDAO.add(new User(-1, "Lucidre", "Lucie", "KASPER", "lucidre.kasper@edu.ece.fr", "eeee", 3, Duration.ZERO, 0));
            MessageDAO.add(new Message(0, 1, new Timestamp(System.currentTimeMillis()), "Hello World!"));
            MessageDAO.add(new Message(0, 2, new Timestamp(System.currentTimeMillis()), "World Hello!"));

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

    /**
     * This method is called when a new connection is established.
     * @throws IOException
     */
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
            case "log" -> {
                LogDAO.add(new Log(0, 0, new Timestamp(System.currentTimeMillis()), "Server: log"));
                LogDAO.printAllLogs();
                return 1;
            }
            default -> {
                System.out.println("Unknown command");
                return -1;
            }
        }
    }
}
