package com.epam.xml.parser.data.parser.jaxb;

import com.epam.xml.parser.data.parser.AbstractParser;
import com.epam.xml.parser.exception.ParseException;
import com.epam.xml.parser.model.AbstractMedicine;
import com.epam.xml.parser.model.Medicines;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JaxbParser extends AbstractParser {

    @Override
    public List<AbstractMedicine> parse(String fileName) throws ParseException {
        try (FileReader reader = new FileReader(fileName)) {
            JAXBContext context = JAXBContext.newInstance(Medicines.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Medicines medicines = (Medicines) unmarshaller.unmarshal(reader);
            return medicines.getMedicines();
        } catch (JAXBException | IOException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }
}
