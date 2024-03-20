import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    JButton play, options;
    private final int winWid = 1200, winHei = 900;

    Menu() {
        play = new JButton("Play");
        play.setBounds(400, 100, 100, 50);
        play.addActionListener(this);

        options = new JButton("Options");
        options.setBounds(400, 200, 100, 50);
        options.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(winWid, winHei);
        this.add(play);
        this.add(options);
        this.setVisible(true);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.GREEN);
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
