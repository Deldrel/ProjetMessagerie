
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import static java.lang.System.out;


public class Interface {
    PrintWriter out;
    BufferedReader in;


    User user = new User();
    private static final Color BACKGROUND_COLOR = Color.decode("#0B194A");
    private static final Color BACKGROUND_BUTTON_COLOR = Color.decode("#522ED3");
    private static final Color BACKGROUND_BUTTON_SURVOL_COLOR = Color.decode("#522ED3");
    private static final Color BACKGROUND_BUTTON_SURVOL_CLICK = Color.decode("#1B2956");

    private static final int MAXMESSAGE = 16;

    private static final Color BACKGROUND_BUTTON_BORDER_COLOR = Color.BLACK;
    private static final Color BACKGROUND_TEXTFIELD_COLOR = Color.decode("#1B2956");
    private static final Color BACKGROUND_TEXTFIELD_BORDER_COLOR = Color.decode("#212F60");
    private static final Color BACKGROUND_LABEL_COLOR = Color.decode("#7688BA");

    public JFrame frameLogin = createJFrame("Login", 800, 400);
    public JFrame frameChat = createJFrame("Chat", 1200, 650);

    public JPanel panelChat = createJPanel();
    public JPanel panelLogin = createJPanel();

    int id = 0;

    String line;
    ArrayList<JLabel> Message = new ArrayList<>();

    ArrayList<String> TabMessage = new ArrayList<>();

    JLabel labelPseudo = createJLabel("Username : ", 30, 30, 200, 30);


    private JFrame createJFrame(String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        return frame;
    }

