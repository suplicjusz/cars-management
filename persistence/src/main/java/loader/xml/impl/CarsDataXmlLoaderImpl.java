package loader.xml.impl;

import loader.xml.CarsDataXmlLoader;
import model.car.CarData;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import model.enums.Color;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@ToString
public class CarsDataXmlLoaderImpl implements CarsDataXmlLoader {


    // https://mkyong.com/java/java-convert-string-to-xml/


    @Override
    public List<CarData> load(String path) {

        try {
            var result = new ArrayList<CarData>();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(path));

            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName("cars");


            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String id = element
                            .getElementsByTagName("id")
                            .item(0)
                            .getTextContent();
                    String model = element
                            .getElementsByTagName("model")
                            .item(0)
                            .getTextContent();
                    String price = element
                            .getElementsByTagName("price")
                            .item(0)
                            .getTextContent();
                    String color = element
                            .getElementsByTagName("color")
                            .item(0)
                            .getTextContent();
                    String mileage = element
                            .getElementsByTagName("mileage")
                            .item(0)
                            .getTextContent();
                    var componentsNode = element
                            .getElementsByTagName("components")
                            .item(0)
                            .getChildNodes();
                    List<String> components = new ArrayList<>();
                    for (int i = 0; i < componentsNode.getLength(); i++) {
                        components.add(componentsNode.item(i).getTextContent());
                    }
                    result.add(
                            CarData
                                    .builder()
                                    .id(Long.parseLong(id))
                                    .model(model)
                                    .price(new BigDecimal(price))
                                    .color(Color.valueOf(color))
                                    .mileage(Double.parseDouble(mileage))
                                    .components(components)
                                    .build()
                    );
                }
            }
            return result;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }

    }
}
