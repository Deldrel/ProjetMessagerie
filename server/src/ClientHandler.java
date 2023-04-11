// https://www.geeksforgeeks.org/multithreaded-servers-in-java/

import java.io.*;
import java.net.*;
import java.sql.Timestamp;
import java.time.Duration;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private boolean loggedin = false;
    private int user_id = -1;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line;

            while ((line = in.readLine()) != null) {
                String[] words = line.split(" ");
                StringBuilder message = new StringBuilder();
                for (int i = 1; i < words.length; i++) {
                    message.append(words[i]).append(" ");
                }

                LogDAO.add(new Log(0, 0, new Timestamp(System.currentTimeMillis()), "Client: " + line));

                switch (words[0]) {
                    case "message" -> {
                        if (loggedin) {
                            MessageDAO.add(new Message(0, user_id, new Timestamp(System.currentTimeMillis()), message.toString()));
                            out.println("message sent");
                        } else {
                            out.println("message error");
                        }
                    }
                    case "login" -> {
                        out.println(login(words[1], words[2]));
                    }
                    case "logout" -> {
                        if (loggedin) {
                            loggedin = false;
                            user_id = -1;
                            out.println("logout success");
                        } else {
                            out.println("logout error");
                        }
                    }
                    case "newaccount" -> {
                        UserDAO.add(new User(0, words[1], words[2], words[3], words[4], words[5], -1, Duration.ZERO));
                        out.println("newaccount success");
                    }
                    default -> {
                        //do nothing
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (out != null)
                    out.close();
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String login(String username, String password) {
        try {
            User auth = UserDAO.get(username, "username");
            if (auth != null && auth.getPassword().equals(UserDAO.sha1(password))) {
                loggedin = true;
                user_id = auth.getId();
                return "login success";
            } else {
                return "login error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}