    // Fonction pour créer et configurer un JPanel
    private JPanel createJPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND_COLOR);
        return panel;
    }

    // Fonction pour créer et configurer un JTabbedPane
    private JTabbedPane createJTabbedPane(int width, int height) {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setSize(width, height);
        return tabbedPane;
    }

    // Fonction pour créer et configurer un JLabel
    private JLabel createJLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setForeground(BACKGROUND_LABEL_COLOR);
        return label;
    }

    // Fonction pour créer et configurer un JTextField
    private JTextField createJTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField(50);
        textField.setBounds(x, y, width, height);
        textField.setBackground(BACKGROUND_TEXTFIELD_COLOR);
        textField.setForeground(Color.WHITE);
        Border border = BorderFactory.createLineBorder(BACKGROUND_TEXTFIELD_BORDER_COLOR, 1);
        textField.setBorder(border);
        return textField;
    }

    // Fonction pour créer et configurer un JButton
    private JButton createJButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setForeground(Color.WHITE);
        button.setBackground(BACKGROUND_BUTTON_COLOR);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFont(new Font("Dialog", Font.BOLD, 14));
        Border border= BorderFactory.createLineBorder(BACKGROUND_TEXTFIELD_BORDER_COLOR, 1);
        button.setBorder(border);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(BACKGROUND_BUTTON_SURVOL_CLICK);
                Border border= BorderFactory.createLineBorder(BACKGROUND_BUTTON_COLOR, 2);
                button.setBorder(border);
                button.setFont(new Font("Dialog", Font.BOLD, 14));
                button.setForeground(BACKGROUND_BUTTON_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(BACKGROUND_BUTTON_SURVOL_COLOR);
                button.setForeground(Color.WHITE);
                Border border= BorderFactory.createLineBorder(BACKGROUND_TEXTFIELD_BORDER_COLOR, 1);
                button.setBorder(border);


            }
        });
        return button;
    }

    private void addActionButton(JButton button, ActionListener actionListener) {
        button.addActionListener(actionListener);
    }

    public void actionListenerNewAccount(JButton buttonNewAccount, JFrame frame) {
        buttonNewAccount.addActionListener(e -> {
            System.out.println("New Account");
            JDialog dialog = new JDialog(frame, "New Account", true);
            dialog.setLayout(null);

            JLabel labelWellcome = createJLabel("",215,1,128,128);
            ImageIcon icon = new ImageIcon("Images/welcome-back.png");
            labelWellcome.setIcon(icon);

            JLabel labelFirtnameNewAccount = createJLabel("Firstname",100,140,100,30);
            JLabel labelLastnameNewAccount = createJLabel("Lastname",100,190,100,30);
            JLabel labelEmailNewAccount = createJLabel("Email",100,240,100,30);
            JLabel labelUsernameNewAccount = createJLabel("Username",100,290,100,30);
            JLabel labelPasswordNewAccount = createJLabel("Password",100,340,100,30);

            JTextField textFieldFirstnameNewAccount = createJTextField(180,140,220,30);
            JTextField textFieldLastnameNewAccount = createJTextField(180,190,220,30);
            JTextField textFieldEmailNewAccount = createJTextField(180,240,220,30);
            JTextField textFieldUsernameNewAccount = createJTextField(180,290,220,30);

            JPasswordField textFieldPasswordNewAccount = new JPasswordField(50);
            textFieldPasswordNewAccount.setBounds(180, 340, 220, 30);
            textFieldPasswordNewAccount.setBackground(BACKGROUND_TEXTFIELD_COLOR);
            textFieldPasswordNewAccount.setForeground(Color.WHITE);
            Border borderPassword = BorderFactory.createLineBorder(BACKGROUND_TEXTFIELD_BORDER_COLOR, 1);
            textFieldPasswordNewAccount.setBorder(borderPassword);

            JButton buttonCreateNewAccount = createJButton("Create",220,400,100,30);

            actionListenerCreateNewAccount(buttonCreateNewAccount, textFieldFirstnameNewAccount, textFieldLastnameNewAccount, textFieldEmailNewAccount, textFieldUsernameNewAccount, textFieldPasswordNewAccount, dialog);
            dialog.getContentPane().setBackground(BACKGROUND_COLOR);
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
            dialog.add(labelWellcome);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        });
    }


    public void actionListenerCreateNewAccount(JButton buttonCreateNewAccount, JTextField textFieldFirstnameNewAccount, JTextField textFieldLastnameNewAccount, JTextField textFieldEmailNewAccount, JTextField textFieldUsernameNewAccount, JPasswordField textFieldPasswordNewAccount, JDialog dialog) {
        buttonCreateNewAccount.addActionListener(e1 -> {
            User newUser = new User();
            newUser.setFirstname(textFieldFirstnameNewAccount.getText());
            newUser.setLastname(textFieldLastnameNewAccount.getText());
            newUser.setEmail(textFieldEmailNewAccount.getText());
            newUser.setUsername(textFieldUsernameNewAccount.getText());
            newUser.setPassword(textFieldPasswordNewAccount.getText());
            VueUser vueUser = new VueUser();
            ControlUser controlUser = new ControlUser(newUser, vueUser);
            out.println(vueUser.newAccountToString(newUser));
            out.flush();
            try {
                System.out.println("Server replied" + in.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            dialog.dispose();
        });
    }

    public void actionListenerSend(JButton buttonSend, JTextField textFieldChat) {
        buttonSend.addActionListener(e -> {
            out.println("sendMessage " + textFieldChat.getText());
            out.flush();
            try {
                line = in.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Server replied " + line);


            UpdateFramChat();




        });
    }

    public void actionListenerLogin(JButton buttonLogin, JTextField textFieldUsername, JPasswordField textFieldPassword) {
        buttonLogin.addActionListener(e -> {
            out.println("login " + textFieldUsername.getText() + " " + textFieldPassword.getText());
            out.flush();
            try {
                line = in.readLine();
                if (line != null) {
                    System.out.println("Server replied " + line);
                    String[] words = line.split(" ");
                    if (Objects.equals(words[1], "success")) {
                        UpdateFramChat();
                        frameLogin.dispose();
                        frameChat.setVisible(true);
                    }
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });
    }

    public Interface(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
        for (int i = 0; i < MAXMESSAGE; i++) {
            JLabel newJlabel = createJLabel("", 800, 500, 200, 30);
            Message.add(newJlabel);
        }
    }

    public void actionListenerDeco(JButton buttonDeco) {
        buttonDeco.addActionListener(e -> {
            out.println("logout");
            out.flush();
            try {
                line = in.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Server replied " + line);
            frameChat.dispose();
            frameLogin.setVisible(true);
        });
    }

    public void ClearFramChat() {
        panelChat.remove(labelPseudo);
        TabMessage.clear();
        for (int i = 0; i < Message.size(); i++) {
            panelChat.remove(Message.get(i));
        }
    }

    public void UpdateFramChat() {
        ClearFramChat();
        out.println("getCurrentUserInfo");
        out.flush();
        try {
            line = in.readLine();
            String[] words = line.split(" ");
            labelPseudo.setText(words[0]);
            id = Integer.parseInt(words[1]);
            System.out.println(id);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        panelChat.add(labelPseudo);


        try {
            // id#userId#username"time(date heure) content |
            out.println("getMessages " + MAXMESSAGE);
            out.flush();
            line = in.readLine();
            String[] words = line.split("&");
            Collections.addAll(TabMessage, words);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        afficherMessage();
        panelChat.repaint();
    }

    public void afficherMessage() {
        int i = TabMessage.size()-1;
        int fin = 0;
        int j=0;
        int l=0;
        if (TabMessage.size() > MAXMESSAGE) {
            fin = TabMessage.size()-MAXMESSAGE;
        }
        if (TabMessage.size() < MAXMESSAGE){
            fin = 0;
        }
        try {
            for (i = TabMessage.size()-1; i > fin; i--) {
                String[] words = TabMessage.get(i).split("#");

                if (id == Integer.parseInt(words[0])) {
                    Message.get(j).setText(words[1] + " " + words[2] + ":");
                    Message.get(j).setBounds(800, 385 - (l * 50), 200, 30);
                    Message.get(j+1).setText(words[3]);
                    Message.get(j+1).setBounds(800, 400 - (l * 50), 200, 30);
                    panelChat.add(Message.get(j));
                    panelChat.add(Message.get(j+1));
                } else {
                    Message.get(j).setText(words[1] + " " + words[2] + ":");
                    Message.get(j).setBounds(400, 385 - (l * 50), 200, 30);
                    Message.get(j+1).setText(words[3]);
                    Message.get(j+1).setBounds(400, 400 - (l * 50), 200, 30);
                    panelChat.add(Message.get(j));
                    panelChat.add(Message.get(j+1));
                }
                j++;
                j++;
                l++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void frameLogin(JFrame frameLogin) {
        // Panels
        //JPanel panelLogin = createJPanel();

        // Tabbed pane
        JTabbedPane paneLogin = createJTabbedPane(800, 500);

        JLabel labelLogo = createJLabel("",340,10,128,128);
        ImageIcon icon = new ImageIcon("Images/bot.png");
        labelLogo.setIcon(icon);


        // panelLogin  / label
        JLabel labelPseudo = createJLabel("Username : ", 200, 150, 200, 30);
        JLabel labelPassword = createJLabel("Password : ", 200, 200, 200, 30);
        labelPassword.setForeground(BACKGROUND_LABEL_COLOR);


        // panelLogin / textField
        JTextField textFieldUsername = createJTextField(300, 150, 200, 30);
        user.setUsername(textFieldUsername.getText());

        JPasswordField textFieldPassword = new JPasswordField(50);
        textFieldPassword.setBounds(300, 200, 200, 30);
        user.setPassword(String.valueOf(textFieldPassword.getPassword()));
        textFieldPassword.setBackground(BACKGROUND_TEXTFIELD_COLOR);
        textFieldPassword.setForeground(Color.WHITE);
        Border borderPassword = BorderFactory.createLineBorder(BACKGROUND_TEXTFIELD_BORDER_COLOR, 1);
        textFieldPassword.setBorder(borderPassword);

        // panelLogin / bouton
        //------------button login
        JButton buttonLogin = createJButton("Login", 300, 250, 200, 30);
        actionListenerLogin(buttonLogin, textFieldUsername, textFieldPassword);

        //------------button new account
        JButton buttonNewAccount = createJButton("New Account", 300, 300, 200, 30);
        actionListenerNewAccount(buttonNewAccount, frameLogin);

        // panelLogin / add elements
        panelLogin.add(labelLogo);
        panelLogin.add(labelPseudo);
        panelLogin.add(labelPassword);
        panelLogin.add(textFieldUsername);
        panelLogin.add(textFieldPassword);
        panelLogin.add(buttonLogin);
        panelLogin.add(buttonNewAccount);

        // add elements to tabbed Pane
        paneLogin.add("Login", panelLogin);

        //add elements in the frameLogin
        frameLogin.add(paneLogin);
        frameLogin.setVisible(true);
    }

    public void frameChat(JFrame frameChat) {
        // Panels
        //JPanel panelChat = createJPanel();

        // Tabbed pane
        JTabbedPane paneChat = createJTabbedPane(1200, 650);

        // panelChat / label

        // panelChat / textField
        JTextField textFieldChat = createJTextField(250, 490, 790, 50);

        // panelChat / bouton
        //------------button send
        JButton buttonSend = createJButton("Send", 1060, 500, 100, 30);
        JButton buttonDeco = createJButton("", 1100, 30, 40, 40);
        actionListenerSend(buttonSend, textFieldChat);

        buttonDeco.setIcon(new ImageIcon("Images/se-deconnecter.png"));
        buttonDeco.setContentAreaFilled(false);
        buttonDeco.setBorderPainted(false);
        buttonDeco.setFocusPainted(false);
        actionListenerDeco(buttonDeco);

        // panelChat / add elements

        panelChat.add(textFieldChat);
        panelChat.add(buttonSend);
        panelChat.add(buttonDeco);


        // add elements to tabbed Pane
        paneChat.add("Chat", panelChat);

        //add elements in the frameChat
        frameChat.add(paneChat);
        //frameChat.setVisible(true);
    }

    public void createInterface() {
        // Frame
        frameLogin(frameLogin);
        frameChat(frameChat);
    }


}