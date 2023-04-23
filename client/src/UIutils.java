import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UIutils {
    public static final Color BACKGROUND_COLOR = Color.decode("#0B194A");
    public static final Color BACKGROUND_BUTTON_COLOR = Color.decode("#522ED3");
    public static final Color BACKGROUND_BUTTON_HOVER_COLOR = Color.decode("#522ED3");
    public static final Color BACKGROUND_BUTTON_HOVER_CLICK = Color.decode("#1B2956");
    public static final Color BACKGROUND_BUTTON_BORDER_COLOR = Color.BLACK;
    public static final Color BACKGROUND_TEXTFIELD_COLOR = Color.decode("#1B2956");
    public static final Color BACKGROUND_TEXTFIELD_BORDER_COLOR = Color.decode("#212F60");
    public static final Color BACKGROUND_LABEL_COLOR = Color.decode("#7688BA");


    public static JFrame createJFrame(String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        return frame;
    }

    public static JPanel createJPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(BACKGROUND_COLOR);
        return panel;
    }

    public static JTabbedPane createJTabbedPane(int width, int height) {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setSize(width, height);
        return tabbedPane;
    }

    public static JLabel createJLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setForeground(BACKGROUND_LABEL_COLOR);
        return label;
    }

    public static JTextField createJTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField(50);
        textField.setBounds(x, y, width, height);
        textField.setBackground(BACKGROUND_TEXTFIELD_COLOR);
        textField.setForeground(Color.WHITE);
        Border border = BorderFactory.createLineBorder(BACKGROUND_TEXTFIELD_BORDER_COLOR, 1);
        textField.setBorder(border);
        return textField;
    }

    public static JButton createJButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setForeground(Color.WHITE);
        button.setBackground(BACKGROUND_BUTTON_COLOR);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFont(new Font("Dialog", Font.BOLD, 14));
        button.setBorder(BorderFactory.createLineBorder(BACKGROUND_TEXTFIELD_BORDER_COLOR, 1));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(BACKGROUND_BUTTON_HOVER_CLICK);
                button.setBorder(BorderFactory.createLineBorder(BACKGROUND_BUTTON_COLOR, 2));
                button.setForeground(BACKGROUND_BUTTON_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(BACKGROUND_BUTTON_HOVER_COLOR);
                button.setForeground(Color.WHITE);
                button.setBorder(BorderFactory.createLineBorder(BACKGROUND_TEXTFIELD_BORDER_COLOR, 1));
            }
        });
        return button;
    }
}
