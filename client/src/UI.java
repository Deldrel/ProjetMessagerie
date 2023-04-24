import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * This class is never used
 */

public class UI {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = (int) (WIDTH * (9f / 16));
    private static final int MAXMESSAGE = 16;
    private static final int TIMER_DELAY = 100;

    private final PrintWriter out;
    private final BufferedReader in;
    private String line;
    private JFrame chatFrame, loginFrame;
    private JPanel liveChat;

    public UI(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
        init();
    }

    public void init() {
        loginFrame();
        chatFrame();
        new Timer(TIMER_DELAY, e -> update()).start();
    }

    public void update() {
        updateChat();
    }

    public void loginFrame() {
        final int componentHeight = 10;
        final int Y = (int) (componentHeight + componentHeight / 2.0);

        Pos pseudopos = new Pos(20, 2 * Y, 25, componentHeight);
        Pos passwordpos = new Pos(20, 3 * Y, 25, componentHeight);
        Pos usernamepos = new Pos(35, 2 * Y, 30, componentHeight);
        Pos userpasswordpos = new Pos(35, 3 * Y, 30, componentHeight);
        Pos loginpos = new Pos(35, 4 * Y, 30, componentHeight);
        Pos newaccountpos = new Pos(35, 5 * Y, 30, componentHeight);

        loginFrame = UIutils.createJFrame("Login", WIDTH, HEIGHT);
        JTabbedPane pane = UIutils.createJTabbedPane(WIDTH, HEIGHT);
        JPanel panel = UIutils.createJPanel();

        JLabel logo = UIutils.createJLabel("", WIDTH / 2 - 50, 10, 128, 128);
        logo.setIcon(new ImageIcon("Images/bot.png"));

        JLabel pseudo = UIutils.createJLabel("Username : ", pseudopos.x, pseudopos.y, pseudopos.w, pseudopos.h);
        JLabel password = UIutils.createJLabel("Password : ", passwordpos.x, passwordpos.y, passwordpos.w, passwordpos.h);

        JTextField username = UIutils.createJTextField(usernamepos.x, usernamepos.y, usernamepos.w, usernamepos.h);

        JPasswordField userpassword = new JPasswordField(50);
        userpassword.setBounds(userpasswordpos.x, userpasswordpos.y, userpasswordpos.w, userpasswordpos.h);
        userpassword.setBackground(UIutils.BACKGROUND_TEXTFIELD_COLOR);
        userpassword.setForeground(Color.WHITE);
        userpassword.setBorder(BorderFactory.createLineBorder(UIutils.BACKGROUND_TEXTFIELD_BORDER_COLOR, 1));

        JButton login = UIutils.createJButton("Login", loginpos.x, loginpos.y, loginpos.w, loginpos.h);
        login.addActionListener(e -> login(username.getText(), String.valueOf(userpassword.getPassword())));

        JButton newaccount = UIutils.createJButton("New Account", newaccountpos.x, newaccountpos.y, newaccountpos.w, newaccountpos.h);
        newaccount.addActionListener(e -> newaccountDialog(loginFrame));

        panel.add(logo);
        panel.add(pseudo);
        panel.add(password);
        panel.add(username);
        panel.add(userpassword);
        panel.add(login);
        panel.add(newaccount);

        pane.add("Login", panel);

        loginFrame.add(pane);
        loginFrame.setVisible(true);
        loginFrame.setResizable(false);
    }

