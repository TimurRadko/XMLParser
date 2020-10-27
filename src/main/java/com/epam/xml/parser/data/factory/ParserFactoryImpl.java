package com.epam.xml.parser.data.factory;

import com.epam.xml.parser.data.parser.AbstractParser;
import com.epam.xml.parser.data.parser.dom.DomParser;
import com.epam.xml.parser.data.parser.jaxb.JaxbParser;
import com.epam.xml.parser.data.parser.sax.SaxParser;

public class ParserFactoryImpl implements ParserFactory {

    public AbstractParser create(ParserType type) {
        switch (type) {
            case DOM:
                return new DomParser();
            case SAX:
                return new SaxParser();
            case JAXB:
                return new JaxbParser();
            default:
                throw new IllegalArgumentException("Parser Type not found");
        }
    }
}