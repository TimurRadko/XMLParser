package com.epam.xml.parser.data.parser;

import com.epam.xml.parser.data.LogAppenderResource;
import com.epam.xml.parser.model.AbstractMedicine;
import com.epam.xml.parser.model.TabletMedicine;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class DomParserTest {
    private static final String FILE_NAME = "src/test/resources/valid/medicines-test.xml";
    private static final String INVALID_DATA_FILE = "src/test/resources/invalid/medicines-test-invalid.xml";
    private static final String INVALID_FILE_NAME = "src/test/resources/valid/medicines1.xml";
    private static final int EXPECTED_SIZE = 2;
    private TabletMedicine expectedMedicine;
    private PrepareEntityToParser prepareEntityToParser;
    private static final String EXPECTED_MESSAGE_FATAL = "FATAL File not found.";
    private static final String EXPECTED_MESSAGE_WARN =
            "WARN  SAX Parser failed with cause org.xml.sax.SAXParseException; systemId: " +
                    "file:///E:/Programming/TestingFolder/Epam/XMLParser/src/test/resources/invalid/medicines-test-invalid.xml; " +
                    "lineNumber: 23; columnNumber: 3; The element type \"tablet\" must be terminated by the matching end-tag \"</tablet>\".";
    @Rule
    public LogAppenderResource appender = new LogAppenderResource(LogManager.getLogger(DomParser.class));

    @Before
    public void createValidEntity() {
        prepareEntityToParser = new PrepareEntityToParser();
        expectedMedicine = prepareEntityToParser.getMedicine();
    }

    @Test
    public void testParseShouldReturnCorrectListSizeWhenXmlDocumentExists() {
        DomParser parser = new DomParser();
        List<AbstractMedicine> medicineList = parser.parse(FILE_NAME);
        Assert.assertEquals(EXPECTED_SIZE, medicineList.size());
    }

    @Test
    public void testParseShouldReturnCorrectEntityWhenXmlDocumentExists() {
        DomParser parser = new DomParser();
        List<AbstractMedicine> medicineList = parser.parse(FILE_NAME);
        TabletMedicine actualTablet = (TabletMedicine) medicineList.get(0);
        Assert.assertEquals(expectedMedicine, actualTablet);
    }

    @Test
    public void testParseShouldEndParsingWhenFileNameIsInvalid() {
        DomParser parser = new DomParser();
        parser.parse(INVALID_FILE_NAME);
        assertThat(appender.getOutput(), containsString(EXPECTED_MESSAGE_FATAL));
    }

    @Test
    public void testParseShouldLoggingWarnWhenEntityNotValid() {
        DomParser parser = new DomParser();
        parser.parse(INVALID_DATA_FILE);
        assertThat(appender.getOutput(), containsString(EXPECTED_MESSAGE_WARN));
    }
}
