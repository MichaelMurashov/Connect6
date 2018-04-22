import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaitPlayerForm extends JFrame {
    WaitPlayerForm waitPlayerForm = this;

    public WaitPlayerForm() {
        setTitle("Ожидание другого игрока");
        setBounds(450, 450, 300, 200);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        setVisible(true);

        MainGameField gameField = MainGameField.getInstance();
        gameField.startNewGame();

        waitPlayerForm.setVisible(false);
    }
}
