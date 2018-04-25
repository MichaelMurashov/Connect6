package client;

import connect6.ClientLogic;
import connect6.ClientLogicHelper;
import connect6.ServerLogic;
import connect6.ServerLogicHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;


public class CorbaClient {

    private static ClientLogic ref;
    private static ServerLogic serverRef;

    public static void main(String[] args) {
        try {
            MainForm mainForm = new MainForm();

            ORB orb = ORB.init(args, null);

            // Создание серванта для IDL-интерфейса
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();
            ClientLogicImpl listener = new ClientLogicImpl(mainForm);
            rootPOA.activate_object(listener);

            // Получение ссылки на сервант
            ref = ClientLogicHelper.narrow(rootPOA.servant_to_reference(listener));

            // Получение контекста именования
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Преобразование имени серванта в объектную ссылку
            NameComponent nc = new NameComponent("Server", "");
            NameComponent path[] = {nc};
            serverRef = ServerLogicHelper.narrow(ncRef.resolve(path));

            // регистрация клиента на сервере
            serverRef.registerClient(ref);

            // Запуск ORB в отдельном потоке управления для прослушивания ходов
            ORBThread ornThr = new ORBThread(orb);
            ornThr.start();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public static ClientLogic getRef() { return ref; }
    public static ServerLogic getServerRef() { return serverRef; }
}
