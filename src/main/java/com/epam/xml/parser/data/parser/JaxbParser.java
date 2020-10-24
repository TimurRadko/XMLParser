package com.epam.xml.parser.data.parser;

import com.epam.xml.parser.model.AbstractMedicine;
import com.epam.xml.parser.model.Medicines;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JaxbParser implements Parser {
    private final static Logger LOGGER = LogManager.getLogger(JaxbParser.class);
    private static final String SCHEMA_NAME = "data/medicines.xsd";
    private static final String PARENT_ENTITY = "com.epam.xml.parser.model";

    @Override
    public List<AbstractMedicine> parse(String fileName) {
        List<AbstractMedicine> listMedicines = new ArrayList<>();
        try {
            JAXBContext context = JAXBContext.newInstance(PARENT_ENTITY);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaLocation = new File(SCHEMA_NAME);
            Schema schema = factory.newSchema(schemaLocation);
            unmarshaller.setSchema(schema);
            Medicines medicines = (Medicines) unmarshaller.unmarshal(new File(fileName));
            listMedicines = medicines.getMedicines();
        } catch (JAXBException e) {
            LOGGER.warn(String.format("JAXB Parser failed with cause %s", e));
        } catch (SAXException e) {
            LOGGER.warn(String.format("SAX Parser failed with cause %s", e));
        }
        return listMedicines;
    }
}
