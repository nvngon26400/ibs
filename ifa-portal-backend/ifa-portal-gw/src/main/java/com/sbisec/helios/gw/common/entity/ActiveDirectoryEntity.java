package com.sbisec.helios.gw.common.entity;

import lombok.Data;

/**
 * Active Directory entity.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
@Data
public class ActiveDirectoryEntity {
    private String url;
    private String domain;

    public static ActiveDirectoryEntity instance(String url, String domain) {
        return new ActiveDirectoryEntity(url, domain);
    }

    private ActiveDirectoryEntity(String url, String domain) {
        this.url = url;
        this.domain = domain;
    }
}
