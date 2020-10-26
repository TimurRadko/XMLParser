package com.epam.xml.parser.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LiquidMedicine", propOrder = {
        "weight",
        "alcoholConcentration"
})
public class LiquidMedicine
        extends AbstractMedicine
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    private int weight;
    @XmlElement(name = "alcohol-concentration")
    private int alcoholConcentration;

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
