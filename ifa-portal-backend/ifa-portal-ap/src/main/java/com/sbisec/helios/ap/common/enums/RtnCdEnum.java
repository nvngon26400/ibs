package com.sbisec.helios.ap.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * Define the return type of the service interface requested by an external program.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
@JsonFormat(shape = Shape.OBJECT)
public enum RtnCdEnum {

    // @formatter:off
    INFO(   0, "INFO"),
    SUCCESS(1, "SUCCESS"),
    WARNING(2, "WARNING"),
    ERROR(  3, "ERROR"),
    INVALID(4, "INVALID"),
    UPGRADE(5, "UPGRADE");
    // @formatter:on

    private Integer cd;
    private String text;

    private RtnCdEnum(Integer cd, String text) {
        this.cd = cd;
        this.text = text;
    }

    public Integer getCd() {
        return cd;
    }

    public String getText() {
        return text;
    }

}
