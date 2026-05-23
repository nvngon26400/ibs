package com.sbisec.helios.ap.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * Define the type of log file.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
@JsonFormat(shape = Shape.OBJECT)
public enum LogTypeEnum {

    API("API"), WEB("WEB"), Security("STY"), REQUEST_PARAM("PARAM_LOG"), ACCESS("ACCESS_LOG");

    private String type;

    private LogTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
