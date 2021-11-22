import java.rmi.Remote;
import java.rmi.RemoteException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class LibraryValidatorImpl implements LibraryValidator, Remote {
    public LibraryValidatorImpl() {
    }

    public int getCountPriceAverage(Element bookcase) throws RemoteException {
        NodeList books = bookcase.getElementsByTagName("book");
        int count = 0;

        for(int j = 0; j < books.getLength(); ++j) {
            Element book = (Element)books.item(j);
            Element bookprice = (Element)book.getElementsByTagName("bookprice").item(0);
            count += Integer.parseInt(bookprice.getAttribute("price"));
        }

        return count;
    }
}