package com.epam.xml.parser.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "medicine"
})
@XmlRootElement(name = "medicines")
public class Medicines {

    @XmlElementRef(name = "medicine", namespace = "http://com.epam.xml.parser.data/medicines", type = JAXBElement.class)
    private List<JAXBElement<? extends AbstractMedicine>> medicine;

    public List<AbstractMedicine> getMedicines() {
        return getMedicineFromJAXBElement();
    }

    public void setMedicines(List<AbstractMedicine> medicines) {
        ObjectFactory factory = new ObjectFactory();
        for (AbstractMedicine value : medicines) {
            JAXBElement<? extends AbstractMedicine> volume = factory.createMedicine(value);
            medicine = getMedicine();
            medicine.add(volume);
        }
    }

    private List<JAXBElement<? extends AbstractMedicine>> getMedicine() {
        if (medicine == null) {
            medicine = new ArrayList<>();
        }
        return this.medicine;
    }

    private List<AbstractMedicine> getMedicineFromJAXBElement() {
        List<AbstractMedicine> medicines = new ArrayList<>();
        for (JAXBElement<? extends AbstractMedicine> volume : medicine) {
            AbstractMedicine medicine = volume.getValue();
            medicines.add(medicine);
        }
        return medicines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Medicines medicines = (Medicines) o;

        return Objects.equals(medicine, medicines.medicine);
    }

    @Override
    public int hashCode() {
        return medicine != null ? medicine.hashCode() : 0;
    }

    @Override
    public String toString() {
        return getMedicineFromJAXBElement().toString();
    }
}
