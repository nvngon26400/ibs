package com.sbisec.helios.ap.common.entity;

import java.util.Map;

import lombok.Data;

/**
 * Initial setting after login.<br>
 * Contains user information, menus and other information.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
@Data
public class InitEntity {

    private Map<String, Object> user;

}
