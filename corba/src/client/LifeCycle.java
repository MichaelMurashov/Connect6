package client;

public class LifeCycle {

    private final MainForm mainForm;

    public LifeCycle(MainForm mainForm) {
        this.mainForm = mainForm;
    }

    public void start(String move) {
                switch (move) {
                    case "set player 1":
                        mainForm.setPlayerFirst(true);
                        break;

                    case "set player 2":
                        mainForm.setPlayerFirst(false);
                        break;

                    case "start game":
                        mainForm.startGame();
                        break;

                    default:
                        //   x/y/playerNum
                        String[] data = move.split("/");
                        int x = Integer.parseInt(data[0]);
                        int y = Integer.parseInt(data[1]);
                        int playerNum = Integer.parseInt(data[2]);

                        mainForm.move(x, y, playerNum);
                        break;
                }
    }
}
