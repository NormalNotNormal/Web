package Java;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class LibraryValidator {
    private DocumentBuilder documentBuilder;

    public void validator(File sourceFile,File ouputFile) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        Document document;
        File file = new File("src/LabaWeb/XML/Library.xml");
        File outputFile = new File("src/LabaWeb/XML/Libraryout.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(file);

        NodeList bookshelfs = document.getElementsByTagName("bookshelf");
        for (int i=0; i<bookshelfs.getLength(); ++i) {
            Element bookshelf = (Element) bookshelfs.item(i);
            int allBookPriceAverage = getCountPriceAverage(bookshelf);
            Node PriceAverage;
            PriceAverage = document.createElement("PageAverage");
            bookshelf.appendChild(PriceAverage);
            PriceAverage.setTextContent(String.valueOf(allBookPriceAverage));
        }
        saveOnDisk(document, outputFile);
    }

    private int getCountPriceAverage(Element bookshelf) {

        NodeList books = bookshelf.getElementsByTagName("book");
        int count = 0;
        for (int j=0; j<books.getLength();j++){
            Element book = (Element)books.item(j);
            Element bookprice = (Element) book.getElementsByTagName("bookprice").item(0);
            count += Integer.parseInt(bookprice.getAttribute("Price"));
        }
        return count;
    }

    private void saveOnDisk (Document doc,File output) throws  TransformerException{

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(doc), new StreamResult(output));

    }
}