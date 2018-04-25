package client;

public class Player{
    String sign;
    MainGameField gameField;
    int isShotReady = 0;

    public Player(String sign) {
        this.sign = sign;
    }

    boolean shot(int x, int y) {
        gameField = MainGameField.getInstance();
        if (!gameField.isCellBusy(x, y)) {
            gameField.cell[x][y] = sign;
            return true;
        }
        return false;
    }

    boolean win() {
        gameField = MainGameField.getInstance();
        return gameField.checkWin(this.sign);
    }
}
