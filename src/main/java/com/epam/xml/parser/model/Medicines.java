package com.epam.xml.parser.model;

import com.epam.xml.parser.model.medicine.AbstractMedicine;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "medicines"
})
@XmlRootElement(name = "medicines")
public class Medicines {
    @XmlElementRef(name = "medicines", namespace = "http://com.epam.xml.parser.data/medicines",
            type = JAXBElement.class)
    private List<AbstractMedicine> medicines = new ArrayList<>();

    public Medicines() {
    }

    public void setMedicines(List<AbstractMedicine> medicines) {
        this.medicines = medicines;
    }

    public boolean addList(AbstractMedicine medicine) {
        return medicines.add(medicine);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (AbstractMedicine medicine : medicines) {
            builder.append(medicine);
        }
        return builder.toString();
    }
}
