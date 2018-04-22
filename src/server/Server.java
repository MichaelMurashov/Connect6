package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static final int PORT = 3443;
    private ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();

    public Server() {
        Socket clientSocket = null;
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запущен!");

            while (true) {
                clientSocket = serverSocket.accept();
                ClientHandler client = new ClientHandler(clientSocket, this);
                clients.add(client);

                if (clients.size() == 2) {
                    new Thread(clients.get(0)).start();
                    clients.get(0).sendMsg("player 1");
                    new Thread(clients.get(1)).start();
                    clients.get(1).sendMsg("player 2");

                    this.sendMessageToAllClients("start game");
                }
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        finally {
            try {
                clientSocket.close();
                System.out.println("Сервер остановлен!");
                serverSocket.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sendMessageToAllClients(String msg) {
        for (ClientHandler o: clients)
            o.sendMsg(msg);
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }
}
