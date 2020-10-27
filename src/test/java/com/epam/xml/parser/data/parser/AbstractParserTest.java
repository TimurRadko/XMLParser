package com.epam.xml.parser.data.parser;

import com.epam.xml.parser.exception.ParseException;
import com.epam.xml.parser.model.AbstractMedicine;
import com.epam.xml.parser.model.LiquidMedicine;
import com.epam.xml.parser.model.Producer;
import com.epam.xml.parser.model.TabletMedicine;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractParserTest {
    private static final String VALID_DATA_FILE = "src/test/resources/valid/medicines-test.xml";
    private static final String INVALID_DATA_FILE = "src/test/resources/invalid/medicines-test-invalid.xml";

    private static final Producer FIRST_PRODUCER = new Producer("Bayer", "ID1");
    private static final TabletMedicine FIRST_TABLET = new TabletMedicine("Aspirin",
            FIRST_PRODUCER, "painkiller", 100, "low");

    private static final Producer SECOND_PRODUCER = new Producer("BelMedications", "ID2");
    private static final TabletMedicine SECOND_TABLET = new TabletMedicine("Activated Carbon",
            SECOND_PRODUCER, "vitamin", 50, "high");

    private static final Producer THIRD_PRODUCER = new Producer("BelMedications", "ID3");
    private static final LiquidMedicine THIRD_LIQUID = new LiquidMedicine("Valerian Root",
            THIRD_PRODUCER, "vitamin", 55, 33);

    private static final List<AbstractMedicine> EXPECTED_MEDICINES = Arrays.asList(FIRST_TABLET, SECOND_TABLET,
            THIRD_LIQUID);

    @Test
    public void testParseShouldReturnListWhenDataIsCorrect() throws ParseException {
        AbstractParser parser = createParser();

        List<AbstractMedicine> actualMedicines = parser.parse(VALID_DATA_FILE);

        Assert.assertEquals(EXPECTED_MEDICINES, actualMedicines);
    }

    @Test(expected = ParseException.class)
    public void testParseShouldThrowExceptionWhenFileNotExists() throws ParseException {
        AbstractParser parser = createParser();

        parser.parse(INVALID_DATA_FILE);
    }

    public abstract AbstractParser createParser();
}
