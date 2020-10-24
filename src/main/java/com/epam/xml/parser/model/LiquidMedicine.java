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
    public String toString() {
        return String.format("%s Weight: %s; Alcohol Concentration: %s", super.toString(), weight, alcoholConcentration);
    }
}
