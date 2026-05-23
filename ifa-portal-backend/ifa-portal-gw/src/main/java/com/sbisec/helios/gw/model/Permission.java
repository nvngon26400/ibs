package com.sbisec.helios.gw.model;

import java.util.Date;

//import javax.persistence.Table;

import lombok.Data;

@Data
//@Table(name = "m_permission")
public class Permission {

    private String permissionId;
    private String permissionType;
    private String explain;

    private String createUser;
    private Date createDt;
    private String updateUser;
    private Date updateDt;
}