    public void chatFrame() {
        Pos chatpos = new Pos(20, 80, 60, 10);
        Pos sendpos = new Pos(80, 80, 10, 10);

        chatFrame = UIutils.createJFrame("Chat", WIDTH, HEIGHT);
        JTabbedPane pane = UIutils.createJTabbedPane(WIDTH, HEIGHT);
        liveChat = UIutils.createJPanel();
        liveChat.setBackground(UIutils.BACKGROUND_COLOR);

        JTextField chat = UIutils.createJTextField(chatpos.x, chatpos.y, chatpos.w, chatpos.h);

        JButton send = UIutils.createJButton("Send", sendpos.x, sendpos.y, sendpos.w, sendpos.h);
        send.addActionListener(e -> sendMessage(chat.getText()));

        JButton logout = UIutils.createJButton("", WIDTH - 60, 10, 32, 32);
        logout.setIcon(new ImageIcon("Images/se-deconnecter.png"));
        logout.setContentAreaFilled(false);
        logout.setBorderPainted(false);
        logout.setFocusPainted(false);
        logout.addActionListener(e -> logout());

        liveChat.add(chat);
        liveChat.add(send);
        liveChat.add(logout);

        pane.add("Chat", liveChat);

        chatFrame.add(pane);
        chatFrame.setResizable(false);
    }

