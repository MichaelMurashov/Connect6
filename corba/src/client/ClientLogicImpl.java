package client;

import connect6.ClientLogicPOA;

public class ClientLogicImpl extends ClientLogicPOA {

    private LifeCycle lifeCycle;

    public ClientLogicImpl(MainForm mainForm) {
        lifeCycle = new LifeCycle(mainForm);
    }

    @Override
    public void sendMove(String move) {
        CorbaClient.getServerRef().receiveMove(move);
    }

    @Override
    public void receiveMove(String move) {
        System.out.println("Receive - " + move);
        lifeCycle.start(move);
    }
}
