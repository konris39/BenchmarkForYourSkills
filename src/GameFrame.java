import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameFrame extends JFrame implements ActionListener {
    JButton button, backToMenuButton, retryButton;
    JLabel scoreLabel, timerLabel, difficultyLabel;
    Random rand = new Random();
    private int xRand = 600, yRand = 500;
    private final int winWid = 1200, winHei = 900;
    private int score = 0;
    private float remainingTime = 30.0f;
    Timer moveTimer, gameTimer;
    private int difficulty = 1000;


    GameFrame() {
        button = new JButton();
        button.setBounds(xRand, yRand, 50, 50);
        button.addActionListener(this);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(10, 10, 100, 25);
        scoreLabel.setForeground(Color.BLACK);

        timerLabel = new JLabel(String.format("Time: %.2f", remainingTime));
        timerLabel.setBounds(10, 35, 100, 25);
        timerLabel.setForeground(Color.BLACK);

        backToMenuButton = new JButton("Back to Menu");
        backToMenuButton.setBounds(500, 400, 200, 50);
        backToMenuButton.addActionListener(e -> {
            this.dispose();
            closeAllWindows();
            new Menu();
        });
        backToMenuButton.setVisible(false);

        retryButton = new JButton("Retry");
        retryButton.setBounds(500, 500, 200, 50);
        retryButton.addActionListener(e -> {
            this.dispose();
            new GameFrame();
        });
        retryButton.setVisible(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(winWid, winHei);
        this.setVisible(true);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.GREEN);
        this.add(button);
        this.add(scoreLabel);
        this.add(timerLabel);
        this.add(backToMenuButton);
        this.add(retryButton);

        difficulty = OptionFrame.getDifficulty();

        moveTimer = new Timer(difficulty, e -> {
            xRand = rand.nextInt(winWid - 50);
            yRand = rand.nextInt(winHei - 50);
            button.setBounds(xRand, yRand, 50, 50);
        });

        gameTimer = new Timer(100, e -> {
            remainingTime -= 0.1f;
            timerLabel.setText(String.format("Time: %.2f", remainingTime));
            if (remainingTime <= 0) {
                button.setEnabled(false);
                moveTimer.stop();
                gameTimer.stop();
                timerLabel.setText("Time: 0.00");
                backToMenuButton.setVisible(true);
                retryButton.setVisible(true);
            }
        });

        difficultyLabel = new JLabel("Difficulty: " + difficulty + " ms");
        difficultyLabel.setBounds(winWid - 200, 10, 180, 25);
        this.add(difficultyLabel);

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

            xRand = rand.nextInt(winWid - 50);
            yRand = rand.nextInt(winHei - 50);
            button.setBounds(xRand, yRand, 50, 50);

            moveTimer.restart();
        }
    }
}
