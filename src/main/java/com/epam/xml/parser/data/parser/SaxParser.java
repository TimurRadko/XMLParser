package com.epam.xml.parser.data.parser;

import com.epam.xml.parser.model.medicine.AbstractMedicine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SaxParser implements Parser {
    private final static Logger LOGGER = LogManager.getLogger(SaxParser.class);

    @Override
    public List<AbstractMedicine> parse(String fileName) {
        List<AbstractMedicine> medicines;
        MedicineHandler handler = new MedicineHandler();
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
            reader.parse(fileName);
        } catch (SAXException e) {
            LOGGER.warn(String.format("SAX Parser failed with cause %s", e));
        } catch (IOException e) {
            LOGGER.fatal("File not found.");
        }
        medicines = handler.getMedicines();
        return medicines;
    }
}
