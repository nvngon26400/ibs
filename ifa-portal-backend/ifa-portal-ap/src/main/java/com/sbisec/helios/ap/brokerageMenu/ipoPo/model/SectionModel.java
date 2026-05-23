package com.sbisec.helios.ap.brokerageMenu.ipoPo.model;

import com.sbibits.earth.model.ModelBase;

@SuppressWarnings("serial")
public class SectionModel extends ModelBase {
    private String sectionId;
    private String sectionName;

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

}
