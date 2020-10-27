package com.epam.xml.parser.data.validator;

import org.junit.Assert;
import org.junit.Test;

public class XmlValidatorTest {
    private static final String SCHEMA_NAME = "src/test/resources/valid/medicines-test.xsd";
    private static final String FILE_NAME = "src/test/resources/valid/medicines-test.xml";
    private static final String INVALID_FILE_NAME = "src/test/resources/invalid/invalid.xml";

    @Test
    public void testValidateShouldValidatingWhenXmlDataCorrect() {
        XmlValidator validator = new XmlValidator(SCHEMA_NAME);
        Assert.assertTrue(validator.validate(FILE_NAME));
    }

    @Test
    public void testValidateShouldLoggingErrorMessageWhenFileNotExists() {
        XmlValidator validator = new XmlValidator(SCHEMA_NAME);
        Assert.assertFalse(validator.validate(INVALID_FILE_NAME));
    }

    @Test
    public void testValidateShouldLoggingErrorMessageWhenXmlNotValid() {
        XmlValidator validator = new XmlValidator(SCHEMA_NAME);
        Assert.assertFalse(validator.validate(INVALID_FILE_NAME));
    }
}
