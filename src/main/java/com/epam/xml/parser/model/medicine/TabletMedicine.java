package com.epam.xml.parser.model.medicine;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TabletMedicine", propOrder = {
        "quantity"
})
public class TabletMedicine extends AbstractMedicine {
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    private int quantity;
    @XmlAttribute(name = "concentration")
    private String concentration;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getConcentration() {
        return concentration;
    }

    public void setConcentration(String concentration) {
        this.concentration = concentration;
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

        TabletMedicine that = (TabletMedicine) o;

        if (quantity != that.quantity) {
            return false;
        }
        return Objects.equals(concentration, that.concentration);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + quantity;
        result = 31 * result + (concentration != null ? concentration.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s Quantity: %s; Concentration: %s%n", super.toString(), quantity, concentration);
    }
}