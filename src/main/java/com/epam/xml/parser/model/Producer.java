package com.epam.xml.parser.model;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Producer", propOrder = {
        "producer-name",
        "license-number"
})
public class Producer {
    @XmlElement(required = true)
    private String producerName;
    @XmlElement(required = true)
    private String licenseNumber;

    public Producer() {
    }

    public Producer(String producerName, String licenseNumber) {
        this.producerName = producerName;
        this.licenseNumber = licenseNumber;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
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
