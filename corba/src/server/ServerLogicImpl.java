package server;

import connect6.ClientLogic;
import connect6.ServerLogicPOA;

import java.util.ArrayList;

public class ServerLogicImpl extends ServerLogicPOA {

    private ArrayList<ClientLogic> clients = new ArrayList<>();

    @Override
    public void registerClient(ClientLogic client) {
        clients.add(client);
        System.out.println("Client was registered!");
        if (clients.size() == 2) {
            sendMove(clients.get(0),"set player 1");
            sendMove(clients.get(1), "set player 2");

            for (ClientLogic _client: clients)
                sendMove(_client, "start game");
        }
    }

    @Override
    public void sendMove(ClientLogic client, String move) {
        client.receiveMove(move);
    }

    @Override
    public void receiveMove(String move) {
        System.out.println(move);
        for (ClientLogic client: clients)
            sendMove(client, move);
    }
}
