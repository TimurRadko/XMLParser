package com.epam.xml.parser.data.factory;

import com.epam.xml.parser.data.parser.DomParser;
import com.epam.xml.parser.data.parser.JaxbParser;
import com.epam.xml.parser.data.parser.SaxParser;
import com.epam.xml.parser.data.parser.Parser;

public class ParserFactoryImpl implements ParserFactory {

    public Parser create(ParserType type) {
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