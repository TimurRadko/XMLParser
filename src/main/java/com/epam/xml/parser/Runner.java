package com.epam.xml.parser;

import com.epam.xml.parser.data.parser.DomParser;
import com.epam.xml.parser.data.parser.JaxbParser;
import com.epam.xml.parser.data.parser.SaxParser;
import com.epam.xml.parser.data.validator.XmlValidator;
import com.epam.xml.parser.model.AbstractMedicine;
import com.epam.xml.parser.model.Medicines;

import java.util.List;

public class Runner {
    private static final String FILE_NAME = "data/medicines.xml";
    private static final String SCHEMA_NAME = "data/medicines.xsd";

    public static void main(String[] args) {
        XmlValidator validator = new XmlValidator();
        validator.validate(FILE_NAME, SCHEMA_NAME);
//        SaxParser parser = new SaxParser();
//        List<AbstractMedicine> medicines = parser.parse(FILE_NAME);
//
//        DomParser parser = new DomParser();
//        List<AbstractMedicine> medicines = parser.parse(FILE_NAME);

        JaxbParser parser = new JaxbParser();
        List<AbstractMedicine> medicines = parser.parse(FILE_NAME);

        Medicines pharmacy = new Medicines();
        pharmacy.setListMedicines(medicines);
        System.out.println(pharmacy);
    }
}
