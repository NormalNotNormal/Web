import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;



public class App {
    public static final String UNIC_BINDING_NAME = "server.library.validator";

    public static void main(String[] args) throws Exception {
        final Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1217);
        LibraryValidator service = (LibraryValidator) registry.lookup(UNIC_BINDING_NAME);

        List<Element> bookcases = getnumbers();
        for (int i = 0; i < bookcases.size(); i++) {
            Element bookcase = bookcases.get(i);

            int result = service.getCountPriceAverage(bookcase);
            System.out.println(result);
        }
    }


    private static List<Element> getnumbers() {
        List<Element> numberResult = new ArrayList<Element>();
        DocumentBuilder documentBuilder;

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setValidating(true);

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();

            File file = new File("src/XML/Library.xml");

            Document document = documentBuilder.parse(file);

            NodeList bookcases = document.getElementsByTagName("bookcase");
            for (int i = 0; i < bookcases.getLength(); i++) {
                Element bookshelf = (Element) bookcases.item(i);

                numberResult.add(bookshelf);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return numberResult;
    }
}