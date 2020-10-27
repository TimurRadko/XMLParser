package com.epam.xml.parser.data.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {
    private final static Logger LOGGER = LogManager.getLogger(XmlValidator.class);
    private final String schemaName;

    public XmlValidator(String schemaName) {
        this.schemaName = schemaName;
    }

    public boolean validate(String fileName) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        boolean isValid;
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.validate(source);
            LOGGER.info(String.format("%s validating is ended.", fileName));
            isValid = true;
        } catch (IOException | SAXException e) {
            LOGGER.error(String.format("%s is not valid because %s%n", fileName, e.getMessage()));
            isValid = false;
        }
        return isValid;
    }
}
