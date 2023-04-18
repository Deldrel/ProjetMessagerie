
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Interface {
    User user = new User();

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
        return label;
    }

    // Fonction pour créer et configurer un JTextField
    private JTextField createJTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField(50);
        textField.setBounds(x, y, width, height);
        return textField;
    }

    // Fonction pour créer et configurer un JButton
    private JButton createJButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        return button;
    }

    // Fonction pour créer et configurer un JDialog


    public void createInterface() {
        // Frame
        JFrame frame = createJFrame("Chat", 1200, 800);


        // Panels
        JPanel panelLogin = createJPanel();
        JPanel panelChat = createJPanel();

        // Tabbed pane
        JTabbedPane tabbedPane = createJTabbedPane(1200, 800);


        // panelLogin  / label
        JLabel labelPseudo = createJLabel("Pseudo", 450, 10, 200, 30);
        JLabel labelPassword = createJLabel("Password", 450, 60, 200, 30);

        // panelLogin / textField
        JTextField textFieldUsername = createJTextField(550, 10, 200, 30);
        user.setUsername(textFieldUsername.getText());

        JTextField textFieldPassword = createJTextField(550, 60, 200, 30);
        user.setPassword(textFieldPassword.getText());

        // panelLogin / bouton
        JButton buttonLogin = createJButton("Login", 500, 150, 200, 30);
        buttonLogin.addActionListener(e -> {
            /*out.println("login"+textFieldUsername.getText()+" "+textFieldPassword.getText());
            out.flush;
            try {
                //System.out.println("Server replied" + in.readline());
            }catch (IOException ex){
                throw new RuntimeException(ex);
            }  */
        });

        JButton buttonNewAccount = createJButton("New Account", 500, 200, 200, 30);
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
                /*VueUser vueUser = new VueUser();
                ControlUser controlUser = new ControlUser(newUser,vueUser);
                out.println(vueUser.newAccountToString(newUser));
                out.flush();*/
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

        // panelLogin / add elements
        panelLogin.add(labelPseudo);
        panelLogin.add(labelPassword);
        panelLogin.add(textFieldUsername);
        panelLogin.add(textFieldPassword);
        panelLogin.add(buttonLogin);
        panelLogin.add(buttonNewAccount);

        // PanelChat / label
        JLabel labelFriends = createJLabel("Friends", 5, 10, 200, 30);
        JLabel labelChat = createJLabel("Chat", 250, 10, 200, 30);

        //panelChat /textField
        JTextField textFieldChat = createJTextField(250, 700, 800, 30);

        //panelChat / button
        JButton buttonSend = createJButton("Send", 1060,700,100,30);

        // panelChat / add elements
        panelChat.add(labelFriends);
        panelChat.add(labelChat);
        panelChat.add(textFieldChat);
        panelChat.add(buttonSend);


        // add elements to tabbed Pane
        tabbedPane.add("Login", panelLogin);
        tabbedPane.add("Chat", panelChat);

        //add elements in the frame
        frame.add(tabbedPane);
        frame.setVisible(true);
    }
}