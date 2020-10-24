package com.epam.xml.parser.model;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TabletMedicine", propOrder = {
        "quantity"
})
public class TabletMedicine
        extends AbstractMedicine
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected int quantity;
    @XmlAttribute(name = "concentration")
    protected String concentration;

    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int value) {
        this.quantity = value;
    }

    public String getConcentration() {
        if (concentration == null) {
            return "medium";
        } else {
            return concentration;
        }
    }

    public void setConcentration(String value) {
        this.concentration = value;
    }

    @Override
    public String toString() {
        return String.format("%s Quantity: %s; Concentration: %s%n", super.toString(), quantity, concentration);
    }
}