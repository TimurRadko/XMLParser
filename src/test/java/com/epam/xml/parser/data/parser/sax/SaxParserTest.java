package com.epam.xml.parser.data.parser.sax;

import com.epam.xml.parser.data.parser.AbstractParser;
import com.epam.xml.parser.data.parser.AbstractParserTest;

public class SaxParserTest extends AbstractParserTest {

    @Override
    public AbstractParser createParser() {
        return new SaxParser();
    }
}
