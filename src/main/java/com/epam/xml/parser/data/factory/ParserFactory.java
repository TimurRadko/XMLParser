package com.epam.xml.parser.data.factory;

import com.epam.xml.parser.data.parser.Parser;

public interface ParserFactory {
    Parser create(ParserType type);
}
