import javax.swing.*;
import java.awt.*;

public class OptionFrame extends JDialog {
    private static int difficulty = 1000;

    OptionFrame(JFrame parent) {
        super(parent, "Options", true);
        this.setLayout(new FlowLayout());
        this.setSize(300, 200);

        JSlider difficultySlider = new JSlider(JSlider.HORIZONTAL, 500, 3000, difficulty);
        difficultySlider.setMajorTickSpacing(500);
        difficultySlider.setMinorTickSpacing(250);
        difficultySlider.setPaintTicks(true);
        difficultySlider.setPaintLabels(true);

        JLabel sliderLabel = new JLabel("Set Difficulty (ms): " + difficulty, JLabel.CENTER);
        difficultySlider.addChangeListener(e -> {
            difficulty = ((JSlider)e.getSource()).getValue();
            sliderLabel.setText("Set Difficulty (ms): " + difficulty);
        });

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> {
            setVisible(false);
        });

        this.add(sliderLabel);
        this.add(difficultySlider);
        this.add(backButton);
        this.setLocationRelativeTo(parent);
    }

    public static int getDifficulty() {
        return difficulty;
    }
}
