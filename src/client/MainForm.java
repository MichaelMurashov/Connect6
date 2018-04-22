package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MainForm extends JFrame {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 3443;

    private Socket clientSocket;
    private Scanner inMessage;
    private PrintWriter outMessage;

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

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (inMessage.hasNext()) {
                            String inMsg = inMessage.nextLine();
                            if (inMsg.equals("start game")) {
                                gameField.startNewGame();
                            } else {
                                System.out.println(inMsg);
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
}
