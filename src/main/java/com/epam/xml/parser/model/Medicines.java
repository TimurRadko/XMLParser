package com.epam.xml.parser.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "medicines", namespace = "http://com.epam.xml.parser.data/medicines")
public class Medicines {

    @XmlElements({@XmlElement(name = "medicine", namespace = "http://com.epam.xml.parser.data/medicines",
            type = AbstractMedicine.class),
            @XmlElement(name = "tablet", namespace = "http://com.epam.xml.parser.data/medicines",
                    type = TabletMedicine.class),
            @XmlElement(name = "liquid", namespace = "http://com.epam.xml.parser.data/medicines",
                    type = LiquidMedicine.class),})
    private List<AbstractMedicine> medicines = new ArrayList<>();

    public Medicines() {
    }

    @XmlTransient
    public List<AbstractMedicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<AbstractMedicine> medicines) {
        this.medicines = medicines;
    }

    public void addDevice(AbstractMedicine medicine) {
        medicines.add(medicine);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Medicines medicines1 = (Medicines) o;

        return Objects.equals(medicines, medicines1.medicines);
    }

    @Override
    public int hashCode() {
        return medicines != null ? medicines.hashCode() : 0;
    }

    @Override
    public String toString() {
        return medicines.toString();
    }
}
