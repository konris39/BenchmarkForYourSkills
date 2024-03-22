import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CPS extends JFrame implements ActionListener {
    private JButton clickButton;
    private JLabel timerLabel, cpsLabel;
    private JPanel centerPanel;
    private int clicks = 0;
    private long startTime = 0;
    private boolean timerStarted = false;
    private Timer countDownTimer;

    public CPS() {
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());

        this.getContentPane().setBackground(Color.DARK_GRAY);

        centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(new Color(95, 158, 160));

        clickButton = new JButton("Click Me FAST!");
        clickButton.setFont(new Font("Arial", Font.BOLD, 30));
        clickButton.addActionListener(this);
        clickButton.setPreferredSize(new Dimension(300, 150));
        clickButton.setFocusPainted(false);
        clickButton.setBackground(new Color(255, 102, 102));
        clickButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        centerPanel.add(clickButton);

        timerLabel = new JLabel("Time left: 5.00");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setForeground(Color.WHITE);

        cpsLabel = new JLabel("CPS: 0.00");
        cpsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        cpsLabel.setHorizontalAlignment(JLabel.CENTER);
        cpsLabel.setForeground(Color.WHITE);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(timerLabel, BorderLayout.NORTH);
        this.add(cpsLabel, BorderLayout.SOUTH);

        countDownTimer = new Timer(100, e -> {
            long elapsed = System.nanoTime() - startTime;
            float timeLeft = 5.0f - elapsed / 1_000_000_000.0f;
            timerLabel.setText(String.format("Time left: %.2f", Math.max(timeLeft, 0)));

            if (timeLeft <= 0) {
                clickButton.setEnabled(false);
                countDownTimer.stop();
                float cps = clicks / 5.0f;
                cpsLabel.setText(String.format("CPS: %.2f", cps));
            }
        });

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!timerStarted) {
            timerStarted = true;
            startTime = System.nanoTime();
            countDownTimer.start();
            clicks = 0;
            timerLabel.setText(String.format("Time left: %.2f", 5.0f));
        } else if (e.getSource() == clickButton) {
            clicks++;
        }
    }
}