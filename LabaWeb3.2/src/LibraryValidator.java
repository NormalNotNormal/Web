import java.rmi.Remote;
import java.rmi.RemoteException;
import org.w3c.dom.Element;

public interface LibraryValidator extends Remote {
    int getCountPriceAverage (Element var1) throws RemoteException;
}