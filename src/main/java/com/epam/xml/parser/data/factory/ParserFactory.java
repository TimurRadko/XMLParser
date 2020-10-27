package com.epam.xml.parser.data.factory;

import com.epam.xml.parser.data.parser.AbstractParser;

public interface ParserFactory {
    AbstractParser create(ParserType type);
}
