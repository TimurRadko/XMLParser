package com.epam.xml.parser.data.validator;

import com.epam.xml.parser.data.LogAppenderResource;
import org.apache.logging.log4j.LogManager;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class XmlValidatorTest {
    private static final String SCHEMA_NAME = "src/test/resources/medicines-test.xsd";
    private static final String FILE_NAME = "src/test/resources/medicines-test.xml";
    private static final String INVALID_FILE_NAME = "src/test/resources/invalid.xml";
    private static final String INVALID_SCHEMA_NAME = "src/test/resources/medicines-test.xsd";
    private static final String EXPECTED_MESSAGE_INFO = "INFO  src/test/resources/medicines-test.xml validating is ended.";
    private static final String EXPECTED_MESSAGE_FATAL =
            "FATAL src/test/resources/invalid.xml is not valid because E:\\Programming\\TestingFolder\\Epam\\XMLParser\\src\\test\\resources\\invalid.xml (Не удается найти указанный файл)";
    @Rule
    public LogAppenderResource appender = new LogAppenderResource(LogManager.getLogger(XmlValidator.class));

    @Test
    public void testValidateShouldValidatingWhenXmlDataCorrect() {
        XmlValidator validator = new XmlValidator();
        validator.validate(FILE_NAME, SCHEMA_NAME);
        assertThat(appender.getOutput(), containsString(EXPECTED_MESSAGE_INFO));
    }

    @Test
    public void testValidateShouldThrowExceptionWhenFileNotExists() {
        XmlValidator validator = new XmlValidator();
        validator.validate(INVALID_FILE_NAME, SCHEMA_NAME);
        assertThat(appender.getOutput(), containsString(EXPECTED_MESSAGE_FATAL));
    }

    @Test
    public void testValidateShouldThrowExceptionWhenXmlNotMatchToXsd() {
        XmlValidator validator = new XmlValidator();
        validator.validate(INVALID_FILE_NAME, INVALID_SCHEMA_NAME);
        assertThat(appender.getOutput(), containsString(EXPECTED_MESSAGE_FATAL));
    }
}
