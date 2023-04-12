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


            //initialisation of elements
            //--------------------------------frame
            JFrame frame = new JFrame("Chat");
            frame.setSize(1200, 800);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //--------------------------------panel
            JPanel panel = new JPanel();
            //--------------------------------layout
            //creer un layout pour le panel grille de 3 lignes et 2 colonnes
            GridLayout layout = new GridLayout(10, 2);
            //espace de 5 pixel entre les composants
            layout.setHgap(5);
            layout.setVgap(10);
            //appliquer le layout au panel
            panel.setLayout(layout);
            //--------------------------------panel/label
            JLabel labelPseudo = new JLabel("Pseudo");
            //position du label dans le layout : ligne 1 colonne 1
            labelPseudo.setLocation(10, 10);
            JLabel labelPassword = new JLabel("Password");
            labelPassword.setLocation(10, 50);
            //--------------------------------panel/text field
            JTextField textFieldPseudo = new JTextField(20);
            //positionner text field pseudo sur la meme ligne que le label pseudo
            textFieldPseudo.setLocation(50, 10);

            JTextField textFieldPassword = new JTextField(20);
            textFieldPassword.setLocation(50, 50);
            //--------------------------------panel/button
            JButton buttonLogin = new JButton("Login");
            buttonLogin.setSize(20, 50);
            buttonLogin.setLocation(50, 500);

            //add elements to panel
            panel.add(labelPseudo);
            panel.add(labelPassword);
            panel.add(textFieldPseudo);
            panel.add(textFieldPassword);
            panel.add(buttonLogin);

            //show panel in the frame
            frame.add(panel);


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

