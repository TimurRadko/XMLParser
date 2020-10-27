package com.epam.xml.parser.data.parser.sax;

import com.epam.xml.parser.data.parser.AbstractParser;
import com.epam.xml.parser.model.LiquidMedicine;
import com.epam.xml.parser.model.TabletMedicine;
import com.epam.xml.parser.model.AbstractMedicine;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import static com.epam.xml.parser.data.parser.AbstractParser.*;

public class MedicineHandler extends DefaultHandler {
    private final List<AbstractMedicine> medicines = new ArrayList<>();
    private AbstractMedicine current;
    private String currentElement;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (isMedicineTag(localName)) {
            current = AbstractParser.create(localName);
            current.setGroupPharmacy(attributes.getValue(GROUP_PHARMACY));
            if (attributes.getLength() == 2) {
                TabletMedicine tabletMedicine = (TabletMedicine) current;
                tabletMedicine.setConcentration(attributes.getValue(CONCENTRATION));
                current = tabletMedicine;
            }
        } else {
            if (FIELD_LIST.contains(localName)) {
                currentElement = localName;
            }
        }
    }

    private boolean isMedicineTag(String localName) {
        return TABLET.equals(localName) | LIQUID.equals(localName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (isMedicineTag(localName)) {
            medicines.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
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
