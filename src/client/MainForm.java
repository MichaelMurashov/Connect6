package client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MainForm extends JFrame {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 3443;

    private static Socket clientSocket;
    private static Scanner inMessage;
    private static PrintWriter outMessage;

    public MainForm() {
        try {
            clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            inMessage = new Scanner(clientSocket.getInputStream());
            outMessage = new PrintWriter(clientSocket.getOutputStream());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        setTitle("Connect6 Game");
        setBounds(300, 300, 475, 525);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MainGameField gameField = MainGameField.getInstance();
        add(gameField, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout());
        add(panel, BorderLayout.SOUTH);
        JLabel playerNumber = new JLabel("You are player #");
        panel.add(playerNumber);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (inMessage.hasNext()) {
                            String inMsg = inMessage.nextLine();
                            switch (inMsg) {
                                case "player 1":
                                    gameField.setPlayerNum(0);
                                    playerNumber.setText("You are player #" + Integer.toString(1));
                                    break;

                                case "player 2":
                                    gameField.setPlayerNum(1);
                                    playerNumber.setText("You are player #" + Integer.toString(2));
                                    break;

                                case "start game":
                                    gameField.startNewGame();
                                    break;

                                default:
                                    //   x/y/playerNum
                                    String[] data = inMsg.split("/");
                                    gameField.setXY(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
                                    gameField.game(Integer.parseInt(data[2]));
                                    break;
                            }
                        }
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

        setVisible(true);
    }

    public static void sendMsg(String msg) {
        outMessage.println(msg);
        outMessage.flush();
    }
}
