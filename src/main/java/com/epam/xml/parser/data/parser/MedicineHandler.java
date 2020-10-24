package com.epam.xml.parser.data.parser;

import com.epam.xml.parser.model.LiquidMedicine;
import com.epam.xml.parser.model.TabletMedicine;
import com.epam.xml.parser.model.AbstractMedicine;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MedicineHandler extends DefaultHandler {
    private final List<AbstractMedicine> medicines = new ArrayList<>();
    private AbstractMedicine current;
    private static final String TABLET = "tablet";
    private static final String LIQUID = "liquid";
    private static final String GROUP_PHARMACY = "group-pharmacy";
    private static final String CONCENTRATION = "concentration";
    private String currentElement;

    private static final String PRODUCER_NAME = "producer-name";
    private static final String NAME = "name";
    private static final String LICENSE_NUMBER = "license-number";
    private static final String QUANTITY = "quantity";
    private static final String WEIGHT = "weight";
    private static final String ALCOHOL_CONCENTRATION = "alcohol-concentration";
    private static final List<String> TAG_LIST =
            Arrays.asList(NAME, LICENSE_NUMBER, QUANTITY, WEIGHT, ALCOHOL_CONCENTRATION, PRODUCER_NAME);

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (isMedicineTag(localName)) {
            current = create(localName);
            current.setGroupPharmacy(attributes.getValue(GROUP_PHARMACY));
            if (attributes.getLength() == 2) {
                TabletMedicine tabletMedicine = (TabletMedicine) current;
                tabletMedicine.setConcentration(attributes.getValue(CONCENTRATION));
                current = tabletMedicine;
            }
        } else {
            if (TAG_LIST.contains(localName)) {
                currentElement = localName;
            }
        }
    }

    private AbstractMedicine create(String localName) {
        switch (localName) {
            case TABLET:
                return new TabletMedicine();
            case LIQUID:
                return new LiquidMedicine();
            default:
                throw new IllegalArgumentException("Tag not found");
        }
    }

    private boolean isMedicineTag(String localName) {
        return TABLET.equals(localName) | LIQUID.equals(localName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (isMedicineTag(localName)) {
            medicines.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length).intern().trim();
        TabletMedicine tabletMedicine;
        LiquidMedicine liquidMedicine;
        if (currentElement != null) {
            switch (currentElement) {
                case NAME:
                    current.setName(s);
                    break;
                case PRODUCER_NAME:
                    current.getProducer().setProducerName(s);
                    break;
                case LICENSE_NUMBER:
                    current.getProducer().setLicenseNumber(s);
                    break;
                case QUANTITY:
                    tabletMedicine = (TabletMedicine) current;
                    tabletMedicine.setQuantity(Integer.parseInt(s));
                    current = tabletMedicine;
                    break;
                case WEIGHT:
                    liquidMedicine = (LiquidMedicine) current;
                    liquidMedicine.setWeight(Integer.parseInt(s));
                    current = liquidMedicine;
                    break;
                case ALCOHOL_CONCENTRATION:
                    liquidMedicine = (LiquidMedicine) current;
                    liquidMedicine.setAlcoholConcentration(Integer.parseInt(s));
                    current = liquidMedicine;
                    break;
                default:
                    throw new IllegalArgumentException("Tag not found");
            }
            currentElement = null;
        }
    }

    public List<AbstractMedicine> getMedicines() {
        return medicines;
    }
}
