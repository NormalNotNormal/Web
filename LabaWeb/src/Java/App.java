package Java;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class App {
    private static String File_Path;
    private static String File_Pathout;

    public static void main(String[] args) {
        File_Path = "C:/Users/pas20/IdeaProjects/LabaWeb/src/XML";
        File sourceFile = new File(File_Path);
        File outputFile = new File(File_Pathout);
        LibraryValidator validator = new LibraryValidator();
        try {
            validator.validator(sourceFile, outputFile);
        } catch (TransformerException e) {
            System.err.println("Произошла внутренняя ошибка.");
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}