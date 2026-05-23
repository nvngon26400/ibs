package com.sbisec.helios.ap.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * Define keywords in the log file.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
@JsonFormat(shape = Shape.OBJECT)
public enum LogKeyEnum {

    user_id("user_id"), batch_id("batch_id"), kind("kind"), ip("ip"), session_id("framework_session_id");

    private String key;

    private LogKeyEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
