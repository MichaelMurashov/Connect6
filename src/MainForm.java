import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    public MainForm() {

        setTitle("Connect6 Game");
        setBounds(300, 300, 455, 525);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MainGameField gameField = MainGameField.getInstance();
        add(gameField, BorderLayout.CENTER);

        // Создаём панель для кнопок
        JPanel buttonPanel = new JPanel(new GridLayout());
        add(buttonPanel, BorderLayout.SOUTH);
        JButton btnStart = new JButton("Начать новую игру");
        buttonPanel.add(btnStart);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(btnStart.getText());
                WaitPlayerForm waitPlayerForm = new WaitPlayerForm();
            }
        });

//        JButton btnEnd = new JButton("Закончить игру");
//        buttonPanel.add(btnEnd);
//        btnEnd.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        });

        setVisible(true);
    }
}
