import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class ClickerMain {

    JLabel counterLabel, perSecLabel;
    JButton button1, button2, button3, button4;
    int petCounter, timerSpeed, eHandNumber, eHandPrice, mHandPrice, mHandNumber, cSelfNumber, cSelfPrice, catinatorNumber, catinatorPrice;
    double perSecond;
    boolean timerOn, mHandUnlocked, cSelfUnlocked, catinatorUnlocked;
    Font font1, font2;
    PetHandler pHandler = new PetHandler();
    Timer timer;
    JTextArea messageText;
    MouseHandler mHandler = new MouseHandler();

    public static void main(String[] args) {

        new ClickerMain();
    }
    public ClickerMain() {

        timerOn = false;
        mHandUnlocked = false;
        cSelfUnlocked = false;
        catinatorUnlocked = false;
        perSecond = 0;
        petCounter = 0;
        eHandNumber = 0;
        eHandPrice = 10;
        mHandNumber = 0;
        mHandPrice = 100;
        cSelfNumber = 0;
        cSelfPrice = 1000;
        catinatorNumber = 0;
        catinatorPrice = 10000;

        createFont();
        createUI();

    }
    public  void createFont() {

        font1 = new Font("Comic Sans MS", Font.PLAIN, 32);
        font2 = new Font("Comic Sans MS", Font.PLAIN, 15);

    }
    public void createUI()  {

        JFrame window = new JFrame();   // creates window for application
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // makes it able to close
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);

        JPanel clickerPanel = new JPanel(); // creates area for clicker placement
        clickerPanel.setBounds(100, 200, 200, 200);
        clickerPanel.setBackground(Color.black);
        window.add(clickerPanel);

        ImageIcon clicker = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("cat.png")));  // imports image

        JButton clickerButton = new JButton();  //creates clickable button on clicker area
        clickerButton.setBackground(Color.black);
        clickerButton.setContentAreaFilled(false);  // removes flashing background from image when clicked
        clickerButton.setFocusPainted(false);
        clickerButton.setBorder(null);
        clickerButton.setIcon(clicker);
        clickerButton.addActionListener(pHandler);  // makes counter able to add count on click
        clickerButton.setActionCommand("Pets");
        clickerPanel.add(clickerButton);

        JPanel counterPanel = new JPanel(); // adds area for overall counter and per sec counter
        counterPanel.setBounds(100, 100, 200, 100);
        counterPanel.setBackground(Color.black);
        counterPanel.setLayout(new GridLayout(2,1));
        window.add(counterPanel);

        counterLabel = new JLabel(petCounter + " pets");    // adds count to counter area
        counterLabel.setForeground(Color.lightGray);
        counterLabel.setFont(font1);
        counterPanel.add(counterLabel);

        perSecLabel = new JLabel(); // adds per sec count to counter area
        perSecLabel.setForeground(Color.lightGray);
        perSecLabel.setFont(font2);
        counterPanel.add(perSecLabel);

        JPanel itemPanel = new JPanel();
        itemPanel.setBounds(500, 170, 250, 250);
        itemPanel.setBackground(Color.black);
        itemPanel.setLayout(new GridLayout(4, 1));
        window.add(itemPanel);

        button1 = new JButton("Extra hand");    // eHand
        button1.setFont(font2);
        button1.setFocusPainted(false);
        button1.addActionListener(pHandler);
        button1.setActionCommand("Extra hand");
        button1.addMouseListener(mHandler);
        itemPanel.add(button1);

        button2 = new JButton("?");   // mHand
        button2.setFont(font2);
        button2.setFocusPainted(false);
        button2.addActionListener(pHandler);
        button2.setActionCommand("Mechanical hand");
        button2.addMouseListener(mHandler);
        itemPanel.add(button2);

        button3 = new JButton("?");     // cSelf
        button3.setFont(font2);
        button3.setFocusPainted(false);
        button3.addActionListener(pHandler);
        button3.setActionCommand("Clone of self");
        button3.addMouseListener(mHandler);
        itemPanel.add(button3);

        button4 = new JButton("?");    // catinator
        button4.setFont(font2);
        button4.setFocusPainted(false);
        button4.addActionListener(pHandler);
        button4.setActionCommand("CATinator 4000");
        button4.addMouseListener(mHandler);
        itemPanel.add(button4);

        JPanel messagePanel = new JPanel();
        messagePanel.setBounds(500, 70, 250, 150);
        messagePanel.setBackground(Color.black);
        window.add(messagePanel);

        messageText = new JTextArea();
        messageText.setBounds(500, 70 ,250, 150);
        messageText.setForeground(Color.white);
        messageText.setBackground(Color.black);
        messageText.setFont(font2);
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setEditable(false);
        messagePanel.add(messageText);


        window.setVisible(true);    // makes application window visible
    }
    public void setTimer() {

        timer = new Timer(timerSpeed, new ActionListener() {    // in milliseconds
            @Override
            public void actionPerformed(ActionEvent e) {

                petCounter++;
                counterLabel.setText(petCounter + " pets");

                if (!mHandUnlocked) {
                    if (petCounter >= 100) {
                        mHandUnlocked = true;
                        button2.setText("Mechanical hand " + "(" + mHandNumber + ")");
                    }
                } else if (!cSelfUnlocked) {
                    if (petCounter >= 1000) {
                        cSelfUnlocked = true;
                        button3.setText("Clone of self " + "(" + cSelfNumber + ")");
                    }
                } else if (!catinatorUnlocked) {
                    if (petCounter >= 10000) {
                        catinatorUnlocked = true;
                        button4.setText("CATinator " + "(" + catinatorNumber + ")");
                    }
                }
            }
        });
    }
    public void timerUpdate() {

        if (!timerOn) {
            timerOn = true;
        } else {
            timer.stop();
        }

        double speed = 1/perSecond*1000;
        timerSpeed = (int)Math.round(speed);

        String perSecondCounterFormat = String.format("%.1f", perSecond);    // creates per second with one decimal place (doesn't work idk)
        perSecLabel.setText("per second: " + perSecondCounterFormat);

        setTimer();
        timer.start();
    }

    public class PetHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {    // upgrades pet count by 1

            String action = event.getActionCommand();

            switch (action) {
                case "Pets" -> {

                    petCounter++;
                    counterLabel.setText(petCounter + " pets");
                }
                case "Extra hand" -> {
                    if (petCounter >= eHandPrice) {
                        petCounter -= eHandPrice;
                        eHandPrice *= 1.2;

                        counterLabel.setText(petCounter + " pets");

                        eHandNumber ++;
                        button1.setText("Extra hand " + "(" + eHandNumber + ")");
                        messageText.setText("Extra hand\n[price: " + eHandPrice + "]\nExtra hand pets once every ten seconds.");

                        perSecond = perSecond + 0.1;
                        timerUpdate();
                    }
                    else {
                        messageText.setText("Need more pets!");
                    }
                }
                case "Mechanical hand" -> {
                    if (petCounter >= mHandPrice) {
                        petCounter -= mHandPrice;
                        mHandPrice *= 1.5;

                        counterLabel.setText(petCounter + " pets");

                        mHandNumber ++;
                        button2.setText("Mechanical hand " + "(" + mHandNumber + ")");
                        messageText.setText("Mechanical hand\n[price: " + mHandPrice + "]\nMechanical hand pets once every second.");

                        perSecond = perSecond + 1;
                        timerUpdate();
                    }
                    else {
                        messageText.setText("Need more pets!");
                    }
                }
                case "Clone of self" -> {
                    if (petCounter >= cSelfPrice) {
                        petCounter -= cSelfPrice;
                        cSelfPrice *= 2;

                        counterLabel.setText(petCounter + " pets");

                        cSelfNumber ++;
                        button3.setText("Clone of self " + "(" + cSelfNumber + ")");
                        messageText.setText("Clone of self\n[price: " + cSelfPrice + "]\nYour clone pets ten times a second.");

                        perSecond = perSecond + 10;
                        timerUpdate();
                    }
                    else {
                        messageText.setText("Need more pets!");
                    }
                }
                case "CATinator 4000" -> {
                    if (petCounter >= catinatorPrice) {
                        petCounter -= catinatorPrice;
                        cSelfPrice *= 3;

                        counterLabel.setText(petCounter + " pets");

                        catinatorNumber ++;
                        button4.setText("CATinator 4000 " + "(" + catinatorNumber + ")");
                        messageText.setText("CATinator\n[price: " + catinatorPrice + "]\nCATinator warps time to pet thousand times a second.");

                        perSecond = perSecond + 1000;
                        timerUpdate();
                    }
                    else {
                        messageText.setText("Need more pets!");
                    }
                }
            }
        }
    }

    public class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {

            JButton button = (JButton)e.getSource();

            if (button == button1) {
                messageText.setText("Extra hand\n[price: " + eHandPrice + "]\nExtra hand pets once every ten seconds.");

            } else if (button == button2){

                if (!mHandUnlocked) {
                    messageText.setText("This upgrade is currently locked!");

                } else {
                    messageText.setText("Mechanical hand\n[price: " + mHandPrice + "]\nMechanical hand pets once every second.");
                }
            }

            else if (button == button3){

                if (!cSelfUnlocked) {
                    messageText.setText("This upgrade is currently locked!");
                } else {
                    messageText.setText("Clone of self\n[price: " + cSelfPrice + "]\nYour clone pets ten times a second.");
                }
            }

            else if (button == button4){

                if (!catinatorUnlocked) {
                    messageText.setText("This upgrade is currently locked!");
                } else {
                    messageText.setText("CATinator\n[price: " + catinatorPrice + "]\nCATinator warps time to pet thousand times a second.");
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {

            JButton button = (JButton)e.getSource();

            if (button == button1) {
                messageText.setText(null);

            } else if (button == button2) {
                messageText.setText(null);
            }

            else if (button == button3) {
                messageText.setText(null);
            }

            else if (button == button4) {
                messageText.setText(null);
            }
        }

    }
}
