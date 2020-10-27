package com.epam.xml.parser.model;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Producer")
public class Producer {

    @XmlElement(namespace = "http://com.epam.xml.parser.data/medicines", name = "producer-name", required = true)
    protected String producerName;
    @XmlElement(namespace = "http://com.epam.xml.parser.data/medicines", name = "license-number", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String licenseNumber;

    public Producer() {
    }

    public Producer(String producerName, String licenseNumber) {
        this.producerName = producerName;
        this.licenseNumber = licenseNumber;
    }

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Producer producer = (Producer) o;

        if (!Objects.equals(producerName, producer.producerName)) {
            return false;
        }
        return Objects.equals(licenseNumber, producer.licenseNumber);
    }

    @Override
    public int hashCode() {
        int result = producerName != null ? producerName.hashCode() : 0;
        result = 31 * result + (licenseNumber != null ? licenseNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("produced %s corporate, number of license %s,", producerName, licenseNumber);
    }
}
