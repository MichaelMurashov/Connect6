package server;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import connect6.ServerLogic;
import connect6.ServerLogicHelper;

public class CorbaServer {

    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);

            // Получение ссылки и активирование POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // Создание серванта для CORBA-объекта
            ServerLogicImpl gameImpl = new ServerLogicImpl();

            // Получение объектной ссылки на сервант
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(gameImpl);
            ServerLogic href = ServerLogicHelper.narrow(ref);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Связывание объектной ссылки с именем
            String name = "Server";
            NameComponent path[] = ncRef.to_name( name );
            ncRef.rebind(path, href);

            System.out.println("Server is ready!");

            // Ожидание обращений от клиентов
            orb.run();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);   e.printStackTrace(System.out);
        }

        System.out.println("Server Exiting ...");
    }
}
