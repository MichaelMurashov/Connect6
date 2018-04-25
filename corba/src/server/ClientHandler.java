//package server;
//
//import serverlogic.ServerLogic;
//
//import java.util.Arrays;
//
//public class ClientHandler implements Runnable {
//
//    private ServerLogic stub;
//
////    private Server server;
////    private PrintWriter outMessage;
////    private Scanner inMessage;
//
////    private static final String HOST = "localhost";
////    private static final int PORT = 3443;
//
//    public ClientHandler(ServerLogic _stub) {
//        stub = _stub;
////        try {
//////            this.server = server;
//////            this.outMessage = new PrintWriter(socket.getOutputStream());
//////            this.inMessage = new Scanner(socket.getInputStream());
////        }
////        catch (IOException ex) {
////            ex.printStackTrace();
////        }
//    }
//
//    @Override
//    public void run() {
//        try {
//            while (true) {
//                int[] msg = stub.getOpponentMove();
//                if (msg.length != 0) {
//                    System.out.println(Arrays.toString(msg));
//                    CorbaServer.sendMessageToAllClients(msg);
//            }
//                Thread.sleep(100);
//            }
//        }
//        catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
////        finally {
////            this.close();
////        }
//    }
//
//    public void sendMsg(int[] msg) {
//        stub.setMyMove(msg[0], msg[1], msg[2], msg[3]);
//    }
//
////    public void close() {
////        server.removeClient(this);
////    }
//}
