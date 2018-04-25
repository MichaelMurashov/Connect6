package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 3443;

    private static Socket clientSocket;
    private static Scanner inMessage;
    private static PrintWriter outMessage;

    public static void main(String[] args) {
        try {
            clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            inMessage = new Scanner(clientSocket.getInputStream());
            outMessage = new PrintWriter(clientSocket.getOutputStream());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        MainForm clientWindow = new MainForm();
        new Lifecycle(clientWindow, inMessage).start();
    }

    public static void sendMsg(String msg) {
        outMessage.println(msg);
        outMessage.flush();
    }

}
