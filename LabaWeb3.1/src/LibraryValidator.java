import org.w3c.dom.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LibraryValidator extends Remote {
    int getCountPriceAverage(Element bookcase) throws RemoteException;
}
