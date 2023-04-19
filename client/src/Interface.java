
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import static java.lang.System.out;



public class Interface {
    User user = new User();
    private static final Color BACKGROUND_COLOR = Color.decode("#081132"   );
    private static final Color BACKGROUND_BUTTON_COLOR = Color.decode("#522ED3");
    private static final Color BACKGROUND_BUTTON_SURVOL_COLOR = Color.decode("#522ED3");
    private static final Color BACKGROUND_BUTTON_SURVOL_CLICK = Color.decode("#78D1D8");

    private static final Color BACKGROUND_BUTTON_BORDER_COLOR = Color.BLACK;

    public JFrame frameLogin = createJFrame("Login", 800, 400);
    public JFrame frameChat = createJFrame("Chat", 1200, 800);


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
        label.setForeground(Color.WHITE);
        return label;
    }

    // Fonction pour créer et configurer un JTextField
    private JTextField createJTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField(50);
        textField.setBounds(x, y, width, height);
        textField.setBackground(Color.decode("#070D24"));
        textField.setForeground(Color.WHITE);
        return textField;
    }

    // Fonction pour créer et configurer un JButton
    private JButton createJButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setForeground(Color.RED);
        button.setBackground(BACKGROUND_BUTTON_COLOR );
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(BACKGROUND_BUTTON_SURVOL_CLICK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(BACKGROUND_BUTTON_SURVOL_COLOR );
            }
        });
        return button;
    }
    private void addActionButton(JButton button, ActionListener actionListener) {
        button.addActionListener(actionListener);
    }

    public void actionListenerNewAccount(JButton buttonNewAccount, PrintWriter out, BufferedReader in, JFrame frame) {
        buttonNewAccount.addActionListener(e -> {
            System.out.println("New Account");
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
            JPasswordField textFieldPasswordNewAccount = new JPasswordField(50);
            textFieldPasswordNewAccount.setBounds(210, 210, 200, 30);
            JButton buttonCreateNewAccount = new JButton("Create");
            buttonCreateNewAccount.setBounds(190, 300, 100, 30);
            actionListenerCreateNewAccount(buttonCreateNewAccount, textFieldFirstnameNewAccount, textFieldLastnameNewAccount, textFieldEmailNewAccount, textFieldUsernameNewAccount, textFieldPasswordNewAccount, dialog, out, in);
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
    }


    public void actionListenerCreateNewAccount(JButton buttonCreateNewAccount, JTextField textFieldFirstnameNewAccount, JTextField textFieldLastnameNewAccount, JTextField textFieldEmailNewAccount, JTextField textFieldUsernameNewAccount, JPasswordField textFieldPasswordNewAccount, JDialog dialog, PrintWriter out, BufferedReader in) {
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

    public void actionListenerLogin(JButton buttonLogin, JTextField textFieldUsername, JPasswordField textFieldPassword, PrintWriter out, BufferedReader in) {
        buttonLogin.addActionListener(e -> {
            frameLogin.dispose();
            frameChat.setVisible(true);
            out.println("login " + textFieldUsername.getText() + " " + textFieldPassword.getPassword());
            out.flush();
            try {
                System.out.println("Server replied" + in.readLine());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });
    }

    public void actionListenerDeco(JButton buttonDeco) {
        buttonDeco.addActionListener(e -> {
            frameChat.dispose();
            frameLogin.setVisible(true);
        });
    }

    public void frameLogin(JFrame frameLogin, PrintWriter out, BufferedReader in){
        // Panels
        JPanel panelLogin = createJPanel();

        // Tabbed pane
        JTabbedPane paneLogin = createJTabbedPane(800, 500);


        // panelLogin  / label
        JLabel labelPseudo = createJLabel("Username : ", 200, 30, 200, 30);
        JLabel labelPassword = createJLabel("Password : ", 200, 80, 200, 30);
        labelPassword.setForeground(Color.WHITE);

        // panelLogin / textField
        JTextField textFieldUsername = createJTextField(300, 30, 200, 30);
        user.setUsername(textFieldUsername.getText());

        JPasswordField textFieldPassword = new JPasswordField(50);
        textFieldPassword.setBounds(300, 80, 200, 30);
        user.setPassword(String.valueOf(textFieldPassword.getPassword()));
        textFieldPassword.setBackground(Color.BLACK);
        textFieldPassword.setForeground(Color.WHITE);


        // panelLogin / bouton
        //------------button login
        JButton buttonLogin = createJButton("Login", 300, 150, 200, 30);
        actionListenerLogin(buttonLogin, textFieldUsername, textFieldPassword, out, in);

        //------------button new account
        JButton buttonNewAccount = createJButton("New Account", 300, 200, 200, 30);
        actionListenerNewAccount(buttonNewAccount, out, in, frameLogin);

        // panelLogin / add elements
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
    public void frameChat(JFrame frameChat, PrintWriter out, BufferedReader in){
        // Panels
        JPanel panelChat = createJPanel();

        // Tabbed pane
        JTabbedPane paneChat = createJTabbedPane(1200, 800);

        // panelChat / label

        // panelChat / textField
        JTextField textFieldChat = createJTextField(250, 680, 790, 50);

        // panelChat / bouton
        //------------button send
        JButton buttonSend = createJButton("Send", 1060, 690, 100, 30);
        JButton buttonDeco = createJButton("",1100,30,40,40);

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

    public void createInterface(PrintWriter out, BufferedReader in) {
        // Frame
        frameLogin(frameLogin, out, in);
        frameChat(frameChat, out, in);
    }



}