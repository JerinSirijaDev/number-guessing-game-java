import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GuessingGameJava implements ActionListener, KeyListener {

    UIManager UI = new UIManager();
    JFrame frame;
    JPanel panel1;
    JPanel panel2;
    JLabel textLabel1;
    JLabel textLabel2;
    JLabel textLabel3;
    JLabel textLabel4;
    JLabel textLabel5;
    JLabel textLabel6;
    JLabel textLabel7;
    JLabel textLabel8;
    JLabel imageLabel;
    JTextField guessTextField;
    JButton guessButton;
    JLabel attemptsLabel;
    JLabel highestScoreLabel;
    JLabel timeTakenLabel;
    JTextField attemptsField;
    JTextField highestScoreField;
    JTextField timeTakenField;
    Timer timer;
    Random random = new Random();
    int randomNumber = random.nextInt(100);
    int attemptCount = 0;
    int scoreCount = 0;
    int elapsedTime = 0;
    int minutes = 0;
    int seconds = 0;
    int temp = 0;
    String second_string = String.format("%02d", seconds);
    String minute_string = String.format("%02d", minutes);
    JComboBox<String> difficultyComboBox; // Add JComboBox for difficulty selection
    int maxAttempts; // Added to keep track of the maximum attempts
    boolean gameInProgress = false; // Track if the game is in progress

    GuessingGameJava() {
        // Managing look and feel of JOptionPane
        UIManager.put("OptionPane.background", new Color(127, 133, 235));
        UIManager.put("Panel.background", new Color(127, 133, 235));

        UIManager.put("OptionPane.messageForeground", Color.white);
        UIManager.put("OptionPane.messageFont", new Font("OCR A Extended", Font.BOLD, 13));

        // Initialize timer
        countDownTimer();

        // Setting properties of JFrame
        frame = new JFrame();
        frame.setTitle("Can You Guess It? a Number Guessing Game");
        frame.getContentPane().setLayout(null);

        // custom icon of JFrame
        ImageIcon img = new ImageIcon("../resources/Guessing Game.png");
        frame.setIconImage(img.getImage());

        // Setting Properties of JPanel one and two
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(new Color(50, 150, 128));
        panel1.setBounds(0, 0, 350, 500);

        panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBackground(new Color(140, 100, 120));
        panel2.setBounds(351, 370, 350, 100);

        // Setting properties of the components
        

        textLabel1 = new JLabel();
        textLabel1.setText("Number");
        textLabel1.setFont(new Font("OCR A Extended", Font.BOLD, 60));
        textLabel1.setForeground(Color.WHITE);
        textLabel1.setBounds(60, 280, 300, 100);

        textLabel2 = new JLabel();
        textLabel2.setText("Guessing Game");
        textLabel2.setFont(new Font("Jokerman", Font.PLAIN, 40));
        textLabel2.setForeground(Color.WHITE);
        textLabel2.setBounds(28, 325, 300, 100);

        textLabel3 = new JLabel();
        textLabel3.setText("Guess The Number");
        textLabel3.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 35));
        textLabel3.setForeground(new Color(150, 59, 30));
        textLabel3.setBounds(370, 45, 300, 100);

        textLabel4 = new JLabel();
        textLabel4.setText("Between 0 to 99");
        textLabel4.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 30));
        textLabel4.setForeground(Color.black);
        textLabel4.setBounds(398, 80, 300, 100);

        guessTextField = new JTextField();
        guessTextField.setDocument(new JTextFieldCharLimit(2));
        guessTextField.setHorizontalAlignment(SwingConstants.CENTER);
        guessTextField.setBounds(440, 170, 145, 30);
        guessTextField.setFont(new Font("OCR A Extended", Font.BOLD, 20));
        guessTextField.setBackground(new Color(232, 232, 232));
        guessTextField.setBorder(BorderFactory.createBevelBorder(1));

        guessButton = new JButton();
        guessButton.setText("Guess");
        guessButton.setFont(new Font("OCR A Extended", Font.BOLD, 20));
        guessButton.setBounds(450, 215, 125, 35);
        guessButton.setFocusable(false);
        guessButton.setBackground(new Color(111, 181, 167));
        guessButton.setForeground(Color.white);

        textLabel5 = new JLabel();
        textLabel5.setText("");
        textLabel5.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
        textLabel5.setForeground(Color.red);
        textLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel5.setBounds(420, 226, 180, 100);

        attemptsLabel = new JLabel();
        attemptsLabel.setText("Attempts");
        attemptsLabel.setFont(new Font("OCR A Extended", Font.BOLD, 15));
        attemptsLabel.setBounds(10, 5, 125, 35);
        attemptsLabel.setForeground(Color.white);

        highestScoreLabel = new JLabel();
        highestScoreLabel.setText("High Score");
        highestScoreLabel.setFont(new Font("OCR A Extended", Font.BOLD, 15));
        highestScoreLabel.setBounds(10, 30, 125, 35);
        highestScoreLabel.setForeground(Color.white);

        timeTakenLabel = new JLabel();
        timeTakenLabel.setText("Time");
        timeTakenLabel.setFont(new Font("OCR A Extended", Font.BOLD, 15));
        timeTakenLabel.setBounds(10, 55, 160, 35);
        timeTakenLabel.setForeground(Color.white);

        attemptsField = new JTextField();
        attemptsField.setEditable(false);
        attemptsField.setBounds(230, 14, 70, 15);
        attemptsField.setHorizontalAlignment(SwingConstants.CENTER);
        attemptsField.setFont(new Font("OCR A Extended", Font.BOLD, 13));
        attemptsField.setBorder(BorderFactory.createBevelBorder(1));

        highestScoreField = new JTextField();
        highestScoreField.setEditable(false);
        highestScoreField.setText("");
        highestScoreField.setBounds(230, 39, 70, 15);
        highestScoreField.setHorizontalAlignment(SwingConstants.CENTER);
        highestScoreField.setFont(new Font("OCR A Extended", Font.BOLD, 13));
        highestScoreField.setBorder(BorderFactory.createBevelBorder(1));

        timeTakenField = new JTextField();
        timeTakenField.setEditable(false);
        timeTakenField.setBounds(230, 64, 70, 15);
        timeTakenField.setHorizontalAlignment(SwingConstants.CENTER);
        timeTakenField.setFont(new Font("OCR A Extended", Font.BOLD, 13));
        timeTakenField.setBorder(BorderFactory.createBevelBorder(1));

        textLabel6 = new JLabel();
        textLabel6.setText("Can You");
        textLabel6.setFont(new Font("OCR A Extended", Font.BOLD, 30));
        textLabel6.setForeground(Color.WHITE);
        textLabel6.setBounds(40, -15, 300, 100);
        
        textLabel7 = new JLabel();
        textLabel7.setText("Guess it?");
        textLabel7.setFont(new Font("OCR A Extended", Font.BOLD, 30));
        textLabel7.setForeground(Color.WHITE);
        textLabel7.setBounds(168, -15, 300, 100);

        textLabel8 = new JLabel();
        textLabel8.setText("Select a Level &");
        textLabel8.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 38));
        textLabel8.setForeground(new Color(150, 59, 30));
        textLabel8.setBounds(385, 11, 300, 100);

        // image on the left panel
        ImageIcon icon = new ImageIcon("../resources/Guessing Game.png");
        imageLabel = new JLabel(icon);
        imageLabel.setBounds(35, 50, icon.getIconWidth(), icon.getIconHeight());

        // Adding Action Listener to JButton
        guessButton.addActionListener(this);

        // Adding Key Listener to JTextField
        guessTextField.addKeyListener(this);

        // Adding panels to JFrame
        frame.add(panel1);
        frame.add(panel2);

        // Adding components to the container
        
        panel1.add(textLabel6);
        panel1.add(textLabel7);
        panel1.add(textLabel1);
        panel1.add(textLabel2);
        panel2.add(attemptsLabel);
        panel2.add(highestScoreLabel);
        panel2.add(timeTakenLabel);
        panel2.add(attemptsField);
        panel2.add(highestScoreField);
        panel2.add(timeTakenField);
        frame.add(textLabel8);
        frame.add(textLabel3);
        frame.add(textLabel4);
        frame.add(textLabel5);
        frame.add(guessTextField);
        frame.add(guessButton);
        panel1.add(imageLabel);

        // Create JComboBox for difficulty selection
        String[] difficultyLevels = {"Easy (10 attempts)", "Medium (7 attempts)", "Expert (5 attempts)"};
        difficultyComboBox = new JComboBox<>(difficultyLevels);
        difficultyComboBox.setFont(new Font("OCR A Extended", Font.BOLD, 15));
        difficultyComboBox.setBounds(420, 301, 190, 35);
        frame.add(difficultyComboBox);
        maxAttempts = 10; // Set default maximum attempts to 10 for "Easy" difficulty

        // Disable the components before starting the game
        disableGameComponents();

        // Adding Action Listener to JComboBox for difficulty selection
        difficultyComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDifficulty = (String) difficultyComboBox.getSelectedItem();
                if (!gameInProgress) {
                    // Game is not in progress, set maxAttempts and start the game
                    if (selectedDifficulty.contains("Easy")) {
                        maxAttempts = 10;
                    } else if (selectedDifficulty.contains("Medium")) {
                        maxAttempts = 7;
                    } else {
                        maxAttempts = 5;
                    }
                    enableGameComponents(); // Enable the game components
                    guessTextField.requestFocus(); // Focus on the text field for input
                    gameInProgress = true;
                    JOptionPane.showMessageDialog(null, "Difficulty level set to " + selectedDifficulty, "Difficulty Set", JOptionPane.INFORMATION_MESSAGE);
                    timer.start(); // Start the timer when difficulty is selected
                } 
				/*else {
                    // Game is already in progress, don't change difficulty
                    JOptionPane.showMessageDialog(null, "Cannot change difficulty during the game.", "Difficulty Change Error", JOptionPane.ERROR_MESSAGE);
                }*/
            }
        });

        // Setting properties of JFrame
        frame.getContentPane().setBackground(Color.yellow);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            handleGuess();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Restricting JTextField to accept only numbers
        char c = e.getKeyChar();
        if (!Character.isDigit(c) || Character.isWhitespace(c)) {
            if (!(e.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                Toolkit.getDefaultToolkit().beep();
            }
            e.consume();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            handleGuess();
        }
    }

    // Handle guess logic
    private void handleGuess() {
        if (!gameInProgress) {
            // Game is not in progress, return
            return;
        }
        
        if (guessTextField.getText().equals("")) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Invalid Input", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            attemptCount++;
            attemptsField.setText(Integer.toString(attemptCount));
            int number = Integer.parseInt(guessTextField.getText());
            if (randomNumber == number) {
                timer.stop();
                guessTextField.setEditable(false);
                guessTextField.setText("");
                textLabel5.setText("");
                temp++;
                if (temp == 1) {
                    highestScoreField.setText(String.valueOf(attemptCount));
                    scoreCount = attemptCount;
                } else {
                    if (attemptCount <= scoreCount) {
                        highestScoreField.setText(String.valueOf(attemptCount));
                        scoreCount = attemptCount;
                    }
                }
                int option = JOptionPane.showConfirmDialog(null, "YOU WIN! Correct Guess was " + randomNumber + "\nDo you want to play again?", "❤️ Congratulations ❤️", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    resetGame();
                } else {
                    System.exit(0);
                }
            }
            else if (randomNumber > number) {
                textLabel5.setText(number + " is Low!!");
            } else if (randomNumber < number) {
                textLabel5.setText(number + " is High!!");
            }

            if (attemptCount >= maxAttempts) {
                timer.stop();
                guessTextField.setEditable(false);
                int option = JOptionPane.showConfirmDialog(null, "You Lost! The correct answer was " + randomNumber + "\nDo you want to play again?", "Better Luck Next Time", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    resetGame();
                } else {
                    System.exit(0);
                }
            }
        }}

    // Reset the game
    private void resetGame() {
        attemptCount = 0;
        elapsedTime = 0;
        minutes = 0;
        seconds = 0;
        second_string = String.format("%02d", seconds);
        minute_string = String.format("%02d", minutes);
        attemptsField.setText("");
        timeTakenField.setText("");
        guessTextField.setEditable(true);
        guessTextField.setText("");
        textLabel5.setText("");
        randomNumber = random.nextInt(100);
        temp = 0;
        maxAttempts = 10; // Reset max attempts to default for "Easy"
        difficultyComboBox.setSelectedIndex(0);
        disableGameComponents();
        gameInProgress = false;
        guessButton.requestFocus();
    }

    // Code for enabling game components
    private void enableGameComponents() {
        guessButton.setEnabled(true);
        guessTextField.setEnabled(true);
        difficultyComboBox.setEnabled(false);
    }

    // Code for disabling game components
    private void disableGameComponents() {
        guessButton.setEnabled(false);
        guessTextField.setEnabled(false);
        difficultyComboBox.setEnabled(true);
    }

    // Code for displaying Timer
    public void countDownTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime = elapsedTime + 1000;
                minutes = (elapsedTime / 60000) % 60;
                seconds = (elapsedTime / 1000) % 60;
                second_string = String.format("%02d", seconds);
                minute_string = String.format("%02d", minutes);
                timeTakenField.setText(minute_string + ":" + second_string);
            }
        });
    }

    // Creating main method
    public static void main(String[] args) {
        /*GuessingGameJava guessingGameJava =*/ 
        new GuessingGameJava();
    }
}

// Restricting JTextField Character limits using PlainDocument class
class JTextFieldCharLimit extends PlainDocument {
    int limit;

    public JTextFieldCharLimit(int limitation) {
        this.limit = limitation;
    }

    public void insertString(int offset, String str, AttributeSet set) throws BadLocationException {
        if (str == null) {
            return;
        } else if (getLength() + str.length() <= limit) {
            super.insertString(offset, str, set);
        } else if (getLength() + str.length() > limit) {
            Toolkit.getDefaultToolkit().beep();
        }
    }
}
