package com.epam.xml.parser.data.parser.dom;

import com.epam.xml.parser.data.parser.AbstractParser;
import com.epam.xml.parser.data.parser.AbstractParserTest;

public class DomParserTest extends AbstractParserTest {

    @Override
    public AbstractParser createParser() {
        return new DomParser();
    }
}
