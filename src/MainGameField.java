import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainGameField extends JPanel {
    private static MainGameField instance = null;
    private static final int FIELD_SIZE = 456;
    private final String NOT_SIGN = "*";
    private boolean gameOver = false;
    private String gameOverMessage = "";
    private static int linesCount = 19;
    private static int countToWin = 5;
    private int cellSize;
    private int x, y;
    private Player player1, player2;
    public String[][] cell;

    public static synchronized MainGameField getInstance() {
        if (instance == null)
            instance = new MainGameField();
        return instance;
    }

    void startNewGame() {
        gameOver = false;
        gameOverMessage = "";
        cellSize = FIELD_SIZE / linesCount;
        cell = new String[linesCount][linesCount];
        repaint();
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < linesCount; j++) {
                cell[i][j] = NOT_SIGN;
            }
        }
        setVisible(true);
    }

    private MainGameField() {
        setVisible(false);
        player1 = new Player("X");
        player2 = new Player("O");
        player1.isShotReady = 1;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                x = e.getX() / cellSize;
                y = e.getY() / cellSize;
                if (!gameOver)
                    game();
            }
        });
    }

    private boolean spotIsEmpty(int x, int y) {
        return cell[x][y].equals(NOT_SIGN);
    }

    private void game() {
        if (player1.isShotReady > 0 & player2.isShotReady == 0)
            if (spotIsEmpty(x, y)) {
                player1.shot(x,y);
                player1.isShotReady--;
                if (player1.isShotReady == 0)
                    player2.isShotReady = 2;
            }

        if (player1.win()) {
            gameOver = true;
            gameOverMessage = "Player 1 WIN!!!";
        }

        repaint();

        if (player2.isShotReady > 0 & player1.isShotReady == 0)
            if (spotIsEmpty(x, y)) {
                player2.shot(x,y);
                player2.isShotReady--;
                if (player2.isShotReady == 0)
                    player1.isShotReady = 2;
            }

//        if (!gameOver) {
//            player2.shot(x, y);
//        }

        if (player2.win()) {
            gameOver = true;
            gameOverMessage = "Player 2 WIN!!!";
        }

        repaint();

        if (isFieldFull() && !player2.win() && !player1.win()) {
            gameOver = true;
            gameOverMessage = "Draw!";
        }
    }

    boolean isCellBusy(int x, int y) {
        if (x < 0 || y < 0 || x > linesCount - 1 || y > linesCount - 1) {
            return false;
        }
        return !cell[x][y].equals(NOT_SIGN);
    }

    private boolean isFieldFull() {
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < linesCount; j++) {
                if (cell[i][j].equals(NOT_SIGN))
                    return false;
            }
        }
        return true;
    }

    private boolean checkLine(int start_x, int start_y, int dx, int dy, String sign) {
        int count = 0;
        for (int i = 0; i < countToWin; i++)
            if (cell[start_x + i * dx][start_y + i * dy].equals(sign)) {
                count++;
                if (count == countToWin)
                    return true;
            }
        return false;
    }

    public boolean checkWin(String sign) {
        for (int i = 0; i <= linesCount - countToWin; i++)
            for (int j = 0; j < linesCount; j++) {
                // строки
                if (checkLine(i, j, 1, 0, sign)) return true;
            }

        for (int i = 0; i < linesCount; i++)
            for (int j = 0; j <= linesCount - countToWin; j++) {
                // столбцы
                if (checkLine(i, j, 0, 1, sign)) return true;
            }

        for (int i = 0; i <= linesCount - countToWin; i++)
            for (int j = 0; j <= linesCount - countToWin; j++) {
                // y=x
                if (checkLine(i, j, 1, 1, sign)) return true;
            }

        for (int i = countToWin - 1; i < linesCount; i++)
            for (int j = 0; j <= linesCount - countToWin; j++) {
                // y=-x
                if (checkLine(i, j, -1, 1, sign)) return true;
            }

        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i <= this.linesCount; i++) {
            g.drawLine(0, i * this.cellSize, FIELD_SIZE, i * this.cellSize);
            g.drawLine(i * this.cellSize, 0, i * this.cellSize, FIELD_SIZE);
        }
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < linesCount; j++) {
                if (!cell[i][j].equals(NOT_SIGN)) {
                    if (cell[i][j].equals("X")) {
                        g.setColor(Color.RED);
                        g.drawLine((i * cellSize), (j * cellSize), (i + 1) * cellSize, (j + 1) * cellSize);
                        g.drawLine((i + 1) * cellSize, (j * cellSize), (i * cellSize), (j + 1) * cellSize);
                    }
                    if (cell[i][j].equals("O")) {
                        g.setColor(Color.BLUE);
                        g.drawOval((i * cellSize), (j * cellSize), cellSize, cellSize);
                    }
                }
            }
        }

        if (gameOver) {
            g.setColor(Color.BLACK);
            g.fillRect(0, FIELD_SIZE / 2, FIELD_SIZE, FIELD_SIZE / 8);
            g.setColor(Color.RED);
            g.drawString(gameOverMessage, 0, 19 * FIELD_SIZE / 32);
        }
    }
}
