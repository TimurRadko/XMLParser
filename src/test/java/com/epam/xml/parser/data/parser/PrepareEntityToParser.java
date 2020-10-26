package com.epam.xml.parser.data.parser;

import com.epam.xml.parser.model.Producer;
import com.epam.xml.parser.model.TabletMedicine;

public class PrepareEntityToParser {
    private final TabletMedicine tabletMedicine = new TabletMedicine();
    private final Producer expectedProducer = new Producer();
    private static final String VALID_NAME = "Aspirin";
    private static final int VALID_QUANTITY = 100;
    private static final  String VALID_CONCENTRATION = "low";
    private static final String VALID_PHARMACY_GROUP = "painkiller";
    private static final String VALID_ID = "ID1";
    private static final String VALID_PRODUCER_NAME = "Bayer";

    public TabletMedicine getMedicine() {
        prepareEntity();
        return tabletMedicine;
    }

    private void prepareEntity() {
        tabletMedicine.setName(VALID_NAME);
        tabletMedicine.setQuantity(VALID_QUANTITY);
        tabletMedicine.setConcentration(VALID_CONCENTRATION);
        tabletMedicine.setGroupPharmacy(VALID_PHARMACY_GROUP);
        expectedProducer.setLicenseNumber(VALID_ID);
        expectedProducer.setProducerName(VALID_PRODUCER_NAME);
        tabletMedicine.setProducer(expectedProducer);
    }
}
