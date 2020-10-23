package com.epam.xml.parser.data.parser;

import com.epam.xml.parser.model.Producer;
import com.epam.xml.parser.model.medicine.AbstractMedicine;
import com.epam.xml.parser.model.medicine.LiquidMedicine;
import com.epam.xml.parser.model.medicine.TabletMedicine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.util.Arrays;
import java.util.List;

public class DomParser implements Parser {
    private final static Logger LOGGER = LogManager.getLogger(DomParser.class);
    private static final String TABLET = "tablet";
    private static final String GROUP_PHARMACY = "group-pharmacy";
    private static final String CONCENTRATION = "concentration";
    private static final String PRODUCER = "producer";
    private static final String PRODUCER_NAME = "producer-name";
    private static final String LIQUID = "liquid";
    private static final String NAME = "name";
    private static final String LICENSE_NUMBER = "license-number";
    private static final String QUANTITY = "quantity";
    private static final String WEIGHT = "weight";
    private static final String ALCOHOL_CONCENTRATION = "alcohol-concentration";
    private static final List<String> TAG_LIST = Arrays.asList(TABLET, LIQUID);

    @Override
    public List<AbstractMedicine> parse(String fileName) {
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
        } catch (ParserConfigurationException e) {
            LOGGER.warn(String.format("DOM Parser failed with cause %s", e));
        } catch (SAXException e) {
            LOGGER.warn(String.format("SAX Parser failed with cause %s", e));
        } catch (IOException e) {
            LOGGER.fatal("File not found.");
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
        AbstractMedicine current = create(medicineElement);
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

    private AbstractMedicine create(Element medicineElement) {
        String tagName = medicineElement.getTagName();
        switch (tagName) {
            case TABLET:
                return new TabletMedicine();
            case LIQUID:
                return new LiquidMedicine();
            default:
                throw new IllegalArgumentException("Tag not found");
        }
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}