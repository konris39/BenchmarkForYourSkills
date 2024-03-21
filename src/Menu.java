import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    JButton play, options;
    private final int winWid = 1200, winHei = 900;

    Menu() {
        play = new JButton("Play");
        play.setBounds(winWid / 2 - 158, winHei / 2 - 200, 300, 100);
        play.addActionListener(this);
        play.setFont(new Font("Arial", Font.BOLD, 20));
        play.setBackground(Color.RED);
        play.setForeground(Color.WHITE);
        play.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4, true));
        play.setFocusPainted(false);

        options = new JButton("Options");
        options.setBounds(winWid / 2 - 158, winHei / 2 - 80, 300, 100);
        options.addActionListener(this);
        options.setFont(new Font("Arial", Font.BOLD, 20));
        options.setBackground(Color.BLUE);
        options.setForeground(Color.WHITE);
        options.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4, true));
        options.setFocusPainted(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(winWid, winHei);
        this.add(play);
        this.add(options);
        this.setVisible(true);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(95, 158, 160));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play) {
            this.dispose();
            new GameFrame();
        } else if (e.getSource() == options) {
            OptionFrame optionsFrame = new OptionFrame(this);
            optionsFrame.setVisible(true);
        }
    }
}
