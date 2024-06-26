import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameFrame extends JFrame implements ActionListener {
    JButton button, backToMenuButton, retryButton;
    JLabel scoreLabel, timerLabel, difficultyLabel;
    JPanel gamePanel, buttonPanel;
    Random rand = new Random();
    private int xRand = 600, yRand = 500;
    private final int winWid = 1200, winHei = 900;
    private int score = 0;
    private float remainingTime = 30.0f;
    Timer moveTimer, gameTimer;
    private int difficulty = 1000;
    private long startTime;

    GameFrame() {
        gamePanel = new JPanel();
        gamePanel.setLayout(null);
        gamePanel.setBounds(0, 0, winWid, winHei);
        gamePanel.setBackground(new Color(95, 158, 160));

        button = new JButton();
        button.setBounds(xRand, yRand, 50, 50);
        button.addActionListener(this);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(Color.RED);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

        backToMenuButton = new JButton("Back to Menu");
        backToMenuButton.addActionListener(e -> {
            this.dispose();
            closeAllWindows();
            new Menu();
        });
        backToMenuButton.setPreferredSize(new Dimension(120, 50));
        backToMenuButton.setOpaque(true);
        backToMenuButton.setBackground(Color.GRAY);
        backToMenuButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));

        retryButton = new JButton("Retry");
        retryButton.addActionListener(e -> {
            this.dispose();
            new GameFrame();
        });
        retryButton.setPreferredSize(new Dimension(120, 50));
        retryButton.setOpaque(true);
        retryButton.setBackground(Color.GRAY);
        retryButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(10, 10, 150, 25);
        scoreLabel.setForeground(Color.WHITE);

        timerLabel = new JLabel(String.format("Time: %.2f", remainingTime));
        timerLabel.setBounds(10, 35, 150, 25);
        timerLabel.setForeground(Color.WHITE);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        int panelWidth = 250;
        int panelHeight = 50;
        buttonPanel.setBounds((winWid - panelWidth) / 2, (winHei - panelHeight) / 2, panelWidth, panelHeight);
        buttonPanel.setOpaque(false);

        backToMenuButton.setVisible(false);
        retryButton.setVisible(false);

        buttonPanel.add(backToMenuButton);
        buttonPanel.add(retryButton);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(winWid, winHei);
        this.setResizable(false);
        this.getContentPane().add(gamePanel);
        gamePanel.add(button);
        gamePanel.add(scoreLabel);
        gamePanel.add(timerLabel);
        gamePanel.add(buttonPanel);

        difficulty = OptionFrame.getDifficulty();

        difficultyLabel = new JLabel("Difficulty: " + difficulty + " ms");
        difficultyLabel.setBounds(winWid - 200, 10, 180, 25);
        difficultyLabel.setForeground(Color.WHITE);
        gamePanel.add(difficultyLabel);

        setupTimers();

        this.setVisible(true);
    }

    private void setupTimers() {
        moveTimer = new Timer(difficulty, e -> {
            xRand = rand.nextInt(winWid - 50);
            yRand = rand.nextInt(winHei - 50);
            button.setBounds(xRand, yRand, 50, 50);
        });

        startTime = System.nanoTime();

        gameTimer = new Timer(100, e -> {
            long elapsed = System.nanoTime() - startTime;
            float timeLeft = 30.0f - elapsed / 1_000_000_000.0f;
            timerLabel.setText(String.format("Time: %.2f", Math.max(timeLeft, 0)));

            if (timeLeft <= 0) {
                button.setEnabled(false);
                moveTimer.stop();
                gameTimer.stop();
                timerLabel.setText("Time: 0.00");
                backToMenuButton.setVisible(true);
                retryButton.setVisible(true);
            }
        });

        moveTimer.start();
        gameTimer.start();
    }

    private void closeAllWindows() {
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JDialog) {
                window.dispose();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            score++;
            scoreLabel.setText("Score: " + score);

            xRand = rand.nextInt(winWid - 100);
            yRand = rand.nextInt(winHei - 100);
            button.setBounds(xRand, yRand, 50, 50);

            moveTimer.restart();
        }
    }
}