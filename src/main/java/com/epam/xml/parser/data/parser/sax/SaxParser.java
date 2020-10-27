package com.epam.xml.parser.data.parser.sax;

import com.epam.xml.parser.data.parser.AbstractParser;
import com.epam.xml.parser.exception.ParseException;
import com.epam.xml.parser.model.AbstractMedicine;
import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SaxParser extends AbstractParser {

    @Override
    public List<AbstractMedicine> parse(String fileName) throws ParseException {
        List<AbstractMedicine> medicines;
        MedicineHandler handler = new MedicineHandler();
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
            reader.parse(fileName);
        } catch (SAXException | IOException e) {
            throw new ParseException(e.getMessage(), e);
        }
        medicines = handler.getMedicines();
        return medicines;
    }
}
