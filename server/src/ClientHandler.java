// https://www.geeksforgeeks.org/multithreaded-servers-in-java/

import java.io.*;
import java.net.*;
import java.sql.Timestamp;
import java.time.Duration;

/**
 * This class handles the client requests and sends the appropriate response.
 * It implements the Runnable interface.
 */

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private boolean loggedin = false;
    private int user_id = -1;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            commandHandler();

        } catch (Exception e) {
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

    /**
     * This method handles the commands and communicates with the client.
     * @throws Exception
     */
    public void commandHandler() throws Exception {
        String line;
        while ((line = in.readLine()) != null) {
            line = line.replaceAll("'", " ").replaceAll("\"", " ");
            String[] words = line.split(" ");


            switch (words[0]) {
                case "sendMessage" -> {
                    LogDAO.add(new Log(0, user_id, new Timestamp(System.currentTimeMillis()), "Client: " + line));
                    if (loggedin) {
                        StringBuilder message = new StringBuilder();
                        for (int i = 1; i < words.length; i++) {
                            message.append(words[i]).append(" ");
                        }

                        MessageDAO.add(new Message(0, user_id, new Timestamp(System.currentTimeMillis()), message.toString()));
                        out.println("sendMessage sent");
                        out.flush();
                    } else {
                        out.println("sendMessage error");
                        out.flush();
                    }
                }
                case "getMessages" -> {

                    if (words.length != 2) {
                        out.println("argument error");
                        out.flush();
                        break;
                    }

                    if (loggedin) {
                        out.println(MessageDAO.getNLastMessages(Integer.parseInt(words[1])));
                        out.flush();
                    } else {
                        out.println("getMessages error");
                        out.flush();
                    }
                }
                case "login" -> {
                    LogDAO.add(new Log(0, user_id, new Timestamp(System.currentTimeMillis()), "Client: " + line));

                    if (words.length != 3) {
                        out.println("argument error");
                        out.flush();
                        break;
                    }

                    out.println(login(words[1], words[2]));
                    out.flush();
                }
                case "logout" -> {
                    LogDAO.add(new Log(0, user_id, new Timestamp(System.currentTimeMillis()), "Client: " + line));

                    if (loggedin) {
                        loggedin = false;
                        user_id = -1;
                        out.println("logout success");
                        out.flush();
                    } else {
                        out.println("logout error");
                        out.flush();
                    }
                }
                case "newAccount" -> {
                    LogDAO.add(new Log(0, user_id, new Timestamp(System.currentTimeMillis()), "Client: " + line));

                    if (words.length != 9) {
                        out.println("argument error");
                        out.flush();
                        break;
                    }

                    UserDAO.add(new User(0, words[1], words[2], words[3], words[4], words[5], -1, Duration.ZERO, 1));
                    out.println("newAccount success");
                    out.flush();
                }
                case "getUserInfo" -> {
                    if (words.length != 2) {
                        out.println("argument error");
                        out.flush();
                        break;
                    }

                    if (loggedin && UserDAO.get(user_id, "id").getPermission() >= 2) {
                        out.println(UserDAO.get(words[1], "username"));
                        out.flush();
                    } else {
                        out.println("getUserInfo error");
                        out.flush();
                    }
                }
                case "getCurrentUserInfo" -> {
                    if (loggedin) {
                        out.println(UserDAO.get(user_id, "id"));
                        //out.println(UserDAO.get(user_id, "id").getUsername()+" "+UserDAO.get(user_id, "id").getId());
                        out.flush();
                    } else {
                        out.println("getCurrentUserInfo error");
                        out.flush();
                    }
                }
                case "getAllUsers" -> {
                    if (loggedin) {
                        out.println(UserDAO.getAllUsers());
                        out.flush();
                    } else {
                        out.println("getAllUsers error");
                        out.flush();
                    }
                }
                case "ban" -> {
                    LogDAO.add(new Log(0, user_id, new Timestamp(System.currentTimeMillis()), "Client: " + line));

                    if (words.length != 2) {
                        out.println("argument error");
                        out.flush();
                        break;
                    }
                    if (loggedin) {
                        int thispermission = UserDAO.get(user_id, "id").getPermission();
                        int argpermission = UserDAO.get(words[1], "username").getPermission();
                        if (thispermission <= 1 || thispermission <= argpermission) {
                            out.println("ban error");
                            out.flush();
                            break;
                        }
                        UserDAO.modifyUserField(words[1], "permission", 0);
                        out.println("ban success");
                        out.flush();
                    } else {
                        out.println("ban error");
                        out.flush();
                    }
                }
                default -> {
                    out.println("command error");
                    out.flush();}
            }
        }
    }


    /**
     * This method is used to login and verify hashed password.
     * @param username
     * @param password
     * @return String
     */
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