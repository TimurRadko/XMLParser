package com.epam.xml.parser.model;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Producer", propOrder = {
        "producerName",
        "licenseNumber"
})
public class Producer {

    @XmlElement(name = "producer-name", required = true)
    protected String producerName;
    @XmlElement(name = "license-number", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String licenseNumber;

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String value) {
        this.producerName = value;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String value) {
        this.licenseNumber = value;
    }

    @Override
    public String toString() {
        return String.format("produced %s corporate, number of license %s,", producerName, licenseNumber);
    }
}
