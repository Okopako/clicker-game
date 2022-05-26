import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ClickerMain {

    JLabel counterLabel, perSecLabel;
    int petCounter;
    Font font1, font2;

    petHandler pHandler = new petHandler();

    public static void main(String[] args) {

        new ClickerMain();
    }
    public ClickerMain() {

        petCounter = 0;

        createFont();
        createUI();

    }
    public  void createFont() {

        font1 = new Font("Comic Sans MS", Font.PLAIN, 32);
        font2 = new Font("Comic Sans MS", Font.PLAIN, 15);

    }
    public void createUI()  {

        JFrame window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);

        JPanel clickerPanel = new JPanel();
        clickerPanel.setBounds(100, 200, 200, 200);
        clickerPanel.setBackground(Color.black);
        window.add(clickerPanel);

        ImageIcon clicker = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("cat.png")));

        JButton clickerButton = new JButton();
        clickerButton.setBackground(Color.black);
        clickerButton.setContentAreaFilled(false);
        clickerButton.setFocusPainted(false);
        clickerButton.setBorder(null);
        clickerButton.setIcon(clicker);
        clickerButton.addActionListener(pHandler);
        clickerPanel.add(clickerButton);

        JPanel counterPanel = new JPanel();
        counterPanel.setBounds(100, 100, 200, 100);
        counterPanel.setBackground(Color.black);
        counterPanel.setLayout(new GridLayout(2,1));
        window.add(counterPanel);

        counterLabel = new JLabel(petCounter + " pets");
        counterLabel.setForeground(Color.lightGray);
        counterLabel.setFont(font1);
        counterPanel.add(counterLabel);

        perSecLabel = new JLabel();
        perSecLabel.setForeground(Color.lightGray);
        perSecLabel.setFont(font2);
        counterPanel.add(perSecLabel);

        window.setVisible(true);
    }

    public class petHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            petCounter++;
            counterLabel.setText(petCounter + " pets");
        }
    }
}