    public void login(String username, String password) {
        System.out.println(username + " " + password);
        out.println("login " + username + " " + password);
        out.flush();
        try {
            line = in.readLine();
            if (line != null) {
                System.out.println("Server replied " + line);
                String[] words = line.split(" ");
                if (Objects.equals(words[1], "success")) {
                    loginFrame.dispose();
                    chatFrame.setVisible(true);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void newaccountDialog(JFrame frame) {
        final int componentHeight = 8;
        final int Y = (int) (componentHeight + componentHeight / 2.0);

        Pos labelfirstnamepos = new Pos(20, 2 * Y, 25, componentHeight);
        Pos labellastnamepos = new Pos(20, 3 * Y, 25, componentHeight);
        Pos labelemailpos = new Pos(20, 4 * Y, 25, componentHeight);
        Pos labelusernamepos = new Pos(20, 5 * Y, 25, componentHeight);
        Pos labelpasswordpos = new Pos(20, 6 * Y, 25, componentHeight);
        Pos textfieldfirstnamepos = new Pos(35, 2 * Y, 30, componentHeight);
        Pos textfieldlastnamepos = new Pos(35, 3 * Y, 30, componentHeight);
        Pos textfieldemailpos = new Pos(35, 4 * Y, 30, componentHeight);
        Pos textfieldusernamepos = new Pos(35, 5 * Y, 30, componentHeight);
        Pos textfieldpasswordpos = new Pos(35, 6 * Y, 30, componentHeight);
        Pos buttoncreatepos = new Pos(35, 7 * Y, 30, componentHeight);

        JDialog dialog = new JDialog(frame, "New Account", true);
        dialog.setLayout(null);

        JLabel labelWellcome = UIutils.createJLabel("",WIDTH / 2 - 64, 10, 128, 128);
        ImageIcon icon = new ImageIcon("Images/welcome-back.png");
        labelWellcome.setIcon(icon);

        JLabel labelFirtnameNewAccount = UIutils.createJLabel("Firstname",labelfirstnamepos.x, labelfirstnamepos.y, labelfirstnamepos.w, labelfirstnamepos.h);
        JLabel labelLastnameNewAccount = UIutils.createJLabel("Lastname", labellastnamepos.x, labellastnamepos.y, labellastnamepos.w, labellastnamepos.h);
        JLabel labelEmailNewAccount = UIutils.createJLabel("Email", labelemailpos.x, labelemailpos.y, labelemailpos.w, labelemailpos.h);
        JLabel labelUsernameNewAccount = UIutils.createJLabel("Username", labelusernamepos.x, labelusernamepos.y, labelusernamepos.w, labelusernamepos.h);
        JLabel labelPasswordNewAccount = UIutils.createJLabel("Password", labelpasswordpos.x, labelpasswordpos.y, labelpasswordpos.w, labelpasswordpos.h);

        JTextField textFieldFirstnameNewAccount = UIutils.createJTextField(textfieldfirstnamepos.x, textfieldfirstnamepos.y, textfieldfirstnamepos.w, textfieldfirstnamepos.h);
        JTextField textFieldLastnameNewAccount = UIutils.createJTextField(textfieldlastnamepos.x, textfieldlastnamepos.y, textfieldlastnamepos.w, textfieldlastnamepos.h);
        JTextField textFieldEmailNewAccount = UIutils.createJTextField(textfieldemailpos.x, textfieldemailpos.y, textfieldemailpos.w, textfieldemailpos.h);
        JTextField textFieldUsernameNewAccount = UIutils.createJTextField(textfieldusernamepos.x, textfieldusernamepos.y, textfieldusernamepos.w, textfieldusernamepos.h);

        JPasswordField textFieldPasswordNewAccount = new JPasswordField(50);
        textFieldPasswordNewAccount.setBounds(textfieldpasswordpos.x, textfieldpasswordpos.y, textfieldpasswordpos.w, textfieldpasswordpos.h);
        textFieldPasswordNewAccount.setBackground(UIutils.BACKGROUND_TEXTFIELD_COLOR);
        textFieldPasswordNewAccount.setForeground(Color.WHITE);
        Border borderPassword = BorderFactory.createLineBorder(UIutils.BACKGROUND_TEXTFIELD_BORDER_COLOR, 1);
        textFieldPasswordNewAccount.setBorder(borderPassword);

        JButton buttonCreateNewAccount = UIutils.createJButton("Create", buttoncreatepos.x, buttoncreatepos.y, buttoncreatepos.w, buttoncreatepos.h);
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

        dialog.getContentPane().setBackground(UIutils.BACKGROUND_COLOR);
        dialog.setSize(WIDTH, HEIGHT);
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
    }

    public void logout() {
        out.println("logout");
        out.flush();
        try {
            line = in.readLine();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Server replied " + line);
        chatFrame.dispose();
        loginFrame.setVisible(true);
    }

    public void sendMessage(String message) {
        out.println("sendMessage " + message);
        out.flush();
        try {
            line = in.readLine();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Server replied " + line);
    }

    public void updateChat() {
        ArrayList<String> messages = new ArrayList<>();

        try {
            out.println("getCurrentUserInfo");
            out.flush();
            line = in.readLine();
            if (line != null && !line.endsWith("error")) {
                String[] userInfos = line.split(" ");
                int id = Integer.parseInt(userInfos[1]);
            }

            out.println("getMessages " + MAXMESSAGE);
            out.flush();
            line = in.readLine();
            if (line != null && !line.endsWith("error")) {
                String[] messagesInfos = line.split("&");
                Collections.addAll(messages, messagesInfos);

                for (int i = messages.size() - 1; i >= 0; i--) {
                    String[] words = messages.get(i).split("#");
                    final int componentHeight = 4;
                    final int Y = (int) (componentHeight + componentHeight / 2.0);
                    Pos pos = new Pos(20, i * Y, 60, componentHeight);
                    JLabel label = UIutils.createJLabel(words[3], pos.x, pos.y, pos.w, pos.h);
                    label.setHorizontalTextPosition(JLabel.LEFT);
                    liveChat.add(label);
                }
            }

            liveChat.repaint();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static class Pos {
        public int x, y, w, h;

        public Pos(int x, int y, int width, int height) {
            this.x = (int) (x * WIDTH / 100.0);
            this.y = (int) (y * HEIGHT / 100.0);
            this.w = (int) (width * WIDTH / 100.0);
            this.h = (int) (height * HEIGHT / 100.0);
        }

        public void setX(int x) {
            this.x = (int) (x * WIDTH / 100.0);
        }

        public void setY(int y) {
            this.y = (int) (y * HEIGHT / 100.0);
        }

        public void setW(int w) {
            this.w = (int) (w * WIDTH / 100.0);
        }

        public void setH(int h) {
            this.h = (int) (h * HEIGHT / 100.0);
        }
    }
}
