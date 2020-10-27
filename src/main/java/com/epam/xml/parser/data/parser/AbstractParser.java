package com.epam.xml.parser.data.parser;

import com.epam.xml.parser.exception.ParseException;
import com.epam.xml.parser.model.AbstractMedicine;
import com.epam.xml.parser.model.LiquidMedicine;
import com.epam.xml.parser.model.TabletMedicine;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractParser {
    public static final String TABLET = "tablet";
    public static final String GROUP_PHARMACY = "group-pharmacy";
    public static final String CONCENTRATION = "concentration";
    public static final String PRODUCER = "producer";
    public static final String PRODUCER_NAME = "producer-name";
    public static final String LIQUID = "liquid";
    public static final String NAME = "name";
    public static final String LICENSE_NUMBER = "license-number";
    public static final String QUANTITY = "quantity";
    public static final String WEIGHT = "weight";
    public static final String ALCOHOL_CONCENTRATION = "alcohol-concentration";
    public static final List<String> TAG_LIST = Arrays.asList(TABLET, LIQUID);
    public static final List<String> FIELD_LIST =
            Arrays.asList(NAME, LICENSE_NUMBER, QUANTITY, WEIGHT, ALCOHOL_CONCENTRATION, PRODUCER_NAME);

    public abstract List<AbstractMedicine> parse(String filename) throws ParseException;

    public static AbstractMedicine create(String tagName) {
        switch (tagName) {
            case TABLET:
                return new TabletMedicine();
            case LIQUID:
                return new LiquidMedicine();
            default:
                throw new IllegalArgumentException("Tag not found");
        }
    }
}
