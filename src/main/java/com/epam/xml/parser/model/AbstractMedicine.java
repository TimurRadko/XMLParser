package com.epam.xml.parser.model;

import javax.xml.bind.annotation.*;

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

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer value) {
        this.producer = value;
    }

    public String getGroupPharmacy() {
        return groupPharmacy;
    }

    public void setGroupPharmacy(String value) {
        this.groupPharmacy = value;
    }

    @Override
    public String toString() {
        return String.format("%s, which %s with pharmacy group is %s.%n", name, producer, groupPharmacy);
    }
}