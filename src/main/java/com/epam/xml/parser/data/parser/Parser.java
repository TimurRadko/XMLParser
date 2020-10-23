package com.epam.xml.parser.data.parser;

import com.epam.xml.parser.model.medicine.AbstractMedicine;

import java.util.List;

public interface Parser {
    List<AbstractMedicine> parse(String filename);
}
