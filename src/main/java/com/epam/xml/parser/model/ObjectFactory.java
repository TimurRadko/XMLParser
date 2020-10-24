package com.epam.xml.parser.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
    private final static QName _Liquid_QNAME =
            new QName("http://com.epam.xml.parser.data/medicines", "liquid");
    private final static QName _Tablet_QNAME =
            new QName("http://com.epam.xml.parser.data/medicines", "tablet");
    private final static QName _Medicine_QNAME =
            new QName("http://com.epam.xml.parser.data/medicines", "medicine");

    public ObjectFactory() {
    }

    public TabletMedicine createTabletMedicine() {
        return new TabletMedicine();
    }

    public AbstractMedicine createAbstractMedicine() {
        return new AbstractMedicine();
    }

    public Medicines createMedicines() {
        return new Medicines();
    }

    public LiquidMedicine createLiquidMedicine() {
        return new LiquidMedicine();
    }

    public Producer createProducer() {
        return new Producer();
    }

    @XmlElementDecl(namespace = "http://com.epam.xml.parser.data/medicines",
            name = "liquid", substitutionHeadNamespace = "http://com.epam.xml.parser.data/medicines",
            substitutionHeadName = "medicine")
    public JAXBElement<LiquidMedicine> createLiquid(LiquidMedicine value) {
        return new JAXBElement<>(_Liquid_QNAME, LiquidMedicine.class, null, value);
    }

    @XmlElementDecl(namespace = "http://com.epam.xml.parser.data/medicines",
            name = "tablet", substitutionHeadNamespace = "http://com.epam.xml.parser.data/medicines",
            substitutionHeadName = "medicine")
    public JAXBElement<TabletMedicine> createTablet(TabletMedicine value) {
        return new JAXBElement<>(_Tablet_QNAME, TabletMedicine.class, null, value);
    }

    @XmlElementDecl(namespace = "http://com.epam.xml.parser.data/medicines", name = "medicine")
    public JAXBElement<AbstractMedicine> createMedicine(AbstractMedicine value) {
        return new JAXBElement<>(_Medicine_QNAME, AbstractMedicine.class, null, value);
    }
}
