package com.epam.xml.parser.model.medicine;

import com.epam.xml.parser.model.Producer;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractMedicine", propOrder = {
        "name",
        "producer"
})
@XmlSeeAlso({
        TabletMedicine.class,
        LiquidMedicine.class
})
public class AbstractMedicine {
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private Producer producer = new Producer();
    @XmlAttribute(name = "group-pharmacy", required = true)
    private String groupPharmacy;

    public AbstractMedicine() {
    }

    public AbstractMedicine(String name, Producer producer, String groupPharmacy) {
        this.name = name;
        this.producer = producer;
        this.groupPharmacy = groupPharmacy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public String getGroupPharmacy() {
        return groupPharmacy;
    }

    public void setGroupPharmacy(String groupPharmacy) {
        this.groupPharmacy = groupPharmacy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractMedicine that = (AbstractMedicine) o;

        if (!Objects.equals(name, that.name)) {
            return false;
        }
        if (!Objects.equals(producer, that.producer)) {
            return false;
        }
        return Objects.equals(groupPharmacy, that.groupPharmacy);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (producer != null ? producer.hashCode() : 0);
        result = 31 * result + (groupPharmacy != null ? groupPharmacy.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s, which %s with pharmacy group is %s.%n", name, producer, groupPharmacy);
    }
}