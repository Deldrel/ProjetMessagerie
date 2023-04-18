import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner sc = new Scanner(System.in);
            String line = null;

            User user = new User();


            //initialisation of elements
            //--------------------------------frame
            JFrame frame = new JFrame("Chat");
            frame.setSize(1200, 800);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);
            //--------------------------------panel
            JPanel panelLogin = new JPanel();
            panelLogin.setLayout(null);
            JPanel panelChat = new JPanel();
            panelChat.setLayout(null);
            //--------------------------------TabbedPaned
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.setSize(1200, 800);

            //--------------------------------panel Login/label
            JLabel labelPseudo = new JLabel("Pseudo");
            labelPseudo.setBounds(450, 10, 200, 30);
            JLabel labelPassword = new JLabel("Password");
            labelPassword.setBounds(450, 60, 200, 30);
            //--------------------------------panel Chat/label
            JLabel labelFriends = new JLabel("Friends");
            labelFriends.setBounds(5, 10, 200, 30);
            JLabel labelChat = new JLabel("Chat");
            labelChat.setBounds(250, 10, 200, 30);

            //--------------------------------panel Login/text field
            JTextField textFieldUsername = new JTextField(50);
            textFieldUsername.setBounds(550, 10, 200, 30);
            user.setUsername(textFieldUsername.getText());

            JTextField textFieldPassword = new JTextField(50);
            textFieldPassword.setBounds(550, 60, 200, 30);
            user.setPassword(textFieldPassword.getText());
            //--------------------------------panel Chat/text field
            JTextField textFieldChat = new JTextField(50);
            textFieldChat.setBounds(250, 700, 800, 30);

            //--------------------------------panel Login/button
            JButton buttonLogin = new JButton("Login");
            buttonLogin.setBounds(500, 150, 200, 30);
            buttonLogin.addActionListener(e -> {
                out.println("login "+textFieldUsername.getText()+" "+textFieldPassword.getText());
                out.flush();
                try {
                    System.out.println("Server replied " + in.readLine());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            //create new account and action listener
            JButton buttonNewAccount = new JButton("New Account");
            buttonNewAccount.setBounds(500, 200, 200, 30);
            buttonNewAccount.addActionListener(e -> {
                //create new account
                JDialog dialog = new JDialog(frame, "New Account", true);
                dialog.setLayout(null);
                JLabel labelFirtnameNewAccount = new JLabel("Firstname");
                labelFirtnameNewAccount.setBounds(100, 10, 100, 30);
                JLabel labelLastnameNewAccount = new JLabel("Lastname");
                labelLastnameNewAccount.setBounds(100, 60, 100, 30);
                JLabel labelEmailNewAccount = new JLabel("Email");
                labelEmailNewAccount.setBounds(100, 110, 100, 30);
                JLabel labelUsernameNewAccount = new JLabel("Username");
                labelUsernameNewAccount.setBounds(100, 160, 100, 30);
                JLabel labelPasswordNewAccount = new JLabel("Password");
                labelPasswordNewAccount.setBounds(100, 210, 100, 30);
                JTextField textFieldFirstnameNewAccount = new JTextField(50);
                textFieldFirstnameNewAccount.setBounds(210, 10, 200, 30);
                JTextField textFieldLastnameNewAccount = new JTextField(50);
                textFieldLastnameNewAccount.setBounds(210, 60, 200, 30);
                JTextField textFieldEmailNewAccount = new JTextField(50);
                textFieldEmailNewAccount.setBounds(210, 110, 200, 30);
                JTextField textFieldUsernameNewAccount = new JTextField(50);
                textFieldUsernameNewAccount.setBounds(210, 160, 200, 30);
                JTextField textFieldPasswordNewAccount = new JTextField(50);
                textFieldPasswordNewAccount.setBounds(210, 210, 200, 30);
                JButton buttonCreateNewAccount = new JButton("Create");
                buttonCreateNewAccount.setBounds(190, 300, 100, 30);
                buttonCreateNewAccount.addActionListener(e1 -> {
                    User newUser = new User();
                    newUser.setFirstname(textFieldFirstnameNewAccount.getText());
                    newUser.setLastname(textFieldLastnameNewAccount.getText());
                    newUser.setEmail(textFieldEmailNewAccount.getText());
                    newUser.setUsername(textFieldUsernameNewAccount.getText());
                    newUser.setPassword(textFieldPasswordNewAccount.getText());
                    VueUser vueUser = new VueUser();
                    ControlUser controlUser = new ControlUser(newUser,vueUser);
                    out.println(vueUser.newAccountToString(newUser));
                    out.flush();
                    System.out.println(newUser.toString());
                    dialog.dispose();
                });

                dialog.setSize(500, 500);
                dialog.setLocationRelativeTo(null);
                dialog.add(labelFirtnameNewAccount);
                dialog.add(textFieldFirstnameNewAccount);
                dialog.add(labelLastnameNewAccount);
                dialog.add(textFieldLastnameNewAccount);
                dialog.add(labelEmailNewAccount);
                dialog.add(textFieldEmailNewAccount);
                dialog.add(labelUsernameNewAccount);
                dialog.add(textFieldUsernameNewAccount);
                dialog.add(labelPasswordNewAccount);
                dialog.add(textFieldPasswordNewAccount);
                dialog.add(buttonCreateNewAccount);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            });

            //--------------------------------panel Chat/button
            JButton buttonSend = new JButton("Send");
            buttonSend.setBounds(1060, 700, 100, 30);

            //add elements to panel Login
            panelLogin.add(labelPseudo);
            panelLogin.add(labelPassword);
            panelLogin.add(textFieldUsername);
            panelLogin.add(textFieldPassword);
            panelLogin.add(buttonLogin);
            panelLogin.add(buttonNewAccount);
            //add elements to panel Chat
            panelChat.add(labelFriends);
            panelChat.add(labelChat);
            panelChat.add(textFieldChat);
            panelChat.add(buttonSend);


            //add elements to tabbed pane
            tabbedPane.add("Login", panelLogin);
            tabbedPane.add("Chat", panelChat);


            //show panel in the frame
            frame.add(tabbedPane);


            frame.setVisible(true);



            while (!"exit".equalsIgnoreCase(line)) {
                line = sc.nextLine();
                out.println(line);
                out.flush();
                System.out.println("Server replied " + in.readLine());
            }

            socket.close();
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

