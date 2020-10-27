package com.epam.xml.parser.model;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TabletMedicine")
public class TabletMedicine
        extends AbstractMedicine
{

    @XmlElement(namespace = "http://com.epam.xml.parser.data/medicines", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected int quantity;
    @XmlAttribute(name = "concentration")
    protected String concentration;

    public TabletMedicine() {
    }

    public TabletMedicine(int quantity, String concentration) {
        this.quantity = quantity;
        this.concentration = concentration;
    }

    public TabletMedicine(String name, Producer producer, String groupPharmacy, int quantity, String concentration) {
        super(name, producer, groupPharmacy);
        this.quantity = quantity;
        this.concentration = concentration;
    }

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