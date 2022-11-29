package xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import svg.Path;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class Xml {

    public static void CreatePathElement(Document document, Element vector, Path p) {
        Element path = document.createElement("path");

        vector.appendChild(path);

        path.setAttribute("android:" + Path.Fields.strokeWidth, p.strokeWidth);
        path.setAttribute("android:" + Path.Fields.strokeColor, p.strokeColor);
        path.setAttribute("android:" + Path.Fields.fillColor, p.fillColor);
        path.setAttribute("android:" + Path.Fields.pathData, p.pathData);
    }

    public static void GenerateXMLFile(String filePath, List<Path> pathsInnerCircle, List<Path> pathsMiddleCircle, List<Path> pathsOuterCircle) {
        Vector v = new Vector("200dp", "200dp", "200.0", "200.0");

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element vector = document.createElement("vector");
            document.appendChild(vector);

            vector.setAttribute("xmlns:android","http://schemas.android.com/apk/res/android");
            vector.setAttribute("android:" + Vector.Fields.width, v.width);
            vector.setAttribute("android:" + Vector.Fields.height, v.height);
            vector.setAttribute("android:" + Vector.Fields.viewportWidth, v.viewportWidth);
            vector.setAttribute("android:" + Vector.Fields.viewportHeight, v.viewportHeight);

            for (Path p : pathsInnerCircle) {
                CreatePathElement(document, vector, p);
            }

            for (Path p : pathsMiddleCircle) {
                CreatePathElement(document, vector, p);
            }

            for (Path p : pathsOuterCircle) {
                CreatePathElement(document, vector, p);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(filePath));

            transformer.transform(domSource, streamResult);

            System.out.println("Done creating xml.XML File");
        } catch (ParserConfigurationException | TransformerException ex) {
            ex.printStackTrace();
        }
    }
}
