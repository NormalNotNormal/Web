import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class App {
    public static final String UNIC_BINDING_NAME = "server.Earliest.validator";

    public App() {

    }

    public static void main(String[] args) throws Exception {
        LibraryValidatorImpl service = new LibraryValidatorImpl();
        Registry registry = LocateRegistry.createRegistry(1217);
        Remote stub = UnicastRemoteObject.exportObject(service, 0);
        registry.bind("server.library.validator", stub);
        Thread.sleep(2147483647L);
    }
}