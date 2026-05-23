package com.sbisec.helios.gw.model;

import java.util.Date;
import java.util.List;

// import javax.persistence.Table;

import lombok.Data;

@Data
// @Table(name = "m_role")
public class Role {

    private String roleId;
    private String explain;

    private List<Permission> permissions;

    private String createUser;
    private Date createDt;
    private String updateUser;
    private Date updateDt;
}
