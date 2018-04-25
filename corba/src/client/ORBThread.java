package client;


import org.omg.CORBA.ORB;

public class ORBThread extends Thread {

    private ORB myOrb;

    public ORBThread(ORB orb) {
        myOrb = orb;
    }

    @Override
    public void run() {
        myOrb.run();
//        while (true) {
//            int[] msg = stub.getOpponentMove();
//            if (msg.length != 0) {
//                switch (msg[0]) {
//                    case -1:
//                        mainForm.setPlayerFirst(true);
//                        break;
//
//                    case -2:
//                        mainForm.setPlayerFirst(false);
//                        break;
//
//                    case -3:
//                        mainForm.startGame();
//                        break;
//
//                    default:
//                        //   x/y/playerNum
//                        int x = msg[1];
//                        int y = msg[2];
//                        int playerNum = msg[3];
//
//                        mainForm.move(x, y, playerNum);
//                        break;
//                }
//            }
//        }
    }
}
