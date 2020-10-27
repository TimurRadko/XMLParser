package com.epam.xml.parser.data.parser.dom;

import com.epam.xml.parser.data.parser.AbstractParser;
import com.epam.xml.parser.exception.ParseException;
import com.epam.xml.parser.model.Producer;
import com.epam.xml.parser.model.AbstractMedicine;
import com.epam.xml.parser.model.LiquidMedicine;
import com.epam.xml.parser.model.TabletMedicine;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser extends AbstractParser {

    @Override
    public List<AbstractMedicine> parse(String fileName) throws ParseException {
        List<AbstractMedicine> medicines = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document doc = documentBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            for (String value : TAG_LIST) {
                NodeList medicinesList = root.getElementsByTagName(value);
                addMedicineToList(medicines, medicinesList);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ParseException(e.getMessage(), e);
        }
        return medicines;
    }

    private void addMedicineToList(List<AbstractMedicine> medicines, NodeList medicinesList) {
        for (int i = 0; i < medicinesList.getLength(); i++) {
            Element medicineElement = (Element) medicinesList.item(i);
            AbstractMedicine medicine = buildAbstractMedicine(medicineElement);
            medicines.add(medicine);
        }
    }

    private AbstractMedicine buildAbstractMedicine(Element medicineElement) {
        String tagName = medicineElement.getTagName();
        AbstractMedicine current = create(tagName);
        current.setGroupPharmacy(medicineElement.getAttribute(GROUP_PHARMACY));
        current.setName(getElementTextContent(medicineElement, NAME));
        Producer producer = new Producer();
        Element producerElement = (Element) medicineElement.getElementsByTagName(PRODUCER).item(0);
        producer.setProducerName(getElementTextContent(producerElement, PRODUCER_NAME));
        producer.setLicenseNumber(getElementTextContent(producerElement, LICENSE_NUMBER));
        current.setProducer(producer);
        addSpecialMedicineParameters(medicineElement, current);
        return current;
    }

    private void addSpecialMedicineParameters(Element medicineElement, AbstractMedicine current) {
        if (current instanceof LiquidMedicine) {
            getParametersLiquidMedicine(medicineElement, current);
        }
        if (current instanceof TabletMedicine) {
            getParametersTabletMedicine(medicineElement, current);
        }
    }

    private void getParametersLiquidMedicine(Element medicineElement, AbstractMedicine current) {
        LiquidMedicine liquidMedicine = (LiquidMedicine) current;
        String weight = getElementTextContent(medicineElement, WEIGHT);
        liquidMedicine.setWeight(Integer.parseInt(weight));
        String alcoholConcentration = getElementTextContent(medicineElement, ALCOHOL_CONCENTRATION);
        liquidMedicine.setAlcoholConcentration(Integer.parseInt(alcoholConcentration));
    }

    private void getParametersTabletMedicine(Element medicineElement, AbstractMedicine current) {
        TabletMedicine tabletMedicine = (TabletMedicine) current;
        String quantity = getElementTextContent(medicineElement, QUANTITY);
        tabletMedicine.setConcentration(medicineElement.getAttribute(CONCENTRATION));
        tabletMedicine.setQuantity(Integer.parseInt(quantity));
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}