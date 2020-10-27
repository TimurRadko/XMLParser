package com.epam.xml.parser.data.parser.jaxb;

import com.epam.xml.parser.data.parser.AbstractParser;
import com.epam.xml.parser.data.parser.AbstractParserTest;

public class JaxbParserTest extends AbstractParserTest {

    @Override
    public AbstractParser createParser() {
        return new JaxbParser();
    }
}
