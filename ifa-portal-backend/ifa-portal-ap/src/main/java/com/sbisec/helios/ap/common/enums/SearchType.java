package com.sbisec.helios.ap.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum SearchType {

	EQUAL    (1, "と等しい"),
	BEGINNING(2, "で始まる"),
	INCLUDING(3, "を含む");

    private final int id;
    private final String label;

    private SearchType(int id, String label) {

        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}