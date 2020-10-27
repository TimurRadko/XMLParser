package com.epam.xml.parser.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LiquidMedicine")
public class LiquidMedicine extends AbstractMedicine {

    @XmlElement(namespace = "http://com.epam.xml.parser.data/medicines", required = true)
    @XmlSchemaType(name = "positiveInteger")
    private int weight;
    @XmlElement(namespace = "http://com.epam.xml.parser.data/medicines", name = "alcohol-concentration")
    private int alcoholConcentration;

    public LiquidMedicine() {
    }

    public LiquidMedicine(String name, Producer producer, String groupPharmacy, int weight, int alcoholConcentration) {
        super(name, producer, groupPharmacy);
        this.weight = weight;
        this.alcoholConcentration = alcoholConcentration;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int value) {
        this.weight = value;
    }

    public int getAlcoholConcentration() {
        return alcoholConcentration;
    }

    public void setAlcoholConcentration(int value) {
        this.alcoholConcentration = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        LiquidMedicine that = (LiquidMedicine) o;

        if (weight != that.weight) {
            return false;
        }
        return alcoholConcentration == that.alcoholConcentration;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + weight;
        result = 31 * result + alcoholConcentration;
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s Weight: %s; Alcohol Concentration: %s", super.toString(), weight, alcoholConcentration);
    }
}
